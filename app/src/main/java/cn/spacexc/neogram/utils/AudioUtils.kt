package cn.spacexc.neogram.utils

import android.media.MediaCodec
import android.media.MediaExtractor
import android.media.MediaFormat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jtransforms.fft.DoubleFFT_1D
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.sqrt

suspend fun decodeToPCM(file: File): ShortArray = withContext(Dispatchers.IO) {
    val extractor = MediaExtractor()
    extractor.setDataSource(file.absolutePath)

    // 找到音频轨道
    val trackIndex = (0 until extractor.trackCount).firstOrNull {
        extractor.getTrackFormat(it).getString(MediaFormat.KEY_MIME)?.startsWith("audio/") == true
    } ?: error("No audio track found")

    extractor.selectTrack(trackIndex)
    val format = extractor.getTrackFormat(trackIndex)
    val mime = format.getString(MediaFormat.KEY_MIME)!!
    val codec = MediaCodec.createDecoderByType(mime)
    codec.configure(format, null, null, 0)
    codec.start()

    val output = ByteArrayOutputStream()
    val inputBuffers = codec.inputBuffers
    val outputBuffers = codec.outputBuffers
    val bufferInfo = MediaCodec.BufferInfo()
    var inputDone = false
    var outputDone = false

    while (!outputDone) {
        if (!inputDone) {
            val inIndex = codec.dequeueInputBuffer(10000)
            if (inIndex >= 0) {
                val buffer = inputBuffers[inIndex]
                val sampleSize = extractor.readSampleData(buffer, 0)
                if (sampleSize < 0) {
                    codec.queueInputBuffer(inIndex, 0, 0, 0L, MediaCodec.BUFFER_FLAG_END_OF_STREAM)
                    inputDone = true
                } else {
                    val time = extractor.sampleTime
                    codec.queueInputBuffer(inIndex, 0, sampleSize, time, 0)
                    extractor.advance()
                }
            }
        }

        val outIndex = codec.dequeueOutputBuffer(bufferInfo, 10000)
        if (outIndex >= 0) {
            val buffer = outputBuffers[outIndex]
            val chunk = ByteArray(bufferInfo.size)
            buffer.get(chunk)
            output.write(chunk)
            buffer.clear()
            codec.releaseOutputBuffer(outIndex, false)

            if (bufferInfo.flags and MediaCodec.BUFFER_FLAG_END_OF_STREAM != 0) {
                outputDone = true
            }
        }
    }

    codec.stop()
    codec.release()
    extractor.release()

    val pcmBytes = output.toByteArray()
    val shorts = ByteBuffer.wrap(pcmBytes)
        .order(ByteOrder.LITTLE_ENDIAN)
        .asShortBuffer()
    ShortArray(shorts.limit()).also { shorts.get(it) }
}

fun extractWaveformUsingJTransforms(
    pcm: ShortArray,
    sampleRate: Int = 44100,
    frameSize: Int = 1024,
    hopSize: Int = 512,
    lowBand: IntRange = 0..1000,
    highBand: IntRange = 2000..4000
): Pair<List<Float>, List<Float>> {
    val fft = DoubleFFT_1D(frameSize.toLong())
    val lowFreq = mutableListOf<Float>()
    val highFreq = mutableListOf<Float>()
    val freqPerBin = (sampleRate / 2.0) / (frameSize / 2)

    for (i in 0 until pcm.size - frameSize step hopSize) {
        val frame = DoubleArray(frameSize)
        for (j in frame.indices) {
            frame[j] = pcm[i + j] / Short.MAX_VALUE.toDouble()
        }

        // Prepare real FFT input: double[2N]
        val fftData = DoubleArray(frameSize * 2)
        frame.copyInto(fftData, 0)

        fft.realForwardFull(fftData)

        val magnitudes = DoubleArray(frameSize / 2)
        for (k in 0 until frameSize / 2) {
            val re = fftData[2 * k]
            val im = fftData[2 * k + 1]
            magnitudes[k] = sqrt(re * re + im * im)
        }

        val low = lowBand.mapNotNull { freq ->
            val bin = (freq / freqPerBin).toInt()
            magnitudes.getOrNull(bin)
        }.average().toFloat()

        val high = highBand.mapNotNull { freq ->
            val bin = (freq / freqPerBin).toInt()
            magnitudes.getOrNull(bin)
        }.average().toFloat()

        lowFreq.add(low)
        highFreq.add(high)
    }

    // Normalize
    val max = (lowFreq + highFreq).maxOrNull() ?: 1f
    return Pair(
        lowFreq.map { (it / max).coerceIn(0f, 1f) },
        highFreq.map { (it / max).coerceIn(0f, 1f) }
    )
}
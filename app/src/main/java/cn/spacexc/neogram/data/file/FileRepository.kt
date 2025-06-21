package cn.spacexc.neogram.data.file

import androidx.compose.runtime.mutableStateMapOf
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.utils.LogUtils
import org.drinkless.tdlib.TdApi

object FileRepository {
    val downloadList = mutableStateMapOf<Int, DownloadState>()

    fun TdApi.Object.downloadHandler() {
        when (this) {
            is TdApi.UpdateFile -> {
                LogUtils.info("UpdateFileDownload", "$this")
                if (downloadList[file.id] == null) {
                    downloadList[file.id] =
                        DownloadState(null, file.expectedSize, file.local.downloadedSize)
                }
                if (file.local.isDownloadingCompleted) {
                    downloadList[file.id] = downloadList[file.id]!!.copy(
                        file.local.path,
                        file.local.downloadedSize,
                        file.local.downloadedSize
                    )
                } else {
                    downloadList[file.id] = downloadList[file.id]!!.copy(
                        null,
                        file.expectedSize,
                        file.local.downloadedSize
                    )
                }
            }
        }
    }

    fun downloadFile(file: TdApi.File) {
        TdClient.send(TdApi.DownloadFile(file.id, 1, 0, 0, false), {
            if (it is TdApi.File && it.local.isDownloadingCompleted) {
                downloadList[file.id] =
                    DownloadState(it.local.path, it.expectedSize, it.expectedSize)
            }

        })
    }
}

data class DownloadState(
    val localPath: String?,
    val expectedSize: Long,
    val downloadedSize: Long
)
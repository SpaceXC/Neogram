package cn.spacexc.neogram.settings

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import cn.spacexc.neogram.Application
import cn.spacexc.neogram.proto.settings.NeogramSettings
import com.google.protobuf.InvalidProtocolBufferException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream
import java.io.OutputStream

object SettingsSerializer : Serializer<NeogramSettings> {
    override val defaultValue: NeogramSettings = NeogramSettings.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): NeogramSettings {
        try {
            return NeogramSettings.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: NeogramSettings,
        output: OutputStream
    ) = t.writeTo(output)
}

val Context.settingsDataStore: DataStore<NeogramSettings> by dataStore(
    fileName = "settings.pb",
    serializer = SettingsSerializer
)

object NeogramSettings {
    val dataFlow = Application.getApplication().settingsDataStore.data
    var data: NeogramSettings = NeogramSettings.getDefaultInstance()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            Application.getApplication().settingsDataStore.data.collect {
                data = it
            }
        }
    }

    @Composable
    fun neogramSettings(): State<NeogramSettings> =
        dataFlow.collectAsState(data)
}

suspend fun Context.updateConfiguration(newConfiguration: NeogramSettings.() -> NeogramSettings) {
    settingsDataStore.updateData { currentConfig ->
        currentConfig.newConfiguration()
    }
}
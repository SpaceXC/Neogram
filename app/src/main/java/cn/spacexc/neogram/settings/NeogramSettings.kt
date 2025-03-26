package cn.spacexc.neogram.settings

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import cn.spacexc.neogram.proto.settings.NeogramSettings
import com.google.protobuf.InvalidProtocolBufferException
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

@Composable
fun neogramSettings(): State<NeogramSettings> = LocalContext.current.settingsDataStore.data.collectAsState(NeogramSettings.getDefaultInstance())

suspend fun Context.updateConfiguration(newConfiguration: NeogramSettings.() -> NeogramSettings) {
    settingsDataStore.updateData { currentConfig ->
        currentConfig.newConfiguration()
    }
}
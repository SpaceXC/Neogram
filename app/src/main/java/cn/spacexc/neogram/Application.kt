package cn.spacexc.neogram

import android.app.Application
import android.os.Build
import cn.spacexc.neogram.data.TdClient
import org.drinkless.tdlib.TdApi
import org.thunderdog.challegram.voip.NLoader
import java.io.File
import java.util.Locale


class Application : Application() {
    lateinit var tdLibParams: TdLibParams

    init {
        System.loadLibrary("tdjni")
        System.loadLibrary("tgcallsjni")
        System.loadLibrary("leveldbjni")
        System.loadLibrary("tgxjni")
        System.loadLibrary("tgcallsjni")
    }

    override fun onCreate() {
        super.onCreate()

        val tglogs = File(filesDir, "tglogs")
        tglogs.mkdir()
        val tgvoip = File(filesDir, "tgvoip")
        tgvoip.mkdir()

        val dbPath = File(filesDir, "tgDb")
        dbPath.mkdir()
        mApplication = this
        NLoader.loadLibrary()

        tdLibParams = TdLibParams(
            databaseDirectory = File(filesDir, "tgDb").absolutePath,
            useMessageDatabase = true,
            useSecretChats = true,
            useFileDatabase = true,
            systemLanguageCode = Locale.getDefault().language,
            deviceModel = getDeviceName(),
            systemVersion = "Android ${Build.VERSION.SDK_INT}",
            applicationVersion = "${getVersionName()} ${getReleaseNumber()}",
            enableStorageOptimizer = true,
            apiId = apiId,
            apiHash = apiHash
        )
        TdClient.send(TdApi.SetLogVerbosityLevel(1))
        TdClient.send(TdApi.SetOption("notification_group_count_max", TdApi.OptionValueInteger(10)))
    }

    companion object {
        private lateinit var mApplication: cn.spacexc.neogram.Application
        fun getApplication(): cn.spacexc.neogram.Application = mApplication

        fun getVersionName(): String {
            val packageInfo =
                getApplication().packageManager.getPackageInfo(getApplication().packageName, 0)
            return packageInfo.versionName ?: ""
        }

        fun getReleaseNumber(): Long {
            val packageInfo =
                getApplication().packageManager.getPackageInfo(getApplication().packageName, 0)
            return if (Build.VERSION.SDK_INT >= 28) packageInfo.longVersionCode else packageInfo.versionCode.toLong()
        }
    }

    private fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.lowercase(Locale.getDefault())
                .startsWith(manufacturer.lowercase(Locale.getDefault()))
        ) {
            capitalize(model)
        } else {
            capitalize(manufacturer) + model
        }
    }
    private fun capitalize(s: String?): String {
        if (s.isNullOrEmpty()) {
            return ""
        }
        val first = s[0]
        return if (Character.isUpperCase(first)) {
            s
        } else {
            first.uppercaseChar().toString() + s.substring(1)
        }
    }
}

data class TdLibParams(
    val databaseDirectory: String,
    val useMessageDatabase: Boolean,
    val useSecretChats: Boolean,
    val useFileDatabase: Boolean,
    val systemLanguageCode: String,
    val deviceModel: String,
    val systemVersion: String,
    val applicationVersion: String,
    val enableStorageOptimizer: Boolean,
    val apiId: Int,
    val apiHash: String
)
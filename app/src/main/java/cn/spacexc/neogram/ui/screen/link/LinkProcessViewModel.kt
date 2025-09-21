package cn.spacexc.neogram.ui.screen.link

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.core.net.toUri
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.utils.LogUtils
import org.drinkless.tdlib.TdApi

class LinkProcessViewModel : ViewModel() {
    fun processLink(link: String) {
        val url = link.toUri()
        val host = url.host
        when (host) {
            "t.me" -> {
                TdClient.send(TdApi.GetInternalLinkType(link), {
                    LogUtils.info("GetInternalLinkType", it.toString())
                    when(it) {

                    }
                })
            }
        }
    }
}
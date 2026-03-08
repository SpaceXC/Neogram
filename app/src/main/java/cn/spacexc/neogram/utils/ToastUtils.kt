package cn.spacexc.neogram.utils

import android.widget.Toast
import cn.spacexc.neogram.Application

object ToastUtils {
    fun toast(message: String) {
        Toast.makeText(Application.getApplication(), message, Toast.LENGTH_SHORT).show()
        //Application.toastContent = message
    }
}
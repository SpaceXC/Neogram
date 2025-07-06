package org.thunderdog.challegram.voip;


import static cn.spacexc.neogram.utils.LogUtilsKt.TAG_VOIP;

import android.util.Log;

class VLog {
    public static void v(String msg) {
        Log.v(TAG_VOIP, msg);
    }

    public static void d(String msg) {
        Log.d(TAG_VOIP, msg);
    }

    public static void i(String msg) {
        Log.i(TAG_VOIP, msg);
    }

    public static void w(String msg) {
        Log.w(TAG_VOIP, msg);
    }

    public static void e(String msg) {
        Log.e(TAG_VOIP, msg);
    }

    public static void e(Throwable x) {
        x.printStackTrace();
        Log.e(TAG_VOIP, x.toString());
        // e(null, x);
    }

    public static void e(String msg, Throwable x) {
        x.printStackTrace();
        Log.e(TAG_VOIP, msg);
		/*StringWriter sw=new StringWriter();
		if(!TextUtils.isEmpty(msg)){
			sw.append(msg);
			sw.append(": ");
		}
		PrintWriter pw=new PrintWriter(sw);
		x.printStackTrace(pw);
		String[] lines=sw.toString().split("\n");
		for(String line:lines)
			e(line);*/
    }
}

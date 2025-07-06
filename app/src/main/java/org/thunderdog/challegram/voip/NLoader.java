/*
 * This file is a part of Telegram X
 * Copyright © 2014 (tgx-android@pm.me)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 * File created on 07/02/2017
 */
package org.thunderdog.challegram.voip;

import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.annotation.OptIn;
import androidx.media3.common.C;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.decoder.ffmpeg.FfmpegLibrary;
import androidx.media3.decoder.flac.FlacLibrary;
import androidx.media3.decoder.opus.OpusLibrary;
import androidx.media3.decoder.vp9.VpxLibrary;

import com.getkeepsafe.relinker.ReLinker;
import com.getkeepsafe.relinker.ReLinkerInstance;

import org.webrtc.SoftwareVideoEncoderFactory;
import org.webrtc.VideoCodecInfo;

import java.util.ArrayList;
import java.util.Locale;

import cn.spacexc.neogram.Application;
import leveldb.LevelDB;


public class NLoader implements ReLinker.Logger {

    private static volatile boolean loaded;

    private static volatile NLoader instance;

    public static NLoader instance() {
        if (instance == null) {
            synchronized (NLoader.class) {
                if (instance == null) {
                    instance = new NLoader();
                }
            }
        }
        return instance;
    }

    private static void loadLibraryImpl(ReLinkerInstance reLinker, String library, @Nullable String version) {
        long ms = SystemClock.uptimeMillis();
        reLinker.loadLibrary(Application.Companion.getApplication(), library, version);
        android.util.Log.v("tgx", "Loaded " + library + " in " + (SystemClock.uptimeMillis() - ms) + "ms");
    }

    @OptIn(markerClass = UnstableApi.class)
    public static synchronized boolean loadLibrary() {
        if (!loaded) {
            try {
                ReLinkerInstance reLinker = ReLinker.recursively().log(NLoader.instance());
                loadLibraryImpl(reLinker, "c++_shared", BuildConfig.NDK_VERSION);
                loadLibraryImpl(reLinker, "cryptox", BuildConfig.OPENSSL_VERSION_FULL);
                loadLibraryImpl(reLinker, "sslx", BuildConfig.OPENSSL_VERSION_FULL);
                loadLibraryImpl(reLinker, "tdjni", BuildConfig.TDLIB_VERSION);
                loadLibraryImpl(reLinker, "leveldbjni", BuildConfig.LEVELDB_VERSION);
                loadLibraryImpl(reLinker, "tgcallsjni", BuildConfig.JNI_VERSION /*TODO: separate variable?*/);
                loadLibraryImpl(reLinker, "tgxjni", BuildConfig.JNI_VERSION);
                OpusLibrary.setLibraries(C.CRYPTO_TYPE_UNSUPPORTED);
                VpxLibrary.setLibraries(C.CRYPTO_TYPE_UNSUPPORTED);
                FlacLibrary.setLibraries();
                FfmpegLibrary.setLibraries();
          /*if (BuildConfig.DEBUG) {
            android.util.Log.v("tgx", String.format(Locale.US,
              "leveldb %s, libopus %s, libvpx %s, ffmpeg %s, tgvoip %s, tgcalls %s",
              LevelDB.getVersion(),
              OpusLibrary.getVersion(),
              VpxLibrary.getVersion(),
              FfmpegLibrary.getVersion(),
              VoIPController.getVersion(),
              TextUtils.join("+", N.getTgCallsVersions())
            ));
            VideoCodecInfo[] softwareVideoCodecs = new SoftwareVideoEncoderFactory().getSupportedCodecs();
          }*/
            } catch (Throwable t) {
                RuntimeException e = new IllegalStateException(instance().collectLog() + "\n" + t.getMessage(), t);
                e.setStackTrace(t.getStackTrace());
                throw e;
            }
            loaded = true;
        }
        return loaded;
    }

    private ArrayList<String> messages;

    @Override
    public void log(String message) {
        synchronized (this) {
            if (messages == null) {
                messages = new ArrayList<>();
            }
            messages.add(message);
        }
    }

    public @Nullable String collectLog() {
        String log = null;
        synchronized (this) {
            if (messages != null && !messages.isEmpty()) {
                log = "==== ReLinker ====\n" + TextUtils.join("\n", messages) + "\n==== ReLinker END ====\n";
            }
            messages = null;
        }
        return log;
    }
}

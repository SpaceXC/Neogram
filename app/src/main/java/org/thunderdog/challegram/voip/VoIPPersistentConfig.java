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
 * File created on 19/03/2023
 */
package org.thunderdog.challegram.voip;

import androidx.annotation.NonNull;

import java.io.File;

import cn.spacexc.neogram.Application;

public class VoIPPersistentConfig {
    static @NonNull File getVoipConfigFile() {
        File dir = new File(Application.Companion.getApplication().getFilesDir(), "tgvoip");
        return new File(dir, "voip_persistent_state.json");
    }

    public static long getVoipConfigFileSize() {
        File file = getVoipConfigFile();
        return file.exists() ? file.length() : 0;
    }
}

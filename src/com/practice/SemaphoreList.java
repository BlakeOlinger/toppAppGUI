package com.practice;

import com.lib.PathsList;

import java.io.IOException;
import java.nio.file.Files;

class SemaphoreList {
    static boolean isSWactionOpen = true;
    static boolean isSWMSpreview = IniFileDO.getFieldValue(IniFileDO.USE_SW_INSTANCE_PREVIEW);

    static boolean isIsSWactionOpen() {
        try {
            return Files.readString(PathsList.toppAppConfig).substring(1, 2).compareTo("0") == 0;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

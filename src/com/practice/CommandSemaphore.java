package com.practice;

import java.io.IOException;
import java.nio.file.Files;

class CommandSemaphore {
    static boolean open = true;

    static void close() {
        try {
            Files.writeString(GUIconfigDO.GUIconfig, "01");

            open = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

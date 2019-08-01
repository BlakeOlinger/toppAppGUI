package com.practice;

import java.io.IOException;
import java.nio.file.Files;

class CommandSemaphore {
    static boolean open = true;

    static boolean isOpen() {
        try {
            return Files.readString(PathsList.toppAppConfig).substring(1, 2).compareTo("0") == 0;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

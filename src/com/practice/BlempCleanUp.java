package com.practice;

import java.io.IOException;
import java.nio.file.Files;

class BlempCleanUp implements Runnable{
    private final Thread thread;

    BlempCleanUp() {
        thread = new Thread(this, "Tmp DDO File Clean Up");
    }

    void clearDDO() {
        thread.start();
    }

    @Override
    public void run() {
        try {
            Files.writeString(Config.BLEMP_DDO_PATH, "");
        } catch (IOException ignore) {
        }
    }
}

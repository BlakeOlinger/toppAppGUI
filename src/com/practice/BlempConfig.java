package com.practice;

import java.io.IOException;
import java.nio.file.Files;

class BlempConfig implements Runnable{
    private final Thread thread;

    BlempConfig() {
        thread = new Thread(this, "Config.Blemp Loader");
    }

    void load() {
        thread.start();
    }

    @Override
    public void run() {
        try {
            DDO.blempDDO = Files.readString(Config.BLEMP_CONFIG_PATH)
                    .split("\\$");
        } catch (IOException ignore) {
        }
    }
}

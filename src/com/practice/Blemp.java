package com.practice;

import java.io.IOException;
import java.nio.file.Files;

public class Blemp implements Runnable{
    private final Thread thread;
    private final String userInput;

    Blemp(String userInput) {
        thread = new Thread(this, "Blemp IO");
        this.userInput = userInput;
    }

    void start() {
        thread.start();
    }

    @Override
    public void run() {
        Config.blempDDO.set(1, userInput);

        var equationOutput = Config.blempDDO.get(0) + "$" + Config.blempDDO.get(1) +
                "$" + Config.blempDDO.get(2) + "$";

        try {
            Files.writeString(Config.BLEMP_DDO_PATH, equationOutput);
        } catch (IOException ignore) {
        }
    }
}

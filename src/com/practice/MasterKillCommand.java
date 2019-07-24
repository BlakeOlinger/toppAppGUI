package com.practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class MasterKillCommand {
    static void kill() {
        var masterConfigPath = Paths.get(Main.userRoot +
                "programFiles/config/master.config");

        var command = "01";

        do {
            try {
                Files.writeString(masterConfigPath, "11");
                command = Files.readString(masterConfigPath).substring(0, 1);

                Thread.sleep(1000);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } while (command.compareTo("0") == 0);
    }
}

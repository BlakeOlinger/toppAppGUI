package com.practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class MasterKillCommand {
    static void kill() {
        var masterConfigPath = Paths.get("programFiles/config/master.config");

        try {
            Files.writeString(masterConfigPath, "11");
        } catch (IOException ignore) {
        }
    }
}

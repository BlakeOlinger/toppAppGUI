package com.practice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class MasterKillCommand {
    static void kill() {
        var masterConfigPath = "programFiles/config/master.config";

        if (new File(masterConfigPath).exists()) {

            try (var masterConfig = new FileOutputStream(masterConfigPath)) {
                System.out.println(" Sending Master Command - End All Processes");

                var command = (int) 'x';

                masterConfig.write(command);
            } catch (IOException ignore) {
            }

        } else {
            System.out.println(" ERROR: Master Config File Not Found");
        }
    }
}

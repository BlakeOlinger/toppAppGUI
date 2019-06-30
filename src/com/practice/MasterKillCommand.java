package com.practice;

import java.io.IOException;

class MasterKillCommand {
    static void kill() {
        try {
            // an 'x' command to master.config tells the master to kill all services
            // and close the app completely
            System.out.println(" Sending Master Command - End All Processes");
        var process = Runtime.getRuntime().exec("cmd.exe /c echo x > " +
                Config.DBKillPath);

            process.waitFor();
        } catch (InterruptedException | IOException ignore) {
        }
    }
}

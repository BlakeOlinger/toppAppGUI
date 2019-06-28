package com.practice;

import com.practice.fileio.Config;

import java.io.IOException;

class MasterKillCommand {
    static void kill() {
        try {
            // an 'x' command to master.config tells the master to kill all services
            // and close the app completely
        var process = Runtime.getRuntime().exec("cmd.exe /c echo x > " +
                Config.DBKillPath);

            process.waitFor();
        } catch (InterruptedException | IOException ignore) {
        }
    }
}

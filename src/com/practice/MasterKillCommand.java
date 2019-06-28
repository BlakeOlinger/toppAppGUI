package com.practice;

import com.practice.fileio.Config;

import java.io.IOException;

class MasterKillCommand {
    static void kill() {
        try {
        var process = Runtime.getRuntime().exec("cmd.exe /c echo 1 > " +
                Config.DBKillPath);

            process.waitFor();
        } catch (InterruptedException | IOException ignore) {
        }
    }
}

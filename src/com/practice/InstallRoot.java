package com.practice;

import java.nio.file.Path;
import java.nio.file.Paths;

class InstallRoot {

    static String getInstallRoot() {
        var currentDirectory = Paths.get("").toAbsolutePath();

        var workingDirectoryChunks = currentDirectory.toString().split("\\\\");

        var installRoot = "C:\\Users\\" + workingDirectoryChunks[2] +
                "\\Desktop\\test install\\";

        System.out.println(" - Install Root - " + installRoot);

        return  installRoot;
    }
}

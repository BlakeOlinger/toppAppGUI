package com.practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

final class BlobDirectory {
    static boolean validateLocalBlobDatabaseInstance(Path installDirectory) {
        var fileCheckStringPath = installDirectory.toString() + "\\blob\\.git";

        var fileCheck = Paths.get(fileCheckStringPath);

        if (Files.exists(fileCheck)) {
            System.out.println(" - Local Blob Database Instance - Found");

            if (!Files.exists(Paths.get(installDirectory.toString() + "\\toppAppDBdaemon.jar"))) {
                if (Internet.isConnected()) {
                    System.out.println(" - Syncing local database instance with remote");

                    try {
                        var process = new ProcessBuilder("cmd.exe", "/c", "cd",
                                installDirectory.toString() + "\\blob", "&&", "git", "pull")
                                .start();

                        process.waitFor();

                        process.destroy();

                        System.out.println(" - Local database instance updated");
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                } else
                    System.out.println(" - Could not update local database instance - no internet connection");
            }

            return true;
        } else {

            if (Internet.isConnected()) {
                try {
                    System.out.println(" - Local Blob Database Instance - Not Found");

                    var process = new ProcessBuilder("cmd.exe", "/c", "cd",
                            installDirectory.toString(), "&&", "git", "clone",
                            "https://github.com/BlakeOlinger/blob").start();

                    process.waitFor();

                    process.destroy();

                    if (Files.exists(fileCheck)) {
                        System.out.println(" - Local Blob Database Instance - Created");

                        return true;
                    }

                    throw new IOException();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();

                    System.out.println("TOPP App GUI - Exit");

                    return false;
                }
            }
            return false;
        }
    }
}

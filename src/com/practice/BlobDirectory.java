package com.practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

final class BlobDirectory {
    static boolean validateLocalBlobDatabaseInstance(Path installDirectory) {
        var fileCheckStringPath = installDirectory.toString() + "\\blob\\.git";

        var fileCheck = Paths.get(fileCheckStringPath);

        if (Files.exists(fileCheck)) {
            System.out.println(" - Local Blob Database Instance - Found");

            return true;
        } else {

            if (Internet.connected()) {
                try {
                    System.out.println(" - Local Blob Database Instance - Not Found");

                    var process = new ProcessBuilder("cmd.exe", "/c", "cd",
                            installDirectory.toString(), "&&", "git", "clone",
                            "https://github.com/BlakeOlinger/blob").start();

                    process.waitFor();

                    process.destroy();

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

    static ArrayList<Path> getAvailableBlempFiles(Path blobDirectory) {
        var paths = new ArrayList<Path>();

        try (var fileListStream = Files.list(blobDirectory)) {
            var tempPaths = new ArrayList<Path>();

            fileListStream.forEach(tempPaths::add);

            for(Path path: tempPaths)
                if (path.toString().contains(".blemp"))
                    paths.add(path);

            return paths.size() == 0 ? null : paths;
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }
}

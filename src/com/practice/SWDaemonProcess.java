package com.practice;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.RecursiveAction;

final class SWDaemonProcess extends RecursiveAction {
    private final Path swExe;

    SWDaemonProcess(Path swExe) {
        this.swExe = swExe;
    }

    @Override
    protected void compute() {
        try {
            var process = new ProcessBuilder("cmd.exe", "/c",
                    swExe.toString()).start();

            process.waitFor();

            process.destroy();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

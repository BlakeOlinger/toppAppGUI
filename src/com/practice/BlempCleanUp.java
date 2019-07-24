package com.practice;

import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.RecursiveAction;

class BlempCleanUp extends RecursiveAction {
    @Override
    protected void compute() {
        System.out.println(" - Cleaning up DDTO File");

        try {
            Files.writeString(Config.BLEMP_DDO_PATH, "");

            System.out.println(" - DDTO File - Clean up - Success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

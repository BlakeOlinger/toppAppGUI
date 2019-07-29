package com.practice;

import java.nio.file.Path;
import java.util.ArrayList;

final class RunEnvironment implements Runnable{
    private final ArrayList<Path> paths;
    private String blempName;

    RunEnvironment(ArrayList<Path> paths, String blempName) {
        this.paths = paths;
        this.blempName = blempName;
    }

    @Override
    public void run() {
        AppWindow.getBlemp(paths, blempName);
    }
}

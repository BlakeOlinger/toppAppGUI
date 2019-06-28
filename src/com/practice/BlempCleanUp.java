package com.practice;

import java.io.FileOutputStream;
import java.io.IOException;

class BlempCleanUp implements Runnable{
    private final Thread thread;

    private BlempCleanUp() {
        thread = new Thread(this, "App Clean Up");
    }

    static void clearDDO() {
        var cleanDDO = new BlempCleanUp();
        cleanDDO.thread.start();
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        // TODO change path to install directory
        String devPath = "C:\\Users\\bolinger\\Documents\\SW Projects\\Blob\\DDO.blemp";
        try (var DDO = new FileOutputStream(devPath)) {
            int cleanFile = (int) ' ';
            DDO.write(cleanFile);
        } catch (IOException ignore) {

        }
    }
}

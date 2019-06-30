package com.practice;

import java.io.FileInputStream;
import java.io.IOException;

class GUIDaemon implements Runnable{
    private final Thread thread;

    GUIDaemon() {
        thread = new Thread(this, "GUI Daemon");
    }

    void start() {
        thread.start();
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
        System.out.println(" GUI Daemon - Start");

        do {
            System.out.println(" GUI Daemon Running...");

            try (var configFile = new FileInputStream("programFiles/config/GUI.config")){
                Config.programState = String.valueOf((char) configFile.read());

                System.out.println(" GUI Daemon - Sleep 2,000 ms");
                Thread.sleep(2000);
            } catch (InterruptedException | IOException ignore) {
                System.out.println(" ERROR: GUI.config File Not Found");
            }

            System.out.println(" Program State - " + Config.programState);

        } while (Config.programState.compareTo("0") == 0);

        System.out.println(" GUI Daemon - End");

        System.exit(0);
    }
}

package com.practice.fileio;

public class Blemp implements Runnable{
    private final Thread thread;
    private final String devPath = "C:\\Users\\bolinger\\Documents\\SW Projects\\Blob\\C-HSSX.blemp";
    private String userInput;

    public Blemp(String userInput) {
        thread = new Thread(this, "Blemp IO");
        this.userInput = userInput;
    }

    public void start() {
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
        System.out.println("Button Pressed!");
        System.out.println(userInput);
    }
}

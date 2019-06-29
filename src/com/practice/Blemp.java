package com.practice;

import java.io.FileOutputStream;
import java.io.IOException;


// TODO change dev path to install directory
public class Blemp implements Runnable{
    private final Thread thread;
    private final String devPath = "C:\\Users\\bolinger\\Documents\\SW Projects\\Blob\\DDO.blemp";
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

        DDO.blempDDO[1] = userInput;

        String equation = DDO.blempDDO[0] + "$" + DDO.blempDDO[1] + "$" + DDO.blempDDO[2] + "$";

        System.out.println();
        System.out.println(equation);
        System.out.println();

        char[] chars = equation.toCharArray();

        System.out.println();
        System.out.println(String.valueOf(chars));
        System.out.println();

        try (var blempWrite = new FileOutputStream(devPath)) {
            int outByte;
            int index = 0;
            do {
                outByte = (int) chars[index++];
                System.out.print((char) outByte);
                blempWrite.write(outByte);
            } while (index < chars.length);
        }catch (IOException ignore) {

        }

        System.out.println("Exiting Thread");
    }
}

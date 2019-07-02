package com.practice;

public class Blemp implements Runnable{
    private final Thread thread;
    private String userInput;

    Blemp(String userInput) {
        thread = new Thread(this, "Blemp IO");
        this.userInput = userInput;
    }

    void start() {
        thread.start();
    }

    @Override
    public void run() {
        DDO.blempDDO[1] = userInput;

        String equation = DDO.blempDDO[0] + "$" + DDO.blempDDO[1] + "$" + DDO.blempDDO[2] + "$";

        char[] chars = equation.toCharArray();
/*
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

 */
    }
}

package com.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Config implements Runnable{
    private final Thread thread;
    static String DBKillPath = "programFiles/config/master.config";

    Config() {
        thread = new Thread(this, "Load Config");
    }

    void start() {
        thread.start();
    }

    private void loadBlempConfig() {
        String devPath = "C:\\Users\\bolinger\\Documents\\SW Projects\\Blob\\config.blemp";
        try (var readConfig = new FileInputStream(devPath)){
            int inputByte;
            char[] chars = new char[readConfig.available()];
            var index = 0;
            do {
                inputByte = readConfig.read();
                if(index < chars.length)
                    chars[index++] = (char) inputByte;

                System.out.print((char) inputByte);
            } while(inputByte != -1);

            System.out.print(chars.length);
            System.out.println(chars);
            var equation = String.valueOf(chars);
            System.out.println(equation);
            String[] ddo = equation.split("\\$");
            System.out.println("Start iteration");
            System.out.println("ddo size " + ddo.length);
            Arrays.stream(ddo).forEach(System.out::println);
            System.arraycopy(ddo, 0, DDO.blempDDO, 0, DDO.blempDDO.length);
            Arrays.stream(DDO.blempDDO).forEach(System.out::println);

        } catch (IOException ignore) {

        }
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
        loadBlempConfig();
    }
}

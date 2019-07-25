package com.practice;

class Logger {

    static void cout(Level severity, String entityName, String message) {
        var level = "";

        switch (severity) {
            case WARNING:
                level = "WARNING";
                break;
            case ERROR:
                level = "ERROR";
                break;
            case INFO:
                level = "INFO";
                break;
        }

        if (severity == Level.ERROR) {
            System.out.printf("ERROR - %s - %s - Exiting App%n%n", entityName, message);

            System.out.println("TOPP App GUI - Exit");
        } else
            System.out.printf("%s - %s - %s%n", level, entityName, message);
    }
}

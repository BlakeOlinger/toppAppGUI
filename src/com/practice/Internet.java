package com.practice;

import java.io.IOException;
import java.net.URL;

final class Internet {
    static boolean connected() {
        try {
            var url = new URL("https://www.google.com");

            url.openConnection();

            return true;
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }
    }
}

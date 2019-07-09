package com.practice;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

class Config {
    static String programState = "1";
    static final Path BLEMP_CONFIG_PATH = Paths.get(Main.userRoot +"programFiles/blemp/config.blemp");
    static final Path BLEMP_DDO_PATH = Paths.get(Main.userRoot + "programFiles/blemp/DDO.blemp");
    static ArrayList<String> blempDDO = new ArrayList<>();

}

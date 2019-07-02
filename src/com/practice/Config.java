package com.practice;

import java.nio.file.Path;
import java.nio.file.Paths;

class Config {
    static String programState = "0";
    static final Path BLEMP_CONFIG_PATH = Paths.get("programFiles/blemp/config.blemp");
    static final Path BLEMP_DDO_PATH = Paths.get("programFiles/blemp/DDO.blemp");
    static String[] blempDDO = new String[3];

}

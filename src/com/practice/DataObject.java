package com.practice;

import java.nio.file.Path;
import java.util.ArrayList;

final class DataObject {
    static ArrayList baseBlempPaths = new ArrayList<Path>();
    static Path userSelectedBlemp;
    static boolean userBlempSelectionSemaphore = false;
}

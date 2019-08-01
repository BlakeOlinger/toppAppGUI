package com.practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class SemaphoreList {
    // FIXME - semaphores don't update during runtime
    //  - this means the states are being written to but possibly
    //  - the static nature makes them immutable during runtime
    static boolean is_SW_action_Open(Path path) {
        try {
            var programState = Files.readString(path).substring(1, 2);

            return programState.compareTo("0") == 0;
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

    }

    // FIXME - refactor - no way to test as is
    static boolean is_SW_MS_preview_Open() {
        return IniFileDO.getFieldValue(IniFileDO.USE_SW_INSTANCE_PREVIEW);
    }
}

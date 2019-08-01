package com.practice;

import com.lib.FileNames;
import com.lib.PathsList;
import com.lib.ToppFiles;

final class SWcommand {
    static void submitCommandOnBlob(String command) {
        if (SemaphoreList.is_SW_action_Open(PathsList.toppAppConfig))
            ToppFiles.writeFile(
                    FileNames.SW_MS_CONFIG,
                    PathsList.SWconfig,
                command + PathsList.blobString + "!");
    }

    static void submitCommand(String command) {
        if (SemaphoreList.is_SW_action_Open(PathsList.toppAppConfig))
            ToppFiles.writeFile(
                    FileNames.SW_MS_CONFIG,
                    PathsList.SWconfig,
                    command);
    }

    static void writeDefaultEquationsToDDTO() {
        if (SemaphoreList.is_SW_action_Open(PathsList.toppAppConfig))
            ToppFiles.writeFile(
                FileNames.DDTO,
                    PathsList.DDTO,
                BlempDO.defaultConfigurationBuffer
        );
    }

    static void writeCurrentEquationsToDDTO() {
        if (SemaphoreList.is_SW_action_Open(PathsList.toppAppConfig))
            ToppFiles.writeFile(
                    FileNames.DDTO,
                    PathsList.DDTO,
                    BlempDO.currentEquationBuffer
            );
    }
}

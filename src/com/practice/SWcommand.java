package com.practice;

import com.lib.PathsList;
import com.lib.ToppFiles;

final class SWcommand {
    static void submitCommandOnBlob(String action) {
        if (SemaphoreList.isIsSWactionOpen())
            ToppFiles.writeFile(
                "SWmicroservice.config",
                    PathsList.SWconfig,
                action + PathsList.blobString + "!");
    }

    static void submitCommand(String command) {
        if (SemaphoreList.isIsSWactionOpen())
            ToppFiles.writeFile(
                    "SWmicroservice.config",
                    PathsList.SWconfig,
                    command);
    }

    static void writeDefaultEquationsToDDTO() {
        if (SemaphoreList.isIsSWactionOpen())
            ToppFiles.writeFile(
                "DDTO.blemp",
                    PathsList.DDTO,
                BlempDO.defaultConfigurationBuffer
        );
    }

    static void writeCurrentEquationsToDDTO() {
        if (SemaphoreList.isIsSWactionOpen())
            ToppFiles.writeFile(
                    "DDTO.blemp",
                    PathsList.DDTO,
                    BlempDO.currentEquationBuffer
            );
    }
}

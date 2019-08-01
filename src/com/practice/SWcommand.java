package com.practice;

import com.lib.ToppFiles;

final class SWcommand {
    static void submitCommandOnBlob(String action) {
        if (CommandSemaphore.isOpen())
            ToppFiles.writeFile(
                "SWmicroservice.config",
                    PathsList.SWconfig,
                action + PathsList.blobString + "!");
    }

    static void submitCommand(String command) {
        if (CommandSemaphore.isOpen())
            ToppFiles.writeFile(
                    "SWmicroservice.config",
                    PathsList.SWconfig,
                    command);
    }

    static void writeDefaultEquationsToDDTO() {
        if (CommandSemaphore.isOpen())
            ToppFiles.writeFile(
                "DDTO.blemp",
                    PathsList.DDTO,
                BlempDO.defaultConfigurationBuffer
        );
    }

    static void writeCurrentEquationsToDDTO() {
        if (CommandSemaphore.isOpen())
            ToppFiles.writeFile(
                    "DDTO.blemp",
                    PathsList.DDTO,
                    BlempDO.currentEquationBuffer
            );
    }
}

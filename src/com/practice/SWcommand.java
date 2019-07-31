package com.practice;

final class SWcommand {
    static void submitCommandOnBlob(String action) {
        if (CommandSemaphore.isOpen())
            ToppFiles.writeFile(
                "SWmicroservice.config",
                SWdaemonCommandDO.SWdaemonConfigPath,
                action + BlobDO.blobPathString + "!");
    }

    static void submitCommand(String command) {
        if (CommandSemaphore.isOpen())
            ToppFiles.writeFile(
                    "SWmicroservice.config",
                    SWdaemonCommandDO.SWdaemonConfigPath,
                    command);
    }

    static void writeDefaultEquationsToDDTO() {
        if (CommandSemaphore.isOpen())
            ToppFiles.writeFile(
                "DDTO.blemp",
                DDTOdataObject.DDTOpath,
                BlempDO.defaultConfigurationBuffer
        );
    }

    static void writeCurrentEquationsToDDTO() {
        if (CommandSemaphore.isOpen())
            ToppFiles.writeFile(
                    "DDTO.blemp",
                    DDTOdataObject.DDTOpath,
                    BlempDO.currentEquationBuffer
            );
    }
}

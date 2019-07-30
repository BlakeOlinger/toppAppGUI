package com.practice;

final class SWcommand {
    static void submitCommand(String action) {
        if (CommandSemaphore.isOpen())
            ToppFiles.writeFile(
                "SWmicroservice.config",
                SWdaemonCommandDO.SWdaemonConfigPath,
                action + BlobDO.blobPathString + "!");
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

package com.practice;

final class SWcommand {
    static void submitCommand() {
        ToppFiles.writeFile(
                "SWmicroservice.config",
                SWdaemonCommandDO.SWdaemonConfigPath,
                "00!" + BlobDO.blobPathString + "!");
    }

    static void writeEquationsToDDTO() {
        ToppFiles.writeFile(
                "DDTO.blemp",
                DDTOdataObject.DDTOpath,
                BlempDO.defaultConfigurationBuffer
        );
    }
}

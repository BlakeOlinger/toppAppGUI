package com.practice;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ForkJoinPool;

/*
Steps to writing a command to SW daemon -
 - CommandSemaphore.close()
 - BlempUtil.populateDefaultConfiguration (for preview) or .populateCurrentConfiguration
 - SWcommand.writeEquationsToDDTO()
 - SWcommand.submitCommand()

 -- Format --
 the .blemp equation must take the form:
    "O.D.@Sketch1"=$40#$in$!
    - whole equations are deliminated via a !
    - subsections are deliminated via a $
    -- there are three subsections in non-branching equations
    - the # symbol appends a significant value that the user can change
 equations sent to the DDTO must take the form:
    "O.D.@Sketch1"=40in!
    - the only deliminator is a ! denoting whole equations
 the blob info for the daemon to open that's sent to the SWmicroservice.config file
    - must be in the form of "C-HSSX.blob.SLDPRT"
    - no other path/directory info
 */

final class Main {
    public static void main(String[] args) {
        System.out.println("TOPP App GUI - Start");

        var installRoot = InstallRoot.getInstallRoot("SolidWorks Daemon");

        var installDirectory = Paths.get(installRoot);

        if (!ToppFiles.validateDirectory(
                "Install",
                installDirectory
                )) {
            return;
        }

        if (!BlobDirectory.validateLocalBlobDatabaseInstance(installDirectory))
            return;

        var GUIiniFileName = "GUI.ini";

        IniFileDO.path = Paths.get(installRoot + GUIiniFileName);

        if (ToppFiles.validateFile(GUIiniFileName, IniFileDO.path)) return;

        IniFileDO.getCurrentUserIniSettings();

        var GUIconfigFileName = "GUI.config";

        GUIconfigDO.GUIconfig = Paths.get(installRoot + GUIconfigFileName);

        if (ToppFiles.validateFile(GUIconfigFileName, GUIconfigDO.GUIconfig)) {
            return;
        }

        if (ToppFiles.writeFile(GUIconfigFileName, GUIconfigDO.GUIconfig, "00")) {
            return;
        }

        var SWexePath = Paths.get(installRoot + "NuSWDaemon.exe");

        var swConfigFileName = "SWmicroservice.config";

        SWdaemonCommandDO.SWdaemonConfigPath = Paths.get(installRoot + swConfigFileName);

        if (!Files.exists(SWexePath)) {


            if (ToppFiles.validateFile(swConfigFileName, SWdaemonCommandDO.SWdaemonConfigPath)) {
                return;
            }

            if (ToppFiles.writeFile(swConfigFileName,
                    SWdaemonCommandDO.SWdaemonConfigPath, "011!")) {
                return;
            }
        }

        if (Files.exists(SWexePath) && IniFileDO.getFieldValue(
                IniFileDO.ON_START_START_SW
        ))
            ForkJoinPool.commonPool().execute(new SWDaemonProcess(SWexePath));

        var DDTOfileName = "DDTO.blemp";

        DDTOdataObject.DDTOpath = Paths.get(installRoot + DDTOfileName);

        if (ToppFiles.validateFile(DDTOfileName, DDTOdataObject.DDTOpath)) return;

        if (ToppFiles.writeFile(DDTOfileName, DDTOdataObject.DDTOpath, "")) return;

        var baseBlobDirectory = Paths.get(installDirectory + "\\blob\\");

        BlempSelectionDO.baseBlempPaths = BlobDirectory.getAvailableBlempFiles(baseBlobDirectory);

        if (BlempSelectionDO.baseBlempPaths == null) {
            System.out.println(" - ERROR - No .blemp files found");

            return;
        }

        TopLevelMenu.instantiateWindow();

        TopLevelMenu.waitFor();

        if (IniFileDO.getFieldValue(
                IniFileDO.ON_EXIT_CLOSE_SW
        ))
            SWcommand.submitCommand(SWaction.EXIT_SW_DAEMON);

        if (IniFileDO.getFieldValue(
                IniFileDO.ON_EXIT_CLOSE_SW_PART
        ) && !IniFileDO.getFieldValue(
                IniFileDO.ON_EXIT_CLOSE_SW
        ))
            SWcommand.submitCommand(SWaction.CLOSE_SW_PART);

        System.exit(0);
    }
}

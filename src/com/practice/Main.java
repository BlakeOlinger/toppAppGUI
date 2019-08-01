package com.practice;

import com.lib.BlobDirectory;
import com.lib.InstallRoot;
import com.lib.ToppFiles;

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

        PathsList.userIni = Paths.get(installRoot + GUIiniFileName);

        if (ToppFiles.validateFile(GUIiniFileName, PathsList.userIni)) return;

        IniFileDO.getCurrentUserIniSettings();

        var GUIconfigFileName = "GUI.config";

        PathsList.toppAppConfig = Paths.get(installRoot + GUIconfigFileName);

        if (ToppFiles.validateFile(GUIconfigFileName, PathsList.toppAppConfig)) {
            return;
        }

        if (ToppFiles.writeFile(GUIconfigFileName, PathsList.toppAppConfig, "00")) {
            return;
        }

        PathsList.SWexe = Paths.get(installRoot + "NuSWDaemon.exe");

        var swConfigFileName = "SWmicroservice.config";

        PathsList.SWconfig = Paths.get(installRoot + swConfigFileName);

        if (!Files.exists(PathsList.SWexe)) {
            if (ToppFiles.validateFile(swConfigFileName, PathsList.SWconfig)) {
                return;
            }

            if (ToppFiles.writeFile(swConfigFileName,
                    PathsList.SWconfig, "011!")) {
                return;
            }
        }

        if (Files.exists(PathsList.SWexe) && IniFileDO.getFieldValue(
                IniFileDO.ON_START_START_SW
        ))
            ForkJoinPool.commonPool().execute(new SWDaemonProcess(PathsList.SWexe));

        var DDTOfileName = "DDTO.blemp";

        PathsList.DDTO = Paths.get(installRoot + DDTOfileName);

        if (ToppFiles.validateFile(DDTOfileName, PathsList.DDTO)) return;

        if (ToppFiles.writeFile(DDTOfileName, PathsList.DDTO, "")) return;

        var baseBlobDirectory = Paths.get(installDirectory + "\\blob\\");

        PathsList.baseBlempPaths = BlempUtil.getAvailableBlempFiles(baseBlobDirectory);

        if (PathsList.baseBlempPaths == null) {
            System.out.println(" - ERROR - No .blemp files found");

            return;
        }

        if  (!Files.exists(Paths.get(installRoot + "toppAPPpreview.jar"))) {
            var configFileName = "toppAppPreview.config";

            var configFilePath = Paths.get(installRoot + configFileName);

            if (ToppFiles.validateFile(configFileName, configFilePath)) {
                return;
            }

            if (ToppFiles.writeFile(configFileName, configFilePath, "0")) {
                return;
            }
        }


        TopLevelMenu.createWindow();

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

package com.practice;

import com.lib.*;

import java.nio.file.Files;
import java.nio.file.Paths;

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

        var DBdaemonFileName = "toppAppDBdaemon.jar";

        PathsList.DBdaemon = Paths.get(installRoot + DBdaemonFileName);

        if (!Files.exists(PathsList.DBdaemon))
            if (!BlobDirectory.validateLocalBlobDatabaseInstance(installDirectory))
                return;

        var GUIiniFileName = "GUI.ini";

        PathsList.userIni = Paths.get(installRoot + GUIiniFileName);

        if (ToppFiles.validateFile(GUIiniFileName, PathsList.userIni)) return;

        IniFileDO.setUserIniConfig();

        var GUIconfigFileName = "GUI.config";

        PathsList.toppAppConfig = Paths.get(installRoot + GUIconfigFileName);

        if (ToppFiles.validateFile(GUIconfigFileName, PathsList.toppAppConfig))
            return;

        if (ToppFiles.writeFile(GUIconfigFileName, PathsList.toppAppConfig, Commands.GUI_INIT))
            return;

        PathsList.SWexe = Paths.get(installRoot + "NuSWDaemon.exe");

        var swConfigFileName = "SWmicroservice.config";

        PathsList.SWconfig = Paths.get(installRoot + swConfigFileName);

        if (!Files.exists(PathsList.SWexe)) {
            if (ToppFiles.validateFile(swConfigFileName, PathsList.SWconfig))
                return;

            if (ToppFiles.writeFile(swConfigFileName,
                    PathsList.SWconfig, Commands.SW_DAEMON_INIT))
                return;
        }

        if (Files.exists(PathsList.SWexe) && IniFileDO.getFieldValue(IniFileDO.ON_START_START_SW))
            new ExecuteProcess(PathsList.SWexe.toString()).execute();

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

        var previewConfigFileName = "toppAppPreview.config";

        PathsList.previewConfig = Paths.get(installRoot + previewConfigFileName);

        if  (!Files.exists(PathsList.previewConfig)) {

            if (ToppFiles.validateFile(previewConfigFileName, PathsList.previewConfig))
                return;

            if (ToppFiles.writeFile(previewConfigFileName, PathsList.previewConfig, Commands.PROGRAM_INIT))
                return;
        }


        TopLevelMenu.createWindow();

        TopLevelMenu.waitFor();

        if (IniFileDO.getFieldValue(
                IniFileDO.ON_EXIT_CLOSE_SW
        ))
            SWcommand.submitCommand(Commands.SWDaemon.EXIT);

        if (IniFileDO.getFieldValue(
                IniFileDO.ON_EXIT_CLOSE_SW_PART
        ) && !IniFileDO.getFieldValue(
                IniFileDO.ON_EXIT_CLOSE_SW
        ))
            SWcommand.submitCommand(Commands.SWDaemon.CLOSE_SW_PART);

        System.exit(0);
    }
}

package com.practice;

import com.lib.*;

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
    public static void main(String[] args) throws InvalidInstallException {
        System.out.println("TOPP App GUI - Start");

        ToppFiles.validateDirectory(
                FileNames.INSTALL_DIRECTORY,
                PathsList.INSTALL_DIRECTORY);
/*
        BlobDirectory.validateLocalBlobDatabaseInstance();

        PathsList.userIni = Paths.get(installRoot + FileNames.GUI_INI);

        if (ToppFiles.validateFile(FileNames.GUI_INI, PathsList.userIni)) return;

        IniFileDO.setUserIniConfig();

        PathsList.toppAppConfig = Paths.get(installRoot + FileNames.GUI_CONFIG);

        if (ToppFiles.validateFile(FileNames.GUI_CONFIG, PathsList.toppAppConfig))
            return;

        if (ToppFiles.writeFile(FileNames.GUI_CONFIG, PathsList.toppAppConfig, Commands.GUI_INIT))
            return;

        PathsList.SWexe = Paths.get(installRoot + FileNames.SW_MS_EXE);

        PathsList.SWconfig = Paths.get(installRoot + FileNames.SW_MS_CONFIG);

        if (!Files.exists(PathsList.SWexe)) {
            if (ToppFiles.validateFile(FileNames.SW_MS_CONFIG, PathsList.SWconfig))
                return;

            if (ToppFiles.writeFile(FileNames.SW_MS_CONFIG,
                    PathsList.SWconfig, Commands.SW_DAEMON_INIT))
                return;
        }

        if (Files.exists(PathsList.SWexe) && IniFileDO.getFieldValue(IniFileDO.ON_START_START_SW))
            new ExecuteProcess(Commands.SWDaemon.EXE_START).execute();

        PathsList.DDTO = Paths.get(installRoot + FileNames.DDTO);

        if (ToppFiles.validateFile(FileNames.DDTO, PathsList.DDTO)) return;

        if (ToppFiles.writeFile(FileNames.DDTO, PathsList.DDTO, "")) return;

        PathsList.baseBlobDirectory = Paths.get(installRoot + "\\blob\\");

        PathsList.baseBlempPaths = BlempUtil.getAvailableBlempFiles(PathsList.baseBlobDirectory);

        if (PathsList.baseBlempPaths == null) {
            System.out.println(" - ERROR - No .blemp files found");

            return;
        }

        PathsList.previewConfig = Paths.get(installRoot + FileNames.PREVIEW_MS_CONFIG);

        if (!Files.exists(PathsList.previewConfig)) {

            if (ToppFiles.validateFile(FileNames.PREVIEW_MS_CONFIG, PathsList.previewConfig))
                return;

            if (ToppFiles.writeFile(FileNames.PREVIEW_MS_CONFIG, PathsList.previewConfig, Commands.PROGRAM_INIT))
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
 */
    }
}

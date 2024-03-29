package com.practice;

import com.lib.Initialize;
import com.lib.Microservice;

/*
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

        if (!Initialize.microservice(Microservice.TOPP_APP)) System.exit(-1);

        // TODO - refactor the IniFileDO class and functions down to base lib
//        if (Files.exists(PathsList.SWexe) && IniFileDO.getFieldValue(IniFileDO.ON_START_START_SW))
//            new ExecuteProcess(Commands.SWDaemon.EXE_START).execute();

        // TODO - don't activate until GUI with ini settings are available
        // TODO - need to define a way to look at the SW config file or
        //  -  some other means to determine if the exe is still running
        //  -  don't call another instance if it is
//        if (Files.exists(PathsList.SW_MS_EXE))
//            Commands.SWDaemon.start();
//        try {
//            Thread.sleep(15_000);
//
//            Commands.SWDaemon.stop();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // TODO - will need to get list of available base.blemp and .blemp files in the local
        //  -  blob database
        //  -  Reading the contents of a directory is something any MS may need
        //  -   put into base lib
//        PathsList.baseBlobDirectory = Paths.get(installRoot + "\\blob\\");
//
//        PathsList.baseBlempPaths = BlempUtil.getAvailableBlempFiles(PathsList.baseBlobDirectory);
//
//        if (PathsList.baseBlempPaths == null) {
//            System.out.println(" - ERROR - No .blemp files found");
//
//            return;
//        }



        // TODO - this will go in the custom desktop lib
//        TopLevelMenu.createWindow();
//
//        TopLevelMenu.waitFor();

        // TODO - these issue commands based on ini settings
        //  - something every app will need to consider
        //  - put in low level library but have it abstract
        //  - the events and handlers similar to Swing event handlers
//        if (IniFileDO.getFieldValue(
//                IniFileDO.ON_EXIT_CLOSE_SW
//        ))
//            SWcommand.submitCommand(Commands.SWDaemon.EXIT);
//
//        if (IniFileDO.getFieldValue(
//                IniFileDO.ON_EXIT_CLOSE_SW_PART
//        ) && !IniFileDO.getFieldValue(
//                IniFileDO.ON_EXIT_CLOSE_SW
//        ))
//            SWcommand.submitCommand(Commands.SWDaemon.CLOSE_SW_PART);
//
//        System.exit(0);

    }
}

package com.practice;

final class BlempJListPreviewAction {
    static void submitPreview() {
        if (CommandSemaphore.open) {
            if (PathsList.userSelectedBlemp.toString().contains(".base.")) {
                System.out.println(" - user selected a .base.blemp file");

                PathsList.blobString = PathsList.userSelectedBlemp.toString()
                        .replace(".base.blemp", ".blob.SLDPRT");

                var tmpArray = new String[] {};

                tmpArray = PathsList.blobString.split("\\\\");

                PathsList.blobString = tmpArray[tmpArray.length - 1];

                System.out.println(" - blob file to load in preview - "
                        + PathsList.blobString);


                BlempUtil.populateDefaultConfiguration();

                SWcommand.writeDefaultEquationsToDDTO();

                SWcommand.submitCommandOnBlob(SWaction.ACTION_SENT);
            }
        }
    }
}

package com.practice;

final class BlempJListPreviewAction {
    static void submitPreview() {
        if (CommandSemaphore.open) {
            if (BlempSelectionDO.userSelectedBlemp.toString().contains(".base.")) {
                System.out.println(" - user selected a .base.blemp file");

                BlobDO.blobPathString = BlempSelectionDO.userSelectedBlemp.toString()
                        .replace(".base.blemp", ".blob.SLDPRT");

                var tmpArray = new String[] {};

                tmpArray = BlobDO.blobPathString.split("\\\\");

                BlobDO.blobPathString = tmpArray[tmpArray.length - 1];

                System.out.println(" - blob file to load in preview - "
                        + BlobDO.blobPathString);


                BlempUtil.populateDefaultConfiguration();

                SWcommand.writeEquationsToDDTO();

                SWcommand.submitCommand(SWaction.ACTION_SENT);
            }
        }
    }
}

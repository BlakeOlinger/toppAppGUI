package com.practice;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

final class BlempSelectionWindow extends JFrame{
    private BlempSelectionWindow(String label) {
        super(label);
    }

    static void getBlemp() {
        BlempSelectionDO.userBlempSelectionSemaphore = true;

        var paths = BlempSelectionDO.baseBlempPaths;

        var window = new BlempSelectionWindow("Select a template file");
        window.setSize(1280, 720);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLayout(new FlowLayout());
        var pathArray = new String[paths.size()];

        for (var i = 0; i < paths.size(); ++i)
            pathArray[i] = paths.get(i).toString();

        var list = new JList<>(pathArray);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        var label = new JLabel();

        var action = new BlempJListSelectionAction(list, label, window);

        var scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(500, 100));

        list.addListSelectionListener(action);

        window.add(scrollPane);
        window.add(label);

        window.setVisible(true);
    }

    static Path waitForUserSelectionAndConsume() {
        while (BlempSelectionDO.userBlempSelectionSemaphore) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        var path = BlempSelectionDO.userSelectedBlemp;

        BlempSelectionDO.userSelectedBlemp = null;

        return path;
    }

    @Override
    public void dispose() {
        BlempSelectionDO.userBlempSelectionSemaphore = false;

        super.dispose();
    }
}

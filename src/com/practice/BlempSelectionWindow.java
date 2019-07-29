package com.practice;

import javax.swing.*;
import java.awt.*;

final class BlempSelectionWindow extends JFrame{
    private BlempSelectionWindow(String label) {
        super(label);
    }

    static void getBlemp() {
        DataObject.userBlempSelectionSemaphore = true;

        var paths = DataObject.baseBlempPaths;

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

    @Override
    public void dispose() {
        DataObject.userBlempSelectionSemaphore = false;

        super.dispose();
    }

    static void waitForUserSelection() {
        while (DataObject.userBlempSelectionSemaphore) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
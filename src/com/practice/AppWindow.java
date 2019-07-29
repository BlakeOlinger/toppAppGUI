package com.practice;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;

final class AppWindow {
    static void getBlemp(ArrayList<Path> paths, String blempName) {
        var window = new JFrame("Select a template file");
        window.setSize(600, 500);
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        window.setLayout(new FlowLayout());
        var pathArray = new String[paths.size()];

        for (var i = 0; i < paths.size(); ++i)
            pathArray[i] = paths.get(i).toString().split("\\\\")[pathArray[i].length() - 1];

        var list = new JList<>(pathArray);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        var label = new JLabel();

        var action = new BlempSelectionAction(list, blempName, window, label);

        var scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(400, 100));

        list.addListSelectionListener(action);

        window.add(scrollPane);
        window.add(label);

        window.setVisible(true);
    }
}

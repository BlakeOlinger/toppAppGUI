package com.practice;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.nio.file.Paths;

final class BlempJListSelectionAction implements ListSelectionListener {
    private final JList<String> nameList;
    private JLabel label;
    private JFrame window;

    BlempJListSelectionAction(JList<String> nameList, JLabel label, JFrame window) {
        this.nameList = nameList;
        this.label = label;
        this.window = window;
    }
    // FIXME - preview listed select and adjust blemp twice on same window
    @Override
    public void valueChanged(ListSelectionEvent e) {
        PathsList.userSelectedBlemp = Paths.get(nameList.getSelectedValue());

        BlempJListPreviewAction.submitPreview();

        label.setText("You selected: "
                + nameList.getSelectedValue()
                + ". Use this template?");

        var selectButton = new JButton("Select");

        var action = new BlempSelectionButtonAction(window);

        selectButton.addActionListener(action);

        window.add(selectButton);

        window.add(
                MenuButton.getInstance(
                        "Adjust Blemp",
                        event -> System.out.println("Adjust Blemp detected")
                )
        );
    }
}

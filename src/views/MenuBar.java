package views;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        JMenu addMenu = new JMenu("Add");
        addMenu.add(new JMenu("Add customer"));
        addMenu.add(new JMenu("Add good"));
        addMenu.add(new JMenu("Add good amount"));
        add(addMenu);
        JMenu changeMenu = new JMenu("Edit");
        changeMenu.add(new JMenu("Edit good amount"));
        changeMenu.add(new JMenu("Edit good price"));
        add(changeMenu);
        add(new JMenu("Sell good"));
        JMenu storageMenu = new JMenu("Storage");
        storageMenu.add(new JMenu("Load from storage"));
        storageMenu.add(new JMenu("Save to storage"));
        add(storageMenu);
    }
}
package views;

import database.Database;
import database.TextFileCommaSeparatedDatabase;
import main.Main;
import models.Shop;

import javax.swing.*;
import java.nio.file.Path;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        JMenu addMenu = new JMenu("Add item");

        JMenuItem addCustomerMenu = new JMenuItem("Add customer");
        addMenu.add(addCustomerMenu);

        JMenuItem addGoodMenu = new JMenuItem("Add good");
        addMenu.add(addGoodMenu);

        JMenuItem addTradeMenu = new JMenuItem("Add trade");
        addMenu.add(addTradeMenu);

        add(addMenu);

        JMenu editMenu = new JMenu("Edit item");

        JMenuItem addGoodAmountMenu = new JMenuItem("Add good amount");
        editMenu.add(addGoodAmountMenu);

        JMenuItem editGoodAmountMenu = new JMenuItem("Edit good amount");
        editMenu.add(editGoodAmountMenu);

        JMenuItem editGoodPriceMenu = new JMenuItem("Edit good price");
        editMenu.add(editGoodPriceMenu);

        add(editMenu);

        JMenu storageMenu = new JMenu("Storage");

        JMenuItem storageLoadMenu = new JMenuItem("Load from .txt storage");
        storageLoadMenu.addActionListener(e -> {
            final JFileChooser jFileChooser = new JFileChooser();
            int result = jFileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                Path path = jFileChooser.getSelectedFile().toPath();
                Database database = new TextFileCommaSeparatedDatabase(path);
                Main.shop = new Shop(database.readRowList());

                Main.mainWindow.loadData(
                        Main.shop.getCustomersStringArray(),
                        Main.shop.getStocksStringArray(),
                        Main.shop.getTradesStringArray());
            }
        });
        storageMenu.add(storageLoadMenu);

        JMenuItem storageSaveMenu = new JMenuItem("Save to .txt storage");
        storageSaveMenu.addActionListener(e -> {
            final JFileChooser jFileChooser = new JFileChooser();
            int result = jFileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                Path path = jFileChooser.getSelectedFile().toPath();
                Database database = new TextFileCommaSeparatedDatabase(path);

                database.writeObjectList(Main.shop.toObjectArray());
            }
        });
        storageMenu.add(storageSaveMenu);

        add(storageMenu);
    }
}
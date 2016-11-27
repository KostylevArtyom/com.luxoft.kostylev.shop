package views;

import database.Database;
import database.TextFileCommaSeparatedDatabase;
import main.Main;
import models.Shop;
import views.utils.LabelValues;

import javax.swing.*;
import java.nio.file.Path;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        JMenu addMenu = new JMenu(LabelValues.ADD_MENU_NAME);

        JMenuItem addCustomerMenu = new JMenuItem(LabelValues.ADD_CUSTOMER_MENU_NAME);
        addMenu.add(addCustomerMenu);

        JMenuItem addGoodMenu = new JMenuItem(LabelValues.ADD_GOOD_MENU_NAME);
        addMenu.add(addGoodMenu);

        JMenuItem addTradeMenu = new JMenuItem(LabelValues.ADD_TRADE_MENU_NAME);
        addMenu.add(addTradeMenu);

        add(addMenu);

        JMenu editMenu = new JMenu(LabelValues.EDIT_MENU_NAME);

        JMenuItem addGoodAmountMenu = new JMenuItem(LabelValues.ADD_GOOD_AMOUNT_MENU_NAME);
        editMenu.add(addGoodAmountMenu);

        JMenuItem editGoodAmountMenu = new JMenuItem(LabelValues.EDIT_GOOD_AMOUNT_MENU_NAME);
        editMenu.add(editGoodAmountMenu);

        JMenuItem editGoodPriceMenu = new JMenuItem(LabelValues.EDIT_GOOD_PRICE_MENU_NAME);
        editMenu.add(editGoodPriceMenu);

        add(editMenu);

        JMenu storageMenu = new JMenu(LabelValues.STORAGE_MENU_NAME);

        JMenuItem storageLoadMenu = new JMenuItem(LabelValues.STORAGE_LOAD_MENU_NAME);
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

        JMenuItem storageSaveMenu = new JMenuItem(LabelValues.STORAGE_SAVE_MENU_NAME);
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
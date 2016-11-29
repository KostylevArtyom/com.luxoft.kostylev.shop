package views;

import database.Database;
import database.TextFileDatabase;
import exceptions.DatabaseClassIndexAlreadyExistException;
import main.Main;
import models.Customer;
import models.Shop;
import models.Stock;
import views.utils.CheckCorrectValueUtils;
import views.utils.LabelValues;

import javax.swing.*;
import java.nio.file.Path;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        JMenu addMenu = new JMenu(LabelValues.ADD_MENU_NAME);

        JMenuItem addCustomerMenu = new JMenuItem(LabelValues.ADD_CUSTOMER_MENU_NAME);
        addCustomerMenu.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(new JFrame(), "Type customer name", "");
            if ((name = CheckCorrectValueUtils.parseNotEmptyString(name)) != null) {
                Customer newCustomer = new Customer(name);
                try {
                    Main.shop.addCustomer(newCustomer);
                } catch (DatabaseClassIndexAlreadyExistException e1) {
                    e1.printStackTrace();
                }
                Main.mainWindow.addCustomer(newCustomer.toStringArray());
            }
        });
        addMenu.add(addCustomerMenu);

        JMenuItem addStockMenu = new JMenuItem(LabelValues.ADD_STOCK_MENU_NAME);
        addStockMenu.addActionListener(e -> new AddStockPanel());
        addMenu.add(addStockMenu);

        JMenuItem addTradeMenu = new JMenuItem(LabelValues.ADD_TRADE_MENU_NAME);
        addTradeMenu.addActionListener(e -> new AddTradePanel());
        addMenu.add(addTradeMenu);

        add(addMenu);

        JMenu editMenu = new JMenu(LabelValues.EDIT_MENU_NAME);

        JMenuItem addGoodAmountMenu = new JMenuItem(LabelValues.ADD_GOOD_AMOUNT_MENU_NAME);
        addGoodAmountMenu.addActionListener(
                e -> new ChangeStockFieldPanel(LabelValues.ADD_GOOD_AMOUNT_MENU_NAME, "Type additional amount") {
            @Override
            public void makeAction() {
                Integer amount = CheckCorrectValueUtils.parsePositiveInteger(getValueTextFieldText());
                if (CheckCorrectValueUtils.checkJComboBoxSelectCorrect(getStocksComboBox()) && amount != null)
                    ((Stock) getStocksComboBox().getSelectedItem()).addAmount(amount);
            }
        });
        editMenu.add(addGoodAmountMenu);

        JMenuItem editGoodAmountMenu = new JMenuItem(LabelValues.EDIT_GOOD_AMOUNT_MENU_NAME);
        editGoodAmountMenu.addActionListener(
                e -> new ChangeStockFieldPanel(LabelValues.EDIT_GOOD_AMOUNT_MENU_NAME, "Type new amount") {
            @Override
            public void makeAction() {
                Integer amount = CheckCorrectValueUtils.parsePositiveInteger(getValueTextFieldText());
                if (CheckCorrectValueUtils.checkJComboBoxSelectCorrect(getStocksComboBox()) && amount != null)
                    ((Stock) getStocksComboBox().getSelectedItem()).setAmount(amount);
            }
        });
        editMenu.add(editGoodAmountMenu);

        JMenuItem editGoodPriceMenu = new JMenuItem(LabelValues.EDIT_GOOD_PRICE_MENU_NAME);
        editGoodPriceMenu.addActionListener(
                e -> new ChangeStockFieldPanel(LabelValues.EDIT_GOOD_PRICE_MENU_NAME, "Type new price") {
            @Override
            public void makeAction() {
                Double price = CheckCorrectValueUtils.parsePositiveDouble(getValueTextFieldText());
                if (CheckCorrectValueUtils.checkJComboBoxSelectCorrect(getStocksComboBox()) && price != null)
                    ((Stock) getStocksComboBox().getSelectedItem()).setPrice(price);
            }
        });
        editMenu.add(editGoodPriceMenu);

        add(editMenu);

        JMenu storageMenu = new JMenu(LabelValues.STORAGE_MENU_NAME);

        JMenuItem storageLoadMenu = new JMenuItem(LabelValues.STORAGE_LOAD_MENU_NAME);
        storageLoadMenu.addActionListener(e -> {
            final JFileChooser jFileChooser = new JFileChooser();
            int result = jFileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                Path path = jFileChooser.getSelectedFile().toPath();
                Database database = new TextFileDatabase(path);
                try {
                    Main.shop = new Shop(database.readAllObjects());
                } catch (DatabaseClassIndexAlreadyExistException e1) {
                    e1.printStackTrace();
                }

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
                Database database = new TextFileDatabase(path);
                try {
                    database.writeObjects(Main.shop.getStorablesList());
                } catch (DatabaseClassIndexAlreadyExistException e1) {
                    e1.printStackTrace();
                }
            }
        });
        storageMenu.add(storageSaveMenu);

        add(storageMenu);
    }
}
package views;

import main.Main;
import models.Stock;

import javax.swing.*;
import java.util.Set;

public abstract class ChangeStockFieldPanel extends JPanel {
    private JComboBox stocksComboBox;
    private JTextField valueTextField;

    public ChangeStockFieldPanel(String showConfirmDialogValue, String JTextFieldValue) {
        add(new JLabel("Choose a stock:"));
        Set<Stock> stocks = Main.shop.getStocks();
        stocksComboBox = new JComboBox<>(stocks.toArray(new Stock[stocks.size()]));
        stocksComboBox.setEditable(true);
        add(stocksComboBox);

        add(Box.createHorizontalStrut(15));

        valueTextField = new JTextField(5);
        add(new JLabel(JTextFieldValue));
        add(valueTextField);

        int result = JOptionPane.showConfirmDialog(null, this, showConfirmDialogValue, JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            makeAction();
            Main.mainWindow.loadStocksData(Main.shop.getStocksStringArray());
        }
    }

    public JComboBox getStocksComboBox() {
        return stocksComboBox;
    }

    public JTextField getValueTextField() {
        return valueTextField;
    }

    public abstract void makeAction();
}
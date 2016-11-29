package views;

import exceptions.DatabaseClassIndexNotExistException;
import main.Main;
import models.Customer;
import models.Good;
import models.Stock;
import models.Trade;
import views.utils.CheckCorrectValueUtils;
import views.utils.LabelValues;

import javax.swing.*;
import java.util.Set;

public class AddTradePanel extends JPanel {
    public AddTradePanel() {
        add(new JLabel("Choose a customer:"));
        Set<Customer> customers = Main.shop.getCustomers();
        JComboBox customersComboBox = new JComboBox<>(customers.toArray(new Customer[customers.size()]));
        customersComboBox.setEditable(true);
        add(customersComboBox);

        add(new JLabel("Choose a good:"));
        Set<Good> goods = Main.shop.getGoods();
        JComboBox goodsComboBox = new JComboBox<>(goods.toArray(new Good[goods.size()]));
        goodsComboBox.setEditable(true);
        add(goodsComboBox);

        add(Box.createHorizontalStrut(15));

        add(new JLabel("Type amount"));
        JTextField amountTextField = new JTextField(5);
        add(amountTextField);

        int result = JOptionPane.showConfirmDialog(null, this, LabelValues.ADD_TRADE_MENU_NAME, JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Integer amount = CheckCorrectValueUtils.parsePositiveInteger(amountTextField.getText());
            if ((CheckCorrectValueUtils.checkJComboBoxSelectCorrect(customersComboBox)) &&
                    (CheckCorrectValueUtils.checkJComboBoxSelectCorrect(goodsComboBox)) &&
                    amount != null) {
                Stock stock = null;
                try {
                    stock = Main.shop.getStockByGoodId(((Good) goodsComboBox.getSelectedItem()).getId());
                } catch (DatabaseClassIndexNotExistException e) {
                    e.printStackTrace();
                }
                Trade trade = new Trade(
                        (Customer) customersComboBox.getSelectedItem(),
                        stock,
                        Integer.valueOf(amountTextField.getText()));
                try {
                    Main.shop.addTrade(trade);
                } catch (Exception e) {
                }
                Main.mainWindow.loadStocksData(Main.shop.getStocksStringArray());
                Main.mainWindow.loadTradesData(Main.shop.getTradesStringArray());
            }
        }
    }
}
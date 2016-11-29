package views;

import main.Main;
import models.Customer;
import models.Good;
import models.Stock;
import models.Trade;
import views.utils.LabelValues;

import javax.swing.*;
import java.util.Set;

public class AddTradePanel extends JPanel {
    private JComboBox customersComboBox;
    private JComboBox goodsComboBox;
    private JTextField amountTextField;

    public AddTradePanel() {
        add(new JLabel("Choose a customer:"));
        Set<Customer> customers = Main.shop.getCustomers();
        customersComboBox = new JComboBox<>(customers.toArray(new Customer[customers.size()]));
        customersComboBox.setEditable(true);
        add(customersComboBox);

        add(new JLabel("Choose a good:"));
        Set<Good> goods = Main.shop.getGoods();
        goodsComboBox = new JComboBox<>(goods.toArray(new Good[goods.size()]));
        goodsComboBox.setEditable(true);
        add(goodsComboBox);

        add(Box.createHorizontalStrut(15));

        amountTextField = new JTextField(5);
        add(new JLabel("Type amount"));
        add(amountTextField);

        int result = JOptionPane.showConfirmDialog(null, this, LabelValues.ADD_TRADE_MENU_NAME, JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Stock stock = Main.shop.getStockByGoodId(((Good)goodsComboBox.getSelectedItem()).getId());
            Main.shop.addTrade(new Trade(
                    (Customer)customersComboBox.getSelectedItem(),
                    stock,
                    Integer.valueOf(amountTextField.getText())));
            Main.mainWindow.loadTradesData(Main.shop.getTradesStringArray());
        }
    }
}
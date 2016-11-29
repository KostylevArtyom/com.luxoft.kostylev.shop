package views;

import main.Main;
import models.Good;
import models.Stock;
import views.utils.CheckCorrectValueUtils;
import views.utils.LabelValues;

import javax.swing.*;

public class AddStockPanel extends JPanel {
    public AddStockPanel() {
        add(new JLabel("Type good description"));
        JTextField goodDescriptionTextField = new JTextField(5);
        add(goodDescriptionTextField);

        add(Box.createHorizontalStrut(15));

        add(new JLabel("Type amount"));
        JTextField amountTextField = new JTextField(5);
        add(amountTextField);

        add(Box.createHorizontalStrut(15));

        add(new JLabel("Type price"));
        JTextField priceTextField = new JTextField(5);
        add(priceTextField);

        int result = JOptionPane.showConfirmDialog(null, this, LabelValues.ADD_STOCK_MENU_NAME, JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String goodDescription = CheckCorrectValueUtils.parseNotEmptyString(goodDescriptionTextField.getText());
            Integer amount = CheckCorrectValueUtils.parseNotNegativeInteger(amountTextField.getText());
            Double price = CheckCorrectValueUtils.parsePositiveDouble(priceTextField.getText());
            if ((goodDescription != null) && (amount != null) && (price != null)) {
                Stock newStock = new Stock(new Good(goodDescription), amount, price);
                Main.shop.addStock(newStock);
                Main.mainWindow.addStock(newStock.toStringArray());
            }
        }
    }
}
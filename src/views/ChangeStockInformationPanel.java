package views;

import javax.swing.*;

public class ChangeStockInformationPanel extends JPanel {
    public ChangeStockInformationPanel() {
        JTextField xField = new JTextField(5);
        JTextField yField = new JTextField(5);

        add(new JLabel("x:"));
        add(xField);
        add(new JLabel("y:"));
        add(yField);

        int result = JOptionPane.showConfirmDialog(null, this, "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("x value: " + xField.getText());
            System.out.println("y value: " + yField.getText());
        }
    }
}
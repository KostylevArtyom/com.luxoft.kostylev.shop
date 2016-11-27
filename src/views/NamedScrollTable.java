package views;

import javax.swing.*;
import java.awt.*;

public class NamedScrollTable extends JPanel {
    public NamedScrollTable(String name, String[]columnNames, String[][] data) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel jLabelName = new JLabel(name);
        jLabelName.setMinimumSize(new Dimension(Integer.MAX_VALUE, jLabelName.getMinimumSize().height));
        add(jLabelName, BorderLayout.NORTH);
        JTable jTable = new JTable(data, columnNames);
        add(new JScrollPane(jTable), BorderLayout.SOUTH);
    }
}
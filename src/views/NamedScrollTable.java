package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class NamedScrollTable extends JPanel {
    private JTable jTable;

    public NamedScrollTable(String name, String[]columnNames) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel jLabelName = new JLabel(name);
        jLabelName.setMinimumSize(new Dimension(Integer.MAX_VALUE, jLabelName.getMinimumSize().height));
        add(jLabelName, BorderLayout.NORTH);
        jTable = new JTable(new DefaultTableModel(columnNames, 0));
        add(new JScrollPane(jTable), BorderLayout.SOUTH);
    }

    public void addRow(String[] row) {
        ((DefaultTableModel)jTable.getModel()).addRow(row);
    }

    public void initializeRows(String[][] rows) {
        DefaultTableModel model = (DefaultTableModel)jTable.getModel();
        model.setRowCount(0);
        for (String[] row: rows)
            model.addRow(row);
    }
}
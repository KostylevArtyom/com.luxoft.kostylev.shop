package views;

import models.Customer;
import models.Stock;
import models.Trade;
import views.utils.LabelValues;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private static NamedScrollTable customersNamedScrollTable;
    private static NamedScrollTable stocksNamedScrollTable;
    private static NamedScrollTable tradesNamedScrollTable;

    public MainWindow() {
        super(LabelValues.FRAME_NAME);

        customersNamedScrollTable = new NamedScrollTable(LabelValues.CUSTOMERS_TABLE_NAME, Customer.getAllClasses());
        stocksNamedScrollTable = new NamedScrollTable(LabelValues.STOCKS_TABLE_NAME, Stock.getAllClasses());
        tradesNamedScrollTable = new NamedScrollTable(LabelValues.TRADES_TABLE_NAME, Trade.getAllClasses());
    }

    public void launchFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridLayout(1, 3));
        setJMenuBar(new MenuBar());
        setSize(1000, 800);

        add(customersNamedScrollTable);
        add(stocksNamedScrollTable);
        add(tradesNamedScrollTable);

        setVisible(true);
    }

    public void loadData(String customersMockData[][], String stocksMockData[][], String tradesMockData[][]) {
        customersNamedScrollTable.initializeRows(customersMockData);
        stocksNamedScrollTable.initializeRows(stocksMockData);
        tradesNamedScrollTable.initializeRows(tradesMockData);
    }
}
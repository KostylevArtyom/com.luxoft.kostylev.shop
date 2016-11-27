package views;

import models.Customer;
import models.Stock;
import models.Trade;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("Shop");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void launchFrame() {
        setLayout(new GridLayout(1, 3));

        setSize(1000, 800);

        String customersMockData[][] = {{"Alphonse"}, {"Barney"}, {"Clare"}, {"Demetra"}, {"Emma"}};
        add(new NamedScrollTable("Customers", Customer.getAllClasses(), customersMockData));

        String stocksMockData[][] = {{"Duck", "10", "3"}, {"Parrot", "3", "9.50"}};
        add(new NamedScrollTable("Stocks", Stock.getAllClasses(), stocksMockData));

        String tradesMockData[][] = {{"Alfred", "Duck", "3", "2.75", "27-11-16"}, {"Barney", "Parrot", "1", "10", "27-11-16"}};
        add(new NamedScrollTable("Trades", Trade.getAllClasses(), tradesMockData));

        setVisible(true);
    }
}
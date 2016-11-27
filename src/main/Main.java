package main;

import models.Shop;
import views.MainWindow;

public class Main {
    public static Shop shop;
    public static MainWindow mainWindow;

    public static void main(String[] args) {
        shop = new Shop();
        mainWindow = new MainWindow();
        mainWindow.launchFrame();
    }
}
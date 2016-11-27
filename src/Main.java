import database.Database;
import database.FileDatabase;
import models.Shop;
import views.MainWindow;

public class Main {
    public static void main(String[] args) {
        Database database = new FileDatabase("test.txt");
        System.out.println(database.readStringList());

        MainWindow mainWindow = new MainWindow();
        mainWindow.launchFrame();
    }
}
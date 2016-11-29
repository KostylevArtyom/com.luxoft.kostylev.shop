package models;

import exceptions.DatabaseClassIndexAlreadyExistException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShopTest {
    private static Shop shop;

    @Before
    public void initialize() {
        shop = new Shop();
        try {
            shop.addCustomer(new Customer("Alphonse"));
            shop.addCustomer(new Customer("Barney"));
            shop.addCustomer(new Customer("Clare"));
            shop.addCustomer(new Customer("Demetra"));
            shop.addCustomer(new Customer("Emma"));

            shop.addStock(new Stock(new Good("Flask"), 2, 110.0));
            shop.addStock(new Stock(new Good("Tango"), 3, 125.0));
            shop.addStock(new Stock(new Good("Clarity"), 3, 50.0));
            shop.addStock(new Stock(new Good("Mango"), 2, 150.0));
        } catch (DatabaseClassIndexAlreadyExistException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSuccessGetCustomerById() throws Exception {
        assertEquals(shop.getCustomerById(3).getName(), "Clare");
    }

    @Test
    public void testAddStock() throws Exception {
        shop.addStock(new Stock(new Good("Gem"), 1, 900.0));
        assertEquals(shop.getStocks().size(), 5);
    }

    @Test
    public void testAddStockDuplicate() throws Exception {
        Good newGood = new Good("Gem");
        shop.addStock(new Stock(newGood, 1, 900.0));
        shop.addStock(new Stock(newGood, 1, 1000.0));
        assertEquals(shop.getStocks().size(), 5);
    }
}
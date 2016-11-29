package models;

import database.Database;
import database.InMemoryArrayListDatabase;
import database.Storable;
import exceptions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Shop {
    private Database database;

    public Shop() {
        database = new InMemoryArrayListDatabase();
    }

    public Object getStorableById(Class storable, Integer id) throws DatabaseClassIndexNotExistException {
        return storable.cast(database.readObject(storable.getSimpleName(), id));
    }

    public Customer getCustomerById(Integer id) throws DatabaseClassIndexNotExistException {
        return (Customer)getStorableById(Customer.class, id);
    }

    public Stock getStockByGoodId(Integer id) throws DatabaseClassIndexNotExistException {
        return (Stock)getStorableById(Stock.class, id);
    }

    public Trade getTradeByGoodId(Integer id) throws DatabaseClassIndexNotExistException {
        return (Trade)getStorableById(Trade.class, id);
    }

    public Set<Customer> getCustomers() {
        return database.readAllObjects()
                .stream()
                .filter(s -> s.getClassSimpleName().equals(Customer.class.getSimpleName()))
                .map(Customer.class::cast)
                .collect(Collectors.toSet());
    }

    public void addCustomer(Customer customer) throws DatabaseClassIndexAlreadyExistException {
        database.writeObject(customer);
    }

    public Set<Stock> getStocks() {
        return database.readAllObjects()
                .stream()
                .filter(s -> s.getClassSimpleName().equals(Stock.class.getSimpleName()))
                .map(Stock.class::cast)
                .collect(Collectors.toSet());
    }

    public void addStock(Stock stock) throws DatabaseClassIndexAlreadyExistException {
        database.writeObject(stock);
    }

//    public void removeStock(Stock stock) {}

    public Set<Good> getGoods() {
        return getStocks().stream().map(s -> s.getGood()).collect(Collectors.toSet());
    }

//    public void removeGood(Good good) {}

    public Set<Trade> getTrades() {
        return database.readAllObjects()
                .stream()
                .filter(s -> s.getClassSimpleName().equals(Trade.class.getSimpleName()))
                .map(Trade.class::cast)
                .collect(Collectors.toSet());
    }

    public void addTrade(Trade trade) throws DatabaseClassIndexNotExistException,
            DatabaseClassIndexAlreadyExistException,
            CustomerNotExistException,
            GoodNotExistException,
            NotEnoughAmountException {
        if (!getCustomers().contains(trade.getCustomer()))
            throw new CustomerNotExistException();
        if (!getGoods().contains(trade.getGood()))
            throw new GoodNotExistException();
        Stock stock = getStockByGoodId(trade.getGood().getId());
        if (stock.getAmount() >= trade.getAmount()) {
            stock.subtractAmount(trade.getAmount());
            database.writeObject(trade);
        }
    }

//    public void removeTrade(Trade trade) {}

    public List<Storable> getStorablesList() {
        return database.readAllObjects();
    }

    public String[][] getCustomersStringArray() {
        return getCustomers().stream().map(Customer::toStringArray).toArray(size -> new String[size][1]);
    }

    public String[][] getStocksStringArray() {
        return getStocks().stream().map(Stock::toStringArray).toArray(size -> new String[size][1]);
    }

    public String[][] getTradesStringArray() {
        return getTrades().stream().map(Trade::toStringArray).toArray(size -> new String[size][1]);
    }
}
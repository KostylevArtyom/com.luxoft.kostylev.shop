package models;

import exceptions.CustomerNotExistException;
import exceptions.GoodNotExistException;
import exceptions.NotEnoughAmountException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Shop {
    private Set<Customer> customers;
    private Set<Stock> stocks;
    private Set<Trade> trades;

    public Shop() {
        customers = new HashSet<>();
        stocks = new HashSet<>();
        trades = new HashSet<>();
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Set<Stock> getStocks() {
        return stocks;
    }

    public void addStock(Stock stock) {
        if (stocks.contains(stock))
            stocks.stream().filter(s -> s == stock).forEach(s -> {
                s.addAmount(stock.getAmount());
                s.setPrice(stock.getPrice());
            });
        else
            stocks.add(stock);
    }

    public void removeStock(Stock stock) {
        stocks.remove(stock);
    }

    public Set<Stock> getGoods() {
        return stocks.stream().map(s -> s.getGood()).collect(Collectors.toSet());
    }

    public void removeGood(Good good) {
        stocks.removeIf(s -> s.getGood() == good);
    }

    public Set<Trade> getTrades() {
        return trades;
    }

    public void addTrade(Trade trade) throws CustomerNotExistException, GoodNotExistException, NotEnoughAmountException {
        if (!getCustomers().contains(trade.customer))
            throw new CustomerNotExistException();
        if (!getGoods().contains(trade.good))
            throw new GoodNotExistException();
        if (stocks.stream()
                .filter(s -> s.getGood() == trade.good)
                .map(s -> s.getAmount())
                .findAny()
                .get() < trade.getAmount())
            throw new NotEnoughAmountException();
        trades.add(trade);
    }

//    public void removeTrade(Trade trade) {}
}
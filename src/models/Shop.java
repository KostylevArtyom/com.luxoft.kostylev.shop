package models;

import exceptions.CustomerNotExistException;
import exceptions.GoodNotExistException;
import exceptions.NotEnoughAmountException;
import exceptions.DataParseException;
import models.utils.Constants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public Shop(List<String> data) {
        this();
        try {
            for (String dataRow : data) {
                String[] dataRowArray = dataRow.split(Constants.STORE_SEPARATOR);
                if (dataRowArray[0].equals(Customer.class.getSimpleName())) {
                    customers.add(new Customer(dataRowArray));
                } else if (dataRowArray[0].equals(Stock.class.getSimpleName())) {
                    stocks.add(new Stock(dataRowArray));
                } else if (dataRowArray[0].equals(Trade.class.getSimpleName())) {
                    trades.add(new Trade(dataRowArray,
                            getCustomerById(Integer.valueOf(dataRowArray[1])),
                            getGoodById(Integer.valueOf(dataRowArray[2]))));
                } else
                    throw new DataParseException();
            }
        } catch (DataParseException e) {
            e.printStackTrace();
        }
    }

    public List toObjectArray() {
        List data = new ArrayList();
        data.addAll(customers);
        data.addAll(stocks);
        data.addAll(trades);
        return data;
    }

    public Customer getCustomerById(Integer id) {
        return customers.stream().filter(s -> s.getId() == id).findAny().get();
    }

    public Good getGoodById(Integer id) {
        return stocks.stream().map(s -> s.getGood()).filter(s -> s.getId() == id).findAny().get();
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

    public Set<Good> getGoods() {
        return stocks.stream().map(s -> s.getGood()).collect(Collectors.toSet());
    }

    public void removeGood(Good good) {
        stocks.removeIf(s -> s.getGood() == good);
    }

    public Set<Trade> getTrades() {
        return trades;
    }

    public void addTrade(Trade trade) {
        try {
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
        } catch (NotEnoughAmountException e) {
            e.printStackTrace();
        } catch (CustomerNotExistException e) {
            e.printStackTrace();
        } catch (GoodNotExistException e) {
            e.printStackTrace();
        }
    }

//    public void removeTrade(Trade trade) {}
}
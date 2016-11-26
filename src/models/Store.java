package models;

import java.util.HashSet;
import java.util.Set;

public class Store {
    private Set<Stock> stocks;

    public Store() {
        stocks = new HashSet<>();
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
}
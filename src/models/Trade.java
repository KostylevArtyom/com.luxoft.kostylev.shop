package models;

import java.time.ZonedDateTime;

public class Trade {
    Customer customer;
    Good good;
    Integer amount;
    Double unitCost;
    ZonedDateTime time;

    public Trade(Customer customer, Stock stock, Integer amount) {
        this(customer, stock.getGood(), amount, stock.getPrice());
    }

    public Trade(Customer customer, Good good, Integer amount, Double unitCost) {
        this(customer, good, amount, unitCost, ZonedDateTime.now());
    }

    public Trade(Customer customer, Good good, Integer amount, Double unitCost, ZonedDateTime time) {
        this.customer = customer;
        this.good = good;
        this.amount = amount;
        this.unitCost = unitCost;
        this.time = time;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Good getGood() {
        return good;
    }

    public Integer getAmount() {
        return amount;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public ZonedDateTime getTime() {
        return time;
    }
}
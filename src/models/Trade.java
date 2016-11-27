package models;

import models.utils.Arrayable;
import models.utils.Constants;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Trade implements Arrayable {
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

    private class StorablePositions {
        static final int CUSTOMER_ID = 1;
        static final int GOOD_ID = 2;
        static final int AMOUNT = 3;
        static final int UNIT_COST = 4;
        static final int TIME = 5;
    }

    public Trade(String[] dataArray, Customer customer, Good good) {
        this(customer, good, Integer.valueOf(dataArray[StorablePositions.AMOUNT]),
                Double.valueOf(dataArray[StorablePositions.UNIT_COST]),
                ZonedDateTime.parse(dataArray[StorablePositions.TIME]));
    }

    @Override
    public String toString() {
        List<String> store = new ArrayList<>();
        store.add(getClass().getSimpleName());
        store.add(StorablePositions.CUSTOMER_ID, getCustomer().getId().toString());
        store.add(StorablePositions.GOOD_ID, getGood().getId().toString());
        store.add(StorablePositions.AMOUNT, getAmount().toString());
        store.add(StorablePositions.UNIT_COST, getUnitCost().toString());
        store.add(StorablePositions.TIME, getTime().toString());
        return String.join(Constants.STORE_SEPARATOR, store);
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

    private class ShowablePositions {
        static final int CUSTOMER_NAME = 0;
        static final int GOOD_DESCRIPTION = 1;
        static final int AMOUNT = 2;
        static final int UNIT_COST = 3;
        static final int TIME = 4;
    }

    private class ShowableNames {
        static final String CUSTOMER_NAME = "Customer";
        static final String GOOD_DESCRIPTION = "Good";
        static final String AMOUNT = "Amount";
        static final String UNIT_COST = "Unit cost";
        static final String TIME = "Time";
    }

    public static String[] getAllClasses() {
        List<String> store = new ArrayList<>();
        store.add(ShowablePositions.CUSTOMER_NAME, ShowableNames.CUSTOMER_NAME);
        store.add(ShowablePositions.GOOD_DESCRIPTION, ShowableNames.GOOD_DESCRIPTION);
        store.add(ShowablePositions.AMOUNT, ShowableNames.AMOUNT);
        store.add(ShowablePositions.UNIT_COST, ShowableNames.UNIT_COST);
        store.add(ShowablePositions.TIME, ShowableNames.TIME);
        return store.toArray(new String[store.size()]);
    }

    @Override
    public String[] toStringArray() {
        List<String> store = new ArrayList<>();
        store.add(ShowablePositions.CUSTOMER_NAME, getCustomer().getName());
        store.add(ShowablePositions.GOOD_DESCRIPTION, getGood().getDescription());
        store.add(ShowablePositions.AMOUNT, getAmount().toString());
        store.add(ShowablePositions.UNIT_COST,getUnitCost().toString());
        store.add(ShowablePositions.TIME, getTime().toString());
        return store.toArray(new String[store.size()]);
    }
}
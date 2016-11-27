package models;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private class StorablePositions {
        static final int CUSTOMER_ID = 0;
        static final int GOOD_ID = 1;
        static final int AMOUNT = 2;
        static final int UNIT_COST = 3;
        static final int TIME = 4;
    }

    public Trade(String[] dataArray, Customer customer, Good good) {
        this(customer, good, Integer.valueOf(dataArray[StorablePositions.AMOUNT]),
                Double.valueOf(dataArray[StorablePositions.UNIT_COST]),
                ZonedDateTime.parse(dataArray[StorablePositions.TIME]));
    }

    @Override
    public String toString() {
        List<String> store = new ArrayList<>();
        store.add(StorablePositions.CUSTOMER_ID, getCustomer().getId().toString());
        store.add(StorablePositions.GOOD_ID, getGood().getId().toString());
        store.add(StorablePositions.AMOUNT, getAmount().toString());
        store.add(StorablePositions.UNIT_COST, getUnitCost().toString());
        store.add(StorablePositions.TIME, getTime().toString());
        return String.join(Constants.STORE_SEPARATOR, store).concat("\n");
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

    private class StowablePositions {
        static final int CUSTOMER_NAME = 0;
        static final int GOOD_DESCRIPTION = 1;
        static final int AMOUNT = 2;
        static final int UNIT_COST = 3;
        static final int TIME = 4;
    }

    private class StowableNames {
        static final String CUSTOMER_NAME = "Customer";
        static final String GOOD_DESCRIPTION = "Good";
        static final String AMOUNT = "Amount";
        static final String UNIT_COST = "Unit cost";
        static final String TIME = "Time";
    }

    public static String[] getAllClasses() {
        List<String> store = new ArrayList<>();
        store.add(StowablePositions.CUSTOMER_NAME, StowableNames.CUSTOMER_NAME);
        store.add(StowablePositions.GOOD_DESCRIPTION, StowableNames.GOOD_DESCRIPTION);
        store.add(StowablePositions.AMOUNT, StowableNames.AMOUNT);
        store.add(StowablePositions.UNIT_COST, StowableNames.UNIT_COST);
        store.add(StowablePositions.TIME, StowableNames.TIME);
        return store.toArray(new String[store.size()]);
    }
}
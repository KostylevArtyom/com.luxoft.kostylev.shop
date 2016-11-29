package models;

import database.Storable;
import models.utils.Arrayable;
import models.utils.Constants;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Trade extends Storable implements Arrayable {
    private static Integer id_counter = 0;
    private Customer customer;
    private Good good;
    private Integer amount;
    private Double unitCost;
    private ZonedDateTime time;

    private void initializeFields(Customer customer, Good good, Integer amount, Double unitCost, ZonedDateTime time) {
        this.customer = customer;
        this.good = good;
        this.amount = amount;
        this.unitCost = unitCost;
        this.time = time;
    }

    public Trade(Customer customer, Stock stock, Integer amount) {
        this(customer, stock.getGood(), amount, stock.getPrice());
    }

    public Trade(Customer customer, Good good, Integer amount, Double unitCost) {
        this(customer, good, amount, unitCost, ZonedDateTime.now());
    }

    public Trade(Customer customer, Good good, Integer amount, Double unitCost, ZonedDateTime time) {
        super(++id_counter);
        initializeFields(customer, good, amount, unitCost, time);
    }

    public Trade(Customer customer, Good good, Integer amount, Double unitCost, ZonedDateTime time, Integer id) {
        super(id);
        if (id_counter <= id)
            id_counter = id + 1;
        initializeFields(customer, good, amount, unitCost, time);
    }

    private static class ToStringPositions {
        static final int CUSTOMER = 0;
        static final int GOOD = 1;
        static final int AMOUNT = 2;
        static final int UNIT_COST = 3;
        static final int TIME = 4;
    }

    @Override
    public String toString() {
        List<String> store = new ArrayList<>();
        store.add(ToStringPositions.CUSTOMER, Constants.wrapString(getCustomer().toString()));
        store.add(ToStringPositions.GOOD, Constants.wrapString(getGood().toString()));
        store.add(ToStringPositions.AMOUNT, getAmount().toString());
        store.add(ToStringPositions.UNIT_COST, getUnitCost().toString());
        store.add(ToStringPositions.TIME, getTime().toString());
        return super.toString() + String.join(Constants.SHOW_ITEMS_SEPARATOR, store);
    }

    public static class StorablePositions {
        static final int ID = 0;
        public static final int CUSTOMER_ID = 1;
        public static final int GOOD_ID = 2;
        static final int AMOUNT = 3;
        static final int UNIT_COST = 4;
        static final int TIME = 5;
    }

    public String toStorableString() {
        List<String> store = new ArrayList<>();
        store.add(StorablePositions.ID, super.toStorableString());
        store.add(StorablePositions.CUSTOMER_ID, getCustomer().getId().toString());
        store.add(StorablePositions.GOOD_ID, getGood().getId().toString());
        store.add(StorablePositions.AMOUNT, getAmount().toString());
        store.add(StorablePositions.UNIT_COST, getUnitCost().toString());
        store.add(StorablePositions.TIME, getTime().toString());
        return String.join(Constants.STORE_SEPARATOR, store);
    }

    public Trade(String[] dataArray, Customer customer, Good good) {
        this(
                customer,
                good,
                Integer.valueOf(dataArray[StorablePositions.AMOUNT]),
                Double.valueOf(dataArray[StorablePositions.UNIT_COST]),
                ZonedDateTime.parse(dataArray[StorablePositions.TIME]),
                Integer.valueOf(dataArray[StorablePositions.ID])
        );
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

    private static class ShowablePositions {
        static final int ID = 0;
        static final int CUSTOMER = 1;
        static final int GOOD = 2;
        static final int AMOUNT = 3;
        static final int UNIT_COST = 4;
        static final int TIME = 5;
    }

    private static class ShowableNames {
        static final String ID = "ID";
        static final String CUSTOMER = "Customer";
        static final String GOOD = "Good";
        static final String AMOUNT = "Amount";
        static final String UNIT_COST = "Unit cost";
        static final String TIME = "Time";
    }

    public static String[] getAllClasses() {
        List<String> store = new ArrayList<>();
        store.add(ShowablePositions.ID, ShowableNames.ID);
        store.add(ShowablePositions.CUSTOMER, ShowableNames.CUSTOMER);
        store.add(ShowablePositions.GOOD, ShowableNames.GOOD);
        store.add(ShowablePositions.AMOUNT, ShowableNames.AMOUNT);
        store.add(ShowablePositions.UNIT_COST, ShowableNames.UNIT_COST);
        store.add(ShowablePositions.TIME, ShowableNames.TIME);
        return store.toArray(new String[store.size()]);
    }

    @Override
    public String[] toStringArray() {
        List<String> store = new ArrayList<>();
        store.add(ShowablePositions.ID, getCustomer().getId().toString());
        store.add(ShowablePositions.CUSTOMER, getCustomer().toString());
        store.add(ShowablePositions.GOOD, getGood().toString());
        store.add(ShowablePositions.AMOUNT, getAmount().toString());
        store.add(ShowablePositions.UNIT_COST,getUnitCost().toString());
        store.add(ShowablePositions.TIME, getTime().toString());
        return store.toArray(new String[store.size()]);
    }
}
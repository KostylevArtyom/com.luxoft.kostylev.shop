package models;

import database.Storable;
import exceptions.NotEnoughAmountException;
import models.utils.Arrayable;
import models.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class Stock extends Storable implements Arrayable {
    private static Integer id_counter = 0;
    private Good good;
    private Integer amount;
    private Double price;

    private void initializeFields(Good good, Integer amount, Double price) {
        this.good = good;
        this.amount = amount;
        this.price = price;
    }

    public Stock(Good good, Integer amount, Double price) {
        super(++id_counter);
        initializeFields(good, amount, price);
    }

    public Stock(Good good, Integer amount, Double price, Integer id) {
        super(id);
        if (id_counter <= id)
            id_counter = id + 1;
        initializeFields(good, amount, price);
    }

    private class ToStringPositions {
        static final int GOOD = 1;
        static final int AMOUNT = 2;
        static final int PRICE = 3;
    }

    @Override
    public String toString() {
        List<String> store = new ArrayList<>();
        store.add(super.toString());
        store.add(ToStringPositions.GOOD, Constants.wrapString(getGood().toString()));
        store.add(ToStringPositions.AMOUNT, getAmount().toString());
        store.add(ToStringPositions.PRICE, getPrice().toString());
        return String.join(Constants.SHOW_SEPARATOR, store);
    }

    public Good getGood() {
        return good;
    }

//    public void modifyGoodDescription(String description) {
//        this.good.modifyDescription(description);
//    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void addAmount(Integer amount) {
        setAmount(getAmount() + amount);
    }

    public void subtractAmount(Integer amount) throws NotEnoughAmountException {
        if (getAmount() >= amount)
            setAmount(getAmount() - amount);
        else
            throw new NotEnoughAmountException();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
        return super.equals(((Stock)object).getGood());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private class ShowablePositions {
        static final int ID = 0;
        static final int GOOD = 0;
        static final int AMOUNT = 1;
        static final int PRICE = 2;
    }

    private class ShowableNames {
        static final String ID = "ID";
        static final String GOOD = "Good";
        static final String AMOUNT = "Amount";
        static final String PRICE = "Price";
    }

    public static String[] getAllClasses() {
        List<String> store = new ArrayList<>();
        store.add(ShowablePositions.ID, ShowableNames.ID);
        store.add(ShowablePositions.GOOD, ShowableNames.GOOD);
        store.add(ShowablePositions.AMOUNT, ShowableNames.AMOUNT);
        store.add(ShowablePositions.PRICE, ShowableNames.PRICE);
        return store.toArray(new String[store.size()]);
    }

    @Override
    public String[] toStringArray() {
        List<String> store = new ArrayList<>();
        store.add(ShowablePositions.ID, getId().toString());
        store.add(ShowablePositions.GOOD, getGood().toString());
        store.add(ShowablePositions.AMOUNT, getAmount().toString());
        store.add(ShowablePositions.PRICE, getPrice().toString());
        return store.toArray(new String[store.size()]);
    }
}
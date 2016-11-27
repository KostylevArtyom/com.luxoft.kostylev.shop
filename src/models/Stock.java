package models;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    private Good good;
    private Integer amount;
    private Double price;

    public Stock(Good good, Integer amount) {
        this.good = good;
        this.amount = amount;
        this.price = null;
    }

    public Stock(Good good, Integer amount, Double price) {
        this.good = good;
        this.amount = amount;
        this.price = price;
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

    private class Positions {
        static final int CLASS = 0;
        static final int GOOD_ID = 1;
        static final int AMOUNT = 2;
        static final int PRICE = 3;
    }

    private class StowablePositions {
        static final int GOOD_DESCRIPTION = 0;
        static final int AMOUNT = 1;
        static final int PRICE = 2;
    }

    private class StowableNames {
        static final String GOOD_DESCRIPTION = "Good";
        static final String AMOUNT = "Amount";
        static final String PRICE = "Price";
    }

    public static String[] getAllClasses() {
        List<String> store = new ArrayList<>();
        store.add(StowablePositions.GOOD_DESCRIPTION, StowableNames.GOOD_DESCRIPTION);
        store.add(StowablePositions.AMOUNT, StowableNames.AMOUNT);
        store.add(StowablePositions.PRICE, StowableNames.PRICE);
        return store.toArray(new String[store.size()]);
    }
}
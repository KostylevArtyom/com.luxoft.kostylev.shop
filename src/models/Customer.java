package models;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private static Integer id_counter = 0;

    private Integer id;
    private String name;

    public Customer(String name) {
        this.id = ++id_counter;
        this.name = name;
    }

    private Customer(String name, Integer id) {
        if (id_counter <= id)
            id_counter = id + 1;
        this.id = id;
        this.name = name;
    }

    private class StorablePositions {
        static final int ID = 1;
        static final int NAME = 2;
    }

    @Override
    public String toString() {
        List<String> store = new ArrayList<>();
        store.add(getClass().getSimpleName());
        store.add(StorablePositions.ID, getId().toString());
        store.add(StorablePositions.NAME, getName());
        return String.join(Constants.STORE_SEPARATOR, store);
    }

    public Customer(String[] dataArray) {
        this(dataArray[StorablePositions.NAME], Integer.valueOf(dataArray[StorablePositions.ID]));
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    public void modifyName(String name) {
//        this.name = name;
//    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
        if (this.getId() == ((Customer)object).getId())
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }

    private class StowablePositions {
        static final int NAME = 0;
    }

    private class StowableNames {
        static final String NAME = "Name";
    }

    public static String[] getAllClasses() {
        List<String> store = new ArrayList<>();
        store.add(StowablePositions.NAME, StowableNames.NAME);
        return store.toArray(new String[store.size()]);
    }
}
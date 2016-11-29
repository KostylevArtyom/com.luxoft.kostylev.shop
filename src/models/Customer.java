package models;

import database.Storable;
import models.utils.Arrayable;
import models.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Storable implements Arrayable {
    private static Integer id_counter = 0;
    private String name;

    private void initializeFields(String name) {
        this.name = name;
    }

    public Customer(String name) {
        super(++id_counter);
        initializeFields(name);
    }

    private Customer(String name, Integer id) {
        super(id);
        if (id_counter <= id)
            id_counter = id + 1;
        initializeFields(name);
    }

    private class ToStringPositions {
        static final int NAME = 0;
    }

    @Override
    public String toString() {
        List<String> store = new ArrayList<>();
        store.add(ToStringPositions.NAME, getName());
        return super.toString() + String.join(Constants.SHOW_ITEMS_SEPARATOR, store);
    }

    private class StorablePositions {
        static final int ID = 0;
        static final int NAME = 1;
    }

    public String toStorableString() {
        List<String> store = new ArrayList<>();
        store.add(StorablePositions.ID, super.toStorableString());
        store.add(StorablePositions.NAME, getName());
        return String.join(Constants.STORE_SEPARATOR, store);
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
        return super.getId();
    }

    private class ShowablePositions {
        static final int ID = 0;
        static final int NAME = 1;
    }

    private class ShowableNames {
        static final String ID = "ID";
        static final String NAME = "Name";
    }

    public static String[] getAllClasses() {
        List<String> store = new ArrayList<>();
        store.add(ShowablePositions.ID, ShowableNames.ID);
        store.add(ShowablePositions.NAME, ShowableNames.NAME);
        return store.toArray(new String[store.size()]);
    }

    @Override
    public String[] toStringArray() {
        List<String> store = new ArrayList<>();
        store.add(ShowablePositions.ID, getId().toString());
        store.add(ShowablePositions.NAME, getName());
        return store.toArray(new String[store.size()]);
    }
}
package models;

import database.Storable;
import models.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class Good extends Storable {
    private static Integer id_counter = 0;

    private String description;

    private void initializeFields(String description) {
        this.description = description;
    }

    public Good(String description) {
        super(++id_counter);
        initializeFields(description);
    }

    Good(String description, Integer id) {
        super(id);
        if (id_counter <= id)
            id_counter = id + 1;
        initializeFields(description);
    }

    private class ToStringPositions {
        static final int DESCRIPTION = 0;
    }

    @Override
    public String toString() {
        List<String> store = new ArrayList<>();
        store.add(ToStringPositions.DESCRIPTION, getDescription());
        return super.toString() + String.join(Constants.SHOW_ITEMS_SEPARATOR, store);
    }

    private static class StorablePositions {
        static final int ID = 0;
        static final int DESCRIPTION = 1;
    }

    public String toStorableString() {
        List<String> store = new ArrayList<>();
        store.add(StorablePositions.ID, super.toStorableString());
        store.add(StorablePositions.DESCRIPTION, getDescription());
        return String.join(Constants.STORE_SEPARATOR, store);
    }

    public String getDescription() {
        return description;
    }

//    public void modifyDescription(String description) {
//        this.description = description;
//    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
        if (this.getId() == ((Good)object).getId())
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return super.getId();
    }
}
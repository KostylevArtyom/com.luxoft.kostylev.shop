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
        static final int DESCRIPTION = 1;
    }

    @Override
    public String toString() {
        List<String> store = new ArrayList<>();
        store.add(super.toString());
        store.add(ToStringPositions.DESCRIPTION, getDescription());
        return String.join(Constants.SHOW_SEPARATOR, store);
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
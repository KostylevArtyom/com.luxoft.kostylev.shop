package models;

import models.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class Good {
    private static Integer id_counter = 0;

    private Integer id;
    private String description;

    public Good(String description) {
        this.id = ++id_counter;
        this.description = description;
    }

    Good(String description, Integer id) {
        if (id_counter <= id)
            id_counter = id + 1;
        this.id = id;
        this.description = description;
    }

    private class StorablePositions {
        static final int ID = 1;
        static final int DESCRIPTION = 2;
    }

    @Override
    public String toString() {
        List<String> store = new ArrayList<>();
        store.add(getClass().getSimpleName());
        store.add(StorablePositions.ID, getId().toString());
        store.add(StorablePositions.DESCRIPTION, getDescription());
        return String.join(Constants.STORE_SEPARATOR, store);
    }

    public Integer getId() {
        return id;
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
        return id;
    }
}
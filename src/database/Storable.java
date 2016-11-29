package database;

import models.utils.Constants;

public abstract class Storable {
    private Integer id;

    public Storable(Integer id) {
        this.id = id;
    }

    public final Integer getId() {
        return id;
    }

    public final String getClassSimpleName() {
        return getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return getClassSimpleName()
                + Constants.SHOW_CLASS_WRAPPER_LEFT + getId()
                + Constants.SHOW_CLASS_WRAPPER_RIGHT
                + Constants.SHOW_CLASS_SEPARATOR;
    }

    public String toStorableString() {
        return getClass().getName() + Constants.STORE_SEPARATOR + getId();
    }
}
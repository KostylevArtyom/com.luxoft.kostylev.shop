package database;

public abstract class Storable {
    private Integer id;

    public Storable(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getClassSimpleName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return getClassSimpleName() + "(" + getId() + "): ";
    }
}
package models;

public class Good {
    private static Integer id_counter = 0;

    private Integer id;
    private String description;

    public Good(String description) {
        this.id = ++id_counter;
        this.description = description;
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
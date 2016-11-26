package models;

public class Customer {
    private static Integer id_counter = 0;

    private Integer id;
    private String name;

    public Customer(String name) {
        this.id = ++id_counter;
        this.name = name;
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
}
package models;

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
}
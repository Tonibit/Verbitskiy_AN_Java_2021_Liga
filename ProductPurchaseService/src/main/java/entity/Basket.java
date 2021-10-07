package entity;

import java.util.HashMap;

public class Basket{

    private HashMap<Item, Integer> itemList;
    private Double totalPrice;
    private User user;

    public Basket() {
        this.itemList = new HashMap<>();
        totalPrice = 0.0;
    }

    public Basket(User user) {
        this.user = user;
        this.itemList = new HashMap<>();
        totalPrice = 0.0;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public HashMap<Item, Integer> getItemList() {
        return itemList;
    }

    public User getUser() {
        return user;
    }
}

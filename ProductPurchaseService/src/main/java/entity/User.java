package entity;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String phone;
    private Basket basket;
    private Order order;
    private List<Order> orderList;

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
        basket = new Basket(this);
        order = null;
        orderList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Basket getBasket() {
        return basket;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}

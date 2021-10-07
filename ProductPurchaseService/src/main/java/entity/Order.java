package entity;

public class Order {
    private Basket basket;
    private Integer orderNumber;
    private static int number = 0;

    public Order(Basket basket) {
        copyBasket(basket);
        number++;
        orderNumber = number;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        copyBasket(basket);
    }

    private void copyBasket(Basket basket) {
       this.basket = new Basket();
       this.basket.setTotalPrice(basket.getTotalPrice());
       this.basket.getItemList().putAll(basket.getItemList());
    }

}

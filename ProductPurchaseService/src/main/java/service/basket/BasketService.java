package service.basket;

import entity.Item;

public interface BasketService {
    void addItem(Item item, Integer quantity);
    void removeItem(Item item);
    void increaseQuantityOfItem(Item item, Integer quantity);
    void decreaseQuantityOfItem(Item item, Integer quantity);
    String printBasket();
    void clearBasket();
}

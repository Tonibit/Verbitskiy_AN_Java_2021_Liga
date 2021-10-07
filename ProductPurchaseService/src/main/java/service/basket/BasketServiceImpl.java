package service.basket;

import entity.Basket;
import entity.Item;
import entity.Order;
import entity.User;

import java.util.Map;

public class BasketServiceImpl implements BasketService {

    private Basket basket;

    public BasketServiceImpl(User user) {
        this.basket = user.getBasket();
    }

    @Override
    public void addItem(Item item, Integer quantity) {
        if (isQuantityMoreThanZero(quantity)) {
            if (basket.getItemList().containsKey(item)) {
                increaseQuantityOfItem(item, quantity);
            } else {
                basket.getItemList().put(item, quantity);
                double price = item.getPrice() * quantity;
                changeTotalPrice(price);
            }
        }
    }

    @Override
    public void removeItem(Item item) {
        if (checkItemInDaBasket(item)) {
            Integer quantity = basket.getItemList().get(item);
            basket.getItemList().remove(item);
            double price = item.getPrice() * quantity;
            changeTotalPrice(-price);
        }
    }

    @Override
    public void increaseQuantityOfItem(Item item, Integer quantity) {
        if (isQuantityMoreThanZero(quantity)) {
            if (checkItemInDaBasket(item)) {
                //увеличиваем кол-во товаров
                Integer itemQuantity = basket.getItemList().get(item);
                itemQuantity += quantity;
                basket.getItemList().put(item, itemQuantity);
                //увеличиваем общую стоимость
                double price = item.getPrice() * quantity;
                changeTotalPrice(price);
            }
        }
    }

    @Override
    public void decreaseQuantityOfItem(Item item, Integer quantity) {
        if (isQuantityMoreThanZero(quantity)) {
            if (checkItemInDaBasket(item)) {
                Integer itemQuantity = basket.getItemList().get(item);
                //проверяем кол-во товара в корзине, если их больше, чем передано на уменьшение, то уменьшаем,
                //иначе удаляем из корзины сам товар.
                if (itemQuantity > quantity) {
                    itemQuantity -= quantity;
                    basket.getItemList().put(item, itemQuantity);
                    //уменьшаем общую стоимость корзины
                    double price = item.getPrice() * quantity;
                    changeTotalPrice(-price);
                } else {
                    removeItem(item);
                }
            }
        }
    }

    @Override
    public void clearBasket() {
        if (!isBasketEmpty()) {
            basket.getItemList().clear();
            changeTotalPrice(-basket.getTotalPrice());
        }
    }

    @Override
    public String printBasket() {
        StringBuilder sb = new StringBuilder();
        if (!isBasketEmpty()) {
            sb.append("В корзине: \n");
            for (Map.Entry<Item, Integer> pair : basket.getItemList().entrySet()) {
                sb.append("\t\"").append(pair.getKey().getName())
                        .append("\" количество ").append(pair.getValue())
                        .append(" шт.").append(" Цена за шт: ")
                        .append(pair.getKey().getPrice());
            }
            sb.append("Общая сумма: ").append(basket.getTotalPrice()).append(" руб.");
        }
        return sb.toString();
    }

    public void placeAnOrder() {
        if (!isBasketEmpty()) {
            basket.getUser().setOrder(new Order(basket));
            clearBasket();
        }
    }

    private boolean checkItemInDaBasket(Item item) {
        if (basket.getItemList().containsKey(item)) {
            return true;
        } else {
            System.out.println("Такого товара нет в корзине.");
            return false;
        }
    }

    private boolean isBasketEmpty() {
        if (basket.getItemList().isEmpty()) {
            System.out.println("Корзина пуста.");
            return true;
        } else {
            return false;
        }
    }

    private void changeTotalPrice(Double price) {
        Double totalPrice = basket.getTotalPrice();
        totalPrice += price;
        basket.setTotalPrice(totalPrice);
    }

    private boolean isQuantityMoreThanZero(Integer quantity) {
        if (quantity > 0) {
            return true;
        } else {
            System.out.println("Введено неверное количество товара (число должно быть больше 0).");
            return false;
        }
    }
}

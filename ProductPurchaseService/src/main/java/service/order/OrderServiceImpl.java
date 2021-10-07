package service.order;

import entity.Item;
import entity.Order;
import entity.User;

import java.util.Map;

public class OrderServiceImpl implements OrderService {

    @Override
    public void makeOrder(User user) {
        if (!isOrderNull(user)) {
            System.out.println("Вы оплатили заказ № " + user.getOrder().getOrderNumber() +
                    " на сумму " + user.getOrder().getBasket().getTotalPrice() + " руб.");
            user.getOrderList().add(user.getOrder());
            user.setOrder(null);
        }
    }

    @Override
    public String printOrder(User user) {
        StringBuilder sb = new StringBuilder();
        if (!isOrderNull(user)) {
            sb.append(String.format("Ваш заказ № %s на сумму %s руб.\nВ корзине:",
                    user.getOrder().getOrderNumber(),
                    user.getOrder().getBasket().getTotalPrice()));
            for (Map.Entry<Item, Integer> pair : user.getOrder().getBasket().getItemList().entrySet()) {
                sb.append("\t\"").append(pair.getKey().getName())
                        .append("\" количество ").append(pair.getValue())
                        .append(" шт.").append(" Цена за шт: ")
                        .append(pair.getKey().getPrice());
            }
        }
        return sb.toString();
    }

    @Override
    public void cancelOrder(User user) {
        if (!isOrderNull(user)) {
            System.out.println("Заказ № " + user.getOrder().getOrderNumber() + " отменен.");
            user.setOrder(null);
        }

    }

    private boolean isOrderNull(User user) {
        if (user.getOrder() == null) {
            System.out.println("Сначала необходимо выполнить оформление заказа");
            return true;
        } else {
            return false;
        }
    }

}

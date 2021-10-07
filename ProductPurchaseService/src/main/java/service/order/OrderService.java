package service.order;

import entity.User;

public interface OrderService {
    void makeOrder(User user);
    String printOrder(User user);
    void cancelOrder(User user);
}

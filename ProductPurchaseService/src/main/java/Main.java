import entity.Item;
import entity.Order;
import entity.User;
import service.basket.BasketServiceImpl;
import service.order.OrderServiceImpl;

public class Main {
    public static void main(String[] args) {
        Item item1 = new Item("Phone", 700.0);
        Item item2 = new Item("Jacket", 150.0);
        Item item3 = new Item("Whiskey", 200.0);

        User user = new User("Anton", "+79994561234");
        BasketServiceImpl basketService = new BasketServiceImpl(user);

        basketService.addItem(item2, 10);
        System.out.println(user.getBasket().getItemList().get(item2));
        basketService.addItem(item3, 5);
        basketService.addItem(item2, 1);

        basketService.printBasket();
        System.out.println();

        basketService.decreaseQuantityOfItem(item3, 3);
        basketService.increaseQuantityOfItem(item1, 2);
        basketService.addItem(item1, 3);
        basketService.printBasket();
        System.out.println();

        basketService.removeItem(item2);

        basketService.printBasket();
        System.out.println();

        basketService.placeAnOrder();
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.printOrder(user);
        basketService.printBasket();
        System.out.println(user.getBasket().getTotalPrice());

        System.out.println();
        orderService.cancelOrder(user);
    }
}

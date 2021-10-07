import entity.Item;
import entity.Order;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import service.basket.BasketServiceImpl;
import service.order.OrderServiceImpl;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    private OrderServiceImpl orderService;
    private User user;
    private BasketServiceImpl basketService;
    private Item phone;
    private Item jacket;
    private Item whiskey;

    @Before
    public void setUp() throws Exception {
        user = new User("Anton", "79999883920");
        phone = new Item("Phone", 700.0);
        jacket = new Item("Jacket", 150.0);
        whiskey = new Item("Whiskey", 200.0);
        basketService = new BasketServiceImpl(user);
        basketService.addItem(phone,2);
        basketService.addItem(whiskey,3);
        basketService.addItem(jacket,1);
        basketService.placeAnOrder();
        orderService = new OrderServiceImpl();
    }

    @Test
    public void testCancelOrder() {
        assertNotNull(user.getOrder());
        orderService.cancelOrder(user);
        assertNull(user.getOrder());
    }

    @Test
    public void testPrintOrder() {
        StringBuilder message = new StringBuilder();
        message.append(String.format("Ваш заказ № %s на сумму %s руб.\nВ корзине:",
                user.getOrder().getOrderNumber(),
                user.getOrder().getBasket().getTotalPrice()));
        for (Map.Entry<Item, Integer> pair : user.getOrder().getBasket().getItemList().entrySet()) {
            message.append("\t\"").append(pair.getKey().getName())
                    .append("\" количество ").append(pair.getValue())
                    .append(" шт.").append(" Цена за шт: ")
                    .append(pair.getKey().getPrice());
        }

        assertEquals(message.toString(), orderService.printOrder(user));
    }

    @Test
    public void testMakeOrder() {
        assertNotNull(user.getOrder());
        assertEquals(0,user.getOrderList().size());
        Order order = user.getOrder();
        orderService.makeOrder(user);
        //проверка, что при оплате заказа у пользовтеля заказ удалился(переменная order =null)
        assertNull(user.getOrder());
        //проверка, что заказ добавился в лист заказов
        assertEquals(1, user.getOrderList().size());
        //проверка, что заказы совпадают
        assertEquals(order, user.getOrderList().get(0));
    }

}

import entity.Item;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import service.basket.BasketServiceImpl;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BasketServiceTest {

    private BasketServiceImpl basketService;
    private User user;
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
    }


    @Test
    public void testAddItemToBasketWithPositiveQuantity() {
        //проверка, что корзина есть и она пуста (по цене и элементам)
        assertEquals(0.0, user.getBasket().getTotalPrice());
        assertEquals(0, user.getBasket().getItemList().size());
        basketService.addItem(phone, 3);
        assertEquals(2100.0, user.getBasket().getTotalPrice());
        assertTrue(user.getBasket().getItemList().containsKey(phone));
    }

    @Test
    public void testAddItemToBasketWithNegativeOrZeroQuantity() {
        basketService.addItem(jacket,-4);
        assertEquals(0.0, user.getBasket().getTotalPrice());
        assertFalse(user.getBasket().getItemList().containsKey(jacket));
    }

    @Test
    public void testAddItemToBasketThatAlreadyInIt_ShouldIncreaseQuantity() {
        //добавляем 1 продукт и проверяем кол-во в корзине
        basketService.addItem(phone,1);
        assertEquals(1, user.getBasket().getItemList().size());
        assertEquals(1,user.getBasket().getItemList().get(phone));
        assertEquals(700.0,user.getBasket().getTotalPrice());

        //добавляем тот продукт и проверяем увеличилось ли кол-во элементов в корзине
        basketService.addItem(phone,5);
        assertEquals(1,user.getBasket().getItemList().size());
        assertEquals(6,user.getBasket().getItemList().get(phone));
        assertEquals(4200.0,user.getBasket().getTotalPrice());
    }

    @Test
    public void testRemoveItem() {
        basketService.addItem(whiskey, 2);
        assertEquals(1, user.getBasket().getItemList().size());
        assertEquals(400.0, user.getBasket().getTotalPrice());
        basketService.removeItem(whiskey);
        assertTrue(user.getBasket().getItemList().isEmpty());
        assertEquals(0, user.getBasket().getTotalPrice());
    }

    @Test
    public void testIncreaseQuantityOfItem() {
        basketService.addItem(jacket,1);
        basketService.increaseQuantityOfItem(jacket,4);
        assertEquals(5,user.getBasket().getItemList().get(jacket));
    }

    @Test
    public void testDecreaseQuantityOfItem() {
        basketService.addItem(whiskey,5);
        basketService.decreaseQuantityOfItem(whiskey,3);
        assertEquals(2,user.getBasket().getItemList().get(whiskey));
    }

    @Test
    public void testPrintBasketIfBasketIsNotEmpty() {
        basketService.addItem(whiskey, 2);
        basketService.addItem(jacket, 1);
        basketService.addItem(phone, 3);
        StringBuilder message = new StringBuilder();
        message.append("В корзине: \n");
        for (Map.Entry<Item, Integer> pair : user.getBasket().getItemList().entrySet()) {
            message.append("\t\"").append(pair.getKey().getName())
                    .append("\" количество ").append(pair.getValue())
                    .append(" шт.").append(" Цена за шт: ")
                    .append(pair.getKey().getPrice());
        }
        message.append("Общая сумма: ").append(user.getBasket().getTotalPrice()).append(" руб.");

        assertEquals(message.toString(), basketService.printBasket());

    }

    @Test
    public void testPrintBasketIfBasketIsEmpty() {
        assertTrue(user.getBasket().getItemList().isEmpty());
        assertEquals("",basketService.printBasket());
    }

    @Test
    public void testClearBasket() {
        basketService.addItem(phone, 2);
        basketService.addItem(whiskey, 2);
        assertEquals(2, user.getBasket().getItemList().size());
        assertEquals(1800.0,user.getBasket().getTotalPrice());
        basketService.clearBasket();
        assertTrue(user.getBasket().getItemList().isEmpty());
        assertEquals(0.0, user.getBasket().getTotalPrice());
    }

    @Test
    public void testPlaceAnOrder() {
        basketService.addItem(phone,1);
        basketService.addItem(whiskey,2);
        basketService.addItem(jacket,1);
        assertNull(user.getOrder());
        basketService.placeAnOrder();
        //проверяем оформился ли заказ и очистилась ли корзина у пользовтеля
        assertNotNull(user.getOrder());
        assertTrue(user.getBasket().getItemList().isEmpty());
    }
}

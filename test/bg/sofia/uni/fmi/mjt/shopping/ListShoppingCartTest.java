package bg.sofia.uni.fmi.mjt.shopping;

import bg.sofia.uni.fmi.mjt.shopping.item.Apple;
import bg.sofia.uni.fmi.mjt.shopping.item.Chocolate;
import bg.sofia.uni.fmi.mjt.shopping.item.Item;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class ListShoppingCartTest {

    public static final double APPLE_PRICE = 7.1;
    public static final double PRICE = 4.6;
    public static final double CHOCOLATE_PRICE1 = 7.0;
    public static final double CHOCOLATE_PRICE2 = 8.1;
    public static final double APPLE_PRICE1 = 2.3;
    public static final double PRICE1 = 2.3;
    public static final double COMPARE = 9.3;

    @Test
    public void getUniqueItems() {
        ListShoppingCart list = new ListShoppingCart();
        Item second = new Apple("Qbylchica", "zelenichka", APPLE_PRICE);
        Item third = new Apple("Qbylchica", "zelenichka", APPLE_PRICE);
        list.addItem(second);
        list.addItem(third);
        assertEquals(1, list.getSortedItems().size());
    }

    @Test(expected = ItemNotFoundException.class)
    public void removeItemExceptionTest() throws ItemNotFoundException {
        ListShoppingCart cart = new ListShoppingCart();
        cart.removeItem(new Apple("qbylka", "zelena", PRICE));
    }

    @Test
    public void removeItemTest() throws ItemNotFoundException {
        ListShoppingCart list = new ListShoppingCart();
        Item second = new Chocolate("Shokoladche", "bqlo", APPLE_PRICE);
        list.addItem(second);

        list.removeItem(second);
        assertEquals(0, list.getUniqueItems().size());
    }

    @Test
    public void getTotalTest() {
        ListShoppingCart list = new ListShoppingCart();
        Item second = new Chocolate("Shokoladche", "bqlo", CHOCOLATE_PRICE1);
        Item first = new Apple("Qbylchica", "chervena", PRICE1);

        list.addItem(second);
        list.addItem(first);

        assertEquals(0, Double.compare(COMPARE, list.getTotal()));
    }

    @Test
    public void getSortedItemsTest() {
        ListShoppingCart cart = new ListShoppingCart();
        Item first = new Apple("Qbylchica", "chervena", APPLE_PRICE1);
        Item second = new Chocolate("Shokoladche", "cheren", CHOCOLATE_PRICE2);
        Item third = new Chocolate("Shokoladche", "bqlo", APPLE_PRICE);

        cart.addItem(first);
        cart.addItem(first);
        cart.addItem(first);
        cart.addItem(first);
        cart.addItem(first);
        cart.addItem(second);
        cart.addItem(second);
        cart.addItem(second);
        cart.addItem(third);
        cart.addItem(third);
        cart.addItem(third);
        cart.addItem(third);

        Collection<Item> items = cart.getSortedItems();
        ArrayList<Item> newItems = new ArrayList<>();
        newItems.add(second);
        newItems.add(third);
        newItems.add(first);

        assertEquals(newItems.get(0), second);
        assertEquals(newItems.get(1), third);
        assertEquals(newItems.get(2), first);
    }
}

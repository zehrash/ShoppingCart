package bg.sofia.uni.fmi.mjt.shopping;

import bg.sofia.uni.fmi.mjt.shopping.item.Apple;
import bg.sofia.uni.fmi.mjt.shopping.item.Chocolate;
import bg.sofia.uni.fmi.mjt.shopping.item.Item;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class MapShoppingCartTest {

    public static final double APPLE_PRICE = 2.3;
    public static final double CHOCOLATE_PRICE = 7.1;
    public static final double CHOCOLATE_PRICE_SECOND = 8.1;
    public static final int PRICE = 2;
    public static final int PRICE1 = 7;
    public static final int COMPARE = 16;

    @Test
    public void addItemTest() {
        MapShoppingCart cart = new MapShoppingCart();
        Item first = new Apple("Qbylchica", "chervena", APPLE_PRICE);
        Item second = new Chocolate("Shokoladche", "bqlo", CHOCOLATE_PRICE);
        Item third = new Chocolate("Shokoladche", "bqlo", CHOCOLATE_PRICE);

        cart.addItem(first);
        cart.addItem(second);
        cart.addItem(third);

        assertEquals(2, cart.getUniqueItems().size());

    }

    @Test
    public void removeItemTest() throws ItemNotFoundException {
        MapShoppingCart cart = new MapShoppingCart();
        Item first = new Apple("Qbylchica", "chervena", APPLE_PRICE);
        Item second = new Chocolate("Shokoladche", "cheren", CHOCOLATE_PRICE_SECOND);
        Item third = new Chocolate("Shokoladche", "bqlo", CHOCOLATE_PRICE);

        cart.addItem(first);
        cart.addItem(second);
        cart.addItem(second);
        cart.addItem(third);

        cart.removeItem(second);
        cart.removeItem(first);

        assertEquals(2, cart.getUniqueItems().size());
    }

    @Test
    public void getTotalTest() {

        MapShoppingCart cart = new MapShoppingCart();
        Item first = new Apple("Qbylchica", "chervena", PRICE);
        Item second = new Chocolate("Shokoladche", "bqlo", PRICE1);
        Item third = new Chocolate("Shokoladche", "bqlo", PRICE1);

        cart.addItem(first);
        cart.addItem(second);
        cart.addItem(third);

        //    cart.getTotal();
        assertEquals(0, Double.compare(COMPARE, cart.getTotal()));
    }

    @Test
    public void getSortedItemsTest() {
        MapShoppingCart cart = new MapShoppingCart();
        Item first = new Apple("Qbylchica", "chervena", APPLE_PRICE);
        Item second = new Chocolate("Shokoladche", "cheren", CHOCOLATE_PRICE_SECOND);
        Item third = new Chocolate("Shokoladche", "bqlo", CHOCOLATE_PRICE);

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
        ArrayList<Item> newItems = new ArrayList<>(items);

        assertEquals(newItems.get(0), first);
        assertEquals(newItems.get(1), second);
        assertEquals(newItems.get(2), third);
    }

    @Test(expected = ItemNotFoundException.class)
    public void removeItemExceptionTest() throws ItemNotFoundException {
        MapShoppingCart cart = new MapShoppingCart();
        cart.removeItem(null);
    }

}

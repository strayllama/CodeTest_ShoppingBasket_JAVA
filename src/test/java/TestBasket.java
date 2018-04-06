import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBasket {

    private Basket basket;
    private Customer customer;
    private Item item1;
    private Item item2;

    @Before
    public void before() {
        customer = new Customer("Steve", false);
        basket = new Basket(customer);
        item1 = new Item("Mars Bar", 100, true);
        item2 = new Item("Tin Foil", 300, false);
    }

    @Test
    public void testBasketHasCustomer() {
        assertEquals("Steve", basket.getCustomerName());
    }

    @Test
    public void testCanAddToBasket() {
        basket.addItem(item1);
        basket.addItem(item1);
        basket.addItem(item1);
        basket.addItem(item1);
        basket.addItem(item1);
        assertEquals(500, basket.getNumberOfItems());
    }

    @Test
    public void testBasketKnowsOfCustomersLoyalityCard() {
        assertEquals(false, basket.hasLoyalityCard());
    }

    @Test
    public void testCanRemoveFromBasket() {
        basket.addItem(item1);
        basket.addItem(item2);
        assertEquals(200, basket.getNumberOfItems());
        basket.removeItem(item1);
        assertEquals(100, basket.getNumberOfItems());
    }

    @Test
    public void testListBasketContents() {
        Item item3 = new Item("Towel", 600, false);
        Item item4 = new Item("Mug", 200, true);
        Item item5 = new Item("Lunch Box", 1000, false);
        basket.addItem(item1);
        basket.addItem(item2);
        basket.addItem(item3);
        basket.addItem(item4);
        basket.addItem(item5);
        basket.addItem(item3);
        basket.listItemsInBasket();
    }

    @Test
    public void testCanGetTotalValueOfBasketWithoutDiscounts() {
        basket.addItem(item1);
        basket.addItem(item2);
        assertEquals(400, basket.getTotalPrice(), 0.01);
    }

    @Test
    public void testBogofDiscountGetsAppliedIfItemSuits() {
        basket.addItem(item1);
        basket.addItem(item1);
        assertEquals(100, basket.getTotalPrice(), 0.01);
    }

    @Test
    public void testBogofDiscountGetsAppliedOnlyForPairsOfItems() {
        basket.addItem(item1);
        basket.addItem(item1);
        basket.addItem(item1);
        assertEquals(200, basket.getTotalPrice(), 0.01);
        basket.addItem(item1);
        assertEquals(200, basket.getTotalPrice(), 0.01);
    }


    @Test
    public void testGet10percentDiscountOVer20pounds() {
        Item item4 = new Item("Mug", 200, true);
        Item item5 = new Item("Lunch Box", 1000, false);
        basket.addItem(item4);
        basket.addItem(item5);
        basket.addItem(item5);
        basket.listItemsInBasket();
        assertEquals(1980, basket.getTotalPrice(), 0.01);
    }

    @Test
    public void testGet2percentOffIfHasLoyaltyCard() {
        Customer customer2 = new Customer("Jane", true);
        Basket basket2 = new Basket(customer2);
        item1 = new Item("Mars Bar", 100, true);
        item2 = new Item("Tin Foil", 300, false);
        basket2.addItem(item1);
        basket2.addItem(item2);
        assertEquals(true, basket2.hasLoyalityCard());
        assertEquals(392, basket2.getTotalPrice(), 0.01);
    }
}

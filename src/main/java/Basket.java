import java.util.*;
/**
 * Shopping Basket
 * The ShoppingBasket project is a Code Test.
 * 3 classes: Basket the main functionality, with Item and Customer as ancillary objects.
 *
 * @author  Paul Clatworthy
 * @version 1.0
 * @since   2018-08-04
 */

public class Basket {
    private Customer customer;
    private Map<Item, Integer> itemsCounted;
    private double discountA;
    private int amountToApplyTenPercent;

    /*
     * Basket constructor requires a customer object, the main property of which is a boolean
     * for having a loyalty card or not, along with string for name.
     * @param customer is added to the basket.
     *
     * Map<Item, Integer> itemsCounted holds all the Item objects added to the basket and an integer
     * value to represent the quantity of each item, which is updated as items are added.
     */
    public Basket(Customer customer) {
        this.customer = customer;
        this.itemsCounted = new HashMap<>();
        this.discountA = 0.1;
        this.amountToApplyTenPercent = 2000;
    }


    /*
     * Method getCustomerName is a simple getter for the customer object name
     * @return Returns a String of customer object name
     */
    public Customer getCustomer() {
        return customer;
    }



    /*
     * addItem Adds an object Item to Map itemsCounted, using value 1 if doesn't exist already
     * or incrementing the value if it already exists in the Map.
     */
    public void addItem(Item item) {
        if (itemsCounted.get(item) != null ) {
            itemsCounted.put(item, itemsCounted.get(item) + 1);
        } else { itemsCounted.put(item, 1); }
    }


    /*
     * getNumberOfItems is purely used for Testing that the itemsCounted list gets added to.
     * DELETE THIS FOR PRODUCTION!!!!!!!!
     * @return Returns int value of total number of items in itemsCounted.
     */
    public int getNumberOfItems() {
        int numberOfItems = 0;
        for (Map.Entry mappedEntry : itemsCounted.entrySet()) {
            numberOfItems += (int)mappedEntry.getValue();
        }
        return numberOfItems;
    }

    /*
     * removeItem will decrease the quantity value in itemsCounted for the item passed in.
     * If there is only one item of the matching Item type, it will be removed from the list
     * completely rather than setting quantity to zero.
     *
     * @param item is Object Item passed of which you wish to remove from Map itemsCounted.
     */
    public void removeItem(Item item) {
        if (itemsCounted.get(item) > 1 ) {
            itemsCounted.put(item, itemsCounted.get(item) - 1);
        } else { itemsCounted.remove(item); }
    }


    /*
     * getTotalPrice loops through every Map<Item, Integer> in itemsCounted
     * and calculates the total price by multiplying the quantity (Integer value from Map)
     * and price (property of Item object). It also takes account of items that have true
     * for bogof (buyOneGetOneFree) where it only adds the price of the first of each pair
     * of items with this property.
     *
     * The total is also reduced by 10% when over 2000 (pennies) (after bogof is applied)
     * and lastly takes a further 2% off if the customer has true for loyalty card.
     *
     * @return returns integer value for total price of basket in pennies, taking account
     * of discounts that apply to the items in the basket and customer.
     */
    public float getTotalPrice() {
        int totalPrice = 0;

        for (Item item : itemsCounted.keySet()) {
            if ( item.isBogof() ) {

                if (itemsCounted.get(item) > 1) { // price * (number of items/2) rounded up to account for a single item price when odd number)
                    int roundedUp = itemsCounted.get(item) / 2 + ((itemsCounted.get(item) % 2 == 0) ? 0 : 1);
                    totalPrice += roundedUp * item.getPrice();
                } else { // add price if only 1 item and its bogof
                    totalPrice += item.getPrice(); } // end nested if, item quanity more than 1.

            } else { // add price if not bogof, price * quantity
                totalPrice += item.getPrice() * itemsCounted.get(item); } // end if, item bogof
        } // end for


        int totalPricediscounted;
        totalPricediscounted = applyPercentDiscount(totalPrice, amountToApplyTenPercent, discountA);


        if ( customer.hasLoyaltyCard() ) {
            totalPricediscounted -= (totalPricediscounted * 0.02);
        }

        return totalPricediscounted;
    }


    public int applyPercentDiscount(int totPrice, int amountOverBeforeApply, double discount) {
        if (totPrice > amountOverBeforeApply) {
            totPrice -= (totPrice * discount);
        }
        return totPrice;
    }



    /*
     * listItemsInBasket gives a textual list of the basket in user friendly way.
     * Ideal for testing, maybe not needed for production.
     *
     * @return Doesn't return anything, but does output strings to the display.
     */
    public void listItemsInBasket() {
        System.out.println(" Unit Price - Quantity - Name");
        for (Item item : itemsCounted.keySet()) {
            System.out.println("   £" + (item.getPrice()/100) + "          " + itemsCounted.get(item) + "        " + item.getName());
        }
    }


} // end Basket class
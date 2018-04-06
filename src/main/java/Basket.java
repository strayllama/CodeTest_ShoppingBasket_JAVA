import java.util.*;

public class Basket {
    private Customer customer;
    private Map<Item, Integer> itemsCounted;
    ArrayList<Item> basketList;


    public Basket(Customer customer) {
        this.customer = customer;
        itemsCounted = new HashMap<>();
        basketList = new ArrayList<>();

    }

    public String getCustomerName() {
        return customer.getName();
    }

    public ArrayList<Item> getCurrentBasketList() {
        basketList.clear();

        for (Map.Entry mappedEntry : itemsCounted.entrySet()) {
            //System.out.println("Item: "+ ((Item)mappedEntry.getKey()).getName() + ", Price: " + ((Item)mappedEntry.getKey()).getPrice() + ", Quantity: " + mappedEntry.getValue());
            basketList.add(((Item)mappedEntry.getKey()));
        }

        return this.basketList;
    }

    // Add item to hash map with value 1 if doesn't exist or increments its value if it already exists.
    public void addItem(Item item) {
        if (itemsCounted.get(item) != null ) {
            itemsCounted.put(item, itemsCounted.get(item) + 1);
        } else { itemsCounted.put(item, 1); }
    }

    public int getNumberOfItems() {
        int numberOfItems = 0;

        for (Map.Entry mappedEntry : itemsCounted.entrySet()) {
            numberOfItems += (int)mappedEntry.getValue();
        }

        return numberOfItems;
    }

    public void removeItem(Item item) {
        if (itemsCounted.get(item) > 1 ) {
            itemsCounted.put(item, itemsCounted.get(item) - 1);
        } else { itemsCounted.remove(item); }
    }


    public boolean hasLoyalityCard() {
        return customer.hasLoyaltyCard();
    }

    public float getTotalPrice() {
        float totalPrice = 0;

        // get ArrayList<Item> of current items by called getCurrentBasketList
        for (Item item : getCurrentBasketList() ) {

            if ( item.isBogof() ) {
                if (itemsCounted.get(item) > 1) { // price * (number of items/2) rounded up to account for a single item price when odd number)
                    int roundedUp = itemsCounted.get(item) / 2 + ((itemsCounted.get(item) % 2 == 0) ? 0 : 1);
                    totalPrice += roundedUp * item.getPrice();
                } else { // add price if only 1 item and its bogof
                    totalPrice += item.getPrice(); }
            } else { // add price if not bogof
                totalPrice += item.getPrice() * itemsCounted.get(item); } // price * quantity
        } // end for



        if (totalPrice > 2000) {
            totalPrice -= (totalPrice * 0.9);
        }

        if ( customer.hasLoyaltyCard() ) {
            totalPrice -= (totalPrice * 0.02);
        }

        return totalPrice;
    }


    // List basket in nice text view

    public void listItemsInBasket() {
        System.out.println(" Unit Price - Quantity - Name");
        for (Item item : getCurrentBasketList()) {
            System.out.println("   £" + (item.getPrice()/100) + "          " + itemsCounted.get(item) + "        " + item.getName());
        }
    }



} // end Basket class


//        add items to the shopping basket,
//        remove items from the shopping basket,
//        empty the shopping basket.
//        Additionally, you must be able to calculate the total value of the shopping basket. This should account for:
//
//        buy-one-get-one-free discounts on items,
//        10% off on totals greater than £20 (after previous discount is applied),
//        2% off on total for customers with loyalty cards (after all other discounts are applied).

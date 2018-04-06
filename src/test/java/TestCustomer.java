import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCustomer {

    private Customer customer1;
    private Customer customer2;


    @Before
    public void before() {
        customer1 = new Customer("Steve", true);
        customer2 = new Customer("Tracy", false);
    }

    @Test
    public void testCustomerHasName() {
        assertEquals("Steve", customer1.getName());
    }

    @Test
    public void testCustomerHasLoyaltyCard() {
        assertEquals(true, customer1.hasLoyaltyCard());
    }

    @Test
    public void testCustomerHasNOTLoyaltyCard() {
        assertEquals(false, customer2.hasLoyaltyCard());
    }

}

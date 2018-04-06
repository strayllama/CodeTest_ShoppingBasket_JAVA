import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestItem {
    private Item item;

    @Before
    public void before() {
        item = new Item ("4 Loo Roll", 4, true);
    }

    @Test
    public void testItemHasName() {
        assertEquals("4 Loo Roll", item.getName());
    }

    @Test
    public void testItemHasPrice() {
        assertEquals(4, item.getPrice());
    }

    @Test
    public void testItemIsBogof() {
        assertEquals(true, item.isBogof());
    }
}

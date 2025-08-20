import com.pahanedu.business.item.model.Item;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    @Test
    public void testItemId() {
        Item item = new Item();
        item.setId(1);
        assertEquals(1, item.getId());
    }

    @Test
    public void testItemName() {
        Item item = new Item();
        item.setName("Book A");
        assertEquals("Book A", item.getName());
    }

    @Test
    public void testItemPrice() {
        Item item = new Item();
        item.setPrice(49.99);
        assertEquals(49.99, item.getPrice());
    }

    @Test
    public void testItemQuantity() {
        Item item = new Item();
        item.setQuantity(100);
        assertEquals(100, item.getQuantity());
    }

    @Test
    public void testItemAvailability() {
        Item item = new Item();
        item.setAvailability("In Stock");
        assertEquals("In Stock", item.getAvailability());
    }

    @Test
    public void testItemConstructor() {
        Item item = new Item(1, "Book A", 49.99, 100, "In Stock");
        assertEquals(1, item.getId());
        assertEquals("Book A", item.getName());
        assertEquals(49.99, item.getPrice());
        assertEquals(100, item.getQuantity());
        assertEquals("In Stock", item.getAvailability());
    }
}

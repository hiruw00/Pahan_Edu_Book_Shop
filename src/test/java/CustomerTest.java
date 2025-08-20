import com.pahanedu.business.customer.model.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    @Test
    public void testCustomerAccountNumber() {
        Customer customer = new Customer();
        customer.setAccountNumber(1001);
        assertEquals(1001, customer.getAccountNumber());
    }

    @Test
    public void testCustomerName() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        assertEquals("John Doe", customer.getName());
    }

    @Test
    public void testCustomerAddress() {
        Customer customer = new Customer();
        customer.setAddress("123 Main St");
        assertEquals("123 Main St", customer.getAddress());
    }

    @Test
    public void testCustomerTelephone() {
        Customer customer = new Customer();
        customer.setTelephone("555-1234");
        assertEquals("555-1234", customer.getTelephone());
    }

    @Test
    public void testCustomerUnitsConsumed() {
        Customer customer = new Customer();
        customer.setUnitsConsumed(250);
        assertEquals(250, customer.getUnitsConsumed());
    }

    @Test
    public void testCustomerConstructor() {
        Customer customer = new Customer(1001, "John Doe", "123 Main St", "555-1234", 250);
        assertEquals(1001, customer.getAccountNumber());
        assertEquals("John Doe", customer.getName());
        assertEquals("123 Main St", customer.getAddress());
        assertEquals("555-1234", customer.getTelephone());
        assertEquals(250, customer.getUnitsConsumed());
    }
}



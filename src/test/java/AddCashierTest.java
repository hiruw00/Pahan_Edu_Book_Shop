import com.pahanedu.business.user.dto.UserDTO;
import com.pahanedu.business.user.model.Cashier;
import com.pahanedu.business.user.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddCashierTest {

    // Test Cashier model properties
    @Test
    public void testCashierProperties() {
        Cashier cashier = new Cashier(1, "john_c", "password123");

        assertEquals(1, cashier.getId());
        assertEquals("john_c", cashier.getUsername());
        assertEquals("password123", cashier.getPassword());
        assertEquals("cashier", cashier.getRole());
        assertEquals("/cashier/dashboard", cashier.getDashboardPath());
    }

    // Test UserDTO properties
    @Test
    public void testUserDTOProperties() {
        UserDTO dto = new UserDTO(2, "alice", "pass456", "cashier");

        assertEquals(2, dto.getId());
        assertEquals("alice", dto.getUsername());
        assertEquals("pass456", dto.getPassword());
        assertEquals("cashier", dto.getRole());
    }

    // Test creating a Cashier from DTO (simulate service layer)
    @Test
    public void testCreateCashierFromDTO() {
        UserDTO dto = new UserDTO(0, "bob", "bobpass", "cashier");

        // Simulate conversion from DTO to model
        Cashier cashier = new Cashier(dto.getId(), dto.getUsername(), dto.getPassword());

        assertEquals(dto.getUsername(), cashier.getUsername());
        assertEquals(dto.getPassword(), cashier.getPassword());
        assertEquals("cashier", cashier.getRole());
    }

    // Test User abstract class behavior
    @Test
    public void testUserAbstractMethods() {
        User cashier = new Cashier(5, "sam", "sam123");
        assertEquals("/cashier/dashboard", cashier.getDashboardPath());
    }
}


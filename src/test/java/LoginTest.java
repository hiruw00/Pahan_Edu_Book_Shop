import com.pahanedu.business.user.model.Admin;
import com.pahanedu.business.user.model.Cashier;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @Test
    public void testAdminUserId() {
        Admin admin = new Admin(1, "admin", "password123");
        assertEquals(1, admin.getId());
    }

    @Test
    public void testAdminUsername() {
        Admin admin = new Admin(1, "admin", "password123");
        assertEquals("admin", admin.getUsername());
    }

    @Test
    public void testAdminPassword() {
        Admin admin = new Admin(1, "admin", "password123");
        assertEquals("password123", admin.getPassword());
    }

    @Test
    public void testAdminRole() {
        Admin admin = new Admin(1, "admin", "password123");
        assertEquals("admin", admin.getRole());
    }

    @Test
    public void testAdminDashboardPath() {
        Admin admin = new Admin(1, "admin", "password123");
        assertEquals("/admin/dashboard", admin.getDashboardPath());
    }

    @Test
    public void testCashierUserId() {
        Cashier cashier = new Cashier(2, "cashier", "password123");
        assertEquals(2, cashier.getId());
    }

    @Test
    public void testCashierUsername() {
        Cashier cashier = new Cashier(2, "cashier", "password123");
        assertEquals("cashier", cashier.getUsername());
    }

    @Test
    public void testCashierPassword() {
        Cashier cashier = new Cashier(2, "cashier", "password123");
        assertEquals("password123", cashier.getPassword());
    }

    @Test
    public void testCashierRole() {
        Cashier cashier = new Cashier(2, "cashier", "password123");
        assertEquals("cashier", cashier.getRole());
    }

    @Test
    public void testCashierDashboardPath() {
        Cashier cashier = new Cashier(2, "cashier", "password123");
        assertEquals("/cashier/dashboard", cashier.getDashboardPath());
    }
}

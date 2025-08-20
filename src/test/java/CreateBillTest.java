import com.pahanedu.business.bill.dto.BillDTO;
import com.pahanedu.business.bill.dto.BillItemDTO;
import com.pahanedu.business.bill.model.Bill;
import com.pahanedu.business.bill.model.BillItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreateBillTest {

    @Test
    public void testBillModel() {
        Bill bill = new Bill();
        bill.setId(1);
        bill.setCustomerAccountNumber(12345);
        bill.setUserId(2);
        bill.setAmount(500.75);
        bill.setDate(new Date());
        bill.setEmail("customer@example.com");
        bill.setPaymentMethod("Cash");

        assertEquals(1, bill.getId());
        assertEquals(12345, bill.getCustomerAccountNumber());
        assertEquals(2, bill.getUserId());
        assertEquals(500.75, bill.getAmount());
        assertNotNull(bill.getDate());
        assertEquals("customer@example.com", bill.getEmail());
        assertEquals("Cash", bill.getPaymentMethod());
    }

    @Test
    public void testBillItemModel() {
        BillItem item = new BillItem();
        item.setId(1);
        item.setBillId(10);
        item.setItemId(101);
        item.setQuantity(2);
        item.setPrice(49.99);

        assertEquals(1, item.getId());
        assertEquals(10, item.getBillId());
        assertEquals(101, item.getItemId());
        assertEquals(2, item.getQuantity());
        assertEquals(49.99, item.getPrice());
    }

    @Test
    public void testBillDTOConstructor() {
        Date now = new Date();
        BillDTO billDTO = new BillDTO(1, 12345, 2, 500.75, now, "customer@example.com", "Card");

        assertEquals(1, billDTO.getId());
        assertEquals(12345, billDTO.getCustomerAccountNumber());
        assertEquals(2, billDTO.getUserId());
        assertEquals(500.75, billDTO.getAmount());
        assertEquals(now, billDTO.getDate());
        assertEquals("customer@example.com", billDTO.getEmail());
        assertEquals("Card", billDTO.getPaymentMethod());
    }

    @Test
    public void testBillItemDTOConstructor() {
        BillItemDTO itemDTO = new BillItemDTO(1, 10, 101, 3, 150.0);

        assertEquals(1, itemDTO.getId());
        assertEquals(10, itemDTO.getBillId());
        assertEquals(101, itemDTO.getItemId());
        assertEquals(3, itemDTO.getQuantity());
        assertEquals(150.0, itemDTO.getPrice());
    }

    @Test
    public void testBillItemListDTO() {
        List<BillItemDTO> itemDTOs = new ArrayList<>();
        itemDTOs.add(new BillItemDTO(1, 10, 101, 2, 50.0));
        itemDTOs.add(new BillItemDTO(2, 10, 102, 1, 100.0));

        assertEquals(2, itemDTOs.size());
        assertEquals(101, itemDTOs.get(0).getItemId());
        assertEquals(50.0, itemDTOs.get(0).getPrice());
        assertEquals(102, itemDTOs.get(1).getItemId());
        assertEquals(100.0, itemDTOs.get(1).getPrice());
    }
}

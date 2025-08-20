import com.pahanedu.business.bill.dto.BillItemDTO;
import com.pahanedu.business.bill.model.Bill;
import com.pahanedu.business.bill.model.BillItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ViewBillTest {

    @Test
    public void testBillAndItemsRetrieval() {
        // Simulate a Bill
        Bill bill = new Bill(1, 1001, 2, 450.0, new Date(), "customer@example.com", "Cash");

        assertEquals(1001, bill.getCustomerAccountNumber());
        assertEquals(2, bill.getUserId());
        assertEquals(450.0, bill.getAmount());
        assertEquals("customer@example.com", bill.getEmail());
        assertEquals("Cash", bill.getPaymentMethod());

        // Simulate Bill Items
        List<BillItem> items = new ArrayList<>();
      
BillItem item1 = new BillItem();
item1.setId(1);
item1.setBillId(1);
item1.setItemId(301);
item1.setQuantity(3);
item1.setPrice(150.0);

BillItem item2 = new BillItem();
item2.setId(2);
item2.setBillId(1);
item2.setItemId(302);
item2.setQuantity(2);
item2.setPrice(75.0);

items.add(item1);
items.add(item2);

        assertEquals(2, items.size());
        assertEquals(301, items.get(0).getItemId());
        assertEquals(3, items.get(0).getQuantity());
        assertEquals(150.0, items.get(0).getPrice());

        assertEquals(302, items.get(1).getItemId());
        assertEquals(2, items.get(1).getQuantity());
        assertEquals(75.0, items.get(1).getPrice());

        // Convert to DTOs
        List<BillItemDTO> itemDTOs = new ArrayList<>();
        for (BillItem item : items) {
            itemDTOs.add(new BillItemDTO(
                    item.getId(),
                    item.getBillId(),
                    item.getItemId(),
                    item.getQuantity(),
                    item.getPrice()
            ));
        }

        assertEquals(2, itemDTOs.size());
        assertEquals(301, itemDTOs.get(0).getItemId());
        assertEquals(3, itemDTOs.get(0).getQuantity());
        assertEquals(150.0, itemDTOs.get(0).getPrice());
    }
}

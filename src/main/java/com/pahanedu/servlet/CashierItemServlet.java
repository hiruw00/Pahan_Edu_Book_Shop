package com.pahanedu.servlet;

import com.pahanedu.business.item.dto.ItemDTO;
import com.pahanedu.business.item.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/cashier/items")
public class CashierItemServlet extends HttpServlet {

    private final ItemService itemService = new ItemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Fetch all items from the service
        List<ItemDTO> items = itemService.getAllItems();

        // Debug: print size to console
        System.out.println("CashierItemServlet - Items fetched: " + items.size());

        // Set the list in request scope
        request.setAttribute("items", items);

        // Forward to JSP
        request.getRequestDispatcher("/cashier/items.jsp").forward(request, response);
    }
}

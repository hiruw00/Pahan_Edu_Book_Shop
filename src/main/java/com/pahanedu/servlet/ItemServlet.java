package com.pahanedu.servlet;

import com.pahanedu.business.item.dto.ItemDTO;
import com.pahanedu.business.item.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {
        "/admin/addItem",
        "/admin/modifyItem",
        "/admin/removeItems"
})
public class ItemServlet extends HttpServlet {

    private final ItemService itemService = new ItemService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        switch (path) {
            case "/admin/addItem":
                addItem(request);
                break;
            case "/admin/modifyItem":
                modifyItem(request);
                break;
            case "/admin/removeItems":
                removeItems(request);
                break;
        }

        response.sendRedirect("items.jsp");
    }

    private void addItem(HttpServletRequest request) {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String availability = request.getParameter("availability");

        ItemDTO dto = new ItemDTO(0, name, price, quantity, availability);
        itemService.addItem(dto);
    }

    private void modifyItem(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String availability = request.getParameter("availability");

        ItemDTO dto = new ItemDTO(id, name, price, quantity, availability);
        itemService.updateItem(dto);
    }

    private void removeItems(HttpServletRequest request) {
        String[] ids = request.getParameterValues("itemIds");
        if (ids != null) {
            List<Integer> itemIds = new ArrayList<>();
            for (String idStr : ids) {
                itemIds.add(Integer.parseInt(idStr));
            }
            itemService.removeItems(itemIds);
        }
    }
}

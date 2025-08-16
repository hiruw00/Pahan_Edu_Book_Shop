package com.pahanedu.servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pahanedu.dao.ItemDAO;


@WebServlet("/admin/removeItems")
public class RemoveItemsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] ids = request.getParameterValues("itemIds");

        if (ids != null && ids.length > 0) {
            List<Integer> itemIds = new ArrayList<>();
            for (String idStr : ids) {
                itemIds.add(Integer.parseInt(idStr));
            }

            ItemDAO dao = new ItemDAO();
            dao.removeItems(itemIds); // ✅ Use the correct DAO method
        }

        response.sendRedirect("items.jsp");
    }
}

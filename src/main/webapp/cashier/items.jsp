<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanedu.business.item.dto.ItemDTO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cashier - Items | Pahana Edu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body class="dashboard-body">
<div class="dashboard-wrapper">

    <!-- Top Border Header -->
    <div class="top-header">
        <div class="top-left">
            <i class="fas fa-box"></i>
            <strong style="font-size: 20px;">Pahana Edu Bookshop - Cashier</strong>
        </div>
        <div class="top-right">
            <form action="${pageContext.request.contextPath}/logout" method="post" style="display:inline;">
                <button type="submit" class="logout-btn">Logout</button>
            </form>
        </div>
    </div>

    <!-- Navigation Bar -->
    <div class="navbar">
        <div class="nav-left">
            <div class="nav-links">
                <a href="<%= request.getContextPath() %>/cashier/dashboard.jsp">Dashboard</a>
                <a href="${pageContext.request.contextPath}/customers">Customers</a>
                <a href="${pageContext.request.contextPath}/cashier/items">Items</a>
                <a href="create_bill.jsp">Create Bill</a>
                <a href="<%= request.getContextPath() %>/view_bills">View Bills</a>
                <a href="<%= request.getContextPath() %>/cashier/help.jsp">Help</a>
            </div>
        </div>
    </div>

    <!-- Items Content -->
    <main class="dashboard-container">
        <h2>Available Items</h2>
<div class="cashier-items-wrapper">
    <table class="cashier-items-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Item Name</th>
                <th>Price (Rs.)</th>
                <th>Quantity</th>
                <th>Availability</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<ItemDTO> items = (List<ItemDTO>) request.getAttribute("items");
                if (items != null && !items.isEmpty()) {
                    for (ItemDTO item : items) {
            %>
            <tr>
                <td><%= item.getId() %></td>
                <td><%= item.getName() %></td>
                <td><%= item.getPrice() %></td>
                <td><%= item.getQuantity() %></td>
                <td><%= item.getAvailability() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5" style="text-align:center;">No items available</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>

    </main>
</div>
</body>
</html>

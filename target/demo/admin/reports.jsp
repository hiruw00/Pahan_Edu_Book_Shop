<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanedu.business.bill.model.Bill" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sales Reports - Pahan Edu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body class="dashboard-body">
<div class="dashboard-wrapper">

    <!-- Top Header -->
    <div class="top-header">
        <div class="top-left">
            <i class="fas fa-chart-line"></i>
            <strong style="font-size: 20px;">Pahana Edu Bookshop - Admin</strong>
        </div>
        <div class="top-right">
            <form action="<%=request.getContextPath()%>/logout" method="post" style="display:inline;">
                <button type="submit" class="logout-btn">Logout</button>
            </form>
        </div>
    </div>

    <!-- Navigation Bar -->
    <div class="navbar">
        <div class="nav-left">
            <div class="nav-links">
                <a href="dashboard.jsp">Dashboard</a>
                <a href="<%=request.getContextPath()%>/addCashier">Cashiers</a>
                <a href="<%=request.getContextPath()%>/admin/customers">Customers</a>
                <a href="items.jsp">Items</a>
                <a href="<%=request.getContextPath()%>/admin/create_bill.jsp">Create Bill</a>
                <a href="<%=request.getContextPath()%>/view_all_bills">Billing</a>
                <a href="<%=request.getContextPath()%>/admin/reports">Reports</a>
                <a href="help.jsp">Help</a>
            </div>
        </div>
    </div>

    <!-- Reports Content -->
    <div class="content-section">
        <h2> Sales Reports</h2>

        <!-- Cards for quick stats -->
        <%
            List<Bill> salesReports = (List<Bill>) request.getAttribute("salesReports");
            double totalRevenue = 0;
            int totalBills = (salesReports != null) ? salesReports.size() : 0;
            if(salesReports != null) {
                for(Bill b : salesReports) totalRevenue += b.getAmount();
            }
        %>
        <div class="report-cards">
            <div class="report-card">
                <h3>Total Bills</h3>
                <p><%= totalBills %></p>
            </div>
            <div class="report-card">
                <h3>Total Revenue</h3>
                <p>Rs. <%= totalRevenue %></p>
            </div>
        </div>

        <!-- Table -->
        <div class="report-table-wrapper">
            <table class="report-table">
                <thead>
                    <tr>
                        <th>Bill ID</th>
                        <th>Customer Account</th>
                        <th>Total Amount</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (salesReports != null && !salesReports.isEmpty()) {
                            for (Bill report : salesReports) {
                    %>
                        <tr>
                            <td><%= report.getId() %></td>
                            <td><%= report.getCustomerAccountNumber() %></td>
                            <td>Rs. <%= report.getAmount() %></td>
                            <td><%= report.getDate() %></td>
                        </tr>
                    <%
                            }
                        } else {
                    %>
                        <tr>
                            <td colspan="4" style="text-align:center;">No sales reports available.</td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>

</div>
</body>
</html>

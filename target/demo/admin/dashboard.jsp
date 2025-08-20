<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard - Pahana Edu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body class="dashboard-body">
    <div class="dashboard-wrapper">

        <div class="top-header">
            <div class="top-left">
                  <i class="fas fa-tachometer-alt"></i>
                <strong style="font-size: 20px;">Pahana Edu Bookshop - Admin</strong>
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
                <a href="dashboard.jsp">Dashboard</a>
                <a href="${pageContext.request.contextPath}/addCashier">Cashiers</a>
                <a href="${pageContext.request.contextPath}/admin/customers">Customers</a>
                <a href="items.jsp">Items</a>
                <a href="${pageContext.request.contextPath}/admin/create_bill.jsp">Create Bill</a>
                <a href="${pageContext.request.contextPath}/view_all_bills">Billing</a>
                <a href="<%=request.getContextPath()%>/admin/reports">Reports</a>
                <a href="help.jsp">Help</a>
                </div>
            </div>
        </div>

        <!-- Dashboard Content -->
           <!-- Dashboard Content -->
    <main class="dashboard-container">
        <div class="welcome">Welcome, Admin!</div>
        <h3>Quick Access</h3>
        <div class="quick-access">

            <div class="card">
                <i class="fas fa-box-open fa-3x card-icon"></i>
                <h4>Manage Items</h4>
                <p>View, add, edit, or remove item records.</p>
                <a href="items.jsp" class="card-btn">Go to Items <i class="fas fa-arrow-right"></i></a>
            </div>

            <div class="card">
                <i class="fas fa-file-invoice fa-3x card-icon"></i>
                <h4>Billing Section</h4>
                <p>Start new billing process and view invoices.</p>
                <a href="${pageContext.request.contextPath}/view_all_bills" class="card-btn">Go to Billing <i class="fas fa-arrow-right"></i></a>
            </div>

            <div class="card">
                <i class="fas fa-users-cog fa-3x card-icon"></i>
                <h4>Manage Cashiers</h4>
                <p>Add or edit cashier accounts and roles.</p>
                <a href="${pageContext.request.contextPath}/addCashier" class="card-btn">Go to Cashiers <i class="fas fa-arrow-right"></i></a>
            </div>

        </div>
    </main>
        <!-- Footer -->
        <footer>
            &copy; 2025 Pahana Edu Book Shop | Admin Panel
        </footer>
    </div>
</body>

</html>

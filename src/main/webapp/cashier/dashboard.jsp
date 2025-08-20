<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cashier Dashboard - Pahana Edu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body class="dashboard-body">
    <div class="dashboard-wrapper">
        <!-- Top Border Header -->
        <div class="top-header">
            <div class="top-left">
                  <i class="fas fa-tachometer-alt"></i>
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
                <a href="${pageContext.request.contextPath}/cashier/items.jsp">Items</a>
                <a href="create_bill.jsp">Create Bill</a>
                <a href="<%= request.getContextPath() %>/view_bills">View Bills</a>
                <a href="<%= request.getContextPath() %>/cashier/help.jsp">Help</a>
                </div>
            </div>
        </div>

      <!-- Dashboard Content -->
    <main class="dashboard-container">
        <div class="welcome">Welcome, Cashier!</div>
        <h3>Quick Access</h3>
        <div class="quick-access">

            <div class="card">
                <i class="fas fa-file-invoice fa-3x card-icon"></i>
                <h4>Create New Bill</h4>
                <p>Start the billing process for a customer.</p>
                <a href="create_bill.jsp" class="card-btn">Go to Billing <i class="fas fa-arrow-right"></i></a>
            </div>

            <div class="card">
                <i class="fas fa-user fa-3x card-icon"></i>
                <h4>Manage Customers</h4>
                <p>Search, view, or update customer information.</p>
                <a href="customers.jsp" class="card-btn">Go to Customers <i class="fas fa-arrow-right"></i></a>
            </div>

            <div class="card">
                <i class="fas fa-box-open fa-3x card-icon"></i>
                <h4>View Items</h4>
                <p>Check item availability and details.</p>
                <a href="${pageContext.request.contextPath}/customers" class="card-btn">Go to Items <i class="fas fa-arrow-right"></i></a>
            </div>

            <div class="card">
                <i class="fas fa-file-alt fa-3x card-icon"></i>
                <h4>View Bills</h4>
                <p>See all created bills and print invoices.</p>
                <a href="<%= request.getContextPath() %>/view_bills" class="card-btn">Go to Bills <i class="fas fa-arrow-right"></i></a>
            </div>

        </div>
    </main>

        <!-- Footer -->
        <footer>
            &copy; 2025 Pahana Edu Book Shop | Cashier Panel
        </footer>
    </div>
</body>
</html>

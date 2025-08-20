<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanedu.business.user.dto.UserDTO" %>
<%@ page import="com.pahanedu.business.user.model.User" %>
<%@ page import="com.pahanedu.business.user.model.Cashier" %>


<!DOCTYPE html>
<html>
<head>
    <title>Manage Cashiers - Admin</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
     
</head>
<body class="dashboard-body">
<div class="dashboard-wrapper">
    <div class="top-header">
        <div class="top-left">
            <i class="fas fa-user"></i>
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

    <div class="content-area">
        <h2>Manage Cashiers</h2>
        <%
            String status = request.getParameter("status");
            if ("success".equals(status)) {
        %>
        <p>✅ Cashier added successfully!</p>
        <%
            } else if ("error".equals(status)) {
        %>
        <p>❌ Failed to add cashier.</p>
        <%
            } else if ("deleted".equals(status)) {
        %>
        <p>✅ Selected cashier(s) deleted successfully!</p>
        <%
            }
        %>

        <!-- Add Cashier Form -->
        <div class="center-wrapper">
            <div class="small-card">
                <form method="post" action="<%= request.getContextPath() %>/addCashier">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>

                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>

                    <button type="submit" class="submit-btn">Add Cashier</button>
                </form>
            </div>
        </div>

        <!-- View Cashiers Button -->
        <div style="text-align: center; margin-top: 20px;">
            <button onclick="document.getElementById('cashierModal').style.display='block'" class="submit-btn">View Cashiers</button>
        </div>

        <!-- Modal for Cashiers Table -->
        <div id="cashierModal" class="modal" style="display:none; position:fixed; top:10%; left:50%; transform:translateX(-50%); width:90%; max-width:800px; background:#fff; border:1px solid #ccc; box-shadow:0 0 10px rgba(0,0,0,0.3); z-index:9999; padding:20px;">
            <div style="text-align:right;">
                <button onclick="document.getElementById('cashierModal').style.display='none'" style="border:none; background:none; font-size:20px; cursor:pointer;">&times;</button>
            </div>
            <form method="post" action="<%= request.getContextPath() %>/addCashier">
                <table class="table-cashiers">
                    <thead>
                        <tr>
                            <th>Select</th>
                            <th>ID</th>
                            <th>Username</th>
                            <th>Password</th>
                        </tr>
                    </thead>
                    <tbody>
                   <%
                    // Use UserDTO instead of User
                    List<UserDTO> cashiers = (List<UserDTO>) request.getAttribute("cashiers");
                    if (cashiers != null && !cashiers.isEmpty()) {
                        for (UserDTO cashier : cashiers) {
                   %>
                  <tr>
                   <td><input type="checkbox" name="deleteIds" value="<%= cashier.getId() %>"></td>
                   <td><%= cashier.getId() %></td>
                   <td><%= cashier.getUsername() %></td>
                   <td><%= cashier.getPassword() != null ? cashier.getPassword() : "********" %></td>
                  </tr>
                  <%
                        }
                    } else {
                  %>
                  <tr>
                     <td colspan="4">No cashiers found.</td>
                  </tr>
                  <%
                    }
                  %>
                    </tbody>
                </table>
                <div style="text-align:right;">
                    <button type="submit" class="delete-btn">Delete Selected</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Optional: Close modal on outside click -->
<script>
    window.onclick = function(event) {
        const modal = document.getElementById('cashierModal');
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
</script>
</body>
</html>

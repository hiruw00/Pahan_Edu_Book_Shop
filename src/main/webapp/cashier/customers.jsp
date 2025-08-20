<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanedu.business.customer.model.Customer" %>
<%@ page import="com.pahanedu.dao.CustomerDAO" %>

<!DOCTYPE html>
<html>
<head>
    <title>Cashier Dashboard - Pahana Edu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body class="dashboard-body">
<div class="dashboard-wrapper">

    <!-- Top Header -->
    <div class="top-header">
        <div class="top-left">
            <i class="fas fa-tachometer-alt"></i>
            <strong>Pahana Edu Bookshop - Cashier</strong>
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
                <a href="<%= request.getContextPath() %>/cashier/create_bill.jsp">Create Bill</a>
                <a href="<%= request.getContextPath() %>/view_bills">View Bills</a>
                <a href="<%= request.getContextPath() %>/cashier/help.jsp">Help</a>
            </div>
        </div>
    </div>

    <!-- Page Content -->
    <main class="dashboard-container">
        <h2>Customer Management</h2>

        <button class="primary-btn" id="addCustomerBtn">Add New Customer</button>

        <!-- Add Customer Popup Form -->
        <div id="customerFormPopup" class="popup-form">
            <div class="popup-content">
                <span class="close-btn" id="closePopup">&times;</span>
                <h3>Add New Customer</h3>
                <form action="${pageContext.request.contextPath}/addCustomer" method="post">
                    <label>Name:</label>
                    <input type="text" name="name" required>
                    <label>Account Number:</label>
                    <input type="text" name="accountNumber" required>
                    <label>Address:</label>
                    <input type="text" name="address" required>
                    <label>Telephone:</label>
                    <input type="text" name="telephone" required>
                    <button type="submit" class="submit-btn">Add</button>
                </form>
            </div>
        </div>

        <!-- Edit Customer Popup Form -->
        <div id="editCustomerPopup" class="popup-form">
            <div class="popup-content">
                <span class="close-btn" id="closeEditPopup">&times;</span>
                <h3>Edit Customer</h3>
                <form id="editCustomerForm" action="${pageContext.request.contextPath}/editcustomer" method="post">
                    <input type="hidden" name="originalAccountNumber" id="editOriginalAccountNumber">
                    <label>Name:</label>
                    <input type="text" name="name" id="editName" required>
                    <label>Account Number:</label>
                    <input type="number" name="accountNumber" id="editAccountNumber" required>
                    <label>Address:</label>
                    <input type="text" name="address" id="editAddress" required>
                    <label>Telephone:</label>
                    <input type="text" name="telephone" id="editTelephone" required>
                    <button type="submit" class="submit-btn">Update</button>
                </form>
            </div>
        </div>

        <!-- Customer Table -->
        <div class="customer-table-wrapper">
            <table class="customer-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Account Number</th>
                        <th>Address</th>
                        <th>Telephone</th>
                        <th>Units Consumed</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
                    if (customers != null && !customers.isEmpty()) {
                        for (Customer customer : customers) {
                %>
                    <tr>
                        <td><%= customer.getName() %></td>
                        <td><%= customer.getAccountNumber() %></td>
                        <td><%= customer.getAddress() %></td>
                        <td><%= customer.getTelephone() %></td>
                        <td><%= customer.getUnitsConsumed() %></td>
                        <td>
                            <a href="#" class="btn edit" 
                                onclick="openEditPopup('<%= customer.getName() %>', 
                                                       '<%= customer.getAccountNumber() %>', 
                                                       '<%= customer.getAddress() %>', 
                                                       '<%= customer.getTelephone() %>')">Edit</a>
                        </td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr><td colspan="6" style="text-align:center;">No customers found</td></tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </main>

</div>

<script>
    // Add Customer Popup
    const popup = document.getElementById("customerFormPopup");
    const openBtn = document.getElementById("addCustomerBtn");
    const closeBtn = document.getElementById("closePopup");
    openBtn.onclick = () => { popup.style.display = "flex"; };
    closeBtn.onclick = () => { popup.style.display = "none"; };
    window.onclick = (event) => { if (event.target === popup) popup.style.display = "none"; };

    // Edit Customer Popup
    const editPopup = document.getElementById("editCustomerPopup");
    const closeEditBtn = document.getElementById("closeEditPopup");
    function openEditPopup(name, accountNumber, address, telephone) {
        document.getElementById("editName").value = name;
        document.getElementById("editAccountNumber").value = accountNumber;
        document.getElementById("editOriginalAccountNumber").value = accountNumber;
        document.getElementById("editAddress").value = address;
        document.getElementById("editTelephone").value = telephone;
        editPopup.style.display = "flex";
    }
    closeEditBtn.onclick = () => { editPopup.style.display = "none"; };
    window.onclick = (event) => { if (event.target === editPopup) editPopup.style.display = "none"; };
</script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.pahanedu.business.customer.model.Customer" %>
<%
    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
    if (customers == null) {
        customers = new ArrayList<>();
    }

    // Detect success messages from query params
    String success = request.getParameter("success");
    String message = "";
    if ("add".equals(success)) {
        message = "Customer added successfully!";
    } else if ("edit".equals(success)) {
        message = "Customer updated successfully!";
    } else if ("delete".equals(success)) {
        message = "Selected customers deleted successfully!";
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard - Pahan Edu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body class="dashboard-body">
    <div class="dashboard-wrapper">

        <!-- Top Header -->
        <div class="top-header">
            <div class="top-left">
                <i class="fas fa-users"></i>
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

        <!-- Search bar -->
        <div class="search-container">
            <input type="text" id="searchInput" placeholder="Search by Account Number" onkeyup="filterCards()">
        </div>

        <!-- Success Message -->
        <%
            if (!message.isEmpty()) {
        %>
            <div class="success-message" style="margin: 15px 15px; color: green; font-weight: bold;">
                <%= message %>
            </div>
        <%
            }
        %>

        <!-- Wrap Add/Delete and checkboxes inside a single form -->
        <form id="deleteForm" action="${pageContext.request.contextPath}/admin/customers/delete" method="post" style="margin: 15px 0 15px 15px;">
            <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 15px;">
                <button type="button" class="primary-btn" id="addCustomerBtn">Add New Customer</button>
                <button type="submit" class="danger-btn">Delete Selected</button>
            </div>

            <!-- Customer Cards Grid -->
            <div class="card-container">
                <% for (Customer customer : customers) { %>
                    <div class="customer-card" data-account="<%= customer.getAccountNumber() %>">
                        <!-- Checkbox for Bulk Delete -->
                        <input type="checkbox" name="accountNumbers" value="<%= customer.getAccountNumber() %>">
                        <p><strong>Account No:</strong> <%= customer.getAccountNumber() %></p>
                        <p><strong>Name:</strong> <%= customer.getName() %></p>
                        <button type="button" onclick="openEditPopup(
                            '<%= customer.getAccountNumber() %>',
                            '<%= customer.getName() %>',
                            '<%= customer.getAddress() %>',
                            '<%= customer.getTelephone() %>',
                            '<%= customer.getUnitsConsumed() %>'
                        )">View / Edit</button>
                    </div>
                <% } %>
            </div>
        </form> <!-- Bulk Delete Form END -->

        <!-- Popup Form for Adding/Editing Customer -->
        <div id="customerFormPopup" class="popup-form">
            <div class="popup-content">
                <span class="close-btn" id="closePopup">&times;</span>
                <h3 id="formTitle">Add New Customer</h3>
                <form id="customerForm" action="" method="post">
                    <input type="hidden" name="originalAccountNumber" id="originalAccountNumber">

                    <label>Account Number:</label>
                    <input type="text" name="accountNumber" id="formAccountNumber" required>

                    <label>Name:</label>
                    <input type="text" name="name" id="formName" required>

                    <label>Address:</label>
                    <input type="text" name="address" id="formAddress" required>

                    <label>Telephone:</label>
                    <input type="text" name="telephone" id="formTelephone" required>

                    <button type="submit" class="submit-btn">Save</button>
                </form>
            </div>
        </div>

        <!-- View Popup Modal -->
        <div id="viewPopup" class="popup-overlay" style="display:none;">
            <div class="popup-content">
                <h3>Customer Details</h3>
                <p><strong>Account Number:</strong> <span id="viewAccount"></span></p>
                <p><strong>Name:</strong> <span id="viewName"></span></p>
                <p><strong>Address:</strong> <span id="viewAddress"></span></p>
                <p><strong>Telephone:</strong> <span id="viewTelephone"></span></p>
                <p><strong>Units Consumed:</strong> <span id="viewUnits"></span></p>
                <button onclick="closePopup()">Close</button>
            </div>
        </div>
    </div>

    <script>
        // Existing "View" popup
        function openPopup(account, name, address, telephone, units) {
            document.getElementById('viewAccount').textContent = account;
            document.getElementById('viewName').textContent = name;
            document.getElementById('viewAddress').textContent = address;
            document.getElementById('viewTelephone').textContent = telephone;
            document.getElementById('viewUnits').textContent = units;
            document.getElementById('viewPopup').style.display = 'block';
        }

        function closePopup() {
            document.getElementById('viewPopup').style.display = 'none';
        }

        function filterCards() {
            const input = document.getElementById('searchInput').value.toLowerCase();
            const cards = document.getElementsByClassName('customer-card');
            Array.from(cards).forEach(card => {
                const account = card.getAttribute('data-account').toLowerCase();
                card.style.display = account.includes(input) ? 'block' : 'none';
            });
        }

        // Add/Edit Customer Popup Scripts
        const popup = document.getElementById("customerFormPopup");
        const openBtn = document.getElementById("addCustomerBtn");
        const closeBtn = document.getElementById("closePopup");
        const customerForm = document.getElementById("customerForm");

        openBtn.onclick = () => {
            document.getElementById("formTitle").innerText = "Add New Customer";
            customerForm.action = "${pageContext.request.contextPath}/admin/customers/add";

            document.getElementById("originalAccountNumber").value = "";
            document.getElementById("formAccountNumber").value = "";
            document.getElementById("formName").value = "";
            document.getElementById("formAddress").value = "";
            document.getElementById("formTelephone").value = "";
            popup.style.display = "flex";
        };

        closeBtn.onclick = () => { popup.style.display = "none"; };
        window.onclick = (event) => {
            if (event.target === popup) {
                popup.style.display = "none";
            }
        };

        function openEditPopup(account, name, address, telephone) {
            document.getElementById("formTitle").innerText = "Edit Customer";
            customerForm.action = "${pageContext.request.contextPath}/admin/customers/edit";

            document.getElementById("originalAccountNumber").value = account;
            document.getElementById("formAccountNumber").value = account;
            document.getElementById("formName").value = name;
            document.getElementById("formAddress").value = address;
            document.getElementById("formTelephone").value = telephone;

            popup.style.display = "flex";
        }
    </script>
</body>
</html>
        
    </script>
</body>
</html>

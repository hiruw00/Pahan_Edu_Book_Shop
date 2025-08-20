<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanedu.dao.CustomerDAO" %>
<%@ page import="com.pahanedu.dao.ItemDAO" %>
<%@ page import="com.pahanedu.business.customer.model.Customer" %>
<%@ page import="com.pahanedu.business.item.model.Item" %>
<%
    CustomerDAO customerDAO = new CustomerDAO();
    List<Customer> customers = customerDAO.getAllCustomers();

    ItemDAO itemDAO = new ItemDAO();
    List<Item> items = itemDAO.getAllItems();

    final int userId = 2; // cashier
%>
<%
    String success = request.getParameter("success");
    String error = request.getParameter("error");
%>

<% if ("true".equals(success)) { %>
    <div class="success-message">✅ Bill created successfully!</div>
<% } else if ("true".equals(error)) { %>
    <div class="error-message">❌ Failed to create bill. Please try again.</div>
<% } %>

<!DOCTYPE html>
<html>
<head>
    <title>Create Bill - Cashier | Pahana Edu</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="../css/style.css">
    <script>
        function updateItemPrice(row) {
            let itemSelect = row.querySelector(".item-select");
            let priceInput = row.querySelector(".price");
            let quantityInput = row.querySelector(".quantity");

            let selectedOption = itemSelect.options[itemSelect.selectedIndex];
            let price = selectedOption.getAttribute("data-price");

            priceInput.value = price;
            calculateTotal();
        }

        function calculateTotal() {
            let itemCost = 0;
            let totalUnits = 0;

            document.querySelectorAll(".item-row").forEach(row => {
                let quantity = parseInt(row.querySelector(".quantity").value || "0");
                let price = parseFloat(row.querySelector(".price").value || "0");
                totalUnits += quantity; // Calculate total units
                itemCost += quantity * price;
            });

            document.getElementById("units_consumed").value = totalUnits; // Display total units
            document.getElementById("amount").value = itemCost;
        }

        function addItemRow() {
            let table = document.getElementById("items-table");
            let newRow = document.createElement("tr");
            newRow.className = "item-row";
            newRow.innerHTML = document.getElementById("template-row").innerHTML;
            table.appendChild(newRow);
        }
    </script>
</head>
<body class="dashboard-body">
    <div class="dashboard-wrapper">

        <!-- Top Border Header -->
        <div class="top-header">
            <div class="top-left">
                <i class="fas fa-file-invoice"></i>
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
                <a href="<%= request.getContextPath() %>/cashier/create_bill.jsp">Create Bill</a>
                <a href="<%= request.getContextPath() %>/view_bills">View Bills</a>
                <a href="<%= request.getContextPath() %>/cashier/help.jsp">Help</a>
                </div>
            </div>
        </div>

        <!-- Create Bill Form -->
        <div class="content">
            <h2>Create Bill</h2>

            <div class="form-card">
              <form action="${pageContext.request.contextPath}/create_bill" method="post">
                <label>Customer Account Number:</label>
                <select name="customer_account_number" id="customer_account_number" required>
                    <option disabled selected>Select Customer</option>
                    <% for (Customer customer : customers) { %>
                        <option value="<%= customer.getAccountNumber() %>">
                            <%= customer.getAccountNumber() %> - <%= customer.getName() %>
                        </option>
                    <% } %>
                </select>

                <label>Email:</label>
                <input type="email" name="email" placeholder="Enter customer email" required />

                <label>Payment Method:</label>
                <select name="payment_method" required>
                <option disabled selected>Select Payment Method</option>
                <option value="Cash">Cash</option>
                <option value="Card">Card</option>
                <option value="Online">Online</option>
                </select>

                <label>Cashier (User ID):</label>
                <input type="number" name="user_id" value="<%= userId %>" readonly />

                <h3>Items</h3>
                <table id="items-table">
                    <tr class="item-row">
                        <td>
                            <select name="item_ids[]" class="item-select" onchange="updateItemPrice(this.parentNode.parentNode)" required>
                                <option disabled selected>Select Item</option>
                                <% for (Item item : items) { %>
                                    <option value="<%= item.getId() %>" data-price="<%= item.getPrice() %>">
                                        <%= item.getName() %>
                                    </option>
                                <% } %>
                            </select>
                        </td>
                        <td><input type="number" name="quantities[]" class="quantity" value="1" onchange="calculateTotal()" required /></td>
                       <td><input type="text" name="prices[]" class="price" readonly /></td>
                       <td><button type="button" class="delete-row-btn" onclick="this.closest('tr').remove(); calculateTotal();">
                       <i class="fas fa-times"></i>
                       </button></td>

                    </tr>
                </table>
                <button type="button" onclick="addItemRow()">Add Item</button>

                <label>Total Units Consumed:</label>
                <input type="text" name="units_consumed" id="units_consumed" readonly /> <!-- Ensure this field is displayed -->

                <label>Total Amount:</label>
                <input type="text" name="amount" id="amount" readonly />

                <label>Date:</label>
                <input type="date" name="date" required />

                <button type="submit">Create Bill</button>
            </form>
        </div>
    </div>

    <!-- Hidden Template Row -->
    <template id="template-row">
        <tr class="item-row">
            <td>
                <select name="item_ids[]" class="item-select" onchange="updateItemPrice(this.parentNode.parentNode)">
                    <option disabled selected>Select Item</option>
                    <% for (Item item : items) { %>
                        <option value="<%= item.getId() %>" data-price="<%= item.getPrice() %>">
                            <%= item.getName() %>
                        </option>
                    <% } %>
                </select>
            </td>
            <td><input type="number" name="quantities[]" class="quantity" value="1" onchange="calculateTotal()" /></td>
            <td><input type="text" name="prices[]" class="price" readonly /></td>
        </tr>
    </template>
</body>
</html>
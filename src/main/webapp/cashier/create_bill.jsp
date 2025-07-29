<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanedu.dao.CustomerDAO" %>
<%@ page import="com.pahanedu.dao.ItemDAO" %>
<%@ page import="com.pahanedu.model.Customer" %>
<%@ page import="com.pahanedu.model.Item" %>
<%
    CustomerDAO customerDAO = new CustomerDAO();
    List<Customer> customers = customerDAO.getAllCustomers();

    ItemDAO itemDAO = new ItemDAO();
    List<Item> items = itemDAO.getAllItems();

    final int userId = 2; // cashier
    final int fixedUnitPrice = 10;
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
    <title>Create Bill - Cashier | Pahan Edu</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="../css/style.css">
    <script>
        let fixedUnitPrice = parseInt("<%= fixedUnitPrice %>");

        function updateCustomerDetails() {
            let select = document.getElementById("customer_account_number");
            let selected = select.options[select.selectedIndex];
            let units = selected.getAttribute("data-units");
            document.getElementById("units_consumed").value = units;

            calculateTotal();
        }

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
            let units = parseInt(document.getElementById("units_consumed").value || "0");
            let unitCost = units * fixedUnitPrice;
            let itemCost = 0;

            document.querySelectorAll(".item-row").forEach(row => {
                let quantity = parseInt(row.querySelector(".quantity").value || "0");
                let price = parseFloat(row.querySelector(".price").value || "0");
                itemCost += quantity * price;
            });

            document.getElementById("amount").value = unitCost + itemCost;
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
                <i class="fas fa-chart-line"></i>
                <strong style="font-size: 20px;">Pahan Edu Bookshop - Cashier</strong>
            </div>
            <div class="top-right">
                <form action="../logout" method="post" style="display:inline;">
                    <button type="submit" class="logout-btn">Logout</button>
                </form>
            </div>
        </div>

        <!-- Navigation Bar -->
        <div class="navbar">
            <div class="nav-left">
                <div class="nav-links">
                    <a href="dashboard.jsp">Dashboard</a>
                    <a href="customers.jsp">Customers</a>
                    <a href="${pageContext.request.contextPath}/create_bill">Create Bill</a>
                    <a href="view_bills.jsp">View Bills</a>
                    <a href="help.jsp">Help</a>
                </div>
            </div>
        </div>

        <!-- Create Bill Form -->
        <div class="content">
            <h2>Create Bill</h2>

            <div class="form-card">
              <form action="${pageContext.request.contextPath}/create_bill" method="post">
                <label>Customer Account Number:</label>
                <select name="customer_account_number" id="customer_account_number" onchange="updateCustomerDetails()" required>
                    <option disabled selected>Select Customer</option>
                    <% for (Customer customer : customers) { %>
                        <option value="<%= customer.getAccountNumber() %>" data-units="<%= customer.getUnitsConsumed() %>">
                            <%= customer.getAccountNumber() %> - <%= customer.getName() %>
                        </option>
                    <% } %>
                </select>

                <label>Units Consumed:</label>
                <input type="number" name="units_consumed" id="units_consumed" readonly />

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
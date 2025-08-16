<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanedu.model.Item" %>
<%@ page import="com.pahanedu.dao.ItemDAO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Items - Pahan Edu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="../css/style.css">
</head>
<body class="dashboard-body">
    <div class="dashboard-wrapper">

        <!-- TOP HEADER + NAV -->
        <div class="top-header">
            <div class="top-left">
                <i class="fas fa-chart-line"></i>
                <strong style="font-size: 20px;">Pahan Edu Bookshop - Admin</strong>
            </div>
            <div class="top-right">
                <form action="${pageContext.request.contextPath}/logout" method="post" style="display:inline;">
                    <button type="submit" class="logout-btn">Logout</button>
                </form>
            </div>
        </div>

       <div class="navbar">
            <div class="nav-left">
                 <div class="nav-links">
                <a href="dashboard.jsp">Dashboard</a>
                <a href="${pageContext.request.contextPath}/admin/customers">Customers</a>
                <a href="items.jsp">Items</a>
                <a href="${pageContext.request.contextPath}/admin/view_all_bills.jsp">Billing</a>
                <a href="help.jsp">Help</a>
                <a href="reports.jsp">Reports</a>
                </div>
            </div>
        </div>

        <!-- CONTENT AREA -->
        <div class="dashboard-container">
            <h2 class="section-title">Manage Items</h2>

            <!-- Add New Item Button -->
            <button class="primary-btn" onclick="openAddModal()">Add New Item</button>

            <!-- Delete Selected Button -->
            <button class="btn delete" onclick="deleteSelected()">Delete Selected</button>

            <!-- Items Table -->
            <form id="deleteForm" method="post" action="${pageContext.request.contextPath}/admin/removeItems">
                <table class="customer-table">
                    <thead>
                        <tr>
                            <th><input type="checkbox" id="selectAll" onclick="toggleAll(this)"/></th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Availability</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ItemDAO itemDAO = new ItemDAO();
                            List<Item> items = itemDAO.getAllItems();
                            for (Item item : items) {
                        %>
                        <tr>
                            <td><input type="checkbox" name="itemIds" value="<%= item.getId() %>"/></td>
                            <td><%= item.getId() %></td>
                            <td><%= item.getName() %></td>
                            <td><%= item.getPrice() %></td>
                            <td><%= item.getQuantity() %></td>
                            <td>
                                <%= item.getAvailability().equals("instock") ? "In Stock" : "Out of Stock" %>
                            </td>
                            <td>
                                <a href="#" class="btn edit" onclick="openEditModal
                                     ('<%= item.getId() %>',
                                     '<%= item.getName() %>',
                                      '<%= item.getPrice() %>',
                                      '<%= item.getQuantity() %>',
                                     '<%= item.getAvailability() %>')">Edit</a>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <!-- ADD ITEM MODAL -->
    <div class="popup-form" id="addItemModal">
        <div class="popup-content">
            <span class="close-btn" onclick="closeAddModal()">&times;</span>
            <h3>Add New Item</h3>
            <form action="${pageContext.request.contextPath}/admin/addItem" method="post">
                <label>Name:</label>
                <input type="text" name="name" required>
                <label>Price:</label>
                <input type="number" name="price" step="0.01" required>
                <label>Quantity:</label>
                <input type="number" name="quantity" required>
                <label>Availability:</label>
                <select name="availability" required>
                    <option value="instock">In Stock</option>
                    <option value="outofstock">Out of Stock</option>
                </select>
                <button class="submit-btn" type="submit">Add</button>
            </form>
        </div>
    </div>

    <!-- EDIT ITEM MODAL -->
    <div class="popup-form" id="editItemModal">
        <div class="popup-content">
            <span class="close-btn" onclick="closeEditModal()">&times;</span>
            <h3>Edit Item</h3>
            <form action="${pageContext.request.contextPath}/admin/modifyItem" method="post">
                <input type="hidden" name="id" id="editId">
                <label>Name:</label>
                <input type="text" name="name" id="editName" required>
                <label>Price:</label>
                <input type="number" name="price" id="editPrice" step="0.01" required>
                <label>Quantity:</label>
                <input type="number" name="quantity" id="editQuantity" required>
                <label>Availability:</label>
                <select name="availability" id="editAvailability" required>
                    <option value="instock">In Stock</option>
                    <option value="outofstock">Out of Stock</option>
                </select>
                <button class="submit-btn" type="submit">Update</button>
            </form>
        </div>
    </div>

    <!-- SCRIPTS -->
    <script>
        function toggleAll(source) {
            const checkboxes = document.getElementsByName('itemIds');
            for (let cb of checkboxes) {
                cb.checked = source.checked;
            }
        }

        function deleteSelected() {
            if (confirm('Are you sure you want to delete selected items?')) {
                document.getElementById('deleteForm').submit();
            }
        }

        function openAddModal() {
            document.getElementById('addItemModal').style.display = 'flex';
        }

        function closeAddModal() {
            document.getElementById('addItemModal').style.display = 'none';
        }

        function openEditModal(id, name, price, quantity, availability) {
            document.getElementById('editId').value = id;
            document.getElementById('editName').value = name;
            document.getElementById('editPrice').value = price;
            document.getElementById('editQuantity').value = quantity;
            document.getElementById('editAvailability').value = availability;
            document.getElementById('editItemModal').style.display = 'flex';
        }

        function closeEditModal() {
            document.getElementById('editItemModal').style.display = 'none';
        }
    </script>
</body>
</html>
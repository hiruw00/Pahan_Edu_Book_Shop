<%@ page import="com.pahanedu.model.Bill" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard - All Bills</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="dashboard-body">
<div class="dashboard-wrapper">
        <%
    Object showBillObj = request.getAttribute("showBillId");
    Integer showBillId = null;

    if (showBillObj instanceof Integer) {
        showBillId = (Integer) showBillObj;
    }

    List<Bill> bills = (List<Bill>) request.getAttribute("bills");

    if (showBillId != null) {
%>
    <input type="hidden" id="autoShowBillId" value="<%= showBillId %>" />
<%
    }
%>
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
                <a href="${pageContext.request.contextPath}/view_all_bills">Billing</a>
                <a href="help.jsp">Help</a>
                <a href="reports.jsp">Reports</a>
            </div>
        </div>
    </div>

   <!-- Main Content -->
    <div class="main-content">
        <h2 style="margin-bottom: 20px;">Created Bills</h2>
        <div class="card-container">
            <%
                if (bills != null && !bills.isEmpty()) {
                    for (Bill bill : bills) {
            %>
                <div class="bill-card">
                    <h3>Bill ID: <%= bill.getId() %></h3>
                    <p>Account #: <%= bill.getCustomerAccountNumber() %></p>
                    <p>Amount: Rs. <%= bill.getAmount() %></p>
                  <button <%= "onclick=\"showBillModal(" + bill.getId() + ")\"" %> class="view-btn">View</button>

                </div>
            <%
                    }
                } else {
            %>
                <p>No bills available.</p>
            <%
                }
            %>
        </div>
    </div>
</div>

<!-- Modal for Viewing Bill -->
<div id="billModal" class="modal" style="display:none;">
    <div class="modal-content" id="modalContent">
        <span class="close-btn" onclick="closeModal()">×</span>
        <div id="billDetails"></div>
        <button onclick="window.print()" class="print-btn">🖨️ Print</button>
    </div>
</div>

<!-- JavaScript for auto-popup -->
<script>
    function showBillModal(billId) {
        fetch('GetBillDetailsServlet?billId=' + billId)
            .then(res => res.text())
            .then(data => {
                document.getElementById("billDetails").innerHTML = data;
                document.getElementById("billModal").style.display = "block";
            })
            .catch(err => {
                alert("Error loading bill details.");
                console.error(err);
            });
    }

    function closeModal() {
        document.getElementById("billModal").style.display = "none";
    }

    window.onload = function() {
        let billInput = document.getElementById("autoShowBillId");
        if (billInput && billInput.value) {
            showBillModal(billInput.value);
        }
    };
</script>
</body>
</html>

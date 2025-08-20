<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Help - Pahan Edu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body class="dashboard-body">
<div class="dashboard-wrapper">

    <!-- Top Header -->
    <div class="top-header">
        <div class="top-left">
            <!-- Updated icon to a help icon -->
            <i class="fas fa-question-circle"></i>
            <strong style="font-size: 20px;">Pahana Edu Bookshop - Admin Help</strong>
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
                <a href="${pageContext.request.contextPath}/admin/view_all_bills.jsp">Billing</a>
                <a href="<%=request.getContextPath()%>/admin/reports">Reports</a>
                <a href="help.jsp" class="active">Help</a>
            </div>
        </div>
    </div>

    <!-- Help Section -->
    <div class="faq-section">
        <h2 class="faq-heading">Frequently Asked Questions (FAQ)</h2>

        <!-- Search Bar -->
        <div class="search-container">
            <input type="text" id="faqSearchInput" placeholder="Search FAQ..." onkeyup="filterFAQs()">
        </div>

        <!-- FAQ List -->
        <div id="faqList">

            <div class="faq-item">
                <button class="accordion">
                    Why can't I edit customer details?
                    <i class="fas fa-chevron-down"></i>
                </button>
                <div class="panel">
                    <p>This may happen if the customer data is being updated simultaneously by another user. Try reloading the page or ensuring you're editing from the correct role.</p>
                </div>
            </div>

            <div class="faq-item">
                <button class="accordion">
                    How do I add a new item to the system?
                    <i class="fas fa-chevron-down"></i>
                </button>
                <div class="panel">
                    <p>Go to the 'Items' section and click the 'Add New Item' button. Fill out the form and click submit to store the item in the database.</p>
                </div>
            </div>

            <div class="faq-item">
                <button class="accordion">
                    What does 'Out of Stock' mean in item management?
                    <i class="fas fa-chevron-down"></i>
                </button>
                <div class="panel">
                    <p>'Out of Stock' indicates that the quantity of the item is 0 or marked unavailable. You can edit the item to restock it.</p>
                </div>
            </div>

            <div class="faq-item">
                <button class="accordion">
                    Can I delete multiple customers at once?
                    <i class="fas fa-chevron-down"></i>
                </button>
                <div class="panel">
                    <p>Currently, customer deletion must be done individually to prevent accidental data loss. Use the delete icon in each customer's card.</p>
                </div>
            </div>

            <div class="faq-item">
                <button class="accordion">
                    How do I view system reports?
                    <i class="fas fa-chevron-down"></i>
                </button>
                <div class="panel">
                    <p>Navigate to the 'Reports' page from the navbar. You can filter and download sales, billing, and inventory reports there.</p>
                </div>
            </div>

        </div>
    </div>

</div>

<script>
    // Accordion Toggle with smooth animation
    const acc = document.getElementsByClassName("accordion");
    for (let i = 0; i < acc.length; i++) {
        acc[i].addEventListener("click", function () {
            this.classList.toggle("active");
            const panel = this.nextElementSibling;
            if (panel.style.maxHeight && panel.style.maxHeight !== "0px") {
                panel.style.maxHeight = "0px";
            } else {
                panel.style.maxHeight = panel.scrollHeight + "px";
            }
        });
    }

    // FAQ Filter Function
    function filterFAQs() {
        const input = document.getElementById("faqSearchInput");
        const filter = input.value.toLowerCase();
        const buttons = document.getElementsByClassName("accordion");

        for (let i = 0; i < buttons.length; i++) {
            const txtValue = buttons[i].textContent || buttons[i].innerText;
            const panel = buttons[i].nextElementSibling;
            if (txtValue.toLowerCase().includes(filter)) {
                buttons[i].style.display = "";
                panel.style.maxHeight = "0px"; // collapse initially
                buttons[i].classList.remove("active");
            } else {
                buttons[i].style.display = "none";
                panel.style.maxHeight = "0px";
            }
        }
    }
</script>

</body>
</html>

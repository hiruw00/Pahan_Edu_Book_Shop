<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cashier Help - Pahan Edu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                <a href="create_bill.jsp">Create Bill</a>
                <a href="<%= request.getContextPath() %>/view_bills">View Bills</a>
                <a href="<%= request.getContextPath() %>/cashier/help.jsp">Help</a>
            </div>
        </div>
    </div>

    <!-- Help Section -->
    <div class="faq-section">
        <h2 class="faq-heading">Frequently Asked Questions (FAQ)</h2>

        <!-- Search Bar -->
       <div class="search-container">
         <input type="text" id="faqSearchInput" placeholder=" Search FAQ..." onkeyup="filterFAQs()">
       </div>

        <!-- FAQ List -->
        <div id="faqList">
            <div class="faq-item">
                <button class="accordion">❓ Why can't I see the latest bill after creating it?</button>
                <div class="panel" style="max-height: 0;">

                    <p>After submitting a bill, the system auto-redirects you to 'View Bills' and opens the latest bill in a popup. If that doesn't happen, try refreshing or check your browser settings.</p>
                </div>
            </div>

            <div class="faq-item">
                <button class="accordion">❓ What if customer details are not showing?</button>
                <div class="panel" style="max-height: 0;">
                    <p>This may occur if the customer was recently deleted or due to temporary DB connection issues. Try refreshing or contact an admin.</p>
                </div>
            </div>

            <div class="faq-item">
                <button class="accordion">❓ How do I print a bill?</button>
             <div class="panel" style="max-height: 0;">
                    <p>Click on the "View" button of a bill card, then press the 🖨️ Print button inside the modal to print it.</p>
                </div>
            </div>

            <div class="faq-item">
                <button class="accordion">❓ I added a wrong item to the bill. What do I do?</button>
                <div class="panel" style="max-height: 0;">
                    <p>If the bill is not submitted yet, use the ❌ delete icon next to the item. If already created, contact your supervisor to void it.</p>
                </div>
            </div>
        </div>
    </div>

</div>

<script>
    // Accordion Toggle
    const acc = document.getElementsByClassName("accordion");
    for (let i = 0; i < acc.length; i++) {
        acc[i].addEventListener("click", function () {
            this.classList.toggle("active");
            const panel = this.nextElementSibling;
            panel.style.display = (panel.style.display === "block") ? "none" : "block";
        });
    }
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
                panel.style.display = "none"; // Collapse on search
                buttons[i].classList.remove("active");
            } else {
                buttons[i].style.display = "none";
                panel.style.display = "none";
            }
        }
    }
</script>

</body>
</html>

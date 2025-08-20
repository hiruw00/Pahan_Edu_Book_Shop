<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Pahan Edu Book Shop</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="login-card">
    <h1>Welcome to Pahana Edu Book Shop</h1>
    <h2>Login</h2>

    <form action="login" method="post">
        <input type="text" name="username" placeholder="Username" required />
        <input type="password" name="password" placeholder="Password" required />
        <button type="submit">Login</button>
    </form>

    <% String error = request.getParameter("error");
       if (error != null) { %>
       <div class="error"><%= error %></div>
    <% } %>

    <div class="credentials">
        <strong> Logins:</strong><br>
        Admin → <code>admin</code> / <code>admin123</code><br>
     
    </div>
</div>

</body>
</html>

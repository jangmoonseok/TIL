<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome Page</title>
<%
String username = "";
Cookie[] cookies = request.getCookies();
if (cookies != null) {
    for (Cookie cookie : cookies) {
        if ("user".equals(cookie.getName())) {
            username = cookie.getValue();
            // Use the username as needed
        }
    }
}
%>
</head>
<body>
    <h2>Welcome Page</h2>
    <p>Welcome, <%= username %>!</p>
</body>
</html>
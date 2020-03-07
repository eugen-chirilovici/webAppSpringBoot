<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <style type="text/css">
        <%@include file="../resources/style.css"%>
    </style>
</head>
<body>
<h1>${message}</h1>
<div align="center">
    <div style="width: 400px; height: 800px;">
        <form:form method="POST" action="regist">
            <div class="container">
                <h1>Register</h1>
                <p>Please fill in this form to create an account.</p>
                <hr>

                <label>First name</label>
                <input type="text" placeholder="First name" name="firstName" required>

                <label>Last name</label>
                <input type="text" placeholder="Last name" name="lastName" required>

                <label>User name</label>
                <input type="text" placeholder="User name" name="username" required>

                <label>Password</label>
                <input type="password" placeholder="Enter Password" name="password" required>

                    <%--<label>Repeat Password</label>--%>
                    <%--<input type="password" placeholder="Repeat Password" name="psw-repeat" required>--%>
                    <%--<hr>--%>

                <button type="submit" class="registerbtn">Register</button>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>

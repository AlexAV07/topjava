<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>User list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meals list</h2>
<table>
    <tr><td>ID</td>
        <td>Дата, время</td>
        <td>Вид</td>
        <td>Калорий</td>
    </tr>
    <c:forEach items="${MealList}" var="meal">
        <tr>
            <td>${meal.getMealId()}</td>
            <td>${meal.getDate()} ${meal.getTime()}</td>
            <td>${meal.getDescription()}</td>
            <td>${meal.getCalories()}</td>
            <td>${meal.isExceed()}</td>
            <td><a href="meals?action=edit&userId=">Update</a></td>
        </tr>
    </c:forEach>
</table>
<li><a href="meals?action=new">New Meal</a></li>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <title>Добавление новой еды</title>
</head>
<body>
<form method="POST" action="meals" name="mealForm">
    Вид еды <input type="text" name="mealName" value="${meal.getDescription}">
    Калорий <input type="text" name="calories" value="${meal.getCalories()}">
    <input type="submit" value="Submit" />
</form>
</body>
</html>
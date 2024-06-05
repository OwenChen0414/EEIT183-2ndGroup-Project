<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新增道具</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/create.css">
</head>
<body>
    <h2 class="center">新增道具</h2>
    <form method="post" action="../CreateProp" class="center" enctype="multipart/form-data">
        <label for="SelectGame">遊戲:</label>
        <select id="SelectGame" name="gameId" required>
            <option value=""></option>
            <c:forEach items="${games}" var="game">
                <option value="${game.id}">${game.name}</option>
            </c:forEach>
        </select>

        <label for="propName">道具名稱:</label>
        <input type="text" name="propName" id="propName" required>

        <label for="propType">種類:</label>
        <input type="text" name="propType" id="propType" required>

        <label for="propRarity">稀有度:</label>
        <input type="text" name="propRarity" id="propRarity" required>

        <p>
            <label for="propDescription">描述:</label>
            <textarea name="propDescription" id="propDescription" rows="2" cols="30" required></textarea>
        </p>

        <label for="propImagePath">上傳圖片:</label>
        <input type="file" name="propImagePath" id="propImagePath" required>

        <input type="submit" value="確定新增">
    </form>
</body>
</html>
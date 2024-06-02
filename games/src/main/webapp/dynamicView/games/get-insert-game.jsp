<%@ page trimDirectiveWhitespaces="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainStyle.css?v=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/games/get-insert-game.css?v=1.0">
    <title>新增遊戲</title>
</head>

<body>
    <header>
        <nav>
            <img src="${pageContext.request.contextPath}/images/logo3.png" alt="" id="logo1">
            <span id="navText">Game Fighter</span>
            <ul class="menu1">
                <li>會員中心<i class="fa-solid fa-caret-down"></i>
                    <ul class="menu2">
                        <li>遊戲發表會</li>
                        <li>功能介紹</li>
                        <li>你在哪哩拉哈哈</li>
                    </ul>
                </li>
                <li>遊戲商店<i class="fa-solid fa-caret-down"></i>
                    <ul class="menu2">
                        <li>遊戲發表會</li>
                        <li>功能介紹</li>
                        <li>你在哪哩拉哈哈</li>
                    </ul>
                </li>
                <li>遊戲物品交易<i class="fa-solid fa-caret-down"></i>
                    <ul class="menu2">
                        <li>遊戲發表會</li>
                        <li>功能介紹</li>
                        <li>你在哪哩拉哈哈</li>
                    </ul>
                </li>
                <li>討論區<i class="fa-solid fa-caret-down"></i>
                    <ul class="menu2">
                        <li>遊戲發表會</li>
                        <li>功能介紹</li>
                        <li>你在哪哩拉哈哈</li>
                    </ul>
                </li>
                <li>尋找玩伴<i class="fa-solid fa-caret-down"></i>
                    <ul class="menu2">
                        <li>遊戲發表會</li>
                        <li>功能介紹</li>
                        <li>你在哪哩拉哈哈</li>
                    </ul>
                </li>
                <li>活動<i class="fa-solid fa-caret-down"></i>
                    <ul class="menu2">
                        <li>遊戲發表會</li>
                        <li>功能介紹</li>
                        <li>你在哪哩拉哈哈</li>
                    </ul>
                </li>
            </ul>
        </nav>
    </header>
    <main>
        <article>
            <section class="games">
                <form action="${pageContext.request.contextPath}/InsertGame" method="post" enctype="multipart/form-data">
                    <h2 class="title2">遊戲名稱：
                        <input class="text" type="text" name="gameName" required maxlength="50">
                    </h2>
                    <h2 class="title2">價格　　：
                        <input class="text" type="text" name="price" required maxlength="5">
                    </h2>
                    <h2 class="title2">遊戲敘述：
                        <textarea class="text" name="description" required></textarea>
                    </h2>
                    <h2 class="title2">開發者　：
                        <input class="text" type="text" name="developer" required maxlength="50">
                    </h2>
                    <h2 class="title2">發行商　：
                        <input class="text" type="text" name="publisher" required maxlength="50">
                    </h2>
                    <h2 class="title2">簡介　　：
                        <textarea class="text" name="introduction" required maxlength="300"></textarea>
                    </h2>
                    <h2 class="title2">分類　　：
                        <select class="text" name="categoryName" multiple required>
                    <c:forEach var="category" items="${categorys}">
                            <option value="${category}">${category}</option>
                    </c:forEach>
                        </select>
                    </h2>
                    <h2 class="title2">圖片　　：
                        <input class="text" type="file" name="photo" required>
                    </h2>
                    <input type="submit" value="新增" class="btn">
                </form>
            </section>
        </article>
    </main>

    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.js?v=1.0"></script>
    <script src="${pageContext.request.contextPath}/js/mouse.js?v=1.0"></script>
</body>

</html>
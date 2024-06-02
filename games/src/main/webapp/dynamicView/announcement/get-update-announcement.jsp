<%@ page trimDirectiveWhitespaces="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainStyle.css?v=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/announcement/get-update-announcement.css?v=1.0">
    <title>${announcement.title}</title>
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
            <section id="announcement">
            <form method="post" action="${pageContext.request.contextPath}/UpdateAnnouncement">
                <h1 id="title1">標題：<input id="text" type="text" name="title" value="${announcement.title}" /></h1>
                <textarea name="content" id="container">${announcement.content}</textarea>
                <input type="hidden" name="createDate" value="${announcement.createDate}">
                <input type="hidden" name="lastEditTime" value="${announcement.lastEditTime}">
                <input type="hidden" name="id" value="${announcement.announcementId}">
                <h2 class="title2">分類：
                        <select name="categoryId" id="">
                        <c:forEach var="category" items="${categorys}">
                            <option <c:if test="${announcement.categoryId == category.categoryId}">selected</c:if> value="${category.categoryId}">${category.categoryName}</option>
                        </c:forEach>
                        </select>
                    </h2>
                <br><br>
                <input id="btn" type="submit" value="修改" />
                </form>
            </section>
        </article>
    </main>

    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.js?v=1.0"></script>
    <script src="${pageContext.request.contextPath}/js/mouse.js?v=1.0"></script>

</html>
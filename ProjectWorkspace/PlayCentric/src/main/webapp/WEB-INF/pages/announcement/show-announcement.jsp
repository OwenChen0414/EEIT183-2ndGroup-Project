<%@ page trimDirectiveWhitespaces="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainStyle.css?v=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/announcement/show-announcement.css?v=1.0">
    <title>${announcement.title}</title>
</head>

<body>
    <header>
        <nav>
            <img src="${pageContext.request.contextPath}/images/logo3.png" alt="" id="logo1">
            <span id="navText">Game Fighter</span>
            <ul class="menu1">
                <c:if test="${empty Member}">
                <li>會員中心<i class="fa-solid fa-caret-down"></i>
                    <ul class="menu2">
                        <li>會員登入</li>
                        <li>功能介紹</li>
                        <li>你在哪哩拉哈哈</li>
                    </ul>
                </li>
            </c:if>
            <c:if test="${not empty Member}">
                <li>${loginMember.memName}<i class="fa-solid fa-caret-down"></i>
                    <ul class="menu2">
                        <li>個人資料</li>
                        <li>購物車</li>
                        <li>遊戲庫</li>
                    </ul>
                </li>
            </c:if>
                <li>遊戲商店<i class="fa-solid fa-caret-down"></i>
                    <ul class="menu2">
                        <li>遊戲總覽</li>
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
                <h1 id="title1">[${announcement.announcementCategory.categoryName}]${announcement.title}</h1>
                <div id="container">
                    <p>${announcement.content}</p>
                <p id="lastEditTime">最近編輯時間：${announcement.lastEditTime}</p>
                </div>
            </section>
        </article>
    </main>

    <script src="${pageContext.request.contextPath}/javascript/jquery-3.7.1.min.js?v=1.0"></script>
    <script src="${pageContext.request.contextPath}/javascript/games/mouse.js?v=1.0"></script>

</html>
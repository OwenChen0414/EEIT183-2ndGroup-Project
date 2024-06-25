<%@ page trimDirectiveWhitespaces="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainStyle.css?v=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homepage.css?v=1.0">
    <title>首頁</title>
</head>

<body>
    <header>
        <nav>
            <img src="${pageContext.request.contextPath}/images/logo3.png" alt="" id="logo1">
            <span id="navText">Game Fighter</span> 
            <ul class="menu1">
                <c:if test="${empty loginMember}">
                <li>會員中心<i class="fa-solid fa-caret-down"></i>
                    <ul class="menu2">
                        <li><a href="${pageContext.request.contextPath}/view/members/login.html">會員登入</a></li>
                        <li>功能介紹</li>
                        <li>你在哪哩拉哈哈</li>
                    </ul>
                </li>
            </c:if>
            <c:if test="${not empty loginMember}">
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
                        <li><a href="${pageContext.request.contextPath}/GameStore" target="_blank">遊戲總覽</a></li>
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
                <li>後台管理<i class="fa-solid fa-caret-down"></i>
                    <ul class="menu2">
                        <li><a href="${pageContext.request.contextPath}/backAnnouncement.con" target="_blank">公告管理</a></li>
                        <li><a href="${pageContext.request.contextPath}/BackGames" target="_blank">遊戲管理</a></li>
                        <li>你在哪哩拉哈哈</li>
                    </ul>
                </li>
            </ul>
        </nav>
    </header>
    <main>
        <article>
            <section id="announcement">
                <h1 id="title1">公告</h1>
                    <table id="content">
                        <c:forEach var="anno" items="${announcements}">
                        <c:set var="key" value="${anno.categoryId}" />
                        <tr style="line-height: 1.5;">
                            <td><a href="${pageContext.request.contextPath}/showAnnouncement.con?id=${anno.announcementId}" style="text-decoration: none;">[${anno.announcementCategory.categoryName}]${anno.title}</a></td>
                            <td style="text-align:right;">${anno.createDate}<td>
                        </tr>
                        </c:forEach>
                    </table>
            </section>
        </article>
    </main>

    <script src="${pageContext.request.contextPath}/javascript/jquery-3.7.1.min.js?v=1.0"></script>
    <script src="${pageContext.request.contextPath}/javascript/games/mouse.js?v=1.0"></script>
</body>

</html>
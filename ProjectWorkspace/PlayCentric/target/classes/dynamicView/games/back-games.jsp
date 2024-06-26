<%@ page trimDirectiveWhitespaces="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainStyle.css?v=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/games/back-games.css?v=1.0">
    <style>

    </style>
    <title>遊戲商店管理</title>
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
    <aside id="contentTag">
        <ul id="leftList">
            <li><a href="${pageContext.request.contextPath}/BackAnnouncement">公告管理</a></li>
            <li>會員管理</li>
            <li>討論區文章管理</li>
            <li>討論區留言管理</li>
            <li id="selected"><a href="${pageContext.request.contextPath}/BackGame">遊戲商店管理</a></li>
            <li>遊戲物品交易管理</li>
            <li>尋找玩伴管理</li>
            <li>活動管理</li>
        </ul>
    </aside>
    <main>
        <article>
            <section id="games">
                <div id="content">
                    <table>
                        <c:forEach var="game" items="${games}">
                            <tr>
                                <td>
                                    <a href="${pageContext.request.contextPath}/ShowGame?id=${game.gameId}">${game.gameName}</a>
                                </td>
                                <td>
                                </td>
                                <c:if test="${game.onMarketTime != null}">
                        		<td class="right">
                                    <a href="${pageContext.request.contextPath}/OnMarket?id=${game.gameId}">下架</a>
                                </td>
                       			</c:if>
                       			<c:if test="${game.onMarketTime == null}">
                        		<td class="right">
                                    <a href="${pageContext.request.contextPath}/OnMarket?id=${game.gameId}">上架</a>
                                </td>
                       			</c:if>
                                
                                
                                <td class="right">
                                    <a href="${pageContext.request.contextPath}/GetUpdateGame?id=${game.gameId}">修改</a>
                                </td>
                                <td class="right">
                                    <a href="${pageContext.request.contextPath}/DeleteGame?id=${game.gameId}">刪除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <a href="${pageContext.request.contextPath}/GetInsertGame" id="plus"><i class="fa-solid fa-plus"></i></a>
            </section>
        </article>
    </main>

    <script src="${pageContext.request.contextPath}/javascript/jquery-3.7.1.min.js?v=1.0"></script>
    <script src="${pageContext.request.contextPath}/javascript/games/mouse.js?v=1.0"></script>
</body>

</html>
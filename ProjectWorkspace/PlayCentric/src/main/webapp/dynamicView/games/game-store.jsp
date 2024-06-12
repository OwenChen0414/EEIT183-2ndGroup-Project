<%@page import="com.ispan.dao.games.GamesDAO"%>
<%@ page trimDirectiveWhitespaces="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainStyle.css?v=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/games/game-store.css?v=1.0">
    <style>

    </style>
    <title>遊戲商店</title>
</head>

<body>
    <header>
        <nav>
            <img src="${pageContext.request.contextPath}/images/logo3.png" alt="" id="logo1">
            <span id="navText">Game Fighter</span>
            <ul class="menu1">
                <c:if test="${empty member}">
                <li>會員中心<i class="fa-solid fa-caret-down"></i>
                    <ul class="menu2">
                        <li><a href="${pageContext.request.contextPath}/view/members/login.html">會員登入</a></li>
                        <li>功能介紹</li>
                        <li>你在哪哩拉哈哈</li>
                    </ul>
                </li>
            </c:if>
            <c:if test="${not empty member}">
                <li>${loginMember.memName}<i class="fa-solid fa-caret-down"></i>
                    <ul class="menu2">
                        <li><a href="${pageContext.request.contextPath}/ShowCart">購物車</a></li>
                        <li>遊戲庫</li>
                        <li>個人資料</li>
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
                        <li><a href="${pageContext.request.contextPath}/BackAnnouncement">公告管理</a></li>
                        <li>功能介紹</li>
                        <li>你在哪哩拉哈哈</li>
                    </ul>
                </li>
            </ul>
        </nav>
    </header>

    <main>
        <aside class="leftMenu">
            <h2>遊戲分類</h2>
            <ul>
            	<li><a href="${pageContext.request.contextPath}/GameStore">全部</a></li>
            	<li><a href="">優惠中</a></li>
                 <c:forEach var="category" items="${allCategory}">
                 <c:set var="myCategory" value="${category}" scope="request" />
                 <% GamesDAO gamesDAO = new GamesDAO();
                 	int categoryId = gamesDAO.getCategoryId((String)request.getAttribute("myCategory"));
                 	request.setAttribute("categoryId", categoryId);
                 %>
                <li><a href="${pageContext.request.contextPath}/GameStore?categoryId=${categoryId}">${category}</a></li>
                </c:forEach>
            </ul>
        </aside>
        <article class="content">
            <section<c:if test="${empty games}"> style="text-align:center;"</c:if>>
                <h2>遊戲列表</h2>
                <c:if test="${empty games}"><span>沒有找到任何遊戲！</span></c:if>
                <table class="gameList">
                <c:forEach var="game" items="${games}">
                    <tr>
                        <td class="td1">
                        <c:if test="${game.photoPath == null}">
                        <a href="${pageContext.request.contextPath}/ShowGame?id=${game.gameId}">
                        <img class="gamePhoto" >
                        </a>
                        </c:if>
                        <c:if test="${game.photoPath != null}">
                        <a href="${pageContext.request.contextPath}/ShowGame?id=${game.gameId}">
                        <img class="gamePhoto" src="/${game.photoPath}" alt="">
                        </a>
                        </c:if>
                        </td>
                        <td class="td2">
                            <a style="color:white;" href="${pageContext.request.contextPath}/ShowGame?id=${game.gameId}">
                            ${game.gameName}</a><br>
                <c:forEach var="category" items="${game.categoryNames}">
                            <span class="categorys">${category}</span>
                </c:forEach>
                            <br><br>${game.onMarketTime}<br><br>評論
                        </td>
                        <td class="lastTD">
                            <br><br><br>
                            <span class="space1"></span>
                            <c:if test="${game.discountRate == 0}">
                            <span class="priceTop" style="background-color: aquamarine;"></span>
                            </c:if>
                            <c:if test="${game.discountRate != 0}">
                            <span class="priceTop" style="text-align:right;">NT$${game.price}</span>
                            </c:if>
                            <span class="space2"></span><br>
                            <c:if test="${game.discountRate == 0}"></c:if>
                            <c:if test="${game.discountRate != 0}">
                            <c:set var="disPer" value="${100 - game.discountRate * 100}" scope="request" />
                            <% 
                            double disPerd = (double)request.getAttribute("disPer"); 
                            int disperInt = (int) disPerd;
                            request.setAttribute("disPer", disperInt);
                            %>
                            <span class="discount">
                            -${disPer}%
                            </c:if>
                            </span>
                            <c:if test="${game.discountRate == 0}">
                             <span class="price" style="color:white;">
                            NT$${game.price}
                            </span>
                            </c:if>
                             <c:if test="${game.discountRate != 0}">
                             <c:set var="price" value="${game.price * game.discountRate}" scope="request" />
                             <% 
                             double priceD = (double)request.getAttribute("price");
                             int priceInt = (int)priceD;
                             request.setAttribute("priceInt", priceInt);
                             %>
                             <span class="price">
                             NT$${priceInt}
                            </span>
                             </c:if>
	                        <c:if test="${empty loginMember}">
	                        <a href="${pageContext.request.contextPath}/view/members/login.html">
	                        <span class="btn">加入購物車</span>
	                        </a>
	                        </c:if>
	                        <c:if test="${not empty loginMember}">
	                        <c:set var="isInCart" value="false"/>
	                        <c:forEach var="cart" items="${inCart}">
	                        <c:if test="${cart.gameId == game.gameId}">
	                        <c:set var="isInCart" value="true"/>
                            </c:if>
                            </c:forEach>
                            <c:if test="${isInCart == false}">
	                        <a href="${pageContext.request.contextPath}/InsertCart?id=${game.gameId}">
	                        <span class="btn">加入購物車</span>
	                        </a>
                            </c:if>
                            <c:if test="${isInCart == true}">
                            <span class="btn">已加入購物車</span>
	                        </a>
                            </c:if>
	                        </c:if>
                            <c:if test="${false}">
                            <span class="btn">下載</span>
                            </c:if>
                        </td>
                    </tr>
                    <tr class="spacing"></tr>
                    </c:forEach>
                </table>
            </section>
        </article>
    </main>
    <a href="#"><i class="fa-solid fa-arrow-up top"></i></a>


    <script src="${pageContext.request.contextPath}/javascript/jquery-3.7.1.min.js?v=1.0"></script>
    <script src="${pageContext.request.contextPath}/javascript/games/mouse.js?v=1.0"></script>
</body>

</html>
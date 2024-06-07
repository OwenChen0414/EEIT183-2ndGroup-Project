<%@ page trimDirectiveWhitespaces="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainStyle.css?v=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/buy/showCarts.css?v=1.0">
    <title>購物車</title>
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
                        <li><a href="${pageContext.request.contextPath}/BackAnnouncement" target="_blank">公告管理</a></li>
                        <li><a href="${pageContext.request.contextPath}/BackGames" target="_blank">遊戲管理</a></li>
                        <li>你在哪哩拉哈哈</li>
                    </ul>
                </li>
            </ul>
        </nav>
    </header>
    <main>
        <article>
            <section>
                <table>
                    <caption>${memName}的購物車</caption>
                    <tbody>
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
                           	 <a href="${pageContext.request.contextPath}/ShowGame?id=${game.gameId}">
                                <p style="text-align: left;font-size: 24px;">${game.gameName}</p>
                             </a>   
                                <br><br><br><br>
                                <span class="space1"></span>
                                <span class="priceTop"></span>
                                <span class="space2"></span>
                                <br>
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
                            </td>
                        </tr>
                        <tr class="spacing"></tr>
                     </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td></td>
                            <td>
                                <a href="" class="btn">結帳</a>
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </section>
        </article>
    </main>
        <script src="${pageContext.request.contextPath}/javascript/jquery-3.7.1.min.js?v=1.0"></script>
    <script src="${pageContext.request.contextPath}/javascript/games/mouse.js?v=1.0"></script>
</body>

</html>
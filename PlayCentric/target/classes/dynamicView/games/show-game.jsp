<%@ page trimDirectiveWhitespaces="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainStyle.css?v=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/games/show-game.css?v=1.0">
    <title>${game.gameName}</title>
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
            </ul>
        </nav>
    </header>
    <main>
        <article>
            <section class="container">
                <h2 class="title">${game.gameName}</h2>
                <div class="box1">
                    <picture>
                        <c:if test="${game.photoPath == null}">
                        <img>
                        </c:if>
                        <c:if test="${game.photoPath != null}">
                        <img src="/${game.photoPath}" alt="">
                        </c:if>
                    </picture>
                    <div class="innerBox1">
                        <p class="introduction">
                            ${game.introduction}
                        </p>
                        <p class="p1">
                            評論
                        </p>
                        <p class="p2">
                            ${game.onMarketTime}
                        </p>
                        <p class="p3">
                            開發者：${game.developer}
                        </p>
                        <p class="p4">
                            發行商：${game.publisher}
                        </p>
                        <p class="p6">
                        	<span>遊戲分類：</span><br>
                            <c:forEach var="category" items="${game.categoryNames}">
                            <span class="categorys">${category}</span>
                			</c:forEach>
                        </p>
                    </div>
                     <div>
                        <table class="buy">
                            <tr>
                                <td class="td1">
                                    <span class="buyGame">購買 ${game.gameName}</span>
                                </td>
                            <td class="lastTD">
                            <br><br><br>
                            <span class="space1"></span>
                            <c:if test="${game.discountRate == 0}">
                            <span class="priceTop" style="background-color: blanchedalmond;"></span>
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
                            <span
                                class="btn">加入購物車
                                </span>
                        </td>
                            </tr>
                        </table>
                    </div>
                    <div class="innerBox2">
                        <h2>遊戲介紹</h2>
                        <p class="description">
                            ${game.description}
                        </p>
                    </div>
                </div>
            </section>
        </article>
    </main>

    <script src="${pageContext.request.contextPath}/javascript/jquery-3.7.1.min.js?v=1.0"></script>
    <script src="${pageContext.request.contextPath}/javascript/games/mouse.js?v=1.0"></script>

</html>
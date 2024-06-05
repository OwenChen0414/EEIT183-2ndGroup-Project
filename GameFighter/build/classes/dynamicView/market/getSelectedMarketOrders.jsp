<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

      <!DOCTYPE html>
      <html>
      <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>遊戲道具交易後台</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/market/style.css" />
      </head>

      <body>
        <header>
          <div class="search">
            <h1>遊戲道具交易後台</h1>
            <form action="${pageContext.request.contextPath}/GetSelectedProps">
              <label for="SelectGame">遊戲選單:</label>
              <select id="SelectGame" name="gameId">
                <option value=""></option>
                <c:forEach items="${games}" var="game">
                  <option value="${game.id}">${game.name}</option>
                </c:forEach>
              </select>
              <input type="submit" value="確認" />
            </form>

            <form action="${pageContext.request.contextPath}/FuzzySearchProp">
              <label for="searchPropName">名稱搜尋:</label>
              <input type="text" id="searchPropName" name="searchPropName" required>
              <input type="submit" value="確認"></button>
            </form>
            <a id="create" href="http://localhost:8080/GameFighter/dynamicView/market/createProp.jsp">新增道具</a>
          </div>

          <%-- 因道具表單沒有設計遊戲名稱欄位，只能拿遊戲ID編號來一一比對取得遊戲名稱 --%>
            <c:set var="selectName" value="" />
            <c:forEach var="game" items="${games}">
              <c:if test="${props[0].gameId == game.id}">
                <c:set var="selectName" value="${game.name}" />
                <c:set var="breakLoop" value="true" />
              </c:if>
              <c:if test="${breakLoop}">
                <c:remove var="breakLoop" />
              </c:if>
            </c:forEach>
            <nav>
            <h2 class="title2">${selectName}</h2>
              <ul>
                <li><a href="http://localhost:8080/GameFighter/GetSelectedProps?gameId=${props[0].gameId}">道具總覽</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/GetSelectedMarketOrders">拍賣場</a></li>
                <li><a href="">成交紀錄</a></li>
                <li><a href="" id="">我的倉庫</a></li>
              </ul>
            </nav>
        </header>
        <main>
          <section class="data" >
            <table>
              <tr>
                <th>委託單ID</th>
                <th>名稱</th>
                <th>物品識別碼</th>
                <th>種類</th>
                <th>圖片</th>
                <th>價格</th>
                <th>賣家ID</th>
                <th>下架時間</th>
                <th>訂單狀態</th>
                <form calss="OrdersData">
                 <c:forEach items="${SeletedGameMrketOrders}" var="order" varStatus="status">
                 <c:set var="prop" value="${SelectedProps[status.index]}"/>
                    <tr>
                      <td><input type="text" value="${order.orderId}" disabled ></td>
                      <td><input type="text" value="${prop.propName}" disabled ></td>
                      <td><input type="text" value="${order.uniqueId}" disabled  ></td>
                      <td><input type="text" value="${prop.propType}" disabled ></td>
                      <td><img src="images/market/${prop.propImagePath}" alt="${prop.propImagePath}" width="40" height="60" disabled ></td>
                      <td><input type="text" value="${order.price}" ></td>
                      <td><input type="text" value="${order.sellerId}" disabled ></td>
                      <td>
                        <input type="text" value="<fmt:formatDate value="${order.expirationTime}" pattern="yyyy-MM-dd HH:mm:ss" />" >
                        </td>
                      <td>
                        <select name="orderStatus" >
                         <option value="Selling" ${order.orderStatus == 'Selling' ? 'selected' : ''}>販售中</option>
                         <option value="Sold" ${order.orderStatus == 'Sold' ? 'selected' : ''}>已售出</option>
                         <option value="Removed" ${order.orderStatus == 'Removed' ? 'selected' : ''}>已下架</option>
                        </select> 
                      </td>
                    </tr>
                 </c:forEach>
           </table>
        <button class="changeOrder" type="submit">確認</button>
      </form>
            <span class="totalRecord">
              <h3>共${SeletedGameMrketOrders.size()}筆資料</h3>
            </span>
          </section>
        </main>
       </body>
      </html>
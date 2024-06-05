<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>遊戲道具交易後台</title>
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css"
    />
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
      </div>
      <nav>
        <ul>
         <li><a href="http://localhost:8080/Martket/GetSelectedProps?game=${props[0].gameId}">道具總覽</a></li>
         <li><a href="">拍賣場</a></li>
         <li><a href="">成交紀錄</a></li>
         <li><a href="" id="">我的倉庫</a></li>
        </ul>
      </nav>
    </header>
  </body>
</html>

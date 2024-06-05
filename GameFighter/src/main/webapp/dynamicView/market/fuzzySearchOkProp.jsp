<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
        <form action="${pageContext.request.contextPath}/FuzzySearchProp">
  			<label for="searchPropName">名稱搜尋:</label>
  			<input type="text"  id="searchPropName" name="searchPropName" required>
  			<input type="submit" value="確認"></button>
		</form>   
			<a id="create" href="http://localhost:8080/Martket/jsp/createProp.jsp">新增道具</a>
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
          <li><a href="http://localhost:8080/Martket/GetSelectedProps?gameId=${props[0].gameId}">道具總覽</a></li>
          <li><a href="${pageContext.request.contextPath}/GetSelectedMarketOrders">拍賣場</a></li>
          <li><a href="">成交紀錄</a></li>
          <li><a href="" id="">我的倉庫</a></li>
        </ul>
      </nav>
    </header>
    <main>
      <section class="data">
        <table>
          <tr>
            <th>ID</th>
            <th>名稱</th>
            <th>圖片</th>
            <th>種類</th>
            <th>稀有度</th>
            <th>描述</th>
            <th>創建時間</th>
            <th>更新時間</th>
            <th>修改</th>
            <th>刪除</th>

            <c:forEach items="${searchOKprops}" var="searchOKprops">
              <tr>
                <td>${searchOKprops.propId}</td>
                <td>${searchOKprops.propName}</td>
                <td><img src="images/${searchOKprops.propImagePath}"  alt="${searchOKprops.propImagePath}" width="65" height="90"></td>
                <td>${searchOKprops.propType}</td>
                <td>${searchOKprops.propRarity}</td>
                <td>${searchOKprops.propDescription}</td>
				<td><fmt:formatDate value="${searchOKprops.createdTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
 				<td><fmt:formatDate value="${searchOKprops.updatedTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
              	<td>
              	  <form action="${pageContext.request.contextPath}/GetPropForUpdate">
                    <input type="hidden" name="propId" value="${searchOKprops.propId}">
                    <input type="hidden" name="propName" value="${searchOKprops.propName}">
                    <input type="hidden" name="propType" value="${searchOKprops.propType}">
                    <input type="hidden" name="propRarity" value="${searchOKprops.propRarity}">
                    <input type="hidden" name="propDescription" value="${searchOKprops.propDescription}">
                    <input type="hidden" name="propImagePath" value="${searchOKprops.propImagePath}">
                    <input type="hidden" name="gameId" value="${searchOKprops.gameId}">
                    <input type="submit" value="update">
                </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/DeleteProp">
                    <input type="hidden" name="gameId" value="${searchOKprops.gameId}">
                    <input type="hidden" name="propId" value="${searchOKprops.propId}">
                    <input type="submit" id="" name="delete" value="delete">
                  </form>
                </td>
              
              </tr>
            </c:forEach>
          </tr>
        </table>
            <span class="totalRecord">
              <h3>共${props.size()}筆資料</h3>
            </span>
      </section>
    </main>
  </body>
</html>

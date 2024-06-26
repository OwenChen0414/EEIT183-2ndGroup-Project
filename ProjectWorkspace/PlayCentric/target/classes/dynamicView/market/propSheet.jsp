<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
        <form id="myForm" action="${pageContext.request.contextPath}/DemoPropServletAction">
            <label for="SelectGame">遊戲選單:</label>
            <select id="SelectGame" name="gameId">
                <c:forEach items="${games}" var="game">
                    <option value="${game.gameId}">${game.gameName}</option>
                </c:forEach>
            </select>
            <input type="submit" value="確認" />
        </form>

        <form action="${pageContext.request.contextPath}/FuzzySearchProp">
            <label for="searchPropName">名稱搜尋:</label>
            <input type="text" id="searchPropName" name="searchPropName" required />
            <input type="submit" value="確認" />
        </form>
        <a id="create" href="${pageContext.request.contextPath}/dynamicView/market/insertProp.jsp">新增道具</a>
    </div>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/DemoGameServletAction?game=${props[0].gameId}">道具總覽</a></li>
            <li><a href="">拍賣場</a></li>
            <li><a href="">成交紀錄</a></li>
            <li><a href="">我的倉庫</a></li>
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
            </tr>
            <c:forEach items="${empty searchOKprops ? props : searchOKprops}" var="prop">
                <tr>
                    <td>${prop.propId}</td>
                    <td>${prop.propName}</td>
                    <td><img src="images/market/${prop.propImageName}" alt="${prop.propImageName}" width="65" height="90"></td>
                    <td>${prop.propType}</td>
                    <td>${prop.propRarity}</td>
                    <td>${prop.propDescription}</td>
                    <td><fmt:formatDate value="${prop.createdTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    <td><fmt:formatDate value="${prop.updatedTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    <td>
                        <form action="${pageContext.request.contextPath}/DemoUpdateServletAction1">
                            <input type="hidden" name="gameId" value="${prop.gameId}" />
                            <input type="hidden" name="propId" value="${prop.propId}" />
                            <input type="hidden" name="propName" value="${prop.propName}" />
                            <input type="hidden" name="propType" value="${prop.propType}" />
                            <input type="hidden" name="propRarity" value="${prop.propRarity}" />
                            <input type="hidden" name="propDescription" value="${prop.propDescription}" />
                            <input type="hidden" name="propImageName" value="${prop.propImageName}" />
                            <input type="submit" value="update" />
                        </form>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}/DemoDeletePropServletAction">
                            <input type="hidden" name="gameId" value="${prop.gameId}" />
                            <input type="hidden" name="propId" value="${prop.propId}" />
                            <input type="submit" name="delete" value="delete" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <span class="totalRecord">
            <h3>共${empty searchOKprops ? props.size() : searchOKprops.size()}筆資料</h3>
        </span>
    </section>
</main>
</body>
</html>

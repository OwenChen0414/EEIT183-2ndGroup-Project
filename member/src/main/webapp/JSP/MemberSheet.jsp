<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/member/Styles/memberSheet.css">
<link rel="stylesheet" media="screen and  (max-width: 780px)" href="/member/Styles/home780.css" />
<script src="/member/javascript/jquery-3.7.1.min.js"></script>
<script src="/member/javascript/MemberSheet.js"></script>
<title>會員管理</title>
</head>
<body>
    <dialog>
        <h3>查詢</h3>
        <form action="SearchMember" method="post">
            id: <input type="text" name="id"><br>
            帳號: <input type="text" name="account"><br>
            密碼: <input type="text" name="password"><br>
            email: <input type="text" name="email"><br>
            暱稱: <input type="text" name="nickName"><br>
            姓名: <input type="text" name="memName"><br>
            生日: <input type="text" name="birthday"><br>
            手機: <input type="text" name="phone"><br>
            地址: <input type="text" name="address"><br>
            消費金額: <input type="number" name="consumption"><br>
            註冊日期: <input type="date" name="registDate"><br>
            上次登入: <input type="datetime-local" name="lastLogin"><br>
            身分: <input type="text" name="role"><br>
            等級: <input type="text" name="level"><br>
            <button type="submit" class="button">查詢</button>
            <button type="reset" class="button">清空</button>
            <button type="button" class="button hideDialog">取消</button>
        </form>
    </dialog>
    <main id="allpage">
        <header>
            <nav>
                <ul class="menu">
                    <li><a href="/member/MemberManage" class="active">會員管理</a></li>
                    <li><a href="">遊戲管理</a></li>
                    <li><a href="/Martket/GetAllGames">交易管理</a></li>
                    <li><a href="">論壇管理</a></li>
                    <li><a href="">陪玩管理</a></li>
                    <li><a href="">活動管理</a></li>
                    <li class="right"><a href="/member/JSP/Home.jsp">回到首頁</a></li>
                </ul>
            </nav>
        </header>
        <div class="page" align="center">
            <form action="" method="post">
            <h1>員工資料</h1>
            <button type="button" class="button search">查詢</button>
            <span class="lines">每頁顯示 <input type="number" class="lines" min="3" max="9" value="9"> 筆資料</span>
            <article>
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>帳號</th>
                                <th>密碼</th>
                                <th>email</th>
                                <th>註冊日期</th>
                                <th>最後登入時間</th>
                                <th>身分</th>
                                <th>消費金額</th>
                                <th>會員等級</th>
                                <th>暱稱</th>
                                <th>姓名</th>
                                <th>生日</th>
                                <th>手機號碼</th>
                                <th>住址</th>
                                <th>第三方登入</th>
                                <th>陪玩帳號</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${members}" var="mb">
                        	<tr>
                        		<td>${mb.id }</td>
                        		<td>${mb.account }</td>
                        		<td>${mb.password }</td>
                        		<td>${mb.email }</td>
                        		<td>${mb.registDate }</td>
                        		<td>${mb.lastLogin }</td>
                        		<td>${mb.role }</td>
                        		<td>${mb.consumption }</td>
                        		<td>${mb.level }</td>
                        		<td>${mb.nickName }</td>
                        		<td>${mb.memName }</td>
                        		<td>${mb.birthday }</td>
                        		<td>${mb.phone }</td>
                        		<td>${mb.address }</td>
                        		<td>${mb.sso }</td>
                        		<td>${mb.accomAccount }</td>
                        	</tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </article>
                <div class="pagebtns">
                    <button type="button" class="button last page">上一頁</button>
                    <button type="button" class="button next page">下一頁</button>
                </div>
                <div class="buttons">
                    <button type="button" class="button insert">新增</button>
                    <button type="button" class="button update">更新</button>
                    <button type="button" class="button delete">刪除</button>
                    <button type="submit" class="button submit">確定</button>
                    <button type="button" class="button cancel">取消</button>
                </div>
            </form>
            <c:if test="${descript != null }">
                <h3 class="hint">${descript}</h3>
            </c:if>
        </div>
	</main>
</body>
</html>
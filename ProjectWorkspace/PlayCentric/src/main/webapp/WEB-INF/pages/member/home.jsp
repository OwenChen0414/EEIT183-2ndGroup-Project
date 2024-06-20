<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/member/home.css">
<link rel="stylesheet" media="screen and  (max-width: 780px)" href="css/member/home780.css" />
<title>首頁</title>
</head>
<body>
    <main id="allpage">
        <header>
            <img class="logo-img" src="images/member/logo.jpg" title="logo" alt="logo">
            <nav>
                <ul class="menu">
                    <li><a href="/Home">首頁</a></li>
                    <li><a href="/homepage.con">遊戲</a></li>
                    <li><a href="">交易</a></li>
                    <li><a href="">論壇</a></li>
                    <li><a href="">陪玩</a></li>
                    <li><a href="">活動</a></li>
                    <c:if test="${member.role == 'manager' }">
	                    <li><a href="/PlayCentric/MemberManage">管理員入口</a></li>
                    </c:if>
                    <li class="right">
                        <c:if test="${member.role == 'manager' || member.role == 'member'}" var="condition">
                        	<a href="/PlayCentric/Logout">${member.account}登出</a>
                        </c:if>
                        <c:if test="${!condition}">
                        	<a href="/PlayCentric/Login">登入</a>
                        </c:if>
                    </li>
                </ul>
            </nav>
        </header>
	</main>
</body>
</html>
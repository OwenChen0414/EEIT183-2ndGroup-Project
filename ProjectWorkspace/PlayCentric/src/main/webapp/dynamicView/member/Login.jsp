<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/PlayCentric/css/member/Login.css">
<script src="/PlayCentric/javascript/jquery-3.7.1.min.js"></script>
<script src="/PlayCentric/javascript/member/Login.js"></script>
<title>登入</title>
</head>
<body>
    <section>
        <div class="page">
            <h2>登入</h2>
            <form action="/PlayCentric/Login" method="post">
                <p>帳號:</p>
                <input type="text" name="account" id="account" autofocus placeholder="輸入帳號" value="${param.account}">
                <p>密碼:</p>
                <input type="password" name="password" id="password" autofocus placeholder="輸入密碼" value="${param.password}">
                <button type="submit" class="button">登入</button>
                <button type="reset" class="button">清空</button>
                <a href="${lastPage}"><button type="button" class="button">取消</button></a>
            </form>
            <c:if test="${role == 'guest' }">
            	<h3 class="fail">帳號或密碼錯誤!</h3>
            </c:if>
        </div>
    </section>
</body>
</html>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>新增活動</title>
    <link rel="stylesheet" type="text/css" href="/PlayCentric/css/event/event01.css">
    <script type="text/javascript">
        window.onload = function() {
            var message = "<%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>";
            if (message) {
                alert(message);
            }
        };
    </script>
</head>
<body>
      <!-- 其他功能的連結 -->
      <div class="box">
     	<div class="btn-group">
            <a href="/PlayCentric/event/getAllEvent">所有活動</a>
     		<a href="/PlayCentric/event/add">新增活動</a>
            <a href="/PlayCentric/event/delete">刪除活動</a>
            <a href="/PlayCentric/event/getOne">查詢活動</a>
        </div>
      </div>
    <div class="container">
    	<h2 style="animation: pulse 1s infinite alternate;">新增活動</h2>
        <form action="/PlayCentric/event/addEvent" method="post">
            <input type="text" name="name" placeholder="活動名稱">
            <br>
            <input type="text" name="description" placeholder="活動描述">
            <br>
            <input type="date" name="date">
            <br>
            <input type="text" name="location" placeholder="活動地點">
            <br>
            <input type="text" name="organizer" placeholder="主辦單位">
            <br>
            <button type="submit">新增</button>
        </form>
    </div>
</body>
</html>


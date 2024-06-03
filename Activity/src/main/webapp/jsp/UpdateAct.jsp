<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改活動</title>
<link rel="stylesheet" type="text/css" href="/Activity/styles/styledark.css">
<script type="text/javascript">
	window.onload = function() {
		var message = "<%=request.getAttribute("message") != null ? request.getAttribute("message") : ""%>";
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
			<a href="/Activity/AddAct">新增活動</a> 
			<a href="/Activity/DeleteAct">刪除活動</a>
			<a href="/Activity/GetAct">查詢活動</a> 
			<a href="/Activity/GetAllAct">查詢所有活動</a>
		</div>
	</div>
	<div class="container">
		<h2 style="animation: pulse 1s infinite alternate;">修改活動</h2>
		<form action="/Activity/UpdateAct" method="post">
			<label for="activityno">活動編號：</label> 
			<input type="text" id="activityno" name="activityno" value="${activityno}"><br>
			<label for="name">活動名稱：</label> 
			<input type="text" id="name" name="name" value="${name}"><br>
			<label for="description">活動描述：</label> 
			<input type="text" id="description" name="description" value="${description}"><br> 
			<label for="date">活動日期：</label> 
			<input type="date" id="date" name="date" value="${date}"><br>
			<label for="location">活動地點：</label>
			<input type="text" id="location" name="location" value="${location}"><br> 
			<label for="organizer">主辦單位：</label> 
			<input type="text" id="organizer" name="organizer" value="${organizer}"><br>
			<button type="submit">修改</button><br>
		</form>
	</div>
</body>
</html>

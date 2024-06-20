<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>活動資料查詢</title>
    <link rel="stylesheet" type="text/css" href="/PlayCentric/css/event/event01.css">
</head>
<body>
      <!-- 其他功能的連結 -->
      <div class="box">
     	<div class="btn-group">
            <a href="/PlayCentric/GetAllEvent">所有活動</a>
     		<a href="/PlayCentric/AddEvent">新增活動</a>
            <a href="/PlayCentric/UpdateEvent">修改活動</a>
            <a href="/PlayCentric/DeleteEvent">刪除活動</a>
            <a href="/PlayCentric/GetEvent">查詢活動</a>
        </div>
      </div>
    <div class="container">
        <h2 style="animation: pulse 1s infinite alternate;">活動資料查詢</h2>
       <form action="/PlayCentric/GetEvent" method="get">
    <table style="margin: 0 auto;">
        <tr>
            <td>查詢條件</td>
            <td>
                <select name="searchCriteria" class="custom-select">
  				  <option value="eventno">活動編號</option>
  				  <option value="name">活動名稱</option>
  				  <option value="description">活動描述</option>
   				  <option value="date">活動日期</option>
    			  <option value="location">活動地點</option>
    			  <option value="organizer">主辦單位</option>
				</select>
            </td>
            <td><input type="text" name="searchQuery" value="${param.searchQuery != null ? param.searchQuery : ''}"></td>
            <td><button type="submit">查詢</button></td>
        </tr>
    </table>
</form>
        <c:if test="${not empty events}">
            <table border="1">
                <tr>
                    <th>活動編號</th>
                    <th>活動名稱</th>
                    <th>活動描述</th>
                    <th>活動日期</th>
                    <th>活動地點</th>
                    <th>主辦單位</th>
                </tr>
                <c:forEach var="event" items="${events}">
                    <tr>
                        <td><input type="text" disabled value="${event.eventno}"></td>
                        <td><input type="text" disabled value="${event.name}"></td>
                        <td><input type="text" disabled value="${event.description}"></td>
                        <td><input type="text" disabled value="${event.date}"></td>
                        <td><input type="text" disabled value="${event.location}"></td>
                        <td><input type="text" disabled value="${event.organizer}"></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <br>
    </div>
    <script type="text/javascript">
        window.onload = function() {
            var message = "<%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>";
            if (message.trim() !== "") {
                alert(message);
            }
        };
    </script>
</body>
</html>

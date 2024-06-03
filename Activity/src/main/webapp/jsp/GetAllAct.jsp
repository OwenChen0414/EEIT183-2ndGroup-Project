<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>活動管理</title>
    <link rel="stylesheet" type="text/css" href="/Activity/styles/styledark.css">
    <style>
        /* 新增的滾動條樣式 */
        .overflow-scroll {
            overflow: auto;
            max-height: 200px; /* 這裡可以調整最大高度 */
        }
    </style>
     <script type="text/javascript">
        window.onload = function() {
            var message = "<%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>";
            if (message.trim() !== "") {
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
            <a href="/Activity/UpdateAct">修改活動</a>
            <a href="/Activity/DeleteAct">刪除活動</a>
            <a href="/Activity/GetAct">查詢活動</a>
            </div>
       </div>
    <div class="container">
        <h2>活動管理</h2>
        <c:if test="${not empty activities}">
            <table>
                <tr>
                    <th>活動編號</th>
                    <th>活動名稱</th>
                    <th>活動描述</th>
                    <th>活動日期</th>
                    <th>活動地點</th>
                    <th>主辦單位</th>
                </tr>
                <c:forEach var="act" items="${activities}">
                    <tr>
                        <td><input type="text" disabled value="${act.activityno}" style="width: auto;"></td>
                        <td><input type="text" disabled value="${act.name}" style="width: auto;"></td>
                        <td class="overflow-scroll"><input type="text" disabled value="${act.description}" style="width: auto;"></td>
                        <td><input type="text" disabled value="${act.date}" style="width: auto;"></td>
                        <td><input type="text" disabled value="${act.location}" style="width: auto;"></td>
                        <td><input type="text" disabled value="${act.organizer}" style="width: auto;"></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <br>
    </div>
</body>
</html>

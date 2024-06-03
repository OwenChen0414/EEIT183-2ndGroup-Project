<%@page import="java.util.*"%>
<%@page import="com.ispanwei.bean.EmpBean"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>刪除該筆資料</title>
<script>
  alert("確定刪除?");
  setTimeout(function() {
    window.location.href = "${pageContext.request.contextPath}/DeleteUser";
  }, 300);
</script>
</head>
<body style="background-color: lightgray">
	<div align="center"></div>
</body>
</html>
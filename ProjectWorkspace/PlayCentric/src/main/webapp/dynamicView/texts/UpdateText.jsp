<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新文章詳細資料</title>
</head>
<body style="background-color: #fdf5e6">
<div align="center">
<h2>更新大成功!!!</h2>
<jsp:useBean id="txt" scope="request" class="com.ispan.bean.texts.TextsBean" />
</div>
<button onclick="goBack()">返回</button>
<script type="text/javascript">
        function goBack() {
            window.history.back();
        }
    </script>
</body>
</html>
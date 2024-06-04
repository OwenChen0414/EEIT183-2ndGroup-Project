<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查無資料</title>
<style type="text/css">
@font-face {
        font-family: 'PixelFont';
        src: url('${pageContext.request.contextPath}/fonts/ark-pixel-12px-monospaced-zh_tw.ttf') format('truetype');
    }
body {
            font-family: 'PixelFont', Arial, sans-serif;
            background-image: url('${pageContext.request.contextPath}/resorce/123.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center center;
            margin: 40%;
            padding: 0;
        }
    h1 {
            color: #34ff44;
            text-align: center;
            margin-top: -6px;
            padding-top: 10px;
            padding-bottom: 10px;
            
        }
    button {
            margin-left: 25%;
            width: 40%;
            height: 50px;
            text-align: center;
            border: 10px groove darkorange;
            background-color: hsl(313, 100%, 73%);
            color: #fcfcff;
        }

    button:hover {
            background-color: gold;
            color: #eb3427;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4) inset;
            transform: translateY(2px) scale(0.98);
        }
</style>
</head>
<body style="background-color: #fdf5e6">
<div align="center">
<h1>勇者<br>這裡不存在你所尋之物...<br><br>( ´･･)ﾉ(._.`)</h1>
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
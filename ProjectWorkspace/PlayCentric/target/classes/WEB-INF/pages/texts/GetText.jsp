<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>單筆文章詳細資料(管理員)</title>
<style type="text/css">
body {
            background-image: url('${pageContext.request.contextPath}/images/texts/123.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center center;
            margin: 0;
            padding: 0;
        }

        h1 {
            color: #34ff44;
            text-align: center;
            margin-top: -7px;
            padding-top: 10px;
            padding-bottom: 10px;
            border-bottom: 10px groove darkorange;
        }


        .form-group {
            display: block;
            margin-bottom: 10px;
            width: 100%;
            padding: 10px;
            border-radius: 4px;
            background-color: black;
            color: #34ff44;
            box-sizing: border-box;
        }



        #d0 {
            background-color: rgb(193, 99, 255);
            margin: 30%;
            padding: 20px;
            border-radius: 20px;

        }


        textarea {
            resize: none;
            width: 100%;
            box-sizing: border-box;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        td {
            padding: 5px;
            vertical-align: top;
        }



        button {
            margin-left: 30%;
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
<body>
<div id="d0">
        <h1 style="color: #34ff44;">單筆文章詳細資料(管理員)</h1>
        <jsp:useBean id="txt" scope="request" class="com.ispan.bean.texts.TextsBean" />
        <table>
            <tr>
                <td style="color: #34ff44;">文章編號:
                <td><input class="form-group" type="text" disabled value="<%= txt.getTextsId() %>">
            <tr>
                <td style="color: #34ff44;">受檢舉種類編號:
                <td><input class="form-group" type="text" disabled value="<%= txt.getTextsReportId() %>">
            <tr>
                <td style="color: #34ff44;">作者(會員)編號:
                <td><input class="form-group" type="text" disabled value="<%= txt.getMembersId() %>">
            <tr>
                <td style="color: #34ff44;">留言區編號:
                <td><input class="form-group" type="text" disabled value="<%= txt.getTalkId() %>">
            <tr>
                <td style="color: #34ff44;">遊戲類型分類編號:
                <td><input class="form-group" type="text" disabled value="<%= txt.getTagId() %>">
            <tr>
                <td style="color: #34ff44;">討論區編號:
                <td><input class="form-group" type="text" disabled value="<%= txt.getForumId() %>">
            <tr>
                <td style="color: #34ff44;">標題:
                <td><textarea id="t1" class="form-group" disabled cols="15"><%= txt.getTitle() %></textarea>
            <tr>
                <td style="color: #34ff44;">內文:
                <td><textarea id="t2" class="form-group" disabled rows="5"
                        cols="50"><%= txt.getTextContent() %></textarea>
            <tr>
                <td style="color: #34ff44;">更新時間:
                <td><input type="text" class="form-group" disabled value="<%= txt.getUpdatedTime() %>">
            <tr>
                <td style="color: #34ff44;">文章建立時間:
                <td><input type="text" class="form-group" disabled value="<%= txt.getDoneTime() %>">
        </table>
        <button onclick="goBack()">返回</button>
    </div>
    <script type="text/javascript">
        function goBack() {
            window.history.back();
        }
    </script>
</body>
</html>
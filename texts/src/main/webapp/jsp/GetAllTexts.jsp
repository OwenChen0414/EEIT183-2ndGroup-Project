<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*,com.texts.bean.TextsBean"%>
<%!@SuppressWarnings("unchecked")%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>文章詳細資料(管理員)</title>
    <style type="text/css">
         html, body {
            margin: 0;
            padding: 0;
            height: 100%;
            width: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            background-image: url('${pageContext.request.contextPath}/resorce/123.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center center;
            font-family: Arial, sans-serif;
        }

        #d0 {
            background-color: rgb(193, 99, 255);
            padding: 20px;
            border-radius: 20px;
            width: 90%;
            max-width: 1200px;
            overflow: auto;
        }

        h2, h3 {
            text-align: center;
            margin-top: 0;
            padding-top: 10px;
        }

        h2 {
            color: #34ff44;
        }

        h3 {
            color: #34ff44;;
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

        table {
            width: 100%;
            border-collapse: collapse;
             background-color: white;
             border-radius: 10px;
             overflow: hidden;
        }

        td, th {
            padding: 5px;
            vertical-align: top;
            border: 1px solid #ddd;
        }

        th {
            background-color: #a8fefa;
        }

        button {
            display: block;
            margin: 20px auto;
            width: 40%;
            height: 50px;
            text-align: center;
            border: 10px groove darkorange;
            background-color: hsl(313, 100%, 73%);
            color: #fcfcff;
            cursor: pointer;
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
        <h2>文章詳細資料(管理員)</h2>
        <table>
            <tr>
                <th>文章編號</th>
                <th>受檢舉種類編號</th>
                <th>作者(會員)編號</th>
                <th>留言區編號</th>
                <th>遊戲類型分類編號</th>
                <th>討論區編號</th>
                <th>標題</th>
                <th>內文</th>
                <th>更新時間</th>
                <th>文章建立時間</th>
            </tr>
            <% List<TextsBean> txts = (ArrayList<TextsBean>)request.getAttribute("txts");
                for(TextsBean txt : txts) { %>
            <tr>
                <td><%= txt.getTextsId() %></td>
                <td><%= txt.getTextsReportId() %></td>
                <td><%= txt.getMembersId() %></td>
                <td><%= txt.getTalkId() %></td>
                <td><%= txt.getTagId() %></td>
                <td><%= txt.getForumId() %></td>
                <td><%= txt.getTitle() %></td>
                <td><%= txt.getTextContent() %></td>
                <td><%= txt.getUpdatedTime() %></td>
                <td><%= txt.getDoneTime() %></td>
            </tr>
            <% } %>
        </table>
        <h3>共<%= txts.size() %>筆文章詳細資料</h3>
        <button onclick="goBack()">返回</button>
    </div>
    <script type="text/javascript">
        function goBack() {
            window.history.back();
        }
    </script>
</body>

</html>


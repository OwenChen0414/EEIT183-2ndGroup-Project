<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*,java.time.LocalDateTime, java.time.format.DateTimeFormatter,com.ispan.bean.texts.Texts"%>
<%!@SuppressWarnings("unchecked")%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>文章詳細資料(管理員)</title>
    <style type="text/css">
        @font-face {
            font-family: 'PixelFont';
            src: url('/PlayCentric/fonts/ark-pixel-12px-monospaced-zh_tw.ttf') format('truetype');
        }

        html, body {
            margin: 0;
            padding: 0;
            height: 100%;
            width: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            background-image: url('/PlayCentric/images/texts/123.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center center;
            font-family: 'PixelFont', Arial, sans-serif;
        }

        #d0 {
            background-color: rgb(193, 99, 255);
            padding: 20px;
            border-radius: 20px;
            width: 90%;
            max-width: 1200px;
            max-height: 80%;
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

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            border-radius: 10px;
            overflow: hidden;
        }

        td, th {
            padding: 5px;
            vertical-align: middle;
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

        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .pagination button {
            margin: 0 5px;
            padding: 5px 10px;
            border: 1px solid #ddd;
            background-color: black;
            cursor: pointer;
            color: #34ff44;
        }

        .pagination button.active {
            background-color: black;
            border-color: #a8fefa;
        }

        .pagination button:hover {
            background-color: gold;
            color: #eb3427;
        }
    </style>
</head>

<body>
    <div id="d0">
        <h2>文章詳細資料(管理員)</h2>
        <table id="textsTable">
            <thead>
                <tr>
                    <th>文章編號</th>
                    <th>討論區編號</th>
                    <th>作者(會員)編號</th>
                    <th>標題</th>
                    <th>內文</th>
                    <th>創作時間</th>
                    <th>最後編輯時間</th>
                    <th>按讚數</th>
                    <th>文章有無受隱藏</th>
                </tr>
            </thead>
            <tbody>
                <% List<Texts> txts = (ArrayList<Texts>) request.getAttribute("txts");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                   for (int i = 0; i < txts.size(); i++) { %>
                <tr data-index="<%= i %>">
                    <td><%= txts.get(i).getTextsId() %></td>
                    <td><%= txts.get(i).getForumId() %></td>
                    <td><%= txts.get(i).getMemId() %></td>
                    <td><%= txts.get(i).getTitle() %></td>
                    <td><%= txts.get(i).getTextsContent() %></td>
                    <td><%= txts.get(i).getDoneTime().format(formatter) %></td>
                    <td><%= txts.get(i).getUpdatedTime().format(formatter) %></td>
                    <td><%= txts.get(i).getTextsLikeNum() %></td>
                    <td><%= txts.get(i).isHideTexts() ? "是" : "否" %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <div class="pagination" id="pagination"></div>
        <h3>共<%= txts.size() %>筆文章詳細資料</h3>
        <button onclick="goBack()">返回</button>
    </div>
    <script type="text/javascript">
        function goBack() {
            window.history.back();
        }

        const rowsPerPage = 5;
        let currentPage = 1;

        function displayTablePage(page) {
            const rows = document.querySelectorAll('#textsTable tbody tr');
            const totalPages = Math.ceil(rows.length / rowsPerPage);

            rows.forEach((row, index) => {
                row.style.display = (index >= (page - 1) * rowsPerPage && index < page * rowsPerPage) ? '' : 'none';
            });

            document.querySelectorAll('.pagination button').forEach(button => {
                button.classList.remove('active');
            });

            document.querySelector(`.pagination button[data-page="${page}"]`).classList.add('active');
        }

        function setupPagination() {
            const rows = document.querySelectorAll('#textsTable tbody tr');
            const totalPages = Math.ceil(rows.length / rowsPerPage);
            const pagination = document.getElementById('pagination');
            pagination.innerHTML = '';

            for (let i = 1; i <= totalPages; i++) {
                const button = document.createElement('button');
                button.textContent = i;
                button.setAttribute('data-page', i);
                button.addEventListener('click', () => displayTablePage(i));
                pagination.appendChild(button);
            }

            if (totalPages > 0) {
                displayTablePage(1);
            }
        }

        document.addEventListener('DOMContentLoaded', () => {
            setupPagination();
        });
    </script>
</body>

</html>



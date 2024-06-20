<%@page import="java.util.*"%>
		<%@page import="com.ispan.bean.playWithOthers.PlayUserBean" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
			<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html>

<head>
<title>後台資料管理</title>
<meta charset="UTF-8">


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<style>
body {
			margin: 0;
			padding: 0;
			font-family: Arial, sans-serif;
			background-image: linear-gradient(to top, #cfd9df 0%, #e2ebf0 100%);
		}

		.sidebar {
			background-color: #fff;
			width: 200px;
			height: 100vh;
			position: fixed;
			left: 0;
			top: 0;
			transition: margin-left 0.3s ease;
		}

		.content {
			margin-left: 200px;
			margin-right: 50px;
			padding: 20px;
		}

		a {
			text-decoration: none;
			color: inherit;
		}

		table {
			width: 100%;
			border-collapse: collapse;
		}

		th,
		td {
			margin-top: 10px;
			padding: 8px;
			text-align: center;
			border: 1px solid #ddd;
		}

		tr {
			border-radius: 10px;
		}

		th {
			background-image: linear-gradient(to top, #cfd9df 0%, #e2ebf0 100%);
		}

		#nickname {
			height: 25px;
			width: 250px;
			margin-top: 10px;
			margin-bottom: 6px;
			border-radius: 5px;
		}

		#nickname:hover {
			border-color: rgba(0, 0, 0, 0.496);
		}

		.selectButton {
			height: 31px;
			border-radius: 6px;
		}

		.selectButton:hover {
			border-color: rgba(0, 0, 0, 0.496);
		}


		.createButton {
			right: 0px;
			height: 31px;
			width: 80px;
			border-radius: 6px;
			background-color: lightblue;
		}

		.createButton:hover {
			box-shadow: 2px 2px 4px rgba(8, 190, 184, 0.55);
		}

		.outside {
			padding: 20px;
			/* border: 1px solid red; */
			border-radius: 25px;
			background-color: #fff;

		}

		.outside:hover {
			box-shadow: 2px 2px 4px rgba(0, 0, 0, .6);
			transition: 0.5s ease;
		}



		.sidebar {
			background-color: #fff;
			width: 200px;
			height: 100vh;
			position: fixed;
			left: 0;
			top: 0;
			transition: margin-left 0.3s ease;
		}

		.sidebar ul {
			list-style-type: none;
			padding: 0;
			margin-top: 50px;
		}

		.sidebar li {
			margin: 5px;
			padding: 10px;
			border-radius: 15px;
			border: 2px solid #fff;
		}

		.sidebar li a {
			display: block;
			width: 150px;
			height: 20px;
			text-decoration: none;
			color: inherit;
		}

		.sidebar li:hover {
			border: 2px solid rgba(144, 144, 144, 0.5);
			transition: 1.5s ease;
		}

		.toggle-button {
			position: absolute;
			top: 10px;
			left: 5px;
			cursor: pointer;
			transition: margin-left 0.9s ease;
			border-radius: 25px;
		}

		.toggle-button:hover {
			background-color: rgba(169, 169, 169, 0.138);
		}

		img {
			width: 68px;
			height: 85px;
		}

		.selectButtonDiv {
			margin: 10px;
			display: flex;
			justify-content: space-between;
		}

		.selectText {
			width: 200px;
			height: 25px;
			border-radius: 8px;
		}

		.table {
			border-radius: 10px;
		}

		.createTd {
			padding: 4px;
			background-color: lightgreen;
			border-radius: 8px;
			font-family: Arial, sans-serif;

		}

		.deleteTd {
			padding: 4px;
			background-color: coral;
			border-radius: 8px;
			font-family: Arial, sans-serif;

		}

		.createTd:hover {
			border: 1px solid gray;
		}

		.deleteTd:hover {
			border: 1px solid gray;
		}
</style>
<body>
	<script>
		$(function() {
			const toggleButton = $('.toggle-button');
			const bar = $('.sidebar');
			const content = $('.content')
			toggleButton.on('click', function() {
				bar.toggleClass('hidden');
				if (bar.hasClass('hidden')) {
					bar.css('marginLeft', '-150px');
					toggleButton.css('marginLeft', '+140px');
					content.css('margin-left', '+80px')
				} else {
					bar.css('marginLeft', '0');
					toggleButton.css('marginLeft', '0');
					content.css('margin-left', '+200px')
				}
			});

		});
	</script>
	<div class="content">


		<div class="sidebar">
			<div class="toggle-button">
				<svg xmlns="http://www.w3.org/2000/svg" height="20"
					viewBox="0 0 24 24" width="50">
								<path d="M0 0h24v24H0z" fill="none" />
								<path d="M3 18h18v-2H3v2zM3 6v2h18V6H3zm0 7h18v-2H3v2z" />
							</svg>
			</div>
			<ul>
				<li><a
					href="${pageContext.request.contextPath}/dynamicView/playWithOthers/cms.jsp">返回前頁</a></li>
			</ul>
		</div>
		<h1>伴遊資料管理</h1>
		<div class="outside">
			<div class="selectButtonDiv">
				<form method="post"
					action="${pageContext.request.contextPath}/fuzzySearch">
					<input class="selectText" type="text" name="nickname"
						placeholder="搜尋資料"> <input class="selectButton"
						type="submit" value="查詢" />
				</form>

				<form method="post"
					action="${pageContext.request.contextPath}/dynamicView/playWithOthers/CreateUser.jsp">
					<input class="createButton" type="submit" value="新增資料" />
				</form>
			</div>


			<div align="center">
				<jsp:useBean id="user" scope="request"
					class="com.ispan.bean.playWithOthers.PlayUserBean" />


				<table border="1" style="background-color: white">
					<tr>
						<th>id</th>
						<th>伴遊暱稱</th>
						<th>遊戲編號</th>
						<th>支付單位(時/次)</th>
						<th>金額</th>
						<th>上線時間</th>
						<th>下線時間</th>
						<th>伴遊者照片</th>
						<th>編輯時間</th>
						<th>交易狀態</th>
						<th></th>
					</tr>

					<c:set var="imagePath" value="${pageContext.request.contextPath}/images/playWithOthers/" />


					<tr>
						<td>${user.id}</td>
						<td>${user.nickname }</td>
						<td>${user.gameId }</td>
						<td>${user.pricingCategory }</td>
						<td>${user.amount }</td>
						<td>${user.onlineTime }</td>
						<td>${user.offlineTime }</td>
						<td><img src="${imagePath}${user.playerPhoto}" /></td>
						<td>${user.editedTime }</td>
						<td>${user.transactionStatus }</td>

						<td><a class="createTd"
							href="${pageContext.request.contextPath}/UpdateUser?id=${user.id} ">修改</a>
							<br> <br> <a class="deleteTd"
							href="${pageContext.request.contextPath}/DeleteUser?id=${user.id} ">刪除</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>

</html>
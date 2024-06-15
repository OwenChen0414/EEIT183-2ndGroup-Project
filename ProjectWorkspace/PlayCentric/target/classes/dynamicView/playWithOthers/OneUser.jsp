
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<title>後台資料管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/playWithOthers/NewFile.css">

<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<style>

</style>

<body>
	<script>
		document.addEventListener('DOMContentLoaded', function() {
			const previewImage = document.getElementById('preview');
			const fileInput = document.querySelector('input[name="photo"]');
			const deleteBtn = document.getElementById('deleteBtn');

			fileInput.addEventListener('change', function(event) {
				const file = event.target.files[0];
				const reader = new FileReader();

				reader.onload = function(e) {
					previewImage.src = e.target.result;
					previewImage.style.display = 'inline-block';
					deleteBtn.style.display = 'inline-block';
				}

				reader.readAsDataURL(file);
			});

		});

		$(function() {
			const toggleButton = $('.toggle-button');
			const bar = $('.sidebar');
			const content = $('.content');
			toggleButton.on('click', function() {
				bar.toggleClass('hidden');
				if (bar.hasClass('hidden')) {
					bar.css('marginLeft', '-150px');
					toggleButton.css('marginLeft', '+140px');
					content.css('margin-left', '+80px')
				} else {
					bar.css('marginLeft', '0');
					toggleButton.css('marginLeft', '0');
					content.css('margin-left', '+250px')
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
                    <path
						d="M3 18h18v-2H3v2zM3 6v2h18V6H3zm0 7h18v-2H3v2z" />
                </svg>
			</div>
			<ul>
				<li><a href="${pageContext.request.contextPath}/DemoPwFindAll">返回前頁</a></li>
			</ul>
		</div>

		<h3></h3>
		<div class="outside">
			<div align="center">
				<h2>${PwUser.nickname}的伴遊資料</h2>

				<%-- 				<jsp:useBean id="user" scope="request" class="com.ispan.bean.playWithOthers.PwUser" /> --%>
				<form method="post" action="DemoUpdatePwUser"
					enctype="multipart/form-data">
					<table>
						<tr>
							<td colspan="2">伴遊編號 <input id="textNam" type="text"
								name="id" value="${PwUser.id}" readonly style="width: 30px"></td>
						</tr>

						<tr>
    <td style="width: 150px;">伴遊暱稱</td>
    <td style="width: 350px;">
        <input type="text" name="nickname" value="${PwUser.nickname}">
    </td>
</tr>

						<tr>
							<td>登列遊戲</td>
							<td><select id="gameId" name="gameId" required>
									<option value="1" ${PwUser.gameId == 1 ? 'selected' : ''}>英雄聯盟</option>
									<option value="2" ${PwUser.gameId == 2 ? 'selected' : ''}>Fortnite</option>
									<option value="3" ${PwUser.gameId == 3 ? 'selected' : ''}>PlayerUnknown's
										Battlegrounds (PUBG)</option>
									<option value="4" ${PwUser.gameId == 4 ? 'selected' : ''}>Call
										of Duty: Warzone</option>
									<option value="5" ${PwUser.gameId == 5 ? 'selected' : ''}>Minecraft</option>
									<option value="6" ${PwUser.gameId == 6 ? 'selected' : ''}>Apex
										Legends</option>
									<option value="7" ${PwUser.gameId == 7 ? 'selected' : ''}>Valorant</option>
									<option value="8" ${PwUser.gameId == 8 ? 'selected' : ''}>Candy
										Crush Saga</option>
									<option value="9" ${PwUser.gameId == 9 ? 'selected' : ''}>傳說對決</option>
							</select></td>
						</tr>
						<tr>
							<td>支付單位(時/次)</td>
							<td><select id="pricingCategory" name="pricingCategory"
								required>
									<option value="小時制"
										${PwUser.pricingCategory == '小時制' ? 'selected' : ''}>小時制</option>
									<option value="單次制"
										${PwUser.pricingCategory == '單次制' ? 'selected' : ''}>單次制</option>
							</select></td>

							<!-- 							<td><input type="text" name="pricingCategory" -->
							<%-- 								value="${PwUser.pricingCategory}"></td> --%>
						</tr>
						<tr>
							<td>金額</td>
							<td><input type="text" name="amount"
								value="${PwUser.amount}"></td>
						</tr>
						<tr>
							<td>上線時間</td>
							<td><input type="time" name="onlineTime"
								value="${PwUser.onlineTime}"></td>
						</tr>
						<tr>
							<td>下線時間</td>
							<td><input type="time" name="offlineTime"
								value="${PwUser.offlineTime}"></td>
						</tr>
						<tr>
							<td>伴遊者照片</td>
							<td>
								<div class="preview-container">
									<c:set var="imagePath"
										value="${pageContext.request.contextPath}/images/playWithOthers/" />
									<img id="preview" src="${imagePath}${PwUser.playerPhoto}"
										alt="">
								</div> <input type="file" name="playerPhoto"
								value="${PwUser.playerPhoto}">
							</td>
						</tr>
						<tr>
							<td>編輯時間</td>
							<td><input type="text" name="editedTime"
								value="${PwUser.editedTime}"></td>
						</tr>
						<tr>
							<td>交易狀態</td>
							<td><select id="pricingCategory" name="transactionStatus"
								required>
									<option value="閒置"
										${PwUser.pricingCategory == '閒置' ? 'selected' : ''}>閒置</option>
									<option value="交易中"
										${PwUser.pricingCategory == '交易中' ? 'selected' : ''}>交易中</option>
							</select></td>
						</tr>
						<tr>
							<td>伴遊一句話</td>
							<td>
								<p style="font-size: 13px">※本內容將會顯示於您的伴遊介紹，請妥善填寫</p> 
								<textarea
									id="description" name="description"
									style="height: 135px; width: 500px; font-size: 20px;">${PwUser.description}</textarea>

							</td>
						</tr>
					</table>
					<br>
					<button type="submit" class="updateTd">確認更新</button>
					<button class="deleteTd"
						href="${pageContext.request.contextPath}/DemoDeletePwUser?id=${PwUser.id}">刪除</button>
				</form>
			</div>
		</div>
	</div>
</body>

</html>

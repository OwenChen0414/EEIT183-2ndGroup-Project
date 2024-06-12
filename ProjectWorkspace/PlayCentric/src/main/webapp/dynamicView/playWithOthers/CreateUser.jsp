<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>填寫用戶資料</title>
		
		
		    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/playWithOthers/CreateUser.css">
	</head>

	<body>
	<script>
			document.addEventListener('DOMContentLoaded', function () {
				const previewImage = document.getElementById('preview');
				const fileInput = document.querySelector('input[name="photo"]');

				fileInput.addEventListener('change', function (event) {
					const file = event.target.files[0];
					const reader = new FileReader();

					reader.onload = function (e) {
						previewImage.src = e.target.result;
						previewImage.style.display = 'block';
					}

					reader.readAsDataURL(file);
				});
			});
		</script>
		<div class="outside">
			<h2>創建伴遊者資料</h2>
			<form method="post" action="/GameFighter/CreateUser" enctype="multipart/form-data">
				<label for="nickname">伴遊者暱稱:</label>
				<input type="text" id="nickname" name="nickname" required><br>

				<label for="gameId">伴遊遊戲:</label>
				<select id="gameId" name="gameId" required>
					<option value="1">英雄聯盟</option>
					<option value="2">Fortnite</option>
					<option value="3">PlayerUnknown's Battlegrounds (PUBG)</option>
					<option value="4">Call of Duty: Warzone</option>
					<option value="5">Minecraft</option>
					<option value="6">Apex Legends</option>
					<option value="7">Valorant</option>
					<option value="8">Candy Crush Saga</option>
					<option value="9">傳說對決</option>
				</select><br>

				<label for="pricingCategory">交易單位:</label>
				<select id="pricingCategory" name="pricingCategory" required>
					<option value="小時制">小時制</option>
					<option value="單次制">單次制</option>
				</select>

				<label for="amount">每單金額:</label>
				<input type="number" name="amount" min="5" max="1000" step="5" value="${user.amount}">
				
				<br>
				<!--  -->
				<label for="onlineTime">上線時間:</label>
				<input type="time" id="onlineTime" name="onlineTime"><br>

				<label for="offlineTime">離線時間:</label>
				<input type="time" id="offlineTime" name="offlineTime"><br>

				<label for="transactionStatus">交易狀態:</label>
				<select id="transactionStatus" name="transactionStatus">
					<option value="閒置">閒置</option>
					<option value="交易中">交易中</option>
				</select>
				<br>

				<label for="description">描述:</label>
				<textarea class="description" id="description" name="description"></textarea>

				<br>
				<br>
				<label for="photo">上傳您的照片:</label>
				<input type="file" name="photo"> <br> <img id="preview" src="#" alt="">
				<br>
				<input type="submit" value="提交">
			</form>
		</div>
	</body>

	</html>
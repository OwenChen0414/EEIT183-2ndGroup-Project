<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>資料修正</title>
		<style>
			body {
				font-family: Arial, sans-serif;
				background-color: #212223;
				padding: 20px;
			}


			form {
				margin: 0 250px;
				background-color: #2b7697;
				padding: 20px;
				border-radius: 8px;
				box-shadow: 2px 2px 4px rgba(0, 0, 0, .6);
			}

			label {
				display: block;
				font-weight: bold;
				margin-bottom: 5px;
			}

			input[type=text],
			select,
			input[type=time],
			input[type=number]
			 {
				width: 100%;
				padding: 10px;
				border: 1px solid #000000;
				border-radius: 8px;
				box-sizing: border-box;
				margin-bottom: 15px;
				text-align: left;
				/* 文本左对齐 */
				vertical-align: top;
				/* 垂直对齐到顶部 */
			}

			input[type=text]:hover,
			input[type=time]:hover,
			input[type=number]:hover,
			select:hover,
			.description:hover {
				border: 1px solid #ffffff;
			}



			input[type=submit] {
				background-color: #4CAF50;
				color: white;
				padding: 12px 20px;
				border: none;
				border-radius: 8px;
				cursor: pointer;
				display: block;
				margin: 0 auto;
			}

			input[type=submit]:hover {
				background-color: #45a049;
			}

			#preview {
				display: block;
				margin: 10px auto;
				max-width: 200px;
				max-height: 200px;
			}

			.description {
				width: 100%;
				/* 设置初始宽度 */
				height: 100px;
				/* 设置初始高度 */
				border: 1px solid #000000;
				border-radius: 8px;
				box-sizing: border-box;
				margin-bottom: 15px;
				text-align: left;
				/* 文本左对齐 */
				vertical-align: top;
				/* 垂直对齐到顶部 */
			}

			.outside {
				margin: auto;
			}

			h2 {
				color: #2b7697;
				text-align: center;
			}
		</style>
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
		<h2>資料修正</h2>
		<% com.ispanwei.bean.PlayUserBean user=(com.ispanwei.bean.PlayUserBean) request.getAttribute("user"); %>
			<form method="post" action="UpdateUser2" enctype="multipart/form-data">
				<label for="nickname">伴遊者暱稱:</label>
				<input type="text" id="nickname" name="nickname" value="${user.nickname}" required><br>

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
				</select>
				<br>

				<label for="pricingCategory">交易單位:</label>
				<select id="pricingCategory" name="pricingCategory" required>
					<option value="小時制" ${user.pricingCategory.equals("小時制") ? 'selected' : '' }>小時制</option>
					<option value="單次制" ${user.pricingCategory.equals("單次制") ? 'selected' : '' }>單次制</option>
				</select><br>

				<label for="amount">每單金額:</label>
				<input type="number" name="amount" min="5" max="1000" step="5" value="${user.amount}">
				<!--  -->
				<label for="onlineTime">上線時間:</label>
				<input type="time" id="onlineTime" name="onlineTime" value="${user.onlineTime}"><br>

				<label for="offlineTime">離線時間:</label>
				<input type="time" id="offlineTime" name="offlineTime" value="${user.offlineTime}"><br>

				<label for="transactionStatus">交易狀態:</label>
				<select id="transactionStatus" name="transactionStatus">
					<option value="閒置" ${user.transactionStatus.equals("閒置") ? 'selected' : '' }>閒置</option>
					<option value="交易中" ${user.transactionStatus.equals("交易中") ? 'selected' : '' }>交易中</option>
				</select>
				<br>

				<label for="description">描述:</label>
				<textarea class="description" id="description" name="description">${user.description}</textarea>

				<br>
				<br>
				<label for="photo">上傳您的照片:</label>
				<input type="file" name="photo"> <br> <img id="preview" src="#" alt="">
				<br>
				<input type="submit" value="提交">
			</form>

	</body>

	</html>
<%@page import="java.util.*" %>
	<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			<!DOCTYPE html>
			<html>

			<head>
				<title>後台資料管理</title>
				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/css/playWithOthers/GetAllUser.css">

				<meta charset="UTF-8">


				<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


			</head>


			<body>
				<script>
					$(document).ready(function () {
						$('th.sortable').on('click', function () {
							var table = $(this).closest('table');
							var rows = table.find('tr:gt(0)').toArray().sort(comparer($(this).index()));
							this.asc = !this.asc;
							if (!this.asc) {
								rows = rows.reverse();
							}
							for (var i = 0; i < rows.length; i++) {
								table.append(rows[i]);
							}
						});
					});

					function comparer(index) {
						return function (a, b) {
							var valA = getCellValue(a, index);
							var valB = getCellValue(b, index);
							return $.isNumeric(valA) && $.isNumeric(valB) ? valA - valB : valA.toString().localeCompare(valB);
						};
					}

					function getCellValue(row, index) {
						return $(row).children('td').eq(index).text();
					}

					$(document).ready(function () {
						// 獲取表單和背景遮罩元素
						const modalForm = $('#modalForm');
						const modalBackground = $('#modalBackground');
						const showFormBtn = $('#showFormBtn');

						// 點擊按鈕顯示表單和背景遮罩
						showFormBtn.click(function () {
							modalForm.css('display', 'block'); // 顯示表單
							modalBackground.css('display', 'block'); // 顯示背景遮罩
						});

						// 點擊背景遮罩隱藏表單和背景遮罩
						modalBackground.click(function () {
							modalForm.css('display', 'none'); // 隱藏表單
							modalBackground.css('display', 'none'); // 隱藏背景遮罩
						});
					});

					$(function () {
						window.confirmDelete = function () {
							return confirm("確認要刪除嗎？"); // 彈出確認框
						};
					});

					document.addEventListener('DOMContentLoaded', function () {
						const previewImage = document.getElementById('preview');
						const fileInput = document.querySelector('input[name="photo"]');
						const deleteBtn = document.getElementById('deleteBtn');

						fileInput.addEventListener('change', function (event) {
							const file = event.target.files[0];
							const reader = new FileReader();

							reader.onload = function (e) {
								previewImage.src = e.target.result;
								previewImage.style.display = 'inline-block';
								deleteBtn.style.display = 'inline-block';
							}

							reader.readAsDataURL(file);
						});

					});

					$(function () {
						const toggleButton = $('.toggle-button');
						const bar = $('.sidebar');
						const content = $('.content');
						toggleButton.on('click', function () {
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

				<div class="modal-background" id="modalBackground"></div>
				<div class="modal" id="modalForm">
					<form method="post" action="${pageContext.request.contextPath}/insert"
						enctype="multipart/form-data">
						<div class="modal-header">
							<h2>請填寫下列資料</h2>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="nickname">伴遊者暱稱:</label>
								<br>
								<input type="text" name="nickname" class="form-control" required>
							</div>
							<div class="form-group">
								<label for="gameId">伴遊遊戲:</label> <select id="gameId" name="gameId" class="form-control"
									required>
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
							</div>
							<div class="form-group">
								<label for="pricingCategory">交易單位:</label> <select id="pricingCategory"
									name="pricingCategory" class="form-control" required>
									<option value="小時制">小時制</option>
									<option value="單次制">單次制</option>
								</select>
							</div>
							<div class="form-group">
								<label for="amount">每單金額:</label> <input type="number" id="amount" name="amount"
									class="form-control" min="5" max="1000" step="5" value="${user.amount}">
							</div>
							<div class="form-group">
								<label for="onlineTime">上線時間:</label> <input type="time" id="onlineTime"
									name="onlineTime" class="form-control">
							</div>
							<div class="form-group">
								<label for="offlineTime">離線時間:</label> <input type="time" id="offlineTime"
									name="offlineTime" class="form-control">
							</div>
							<div class="form-group">
								<label for="transactionStatus">交易狀態:</label> <select id="transactionStatus"
									name="transactionStatus" class="form-control">
									<option value="閒置">閒置</option>
									<option value="交易中">交易中</option>
								</select>
							</div>
							<div class="form-group">
								<label for="description">請描述自己(該內容將會顯示於伴遊主頁，請務必填寫提高伴遊意願)</label>
								<textarea id="description" name="description" class="form-control"></textarea>
							</div>

							<div class="preview-container">
								<img id="preview" src="#" alt="" style="display: none;">
							</div>
							<br>
							<input type="file" name="photo">
							<br>

						</div>
						<div class="modal-footer">
							<button type="submit" class="btn-submit">提交</button>
						</div>

					</form>

				</div>




				<div class="content">
					<div class="sidebar">
						<div class="toggle-button">
							<svg xmlns="http://www.w3.org/2000/svg" height="20" viewBox="0 0 24 24" width="50">
								<path d="M0 0h24v24H0z" fill="none" />
								<path d="M3 18h18v-2H3v2zM3 6v2h18V6H3zm0 7h18v-2H3v2z" />
							</svg>
						</div>
						<ul>
							<li><a href="${pageContext.request.contextPath}/weiching/xxx.jsp">註冊伴遊</a>
							</li>
							<li><a href="${pageContext.request.contextPath}/weiching/xxx.jsp">伴遊者名冊</a>
							</li>
							<li><a href="${pageContext.request.contextPath}/weiching/xxx.jsp">申請資格審核</a>
							</li>
							<li><a href="${pageContext.request.contextPath}/weiching/xxx.jsp">回伴遊首頁</a>
							</li>
						</ul>
					</div>
					<h1>伴遊資料管理</h1>
					<div class="outside">
						<div class="selectButtonDiv">
							<form method="post" action="${pageContext.request.contextPath}/search">
								<input class="selectText" type="text" name="nickname" placeholder="搜尋資料"> <input
									class="selectButton" type="submit" value="查詢" />
							</form>

							<input id="showFormBtn" class="createButton" type="submit" value="新增資料" />
						</div>

						<div align="center" class="table">
							<c:if test="${empty pwUsers}">
								<p style="color: red">查無資料，請重新輸入。</p>
							</c:if>
							<c:if test="${not empty pwUsers}">
								<table border="1" style="background-color: white">
									<tr>
										<th class="sortable">伴遊編號</th>
										<th class="sortable">伴遊暱稱</th>
										<th class="sortable">伴遊者照片</th>
										<th class="sortable">遊戲編號</th>
										<th class="sortable">支付單位(時/次)</th>
										<th class="sortable">金額</th>
										<th class="sortable">上線時間</th>
										<th class="sortable">下線時間</th>
										<th class="sortable">編輯時間</th>
										<th class="sortable">交易狀態</th>
										<th class="sortable"></th>
										<th class="sortable"></th>
									</tr>
									<c:forEach items="${pwUsers}" var="PwUser">
										<c:set var="imagePath"
											value="${pageContext.request.contextPath}/images/playWithOthers/" />
										<tr>
											<td>${PwUser.id}</td>
											<td>${PwUser.nickname }</td>
											<td><img src="${imagePath}${PwUser.playerPhoto}" class="zoomable-image" />
											</td>
											<td>${PwUser.gameId }</td>
											<td>${PwUser.pricingCategory }</td>
											<td>${PwUser.amount }</td>
											<td>${PwUser.onlineTime }</td>
											<td>${PwUser.offlineTime }</td>
											<td>${PwUser.editedTime }</td>
											<td>${PwUser.transactionStatus }</td>

											<td>
												<form action="${pageContext.request.contextPath}/sel"
													method="get" style="display: inline;">
													<input type="hidden" name="id" value="${PwUser.id}">
													<button class="createTd" type="submit">詳細</button>
												</form>
											</td>
											<td>
												<form action="${pageContext.request.contextPath}/del"
													method="get" style="display: inline;"
													onsubmit="return confirmDelete();">
													<input type="hidden" name="id" value="${PwUser.id}">
													<button class="deleteTd" type="submit">刪除</button>
												</form>
											</td>

										</tr>
									</c:forEach>
								</table>
							</c:if>
						</div>
					</div>
				</div>
			</body>

			</html>
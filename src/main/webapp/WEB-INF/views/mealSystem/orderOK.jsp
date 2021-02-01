<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<link
	href="https://fonts.googleapis.com/css?family=Rajdhani:300,400,500,600,700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="http://cdn.bootstrapmb.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/all.min.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/lightcase.css">
<link rel="stylesheet" href="css/flaticon.css">
<link rel="stylesheet" href="css/swiper.min.css">

<link rel="shortcut icon" href="images/meal/favicon.png"
	type="image/x-icon">
<link rel="stylesheet" href="css/stylemeal.css">

<title>餐點簡介</title>
</head>
<body>

	<!-- 引入共同的頁首 -->
	<div>
		<jsp:include page="/fragment/top.jsp" />
	</div>
	<section class="page-header bg_img"
		data-background="images/meal/foodtop.jpg">
		<div class="container">
			<h3 class="title">
				<span class="shape-wrapper"><span class="shape"></span>Meal
					Information<span class="shape"></span></span>
			</h3>
		</div>
	</section>
	<div class="breadcrumb-section1">
		<div class="container">
			<div class="breadcrumb-wrapper">
				<div class="breadcrumb-title">
					<h6 class="title">餐點查詢</h6>
				</div>
				<ul class="breadcrumb"
					style="background: transparent; margin: -5px -10px; padding: 0;">
					<li><a href="mealList?id=${mealList.mealId}">GoBackToBeal</a></li>
					<li><a href="mealSystem">HOME</a></li>
					<li>${mealList.mealName}</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- SIDE BAR -->
	<br>
	<!-- BMI Calculate Section Starts here -->
	<form>
		<section>
			<!-- Package Section Two Starts Here -->
			<br>
			<div class="container">
				<div class="row mb-30-none justify-content-center">
					<c:forEach var="orderList" items="${orderList}">
						<div class="col-lg-4 col-md-6">
							<div class="package-item">
								<div class="package-header">
									<h2 class="title">訂單ID： ${orderList.orderNo}</h2>
									<input type="hidden" value="${orderList.orderNo}" id="odNo" />
									<img src="css/img/plane.png" />
									<p>
										可取餐時間<br>
										<fmt:formatDate value="${orderList.orderFinishTime}"
											pattern="HH:mm" />
									</p>
									<p>訂餐人：${orderList.memberBean.username}</p>
								</div>
								<div class="package-content">
									<ul>
										<li>*訂餐後30分鐘後可取餐*</li>
										<li>訂餐時間：<fmt:formatDate
												value="${orderList.orderStartTime}"
												pattern="yyyy-MM-dd HH:mm" /></li>
										<li>取餐時間：<fmt:formatDate
												value="${orderList.orderFinishTime}"
												pattern="yyyy-MM-dd HH:mm" /></li>

										<c:choose>
											<c:when test="${orderList.orderStatus =='0'}">
												<li>訂單狀態：未取餐</li>
											</c:when>
											<c:when test="${orderList.orderStatus =='1'}">
												<li>訂單狀態：取餐完成</li>
											</c:when>
										</c:choose>
										<li>餐點金額: ${orderList.totalAmount}</li>
									</ul>
									<c:choose>
										<c:when test="${orderList.orderStatus =='0'}">
											<a
												href="<spring:url value='updateOrderStatus?id=${orderList.orderNo}' />"
												class="custom-button">我要取餐</a>
										</c:when>
										<c:when test="${orderList.orderStatus =='1'}">
											<a class="custom-button">取餐完成</a>
											<a class="custom-button" onclick="delOrder(${anEntry.key})">刪除訂單</a>
										</c:when>
									</c:choose>

								</div>
							</div>
						</div>
						<br>
					</c:forEach>
				</div>
			</div>

			<br> <br>
		</section>
	</form>
	<!-- Package Section Two Ends Here -->
	<!-- BMI Calculate Section Ends here -->
	<script type='text/javascript'>
	function delOrder(n) {
		if (confirm("確定刪除訂單 ? ")) {
			var x = $("#odNo").val();
			xtr = x.toString();
			document.forms[0].action = "deleteMealOrder/" + xtr;
			document.forms[0].method = "POST";
			document.forms[0].submit();
			return;
		} else {
			return;
		}
	}
	</script>
	<!-- Js Plugins -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/modernizr-3.6.0.min.js"></script>
	<script src="js/plugins.js"></script>
	<script
		src="http://cdn.bootstrapmb.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script src="js/isotope.pkgd.min.js"></script>
	<script src="js/swiper.min.js"></script>
	<script src="js/waypoint.js"></script>
	<script src="js/counterup.min.js"></script>
	<script src="js/lightcase.js"></script>
	<script src="js/wow.min.js"></script>
	<script src="js/maincoach.js"></script>
</body>
</html>

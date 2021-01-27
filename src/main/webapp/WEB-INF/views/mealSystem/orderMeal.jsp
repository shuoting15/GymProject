<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
					<h6 class="title">訂餐確認</h6>
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
	<form method='POST'>
	<section>
		<div class="container">
			<div class="row align-items-center justify-content-between">
				<div class="col-lg-6 bmi-mb">
				<br>
					<article style="display: flex; justify-content: center;">
						<img width='800'
							src="<c:url value='meal/picture/${mealList.mealId}' />" />
					</article>
				</div>
				
				<!-- right -->
				<div class="col-lg-6 bmi-mb">
					<div class="bmi-header">
						<h3 class="title" style="text-align: center">
							<span>${mealList.mealName}</span>
						</h3>
						<ul class="about-bmi">
							<li class="about-bmi-header">
								<div class="bmi-rate">會員</div>
								<div class="bmi-status">${LoginOK.member_id}</div>
							</li>
							<li>
								<div class="bmi-rate">餐點分類</div>
								<div class="bmi-status">${mealList.mealCategoryBean.categoryName}</div>
							</li>
							<li>
								<div class="bmi-rate">餐點熱量</div>
								<div class="bmi-status">${mealList.mealKcal}　Kcal</div>
							</li>
							<li>
								<div class="bmi-rate">餐點編號</div>
								<div class="bmi-status">${mealList.mealId}　號</div>
								<input type="hidden" value="${mealList.mealId}" id="hidId" />
							</li>
							<li class="about-bmi-header">
								<div class="bmi-rate">餐點價格(扣點)</div>
								<div class="bmi-status">TWD$　${mealList.mealPrice}</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<br>
			<article style="display: flex; justify-content: center;">
				<a class="bmi-calculator" type="submit"><img width='200' onclick="confirmOrder(${anEntry.key})"
								src="<c:url value='images/meal/orderOK.png' />" alt="feature"></a>
			</article>
		</div>
	</section>
	<!-- BMI Calculate Section Ends here -->
</form>


	<script type='text/javascript'>
	function confirmOrder(n) {
		if (confirm("確定訂餐 ? ") ) {
			
			
			var x=$("#hidId").val();
			xtr = x.toString();
			document.forms[0].action="orderAdd/"+xtr ;
			
			
// 			document.forms[0].action="<c:url value='/orderAdd/${mealList.mealId}' />" ;
			document.forms[0].method="POST";
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

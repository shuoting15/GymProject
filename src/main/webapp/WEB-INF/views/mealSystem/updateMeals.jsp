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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Rajdhani:300,400,500,600,700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="http://cdn.bootstrapmb.com/bootstrap/4.3.1/css/bootstrap.min.css">
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
				<span class="shape-wrapper"><span class="shape"></span>Exercise
					meal<span class="shape"></span></span>
			</h3>
		</div>
	</section>
	<div class="breadcrumb-section1">
		<div class="container">
			<div class="breadcrumb-wrapper">
				<div class="breadcrumb-title">
					<h6 class="title"><a href="menu">新增餐點</a></h6>
				</div>
				<ul class="breadcrumb"
					style="background: transparent; margin: -5px -10px; padding: 0;">
					<li><a href="menu">新增餐點</a></li>
					<li><a href="updateShowAllMealList">所有餐點</a></li>
					<li><a href="<c:url value='updateShowCategoryMealList?id=1'/>">增肌餐點</a></li>
					<li><a href="<c:url value='updateShowCategoryMealList?id=2'/>">減脂餐點</a></li>
					<li><a href="<c:url value='updateShowCategoryMealList?id=3'/>">素食餐點</a></li>
					<li><a href="mealSystem">HOME</a></li>
					<li>後台MealList</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- SIDE BAR -->


	<!-- Feature Section Starts Here -->
	<section class="feature-section padding-bottom padding-top" 
	style="background-image: url(images/meal/banner.jpg);
			background-size: 100% 100%;
            background-repeat: no-repeat;
            background-attachment: fixed;">

		<aside class="sidebar"
			style="width: 300px; float: left; margin-left: 400px">
			<div class="widget widget-category">
				<h4 class="widget-title">餐點分類</h4>
				<ul>
					<c:forEach var="category" items="${categoryList}">
						<a href="<c:url value='updateShowCategoryMealList?id=${category.categoryId}' />" class="glyphicon glyphicon-cutlery">${category.categoryName}</a>
						<br>
					</c:forEach>
				</ul>
			</div>
		</aside>



		<div class="container">
			<div class="style-feature">
				<c:forEach var='mealList' items='${mealLists}'>
					<div class="feature-item">
						<div class="feature-thumb">
							<a href="<spring:url value='/updateMealList?id=${mealList.mealId}' />"><img
								src="<c:url value='/meal/picture/${mealList.mealId}' />" alt="feature"></a>
						</div>
						<div class="feature-content" style="background-color: white ;">
							<div class="feature-header">
								<h4 class="title">
								<a href="<c:url value='/updateMealList?id=${mealList.mealId}'/>">${mealList.mealName}</a>
								</h4>
								<p>${mealList.mealCategoryBean.categoryName}</p>
								<p>TWD$${mealList.mealPrice}</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>

		</div>
	</section>
	<!-- Feature Section Ends Here -->
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
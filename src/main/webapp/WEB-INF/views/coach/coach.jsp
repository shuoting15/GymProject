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
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:400,400i,600,600i,700,700i&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="http://cdn.bootstrapmb.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/all.min.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/lightcase.css">
<link rel="stylesheet" href="css/flaticon.css">
<link rel="stylesheet" href="css/swiper.min.css">

<link rel="shortcut icon" href="images/favicon.png" type="image/x-icon">
<link rel="stylesheet" href="css/stylecoach.css">

<title>教練資訊</title>

</head>

<body>
	<jsp:include page="/fragment/top.jsp" />
	<!-- Page Header EndsHere -->
	<!-- Breadcrumb Section Starts Here -->
	<section class="page-header bg_img"
		data-background="./assets/images/banner/banner.jpg">
		<div class="container">
			<h3 class="title">
				<span class="shape-wrapper"><span class="shape"></span>Trainers<span
					class="shape"></span></span>
			</h3>
		</div>
	</section>
	<div class="breadcrumb-section1">
		<div class="container">
			<div class="breadcrumb-wrapper">
				<div class="breadcrumb-title">
					<h6 class="title">教練詳細資訊</h6>
				</div>
				<ul class="breadcrumb"
					style="background: transparent; margin: -5px -10px; padding: 0;">
					<li><a href="index.html">首頁</a></li>
					<li>教練詳細資訊</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- Breadcrumb Section Ends Here -->
	<!-- Trainer Section Starts Here -->
	<div class="trainer-details padding-bottom padding-top">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 mb-5 mb-lg-0">
					<article>
						<div class="trainer-area">
							<div class="trainer-item">
								<div class="trainer-thumb">
									<img style=""
										src="<c:url value='/getPicture/${coach.coachId}'/>"
										alt="trainer">
								</div>
								<div class="trainer-content">
									<h3 class="title">${coach.coachName}</h3>
									<span>${coach.coachExpertise}</span>
									<p>${coach.coachIntroduction}</p>
								</div>
							</div>
						</div>
					</article>
				</div>
				<div class="col-lg-4">
					<aside class="sidebar">
						<div class="widget widget-category">
							<h5 class="widget-title">教練資訊</h5>
							<ul>
								<li><a href="#">身高:${coach.coachHeight}cm</a></li>
								<li><a href="#">體重:${coach.coachWeight}kg</a></li>
								<li><a href="#">${coach.coachExpertiseOne}</a></li>
								<li><a href="#">${coach.coachExpertiseTwo}</a></li>
								<li><a href="#">${coach.coachExpertiseThree}</a></li>
							</ul>
						</div>
						<div class="widget widget-popular">
							<h5 class="widget-title">學員評分</h5>
							<div>${coach.coachRating}/5</div>
							<div class="ratings">
								<div class="empty_star">★★★★★</div>
								<div class="full_star" style="width: calc(20% * ${coach.coachRating})">★★★★★</div>
								
							</div>
						</div>
					</aside>
				</div>
			</div>
		</div>
	</div>
	<div align="center" style="width:1200px; margin:0 auto ">
	<jsp:include page="coachClander.jsp" />
	</div>
	<!-- JavaScript File Links -->
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
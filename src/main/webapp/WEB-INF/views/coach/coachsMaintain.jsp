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

<link rel="shortcut icon" href="images/trainers/favicon.png"
	type="image/x-icon">
<link rel="stylesheet" href="css/stylecoach.css">

<title>預約課程</title>
</head>
<body>
	<!-- 引入共同的頁首 -->
	<div>
		<jsp:include page="/fragment/top.jsp" />
	</div>
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
					<h6 class="title">個人教練</h6>
				</div>
				<ul class="breadcrumb"
					style="background: transparent; margin: -5px -10px; padding: 0;">
					<li><a href="index.html">首頁</a></li>
					<li>個人教練</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- Blog Section Begin -->
	<div align="center">
		<input class="btn btn-primary" style="width: 200px" type="button"
			value="新增教練"
			onclick="window.location.href='<c:url value="/coachs/add" />';" />
	</div>
	<section class="expert-trainers padding-bottom padding-top">
		<div class="container">
			<div class="expert-item-area">
				<c:forEach var='coach' items='${coachs}'>
					<!-- 					<div class="col-sm-6 col-md-3" style="width: 2000px; height: 2000px"> -->
					<!-- 						<div class="thumbnail" style="width: 500px; height: 500px"> -->
					<div class="expert-item">
						<div class="expert-thumb">
							<a href="<c:url value='coachUpdate/${coach.coachId}'/>"> <img
								src="<c:url value='/getPicture/${coach.coachId}'/>"
								alt="trainers"></a>
						</div>
						<div class="expert-content">
							<div class="expert-info">
								<h4 class="sub-title">
									<a href="<c:url value='coachUpdate/${coach.coachId}'/>">${coach.coachName}</a>
								</h4>
								<span>${coach.coachExpertise}</span>
							</div>
							<a class="expert-link" href="#0"> <i class="fas fa-link"></i>
							</a>
						</div>
						<div align="center" style="margin-top: 10px">
							<input class="btn btn-primary" style="width: 200px;"
								type="button" value="教練時間管理"
								onclick="window.location.href='<c:url value='coachTimeMaintain?id=${coach.coachId}'/>';" />
						</div>
						<div align="center" style="margin-top: 10px">
							<input class="btn btn-primary" style="width: 200px;"
								type="button" value="顧客預約明細"
								onclick="window.location.href='<c:url value='showWorkingList?coachId=${coach.coachId}'/>';" />
						</div>
					</div>

					<!-- 						</div> -->
					<!-- 					</div> -->
				</c:forEach>
			</div>
	</section>
	<!-- Blog Section End -->

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
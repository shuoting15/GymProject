<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="fr">
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
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/news.css">


<title>Video</title>

</head>

<body>
	<!-- 引入共同的頁首 -->
	<div>
		<jsp:include page="/fragment/top.jsp" />
	</div>
	<section class="page-headernews bg_imgnews"
		data-background="images/banner.jpg">
		<div class="container">
			<h3 class="titlenews">Video</h3>
		</div>
	</section>

	<div class="blog-section-area padding-top padding-bottom">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 mb-5 mb-lg-0 row">
					<c:forEach var='newsCategory' items='${newscategoryList}'>
						<div class="card-columns col-6">
							<a href='news${newsCategory}'>
								<div class="card text-white text-center p-5 category-card">
									${newsCategory}</div>
							</a>
						</div>
					</c:forEach>
				</div>

				<div class="col-lg-4">
					<aside class="sidebar">
						<form class="widget-form" action="searchnews" id="w1"
							placeholder="Search in here" method="post">
							<div class="input-group mb-3">

								<input type="text" placeholder="請輸入關鍵字" name="newskw"
									class="form-control" aria-describedby="sidebar-search">
								<div class="input-group-append">
									<button type="submit" class="btn btn-outline-secondary"
										id="sidebar-search">Search</button>
								</div>


							</div>
						</form>

						<div class="widget widget-category">
							<div class="card category-sidebar">
								<div class="card-header">News categories</div>
								<ul class="list-group list-group-flush">
									<li class="list-group-item"><a
										href="<c:url value='/news'/>">全部文章</a></li>
									<li class="list-group-item"><a
										href="<c:url value='/newsviews'/>">熱門排行</a></li>
									<li class="list-group-item"><a
										href="<c:url value='/queryNewsByCategory'/>">分類查詢</a></li>
								</ul>
							</div>
						</div>


					</aside>
				</div>
			</div>
		</div>
	</div>
	<!-- Blog Section Starts Here -->
	<!--Blog Section Ends Here -->
	<!-- Footer Section Starts Here -->
	<!-- 	<footer class="footer-section"> -->
	<!-- 		<div class="footer-top"> -->
	<!-- 			<div class="container"> -->
	<!-- 				<div class="row mb-45-none"> -->
	<!-- 					<div class="col-lg-4 col-md-6"> -->
	<!-- 						<div class="footer-widget widget-about"> -->
	<!-- 							<h5 class="widget-title">About Us</h5> -->
	<!-- 							<p>Lorem ipsum dolor sit amet, mauris libero congue eget -->
	<!-- 								pulvinar, cras ut mus tempus dolor, ante tortor ornare ante arcu -->
	<!-- 								nam</p> -->
	<!-- 							<h6 class="sub-title">Subscribe</h6> -->
	<!-- 							<form class="footer-form"> -->
	<!-- 								<input type="text" placeholder="Your Email" class="footer-input"> -->
	<!-- 								<label for="f1"><i class="fas fa-arrow-right"></i></label> <input -->
	<!-- 									type="submit" value="" id="f1"> -->
	<!-- 							</form> -->
	<!-- 							<div class="social-icons"> -->
	<!-- 								<a href="#"><i class="fab fa-facebook-f"></i></a> <a href="#"><i -->
	<!-- 									class="fab fa-twitter"></i></a> <a href="#"><i -->
	<!-- 									class="fab fa-linkedin-in"></i></a> <a href="#"><i -->
	<!-- 									class="fab fa-google-plus-g"></i></a> -->
	<!-- 							</div> -->
	<!-- 						</div> -->
	<!-- 					</div> -->
	<!-- 					<div class="col-lg-2 col-md-6"> -->
	<!-- 						<div class="footer-widget widget-link"> -->
	<!-- 							<h5 class="widget-title">Useful Link</h5> -->
	<!-- 							<ul> -->
	<!-- 								<li><a href="#">Weight Loss</a></li> -->
	<!-- 								<li><a href="#">Pakckages</a></li> -->
	<!-- 								<li><a href="#">Class Time</a></li> -->
	<!-- 								<li><a href="#">About Us</a></li> -->
	<!-- 								<li><a href="#">Offer</a></li> -->
	<!-- 							</ul> -->
	<!-- 						</div> -->
	<!-- 					</div> -->
	<!-- 					<div class="col-lg-3 col-md-6"> -->
	<!-- 						<div class="footer-widget widget-post"> -->
	<!-- 							<h5 class="widget-title">Our Blog post</h5> -->
	<!-- 							<ul> -->
	<!-- 								<li><a class="post-title" href="#">Ullamco est amet -->
	<!-- 										quis tullam cursus, metus.</a> <span>05 may 2017</span></li> -->
	<!-- 								<li><a class="post-title" href="#">Ullamco est amet -->
	<!-- 										quis tullam cursus, metus.</a> <span>05 may 2017</span></li> -->
	<!-- 							</ul> -->
	<!-- 						</div> -->
	<!-- 					</div> -->
	<!-- 					<div class="col-lg-3 col-md-6"> -->
	<!-- 						<div class="footer-widget widget-form"> -->
	<!-- 							<h5 class="widget-title">Contact Form</h5> -->
	<!-- 							<form class="footer-contact-form"> -->
	<!-- 								<input type="text" placeholder="Name"> <input -->
	<!-- 									type="text" placeholder="Email"> -->
	<!-- 								<textarea name="footer-message" id="f2" class="footer-textarea" -->
	<!-- 									placeholder="Message"></textarea> -->
	<!-- 								<input type="submit" value="submit Now"> -->
	<!-- 							</form> -->
	<!-- 						</div> -->
	<!-- 					</div> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 		<div class="footer-bottom align-items-center d-flex"> -->
	<!-- 			<div class="container"> -->
	<!-- 				<p class="m-0"> -->
	<!-- 					&copy; Copyright <a href="http://www.bootstrapmb.com/">Gymio</a> - -->
	<!-- 					2019 -->
	<!-- 				</p> -->
	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 	</footer> -->
	<!-- Footer Section Ends Here -->
	<!-- JavaScript File Links -->
	<!--     <script src="assets/js/jquery-3.3.1.min.js"></script> -->
	<!--     <script src="assets/js/modernizr-3.6.0.min.js"></script> -->
	<!--     <script src="assets/js/plugins.js"></script> -->
	<!--     <script src="http://cdn.bootstrapmb.com/bootstrap/4.3.1/js/bootstrap.min.js"></script> -->
	<!--     <script src="assets/js/isotope.pkgd.min.js"></script> -->
	<!--     <script src="assets/js/swiper.min.js"></script> -->
	<!--     <script src="assets/js/waypoint.js"></script> -->
	<!--     <script src="assets/js/counterup.min.js"></script> -->
	<!--     <script src="assets/js/lightcase.js"></script> -->
	<!--     <script src="assets/js/wow.min.js"></script> -->
	<!--     <script src="assets/js/main.js"></script> -->

</body>

</html>
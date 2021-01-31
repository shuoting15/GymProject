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
<link rel="stylesheet"
	href="http://cdn.bootstrapmb.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/all.min.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/lightcase.css">
<link rel="stylesheet" href="css/flaticon.css">
<link rel="stylesheet" href="css/swiper.min.css">
<link rel="stylesheet" href="css/news.css">
<link rel="shortcut icon" href="images/favicon.png" type="image/x-icon">
<link rel="stylesheet" href="css/style.css">




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
				<div class="col-lg-8 mb-5 mb-lg-0">
					<c:forEach var='news' items='${news}'>
						<article>
							<div class="newsarticle">
								<a href="<spring:url value='/newsone?id=${news.newsId}' />">
									<img src="<c:url value='/getNewsPicture/${news.newsId}' />"
									class="article_photo" alt="...">
								</a>
								<div class="article_area">
									<p class="article_title_news">
										<a href="<spring:url value='/newsone?id=${news.newsId}' />">${news.newsTitle}</a>
									</p>
									<p class="article_content_newssub">教練:${news.coachBean.coachName}</p>
								</div>
								<div class="article_info">
									<div class="writer_info">上傳日期${news.newsUploadTime}/瀏覽次數:${news.newsViews}</div>
								</div>
							</div>

						</article>
					</c:forEach>
				</div>

				<div class="col-lg-4">
					<aside class="sidebar">
					<form class="widget-form" action="searchnews" id="w1" placeholder="Search in here"
								method="post">
						<div class="input-group mb-3">
							
								<input type="text" placeholder="請輸入關鍵字" name="newskw" class="form-control" aria-describedby="sidebar-search">
								<div class="input-group-append">
								<button type="submit" class="btn btn-outline-secondary" id="sidebar-search" >
								Search								
								</button>	</div>
							

						</div></form>

						
						<div class="widget widget-category">
							<div class="card category-sidebar">
								<div class="card-header">常用功能</div>
								<ul class="list-group list-group-flush">
									<li class="list-group-item"><a
										href="<c:url value='/news'/>">全部文章</a></li>
									<li class="list-group-item"><a
										href="<c:url value='/newsviews'/>">熱門排行</a></li>
									<li class="list-group-item"><a
										href="<c:url value='/queryNewsByCategory'/>">分類查詢</a></li>
									<li class="list-group-item">
									
									<a						
										href="<c:url value='/newsplaylist${LoginOK.member_id}'/>">${LoginOK.username}的撥放清單</a>
										</li>
								</ul>
							</div>
						</div>

					</aside>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer Section Ends Here -->
	<!-- 	JavaScript File Links -->
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



</body>

</html>
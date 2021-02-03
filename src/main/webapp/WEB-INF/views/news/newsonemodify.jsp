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
					<article>
						<div class="col-md-5">
							${newsone.newsVideoPath}
							<div>
								<h3 class="article_title_news">${newsone.newsTitle}</h3>
								<p class="article_content_news">${newsone.newsContent}</p>
								<p class="article_content_newssub">分類:
									${newsone.newsCategory}</p>
								<p>
									<strong>No. </strong> <span class='label label-warning'>
										${newsone.newsId} </span>
								</p>
								<p class="article_content_newsco">教練:
									${newsone.coachBean.coachName}</p>
								<p class="article_content_newsco">擅長項目:
									${newsone.coachBean.coachExpertiseOne}/${newsone.coachBean.coachExpertiseTwo}/${newsone.coachBean.coachExpertiseThree}</p>
								<p class="newslink">
									<button class="btn btn-secondary">
										<a href="<spring:url value='/newsmodify' />"
											class="btn btn-default"> <span
											class="glyphicon-hand-left glyphicon"></span>返回
										</a>
									</button>
									&emsp; &emsp; &emsp;
									<!-- 									<button class="btn btn-lg btn-primary">	<a -->
									<%-- 										href="<spring:url value='/addintoplaylist?id=${newsone.newsId}'/>" --%>
									<!-- 										class="btn btn-default"> <span -->
									<!-- 										class="glyphicon-hand-left glyphicon"></span>加入我的撥放清單 -->
									<!-- 									</a></button> -->

									<form:form method='POST' modelAttribute="newsone"
										class='form-horizontal' enctype="multipart/form-data">
										<form:hidden path='newsViews' value=${newsone.newsViews } />
									</form:form>
									<button class="btn btn-warning">
										<a
											href="<spring:url value='/updatenewsone?id=${newsone.newsId}'/>"
											class="btn btn-default"> <span
											class="glyphicon-hand-left glyphicon"></span>修改
										</a>
									</button>
									
									&emsp; &emsp; &emsp;

<!-- 									<button class="btn btn-danger"> -->
<!-- 										<a -->
<%-- 											href="<spring:url value='/deletenewsbyId?id=${newsone.newsId}'/>" --%>
<!-- 											class="btn btn-default"> <span -->
<!-- 											class="glyphicon-hand-left glyphicon"></span>刪除 -->
<!-- 										</a> -->
<!-- 										</td> -->
<!-- 									</button> -->
<!-- 									<input id="clickMe" type="button" value="刪除" onclick="doFunction();" class="btn btn-danger"> -->
<!-- 										<a -->
<%-- 											href="<spring:url value='/deletenewsbyId?id=${newsone.newsId}'/>" --%>
<!-- 											class="btn btn-default"> <span -->
<!-- 											class="glyphicon-hand-left glyphicon"></span> -->
<!-- 										</a> -->
<!-- 										</td> -->


<!---------------------------------------------------------------->
<div class="blog-section-area padding-top padding-bottom">
  <div class="modal fade show" id="modal-demo">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">請再次確認</h5>
          <button class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <p>資料刪除後無法復原，是否真的要刪除?</p>
        </div>
        <div class="modal-footer">
          <a href="<spring:url value='/deletenewsbyId?id=${newsone.newsId}'/>"> 
          <button class="btn btn-primary">       
										就是要刪除</button></a>
          <a href=""><button class="btn btn-secondary">取消</button></a>
        </div>
      </div>
    </div>
  </div>
  <button class="btn btn-danger" data-toggle="modal" data-target="#modal-demo"><i class="fas fa-exclamation-circle"></i>刪除</button>
</div>	
						
								
<!---------------------------------------------------------------->
								</p>
							</div>
						</div>



					</article>
				</div>


				<div class="col-lg-4">
					<aside class="sidebar">
<!-- 						<form class="widget-form" action="searchnewsmodify" id="w1" -->
<!-- 							placeholder="Search in here" method="post"> -->
<!-- 							<div class="input-group mb-3"> -->

<!-- 								<input type="text" placeholder="請輸入關鍵字" name="newskw" -->
<!-- 									class="form-control" aria-describedby="sidebar-search"> -->
<!-- 								<div class="input-group-append"> -->
<!-- 									<button type="submit" class="btn btn-outline-secondary" -->
<!-- 										id="sidebar-search">Search</button> -->
<!-- 								</div> -->


<!-- 							</div> -->
<!-- 						</form> -->


						<div class="card category-sidebar">
								<div class="card-header">常用功能</div>
								<ul class="list-group list-group-flush">
									<li class="list-group-item list-group-item-primary"><a
										href="<c:url value='/news'/>">全部文章</a></li>
									<li class="list-group-item list-group-item-secondary"><a
										href="<c:url value='/newsviews'/>">熱門排行</a></li>
									<li class="list-group-item list-group-item-warning"><a
										href="<c:url value='/queryNewsByCategory'/>">分類查詢</a></li>
									<li class="list-group-item list-group-item-info"><a
										href="<c:url value='/newsplaylist${LoginOK.member_id}'/>">${LoginOK.username}的撥放清單</a>
									</li>
								</ul>
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
<!-- 	JavaScript File Links -->
	    <script src="js/jquery-3.3.1.min.js"></script>
	    <script src="js/modernizr-3.6.0.min.js"></script>
	    <script src="js/plugins.js"></script>
	    <script src="http://cdn.bootstrapmb.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	    <script src="js/isotope.pkgd.min.js"></script>
	    <script src="js/swiper.min.js"></script>
	    <script src="js/waypoint.js"></script>
	    <script src="js/counterup.min.js"></script>
	    <script src="js/lightcase.js"></script>
	    <script src="js/wow.min.js"></script>
	    <script src="js/main.js"></script>

</body>

</html>
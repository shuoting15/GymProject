<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

<!-- 	<div class="breadcrumb-section"> -->
<!-- 		<div class="container"> -->
<!-- 			<div class="breadcrumb-wrapper"> -->
<!-- 				<div class="breadcrumb-title"> -->
<!-- 					<h6 class="title"></h6> -->
<!-- 				</div> -->
<!-- 				<ul class="breadcrumb"> -->
<!-- 					<li>影片區</li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
	<section class="container">
		<!--       三個地方要完全一樣 -->
		<form:form method='POST' modelAttribute="newsBean"
			class='form-horizontal' enctype="multipart/form-data">
			<fieldset>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='newsTitle'>
						Title </label>
					<div class="col-lg-10">
						<form:input id="newsTitle" path="newsTitle" type='text'
							class='form:input-large' />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='newsContent'>
						Content </label>
					<div class="col-lg-10">
						<form:input id="newsContent" path="newsContent" type='textarea'
							class='form:input-large' />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='newsUploadTime'>
						Time </label>
					<div class="col-lg-10">
						<form:input id="newsUploadTime" path="newsUploadTime" type='date'
							class='form:input-large' />
					</div>
				</div>
				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="newsCategory">
						分類 </label>
					<div class='col-lg-10'>
						<form:select path="newsCategory">
							<form:option value="-1">
							   Please select
							</form:option>
							<form:options items="${newscategoryList}" />
						</form:select>
					</div>
				</div>


				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="authorId">
						作者 </label>
					<div class='col-lg-10'>
						<form:select path="authorId">
							<form:option value="-1">
							  Please select
							</form:option>
							<form:options items="${authorList}" />
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2'
						for="newsproductImage"> 圖 </label>
					<div class='col-lg-10'>
						<form:input id="newsproductImage" path="newsproductImage"
							type='file' class='form:input-large' />
					</div>
				</div>
				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="newsFilePath">
						影片 </label>
					<div class='col-lg-10'>
						<form:input id="newsVideoPath" path="newsVideoPath" type='text'
							class='form:input-large' />
						檔案路徑

					</div>
				</div>
				<div class="form-group">
					<div class='col-lg-offset-2 col-lg-10'>
						<input id="btnAdd" type='submit' class='btn btn-primary'
							value="Submit" />
					</div>
				</div>
			</fieldset>
		</form:form>
	</section>
	<!-- 							<div class="post-item style-two regular-item"> -->
	<!-- 								<div class="post-thumb"> -->
	<!-- 									<a href="blog-details.html"><img -->
	<!-- 										src="assets/images/blog/04.jpg" alt="blog"></a> -->
	<!-- 								</div> -->
	<!-- 								<div class="post-content"> -->
	<!-- 									<div class="post-header"> -->
	<!-- 										<h4 class="title"> -->
	<!-- 											<a href="blog-details.html"> AA -->
	<!-- 												</p> -->
	<!-- 									</div> -->
	<!-- 									<div class="meta-post d-flex flex-wrap justify-content-between"> -->
	<!-- 										<div class="meta-date"> -->
	<!-- 											<a href="#"><i class="far fa-calendar-alt"></i> <span>BB</span></a> -->
	<!-- 										</div> -->
	<!-- 										<div class="meta-comment"> -->
	<!-- 											<a href="#"><i class="far fa-comment"></i> <span>CC</span></a> -->
	<!-- 										</div> -->
	<!-- 									</div> -->
	<!-- 								</div> -->
	<!-- 							</div> -->
	<!--                             <div class="post-item blog-video style-two"> -->
	<!--                                 <div class="post-thumb"> -->
	<!--                                     <a href="blog-details.html"><img src="assets/images/blog/05.jpg" alt="blog"></a> -->
	<!--                                     <div class="blog-video-icon-wrapper"> -->
	<!--                                         <a href="https://www.youtube.com/embed/GT6-H4BRyqQ" -->
	<!--                                             data-rel="lightcase:myCollection"><img -->
	<!--                                                 src="assets/images/blog/video-button.png" alt="blog-button"></a> -->
	<!--                                     </div> -->
	<!--                                 </div> -->
	<!--                                 <div class="post-content"> -->
	<!--                                     <div class="post-header"> -->
	<!--                                         <h4 class="title"><a href="blog-details.html">Sent to Prison by a Software Program’s Secret Algorithms</a></h4> -->
	<!--                                         <p>Euismod hendrerit, metus ac rem sagittis justo velit. Volutpat ut, est sed et -->
	<!--                                             tincidunqusum consectetuer blandit eros. Vestibulum diam nec. Ridiculus -->
	<!--                                             justo -->
	<!--                                             volutpat dictueget odiulus gravida arcu aliquam pede fringilla. Ante et -->
	<!--                                             vestibulum sed. Eros felis mollis pharetrsper id ac, mus et posuere proin -->
	<!--                                             mauris -->
	<!--                                             donec vivamus. Aliqueaquam, odio sodales dapibus -->
	<!--                                             over the wold choose </p> -->
	<!--                                     </div> -->
	<!--                                     <div class="meta-post d-flex flex-wrap justify-content-between"> -->
	<!--                                         <div class="meta-date"> -->
	<!--                                             <a href="#"><i class="far fa-calendar-alt"></i> <span>29 May 2019</span></a> -->
	<!--                                         </div> -->
	<!--                                         <div class="meta-comment"> -->
	<!--                                             <a href="#"><i class="far fa-comment"></i> <span>Comment</span></a> -->
	<!--                                         </div> -->
	<!--                                     </div> -->
	<!--                                 </div> -->
	<!--                             </div> -->
	<!--                             <div class="post-item blockquote-item style-two"> -->
	<!--                                 <blockquote class="blog-blockquote"> -->
	<!--                                     Lorem ipsum dolor sit amet, luctus odio odio magna pquatus. In pellentesque ipsum -->
	<!--                                     nulla, -->
	<!--                                     lectus at faucibus quis ac tortor. Cras cies, purus dolor -->
	<!--                                     <span class="title">admo nuna</span> -->
	<!--                                 </blockquote> -->
	<!--                             </div> -->
	<!--                             <div class="post-item style-two"> -->
	<!--                                 <div class="post-thumb blog-slider"> -->
	<!--                                     <div class="swiper-wrapper"> -->
	<!--                                         <div class="swiper-slide"> -->
	<!--                                             <div class="post-thumb"> -->
	<!--                                                 <a href="blog-details.html"><img src="assets/images/blog/06.jpg" -->
	<!--                                                         alt="blog"></a> -->
	<!--                                             </div> -->
	<!--                                         </div> -->
	<!--                                         <div class="swiper-slide"> -->
	<!--                                             <div class="post-thumb"> -->
	<!--                                                 <a href="blog-details.html"><img src="assets/images/blog/05.jpg" -->
	<!--                                                         alt="blog"></a> -->
	<!--                                             </div> -->
	<!--                                         </div> -->
	<!--                                         <div class="swiper-slide"> -->
	<!--                                             <div class="post-thumb"> -->
	<!--                                                 <a href="blog-details.html"><img src="assets/images/blog/04.jpg" -->
	<!--                                                         alt="blog"></a> -->
	<!--                                             </div> -->
	<!--                                         </div> -->
	<!--                                     </div> -->
	<!--                                     <div class="blog-next"><i class="fas fa-angle-left"></i></div> -->
	<!--                                     <div class="blog-prev"><i class="fas fa-angle-right"></i></div> -->
	<!--                                 </div> -->
	<!--                                 <div class="post-content"> -->
	<!--                                     <div class="post-header"> -->
	<!--                                         <h4 class="title"><a href="blog-details.html">Why Instagram Is Becoming Blazeon’s Next Blazeon</a></h4> -->
	<!--                                         <p>Euismod hendrerit, metus ac rem sagittis justo velit. Volutpat ut, est sed et -->
	<!--                                             tincidunqusum consectetuer blandit eros. Vestibulum diam nec. Ridiculus -->
	<!--                                             justo -->
	<!--                                             volutpat dictueget odiulus gravida arcu aliquam pede fringilla. Ante et -->
	<!--                                             vestibulum sed. Eros felis mollis pharetrsper id ac, mus et posuere proin -->
	<!--                                             mauris -->
	<!--                                             donec vivamus. Aliqueaquam, odio sodales dapibus -->
	<!--                                             over the wold choose </p> -->
	<!--                                     </div> -->
	<!--                                     <div class="meta-post d-flex flex-wrap justify-content-between"> -->
	<!--                                         <div class="meta-date"> -->
	<!--                                             <a href="#"><i class="far fa-calendar-alt"></i> <span>29 May 2019</span></a> -->
	<!--                                         </div> -->
	<!--                                         <div class="meta-comment"> -->
	<!--                                             <a href="#"><i class="far fa-comment"></i> <span>Comment</span></a> -->
	<!--                                         </div> -->
	<!--                                     </div> -->
	<!--                                 </div> -->
	<!--                             </div> -->
	<!--                         </div> -->
	<!--                         <div class="inner-pagination-wrapper d-flex flex-wrap"> -->
	<!--                             <a href="blog.html"><i class="fas fa-angle-left"></i></a> -->
	<!--                             <a href="blog.html">03</a> -->
	<!--                             <a href="blog.html" class="active">04</a> -->
	<!--                             <a href="blog.html">05</a> -->
	<!--                             <a href="blog.html"><i class="fas fa-angle-right"></i></a> -->
	<!--                         </div> -->
	<!-- 					</article> -->
	<!-- 				</div> -->
	<div class="col-lg-4">
		<aside class="sidebar">
			<div class="widget widget-search">
				<form class="widget-form">
					<input type="text" placeholder="Search in here"> <label
						for="w1"><i class="fas fa-search"></i></label> <input
						type="submit" value="Search" id="w1">
				</form>
			</div>
			<div class="widget widget-category">
				<h5 class="widget-title">news categories</h5>
				<ul>
					<li><a href="#">gym<span>32</span></a></li>
					<li><a href="#">body building<span>55</span></a></li>
					<li><a href="#">yoga<span>77</span></a></li>
					<li><a href="#">boxing<span>90</span></a></li>
					<li><a href="#">fitness<span>09</span></a></li>
					<li><a href="#">class<span>34</span></a></li>
				</ul>
			</div>
			<div class="widget widget-post">
				<h5 class="widget-title">popular news</h5>
				<ul>
					<li><a href="#" class="post-title">Daily Report: More
							Self-Driving Cars Take to the Streets</a>
						<div class="meta-post">
							<div class="meta-date">
								<a href="#"><i class="far fa-calendar-alt"></i> <span>29
										May 2019</span></a>
							</div>
							<div class="meta-comment">
								<a href="#"><i class="far fa-comment"></i> <span>Comment</span></a>
							</div>
						</div></li>
					<li><a href="#" class="post-title">Daily Report: More
							Self-Driving Cars Take to the Streets</a>
						<div class="meta-post">
							<div class="meta-date">
								<a href="#"><i class="far fa-calendar-alt"></i> <span>29
										May 2019</span></a>
							</div>
							<div class="meta-comment">
								<a href="#"><i class="far fa-comment"></i> <span>Comment</span></a>
							</div>
						</div></li>
					<li><a href="#" class="post-title">Daily Report: More
							Self-Driving Cars Take to the Streets</a>
						<div class="meta-post">
							<div class="meta-date">
								<a href="#"><i class="far fa-calendar-alt"></i> <span>29
										May 2019</span></a>
							</div>
							<div class="meta-comment">
								<a href="#"><i class="far fa-comment"></i> <span>Comment</span></a>
							</div>
						</div></li>
				</ul>
			</div>
			<div class="widget widget-tags">
				<h5 class="widget-title">tags</h5>
				<div class="tag-item-wrapper">
					<a href="#" class="tag-item active">boxing</a> <a href="#"
						class="tag-item">Fitness</a> <a href="#" class="tag-item">class
						offers</a> <a href="#" class="tag-item">build</a>
				</div>
			</div>
			<div class="widget widget-banner">
				<div class="widget-banner-thumb">
					<img src="assets/images/blog/07.jpg" alt="blog-banner">
				</div>
				<div class="widget-banner-content">
					<h4>add banner</h4>
					<a href="#" class="custom-button">buy now</a>
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
	<footer class="footer-section">
		<div class="footer-top">
			<div class="container">
				<div class="row mb-45-none">
					<div class="col-lg-4 col-md-6">
						<div class="footer-widget widget-about">
							<h5 class="widget-title">About Us</h5>
							<p>Lorem ipsum dolor sit amet, mauris libero congue eget
								pulvinar, cras ut mus tempus dolor, ante tortor ornare ante arcu
								nam</p>
							<h6 class="sub-title">Subscribe</h6>
							<form class="footer-form">
								<input type="text" placeholder="Your Email" class="footer-input">
								<label for="f1"><i class="fas fa-arrow-right"></i></label> <input
									type="submit" value="" id="f1">
							</form>
							<div class="social-icons">
								<a href="#"><i class="fab fa-facebook-f"></i></a> <a href="#"><i
									class="fab fa-twitter"></i></a> <a href="#"><i
									class="fab fa-linkedin-in"></i></a> <a href="#"><i
									class="fab fa-google-plus-g"></i></a>
							</div>
						</div>
					</div>
					<div class="col-lg-2 col-md-6">
						<div class="footer-widget widget-link">
							<h5 class="widget-title">Useful Link</h5>
							<ul>
								<li><a href="#">Weight Loss</a></li>
								<li><a href="#">Pakckages</a></li>
								<li><a href="#">Class Time</a></li>
								<li><a href="#">About Us</a></li>
								<li><a href="#">Offer</a></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="footer-widget widget-post">
							<h5 class="widget-title">Our Blog post</h5>
							<ul>
								<li><a class="post-title" href="#">Ullamco est amet
										quis tullam cursus, metus.</a> <span>05 may 2017</span></li>
								<li><a class="post-title" href="#">Ullamco est amet
										quis tullam cursus, metus.</a> <span>05 may 2017</span></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="footer-widget widget-form">
							<h5 class="widget-title">Contact Form</h5>
							<form class="footer-contact-form">
								<input type="text" placeholder="Name"> <input
									type="text" placeholder="Email">
								<textarea name="footer-message" id="f2" class="footer-textarea"
									placeholder="Message"></textarea>
								<input type="submit" value="submit Now">
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer-bottom align-items-center d-flex">
			<div class="container">
				<p class="m-0">
					&copy; Copyright <a href="http://www.bootstrapmb.com/">Gymio</a> -
					2019
				</p>
			</div>
		</div>
	</footer>
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
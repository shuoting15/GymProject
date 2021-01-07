<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="css/nice-select.css" type="text/css">
<link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
<link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="css/style.css" type="text/css">


<!-- Header Section Begin -->
<header class="header">
	<div class="header__top">
		<div class="container">
			<div class="row">
				<div class="header__top__right__auth col-lg-6"></div>
				<div class="header__cart col-lg-6">
					<ul>
						<li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
						<li><a href="<c:url value='/shoppingCart/showCartContent'/>"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
						<li><a href="#">
								<h5>
									<i class="fa fa-user"></i>
								</h5>
						</a></li>
					</ul>
				</div>

			</div>
		</div>
	</div>
	</div>
	</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-2">
				<div class="header__logo">
					<a href="./index.html"><img src="images/logo5.png" alt=""></a>
				</div>
			</div>
			<div class="col-lg-10">
				<nav class="header__menu">
					<ul>
						<li><a href="./index.html">會員</a></li>
						<li><a href="./shop-grid.html">商城</a>
							<ul class="header__menu__dropdown">
								<li><a href="./shop-details.html">Shop Details</a></li>
								<li><a href="./shoping-cart.html">Shoping Cart</a></li>
								<li><a href="./checkout.html">Check Out</a></li>
								<li><a href="./blog-details.html">Blog Details</a></li>
							</ul></li>
						<li><a href="./blog.html">預約教練</a></li>
						<li><a href="./blog.html">團體課程</a>
							<ul class="header__menu__dropdown">
								<li><a href="./shop-details.html">實體</a></li>
								<li><a href="./shoping-cart.html">線上直播</a></li>
							</ul></li>
						<li><a href="./blog.html">健身餐</a></li>
						<li><a href="./blog.html">教學區</a>
							<ul class="header__menu__dropdown">
								<li><a href="./shop-details.html">文章</a></li>
								<li><a href="./shoping-cart.html">影片</a></li>
							</ul></li>
						<li><a href="./blog.html">健身論壇</a></li>
						<li><a href="./contact.html"> 聯絡我們 </a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
	<div class="humberger__open">
		<i class="fa fa-bars"></i>
	</div>
	</div>
</header>
<!-- Header Section End -->



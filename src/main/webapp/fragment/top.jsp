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
						<li><a href="#">
								<h5>
									<i class="fa fa-user"></i>
								</h5>
						</a></li>
						<li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
						<c:choose>
						<c:when test="${ShoppingCart.itemNumber > 0}">
							<c:set var="cartContent" value="${ShoppingCart.itemNumber}" />
							<c:set var="cartSubtotal" value="${ShoppingCart.subtotal}" />
						</c:when>
						<c:otherwise>
							<c:set var="cartContent" value="0" />
							<c:set var="cartSubtotal" value="0" />
						</c:otherwise>
						</c:choose>
						<li><a href="<c:url value='/shoppingCart/showCartContent'/>"><i
								class="fa fa-shopping-bag"></i> <span>${cartContent}</span></a></li>
						<li style="font-weight: 400px; color: red;">$
							${cartSubtotal}</li>

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
					<a href="./index.html"><img src="../images/logo5.png" alt=""></a>
				</div>
			</div>
			<div class="col-lg-10">
				<nav class="header__menu">
					<ul>
						<li><a href="./index.html">會員</a></li>
						<li><a href="<c:url value='/productDisplay/productAll' />">商城</a>
							<ul class="header__menu__dropdown">
								<li><a href="<c:url value='/productDisplay/productAll' />">所有商品</a></li>
								<li><a
									href="<c:url value='/shoppingCart/showCartContent' />">我的購物車</a></li>
								<li><a href="./checkout.html">我的訂單</a></li>
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
						<li><a href="./contact.html">後台管理</a>
						 <ul class="header__menu__dropdown">
								<li><a href="./shop-details.html">會員</a></li>
								<li><a href="<c:url value='/productMaintain/productAll' />">商品</a></li>
								<li><a href="<c:url value='/productMaintain/productAll' />">訂單</a></li>
								<li><a href="./shoping-cart.html">教練</a></li>
								<li><a href="./shoping-cart.html">團課</a></li>
								<li><a href="./shoping-cart.html">健身餐</a></li>
								<li><a href="./shoping-cart.html">教學區</a></li>
								<li><a href="./shoping-cart.html">論壇</a></li>
							</ul>
						</li>
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

<!-- <div class="container"> -->
<!-- 	<div class="row"> -->
<!-- 		<div class="col-lg-12"> -->
<!-- 			<div class="hero__search"> -->
<!-- 				<div class="hero__search__form"> -->
<!-- 					<form action="#"> -->
<!-- 						<div class="hero__search__categories"> -->
<!-- 							All Categories <span class="arrow_carrot-down"></span> -->
<!-- 						</div> -->
<!-- 						<input type="text" placeholder="想買什麼?"> -->
<!-- 						<button type="submit" class="site-btn">SEARCH</button> -->
<!-- 					</form> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->

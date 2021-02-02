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
<!-- <link rel="stylesheet" href="css/stylecoach.css" type="text/css"> -->
<link rel="stylesheet" href="css/stylemember.css" type="text/css">
<link rel="stylesheet" href="../css/stylemember.css" type="text/css">

<style>
.dropdown:hover .dropdown-content {
	display: block;
}

.dropbtn {
	font-size: 16px;
	border: none;
}

.dropdown {
	/* 	position: relative; */
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	/* 	background-color: #f1f1f1; */
	background-color: #3C3C3C;
	min-width: 300px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	color: white;
	padding: 8px 12px;
	text-decoration: none;
	display: block;
	min-height: 60px;
}

.dropdown-content a:hover {
	background-color: #6C6C6C;
	color: #7fad39;
}

/* .dropdown:hover .dropbtn { */
/* 	background-color: #3e8e41; */
/* } */
</style>

<!-- Header Section Begin -->
<header class="header">
	<div class="header__top">
		<div class="container">
			<div class="row" style='margin-right: 30px'>
				<div class="header__top__right__auth col-lg-6"></div>
				<div class="header__cart col-lg-6" id="headerBoxTop">
					<ul>
						<li><a href="<c:url value="/register"/>" class="headerButton"
							id="topregister">加入會員</a> <a href="<c:url value="/login"/>"
							class="headerButton" id="toplogin">會員登入</a> <a
							href="<c:url value="/logout"/>" class="headerButton logout"
							id="toplogout">會員登出</a></li>
						<li><span style='color: #336666; font-weight: bold'>${LoginOK.point}</span><a
							href="memberarea"><img src="<c:url value="/images/point.png"/>" alt=""></a></li>

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
						<li style="font-weight: 400px; color: red;" id='cartSubtotal'>$
							${cartSubtotal}</li>
						<li>
							<div class="dropdown">
								<a href="<c:url value='/shoppingCart/showCartContent'/>"> <i
									class="fa fa-shopping-bag dropbtn"></i><span id='cartContent'>${cartContent}</span>
								</a>
								<div class="dropdown-content" id='showCart'>
									<c:forEach var="cart" items="${ShoppingCart.content}">
										<a
											href="<spring:url value='/productDisplay/product?id=${cart.value.productId}' />">
											<table>
												<tr>
													<td width='80'><img width='70'
														src="<c:url value='/productMaintain/getBookImage?id=${cart.value.productId}' />"></td>
													<td><div style='padding-left: 3px'>
															${cart.value.productName} <br>價格：${cart.value.unitPrice}<br>數量：${cart.value.quantity}
														</div></td>
												</tr>
											</table>
										</a>
										
										<div class="row justify-content-end"
											style='padding-right: 30px'>
											<div class="product__details__quantity">
												<div class="quantity">
													<div class="pro-qty" style='width: 120px;height:30px'>
														<input type="text" name="qty" value="1" style='width: 30px'
															id="qty${cart.value.productId}">
													</div>
												</div>
											</div>
											<Input type='hidden' name='productId'
												id="id${cart.value.productId}"
												value='${cart.value.productId}'>

											<button class="primary-btn"
												onclick="addToCart(${cart.value.productId})"
												style="margin-left: 8px; border-radius: 30px">
												<i class="fa fa-shopping-cart"></i>
											</button>
											<button style='border-radius:50px;margin-left:16px' onclick="confirmDelete(${cart.value.productId})"><i class="fa fa-trash-o"></i></button>	
										</div>
									</c:forEach>
								</div>
						</li>

						<c:choose>
							<c:when test="${MyFavorite.itemNumber > 0}">
								<c:set var="heartContent" value="${MyFavorite.itemNumber}" />
							</c:when>
							<c:otherwise>
								<c:set var="heartContent" value="0" />
							</c:otherwise>
						</c:choose>

						<li><a href="<c:url value='/showFavorite' />"><i
								class="fa fa-heart"></i> <span id='heartContent'>${heartContent}</span></a></li>
						<li><div></div></li>

					</ul>
				</div>

			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-lg-2">
				<div class="header__logo">
					<a href="<c:url value='/' />"><img src="<c:url value="/images/logo5.png"/>" alt=""></a>
				</div>
			</div>
			<div class="col-lg-10">
				<nav class="header__menu">
					<ul>

						<li><a href="<c:url value="/memberarea"/>">會員</a></li>
						<li><a href="<c:url value='/productDisplay/productAll' />">商城</a>
							<ul class="header__menu__dropdown">
								<li><a href="<c:url value='/productDisplay/productAll' />">所有商品</a></li>
								<li><a
									href="<c:url value='/shoppingCart/showCartContent' />">我的購物車</a></li>
								<li><a href="<c:url value='/orderProcess/orderList' />">我的訂單</a></li>
								<li><a href="<c:url value='/showFavorite' />">我的收藏</a></li>
							</ul></li>

						<li><a href="<c:url value="/coachs" />">個人教練預約</a>
							<ul class="header__menu__dropdown">
								<li><a href="<c:url value="/showBookingList" />">預約查詢</a></li>
							</ul></li>
						<li><a href="<c:url value="/courses" />">團體課程</a>
							<ul class="header__menu__dropdown">
								<li><a href="<c:url value="/courses" />">課程列表</a></li>
								<li><a href="<c:url value="/mycourses" />">預約查詢</a></li>
							</ul></li>
						<li><a href="<c:url value="showAllMealList" />">健身餐點</a>
							<ul class="header__menu__dropdown">
								<li><a href="<c:url value="showOrderList" />">我的餐點</a></li>
							</ul></li>
						<li><a href="<c:url value='/news'/>">教學區</a>
							<ul class="header__menu__dropdown">
								<!-- 								<li><a href="./shop-details.html">文章</a></li> -->
								<li><a href=<c:url value='/news'/>>影片教學區</a></li>
							</ul></li>
						<li><a href="<c:url value="/messages" />">健身論壇</a></li>
						<li><a href="#" style='display: none'
							id="topBackstage">後台管理</a>
							<ul class="header__menu__dropdown">
<!-- 								<li><a href="./shop-details.html">會員</a></li> -->
								<li><a href="<c:url value='/productMaintain/productAll' />">商品</a></li>
								<li><a href="<c:url value='/orderProcess/orderListAll' />">訂單</a></li>
								<li><a href="<c:url value='/coachMaintain' />">教練</a></li>
								<li><a href="<c:url value='/coachPerformance' />">教練業績</a></li>
								<li><a href="<c:url value="/courseManage" />">團課</a></li>
								<li><a href="<c:url value='/coursesPerformance' />">團課業績</a></li>
								<li><a href="updateShowAllMealList">健身餐</a></li>
								<li><a href="<c:url value='/newsmodify'/>">教學區</a></li>
								<li><a href="<c:url value='/reports'/>">論壇</a></li>

							</ul></li>

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
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous">
	
</script>
<script type="text/javascript">
	//======jQuery開始===========================
	$(function() {
		checkLogin = "${LoginOK}";
		console.log("${LoginOK}");
		if (checkLogin != "") {
			$("#topregister").remove();
			$("#toplogin").remove();
			$("#headerBoxTop")
					.prepend(
							"<span style='color:#336666;font-weight:bold'>Welcome♥ ${LoginOK.username}</span>")
			$("#toplogout").css("display", "initial")
		} else {
		}
		;
		//下一個jQuery事件可以加在這後面=======================================================
		checkBackstage = "${member_type}";
		if (checkBackstage == 1) {
			$("#topBackstage").css("display", "initial")
		} else {
		}
		;
	})
</script>
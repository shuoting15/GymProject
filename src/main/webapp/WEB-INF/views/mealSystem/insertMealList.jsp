<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<link
	href="https://fonts.googleapis.com/css?family=Rajdhani:300,400,500,600,700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="http://cdn.bootstrapmb.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/all.min.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/lightcase.css">
<link rel="stylesheet" href="css/flaticon.css">
<link rel="stylesheet" href="css/swiper.min.css">

<link rel="shortcut icon" href="images/meal/favicon.png"
	type="image/x-icon">
<link rel="stylesheet" href="css/stylemeal.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<style type="text/css">
span.error {
	color: red;
	display: inline-block;
	font-size: 5pt;
}
</style>
<title>餐點新增</title>
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
				<span class="shape-wrapper"><span class="shape"></span>Meal
					Information<span class="shape"></span></span>
			</h3>
		</div>
	</section>
	<div class="breadcrumb-section1">
		<div class="container">
			<div class="breadcrumb-wrapper">
				<div class="breadcrumb-title">
					<h6 class="title">新增餐點</h6>
				</div>
				<ul class="breadcrumb"
					style="background: transparent; margin: -5px -10px; padding: 0;">
					<li><a href="updateShowAllMealList">GoBackToUpdateBealList</a></li>
					<li><a href="mealSystem">HOME</a></li>
					<li>${mealList.mealName}</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- SIDE BAR -->

	<!-- Four Not Four Section Starts Here -->
	<section
		class="login-section registration-section padding-bottom padding-top">
		<div class="container">
			<div class="login-wrapper">
				<h3 class="title">新增餐點資料(MealList)</h3>
				<form:form class="login-form registration-form" method="POST"
					modelAttribute="add" enctype='multipart/form-data'><br>
					<div class="form-group">
						<h5 class="title">餐點名稱：</h5>
						<form:input path='mealName' />
						<br>&nbsp;
						<form:errors path='mealName' cssClass="error" />
					</div>
					<div class="form-group">
						<h5 class="title">餐點價格：</h5>
						<form:input path='mealPrice' />
						<br>&nbsp;
						<form:errors path='mealPrice' cssClass="error" />
					</div>
					<div class="form-group">
						<h5 class="title">餐點熱量：</h5>
						<form:input path="mealKcal" />
						<br>&nbsp;
						<form:errors path='mealKcal' cssClass="error" />
					</div>
					<div class="form-group">
						<h5 class="title">分類餐點：</h5>
						<form:select path="mealCategoryBean.categoryId">
							<form:option value="-1" label="請挑選" />
							<form:options items="${menuList}" itemLabel='categoryName'
								itemValue='categoryId' />
						</form:select><br>&nbsp; <form:errors path="mealCategoryBean"
							cssClass="error" />
					</div>
					<div class="form-group">
						<h5 class="title">餐點內容：</h5>
						<form:textarea
							class="form-group" rows="20" path="mealContent" /><br>&nbsp;
						<form:errors path='mealContent' cssClass="error" />
					</div>
					<div class="form-group">
						<h5 class="title">餐點照片：</h5>
						<img id="blah" width='200' src="<c:url value='' />" />
						<form:input runat="server" id="imgInp" path="productImage"
							type='file' /><br>&nbsp; <form:errors path="productImage"
							cssClass="error" />
					</div>
					 <div class="form-group">
                        <input type="submit" value="新增餐點" class="m-0">
                    </div>
				</form:form>
			</div>
		</div>
	</section>
	<!-- Four Not Four Section Ends Here -->
	<br>

	<script type='text/javascript'>

	</script>


</body>
</html>
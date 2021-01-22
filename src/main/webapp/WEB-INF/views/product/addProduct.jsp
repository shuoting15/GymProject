<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Product</title>

<!-- Bootstrap -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" -->
<!-- 	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" -->
<!-- 	crossorigin="anonymous"> -->

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="../css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="../css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="../css/nice-select.css" type="text/css">
<link rel="stylesheet" href="../css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="../css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet" href="../css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="../css/style.css" type="text/css">
<style>
</style>
<script type="text/javascript">
	function readURL(input) {
		if (input.files && input.files[0]) {
			var imageTagID = input.getAttribute("targetID");
			var reader = new FileReader();
			reader.onload = function(e) {
				var img = document.getElementById(imageTagID);
				img.setAttribute("src", e.target.result)
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	
	function setInput(){
		document.getElementsByName("productName")[0].value="【附膝軟墊】兩用健腹輪"
		document.getElementsByName("productPrice")[0].value=250
		document.getElementsByName("discount")[0].value=1
		document.getElementsByName("productInStock")[0].value=35
		document.getElementsByName("productDescription")[0].value="以膝蓋或腳尖著地進行穩定或不穩定模式訓練，鍛鍊腹橫肌、腹直肌、腹斜肌等等。"
	}
	
</script>
</head>
<body>
	<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/top.jsp" />
	<hr />
	<!-- 回商品列表 -->
	<div class="container" style="max-width:860px;margin-bottom:50px">
		<a class="btn btn-primary"
			href="<c:url value='/productMaintain/productAll' />" role="button">返回編輯商品列表</a>
		<hr>

		<!-- 新增商品欄位 -->
        
		<form:form method="post" modelAttribute="productBean"
			enctype="multipart/form-data">
           
			<div class="row">
				<div class="col-lg-6 col-md-6">
					<div class="product__details__pic">
						<div class="form-group">
							<p style="color:black">商品圖片</p> 
							<div class="product__details__pic__item">
							<img class="product__details__pic__item--large"
								id="preview_progressbarTW_img" src="#" />
								</div>
							<form:input path="productImage" type='file'
								class="form-control-file" onchange="readURL(this)"
								targetID="preview_progressbarTW_img"
								accept="image/gif, image/jpeg, image/png" />
						</div>
					</div>
				</div>

				<div class="col-lg-6 col-md-6">
					<div class="row">
						<div class="col">
						<button type="button" onclick="setInput()">✎ </button>
							<label for="productName">商品名稱</label>
							<form:input path="productName" class="form-control" type="text" />
							<small id="textHelp" class="form-text text-muted"></small>
						</div>
					</div>
					<div class="row">
						<div class="col" style="margin-bottom:15px">
						<p style="color:black">商品類別</p>
<!-- 							<label for="productCategory">商品類別</label> -->
							<form:select path='productCategory' class="custom-select">
								<form:option value="-1" label="請挑選" />
								<form:options items="${category}" />
							</form:select>
				
							<small id="textHelp" class="form-text valid-feedback"></small>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<label for="productPrice">價格</label>
							<form:input path="productPrice" class="form-control" type="text" />
							<small id="emailHelp" class="form-text text-muted"></small>
						</div>
						<div class="col">
							<label for="discount">折價</label>
							<form:input path="discount" class="form-control" type="text" />
							<small id="textHelp" class="form-text text-muted"></small>
						</div>

					</div>
					<div class="row">
						<div class="col">
							<label for="productInStock">欲上架數量</label>
							<form:input path="productInStock" class="form-control"
								type="text" />
							<small id="textHelp" class="form-text text-muted"></small>
						</div>
						<div class="col">
							<label for="productDate">上架日期</label>
							<form:input type="date" path="productDate" class="form-control" />
							<small id="emailHelp" class="form-text text-muted"></small>
						</div>

					</div>

					<div class="form-group">
						<label for="productDescription">商品描述</label>
						<form:textarea path="productDescription" class="form-control-file"
							rows="4" cols="5" />
					</div>

					<!--   <input type="submit" class="btn btn-primary" value="Submit">  -->
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</div>
		</form:form>
		<br>
		<br>
	</div>

	<!-- Js Plugins -->
	<script src="../js/jquery-3.3.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/jquery.nice-select.min.js"></script>
	<script src="../js/jquery-ui.min.js"></script>
	<script src="../js/jquery.slicknav.js"></script>
	<script src="../js/mixitup.min.js"></script>
	<script src="../js/owl.carousel.min.js"></script>
	<script src="../js/main.js"></script>

	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>

</body>
</html>
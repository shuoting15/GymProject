<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Product</title>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="/mvcExercise/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="/mvcExercise/css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="../css/nice-select.css" type="text/css">
<link rel="stylesheet" href="/mvcExercise/css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="../css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet" href="../css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="/mvcExercise/css/style.css" type="text/css">

</head>
<body>
<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/top.jsp" />
	<hr />
	
	<!-- 回商品列表 -->
	<div class="container">
		<a class="btn btn-primary"
			href="<c:url value='/productMaintain/productAll' />" role="button">返回編輯商品列表</a>
		<hr>

		<form:form method="post" modelAttribute="productBean"
			enctype="multipart/form-data">
			<form:hidden path="productId" />
			<div class="row">
				<div class="col">
					<label for="productName">商品名稱</label>
					<form:input path="productName" class="form-control" />
					<small id="textHelp" class="form-text text-muted"></small>
				</div>
				<div class="col">
					<label for="productCategory">商品類別</label>
					<form:select path='productCategory' class="custom-select">
						<form:option value="0" label="請挑選" />
						<form:options items="${category}" />
					</form:select>
					<small id="textHelp" class="form-text valid-feedback"></small>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<label for="productPrice">價格</label>
					<form:input path="productPrice" class="form-control" />
					<%--     <input type="text" class="form-control" id="productPrice" name="productPrice" value="${product.productPrice}" required> --%>
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
					<label for="productInStock">庫存數量</label>
					<form:input path="productInStock" class="form-control" />
					<%--     <input type="text" class="form-control" id="productQuantityAdd"name="productInStock" value="${product.productInStock}" required> --%>
					<small id="textHelp" class="form-text text-muted"></small>
				</div>
				<div class="col">
					<label for="productDate">上架日期</label>
					<form:input type="date" path="productDate" class="form-control" />
					<%--     <input type="date" class="form-control" id="productAddTime" name="productAddTime" value="${product.productDate}" required> --%>
					<small id="emailHelp" class="form-text text-muted"></small>
				</div>

			</div>
			<div class="form-group">
				<label for="productDescription">商品描述</label>
				<form:textarea path="productDescription" class="form-control-file"
					rows="4" cols="5" />
			</div>

			<div class="form-group">
				<label for="productImage">商品圖片</label>
				<div>
					<img height='120' width='96'
						src='<c:url value='/productMaintain/getBookImage?id=${productBean.productId}' />' />
				</div>
				<div style="margin-top: 8px">
					<form:input path="productImage" type='file'
						class="form-control-file" />
				</div>
				<%-- 			    <input type="file" class="form-control-file" id="productImg" name="productImg" value="${product.productImg}"> --%>
			</div>
			<button type="submit" class="btn btn-primary">Modify</button>
		</form:form>
		
		<br>
		<br>
	</div>

<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/footer.jsp" />
	
	
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
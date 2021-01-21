<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GYM|我的收藏</title>
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

<script type="text/javascript">
function confirmDelete(n) {
	if (confirm("確定將此項商品移除收藏 ? ") ) {
		document.forms[0].action="<c:url value='/deleteFavorite?productId=" + n +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
	
	}
}
</script>

</head>
<body>
	<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/top.jsp" />
<hr>
<div class="container">
<h4 style="margin-bottom:16px">我的收藏</h4>
<h6> ${noFavMsg}</h6>
<div class="row" >
	<c:forEach varStatus="vs" var="entry" items="${MyFavorite.content}">
	<div class="col-lg-3 col-md-6 col-sm-6">
		<div class="card" style="width: 18rem;height:420px;margin:8px;">
			<img
				src="<c:url value='/productMaintain/getBookImage?id=${entry.value.productId}' />"
				class="card-img-top" alt="...">
			<div class="card-body">
				<h5 class="card-title">${entry.value.productName}</h5>
				<a href="<spring:url value='/productDisplay/product?id=${entry.value.productId}' />" class="btn btn-primary" style="background: #7fad39;border-color:#7fad39">商品細節</a> 
				<span style="padding-left:16px"><i class="fa fa-trash-o" style="color:gray" onclick="confirmDelete(${entry.key})"></i></span>
			</div>
		</div>
		</div>
	</c:forEach>
	</div>
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
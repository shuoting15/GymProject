<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <jsp:include page="../index.jsp"/>  --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編輯商品列表</title>
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
/* .container {
	max-width: 1000px;
} */
.table th, .table td {
text-align: center;
vertical-align: middle!important;
}

</style>
<script type="text/javascript">
function confirmDelete(id) {
	if (confirm("確定刪除此項商品資料(編號:"+id+")?") ) {
		document.location.href= "<c:url value='/productMaintain/delete?id="+id+"' />";
	} else {
	}
}
</script>

</head>
<body>
	<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/top.jsp" />
<hr/>
	<div class="container">
	<a class="btn btn-primary"
			href="<c:url value='/productMaintain/productAll' />" role="button">返回編輯商品列表</a>
		<hr>

		<div class="row justify-content-between">
			<!-- 搜尋 -->
			<div class="blog__sidebar__search" style="margin-bottom:20px">
                            <form action="<c:url value="/productMaintain/productFuzzy"/>" method="post">
                                <input type="text" placeholder="Search..." name="keyword">
                                <button type="submit"><span class="icon_search"></span></button>
                            </form>
                        </div>
			
			<!-- 新增商品 -->
			<div >
				<a class="btn btn-primary"
					href="<c:url value='/productMaintain/insert' />" role="button">新增商品</a>
			</div>
		</div>


		<!-- 商品列表與修改刪除 -->
		<!-- 是否要做pageNo? -->
		<table  class="table table-hover">			
			<thead><tr>
				<th>商品編號</th><th>圖片</th><th>品名</th><th>類別</th><th>價錢</th><th>折扣</th>
				<th>庫存</th><th>上架日期</th><th>描述</th><th>編輯</th>
			</tr></thead>
			  <tbody>
			<c:forEach var="product" items="${products}">
				<tr>
					<td>${product.productId}</td>
					<td width='100'><img height='70' width='120'
						src='<c:url value='/productMaintain/getBookImage?id=${product.productId}' />'>
					</td>
					<td>${product.productName}</td>
					<td>${product.productCategory}</td>
					<td>${product.productPrice}</td>
					<td>${product.discount}</td>
					<td>${product.productInStock}</td>
					<td>${product.productDate}</td>
					<td>${product.productDescription}</td>
					<td><a
						href="<c:url value='/productMaintain/updateAny/${product.productId}' />"
						id="modify" name="modify"><i class='fa fa-pencil-square-o' style='color: gray'></i></a> 
					<a
						href="<c:url value='/productMaintain/delete?id=${product.productId}' />"
						id="delete"
						onclick="confirmDelete(${product.productId})"><i
							class='fa fa-trash-o' style="color: gray"></i></a></td>
				</tr>
			</c:forEach>
			  </tbody>
		</table>
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
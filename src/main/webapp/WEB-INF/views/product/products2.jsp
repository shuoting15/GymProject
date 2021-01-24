<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ＧＹＭ｜編輯商品列表</title>
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
	vertical-align: middle !important;
}
</style>
<script type="text/javascript">

	var pageNo = 0;
	var totalPage = 0;
	fetch("<c:url value='/productMaintain/pageProductList' />").then(
			function(response) {
				return response.text();
			}).then(function(data) {
				console.log(data)
				displayPageProducts(data);				
	})
	//當使用者按下『第一頁』、『前一頁』、『下一頁』、『最末頁』的連結時，由本方法發出非同步請求。
	function asynRequest(id) {
		var xhr = new XMLHttpRequest();
		var no = 0;
		var queryString = ""; // queryString紀錄查詢字串
		if (id == "first") { // 算出查詢字串中，要送出的pageNo為何?
			no = 1;
		} else if (id == "prev") {
			no = pageNo - 1;
		} else if (id == "next") {
			no = pageNo + 1;
		} else if (id == "last") {
			no = totalPage;
		}
		// 查詢字串包含1.即將要讀取的頁數(pageNo), 2.總共有幾頁(totalPage)
		// 注意，查詢字串的前面有問號
		queryString = "?pageNo=" + no + "&totalPage=" + totalPage;

		xhr.open("GET", "<c:url value='/productMaintain/pageProductList' />"
				+ queryString, true);
		xhr.send();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var responseData = xhr.responseText;
				displayPageProducts(responseData);
			}
		}
	}

	function displayPageProducts(responseData) {
		var content = "<table  class='table table-hover'><thead><tr><th>商品編號</th><th>圖片</th>"
		content += "<th>品名</th><th>類別</th><th>價錢</th><th>折扣</th><th>庫存</th><th>上架日期</th><th>描述</th><th>編輯</th></tr></thead><tbody>";

		var mapData = JSON.parse(responseData);
		pageNo = mapData.currPage;
		totalPage = mapData.totalPage;
		var products = mapData.list;
		var imageURL = "<c:url value='/productMaintain/getBookImage' />";
		for (var i = 0; i < products.length; i++) {
			content += "<tr><td>"
					+ products[i].productId
					+ "&nbsp;</td>"
					+ "<td width='100'><img  width='70' height='120' "
					+ " src='"
					+ imageURL
					+ "?id="
					+ products[i].productId
					+ "'></td>"
					+ "<td>"
					+ products[i].productName
					+ "</td>"
					+ "<td>"
					+ products[i].productCategory
					+ "</td>"
					+ "<td>"
					+ products[i].productPrice
					+ "&nbsp;</td>"
					+ "<td>"
					+ products[i].discount
					+ "</td>"
					+ "<td>"
					+ products[i].productInStock
					+ "</td>"
					+ "<td>"
					+ products[i].productDate
					+ "</td>"
					+ "<td>"
					+ products[i].productDescription
					+ "</td>"
					+ "<td><a href='<c:url value='/productMaintain/updateAny/"+products[i].productId+"' />' id='modify' name='modify'>"
					+ "<i class='fa fa-pencil-square-o' style='color: gray'></i></a>"
					+ "<a href='<c:url value='/productMaintain/delete?id="+products[i].productId+"' />' id='delete'"
					+ "onclick='confirmDelete("+products[i].productId+")'>"
					+ "<i class='fa fa-trash-o' style='color: gray'></i></a></td>"
					+ "</tr>";
		}
		content += "</table>";
		document.getElementById("display").innerHTML = content;

		var navContent = "<div class='product__pagination' style='margin-bottom:auto;text-align: center'>";
		var prePage = pageNo-1
		var nextPage=pageNo+1
		if (pageNo != 1) {
			
			navContent += "<td width='80' align='center'><a id='prev'><i class='fa fa-long-arrow-left'></i></a></td>";
			navContent += "<td width='80' align='center'><a id='first'>"+prePage+"</a></td>";
		} else {
			navContent += "<td width='80' align='center'>&nbsp;</td>";
			navContent += "<td width='80' align='center'>&nbsp;</td>";
		}
		navContent += "<td width='200' align='center'><a>"+ pageNo + "</a></td>";
		if (pageNo != totalPage) {
			navContent += "<td width='80' align='center'><a id='last'>"+nextPage+"</a></td>";
			navContent += "<td width='80' align='center'><a id='next'><i class='fa fa-long-arrow-right'></i></a></td>";
			
		} else {
			navContent += "<td width='80' align='center'>&nbsp;</td>";
			navContent += "<td width='80' align='center'>&nbsp;</td>";
		}
		navContent +="</div>"
		document.getElementById("nav").innerHTML = navContent;
		var firstBtn = document.getElementById("first");
		var prevBtn = document.getElementById("prev");
		var nextBtn = document.getElementById("next");
		var lastBtn = document.getElementById("last");
		if (firstBtn != null) {
			firstBtn.onclick = function() {
				asynRequest(this.id);
			}
		}

		if (prevBtn != null) {
			prevBtn.onclick = function() {
				asynRequest(this.id);
			}
		}

		if (nextBtn != null) {
			nextBtn.onclick = function() {
				asynRequest(this.id);
			}
		}

		if (lastBtn != null) {
			lastBtn.onclick = function() {
				asynRequest(this.id);
			}
		}
	}

	function confirmDelete(no, id) {
		if (confirm("確定刪除此項商品資料(編號:" + no + ")?")) {
			document.location.href = "<c:url value='/productMaintain/delete?id="+id+"' />";
		} else {
		}
	}
</script>

</head>
<body>
	<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/top.jsp" />
	<hr />
	<div class="container">

		<div class="row justify-content-between">
			<!-- 搜尋 -->
			<div class="blog__sidebar__search" style="margin-bottom: 20px">
				<form action="/mvcExercise/productMaintain/productFuzzy"
					method="post">
					<input type="text" placeholder="Search..." name="keyword">
					<button type="submit">
						<span class="icon_search"></span>
					</button>
				</form>
			</div>

			<!-- 新增商品 -->
			<div>
				<a class="btn btn-primary"
					href="<c:url value='/productMaintain/insert' />" role="button">新增商品</a>
			</div>
		</div>


		<!-- 商品列表與修改刪除 -->
		<div id="display"></div>
		<!-- page -->
		<div id="nav"></div>
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
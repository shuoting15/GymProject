<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ＧＹＭ</title>
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

<style type="text/css">
body {
	margin: 0px;
	padding: 0px auto;
}
</style>

<script type="text/javascript">
	window.onload = function() {
       document.querySelector("select").addEventListener("click",function(){
    	   document.querySelector("#nice-select").classList.add("open");
       })
		
		//sortBy Ajax
		var selectElement = document.getElementById('sort');
		selectElement.onchange = function() {
			var sortBy = selectElement.options[selectElement.selectedIndex].value;
			if (sortBy === "0") {
				window.location
						.assign("<c:url value='/productDisplay/productAll' />");
			} else {
				fetch("<c:url value='/productDisplay/sort?by=" + sortBy + "'/>")
						.then(function(response) {
							console.log(response);
							return response.json();
						}).then(function(data) {
							console.log(data);
							display(data);
						})
			}
		}

		function display(data) {
			var product = data.sort
			var content = ""
			var imageURL = "<c:url value='/productMaintain/getBookImage' />";
			for (var i = 0; i < product.length; i++) {
				content += "<div class='col-lg-4 col-md-6 col-sm-6'><div class='product__item'><div class='product__item__pic set-bg'"
				content += "data-setbg='" + imageURL + "?id="
						+ product[i].productId + "'>"
				content += "<img  width='70' height='260' " + " src='"
						+ imageURL + "?id=" + product[i].productId + "'>"
				content += "<ul class='product__item__pic__hover'>"
				content += "<li><a><i class='fa fa-heart' onclick='addToFav("+product[i].productId+")'></i></a></li></ul></div>"
				content += "<div class='product__item__text'>"
				content += "<h6><a href='<spring:url value='/productDisplay/product?id="
						+ product[i].productId
						+ "' />'>"
						+ product[i].productName + "</a></h6>"
				if (product[i].discount !== 1) {
					content += "<h5><span style='text-decoration: line-through; color: gray'> $"
							+ product[i].productPrice + "</span>"
					content += "<span style='color: red;'> $"
							+ product[i].productPrice * product[i].discount
							+ "</span></h5>"
				} else {
					content += "<h5> $" + product[i].productPrice + "</h5>"
				}
				content += "</div>"

				content += "<div class='container'><div class='row justify-content-center'>"
				content += "<select name='qty' id='qty"+product[i].productId+"'><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select>"
				content += "<Input type='hidden' name='productId' id='id"+product[i].productId+"' value='"+product[i].productId+"'>"
				content += "<Input type='hidden' name='pageNo' value='param.pageNo'>"
				content += "<button class='primary-btn' onclick='addToCart("+product[i].productId+")' style='margin-left: 8px;border-radius:30px'><i class='fa fa-shopping-cart'></i></button>"
				content += "</div></div></div></div>"
			}
			document.getElementById("display").innerHTML = content
		}
	}
	
	function addToCart(id){
		var i="id"+id;
		var q="qty"+id;
		var id=document.getElementById(i).value;
		var qty=document.getElementById(q).value;
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "<c:url value='/buy'/>"+"?productId="+id+"&qty="+qty,true);
		xhr.send();
		
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 &&(xhr.status==200)){
				alert(xhr.responseText)
				var result=JSON.parse(xhr.responseText)
				alert(result)
				alert(result.msg);
				
				if(Object.keys(result).length>1){
				var keyset=Object.keys(result.cart)
				var cart=""
				for(let i=0;i<keyset.length;i++){					
// 					cart+="<a href='#'><div class='container'><div class='row'>"
// 					cart+="<div><img width='70' src='<c:url value='/productMaintain/getBookImage?id="+result.cart[keyset[i]].productId+"' />'></div>"		
// 					cart+="<div style='padding-left:3px'> "+result.cart[keyset[i]].productName+" <br>價格："+ result.cart[keyset[i]].unitPrice+"<br>數量："+ result.cart[keyset[i]].quantity+"</div></div></div></a>"
					cart+="<a href='#'><table><tr>"
					cart+="<td width='80'><img width='70' src='<c:url value='/productMaintain/getBookImage?id="+result.cart[keyset[i]].productId+"' />'><td>"
					cart+="<td><div style='padding-left: 3px'>"+result.cart[keyset[i]].productName+" <br>價格："+ result.cart[keyset[i]].unitPrice+"<br>數量："+ result.cart[keyset[i]].quantity+"</div></td>"
					cart+="</tr></table></a>"
				}
				document.getElementById('cartContent').innerHTML=result.cartContent;
				document.getElementById('cartSubtotal').innerHTML='$'+result.cartSubtotal;
				document.getElementById('showCart').innerHTML=cart;
				}
			}
		}		
	}
	
	function addToFav(id){
		var i="id"+id;		
		var id=document.getElementById(i).value;
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "<c:url value='/addFavorite'/>"+"?productId="+id,true);
		xhr.send();
		
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 &&(xhr.status==200)){
				var result=JSON.parse(xhr.responseText)
				alert(result[0]);
				document.getElementById('heartContent').innerHTML=result[1];		
			}
		}		
	}
</script>

</head>
<body>

	<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/top.jsp" />

	<!-- Breadcrumb Section Begin -->
	<section class="breadcrumb-section set-bg"
		data-setbg="../images/shop-setBack.png" style="height: 320px;">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="breadcrumb__text">
						<h2>Organi Shop</h2>
						<div class="breadcrumb__option">
							<a href="./index.html">Home</a> <span>Shop</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Breadcrumb Section End -->

	<!-- Main Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-3 col-md-5">
				<div class="sidebar">

					<div class="sidebar__item" style="margin-top: 35px">
						<div class="blog__sidebar__search">
							<form action="/mvcExercise/productDisplay/productFuzzy"
								method="post">
								<input type="text" placeholder="Search..." name="keyword">
								<button type="submit">
									<span class="icon_search"></span>
								</button>
							</form>
						</div>

						<div class="blog__sidebar__item">
							<h4>Categories</h4>
							<ul>
								<li><a href="<c:url value='/productDisplay/productAll' />">全部商品</a></li>
								<c:forEach var="category" items="${categoryLst}">
									<li><a
										href="<c:url value='/productDisplay/${category}' />">${category}</a>
									</li>
								</c:forEach>
							</ul>
						</div>

					</div>
					<div class="sidebar__item">
						<h4>Price</h4>
						<div class="price-range-wrap">
							<div
								class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"
								data-min="10" data-max="540">
								<div class="ui-slider-range ui-corner-all ui-widget-header"></div>
								<span tabindex="0"
									class="ui-slider-handle ui-corner-all ui-state-default"></span>
								<span tabindex="0"
									class="ui-slider-handle ui-corner-all ui-state-default"></span>
							</div>
							<div class="range-slider">
								<div class="price-input">
									<input type="text" id="minamount"> <input type="text"
										id="maxamount">
								</div>
							</div>
						</div>
					</div>
					<div class="sidebar__item">
						<div class="latest-product__text">
							<h4>Latest Products</h4>
							<div class="latest-product__slider owl-carousel">
								<div class="latest-prdouct__slider__item container">
									<c:forEach var="i" begin="0" end="2" step="1">
										<a
											href="<spring:url value='/productDisplay/product?id=${latestProducts[i].productId}' />"
											class="latest-product__item">
											<div class="latest-product__item__pic  col-lg-6">
												<img height="20px" width="20px"
													src="<c:url value='/productMaintain/getBookImage?id=${latestProducts[i].productId}' />"
													alt="">
											</div>
											<div class="latest-product__item__text col-lg-6">

												<h6>${latestProducts[i].productName}</h6>
												<c:if test="${latestProducts[i].discount != 1}">
													<span style="text-decoration: line-through; color: gray">${latestProducts[i].productPrice}</span>
													<span style="color: red;">
														${latestProducts[i].productPrice*latestProducts[i].discount}</span>
												</c:if>
												<c:if test="${latestProducts[i].discount == 1}">
													<span>${latestProducts[i].productPrice}</span>
												</c:if>

											</div>
										</a>
									</c:forEach>
								</div>
								<div class="latest-prdouct__slider__item">
									<c:forEach var="i" begin="3" end="5" step="1">
										<a href="#" class="latest-product__item">


											<div class="latest-product__item__pic col-lg-6">
												<img
													src="<c:url value='/productMaintain/getBookImage?id=${latestProducts[i].productId}' />"
													alt="">
											</div>
											<div class="latest-product__item__text col-lg-6">
												<h6>${latestProducts[i].productName}</h6>
												<c:if test="${latestProducts[i].discount != 1}">
													<span style="text-decoration: line-through; color: gray">${latestProducts[i].productPrice}</span>
													<span style="color: red;">
														${latestProducts[i].productPrice*latestProducts[i].discount}</span>
												</c:if>
												<c:if test="${latestProducts[i].discount == 1}">
													<span>${latestProducts[i].productPrice}</span>
												</c:if>

											</div>

										</a>
									</c:forEach>

								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
			<div class="col-lg-9 col-md-7">
 				<div class="filter__item"> 
					<div class="row">
						<div class="col-lg-6 col-md-6">
							<div class="filter__sort"> 
								<span>Sort By</span> 
								<select id="sort">
									<option value="0">預設</option>
									<option value="priceAsc">價格(低到高)</option>
									<option value="priceDesc">價格(高到低)</option>
									<option value="newest">新上架</option>
								</select>
							</div>
						</div>
						<div class="col-lg-6 col-md-6">
							<div class="filter__found">
								<h6>
									<span id="productsFound">${productsFound}</span> Products found
								</h6>
							</div>
						</div>
						<!-- 						<div class="col-lg-4 col-md-3"> -->
						<!-- 							<div class="filter__option"> -->
						<!-- 								<span class="icon_grid-2x2"></span> <span class="icon_ul"></span> -->
						<!-- 							</div> -->
						<!-- 						</div> -->
					</div>
				</div>
				<div class="row" id="display">
					<c:forEach var="product" items="${products}">

						<div class="col-lg-4 col-md-6 col-sm-6">
							<div class="product__item">
								<div class="product__item__pic set-bg"
									data-setbg="<c:url value='/productMaintain/getBookImage?id=${product.value.productId}' />">
									<ul class="product__item__pic__hover">
										<li><a><i
												class="fa fa-heart" onclick="addToFav(${product.value.productId})"></i></a></li>

									</ul>
								</div>
								<div class="product__item__text">
									<h6>
										<a
											href="<spring:url value='/productDisplay/product?id=${product.value.productId}' />">${product.value.productName}</a>
									</h6>
									<c:if test="${product.value.discount != 1}">
										<h5>
											<span style="text-decoration: line-through; color: gray">$
												${product.value.productPrice}</span> <span style="color: red;">
												$ ${product.value.productPrice*product.value.discount}</span>
										</h5>
									</c:if>
									<c:if test="${product.value.discount == 1}">
										<h5>$ ${product.value.productPrice}</h5>
									</c:if>

								</div>
								<div class="container">
									<%-- 									<FORM method="post" action="<c:url value='/buy'/>"> --%>
<%-- 									<FORM id="form${product.value.productId}"> --%>
										<div class="row justify-content-center">
											<select name='qty' id="qty${product.value.productId}">
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
											</select>
											<!-- 這些隱藏欄位都會送到後端 -->
											<Input type='hidden' name='productId' id="id${product.value.productId}"
												value='${product.value.productId}'> <Input
												type='hidden' name='pageNo' value='${param.pageNo}'>

											<button class="primary-btn" onclick="addToCart(${product.value.productId})"
												style="margin-left: 8px;border-radius: 30px">
												<i class="fa fa-shopping-cart"></i>
											</button>

										</div>
<!-- 									</FORM> -->
								</div>
							</div>
						
				</div>

					</c:forEach>

				</div>
				<div class="product__pagination" style='text-align: center'>
					<c:if test="${pageNo > 1}">
						<a
							href="<c:url value='/productDisplay/productAll?pageNo=${pageNo-1}' />"><i
							class="fa fa-long-arrow-left"></i></a>
						<a
							href="<c:url value='/productDisplay/productAll?pageNo=${pageNo-1}' />">${pageNo-1}</a>
					</c:if>

					<a
						href="<c:url value='/productDisplay/productAll?pageNo=${pageNo}' />">${pageNo}</a>

					<c:if test="${pageNo!= totalPages}">
						<a
							href="<c:url value='/productDisplay/productAll?pageNo=${pageNo+1}' />">${pageNo+1}</a>
						<a
							href="<c:url value='/productDisplay/productAll?pageNo=${pageNo+1}' />"><i
							class="fa fa-long-arrow-right"></i></a>
					</c:if>
				</div>
			</div>

		</div>
	</div>

	<script type="text/javascript">
		// var selectElement = document.getElementById('sort');
		// selectElement.onchange = function(){
		// 	var sortBy=selectElement.options[ selectElement.selectedIndex ].value;
		// 	if(sortBy==="0"){
		// 		fetch("<c:url value='/productDisplay/productAll' />").then(
		// 				function(response) {
		// 					console.log(response);
		// 					return response.text();
		// 				}).then(function(data) {
		// 					console.log(data);
		// 					display(data);					
		// 		})			
		// 	}else{
		// 		fetch("<c:url value='/productDisplay/sort?by="+ sortBy +"'/>").then(
		// 				function(response) {
		// 					console.log(response);
		// 					return response.json();
		// 				}).then(function(data) {
		// 					console.log(data);
		// 					display(data);

		// 		})	
		// 	}
		// } 

		// function display(data){
		// 	var product=data.sort
		// 	var content=""
		// 	var imageURL = "<c:url value='/productMaintain/getBookImage' />";
		// 	for (var i = 0; i < product.length; i++) {
		// 	var state1,state2
		// 	if(product[i].discount===1){
		// 		state1=true
		// 	}else{
		// 		state1=false
		// 	}
		// 	if(product[i].discount!==1){
		// 		state2=true
		// 	}else{
		// 		state2=false
		// 	}
		// 	content+="<div class='col-lg-4 col-md-6 col-sm-6'><div class='product__item'><div class='product__item__pic set-bg' "
		// 		content+="data-setbg='"+imageURL+"?id="+product[i].productId+ "'>"
		// 		content+="<ul class='product__item__pic__hover'>"
		// 		content+="<li><a href='#'><i class='fa fa-heart'></i></a></li></ul></div>"
		// 		content+="<div class='product__item__text'>"
		// 		content+="<h6><a href='<spring:url value='/productDisplay/product?id="+product[i].productId+"' />'>"+product[i].productName+"</a></h6>"
		// 		content+="<c:if test='"+state2+"'><h5>"
		// 		content+="<span style='text-decoration: line-through; color: gray'>"+product[i].productPrice+"</span>"	
		// 		content+="<span style='color: red;'>"+product[i].productPrice*product[i].discount+"</span></h5></c:if>"	
		// 		content+="<c:if test='"+status1+"'>"
		// 		content+="<h5>"+product[i].productPrice+"</h5></c:if></div>"	
		// 		content+="<div><FORM action='<c:url value='/buyBook' />' method='POST'>"	
		// 		content+="<select name='qty'><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select>"			
		// 		content+="<Input type='hidden' name='productId' value='"+product[i].productId+"'>"
		// 		content+="<Input type='hidden' name='pageNo' value='param.pageNo'>"
		// 		content+="<button type='submit' class='primary-btn' style='margin-left: 8px'><i class='fa fa-shopping-cart'></i></button>"	
		// 		content+="</FORM></div></div></div>"			
		// 	}		
		// 	document.getElementById("display").innerHTML=content
		// }
	</script>

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
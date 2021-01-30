<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

<script type="text/javascript">
function addToCart(id){
	var i="id"+id;
	var q="qty"+id;
	var id=document.getElementById(i).value;
	var qty=document.getElementById(q).value;
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/buyProduct'/>"+"?productId="+id+"&qty="+qty,true);
	xhr.send();
	
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 &&(xhr.status==200)){		
			var result=JSON.parse(xhr.responseText)
			alert(result.msg);
			
			if(Object.keys(result).length>1){
				var keyset=Object.keys(result.cart)
				var cart=""
				for(let i=0;i<keyset.length;i++){					
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
		data-setbg="../images/shop.png" style="height: 320px;">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="breadcrumb__text">
						<h2>GYM SHOP</h2>
						<div class="breadcrumb__option">
							<span>滿三千折100，限時活動中</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Breadcrumb Section End -->


	<!-- Product Details Section Begin -->
	<section class="product-details spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-2 col-md-4">

					<div class="sidebar">
						<div class="sidebar__item">
							<div class="blog__sidebar__item">
								<h4>Categories</h4>
								<ul>
									<li><a href="<c:url value='/productDisplay/productAll' />">全部商品</a></li>
									<c:forEach var="category" items="${categoryLst}">
										<li><a
											href="<c:url value='/productDisplay/${category}' />">${category}</a></li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-5 col-md-4">
					<div class="product__details__pic">
						<div class="product__details__pic__item">
							<img class="product__details__pic__item--large"
								src="<c:url value='/productMaintain/getBookImage?id=${product.productId}' />"
								alt="">
						</div>

					</div>
				</div>
				<div class="col-lg-5 col-md-4">
					<div class="product__details__text">
						<h3>${product.productName}</h3>

						<c:if test="${product.discount != 1}">
							<div class="product__details__price">
								<span style="text-decoration: line-through; color: gray">$<fmt:formatNumber value="${product.productPrice}" pattern="####" /></span>
								<span> $<fmt:formatNumber value="${product.productPrice*product.discount} "  pattern="####" /></span> <span
									style="color: red; font-size: 15px">
									打${product.discount}折</span>
							</div>
						</c:if>
						<c:if test="${product.discount == 1}">
							<div class="product__details__price">$
								<fmt:formatNumber value="${product.productPrice}"  pattern="####" /></div>
						</c:if>

						<p>${product.productDescription}</p>
<%-- 						<FORM action="<c:url value='/buyBook' />" method="POST">  --%>
							<div class="product__details__quantity">
								<div class="quantity">
									<div class="pro-qty">
										<input type="text" name="qty" value="1" id="qty${product.productId}">
									</div>
								</div>
							</div>
							<Input type='hidden' name='productId'
								value='${product.productId}' id="id${product.productId}"> 
								<Input type='submit'
								value='加入購物車' class="primary-btn" onclick="addToCart(${product.productId})">
							<!-- 							<a href="#" -->
							<!-- 								class="primary-btn">ADD TO CARD</a> -->
							<a class="heart-icon"><span class="icon_heart_alt" onclick="addToFav(${product.productId})"></span></a>
<!-- 						</FORM>  -->

						<ul>
							<li><b>庫存</b> <span>${product.productInStock}</span></li>
							<li><b>類別</b> <span>${product.productCategory} </span></li>
							<li><b>相關活動</b> <span>滿三千折一百<samp>Free pickup
										today</samp></span></li>
						</ul>
					</div>
				</div>

			</div>
		</div>
	</section>
	<!-- Product Details Section End -->




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
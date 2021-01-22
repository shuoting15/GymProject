<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ＧＹＭ｜我的購物車</title>
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
function Checkout(qty) {
	if (qty == 0)  {
		alert("無購買任何商品，不需結帳");
		return false;
	}
	if (confirm("再次確認訂單內容 ? ") ) {
		return true;
	} else {
		return false;
	}
}

function confirmDelete(n) {
	if (confirm("確定刪除此項商品 ? ") ) {
		document.forms[0].action="<c:url value='/shoppingCart/UpdateItem?cmd=DEL&productId=" + n +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
	
	}
}
function Abort() {
	if (confirm("確定放棄購物 ? ") ) {
		return true;
	} else {
		return false;
	}
}

function modify(key, qty, index) {
	var x = "newQty" + index;
	var y = "newTotal" + index;
	var newQty = document.getElementById(x).value;
	if  (newQty < 0 ) {
		window.alert ('數量不能小於 0');
		return ; 
	}
	if  (newQty == 0 ) {
		window.alert ("請執行刪除功能來刪除此項商品");
		document.getElementById(x).value = qty;
		return ; 
	}
	if  (newQty == qty ) {
		window.alert ("數量未更動");
		return ; 
	}
	if (confirm("確定將此商品的數量由" + qty + " 改為 " + newQty + " ? ") ) {
		document.forms[0].action="<c:url value='UpdateItem?cmd=MOD&productId=" + key + "&newQty=" + newQty +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
		
	} else {
		document.getElementById(x).value = qty;
	}
}

function  updateItemQty(){
	fetch("<c:url value='/productMaintain/pageProductList' />").then(
			function(response) {
				return response.text();
			}).then(function(data) {
				console.log(data);
				displayPageProducts(data);
				
	})
}

function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57)){
      return false;
   }
   return true;
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

	<!-- Shoping Cart Section Begin -->
	<section class="shoping-cart spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="shoping__cart__table">
						<table>
							<thead>
								<tr>
									<th class="shoping__product">Products</th>
									<th>Price</th>
									<th>Discount</th>
									<th>Quantity</th>
									<th>Total</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach varStatus="vs" var="cartEntry" items="${ShoppingCart.content}">
									<tr>
										<td class="shoping__cart__item"><img height='80' width='80'
											src="<c:url value='/productMaintain/getBookImage?id=${cartEntry.value.productId}' />" alt="">
											<h5>${cartEntry.value.productName}</h5></td>
										<td id="unitPrice" class="shoping__cart__price">
											${cartEntry.value.unitPrice}</td>
										<td id="discount" class="shoping__cart__price">
											${cartEntry.value.discount}</td>
										<td class="shoping__cart__quantity">
											<div class="quantity">
												<div class="pro-qty" >
													<input id="newQty${vs.index}" type="text" value="${cartEntry.value.quantity}"
													onchange="modify(${cartEntry.key}, ${cartEntry.value.quantity}, ${vs.index})"
													>
												</div>
											</div>
										</td>
										<td class="shoping__cart__total" id="newTotal${vs.index}">$
											${cartEntry.value.unitPrice * cartEntry.value.discount * cartEntry.value.quantity}</td>
										<td class="shoping__cart__item__close"><span
											class="fa fa-trash-o" onclick="confirmDelete(${cartEntry.key})"></span>
											<i class='fa fa-pencil-square-o' style='color: gray' 
											onclick="modify(${cartEntry.key}, ${cartEntry.value.quantity}, ${vs.index})"></i>
											</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>					
					</div>
				</div>
			</div>
			<p style="color:red">${errorMsg}</p>
			<div class="row">
				<div class="col-lg-12">
					<div class="shoping__cart__btns">
						<a
							href="<c:url value='/productDisplay/productAll?pageNo=${pageNo}' />"
							class="primary-btn cart-btn">繼續購物</a> 
						<a
							href="<c:url value='/shoppingCart/abort' />"
							class="primary-btn cart-btn" onClick="return Abort();">取消購物</a> 
<!-- 						<a -->
<%-- 							onclick="modify(${anEntry.key}, ${anEntry.value.quantity}, ${vs.index})" --%>
<!-- 							class="primary-btn cart-btn cart-btn-right"><span -->
<!-- 							class="icon_loading"></span> 更新數量</a> -->
					</div>
				</div>
				<div class="col-lg-6">
<!-- 					<div class="shoping__continue"> -->
<!-- 						<div class="shoping__discount"> -->
<!-- 							<h5>Discount Codes</h5> -->
<!-- 							<form action="#"> -->
<!-- 								<input type="text" placeholder="Enter your coupon code"> -->
<!-- 								<button type="submit" class="site-btn">APPLY COUPON</button> -->
<!-- 							</form> -->
<!-- 						</div> -->
<!-- 					</div> -->
				</div>
				<div class="col-lg-6">
					<div class="shoping__checkout">
						<h5>Cart Total</h5>
						<ul>
							<li>Subtotal <span>${ShoppingCart.subtotal}</span></li>
							<li>Total <span>${ShoppingCart.subtotal}</span></li>
						</ul>
						<a href="<c:url value='/shoppingCart/checkout' />"
							class="primary-btn" onClick="return Checkout(${subtotal});">PROCEED
							TO CHECKOUT</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Shoping Cart Section End -->
<form>
   <input type="hidden" name="a"/>
</form>

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
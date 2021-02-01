<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:useBean id="today" class="java.util.Date" scope="session" />
<title>ＧＹＭ｜確認訂單明細資訊</title>
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
	function cancelOrder() {
		if (confirm("確定取消此份訂單 ? ")) {
			// 接收此資料的Servlet會使用 finalDecision 參數的值
			document.forms[0].finalDecision.value = "CANCEL";
			document.forms[0].action = "<c:url value='cancelOrder' />";
			document.forms[0].method = "GET";
			document.forms[0].submit();
			return;
		} else {
			return;
		}
	}
	function reconfirmOrder() {
		var sa = document.getElementById('ShippingAddress').value;
		if (sa === "") {
			window.alert('出貨地址不能是空白');
			return;
		}
		if (confirm("確定送出此份訂單 ? ")) {
			// 接收此資料的Servlet會使用 finalDecision 參數的值
			// 		document.forms[0].finalDecision.value = "ORDER";
			document.forms[0].action = "<c:url value='/shoppingCart/processOrder' />";
			document.forms[0].method = "POST";
			document.forms[0].submit();
			return;
		} else {
			return;
		}
	}

	function checkCode() {
		let code = document.getElementById("code");
		let Vobj1 = document.getElementById("checkCodeMsg");
		let Vobj2 = document.getElementById("amount");
		let Vobj3 = document.getElementById("newTotal");
		let total = document.getElementById("total");

		fetch("<c:url value='/coupon?code=" + code.value + "' />").then(
				function(res) {
					console.log(res)
					return res.json();
				}).then(function(result) {
			console.log(result);
			total.value = result.newTotal;
			Vobj1.innerHTML = "<span>" + result.codeMsg + "</span>";
			Vobj2.innerHTML = "<span>" + "NT." + result.amount + "</span>";
			Vobj3.innerHTML = "<span>" + "NT." + result.newTotal + "</span>";
		});

	}

	// 	function checkCode() {
	//         alert("code")
	// 		let code = document.getElementsById("code");
	// 		let Vobj1 = document.getElementById("checkCodeMsg");
	// 		let Vobj2 = document.getElementById("amount");
	// 		let Vobj3 = document.getElementById("newTotal");
	// 		let total = document.getElementById("total");
	// 		alert("code2")
	// 		let xhr = new XMLHttpRequest();
	// 		xhr.open("POST", "<c:url value='/coupon?' />", true);
	// 		xhr.setRequestHeader("Content-Type",
	// 				"application/x-www-form-urlencoded");
	// 		xhr.send("code=" + code.value);
	// 		let msg = "";
	// 		xhr.onreadystatechange = function() {
	// 			if (xhr.readyState == 4 && xhr.status == 200) {
	//                 alert(xhr.responseText)
	// 				let result = JSON.parse(xhr.responseText);
	/* 					total.value = result.newTotal;
	 Vobj1.innerHTML = "<span>" + result.codeMsg + "</span>";
	 Vobj2.innerHTML = "<span>" + "NT." + result.amount + "</span>";
	 Vobj3.innerHTML = "<span>" + "NT." + result.newTotal
	 + "</span>"; */
	// 			}
	// 		}
	// 	}
	function setInput() {
		document.getElementsByName("memberMail")[0].value = "pantone110304@gmail.com"
// 		document.getElementsByName("ShippingAddress")[0].value = "台北市大安區復興南路一段390號2樓"
		document.getElementsByName("note")[0].value = "宅配送貨前請先聯繫"
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
							<span>折扣碼抽抽樂，限時活動中</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Breadcrumb Section End -->

	<!-- Checkout Section Begin -->
	<section>
		<!-- 	class="checkout spad" -->
		<div class="container">
			<!-- 			<div class="row"> -->
			<!-- 				<div class="col-lg-12"> -->
			<!-- 					<h6> -->
			<!-- 						<span class="icon_tag_alt"></span> Have a coupon? <a href="#">Click -->
			<!-- 							here</a> to enter your code -->
			<!-- 					</h6> -->
			<!-- 				</div> -->
			<!-- 			</div> -->
			<div class="checkout__form">
				<h4>訂單明細</h4>
				<button type="button" onclick="setInput()">✎</button>
				<form action="<c:url value='/shoppingCart/processOrder' />"
					method="post">
					<div class="row">
						<div class="col-lg-6 col-md-6">
							<div class="row">
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											姓名<span>*</span>
										</p>
										<input type="text" name="memberName" value="${LoginOK.username}">
										<%-- 之後用${loginOK.member} --%>
									</div>
								</div>
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											手機<span>*</span>
										</p>
										<input type="text" name="memberPhone" value="${LoginOK.mobile}">
									</div>
								</div>

							</div>
							<div class="row">

								<div class="col-lg-12">
									<div class="checkout__input">
										<p>
											電子信箱<span>*</span>
										</p>
										<input type="text" name="memberMail">
									</div>
								</div>
							</div>
							<div class="row" style='margin-bottom: 20px'>

								<div class="col-lg-12">
									<div>
										<!-- class="checkout__input" -->
										<p style='color:black'>
											付款方式<span>*</span>
										</p>
										<!-- <input type="text" id="payment" name="payment" value="信用卡付款"> -->
										<select id="payment" name="payment" required>
											<option selected disabled value="">請選擇...</option>
											<option value="現金支付">現金支付</option>
											<option value="信用卡付款">信用卡支付</option>
											<option value="點數付款">點數支付</option>
										</select>
									</div>
								</div>
							</div>


							<div class="checkout__input">
								<p>
									出貨地址<span>*</span>
								</p>
								<input type="text" name="ShippingAddress" id="ShippingAddress" value="${LoginOK.address}">
							</div>

							<div class="row">
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>統編</p>
										<input type="text" name="BNO">
									</div>
								</div>
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>發票抬頭</p>
										<input type="text" name="InvoiceTitle">
									</div>
								</div>
							</div>

							<div class="checkout__input">
								<p>備註</p>
								<input type="text" placeholder="有什麼話想跟我們說？" name="note">
							</div>

							<!-- 							<div class="shoping__continue"> -->
							<!-- 								<div class="shoping__discount"> -->
							<!-- 									<h5>Discount Codes</h5> -->
							<!-- 								<form > -->
							<!-- 									<input type="text" id="code" -->
							<!-- 										placeholder="Enter your coupon code"> -->
							<!-- 									<button class="site-btn" onClick="checkCode()">APPLY -->
							<!-- 										COUPON</button> -->
							<!-- 									<p id="checkCodeMsg" -->
							<!-- 										style="color: red; font-weight: blod; font-size: 14px"></p> -->
							<!-- 							</form> -->
							<!-- 								</div> -->
							<!-- 							</div> -->
						</div>

						<div class="col-lg-6 col-md-6">
							<div class="checkout__order">
								<h4>訂單內容</h4>
								<div class="checkout__order__products">
									Products <span>Total</span>
								</div>
								<c:set var="VAT" value="${ShoppingCart.subtotal*0.05 + 0.0001}" />
								<ul>
									<c:forEach var="entry" items="${ShoppingCart.content}">
										<li>${entry.value.productName}<span>${entry.value.unitPrice * entry.value.discount * entry.value.quantity}</span></li>
									</c:forEach>
								</ul>
								<div class="checkout__order__subtotal">
									折扣前 <span>NT. ${ShoppingCart.subtotal}</span>
								</div>
								<!-- 								<div class="checkout__order__total"> -->
								<%-- 									tax <span>$ ${ShoppingCart.subtotal}</span> --%>
								<!-- 								</div> -->
								<div class="checkout__order__total">
									折扣金額<span id="amount">NT.0</span> <br /> 總計 <span
										id="newTotal">NT.<fmt:formatNumber
											value="${ShoppingCart.subtotal}" pattern="#,###,###" /></span> <input
										type="hidden" name="total" id="total"
										value="${ShoppingCart.subtotal}">
								</div>
								<div style="margin-bottom: 15px">
									訂購日期：
									<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" />
								</div>

								<!-- 								<div class="checkout__input__checkbox"> -->
								<!-- 									<label for="payment"> 現金支付 <input type="checkbox" -->
								<!-- 										id="payment"> <span class="checkmark"></span> -->
								<!-- 									</label> -->
								<!-- 								</div> -->
								<!-- 								<div class="checkout__input__checkbox"> -->
								<!-- 									<label for="paypal"> 信用卡支付 <input type="checkbox" -->
								<!-- 										id="paypal"> <span class="checkmark"></span> -->
								<!-- 									</label> -->
								<!-- 								</div> -->
								<button type="submit" class="site-btn">確定送出</button>
								<!-- 								<button type="submit" class="site-btn">CANCEL ORDER</button> -->
								<!-- 								<button type="submit" name="OrderBtn" class="site-btn" value="確定送出" onclick="reconfirmOrder()">確定送出</button> -->
								<!--   								<input type="button" name="CancelBtn" class="site-btn" value="取消訂單" onclick="cancelOrder()"> -->
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="shoping__continue">
				<div class="shoping__discount">
					<h5>Discount Codes</h5>
					<!-- 					<form> -->
					<input type="text" id="code" placeholder="Enter your coupon code">
					<button class="site-btn" onClick="checkCode()">APPLY
						COUPON</button>
					<p id="checkCodeMsg"
						style="color: red; font-weight: blod; font-size: 14px"></p>
					<!-- 					</form> -->
				</div>
			</div>
		</div>
		<br> <br>
	</section>
	<!-- Checkout Section End -->



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
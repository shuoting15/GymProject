<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

function showOrderDetail(no){
	var dropdown = document.getElementById(no)
	if (dropdown.style.display == "none"){
		    dropdown.style.display = 'block';
		  	fetch("<c:url value='/orderProcess/orderDetail?orderNo="+no+"' />").then(
	 	    function(response) {
	 				return response.json();
	        }).then(function(data) {
	 		displayOrderDetail(data,no)
            })
	} else {
		     dropdown.style.display = "none";
    }		 
// 	fetch("<c:url value='/orderProcess/orderDetail?orderNo="+no+"' />").then(
// 			function(response) {
// 				return response.json();
// }).then(function(data) {
// 		displayOrderDetail(data,no)
// })
}

function displayOrderDetail(data,no){
	var imageURL = "<c:url value='/productMaintain/getBookImage' />";
	var content=""
	content+="<table class='table'><thead class='thead-light'><tr>"
	content+="<th scope='col'></th>"
	content+="<th scope='col'>商品名稱</th>"
	content+="<th scope='col'>商品單價</th>"
	content+="<th scope='col'>商品折扣</th>"
	content+="<th scope='col'>訂購數量</th>"
	content+="<th scope='col'>金額小計</th></tr></thead><tbody>"
	for(let i=0;i<data.length;i++){
		content+="<tr><td><img  width='40' height='80' "
			+ " src='"
			+ imageURL
			+ "?id="
			+ data[i].productId
			+ "'> </td>"
		content+="<td>"+data[i].productName+"</td>"
		content+="<td>"+data[i].unitPrice+"</td>"
		content+="<td>"+data[i].discount+"</td>"
		content+="<td>"+data[i].quantity+"</td>"
		content+="<td>"+data[i].unitPrice*data[i].discount*data[i].quantity+"</td></tr>"
	}
	content+="</tbody></table>"
	var display=document.getElementById(no);
	display.innerHTML=content
}

function confirmReturn(no) {
	if (confirm("確定提出退貨申請(訂單編號:" + no + ")?")) {
		var status='oStatus'+no
		var btn='return'+no
		fetch("<c:url value='/orderProcess/updateOrder?orderNo="+no+"&orderStatus=退貨審核中' />").then(
		 	    function(response) {
		 				return response.text();
		        }).then(function(data) {
		        	document.getElementById(status).innerHTML=data
	            })
		
		//document.location.href = "<c:url value='/orderProcess/updateOrder?orderNo="+no+"' />";
	} else {
	}
}
	
</script>
</head>
<body>
	<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/top.jsp" />
	<hr>

	<div class="container" style="margin-top: 35px">
		<c:forEach var="OrderBean" varStatus="stat" items="${memberOrders}">

			<div class="card" style="margin-bottom: 20px">
				<h5 class="card-header">訂單編號：${OrderBean.orderNo}</h5>
				<div class="card-body">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">訂購日期</th>
								<th scope="col">總金額</th>
								<th scope="col">付款方式</th>
								<th scope="col">送貨地址</th>
								<th scope="col">狀態</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">${OrderBean.orderDate}</th>
								<td>${OrderBean.totalAmount}</td>
								<td>${OrderBean.payment}</td>
								<td>${OrderBean.shippingAddress}</td>
								<td id='oStatus${OrderBean.orderNo}'>${OrderBean.orderStatus}</td>
							</tr>
						</tbody>
					</table>

					<div class="accordion" id="accordionExample">
						<div class="accordion-item">
							<h5 class="accordion-header" id="headingOne"
								style="margin-bottom: 12px">
								<button class="btn btn-success" style="background: #7fad39"
									type="button" onclick='showOrderDetail(${OrderBean.orderNo})'
									data-bs-toggle="collapse" data-bs-target="#collapseOne"
									aria-expanded="true" aria-controls="collapseOne">訂單明細</button>
								<button id='return${OrderBean.orderNo}' type='button' class='btn btn-secondary' onclick='confirmReturn(${OrderBean.orderNo})'>退貨申請</button>
							</h5>
							<div id="${OrderBean.orderNo}" style='display: none'
								class="accordion-collapse collapse show"
								aria-labelledby="headingOne" data-bs-parent="#accordionExample">
								<div class="accordion-body">
									<!-- 訂單明細  ajax display here-->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
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
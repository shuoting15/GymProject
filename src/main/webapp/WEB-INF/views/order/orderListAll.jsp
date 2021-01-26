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
	var id='ob'+no
	var dropdown = document.getElementById(id)
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
}



// fetch("<c:url value='/orderProcess/updateOrder?orderNo="+no+"' />").then(
// 		function(response) {
// 			var result=response.json()
// 			console.log(result)
// 			//return response.json();
// }).then(function(data) {
// 	//displayModifyForm(data,no)	
// })


function displayOrderDetail(data,no){
	var imageURL = "<c:url value='/productMaintain/getBookImage' />";
	var content="<table class='table'><thead class='thead-light'><tr>"
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
	var getDiv='ob'+no
	var display=document.getElementById(getDiv);
	display.innerHTML=content
}

function modifyOrder(no){
	var ob='ob'+no	
	var dropdown = document.getElementById(ob)
	if (dropdown.style.display == "none"){
		dropdown.style.display = 'block';
		displayModifyStatus(no)
	} else {
		dropdown.style.display = "none";
    }	
}

function displayModifyStatus(no){
	var content="<div class='form-group'><label for='statusForm'>狀態 : </label>"
	content+="<select class='form-control' id='statusForm"+no+"' >"
	content+="<option value='請選擇'>請選擇</option>"
	content+="<option value='付款成功'>付款成功</option>"
	content+="<option value='訂單取消'>訂單取消</option>"
	content+="<option value='訂單作廢'>訂單作廢</option>"	
	content+="<option value='退貨審核中'>退貨審核中</option>"
	content+="<option value='退貨中'>退貨中</option>"
	content+="<option value='退貨完成'>退貨完成</option> </select> </div>"
	content+="<button type='button' class='btn btn-secondary'  onclick='submitStatus("+no+")'"	
	content+=">Submit</button>"
	var getDiv='ob'+no
	var display=document.getElementById(getDiv);
	display.innerHTML=content
}

function submitStatus(no){
	var status='status'+no	
	var select='statusForm'+no	
	var orderStatus=document.getElementById(select).value
	//document.getElementById(status).innerHTML=orderStatus
	
	fetch("<c:url value='/orderProcess/updateOrder?orderNo="+no+"&orderStatus="+orderStatus+"' />").then(
	 	    function(response) {
	 				return response.text();
	        }).then(function(data) {
	        	document.getElementById(status).innerHTML=data
            })
	
	var ob='ob'+no	
	var dropdown = document.getElementById(ob)	
	dropdown.style.display = "none";	
}

</script>

</head>
<body>
	<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/top.jsp" />
	<hr>

	<div class='container'>

		<div class="row">
			<div class="col-lg-12">
				<div class="hero__search">
					<div class="hero__search__form">
						<form action="<c:url value='/orderProcess/findOrder'/>"
							method="post">
							<div class="hero__search__categories">
								以 <select name='searchBy'>
									<option value="byOrder">訂單編號</option>
									<option value="byMember">會員編號</option>
								</select> 搜尋
							</div>
							<input type="text" name="keyword" placeholder="請輸入編號">
							<button type="submit" class="site-btn">SEARCH</button>
						</form>
					</div>
				</div>
				
			</div>
		</div>


		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">訂單編號</th>
					<th scope="col">會員ID</th>
					<th scope="col">總金額</th>
					<th scope="col">付款方式</th>
					<th scope="col">訂購日期</th>
					<th scope="col">送貨地址</th>
					<th scope="col">狀態</th>
					<th scope="col">管理</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="ob" varStatus="stat" items="${allOrders}">
					<tr>
						<td>${ob.orderNo}</td>
						<td>${ob.memberId}</td>
						<td>${ob.totalAmount}</td>
						<td>${ob.payment}</td>
						<td>${ob.orderDate}</td>
						<td>${ob.shippingAddress}</td>
						<td id='status${ob.orderNo}'>${ob.orderStatus}</td>
						<td><i class='fa fa-pencil-square-o' style='color: gray'
							onclick='modifyOrder(${ob.orderNo})'></i>
							<button class="btn btn-success" style="background: #7fad39"
								type="button" onclick='showOrderDetail(${ob.orderNo})'
								data-bs-toggle="collapse" data-bs-target="#collapseOne"
								aria-expanded="true" aria-controls="collapseOne">明細</button></td>
					</tr>
					<!-- Detail toggle -->
					<tr>
						<td colspan="8">
							<div id="ob${ob.orderNo}" style='display: none'></div>
						</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://fonts.googleapis.com/css?family=Rajdhani:300,400,500,600,700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="http://cdn.bootstrapmb.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/all.min.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/lightcase.css">
<link rel="stylesheet" href="css/flaticon.css">
<link rel="stylesheet" href="css/swiper.min.css">

<link rel="shortcut icon" href="images/trainers/favicon.png"
	type="image/x-icon">
<link rel="stylesheet" href="css/stylecoach.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.23/css/jquery.dataTables.min.css">
<script src="js/jquery-3.3.1.min.js"></script>

<title>Insert title here</title>
</head>
<body>
 	<script type="text/javascript"> 
 		$(document).ready(function() {
 			$('#category').DataTable({
 				searching: true,
 				columnDefs: [{
 				      targets: [2,3,4,5],
 				       orderable: false,
 				}],
 				paging: false,
 				
 			});

 			var monthRevenueData = [];
 			var chart = new CanvasJS.Chart("monthRevenue", {
 				theme : "light2", // "light1", "light2", "dark1", "dark2"
 				exportEnabled : true,
 				animationEnabled : true,
 				title : {
 					text : "當月業績: NT$ ${allmonthrevenue}"
 				},
 				legend: {
 					verticalAlign: "center",
 					horizontalAlign: "left"
 				},
 				data : [ {
 					type : "doughnut",
 					startAngle : 25,
 					toolTipContent : "<b>{label}</b>: {y}%",
 					showInLegend : "true",
 					legendText : "{label}",
 					indexLabelFontSize : 16,
 					indexLabel : "{label} - {y}%",
 					dataPoints : monthRevenueData,
 				} ]
 			});

 			var totalRevenueData = [];
 			var chart2 = new CanvasJS.Chart("totalRevenue", {
 				theme : "light2", // "light1", "light2", "dark1", "dark2"
 				exportEnabled : true,
 				animationEnabled : true,
 				title : {
 					text : "總業績: NT$ ${alltotalrevenue}"
 				},
 				legend: {
 					verticalAlign: "center",
 					horizontalAlign: "left"
 				},
 				data : [ {
 					type : "doughnut",
 					startAngle : 25,
 					toolTipContent : "<b>{label}</b>: {y}%",
 					showInLegend : "true",
 					legendText : "{label}",
 					indexLabelFontSize : 16,
 					indexLabel : "{label} - {y}%",
 					dataPoints : totalRevenueData,
 				} ]
 			});

 			var yValue;
 			var label;

 			<c:forEach var='category' items='${category}' varStatus="loop">

 			yValue = "${category.totalrevenuePercent}";
 			label = "${category.categoryName}";
 			totalRevenueData.push({
 				label : label,
 				y : yValue,
 			});
 			</c:forEach>

 			<c:forEach var='category' items='${category}' varStatus="loop">

 			yValue = "${category.monthrevenuePercent}";
 			label = "${category.categoryName}";
 			monthRevenueData.push({
 				label : label,
 				y : yValue,
 			});
 			</c:forEach>

 			chart.render();
 			chart2.render();

 		});
 	</script>
	<div>
		<jsp:include page="/fragment/top.jsp" />
	</div>

	<section class="page-header bg_img" data-background="images/banner.jpg">
		<div class="container">
			<h3 class="title">
				<span class="shape-wrapper"><span class="shape"></span>Course Performance<span
					class="shape"></span></span>
			</h3>
		</div>
	</section>
<!-- 	<div class="breadcrumb-section1"> -->
<!-- 		<div class="container"> -->
<!-- 			<div class="breadcrumb-wrapper"> -->
<!-- 				<div class="breadcrumb-title"> -->
<!-- 					<h6 class="title">團課業績</h6> -->
<!-- 				</div> -->
<!-- 				<ul class="breadcrumb" -->
<!-- 					style="background: transparent; margin: -5px -10px; padding: 0;"> -->
<!-- 					<li><a href="index.html">首頁</a></li> -->
<!-- 					<li>團課業績</li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
	<script type="text/javascript" charset="utf8"
		src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>


	<section class="schedule-section padding-bottom padding-top">
		<script type="text/javascript"
			src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
		<div id="monthRevenue"
			style="width: 45%; height: 400px; display: inline-block; margin-left: 100px"></div>
		<div id="totalRevenue"
			style="width: 45%; height: 400px; display: inline-block;"></div>

		<div class="container">


			<table id="category" class="display" style="width: 100%">
				<thead>
					<tr>
						<th>編號</th>
						<th>類別</th>
						<th></th>
						<th></th>
						<th></th>
						<th>本月業績</th>
						<th>總業績</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var='category' items='${category}'>
						<tr>
							<td>${category.categoryId}</td>
							<td>${category.categoryName}</td>
							<td></td>
							<td></td>
							<td></td>
							<td>${category.monthrevenue}</td>
							<td>${category.totalrevenue}</td>




						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<th>編號</th>
						<th>類別</th>
						<th></th>
						<th></th>
						<th></th>
						<th>本月業績</th>
						<th>總業績</th>
					</tr>
				</tfoot>
			</table>
		</div>
	</section>
</body>
</html>
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

<style media="screen">
      /** 展开按钮 **/
      td.details-control {
        background: url('./details_open.png') no-repeat center center;
        cursor: pointer;
      }
 
      /** 收起按钮 **/
      tr.shown td.details-control {
        background: url('./details_close.png') no-repeat center center;
      }
    </style>

<title>Insert title here</title>
</head>
<body>
 	<script type="text/javascript"> 
 		$(document).ready(function() {
 			$('#category').DataTable({
 				searching: true,
//  				columnDefs: [{
//  				      targets: [2,3,4,5],
//  				       orderable: false,
//  				}],
 				paging: true,
 				
 			});
 			
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

		<div class="container">
		<h2><b>${alist[0].category}</b></h2>
			<table id="category" class="display" style="width: 100%">
				<thead>
					<tr>
						<th>編號</th>
						<th>課程名稱</th>
						<th>日期</th>
						<th>價格</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var='category' items='${alist}'>
						<tr>
							<td>${category.courseId}</td>
							<td>${category.title}</td>
							<td>${category.date}</td>
							<td>${category.price}</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<th>編號</th>
						<th>課程名稱</th>
						<th>日期</th>
						<th>價格</th>
					</tr>
				</tfoot>
			</table>
		</div>
	</section>
</body>
</html>
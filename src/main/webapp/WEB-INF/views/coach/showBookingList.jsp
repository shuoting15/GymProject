<%@page import="com.gym.coach.model.CoachOrderBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.time.Instant"%>
<%@ page import="java.time.temporal.ChronoUnit"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<link
	href="https://fonts.googleapis.com/css?family=Rajdhani:300,400,500,600,700&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:400,400i,600,600i,700,700i&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="http://cdn.bootstrapmb.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/all.min.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/lightcase.css">
<link rel="stylesheet" href="css/flaticon.css">
<link rel="stylesheet" href="css/swiper.min.css">

<link rel="shortcut icon" href="images/favicon.png" type="image/x-icon">
<link rel="stylesheet" href="css/stylecoach.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script type="text/javascript">
function finishBooking(orderId,startTime,coachId,memberId){
	var startTime = new Date(startTime);
	Swal.fire({
		  title: '確認此課程已完成?'+'<br>'+startTime.toLocaleString(),
		  text: "",
		  icon: 'question',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: '確定',
		  cancelButtonText:'取消'
		}).then((result) => {
		  if (result.isConfirmed) {
			  
			  (async () => {
				  const { value: range } = await Swal.fire({
				    title:'對此堂課的評分',
				    icon: 'question',
				    input: 'range',
				    inputLabel: '分數',
				    inputAttributes: {
				      min: 0,
				      max: 5,
				      step: 1
				    },
				    inputValue: 3,

				  })
				  if (range) {
					  $.ajax({
			          		url : "<c:url value='/finishBooking'/>",
			          		type : "POST",
			          		dataType : "JSON",
			          		data : {"orderId":orderId, "startTime":startTime,"rating":range,"coachId":coachId,"memberId":memberId}, 
			          		success : function (data) {},
			          		})
			          		
			          		
			          		Swal.fire('Good job!','完成','success');
           	
           					setTimeout(function(){
        					window.location.reload();//刷新当前页面.
        						},1500)
				  }
				  })()
				  
			  
		  }
		})

}
	










function confirmDelete(orderId,startTime) {
	var DayTime = new Date();
	var startTime = new Date(startTime);
	var LimitTime = new Date(startTime);
	LimitTime.setDate(LimitTime.getDate() - 1);
	if(DayTime < LimitTime){
		Swal.fire({
			  title: '確定取消此時段預約?'+'<br>'+startTime.toLocaleString(),
			  text: "",
			  icon: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: '確定取消',
			  cancelButtonText:'取消'
			}).then((result) => {
			  if (result.isConfirmed) {
	            	$.ajax({
					url : "<c:url value='/cancelBooking'/>",
					type : "POST",
					dataType : "JSON",
					data : {"orderId":orderId }, 
					success : function (data) {
		
							},
    					})              		               		       				           				              				  
				  
	            		Swal.fire('取消成功','已返還點數','已返還點數');
	            		setTimeout(function(){
	            		window.location.reload();//刷新当前页面.
	            		},1000)
			    
			 		 }
					})
	
	}
	else{
	Swal.fire('Opps!','此時段已超過可取消時間','error'); 
	}
	

}


</script>



<title>個人教練預約查詢</title>

</head>

<body>
	<jsp:include page="/fragment/top.jsp" />
	<!-- Page Header EndsHere -->
	<!-- Breadcrumb Section Starts Here -->
	<section class="page-header bg_img"
		data-background="images/banner.jpg">
		<div class="container">
			<h3 class="title">
				<span class="shape-wrapper"><span class="shape"></span>Trainers<span
					class="shape"></span></span>
			</h3>
		</div>
	</section>
	<div class="breadcrumb-section1">
		<div class="container">
			<div class="breadcrumb-wrapper">
				<div class="breadcrumb-title">
					<h6 class="title">教練預約查詢</h6>
				</div>
				<ul class="breadcrumb"
					style="background: transparent; margin: -5px -10px; padding: 0;">
					<li><a href="index.html">首頁</a></li>
					<li>教練預約查詢</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- Breadcrumb Section Ends Here -->
	<!-- Schedule Section Starts Here -->
	<section class="schedule-section padding-bottom padding-top">
		<div class="container">
			<div class="schedule-wrapper bg_img"
				data-background="./assets/images/schedule/schedule--bg.png">
				<div class="schedule-header text-center">
					<h3 class="title text-md-left">個人教練預約時間</h3>
					<p class="text-md-right">24小時內無法取消</p>
				</div>
				<table class="schedule-table" role="table">
					<thead role="rowgroup">
						<tr role="row">
							<th role="columnheader">會員姓名</th>
							<th role="columnheader">上課日期</th>
							<th role="columnheader">上課時間</th>
							<th role="columnheader">教練資訊</th>
							<th role="columnheader">狀態</th>
							<th role="columnheader">完成/取消課程</th>
						</tr>
					</thead>
					<tbody role="rowgroup">
						<c:forEach var="Booking" items="${Booking}">
							<tr role="row">
								<td role="cell" data-input="會員姓名">${Booking.memberBean.username}</td>

								<td role="cell" data-input="上課日期"><fmt:formatDate
										value="${Booking.orderDate}" pattern="yyyy/MM/dd" /></td>

								<td role="cell" data-input="上課時間"><fmt:formatDate
										value="${Booking.orderStartTime}" pattern="HH:mm" />
										~<fmt:formatDate
										value="${Booking.orderEndTime}" pattern="HH:mm" />
										</td>
								<td role="cell" data-input="Instructor">
									<div class="instructor">
										<div class="thumb">
											<a
												href="<c:url value='coach?id=${Booking.coachBean.coachId}'/>"><img
												src="<c:url value='/getPicture/${Booking.coachBean.coachId}'/>"
												alt="schedule"></a>
										</div>
										<div class="content">
											<a
												href="<c:url value='coach?id=${Booking.coachBean.coachId}'/>">${Booking.coachBean.coachName}</a>
											<span>${Booking.coachBean.coachExpertise}</span>
										</div>
									</div>
								</td>
								<c:choose>
									<c:when test="${Booking.orderStatus =='x'}">
										<td role="cell" data-input="Note">預約成功</td>
									</c:when>
									<c:when test="${Booking.orderStatus =='f'}">
										<td role="cell" data-input="Note">課程已完成</td>
									</c:when>
								</c:choose>
								<td role="cell" data-input="Note">
 									<fmt:formatDate value="${Booking.orderStartTime}" 
									pattern="yyyy-MM-dd HH:mm:ss" var="startTime" /> 

 									<c:choose>
										<c:when test="${Booking.orderStatus =='x'}">
										<button type="button" class="btn btn-success" onclick="finishBooking(${Booking.orderId},'${startTime}','${Booking.coachBean.coachId}','${LoginOK.member_id}')">完成課程</button> 

										</c:when>
										<c:when test="${Booking.orderStatus eq 'f'}">
<!-- 										<button type="button" class="btn btn-success" disabled="disabled">課程已完成</button>  -->
										</c:when>
									</c:choose>
							
									 	<c:choose>
										<c:when test="${Booking.orderStatus =='x'}">
												<button type="button" class="btn btn-danger"
										style="margin-top: 2px"
										onclick="confirmDelete(${Booking.orderId},'${startTime}')">取消預約</button>

										</c:when>
										<c:when test="${Booking.orderStatus =='f'}">
<!-- 										<button type="button" class="btn btn-danger" -->
<!-- 										style="margin-top: 2px" disabled="disabled" -->
<%-- 										onclick="confirmDelete(${Booking.orderId},'${startTime}')">取消預約</button> --%>
										</c:when>
									</c:choose>



								</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</section>
	<!-- Schedule Section Ends Here -->
	<!-- Footer Section Starts Here -->

	<!-- Footer Section Ends Here -->
	<!-- JavaScript File Links -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/modernizr-3.6.0.min.js"></script>
	<script src="js/plugins.js"></script>
	<script
		src="http://cdn.bootstrapmb.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script src="js/isotope.pkgd.min.js"></script>
	<script src="js/swiper.min.js"></script>
	<script src="js/waypoint.js"></script>
	<script src="js/counterup.min.js"></script>
	<script src="js/lightcase.js"></script>
	<script src="js/wow.min.js"></script>
	<script src="js/maincoach.js"></script>

</body>

</html>
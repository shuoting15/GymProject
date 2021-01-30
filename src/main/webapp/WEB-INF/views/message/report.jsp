<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
#read {
	text-align:right;
	margin-left:30px;
}
#cmcolor{
color:#C0C0C0;
}
#reportid{
color:#C0C0C0;
}
</style>

<title>論壇後臺管理</title>


<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	data-integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	data-crossorigin="anonymous">

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<!-- <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"> -->
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="css/nice-select.css" type="text/css">
<link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
<link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="css/style.css" type="text/css">
<link rel="stylesheet" href="css/stylemember.css" type="text/css">

</head>
<body>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
  integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
  crossorigin="anonymous"></script>
  
  <link rel="stylesheet"
  href="<c:url value='https://cdn.datatables.net/1.10.23/css/jquery.dataTables.min.css' />">
 <script defer
  src="<c:url value='https://cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js' />"></script>



  
	<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/top.jsp" />
	<!-- Breadcrumb Section Begin -->
	<section class="breadcrumb-section set-bg"
		data-setbg="images/shop-setBack.png" style="height: 320px;">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="breadcrumb__text">
						<h2>Gym forum</h2>
						<div class="breadcrumb__option">
							<a href="<c:url value='/messages'/>">Home</a> <span>Message</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Breadcrumb Section End -->

	<!-- Blog Section Begin -->
	<section class="blog spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-5">
					<div class="blog__sidebar">
					
<!-- 						<div class="blog__sidebar__item"> -->
							
<!-- 							<div class="blog__sidebar__recent"> -->
<!-- 								<a href="#" class="blog__sidebar__recent__item"> -->
<!-- 									<div class="blog__sidebar__recent__item__pic"> -->
										
<!-- 									</div> -->
<!-- 									<div class="blog__sidebar__recent__item__text"> -->
										
<!-- 									</div> -->
<!-- 								</a> <a href="#" class="blog__sidebar__recent__item"> -->
<!-- 									<div class="blog__sidebar__recent__item__pic"> -->
<!-- 										<img src="img/blog/sidebar/sr-2.jpg" alt=""> -->
<!-- 									</div> -->
<!-- 									<div class="blog__sidebar__recent__item__text"> -->
										
<!-- 									</div> -->
<!-- 								</a> <a href="#" class="blog__sidebar__recent__item"> -->
<!-- 									<div class="blog__sidebar__recent__item__pic"> -->
<!-- 										<img src="img/blog/sidebar/sr-3.jpg" alt=""> -->
<!-- 									</div> -->
<!-- 									<div class="blog__sidebar__recent__item__text"> -->
										
<!-- 									</div> -->
<!-- 								</a> -->
<!-- 							</div> -->
<!-- 						</div> -->
						<div class="blog__sidebar__item">
							<h4>後臺管理</h4>
							<ul>
								<li><a href="<c:url value='/allmessages'/>">前往論壇</a></li>
							</ul>
							<div class="blog__sidebar__item__tags">
									
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-8 col-md-7">
					<div class="row">
					<table id="myreport" border="1">
					<thead>
					<tr>
					<th style="text-align:center">文章ID</th>
					<th style="text-align:center">舉報原因</th>
					<th style="text-align:center">舉報者</th>
					<th style="text-align:center">舉報時間</th>
					</tr>
					</thead>
					<tbody>
							<c:forEach var='reports' items='${reports}'>
							<tr>
							<td style="text-align:center;width:80px;">${reports.messageBean.articleId}</td>
							<td style="text-align:center;width:700px;">${reports.reportContent}</td>
							<td style="text-align:center;width:80px;">${reports.memberbean.username}</td>
							<td style="text-align:center;width:400px;">${reports.time}</td>
							</tr>
							</c:forEach>
							</tbody>
							</table>
						</div>
						
					</div>
				</div>


			</div>
	
	 <script>
  $(document).ready(function() {
   $('#myreport').DataTable();
  });
 </script>
	
	
	
 
	
	
	</section>
	<!-- Blog Section End -->

	<!-- Js Plugins -->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/jquery.slicknav.js"></script>
	<script src="js/mixitup.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>


</body>
</html>
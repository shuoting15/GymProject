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
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="css/customerdetail.css">
<title>教練顧客維護</title>
</head>
<body>
	<script type="text/javascript">
	/* Formatting function for row details - modify as you need */
	function format ( d ) {
	    // `d` is the original data object for the row
	    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
	        '<tr>'+
	            '<td>電話:</td>'+
	            '<td>'+d.mobile+'</td>'+
	        '</tr>'+
	        '<tr>'+
	            '<td>Email:</td>'+
	            '<td>'+d.member_id+'</td>'+
	        '</tr>'+
	        '<tr>'+
	            '<td>Extra info:</td>'+
	            '<td><img src='+d.memberphoto+' alt="schedule" style="width: 150px; height: 150px;" ></td>'+
	        '</tr>'+
	    '</table>';
	}
	 
	$(document).ready(function() {
	    var table = $('#customers').DataTable( {
            "ajax": {
                url :"<c:url value='/customerManageapi?coachId=${coach.coachId}'/>",             
                type: 'POST',
 				dataSrc: "",
  
            },
	        "columns": [
	            {
	                "className":      'details-control',
	                "orderable":      false,
	                "data":           null,
	                "defaultContent": ''
	            },
	            { "data": "username" },
	            { "data": "gender",
	               "render":function(data){
	            	   if(data == 1){
	            		   
	            		   return "男";
	            	   }else{
	            		   return "女";
	            	   }	   
	               }
	            },
	            { "data": "birth" },
	            { "data": "member_height" },
	            { "data": "member_weight" },
	            { "data": "monthConsumeInCoach" },
	            { "data": "totalConsumeInCoach" }
	        ],
	        "order": [[1, 'asc']]
	    } );
	     
	    // Add event listener for opening and closing details
	    $('#customers tbody').on('click', 'td.details-control', function () {
	        var tr = $(this).closest('tr');
	        var row = table.row( tr );
	 
	        if ( row.child.isShown() ) {
	            // This row is already open - close it
	            row.child.hide();
	            tr.removeClass('shown');
	        }
	        else {
	            // Open this row
	            row.child( format(row.data()) ).show();
	            tr.addClass('shown');
	        }
	    } );
	} );
	</script>


	<div>
		<jsp:include page="/fragment/top.jsp" />
	</div>

	<section class="page-header bg_img" data-background="images/banner.jpg">
		<div class="container">
			<h3 class="title">
				<span class="shape-wrapper"><span class="shape"></span>CustomerMange<span
					class="shape"></span></span>
			</h3>
		</div>
	</section>
	<div class="breadcrumb-section1">
		<div class="container">
			<div class="breadcrumb-wrapper">
				<div class="breadcrumb-title">
					<h6 class="title">教練顧客管理</h6>
				</div>
				<ul class="breadcrumb"
					style="background: transparent; margin: -5px -10px; padding: 0;">
					<li><a href="index.html">首頁</a></li>
					<li>教練顧客管理</li>
				</ul>
			</div>
		</div>
	</div>
	<script type="text/javascript" charset="utf8"
		src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>


	<section class="schedule-section padding-bottom padding-top">
		<div class="trainer-thumb" align="center" style="margin-bottom: 30px">
			<h2 align="center">教練姓名:${coach.coachName}</h2>
			<img style="" src="<c:url value='/getPicture/${coach.coachId}'/>"
				alt="trainer" width="20%" height="20%">
		</div>

		<div class="container">

			<h2 align="center">個人顧客表單</h2>
			<table id="customers" class="display" style="width: 100%">
				<thead>
					<tr>
						<th></th>
						<th>會員姓名</th>
						<th>性別</th>
						<th>生日</th>
						<th>身高</th>
						<th>體重</th>
						<th>本月消費</th>
						<th>總消費</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th></th>
						<th>會員姓名</th>
						<th>性別</th>
						<th>生日</th>
						<th>身高</th>
						<th>體重</th>
						<th>本月消費</th>
						<th>總消費</th>
					</tr>
				</tfoot>
			</table>
		</div>
	</section>
</body>
</html>
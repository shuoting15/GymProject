<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員登入</title>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
	
<!-- Google Font -->
<link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="css/nice-select.css" type="text/css">
<link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
<link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="css/style.css" type="text/css">
<link rel="stylesheet" href="css/responsive.css" type="text/css">
<link rel="stylesheet" href="css/stylemember.css" type="text/css">

</head>

<body>
	<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/top.jsp" />

	<!-- Login Section Begin -->
	<section class="loginMain">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-5 loginForm">				
					<h1>會員登入</h1>
					<form action="login" method="post" id="loginForm">
						<p>
							<label class="loginFormLabel">帳號：</label> 
							<input type="text" name="member_id" placeholder="輸入您的電子信箱" value="" />
						</p>
						<p>
							<label class="loginFormLabel">密碼：</label> 
							<input type="password" name="password" value="" />
						</p>
						<span>${errors.accountx}${errors.loginx}</span>
						<p>
							<input type="submit" value="登入">
						</p>
						<p class="forgetPassword">
							<a href="<c:url value='/forgetpd.jsp'/>">忘記密碼</a>
						</p>
						<p>
							<a href="" class="facebookAccount"><img src="images/facebook_btn.png"></a>
							<a href="" class="googleAccount"><img src="images/google_btn.png"></a>
						</p>
					</form>
				</div>
				<div class="col-md-3 visible-lg"></div>
				<div class="col-md-3 loginBG visible-lg">
					<img src="images/loginMember.png" />
				</div>
			</div>
		</div>
	</section>
	<!-- Login Section End -->

	<!-- Js Plugins -->
	<script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/jquery.slicknav.js"></script>
	<script src="js/mixitup.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>
	
	<script src="https://code.jquery.com/jquery-3.5.1.js"
		    integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		    crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
	
	<script type="text/javascript">	   
		$(function() {			
			$("#loginForm").validate({
				errorClass : "my-error-class",
				validClass : "my-valid-class",
				//去除左側空白  
				onkeyup : function(element, event) {
						  var value = this.elementValue(element).replace(/^\s+/g, "");
						  $(element).val(value);
						},					
				//驗證規則
				rules : {
						 member_id : {
					     required : true
						},
						 password : {
						 required : true,								
						}
						},
				//自訂錯誤訊息
				messages : {
							member_id : {
							required : "請輸入帳號"
						   },
							password : {
							required : "請輸入密碼",								
						   }
						   }
					});		      
		    //下一個jQuery事件可以加在這後面======================================================
			
		});//此行jQuery結束
	</script>

</body>
</html>
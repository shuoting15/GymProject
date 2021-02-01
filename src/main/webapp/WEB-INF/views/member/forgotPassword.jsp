<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>忘記密碼</title>
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

	<!-- ForgotPwd Section Begin -->
	<section class="joinMemberTitle">
	<h1>忘記密碼</h1>
	</section>
	<section class="joinMemberMain">
	<div class="container">
		<div class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10 joinForm">
				<h3>請輸入您註冊時填寫的帳號(Email)，密碼重設信件將寄到此帳號(Email)中</h3>
				<form id="email2uForm" action="forgotPassword" method="post">
					<p>
						<label class="col-sm-2">◆ 電子信箱：</label>
						<input type="text" name="member_id" placeholder=" 輸入您當初註冊的電子信箱"/> 
						<span>${errors.accountx}${errors.failedToSend}</span>
					</p>
					<p class="forgetSuccess">${errors.email2youOK}</p>
                    <p>
						<input type="submit" onclick="doClick()" value="發送"/> 
						<span><img id="forgotPwdSubmit" src="images/ajax-loader.gif" style="display: none"></span>
					</p>
				</form>
				<p>
					<button onclick="gymproject121()">DEMO忘記密碼</button>
				</p>
			</div>
			<div class="col-sm-1"></div>
		</div>
	</div>
	</section>
	<!-- ForgotPwd Section End -->

	<!-- Js Plugins -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/jquery.slicknav.js"></script>
	<script src="js/mixitup.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>
	
	<!-- <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script> -->
	<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
	
	<script type="text/javascript">
		function doClick() {
			document.getElementById("forgotPwdSubmit").style.display = "inline";
		}
		
	//======jQuery開始====================================================================

		$(function() {
			$("#email2uForm").validate({
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
						required : true,
						email : true
					}
				},
				//自訂錯誤訊息
				messages : {
					member_id : {
						required : "必填",
						email : "請輸入正確電子郵件"
					}
				}
			});
			//下一個jQuery事件可以加在這後面=======================================================	

		});
	</script>
	
	<script type="text/javascript">
	
		function gymproject121() {			
			email2uForm.member_id.value="gymproject121@gmail.com";			
		};
		
	</script>

</body>
</html>
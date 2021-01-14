<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員註冊</title>
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

	<!-- Register Section Begin -->
	<section class="joinMemberTitle">
	<h1>註冊會員</h1>
	</section>
	<section class="joinMemberMain">
	<div class="container">
		<div class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10 joinForm">
				<h3>歡迎加入GYM 會員</h3>
				<form id="registerForm" action="register" method="post">
					<p>
						<label class="col-sm-2" for="account">◆ 帳號：</label> 
						<input type="text" name="member_id" id="member_id" onblur="doBlur()" placeholder="輸入您的電子信箱"/> 
						<span id="accountResult">${errors.accountx}${errors.accountexist}</span>
						<!-- <span><img id="img" src="images/ajax-loader.gif" style="display:none"></span> -->
					</p>
					<p>
						<label class="col-sm-2" for="password">◆ 密碼：</label> 
						<input type="password" name="password" id="password" class="checkpassword" placeholder="英數混合且六個字以上"/> 
						<span id="passwordResult">${errors.passwordx}</span>
					</p>
					<p>
						<label class="col-sm-2" for="confirmpw">◆ 確認密碼： </label> 
						<input type="password" name="confirmpw" id="confirmpw" /> 
						<span id="passwordAgainResult">${errors.confirmpwx}</span>
					</p>
					<p><input type="submit" id="submit" value="註冊"></p>
					<span id="checkAll">${errors.success}</span>
				</form>
			</div>
			<div class="col-sm-1"></div>
		</div>
	</div>
	</section>
	<!-- Register Section End -->

	<!-- Js Plugins -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/jquery.slicknav.js"></script>
	<script src="js/mixitup.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>
	
	<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
	
	<script type="text/javascript">
	  //Ajax確認帳號有無重複	  
	    function doBlur() {//帳號欄位blur呼叫
	    	//清空上次遺留字串
	    	var span = document.getElementById("accountResult");
	    	span.innerHTML = "<font color='blue' size='small'>" +""+ "</font>";
	    	//email正則
	    	var member_id = document.getElementById("member_id").value
	    	let patt = /\S+@\S+/;  	
	    	let result = patt.test(member_id);
	    	//有輸入帳號且符合email格式才呼叫controller
	    	if(!member_id.trim().length == 0 && result == true){
			    var xhr = new XMLHttpRequest();
			    xhr.open("POST", "memberCheckAjex", true);//呼叫
			    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	            xhr.send("member_id=" + member_id);
	            var message = "";//驗證字串
	            
	            xhr.onreadystatechange = function() {	            	
					// 伺服器請求完成
					if (xhr.readyState == 4 && xhr.status == 200) {
						var result = JSON.parse(xhr.responseText);//傳出文字解析
						if (result.id.length == 0) {
							message = "帳號可用";
						} else if (result.id.startsWith("Error")) {
							message = result.id;
						} else {
							message = "帳號重複，請重新輸入帳號";
						}
						span.innerHTML = "<font color='blue' size='small'>" + message+ "</font>";
					}
				}	
	    	};
	    };
	  //jQuery開始
	$(function() {		  
		$("#registerForm").validate({
					errorClass : "my-error-class",
					validClass : "my-valid-class",
			        //失去焦點就驗證 
			        onfocusout: function(element) { $(element).valid(); },
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
						},
						password : {
							required : true
						},
						confirmpw : {
							required : true,
							equalTo : "#password"
						}
					},
					//自訂錯誤訊息
					messages : {
						member_id : {
							required : "必填",
							email : "請輸入正確電子郵件格式"
						},
						password : {
							required : "必填"
						},
						confirmpw : {
							required : "必填",
							equalTo : "請輸入相同密碼"
						}
					}
			});	         
				//密碼的表單驗證
				jQuery.validator.addMethod("checkpassword", function( value, element ) {
					let str = value;
					let result = false;
				 
					if(str.length > 0){
						let patt = /^[0-9A-z!@#$%&*]{6,}$/;
						let result1 = patt.test(str);
						//先測試是否有英文
						let pattEN = /[a-zA-Z]{1,}/;
						result2 = pattEN.test(str);
						//先測試是否有數字
						let pattDigit = /[0-9]{1,}/;
						result3 = pattDigit.test(str);						
				 
						if(result1 == true && result2 == true && result3 == true ){
							result = true;
						} else{
							result = false;
						}
					} else {
						result = true;
					}
					return result;
				}, "請輸入正確格式");

		//下一個jQuery事件可以加在這後面======================================================
			
	});//此行jQuery結束
	</script>

</body>
</html>
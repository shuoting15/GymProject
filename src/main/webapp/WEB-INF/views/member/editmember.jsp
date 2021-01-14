<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編輯基本資料</title>
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

	<!-- EditMember Section Begin -->
	<div class="container">
			<div class="col-sm-10">
				<h1 class="memberBasicTitle">編輯基本資料</h1>
				<div class="col-sm-12">
					<section class="row memberAreaBody">
						<div class="memberIntr">
							<form id="editFrom" action="editmember" method="post">
								<div class="memberBasicFormBox">
									<div class="memberBasicFormText">
										<label for="member_id">◆ 帳號</label>
									</div>
									<div class="memberBasicFormInput">
										<input id="member_id" type="text" name="member_id" value="${member_id}" readonly /> 
										<span>${errors.member_idx}</span>
									</div>
								</div>
								<div class="memberBasicFormBox">
									<div class="memberBasicFormText">
										<label for="password">◆ 密碼</label>
									</div>
									<div class="memberBasicFormInput">
										<input type="password" id="password" name="password" class="checkpassword" value="${password}" /> 
										<span>${errors.passwordx}</span>
									</div>
								</div>
								<div class="memberBasicFormBox">
									<div class="memberBasicFormText">
										<label for="confirmpw">◆ 確認密碼</label>
									</div>
									<div class="memberBasicFormInput">
										<input type="password" id="confirmpw" name="confirmpw" value="" /> 
										<span>${errors.confirmpwx}</span>
									</div>
								</div>
								<div class="memberBasicFormBox">
									<div class="memberBasicFormText">
										<label for="username">◆ 姓名</label>
									</div>
									<div class="memberBasicFormInput">
										<input type="text" id="username" name="username" value="${username}" /> 
										<span>${errors.usernamex}</span>
									</div>
								</div>
								<div class="memberBasicFormBox">
									<div class="memberBasicFormText">
										<label for="mobile">◆ 手機</label>
									</div>
									<div class="memberBasicFormInput">
										<input type="text" id="mobile" name="mobile" value="${mobile}" />
										<span>${errors.mobilex}</span>
									</div>
								</div>
								<div class="memberBasicFormBox">
									<div class="memberBasicFormText">
										<label>◆ 性別</label>
									</div>
									<div class="memberBasicFormInput">
										<input id="male" type="radio" name="gender" value="1">男
										<input id="female" type="radio" name="gender" value="2">女
										<input id="other" type="radio" name="gender" value="0">未選擇
										<span>${errors.genderx}</span>
									</div>
								</div>
								<div class="memberBasicFormBox">
									<div class="memberBasicFormText">
										<label for="birth">◆ 生日</label>
									</div>
									<div class="memberBasicFormInput">
										<input type="date" name="birth" id="birth" value="${birth}">
										<span>${errors.birthx}</span>
									</div>
								</div>
								<div class="memberBasicFormBox">
									<div class="memberBasicFormText">
										<label for="address">◆ 地址</label>
									</div>
									<div class="memberBasicFormInput">
										<input type="text" id="address" name="address" value="${address}" /> 
										<span>${errors.addressx}</span>
									</div>
								</div>
								<div class="memberBasicFormBox">
									<div class="memberBasicFormText">
										<label for="facebook_account">◆ FACEBOOK</label>
									</div>
									<div class="memberBasicFormInput">
										<input type="text" name="facebook_account" id="facebook_account" value="${facebook_account}" /> 
										<span>${errors.fbx}</span>
									</div>
								</div>
								<div class="memberBasicFormBox">
									<div class="memberBasicFormText">
										<label for="google_account">◆ GOOGLE</label>
									</div>
									<div class="memberBasicFormInput">
										<input type="text" name="google_account" id="google_account" value="${google_account}" />
										<span>${errors.googlex}</span>
									</div>
								</div>								
								<div class="memberBasicFormBox">
									<div class="memberBasicFormText aboutMeHeight">
										<label for="detail">◆ 關於我</label>
									</div>
									<div class="memberBasicFormInput">
										<textarea name="detail" id="detail">${detail}</textarea>
									</div>
								</div>
								<div class="gymFormButton">
									<input type="submit" name="submit" id="submit" value="確定" /> 
									<input class="BGCG" type="reset" name="" value="取消" /> 
								    <div style="text-align:center;margin-top:10px;color:#46A3FF;font-weight:bold">${errors.updatex}${errors.updateok}</div>									
								</div>
								
							</form>
						</div>
					</section>
				</div>
			</div>
	</div>
	<!-- EditMember Section End -->

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
	$(function() {
		$("#editFrom").validate({
			errorClass : "my-error-class",
			validClass : "my-valid-class",
					//失去焦點就驗證 
					onfocusout : function(element) {
						$(element).valid();
					},
					//去除左側空白  
					onkeyup : function(element, event) {
						var value = this.elementValue(element).replace(/^\s+/g, "");
						$(element).val(value);
					},
					//驗證規則
					rules : {
						password : {
							required : true
						},
						confirmpw : {
							required : true,
							equalTo : "#password"
						},
						mobile : {
							digits : true
						},
						facebook_account : {
							email : true
						},
						google_account : {
							email : true
						}
					},
					//自訂錯誤訊息
					messages : {
						password : {
							required : "必填"
						},
						confirmpw : {
							required : "必填",
							equalTo : "請輸入相同密碼"
						},
						mobile : {
							digits : "請輸入正確電話號碼"
						},
						facebook_account : {
							email : "請輸入正確FB帳號"
						},
						google_account : {
							email : "請輸入正確Google帳號"
						}
					}
				});
		
		//密碼的表單驗證
		jQuery.validator.addMethod("checkpassword",function(value, element) {
					let str = value;
					let result = false;

					if (str.length > 0) {
						let patt = /^[0-9A-z!@#$%&*]{6,}$/;
						let result1 = patt.test(str);
						//先測試是否有英文
						let pattEN = /[a-zA-Z]{1,}/;
						result2 = pattEN.test(str);
						//先測試是否有數字
						let pattDigit = /[0-9]{1,}/;
						result3 = pattDigit.test(str);
						
						if (result1 == true && result2 == true
								&& result3 == true) {
							result = true;
						} else {
							result = false;
						}
					} else {
						result = true;
					}
					return result;
				}, "請輸入正確格式");

		//功能:導入此頁時判斷男女
		if(${gender}==0){
			$("#other").attr("checked",true);//打勾
	      }else if(${gender}==1){
	    	  $("#male").attr("checked",true);//打勾
	      }else if(${gender}==2){
	    	  $("#female").attr("checked",true);//打勾
		      }
		
	    //下一個jQuery事件可以加在這後面======================================================
	    
	});//此行jQuery結束
	</script>

</body>
</html>
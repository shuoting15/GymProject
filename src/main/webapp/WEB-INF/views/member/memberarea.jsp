<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員專區</title>
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

	<!-- MemberArea Section Begin -->
	<div class="container">
		<div class="col-sm-10">
			<div class="col-sm-12">
				<form name="Form1" id="Form1" method="POST" action="${pageContext.request.contextPath}/"enctype="multipart/form-data">
					<input type="file" name="FILE" id="FILE" style="display: none" />
					<input type="hidden" name="DIR" id="DIR" /> 
					<input name="btnUpload" id="btnUpload" type="submit" value="儲存" style="display: none" />
				</form>
				<section class="row memberAreaBody">
					<div class="memberIntr">
						<div class="memberIntrPic" onclick="upload()">
							<img src="....讀圖檔" id="memberphoto" name="memberphoto" />
<!-- 							<div class="memberIntrEdit">編輯</div> -->
						</div>
						<div class="memberIntrTextBox">
							<div class="memberIntrName">${username}</div>
							<div class="memberIntrEmail">${member_id}</div>
							<div class="memberIntrText">${detail}</div>
						</div>
						 <div class="memberIntrBtn">
							<a href="editmember">編輯會員基本資料</a>
						</div>
						<br>
						<div class="memberIntrBtn">
							<a href="logout">登出</a>
						</div>						
					</div>
				</section>				
			</div>
		</div>
	</div>
	<!-- MemberArea Section End -->

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
		//======以下照片更換==========================
		function upload() {
			$("#FILE").trigger("click");
		}

		function onload() {
			$("#btnUpload").trigger("click");
		}
		//======照片更換結束===========================

		//======jQuery開始===========================
		$(function() {

			//1.會員照片功能 : 當 input.upl[type=file] 發生改變的時候會觸發下面的preview方法
			$("body").on("change", "#FILE", function() {
				preview(this);
				onload();
			})

			//預覽圖 @param input輸入input[type=file]的 this
			function preview(input) {
				// 若有選取檔案
				if (input.files && input.files[0]) {
					// 建立一個物件，使用 Web APIs的檔案讀取器(FileReade物件)來讀取使用者選取電腦中的檔案
					var reader = new FileReader();
					// 事先定義好，當讀取成功後會觸發的事情
					reader.onload = function(e) {
						console.log(e);
						// 這裡看到的 e.target.result物件，是使用者的檔案被 FileReader 轉換成 base64 的字串格式，
						// 在這裡我們選取圖檔，所以轉換出來的，會是如 『data:image/jpeg;base64,.....』這樣的字串樣式。
						// 我們用它當作圖片路徑就對了。
						$('#photo').attr('src', e.target.result);
					}
					// 因為上面定義好讀取成功的事情，所以這裡可以放心讀取檔案
					reader.readAsDataURL(input.files[0]);
				}
			}
			//下一個jQuery事件可以加在這後面=======================================================
				
		});//此行jQuery結束
	</script>

</body>
</html>
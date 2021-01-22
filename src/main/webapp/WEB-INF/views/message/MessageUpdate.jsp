<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新文章</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript">
	function confirmDelete(id) {
		if (confirm("確定刪除此文章(${messageBean.articleId})?")) {
			document.forms[0].action ="<c:url value='/MessageDelete/${messageBean.articleId}'  />";
			document.forms[0].method ="POST";
			document.forms[0].submit();
		} else {
		}
	}
	function updateBook() {
		var hiddenField = document.getElementById("updateOrDelete");
		//     hiddenField.value='PUT';
		document.forms[0].method = "POST";
		document.forms[0].submit();
	}
</script>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/css/style.css' type="text/css" />
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">
<style type="text/css">
fieldset {
	font-weight: bold;
	color: black;
	border: 1px solid black;
	padding: 4px 2px;
}

span.error {
	color: red;
	display: inline-block;
	font-size: 5pt;
}

.fieldset-auto-width {
	display: inline-block;
}

.cnt {
	width: 650px;
	height: 500px;
	resize: none
}

.tt1 {
	height: 40px;
	width: 650px;
	resize: none
}

.test1 {
	width: 50px;
}
</style>
<!-- Css Styles -->
<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="../css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="../css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="../css/nice-select.css" type="text/css">
<link rel="stylesheet" href="../css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="../css/owl.carousel.min.css" type="text/css">
<link rel="stylesheet" href="../css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body>
	<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/top.jsp" />

	<!-- Breadcrumb Section Begin -->
	<!-- 	<section class="breadcrumb-section set-bg" -->
	<!-- 		data-setbg="images/shop-setBack.png" style="height: 320px;"> -->
	<!-- 		<div class="container"> -->
	<!-- 			<div class="row"> -->
	<!-- 				<div class="col-lg-12 text-center"> -->
	<!-- 					<div class="breadcrumb__text"> -->
	<!-- 						<h2>Gym forum</h2> -->
	<!-- 						<div class="breadcrumb__option"> -->
	<%-- 							<a href="<c:url value='/messages'/>">Home</a> <span>Message</span> --%>
	<!-- 						</div> -->
	<!-- 					</div> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 	</section> -->
	<!-- Breadcrumb Section End -->

	<!-- Blog Section Begin -->
	<section class="blog spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-5">
					<div class="blog__sidebar">
						<!-- 						<div class="blog__sidebar__search"> -->
						<%-- 							<form action="#"> --%>
						<!-- 								<input type="text" placeholder="Search..."> -->
						<!-- 								<button type="submit"> -->
						<!-- 									<span class="icon_search"></span> -->
						<!-- 								</button> -->
						<%-- 							</form> --%>
						<!-- 						</div> -->
						<div class="blog__sidebar__item">
							<div class="blog__item">
								<div class="blog__item__pic">
									<div class="blog__item__text">
										<a href="<c:url value='/messages/add'/>" class="blog__btn">新增文章</a>
										<!-- 			</div> -->
									</div>
								</div>
							</div>
							<h4>主題</h4>
							<ul>
								<li><a href="<c:url value='/allmessages'/>">全部主題</a></li>
							</ul>
							<c:forEach var='kanbanName' items='${kanbanNameList}'>
								<ul>
									<li><a href="<c:url value='/${kanbanName}'/>">${kanbanName}</a>
									</li>
								</ul>
							</c:forEach>
						</div>
						<!-- 						<div class="blog__sidebar__item"> -->
						<!-- 							<h4>Recent News</h4> -->
						<!-- 							<div class="blog__sidebar__recent"> -->
						<!-- 								<a href="#" class="blog__sidebar__recent__item"> -->
						<!-- 									<div class="blog__sidebar__recent__item__pic"> -->
						<!-- 										<img src="img/blog/sidebar/sr-1.jpg" alt=""> -->
						<!-- 									</div> -->
						<!-- 									<div class="blog__sidebar__recent__item__text"> -->
						<!-- 										<h6> -->
						<!-- 											09 Kinds Of Vegetables<br /> Protect The Liver -->
						<!-- 										</h6> -->
						<!-- 										<span>MAR 05, 2019</span> -->
						<!-- 									</div> -->
						<!-- 								</a> <a href="#" class="blog__sidebar__recent__item"> -->
						<!-- 									<div class="blog__sidebar__recent__item__pic"> -->
						<!-- 										<img src="img/blog/sidebar/sr-2.jpg" alt=""> -->
						<!-- 									</div> -->
						<!-- 									<div class="blog__sidebar__recent__item__text"> -->
						<!-- 										<h6> -->
						<!-- 											Tips You To Balance<br /> Nutrition Meal Day -->
						<!-- 										</h6> -->
						<!-- 										<span>MAR 05, 2019</span> -->
						<!-- 									</div> -->
						<!-- 								</a> <a href="#" class="blog__sidebar__recent__item"> -->
						<!-- 									<div class="blog__sidebar__recent__item__pic"> -->
						<!-- 										<img src="img/blog/sidebar/sr-3.jpg" alt=""> -->
						<!-- 									</div> -->
						<!-- 									<div class="blog__sidebar__recent__item__text"> -->
						<!-- 										<h6> -->
						<!-- 											4 Principles Help You Lose <br />Weight With Vegetables -->
						<!-- 										</h6> -->
						<!-- 										<span>MAR 05, 2019</span> -->
						<!-- 									</div> -->
						<!-- 								</a> -->
						<!-- 							</div> -->
						<!-- 						</div> -->
						<div class="blog__sidebar__item">
							<!-- 							<h4>Search By</h4> -->
							<!-- 							<div class="blog__sidebar__item__tags"> -->
							<!-- 								<a href="#">Apple</a> <a href="#">Beauty</a> <a href="#">Vegetables</a> -->
							<!-- 								<a href="#">Fruit</a> <a href="#">Healthy Food</a> <a href="#">Lifestyle</a> -->
							<!-- 							</div> -->
						</div>
					</div>
				</div>
				<div class="col-lg-8 col-md-7 order-md-1 order-1">
					<div class="blog__details__text">
						<form:form method='POST' modelAttribute="messageBean"
							enctype="multipart/form-data">
							<input type='hidden' id='updateOrDelete' name='_method'>
							<c:if test='${MessageBean.articleId != null}'>
								<form:hidden path="articleId" />
								<br>&nbsp;
							</c:if>
							<fieldset class="fieldset-auto-width">
								<legend>編輯文章</legend>
								<table>
									<tr>
										<td style="display: inline; width: 40px">子板:<br />
											<p width="300">
												<form:select path='kanbanName'>
													<form:option value="-1" label="請挑選" />
													<form:options items="${kanbanNameList}" />

												</form:select>
												<br>&nbsp;
											</p>
										</td>

									</tr>
									<tr class="test1">
										<td style="display: inline; width: 40px">標題:<br />
											<p>
												<form:input class="tt1" path="title" placeholder="請輸入標題...." />
												<br>&nbsp;
											</p>
										</td>

									</tr>
									<tr>
										<td>內容:<br />
											<p>
												<form:textarea class="cnt" path="content"
													placeholder="請輸入內容...." />
												<br>&nbsp;
											</p>
										</td>
									</tr>
									<tr>
										<td style="width: 30">圖片:<br>
											<p>
												<img id="blah"
													src="<c:url value='/getimage/${messageBean.articleId}'/>">
											<p>
												<form:input runat="server" id="imgInp" path="productImage"
													type='file' class='form:input-large' />
											</p>
									
										</td>



									</tr>
									<tr style="width: 30px">
										<td align='center' class="blog__item__text"><input
											class="blog__btn" type='submit' value='修改'
											onclick='updateBook()'></td>
										<td align='center' class="blog__item__text"><input
											class="blog__btn" type='submit' value='刪除'
											onclick='confirmDelete(${messageBean.articleId})'></td>
									</tr>

								</table>
							</fieldset>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Blog Section End -->

	<!-- Js Plugins -->
	<script src="../js/jquery-3.3.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/jquery.nice-select.min.js"></script>
	<script src="../js/jquery-ui.min.js"></script>
	<script src="../js/jquery.slicknav.js"></script>
	<script src="../js/mixitup.min.js"></script>
	<script src="../js/owl.carousel.min.js"></script>
	<script src="../js/main.js"></script>
	
	
<script type='text/javascript'>
  function readURL(input) {
   if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function(e) {
     $('#blah').attr('src', e.target.result);
     console.log("321")
    }
    reader.readAsDataURL(input.files[0]); // convert to base64 string
   }
  }
  $("#imgInp").change(function() {
	  console.log("123")
   readURL(this);
  });
 </script>

</body>

</html>
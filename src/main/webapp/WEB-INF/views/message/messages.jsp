<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>論壇首頁</title>
<style>
.hidetext {
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
</style>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
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
<script type="text/javascript">
$(document).ready(function () {
$("#btn1").click(function () {
	var keyword = $("#ipt1").val();
	console.log(keyword);
	$.ajax({
        url: "MessageSearchByKey",
        type: "POST",
        async: false,
        data: {
            Keyword: keyword,
        },
        success: function (list_search) {
            $("#dv1").html("");
            for (var i = 0; i < list_search.length; i++) {
                var messages = list_search[i];
                console.log(messages)
                var tbd = `
                	<div id="dv1" class="col-lg-6 col-md-6 col-sm-6">
					<div class="blog__item">
						<div class="blog__item__pic">
							<img src="<c:url value='/getimage/\${messages.articleId}'/>" />
						</div>
						<div class="blog__item__text">
							<ul>
								<li><i class="fa fa-calendar-o"></i>發表時間:\${messages.time}</li>
								<li><i class="fa fa-comment-o"></i> 5</li>
							</ul>
							<h5>
								<a
									href="<c:url value='message?articleId=\${messages.articleId}'/>">\${messages.title}</a>
							</h5>
							<p class="hidetext">\${messages.content}</p>
							<a
								href="<c:url value='message?articleId=\${messages.articleId}'/>"
								class="blog__btn">READ MORE <span class="arrow_right"></span></a>
						</div>
					</div>
				</div>
				`;
                $("#dv1").append(tbd);    
            }
//             $("#btn1").trigger("click") 
        }
    });
})
})
</script>
</head>
<body>
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
						<div class="blog__sidebar__search">
						<form>
								<input id="ipt1" type="text" placeholder="Search...">
								<button type="button" id="btn1">
									<span class="icon_search"></span>
								</button>
						</form>
						</div>
						<div class="blog__sidebar__item">
<!-- 							<div class="col-lg-6 col-md-6 col-sm-6"> -->
								<div class="blog__item">
									<div class="blog__item__pic">
									<div class="blog__item__text">
										<a href="<c:url value='messagesadd'/>" class="blog__btn">發表文章</a>
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
									<li><a href="<c:url value='${kanbanName}'/>">${kanbanName}</a>
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
<!-- 						<div class="blog__sidebar__item"> -->
<!-- 							<h4>Search By</h4> -->
<!-- 							<div class="blog__sidebar__item__tags"> -->
<!-- 								<a href="#">Apple</a> <a href="#">Beauty</a> <a href="#">Vegetables</a> -->
<!-- 								<a href="#">Fruit</a> <a href="#">Healthy Food</a> <a href="#">Lifestyle</a> -->
<!-- 							</div> -->
<!-- 						</div> -->
					</div>
				</div>
				<div class="col-lg-8 col-md-7">
					<div id="dv1" class="row">
					
						<c:forEach  var='messages' items='${messages}'>
							<div  class="col-lg-6 col-md-6 col-sm-6">
								<div class="blog__item">
									<div class="blog__item__pic">
										<img src="<c:url value='/getimage/${messages.articleId}'/>" />
									</div>
									<div class="blog__item__text">
										<ul>
											<li><i class="fa fa-calendar-o"></i>發表時間:${messages.time}</li>
											<li><i class="fa fa-comment-o"></i> 5</li>
										</ul>
										<h5>
											<a
												href="<c:url value='message?articleId=${messages.articleId}'/>">${messages.title}</a>
										</h5>
										<p class="hidetext">${messages.content}</p>
										<a
											href="<c:url value='message?articleId=${messages.articleId}'/>"
											class="blog__btn">READ MORE <span class="arrow_right"></span></a>
									</div>
								</div>
							</div>
						</c:forEach>

					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Blog Section End -->

	<!-- Js Plugins -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/jquery.slicknav.js"></script>
	<script src="js/mixitup.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>


</body>
</html>
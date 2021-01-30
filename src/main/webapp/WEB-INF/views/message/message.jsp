<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>

<style type="text/css">
#mailbox {
	width: 500px;
	resize: none;
	height: 34px;
	border-radius: 3px;
}

#showdiv {
	width: 200px;
	height: 150px;
	border: 1px solid black;
	border-radius: 5px;
	position: absolute;
	background-color: #FCFCFC;
	box-shadow: 10px 10px 10px #9D9D9D;
	data-role: popup;
	text-align: center;
}

#returnbtn {
	background-color: white;
}

.content {
	color: #000000;
	text-align: center;
}

#cmcolor {
	color: #C0C0C0;
}
</style>
<meta charset="UTF-8">
<title>文章</title>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	data-integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	data-crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.5.1.js"
	data-integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	data-crossorigin="anonymous"></script>
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
							<!-- 							<form action="#"> -->
							<!-- 								<input type="text" placeholder="Search..."> -->
							<!-- 								<button type="submit"> -->
							<!-- 									<span class="icon_search"></span> -->
							<!-- 								</button> -->
							<!-- 							</form> -->
						</div>
						<div class="blog__sidebar__item">

							<div class="blog__item">
								<div class="blog__item__pic">
									<div class="blog__item__text">
										<a href="<c:url value='messagesadd'/>" class="blog__btn">發表文章</a>
										<c:if
											test="${message.memberbean.member_id != memname.member_id}">
											<!-- 										<a href="" class="blog__btn" name="rd" id="rptdiv">檢舉文章</a> -->
											<button
												style="width: 110px; height: 50px; background-color: red; color: white"
												type="button" class="btn btn-primary" data-toggle="modal"
												data-target="#exampleModal">檢舉文章</button>
										</c:if>
										<script type="text/javascript">
											$("#rptdiv").click(function() {
												console.log("123321111")
											})
										</script>
										<c:if
											test="${message.memberbean.member_id == memname.member_id}">
											<a
												href="<spring:url value='/MessageUpdate${message.articleId}' />"
												class="blog__btn">編輯文章 </a>
										</c:if>
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
				<div class="col-lg-8 col-md-7 order-md-1 order-1">
					<div class="blog__details__text">
						<div class="blog__sidebar__item"></div>
						<div class="blog__details__content">
							<div class="row">
								<div class="col-lg-6">
									<div class="blog__details__author">
										<div class="blog__details__author__pic">
											<img src="${message.memberbean.memberphoto}" alt="">
										</div>

										<div class="blog__details__author__text">
											<h6>${message.memberbean.username}</h6>

										</div>
										<div>
											<span>主題:</span>${message.kanbanName}
										</div>
									</div>
								</div>
								<img src="<c:url value='/getimage/${message.articleId}'/>" />

								<p>${message.content}</p>
							</div>

							<div class="col-lg-6">
								<div class="blog__details__widget">

									<!--                                     <div class="blog__details__social"> -->
									<!--                                         <a href="#"><i class="fa fa-facebook"></i></a> -->
									<!--                                         <a href="#"><i class="fa fa-twitter"></i></a> -->
									<!--                                         <a href="#"><i class="fa fa-google-plus"></i></a> -->
									<!--                                         <a href="#"><i class="fa fa-linkedin"></i></a> -->
									<!--                                         <a href="#"><i class="fa fa-envelope"></i></a> -->
									<!--                                     </div> -->
								</div>
							</div>
						</div>
					</div>
					<div class="blog__item__text">
						<h5>留言板</h5>
					</div>
					<c:forEach var='comments' items='${comments}'>
						<div class="blog__item__text">
							<p id="ppp">
							<div class="blog__details__author__pic">
								<img id="imggg" src="${comments.memberbean.memberphoto}" alt="">
							</div>${comments.memberbean.username}:${comments.mailboxContent}</p>
							<i class="fa fa-calendar-o" id="cmcolor">發表時間:${comments.time}</i>
							<div style="text-align: right">
								<c:if
									test="${comments.memberbean.member_id == memname.member_id}">
									<a href="<c:url value='/MailboxDelete/${comments.mailboxId}'/>"
										class="blog__btn">刪除</a>
								</c:if>
							</div>

							<hr>
						</div>
					</c:forEach>
					<div>
						<p id="fku"></p>
					</div>
					<div class="blog__item__text">
						<input type="text" id="mailbox" placeholder="請輸入留言...."></input> <br />
						<br />
						<button id="insertmailbox" name="nd" class="blog__btn">留言</button>
						<br /> <br />
						<script type="text/javascript">
							$("#insertmailbox").click(function() {
								$.ajax({
									url : 'mailbox/add',
									type : 'POST',
									data : {
										nd : $("#mailbox").val()
									},
									success : function(nd) {
									
										var imginfront="<div class='blog__details__author__pic'><img id='imggg' src='"
										var imginback="' alt=''></div>"
										var imgsrc=nd.split(",")
										var imgsrc1=imgsrc[0]
										var imgsrc2=imgsrc[1]
										var bb1="</br>"
										var hh1="<hr>"
										$("#fku").append(imginfront+imgsrc1+imginback+bb1+imgsrc2);
									
										// 						console.log(nd)

									}
								})
							})
						</script>

						<div class="modal fade" id="exampleModal" tabindex="-1"
							role="dialog" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<!--         <h4 style="margin-left:200px;width:300px"  id="exampleModalLabel" >檢舉中..</h4> -->
										<img style="margin-left: 180px" alt=""
											src="images\pngtree-yellow-warning-tape-png-image_3453302.jpg">
										<button style="margin-left: 150px" type="button" class="close"
											data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										<input type="text" id="reportbox" placeholder="請輸入內容...."></input>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">關閉</button>
										<button style="background-color: red" id="reportbtn"
											type="button" class="btn btn-primary">檢舉</button>
									</div>
								</div>
							</div>
						</div>
						
						<script type="text/javascript">
						
						$("#reportbtn").click(function() {
								$.ajax({
									url : 'report/add',
									type : 'POST',
									data : {
										rd : $("#reportbox").val()
									},
									success : function(data) {
										
										if (data=="檢舉成功")
											alert("檢舉成功")
											$('#exampleModal').modal('hide');
										
										
									}
								})
							})
						</script>

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
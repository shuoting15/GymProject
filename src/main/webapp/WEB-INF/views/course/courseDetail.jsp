<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link href="https://fonts.googleapis.com/css?family=Rajdhani:300,400,500,600,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,400i,600,600i,700,700i&display=swap"
        rel="stylesheet">

    <link rel="stylesheet" href="http://cdn.bootstrapmb.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/all.min.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/lightcase.css">
    <link rel="stylesheet" href="css/flaticon.css">
    <link rel="stylesheet" href="css/swiper.min.css">

    <link rel="shortcut icon" href="images/favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="css/stylecourses.css">
<script>
function bookfunction(){
	if(confirm("本課程確認無誤後請按確定!")){  
		return true;  
	}  
		return false;  
	}
</script>
</head>
<body>
<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/top.jsp" />
<!-- 上面的圖 -->
    <section class="page-header bg_img" data-background="images/banner.jpg">
        <div class="container">
            <h3 class="title"><span class="shape-wrapper"><span class="shape"></span>課程詳情<span
                        class="shape"></span></span></h3>
        </div>
    </section>
    
    <!-- Breadcrumb Section Ends Here -->
    <!-- Trainer Section Starts Here -->
    <section>
    <div class="expert-trainer-details padding-bottom padding-top">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 mb-5 mb-lg-0">
                    <article>
                        <div class="expert-single-item">
                            <div class="expert-thumb">
                                <img src="<c:url value='/getCPicture/${course.courseId}'/>" alt="feature/07.jpg">
                            </div>
                            <div class="expert-single-content">
                                <h3 class="title">關於本課程</h3>
                                <p>${course.description}</p>
                                
                                </div>
                        </div>
                    </article>
                </div>
                <div class="col-lg-4">
                    <aside class="sidebar">
                        <div class="widget widget-category widget-info">
                            <h5 class="widget-title">Class Info</h5>
                            <ul>
                                <li>
                                    <a href="#">class name<span>${course.title}</span></a>
                                </li>
                                <li>
                                    <a href="#">trainers<span>${course.courseCoachBean.name}</span></a>
                                </li>
                                <li>
                                    <a href="#">class date<span>${course.date} ${course.starttime}~${course.endtime}</span></a>
                                </li>
                                <li>
                                    <a href="#">location<span>${course.location}</span></a>
                                </li>
                                <li>
                                    <a href="#">class fee<span>${course.price} 點</span></a>
                                </li>
                                <li>
                                    <a href="#">per class<span>${course.max} Students</span></a>
                                </li>
                                <li>
                                
                                <c:choose>
                                	<c:when test="${course.max - course.selected == 0}">
                                		<a id="a${course.max - course.selected}" href="<c:url value='/booking/course?id=${course.courseId}'/>" class="btn btn-primary" style="color:white;" onclick="return bookfunction()">
                                		<span class="glyphicon-info-sigh glyphicon"></span>已額滿</a>
                                		<script type="text/javascript">
                                $("#a0").attr("class","btn btn-secondary").attr("disabled",true).css("pointer-events","none");
                                </script>
                                	</c:when>
                                	<c:otherwise>
                                		<a href="<c:url value='/booking/course?id=${course.courseId}'/>" class="btn btn-primary" style="color:white;" onclick="return bookfunction()">
                                		<span class="glyphicon-info-sigh glyphicon"></span>立即預約</a>
                                	</c:otherwise>
                                </c:choose>
                                
                                	
                                </li>
                            </ul>
                        </div>
                        
                    </aside>
                </div>
            </div>
        </div>
    </div>
    </section>
    <!-- Trainer Section Ends Here -->
    
    
    <!-- JavaScript File Links -->
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/modernizr-3.6.0.min.js"></script>
    <script src="js/plugins.js"></script>
    <script src="http://cdn.bootstrapmb.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="js/isotope.pkgd.min.js"></script>
    <script src="js/swiper.min.js"></script>
    <script src="js/waypoint.js"></script>
    <script src="js/counterup.min.js"></script>
    <script src="js/lightcase.js"></script>
    <script src="js/wow.min.js"></script>
    <script src="js/maincoursesdetail.js"></script>
</body>
</html>
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

<link rel="stylesheet"
	href="http://cdn.bootstrapmb.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/all.min.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/lightcase.css">
<link rel="stylesheet" href="css/flaticon.css">
<link rel="stylesheet" href="css/swiper.min.css">

<link rel="shortcut icon" href="images/trainers/favicon.png"
	type="image/x-icon">
<link rel="stylesheet" href="css/stylecourses.css">
<title>Insert title here</title>
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
    <section class="page-header bg_img" data-background="./assets/images/banner/black.jpg">
        <div class="container">
            <h3 class="title"><span class="shape-wrapper"><span class="shape"></span>課程詳情<span
                        class="shape"></span></span></h3>
        </div>
    </section>
    <section>
    <!-- Breadcrumb Section Ends Here -->
    <!-- Trainer Section Starts Here -->
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
                                
                                <h3 class="sub-title">預約學生名單(${course.selected} / ${course.max}) </h3>
                                <c:forEach var='name' items='${namelist}'>
                                <ul>
                                    <li>${name}</li>
<!--                                     <li>Enim penatibus</li> -->
<!--                                     <li>metus estlaoreet nullam.</li> -->
<!--                                     <li>metus estlaoreet nullam.</li> -->
<!--                                     <li>suspendisse convallis</li> -->
<!--                                     <li>suspendisse convallis</li> -->
<!--                                     <li>blandit convallis neque eget</li> -->
<!--                                     <li>blandit convallis neque eget</li> -->
                                </ul>
                                </c:forEach>
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
                                 	<a href="<c:url value='/courseUpdate?id=${course.courseId}'/>" class="btn btn-primary">
                                 <span class="glyphicon-info-sigh glyphicon"></span>編輯</a> 
                                </li>
                            </ul>
                        </div>
                        
                    </aside>
                </div>
            </div>
        </div>
    </div>
    <!-- Trainer Section Ends Here -->
    <!-- Footer Section Starts Here -->
    <footer class="footer-section">
        <div class="footer-top">
            <div class="container">
                <div class="row mb-45-none">
                    <div class="col-lg-4 col-md-6">
                        <div class="footer-widget widget-about">
                            <h5 class="widget-title">About Us</h5>
                            <p>Lorem ipsum dolor sit amet, mauris libero congue eget pulvinar, cras ut mus tempus dolor,
                                ante tortor ornare ante arcu nam </p>
                            <h6 class="sub-title">Subscribe</h6>
                            <form class="footer-form">
                                <input type="text" placeholder="Your Email" class="footer-input">
                                <label for="f1"><i class="fas fa-arrow-right"></i></label>
                                <input type="submit" value="" id="f1">
                            </form>
                            <div class="social-icons">
                                <a href="#"><i class="fab fa-facebook-f"></i></a>
                                <a href="#"><i class="fab fa-twitter"></i></a>
                                <a href="#"><i class="fab fa-linkedin-in"></i></a>
                                <a href="#"><i class="fab fa-google-plus-g"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-6">
                        <div class="footer-widget widget-link">
                            <h5 class="widget-title">Useful Link</h5>
                            <ul>
                                <li>
                                    <a href="#">Weight Loss</a>
                                </li>
                                <li>
                                    <a href="#">Pakckages</a>
                                </li>
                                <li>
                                    <a href="#">Class Time</a>
                                </li>
                                <li>
                                    <a href="#">About Us</a>
                                </li>
                                <li>
                                    <a href="#">Offer</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="footer-widget widget-post">
                            <h5 class="widget-title">Our Blog post</h5>
                            <ul>
                                <li>
                                    <a class="post-title" href="#">Ullamco est amet quis tullam cursus, metus.</a>
                                    <span>05 may 2017</span>
                                </li>
                                <li>
                                    <a class="post-title" href="#">Ullamco est amet quis tullam cursus, metus.</a>
                                    <span>05 may 2017</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="footer-widget widget-form">
                            <h5 class="widget-title">Contact Form</h5>
                            <form class="footer-contact-form">
                                <input type="text" placeholder="Name">
                                <input type="text" placeholder="Email">
                                <textarea name="footer-message" id="f2" class="footer-textarea"
                                    placeholder="Message"></textarea>
                                <input type="submit" value="submit Now">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer-bottom align-items-center d-flex">
            <div class="container">
                <p class="m-0">&copy; Copyright <a href="http://www.bootstrapmb.com/">Gymio</a> - 2019</p>
            </div>
        </div>
    </footer>
    <!-- Footer Section Ends Here -->
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
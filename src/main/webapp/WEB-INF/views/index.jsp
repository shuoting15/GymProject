<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
    <link rel="stylesheet" href="css/stylecoach.css">
    

    <title>Gymio - Fitness and Gym HTML Template</title>

</head>

<body>
	<jsp:include page="/fragment/top.jsp" />

	<section class="page-header bg_img"
		data-background="images/indeximg.jpg">
		<div class="container">
			<h3 class="title">
				<span class="shape-wrapper"><span class="shape"></span>GYM<span
					class="shape"></span></span>
			</h3>
		</div>
	</section>

    <section class="body-building-section padding-top padding-bottom">
        <div class="container">
            <div class="body-building-slider">
                <div class="swiper-wrapper">
                    <div class="swiper-slide">
                        <div class="body-building-item">
                            <div class="body-building-inner">
                                <div class="body-building-thumb">
                                    <a href="classes.html">
                                        <img src="assets/images/body-builder/01.jpg" alt="body-builder">
                                    </a>
                                </div>
                                <div class="body-building-content">
                                    <div class="building-content">
                                        <h4 class="title"><a href="classes.html">Body Building</a></h4>
                                    </div>
                                    <div class="building-category">
                                        <img src="assets/images/body-builder/01.png" alt="body-builder">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="swiper-slide">
                        <div class="body-building-item">
                            <div class="body-building-inner">
                                <div class="body-building-thumb">
                                    <a href="classes.html">
                                        <img src="assets/images/body-builder/02.jpg" alt="body-builder">
                                    </a>
                                </div>
                                <div class="body-building-content">
                                    <div class="building-content">
                                        <h4 class="title"><a href="classes.html">weight lifting</a></h4>
                                    </div>
                                    <div class="building-category">
                                        <img src="assets/images/body-builder/02.png" alt="body-builder">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="swiper-slide">
                        <div class="body-building-item">
                            <div class="body-building-inner">
                                <div class="body-building-thumb">
                                    <a href="classes.html">
                                        <img src="assets/images/body-builder/03.jpg" alt="body-builder">
                                    </a>
                                </div>
                                <div class="body-building-content">
                                    <div class="building-content">
                                        <h4 class="title"><a href="classes.html">cycling & cardio</a></h4>
                                    </div>
                                    <div class="building-category">
                                        <img src="assets/images/body-builder/03.png" alt="body-builder">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="body-building-pagination text-center">
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
            </div>
        </div>
    </section>
    <!-- Body Building Slider Section Ends Here -->
    <!-- About Counter Section Starts Here -->
    <section class="about-counter padding-bottom">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-6 ">
                    <div class="about-counter-content">
                        <div class="section-header">
                            <h2 class="title">
								為何要選擇我們GYM
                            </h2>
                        </div>
                        <p>我們擁有最好的教練團隊，最齊全的商品資訊，以及最好的場地</p>
                        <ul>
                            <li>交通方便</li>
                            <li>器材充足</li>
                            <li>優質教練</li>
                            <li>多元團課</li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-6 ">
                    <div class="row mb-30-none">
                        <div class="col-6">
                            <div class="about-counter-item">
                                <h2 class="title"><span class="counter">53</span></h2>
                                <p>分店</p>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="about-counter-item">
                                <h2 class="title"><span class="counter">41</span>k</h2>
                                <p>滿意的客戶</p>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="about-counter-item">
                                <h2 class="title"><span class="counter">327</span></h2>
                                <p>已完成團課</p>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="about-counter-item">
                                <h2 class="title"><span class="counter">15</span>y</h2>
                                <p>已開設年資</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- About Counter Section Ends Here -->
    <!-- Overview Section Section Starts Here -->
    
    <!-- Overview Section Section Ends Here -->
    <!-- Feature Section Starts Here -->
    <section class="feature-section padding-bottom padding-top">
        <div class="container">
            <div class="section-header-wrapper">
                <div class="section-header">
                    <h2 class="title">
                        精選團課
                    </h2>
                    <p>包含拳擊，飛輪，等優質課程</p>
                </div>
                <div class="common-navigation d-flex ">
                    <div class="common-prev navigation"><i class="fas fa-angle-left"></i></div>
                    <div class="common-next active navigation"><i class="fas fa-angle-right"></i></div>
                </div>
            </div>
            <div class="feature-slider">
                <div class="swiper-wrapper">
                <c:forEach var='courseList' items='${courseList}'>
                    <div class="swiper-slide">
                        <div class="feature-item">
                            <div class="feature-thumb">
                                <a href="classes.html"><img src="<c:url value='/getCPicture/${courseList.courseId}'/>" alt="feature"></a>
                            </div>
                            <div class="feature-content">
                                <div class="feature-header">
                                    <h4 class="title"><a href="<c:url value='/course?id=${courses.courseId}'/>" target="_blank">${courseList.title}</a></h4>
                                    <p>${courseList.description}</p>
                                </div>
                                <div class="feature-footer d-flex flex-wrap align-items-center justify-content-between">
                                    <div class="d-flex flex-wrap">
                                        <div class="feature-author-thumb">
<!--                                             <a href="trainer-details.html"><img src="assets/images/feature/01.png" alt="feature"></a> -->
                                        </div>
                                        <div class="feature-author-content">
                                            <h6 class="sub-title">${courseList.courseCoachBean.name}</h6>
                                        </div>
                                    </div>
                                    <a href="<c:url value='/course?id=${courses.courseId}'/>" class="join-now-button">join now</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </section>
    <!-- Feature Section Ends Here -->
    <!-- BMI Calculate Section Starts here -->
   
    <!-- BMI Calculate Section Ends here -->
    <!-- Expert Section Starts Here -->
    <section class="expert-trainers padding-bottom padding-top">
        <div class="container">
            <div class="section-header-wrapper">
                <div class="section-header">
                    <h2 class="title">
                        專業個人教練
                    </h2>
                    <p> 我們教練擁有各種專長，重量訓練，體態雕塑，提升運動表現</p>
                </div>
                <div class="common-navigation d-flex ">
                    <div class="common-prev navigation"><i class="fas fa-angle-left"></i></div>
                    <div class="common-next active navigation"><i class="fas fa-angle-right"></i></div>
                </div>
            </div>
            <div class="expert-slider">
            <div class="swiper-wrapper">
            <c:forEach var='coachList' items='${coachList}'>
                    <div class="swiper-slide">
                        <div class="expert-item">
                            <div class="expert-thumb">
                                <a href="trainers.html"><img src="<c:url value='/getPicture/${coachList.coachId}'/>"  style="width: 500px;height: 450px"  alt="trainers"></a>
                            </div>
                            <div class="expert-content">
                                <div class="expert-info">
                                    <h4 class="sub-title"><a href="<c:url value='coach?id=${coachList.coachId}'/>">${coachList.coachName}</a></h4>
                                    <span>${coachList.coachExpertise}</span>
                                </div>
                                <a class="expert-link" href="<c:url value='coach?id=${coachList.coachId}'/>">
                                    <i class="fas fa-link"></i>
                                </a>
                            </div>
                        </div>
                    </div>
               </c:forEach>
                </div>
            </div>
        </div>
    </section>
    <!-- Expert Section Ends Here -->
    <!-- Schedule Section Starts Here -->
    <section class="schedule-section padding-bottom">
        <div class="container">
            <div class="schedule-wrapper bg_img" data-background="./assets/images/schedule/schedule--bg.png">
                <div class="schedule-header text-center">
                    <h3 class="title text-md-left">
                        團課時間
                    </h3>
                    <p class="text-md-right">courses schedule will be change by instructor</p>
                </div>
                <table class="schedule-table" role="table">
                    <thead role="rowgroup">
                        <tr role="row">
                            <th role="columnheader">Course Name</th>
                            <th role="columnheader">Course Duration</th>
                            <th role="columnheader">Class Time</th>
                            <th role="columnheader">Instructor</th>
                            <th role="columnheader">Note</th>
                        </tr>
                    </thead>
                    <tbody role="rowgroup">
                        <tr role="row">
                            <td role="cell" data-input="Course Name">Weight Loss</td>
                            <td role="cell" data-input="Course Duration">06 Mounth</td>
                            <td role="cell" data-input="Course Time">
                                <span class="class-date">Satday to Monday</span>
                                <span class="class-time">10.00 am - 02.00 pm</span>
                            </td>
                            <td role="cell" data-input="Instructor">
                                <div class="instructor">
                                    <div class="thumb">
                                        <a href="#"><img src="assets/images/schedule/01.png" alt="schedule"></a>
                                    </div>
                                    <div class="content">
                                        <a href="#">shijika loo</a>
                                        <span>weight loss expert</span>
                                        <span>call - 0147854145</span>
                                    </div>
                                </div>
                            </td>
                            <td role="cell" data-input="Note">pellentesque odio qu amet ut amet morbi </td>
                        </tr>
                        <tr role="row">
                            <td role="cell" data-input="Course Name">Meditation</td>
                            <td role="cell" data-input="Course Duration">08 Mounth</td>
                            <td role="cell" data-input="Course Time">
                                <span class="class-date">Satday to Monday</span>
                                <span class="class-time">10.00 am - 02.00 pm</span>
                            </td>
                            <td role="cell" data-input="Instructor">
                                <div class="instructor">
                                    <div class="thumb">
                                        <a href="#"><img src="assets/images/schedule/02.png" alt="schedule"></a>
                                    </div>
                                    <div class="content">
                                        <a href="#">shijika loo</a>
                                        <span>weight loss expert</span>
                                        <span>call - 0147854145</span>
                                    </div>
                                </div>
                            </td>
                            <td role="cell" data-input="Note">pellentesque odio qu amet ut amet morbi </td>
                        </tr>
                        <tr role="row">
                            <td role="cell" data-input="Course Name">Weight Lifting</td>
                            <td role="cell" data-input="Course Duration">03 Mounth</td>
                            <td role="cell" data-input="Course Duration">
                                <span class="class-date">Satday to Monday</span>
                                <span class="class-time">10.00 am - 02.00 pm</span>
                            </td>
                            <td role="cell" data-input="Instructor">
                                <div class="instructor">
                                    <div class="thumb">
                                        <a href="#"><img src="assets/images/schedule/03.png" alt="schedule"></a>
                                    </div>
                                    <div class="content">
                                        <a href="#">shijika loo</a>
                                        <span>weight loss expert</span>
                                        <span>call - 0147854145</span>
                                    </div>
                                </div>
                            </td>
                            <td role="cell" data-input="Note">pellentesque odio qu amet ut amet morbi </td>
                        </tr>
                        <tr role="row">
                            <td role="cell" data-input="Course Name">Body Building</td>
                            <td role="cell" data-input="Course Duration">1 Year</td>
                            <td role="cell" data-input="Course Time">
                                <span class="class-date">Satday to Monday</span>
                                <span class="class-time">10.00 am - 02.00 pm</span>
                            </td>
                            <td role="cell" data-input="Instructor">
                                <div class="instructor">
                                    <div class="thumb">
                                        <a href="#"><img src="assets/images/schedule/04.png" alt="schedule"></a>
                                    </div>
                                    <div class="content">
                                        <a href="#">shijika loo</a>
                                        <span>weight loss expert</span>
                                        <span>call - 0147854145</span>
                                    </div>
                                </div>
                            </td>
                            <td role="cell" data-input="Note">pellentesque odio qu amet ut amet morbi </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
    <!-- Schedule Section Ends Here -->
    <!-- Overview Section Two Starts Here -->
 
    <!-- Overview Section Two Ends Here -->
    <!-- Package Section Two Ends Here -->
    <section class="package-section padding-bottom padding-top">
        <div class="container">
            <div class="section-header">
                <h2 class="title">
                    our awesome packages
                </h2>
                <p> Augue urna molestie mi adipiscing vulputate pede sedmassa Praesquam massa, sodales velit turpis
                    intellu.</p>
            </div>
            <div class="row mb-30-none justify-content-center">
                <div class="col-lg-4 col-md-6">
                    <div class="package-item">
                        <div class="package-header">
                            <span>$239.02</span>
                            <h3 class="title">silver pack</h3>
                        </div>
                        <div class="package-content">
                            <ul>
                                <li>free hand</li>
                                <li>06 month subscribe</li>
                                <li>weight loss</li>
                                <li>body building</li>
                                <li>12 month train</li>
                            </ul>
                            <a href="#0" class="custom-button">Choose Pack</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="package-item">
                        <div class="package-header">
                            <span>$459.02</span>
                            <h3 class="title">golden pack</h3>
                        </div>
                        <div class="package-content">
                            <ul>
                                <li>free hand</li>
                                <li>06 month subscribe</li>
                                <li>weight loss</li>
                                <li>body building</li>
                                <li>12 month train</li>
                            </ul>
                            <a href="#0" class="custom-button">Choose Pack</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="package-item">
                        <div class="package-header">
                            <span>$349.02</span>
                            <h3 class="title">diamond pack</h3>
                        </div>
                        <div class="package-content">
                            <ul>
                                <li>free hand</li>
                                <li>06 month subscribe</li>
                                <li>weight loss</li>
                                <li>body building</li>
                                <li>12 month train</li>
                            </ul>
                            <a href="#0" class="custom-button">Choose Pack</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Package Section Two Ends Here -->
    <!-- Newslater Section Starts Here -->
    <!-- Newslater Section Ends Here -->
    <!-- Blog Section Starts Here -->
    <section class="blog-section padding-bottom padding-top">
        <div class="container">
            <div class="section-header">
                <h2 class="title">
                    最新教學&文章
                </h2>
                <p> </p>
            </div>
            <div class="row mb-30-none justify-content-center blog-section-wrapper">
                <div class="col-xl-4 col-md-6">
                    <div class="post-item">
                        <div class="post-thumb">
                            <a href="blog-details.html"><img src="assets/images/blog/01.jpg" alt="blog"></a>
                        </div>
                        <div class="post-content">
                            <h4 class="title"><a href="blog-details.html">fringilla scelerisque over the game to industry</a></h4>
                            <p>mauris elementum eget, ingererat donec, vestibulum felis. Ruomauris enim volutpat nec nispdonec,vlum felis. Rutrum odio</p>
                            <div class="meta-post d-flex justify-content-between">
                                <span>Posted By <a href="#">Admin</a></span><span><a href="#">22 may 2019</a></span>
                            </div>
                            <a href="blog.html" class="blog-button"><i class="fas fa-link"></i></a>
                        </div>
                    </div>
                </div>
                <div class="col-xl-4 col-md-6">
                    <div class="post-item">
                        <div class="post-thumb">
                            <a href="blog-details.html"><img src="assets/images/blog/02.jpg" alt="blog"></a>
                        </div>
                        <div class="post-content">
                            <h4 class="title"><a href="blog-details.html">fringilla scelerisque over the game to industry</a></h4>
                            <p>mauris elementum eget, ingererat donec, vestibulum felis. Ruomauris enim volutpat nec nispdonec,vlum felis. Rutrum odio</p>
                            <div class="meta-post d-flex justify-content-between">
                                <span>Posted By <a href="#">Admin</a></span><span><a href="#">22 may 2019</a></span>
                            </div>
                            <a href="blog.html" class="blog-button"><i class="fas fa-link"></i></a>
                        </div>
                    </div>
                </div>
                <div class="col-xl-4 col-md-6">
                    <div class="post-item">
                        <div class="post-thumb">
                            <a href="blog-details.html"><img src="assets/images/blog/03.jpg" alt="blog"></a>
                        </div>
                        <div class="post-content">
                            <h4 class="title"><a href="blog-details.html">fringilla scelerisque over the game to industry</a></h4>
                            <p>mauris elementum eget, ingererat donec, vestibulum felis. Ruomauris enim volutpat nec nispdonec,vlum felis. Rutrum odio</p>
                            <div class="meta-post d-flex justify-content-between">
                                <span>Posted By <a href="#">Admin</a></span><span><a href="#">22 may 2019</a></span>
                            </div>
                            <a href="blog.html" class="blog-button"><i class="fas fa-link"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Blog Section Ends Here -->
    <!-- Sponsor Slider Section Starts Here -->
    <!-- Sponsor Slider Section Ends Here -->
    <!-- Footer Section Starts Here -->
    <!-- Footer Section Ends Here -->
    
    <!-- JavaScript File Links -->
    <!-- Sponsor Slider Section Ends Here -->
    <!-- Footer Section Starts Here -->
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
    <script src="js/maincoach.js"></script>

</body>

</html>
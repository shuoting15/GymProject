<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

    <title>個人教練預約查詢</title>

</head>

<body>
	<jsp:include page="/fragment/top.jsp" />
    <!-- Page Header EndsHere -->
    <!-- Breadcrumb Section Starts Here -->
   <section class="page-header bg_img"
		data-background="./assets/images/banner/banner.jpg">
		<div class="container">
			<h3 class="title">
				<span class="shape-wrapper"><span class="shape"></span>Reservation<span
					class="shape"></span></span>
			</h3>
		</div>
	</section>
	<div class="breadcrumb-section1">
		<div class="container">
			<div class="breadcrumb-wrapper">
				<div class="breadcrumb-title">
					<h6 class="title">教練預約查詢</h6>
				</div>
				<ul class="breadcrumb"
					style="background: transparent; margin: -5px -10px; padding: 0;">
					<li><a href="index.html">首頁</a></li>
					<li>教練預約查詢</li>
				</ul>
			</div>
		</div>
	</div>
    <!-- Breadcrumb Section Ends Here -->
    <!-- Schedule Section Starts Here -->
    <section class="schedule-section padding-bottom padding-top">
        <div class="container">
            <div class="schedule-wrapper bg_img" data-background="./assets/images/schedule/schedule--bg.png">
                <div class="schedule-header text-center">
                    <h3 class="title text-md-left">
                        個人教練預約時間
                    </h3>
                    <p class="text-md-right">2小時內無法取消</p>
                </div>
                <table class="schedule-table" role="table">
                    <thead role="rowgroup">
                        <tr role="row">
                            <th role="columnheader">教練姓名</th>
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
                            <td role="cell" data-input="Course Name">Body Building</td>
                            <td role="cell" data-input="Course Duration">1 Year</td>
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
                    </tbody>
                </table>
            </div>
        </div>
    </section>
    <!-- Schedule Section Ends Here -->
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
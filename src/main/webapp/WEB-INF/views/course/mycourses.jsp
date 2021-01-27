<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<link rel="stylesheet"
    href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<link rel="stylesheet"
    href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>MyCourses</title>
<%-- <link rel="stylesheet" href='${pageContext.request.contextPath}/css/styles.css' type="text/css"> --%>
<script type="text/javascript">
function bookfunction(){
	if(confirm("確定要取消嗎?")){  
		return true;  
	}  
		return false;  
	}
</script>
</head>
<body>
<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/top.jsp" />
	<section class="page-header bg_img" data-background="images/banner.jpg">
        <div class="container">
            <h3 class="title"><span class="shape-wrapper"><span class="shape"></span>我的課程<span
                        class="shape"></span></span></h3>
        </div>
    </section>
	<div align="right">
		<p>
			<a href="<c:url value='/courses'/>" class="btn btn-dark"> <span
				class="glyphicon-info-sigh glyphicon"></span>返回課程列表
			</a>
		</p>
	</div>
	<section>
        <div>
            <div class="container" style="text-align: center" >
                <h3>已預約課程</h3>
                <h3 style="color: red;">${message}</h3>
            </div>
        </div>
    </section>
    <hr style="height:1px;border:none;color:#333;background-color:#333;">
    <section class="container">
        <div class="row">
        <c:forEach var='course' items='${mycourses}'>
            <div class="col-sm-6 col-md-3" style="width: 360px; height: 360px">
                <div class="thumbnail" style="width: 320px; height: 330px">
<%--                 <img  width='100' height='200' src="<c:url value='/getPicture/${course.courseId}'/>" /> --%>
                    <div class="caption">
                    <img width='264px' src="<c:url value='/getCPicture/${course.courseBean.courseId}'/>" />
                        <p>
                        <a href="<c:url value='/course?id=${course.courseBean.courseId}'/>"><b style='font-size: 20px;'> ${course.courseBean.title}</b></a>
<%--                         <a href="<c:url value='/course?id=${course.courseBean.courseId}'/>">he</a> --%>
<%--                             <b style='font-size: 20px;'>${course.courseBean.title}</b> --%>
                        </p>
                        <p>日期: ${course.courseBean.date}</p>
                        <p>時段: ${course.courseBean.starttime} ~ ${course.courseBean.endtime}</p>
                        <p>教室: ${course.courseBean.location}</p>
<%--                         <p>教練:${course.c_id}</p> --%>
<%--                         <p>分類:${course.c_category}</p> --%>
                        <p><a href="<c:url value='/unbooking/course?id=${course.id}'/>" class="btn btn-dark" onclick="return bookfunction()">
                        <span class="glyphicon-info-sigh glyphicon"></span>取消預約
                        </a>
                        
<%--                         <a href="<c:url value='course?id=${course.courseId}'/>" class="btn btn-primary"> --%>
<!--                         <span class="glyphicon-info-sigh glyphicon"></span>詳細資訊 -->
<!--                         </a></p> -->
<%--                         <a href="<spring:url value='product.json?id=${product.bookId}' />"    --%>
<!-- 								class="btn btn-primary">  -->
<!-- 								<span class="glyphicon-info-sigh glyphicon"></span>JSON -->
<!-- 							</a> -->
<!--                         </p> -->
                    </div>
                </div>
            </div>
            </c:forEach>
        </div>
        
        
    </section>
    
    <section>
        <div>
            <div class="container" style="text-align: center" >
                <h3>進行中課程</h3>
<%--                 <h3 style="color: red;">${message}</h3> --%>
            </div>
        </div>
    </section>
<!--     <div align="right"> -->
<%--     <p><a href="<c:url value='/courses/mycourses'/>" class="btn btn-primary"> --%>
<!--     <span class="glyphicon-info-sigh glyphicon"></span>我的課程 -->
<!--     </a> -->
<%--     <a href="<c:url value='/courses'/>" class="btn btn-primary"> --%>
<!--     <span class="glyphicon-info-sigh glyphicon"></span>課程列表 -->
<!--     </a></p> -->
<!--     </div> -->
    <hr style="height:1px;border:none;color:#333;background-color:#333;">
    <section class="container">
        <div class="row">
        <c:forEach var='nowcourse' items='${mynowlist}'>
            <div class="col-sm-6 col-md-3" style="width: 360px; height: 320px">
                <div class="thumbnail" style="width: 320px; height: 300px">
<%--                 <img  width='100' height='200' src="<c:url value='/getPicture/${course.courseId}'/>" /> --%>
                    <div class="caption">
                    <img width='264px' src="<c:url value='/getCPicture/${nowcourse.courseBean.courseId}'/>" />
                        <p>
                            <b style='font-size: 20px;'>${nowcourse.courseBean.title}</b>
                        </p>
                        <p>日期: ${nowcourse.courseBean.date}</p>
                        <p>時段: ${nowcourse.courseBean.starttime} ~ ${nowcourse.courseBean.endtime}</p>
                        <p>教室: ${nowcourse.courseBean.location}</p>
<%--                         <p>教練:${course.c_id}</p> --%>
<%--                         <p>分類:${course.c_category}</p> --%>
<%--                         <p><a href="<c:url value='/unbooking/course?id=${course.id}'/>" class="btn btn-primary" onclick="return bookfunction()"> --%>
<!--                         <span class="glyphicon-info-sigh glyphicon"></span>取消預約 -->
<!--                         </a> -->
                        
<%--                         <a href="<c:url value='course?id=${course.courseId}'/>" class="btn btn-primary"> --%>
<!--                         <span class="glyphicon-info-sigh glyphicon"></span>詳細資訊 -->
<!--                         </a></p> -->
<%--                         <a href="<spring:url value='product.json?id=${product.bookId}' />"    --%>
<!-- 								class="btn btn-primary">  -->
<!-- 								<span class="glyphicon-info-sigh glyphicon"></span>JSON -->
<!-- 							</a> -->
<!--                         </p> -->
                    </div>
                </div>
            </div>
            </c:forEach>
        </div>
        
        
    </section>
    
    <section>
        <div>
            <div class="container" style="text-align: center" >
                <h3>已結束課程</h3>
<%--                 <h3 style="color: red;">${message}</h3> --%>
            </div>
        </div>
    </section>
<!--     <div align="right"> -->
<%--     <p><a href="<c:url value='/courses/mycourses'/>" class="btn btn-primary"> --%>
<!--     <span class="glyphicon-info-sigh glyphicon"></span>我的課程 -->
<!--     </a> -->
<%--     <a href="<c:url value='/courses'/>" class="btn btn-primary"> --%>
<!--     <span class="glyphicon-info-sigh glyphicon"></span>課程列表 -->
<!--     </a></p> -->
<!--     </div> -->
    <hr style="height:1px;border:none;color:#333;background-color:#333;">
    <section class="container">
        <div class="row">
        <c:forEach var='fcourse' items='${finishedlist}'>
            <div class="col-sm-6 col-md-3" style="width: 360px; height: 320px">
                <div class="thumbnail" style="width: 320px; height: 300px">
                <img  width='264' src="<c:url value='/getCPicture/${fcourse.courseBean.courseId}'/>" />
                    <div class="caption">
                        <p>
                            <b style='font-size: 20px;'>${fcourse.courseBean.title}</b>
                        </p>
                        <p>日期: ${fcourse.courseBean.date}</p>
                        <p>時段: ${fcourse.courseBean.starttime} ~ ${fcourse.courseBean.endtime}</p>
                        <p>教室: ${fcourse.courseBean.location}</p>
<%--                         <p>教練:${course.c_id}</p> --%>
<%--                         <p>分類:${course.c_category}</p> --%>
<%--                         <p><a href="<c:url value='/unbooking/course?id=${course.id}'/>" class="btn btn-primary" onclick="return bookfunction()"> --%>
<!--                         <span class="glyphicon-info-sigh glyphicon"></span>取消預約 -->
<!--                         </a> -->
                        
<%--                         <a href="<c:url value='course?id=${course.courseId}'/>" class="btn btn-primary"> --%>
<!--                         <span class="glyphicon-info-sigh glyphicon"></span>詳細資訊 -->
<!--                         </a></p> -->
<%--                         <a href="<spring:url value='product.json?id=${product.bookId}' />"    --%>
<!-- 								class="btn btn-primary">  -->
<!-- 								<span class="glyphicon-info-sigh glyphicon"></span>JSON -->
<!-- 							</a> -->
<!--                         </p> -->
                    </div>
                </div>
            </div>
            </c:forEach>
        </div>
        
        
    </section>
</body>
</html>
    
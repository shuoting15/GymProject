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
<!--     <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,400i,600,600i,700,700i&display=swap" -->
<!--         rel="stylesheet"> -->

    <link rel="stylesheet" href="http://cdn.bootstrapmb.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/all.min.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/lightcase.css">
    <link rel="stylesheet" href="css/flaticon.css">
    <link rel="stylesheet" href="css/swiper.min.css">

    <link rel="shortcut icon" href="images/favicon.png" type="image/x-icon">
    
<link rel="stylesheet"
    href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<link rel="stylesheet"
    href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<link rel="stylesheet" href="css/stylecoach.css">
<title>MyCourses</title>
<%-- <link rel="stylesheet" href='${pageContext.request.contextPath}/css/styles.css' type="text/css"> --%>
<script type="text/javascript">
	function bookfunction(price, id) {
        Swal.fire({
            type: 'warning', // 彈框類型
            title: '確定要取消預約本課程嗎?', //標題
            text: "如確定將立即退還"+ price + "點!", //顯示內容            
            icon: 'question',
            confirmButtonColor: '#3085d6',// 確定按鈕的 顏色
            confirmButtonText: '確定',// 確定按鈕的 文字
            showCancelButton: true, // 是否顯示取消按鈕
            cancelButtonColor: '#d33', // 取消按鈕的 顏色
            cancelButtonText: "取消", // 取消按鈕的 文字
            focusCancel: true, // 是否聚焦 取消按鈕

        }).then((isConfirm) => {

            try {
                //判斷 是否 點擊的 確定按鈕
                if (isConfirm.value) {
                    $.ajax({
                        url: "<c:url value='/unbooking/course'/>",

                        dataType: "JSON",
                        data: { "id": id },
                        success: function (data) {
                        	console.log(data);
                            if (data.messages == "課程開始前1小時無法取消!") {
                                Swal.fire("Sorry!", "課程開始前1小時無法取消!", "error");
                            } else {
                                Swal.fire("取消成功", "請確認點數是否退還", "success");
                            }
                            setTimeout(function(){
    	            			window.location.reload();//刷新
    		            		},1500)
                            
                        }
                    })
                }
                else {
                    Swal.fire("取消操作", "", "error");

                }
            } catch (e) {
                alert(e);
            }

        });
        //      	if(confirm("確定預約本課程嗎? 確認無誤後請按確定!")){  
        //      		return true;  
        //      	}  
        //      	return false;  
    }
</script>
</head>
<body>
<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/top.jsp" />
	<section class="page-header bg_img" data-background="images/banner.jpg">
        <div class="container">
            <h3 class="title"><span class="shape-wrapper"><span class="shape"></span>My Courses<span
                        class="shape"></span></span></h3>
        </div>
    </section>
<!-- 	<div align="right"> -->
<!-- 		<p> -->
<%-- 			<a href="<c:url value='/courses'/>" class="btn btn-dark"> <span --%>
<!-- 				class="glyphicon-info-sigh glyphicon"></span>返回課程列表 -->
<!-- 			</a> -->
<!-- 		</p> -->
<!-- 	</div> -->

                <h3 style="color: red;text-align:center">${messages}</h3>
<section>
        <div>
            <div class="container" style="text-align: center" >
                <h3>進行中課程</h3>
            </div>
        </div>
    </section>
    <hr style="height:1px;border:none;color:#333;background-color:#333;">
    <section class="container">
        <div class="row">
        <c:forEach var='nowcourse' items='${mynowlist}'>							
            <div class="col-sm-6 col-md-3" style="width: 360px; height: 320px">
                <div class="thumbnail" style="width: 320px; height: 300px">
                    <div class="caption">
                    <img width='264px' src="<c:url value='/getCPicture/${nowcourse.courseBean.courseId}'/>" />
                        <p>
                            <b style='font-size: 20px;color:black'>${nowcourse.courseBean.title}</b>
                        </p>
                        <p><b style='color:black'>日期: ${nowcourse.courseBean.date}</b></p>
                        <p><b style='color:black'>時間: ${nowcourse.courseBean.starttime} ~ ${nowcourse.courseBean.endtime}</b></p>
                        <p><b style='color:black'>教室: ${nowcourse.courseBean.location}</b></p>
                    </div>
                </div>
            </div>
            </c:forEach>
        </div>
        
        
    </section>

	<section>
        <div>
            <div class="container" style="text-align: center" >
                <h3>已預約課程</h3>
<%--                 <h3 style="color: red;">${messages}</h3> --%>
            </div>
        </div>
    </section>
    <hr style="height:1px;border:none;color:#333;background-color:#333;">
<!--     <section class="container"> -->
<!--         <div class="row"> -->
        <table style="width:1150px;margin:30px auto;">
    	<thead>
    		<tr style="background-color: gray; height:30px;">
    			<th class="table-header" style="width:200px;"></th>
    			<th class="table-header" style="width:400px;color:black;">課程</th>
    			<th class="table-header" style="width:400px;color:black;">上課日期</th>
    			<th class="table-header" style="width:400px;color:black;">上課時間</th>    			
    			<th class="table-header" style="color:black;">教室</th>
    			<th class="table-header" style="width:100px;">教練</th>
    			<th class="table-header" style="width:100px;">操作</th>
    		</tr>
    	</thead>
       
        <c:forEach var='course' items='${mycourses}'>
			<tr>
				<td><img width='200px'
					src="<c:url value='/getCPicture/${course.courseBean.courseId}'/>" /></td>
				<td class="table-body" style="width: 350px;">
					<div>
						<a style="color: black;"
							href="<c:url value='/course?id=${course.courseBean.courseId}'/>"
							onmouseover="this.style.color='orange'"
							onmouseout="this.style.color='black'"><b
							style='font-size: 20px;'> ${course.courseBean.title}</b></a>
					</div>
				</td>
				<td><b style='font-size: 20px;'>${course.courseBean.date}</b></td>
				<td>${course.courseBean.starttime}~
					${course.courseBean.endtime}</td>
				<td class="table-body" style="width: 150px;">
					<div width="150px">
						${course.courseBean.location}
					</div>
				</td>
				<td class="table-body">${course.courseBean.courseCoachBean.name}</td>
				<td class="table-body">
					<div>
						<button class="btn btn-dark" style="width:100px" onclick="bookfunction(${course.courseBean.price}, ${course.id})">取消預約</button>
					</div>
				</td>


			</tr>
			<!--             <div class="col-sm-6 col-md-3" style="width: 360px; height: 360px"> -->
<!--                 <div class="thumbnail" style="width: 320px; height: 330px"> -->
<!--                     <div class="caption"> -->
<%--                     <img width='264px' src="<c:url value='/getCPicture/${course.courseBean.courseId}'/>" /> --%>
<!--                         <p> -->
<%--                         <a href="<c:url value='/course?id=${course.courseBean.courseId}'/>"><b style='font-size: 20px;'> ${course.courseBean.title}</b></a> --%>
<!--                         </p> -->
<%--                         <p>日期: ${course.courseBean.date}</p> --%>
<%--                         <p>時段: ${course.courseBean.starttime} ~ ${course.courseBean.endtime}</p> --%>
<%--                         <p>教室: ${course.courseBean.location}</p> --%>
<%--                         <p><a href="<c:url value='/unbooking/course?id=${course.id}'/>" class="btn btn-dark" onclick="return bookfunction()"> --%>
<!--                         <span class="glyphicon-info-sigh glyphicon"></span>取消預約 -->
<!--                         </a> -->
                        
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
            </c:forEach>
            </table>
<!--         </div> -->
        
        
<!--     </section> -->
    
    
    
    <section>
        <div>
            <div class="container" style="text-align: center" >
                <h3>已結束課程</h3>
<%--                 <h3 style="color: red;">${message}</h3> --%>
            </div>
        </div>
    </section>
    <hr style="height:1px;border:none;color:#333;background-color:#333;">
<!--     <section class="container"> -->
<!--         <div class="row"> -->
<table style="width:1150px;margin:30px auto;">
    	<thead>
    		<tr style="background-color: gray; height:30px;">
    			<th class="table-header" style="width:200px;"></th>
    			<th class="table-header" style="width:400px;color:black;">課程</th>
    			<th class="table-header" style="width:400px;color:black;">上課日期</th>
    			<th class="table-header" style="width:400px;color:black;">上課時間</th>    			
    			<th class="table-header" style="color:black;">價格</th>
    			<th class="table-header" style="width:100px;">教練</th>
    			<th class="table-header" style="width:100px;">狀態</th>
    		</tr>
    	</thead>
        <c:forEach var='fcourse' items='${finishedlist}'>
        <tr>
				<td><img width='200px'
					src="<c:url value='/getCPicture/${fcourse.courseBean.courseId}'/>" /></td>
				<td class="table-body" style="width: 350px;">${fcourse.courseBean.title}</td>
				<td>${fcourse.courseBean.date}</td>
				<td>${fcourse.courseBean.starttime}~
					${fcourse.courseBean.endtime}</td>
				<td class="table-body" style="width: 150px;">
					<div width="150px">
						${fcourse.courseBean.price}
					</div>
				</td>
				<td class="table-body">${fcourse.courseBean.courseCoachBean.name}</td>
				<td>已完成</td>

			</tr>
<!--             <div class="col-sm-6 col-md-3" style="width: 360px; height: 320px"> -->
<!--                 <div class="thumbnail" style="width: 320px; height: 300px"> -->
<%--                 <img  width='264' src="<c:url value='/getCPicture/${fcourse.courseBean.courseId}'/>" /> --%>
<!--                     <div class="caption"> -->
<!--                         <p> -->
<%--                             <b style='font-size: 20px;'>${fcourse.courseBean.title}</b> --%>
<!--                         </p> -->
<%--                         <p>日期: ${fcourse.courseBean.date}</p> --%>
<%--                         <p>時段: ${fcourse.courseBean.starttime} ~ ${fcourse.courseBean.endtime}</p> --%>
<%--                         <p>教室: ${fcourse.courseBean.location}</p> --%>
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
            </c:forEach>
<!--         </div> -->
        
        </table>
<!--     </section> -->

<!--     頁尾 -->
    <jsp:include page="/fragment/footer.jsp" />

</body>
</html>
    
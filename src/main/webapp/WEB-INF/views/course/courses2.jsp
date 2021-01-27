<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link href="https://fonts.googleapis.com/css?family=Rajdhani:300,400,500,600,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,400i,600,600i,700,700i&display=swap"
        rel="stylesheet">

    <link rel="stylesheet" href="http://cdn.bootstrapmb.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/all.min.css">
    <link rel="stylesheet" href="assets/css/animate.css">
    <link rel="stylesheet" href="assets/css/lightcase.css">
    <link rel="stylesheet" href="assets/css/flaticon.css">
    <link rel="stylesheet" href="assets/css/swiper.min.css">

    <link rel="shortcut icon" href="assets/images/favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="assets/css/style.css">
<link rel="stylesheet"
    href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<title>Courses</title>
<%-- <link rel="stylesheet" href='${pageContext.request.contextPath}/css/styles.css' type="text/css"> --%>
<script type="text/javascript">

function bookfunction(){
	if(confirm("本課程確認無誤後請按確定!")){  
		return true;  
	}  
		return false;  
	}
$(document).ready(function () {
	var selectText = "請選擇";
    var selectVal = "--select--";
    $("#cdate").append("<option value=" + selectVal + ">" + selectText + "</option>");
	
    
	
	
$("#re").click(function () {
	var keyword = $("#keyword").val();
// 	console.log(keyword);
	var cate = $("#cate").find("option:selected").text();
	var cdate = $("#cdate").find("option:selected").text();
	
	$.ajax({
        url: "CourseSearchByKC",
        type: "POST",
        async: false,
        data: {
            Keyword: keyword,
            Cate: cate,
            Cdate: cdate,
        },
        success: function (list_search) {
            $("#clist").html("");
            for (var i = 0; i < list_search.length; i++) {
                var courses = list_search[i];

                var tbd = `
                                <tr>
                                <td><img width='264px' src="<c:url value='/getPicture/\${courses.courseId}'/>" /></td>
                                <td class="table-body" style="width:150px;"><div width="150px">\${courses.location}</div></td>
                				<td class="table-body">
                                <div><a href="<c:url value='/course?id=\${courses.courseId}'/>" target="_blank"><b style='font-size: 20px;'> \${courses.title}</b></a></div>
                                <div> \${courses.date} \${courses.starttime}~\${courses.endtime}</div>
                                <div>教練: \${courses.coachBean.name}</div>
                                <div>人數: \${courses.selected} / \${courses.max}</div>
                                </td>
                                <td class="table-body">NT$ \${courses.price}</td>
                                <td style="padding-right:1px;">
                                <a name="a\${courses.max - courses.selected}" href="<c:url value='/booking/course?id=\${courses.courseId}'/>" class="btn btn-dark" onclick="return bookfunction()">
                                <span class="glyphicon-info-sigh glyphicon"></span>立即預約</a>

                                </td>
								</tr>`;
				
                $("#clist").append(tbd);
                $("a[name='a0']").each(function(){
    				$(this).attr('disabled', 'true').attr("class","btn btn-secondary").text("已額滿"); 
    			}) 
//                 $("#a0").attr('disabled', 'true').attr("class","btn btn-secondary").text("已額滿");
            }
        }
    });})
	$.ajax({
        url: "CourseSearchShowDate",
        type: "POST",
        async: false,
        success: function (list) {
            for (var i = 0; i < list.length; i++) {
                var date = list[i];
//                  console.log(date);
                var optionDate = "<option value=" + date + ">" + date + "</option>";
                $("#cdate").append(optionDate);
            }
            $("#re").trigger("click");

        }
    })


})

</script>
<style type="text/css">
#kw {
	align:center;
	width:50px;
	line-height:30px;
	padding:10px;
	margin-right:150px;
	float:left;
}

#cli {
	align:center;
	width:150px;
	line-height:20px;
	padding:9px;
	float:left;
}
</style>
</head>
<body>
<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/top.jsp" />
<section class="page-header bg_img" data-background="./assets/images/banner/black.jpg">
        <div class="container">
            <h3 class="title"><span class="shape-wrapper"><span class="shape"></span>課程列表<span
                        class="shape"></span></span></h3>
        </div>
    </section>
    <section>
        <div>
            <div class="container" style="text-align: center" >
<!--                 <h1>課程列表</h1> -->
                <h3 style="color: red;">${message}</h3>
            </div>
        </div>
    </section>
    <div align="center" style="margin-left:300px;margin-bottom:10px; width:1000px; height:60px;">
    <div id="kw">
    <input id="keyword" type="text" placeholder="關鍵字" style="border: 1px gray solid;height:42px;width:180px">
    </div>
    <div id="kw">
    
    <select id="cate" style="width:180px;height:42px;border: 1px gray solid;appearance:none;
  -moz-appearance:none;
  -webkit-appearance:none;">
    <option style="color: gray" value="--">不限種類</option>
    <option style="color: gray" value="瑜珈 & 皮拉提斯">瑜珈 & 皮拉提斯</option>
    <option style="color: gray" value="拳擊">拳擊</option>
    <option style="color: gray" value="舞蹈">舞蹈</option>
    <option style="color: gray" value="飛輪">飛輪</option>
    <option style="color: gray" value="肌力訓練">肌力訓練</option>
    </select>
    </div>
    <div id="kw">
    <select id="cdate" style="width:180px;height:42px;border: 1px gray solid;appearance:none;
  -moz-appearance:none;
  -webkit-appearance:none;">
  	</select>
    </div>
    
    <div id="cli"><a style="font-size: 20px;color: white;" class="btn btn-primary btn-lg" id="re">
    顯示結果</a>
    </div>
    <div id="cli"><a style="font-size: 20px;" href="<c:url value='/courses'/>" class="btn btn-secondary btn-lg">
    重置條件</a>
    </div>
    </div>
    <br />
<!--     <div align="center"> -->
<%--     <a href="<c:url value='/courses/date?date=2021-01-11'/>" style="font-size: 25px; margin-right: 10px;"> --%>
<!--     01/11</a> -->
<%--     <a href="<c:url value='/courses/date?date=2021-01-12'/>" style="font-size: 25px"> --%>
<!--     01/12</a> -->
<!--     </div> -->
    <div align="right">
    <p><a href="<c:url value='/mycourses'/>" class="btn btn-primary">
    <span class="glyphicon-info-sigh glyphicon"></span>我的課程
    </a></p></div>
    <hr style="height:1px;border:none;color:#333;background-color:#333;">
    <section class="container">
    <table style="width:1050px;margin-bottom:50px;margin-left:50px">
    	<thead>
    		<tr style="background-color: gray; height:30px;">
    			<th class="table-header" style="width:300px;"></th>
    			<th class="table-header">教室</th>
    			<th class="table-header" style="width:300px;">課程</th>
    			<th class="table-header" style="width:120px;">價格</th>
    			<th class="table-header" style="width:80px;"></th>
    		</tr>
    	</thead>
    	
    	<tbody id="clist">
    		
    	</tbody>
    	
    </table>

    </section>
    
    <section class="container">
    <div class="row" >
    </div>
    </section>
    
    
</body>
</html>
    
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
<title>Courses</title>
<%-- <link rel="stylesheet" href='${pageContext.request.contextPath}/css/styles.css' type="text/css"> --%>
<script type="text/javascript">

function bookfunction(id){
	if(confirm("確定要下架本課程:\${courses.title}嗎?")){  
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
        url: "CourseSearchByKCAdmin",
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
                                <td><img width='264px' src="<c:url value='/getCPicture/\${courses.courseId}'/>" /></td>
                                <td class="table-body" style="width:150px;">
                                <div width="150px">\${courses.location}</div>
                                </td>
                				<td class="table-body" style="width:350px;">
                                <div><a href="<c:url value='/courseMaintain?id=\${courses.courseId}'/>"><b style='font-size: 20px;'> \${courses.title}</b></a></div>
                                <div> \${courses.date} \${courses.starttime}~\${courses.endtime}</div>
                                <div>教練: \${courses.courseCoachBean.name}</div>
                                <div>人數: \${courses.selected} / \${courses.max}</div>
                                </td>
                                <td class="table-body">\${courses.price} 點</td>
                                <td class="table-body" name="\${courses.status}"></td>
                                <td style="padding-right:5px;">
                                <a href="<c:url value='/courseUpdate?id=\${courses.courseId}'/>" class="btn btn-primary">
                                <span class="glyphicon-info-sigh glyphicon"></span>編輯</a>
                                <a name="\${courses.status}\${courses.selected}" href="<c:url value='/courseOnOff/\${courses.courseId}'/>" onclick="return bookfunction(\${courses.title})">
                                <span class="glyphicon-info-sigh glyphicon"></span></a>
                                </td>
								</tr>`;
				
                $("#clist").append(tbd);
//                 $("#a0").attr('disabled', 'true').attr("class","btn btn-secondary").text("已額滿");
                
                $("a[name='false0']").each(function(){
                	$(this).attr("class","btn btn-primary").text("上架");
				})
				$("a[name='true0']").each(function(){
					
                	$(this).attr("class","btn btn-primary").text("下架");
				})
				$("td[name='true']").each(function(){
                	$(this).text("上架中");
				})
				$("td[name='false']").each(function(){
                	$(this).text("已下架");
				})
				
            }
        }
    });})
	$.ajax({
        url: "CourseSearchShowDateAdmin",
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
<section class="page-header bg_img" data-background="images/banner.jpg">
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
<!--     <div align="right"> -->
<%--     <p><a href="<c:url value='/courses/mycourses'/>" class="btn btn-primary"> --%>
<!--     <span class="glyphicon-info-sigh glyphicon"></span>我的課程 -->
<!--     </a></p></div> -->
    <hr style="height:1px;border:none;color:#333;background-color:#333;">
    <section class="container">
    <table style="width:1150px;margin-bottom:50px;">
    	<thead>
    		<tr style="background-color: gray; height:30px;">
    			<th class="table-header" style="width:300px;"></th>
    			<th class="table-header" style="color:black;">教室</th>
    			<th class="table-header" style="width:350px;color:black;">課程</th>
    			<th class="table-header" style="width:120px;color:black;">價格</th>
    			<th class="table-header" style="width:80px;color:black;">狀態</th>
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
    
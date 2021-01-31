<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<style type="text/css">
fieldset {
	border: 1px solid rgb(255, 232, 57);
	width: 1000px;
	margin-left: 300px;
	paddin-left: 300px
}

span.error {
	color: red;
	display: inline-block;
	font-size: 10pt;
	margin-left: 10px;
}
</style>
<title>Course</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript">

function readURL(input) {
	if (input.files && input.files[0]) {
		var imageTagID = input.getAttribute("targetID");
		var reader = new FileReader();
		reader.onload = function(e) {
			var img = document.getElementById(imageTagID);
			img.setAttribute("src", e.target.result)
		}
		reader.readAsDataURL(input.files[0]);
	}
}

function setInput(){
	document.getElementById("title").value="拳擊有氧"
	document.getElementById("price").value=350;
	document.getElementById("max").value=40;
	document.getElementById("description").value="有氧(Aerobics)運動一詞，是美國Dr. Cooper於一九六八年所提出來的，意思是指在運動時隨時在體內充份攝取的氧氣，而自從蘇瑞珊(Tackj Sorensen)利用步行、跑步、跳躍，以各種不同於手臂擺動和踢腿動作配合輕快緊湊的音樂節奏與全身的大肌肉活動一起實施的健身運動。";
	document.getElementById('date').value = "2021-02-06";
	document.getElementById('st').value = "07:00:00";
	document.getElementById('et').value = "09:00:00";
	document.getElementById('category').value = "肌力訓練";
	document.getElementById('coachId').value = 3;	
}

$(document).ready(function () {
// 	var selectText = "請選擇";
//     var selectVal = "--select--";
//     $("#cdate").append("<option value=" + selectVal + ">" + selectText + "</option>");
		
$("#checkroom").click(function () {
// 	var keyword = $("#st").val();
// 	console.log(keyword);
	
	var date = $("#date").val();
	var st = $("#st").find("option:selected").val();
	var et = $("#et").find("option:selected").val();
// 	console.log(date);
	console.log(st);
	console.log(et);
	
	
	$.ajax({
        url: "showRoomCheck",
        type: "POST",
        async: false,
        data: {
            Date: date,
            St: st,
            Et: et,
        },
        success: function (list) {
        	$("#location").empty();
            for (var i = 0; i < list.length; i++) {
                var room = list[i];
//                  console.log(room);
                var optionRoom = "<option value=" + room + ">" + room + "</option>";
                $("#location").append(optionRoom);
            }
//             $("#re").trigger("click");

        }
    })
})

})
</script>
</head>
<body>
<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/top.jsp" />
<!-- <section class="page-header bg_img" data-background="../../assets/images/banner/black.jpg"> -->
<!--         <div class="container"> -->
<!--             <h3 class="title"><span class="shape-wrapper"><span class="shape"></span>課程<span -->
<!--                         class="shape"></span></span></h3> -->
<!--         </div> -->
<!--     </section> -->
	<section>
		<div class="container">
			<h3 style="text-align: center">
			<c:choose>
			<c:when test="${courseBean.courseId == null }">
				新增課程資訊
			</c:when>
			<c:otherwise>
				修改課程資訊
			</c:otherwise>
			</c:choose>
<%-- 			<spring:message code='spring.addProduct.form.addProductData.label' /> --%>
			</h3>
		</div>
	</section>
	<hr
		style="height: 1px; border: none; color: #333; background-color: #333;">
	<section class="container">
	<button type="button" onclick="setInput()">✎ </button>
		<!--       三個地方要完全一樣 -->
		<form:form method='POST' modelAttribute="courseBean"
			class='form-horizontal' enctype="multipart/form-data">
			<%-- employee物件有編號就是修改 --%>
			<c:if test="${courseBean.courseId != null }">
      		<form:input path="courseId" type="hidden" />
      		<form:input path="selected" type="hidden" />
			</c:if>
			<fieldset>
			
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='title' style="font-size:18px">
<%-- 						<spring:message code='spring.addProduct.form.title.label' /> --%>
					名稱
					</label>
					<div class="col-lg-10">
						<form:input id="title" name="title" path="title" type='text'
							class='form:input-large' required="required" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='date' style="font-size:18px">
<%-- 						<spring:message code='spring.addProduct.form.author.label' /> --%>
						日期
					</label>
					<div class="col-lg-10">
						<form:input id="date" path="date" type='date'
							class='form:input-large' required="required" />
						<script>
							var ddd = new Date();

							var day = ddd.getDate();

							var month = ddd.getMonth() + 1;

							if (ddd.getMonth() < 10) {

								var month = "0" + (ddd.getMonth() + 1);

							}

							if (ddd.getDate() < 10) {

								day = "0" + ddd.getDate();

							}

							var datew = ddd.getFullYear() + "-" + month + "-"
									+ day;

							var datew = datew.toString();
							document.getElementById('date').setAttribute("min", datew); 
							
						</script>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='st' style="margin-right:15px;font-size:18px">
<%-- 						<spring:message code='spring.addProduct.form.author.label' /> --%>
						時段
					</label>
<!-- 					<div class="col-lg-10"> -->
					<div class="col-lg-10" style="width:80px;padding:0px" >
						<form:select path="st" id="st" >
							<form:option value="-1">
<%-- 								<spring:message code='spring.addProduct.form.select.label' /> --%>
							請選擇
							</form:option>
							<form:option value="07:00:00">
								07:00
							</form:option>
							<form:option value="08:00:00">
								08:00
							</form:option>
							<form:option value="09:00:00">
								09:00
							</form:option>
							<form:option value="10:00:00">
								10:00
							</form:option>
							<form:option value="11:00:00">
								11:00
							</form:option>
							<form:option value="12:00:00">
								12:00
							</form:option>
							<form:option value="13:00:00">
								13:00
							</form:option>
							<form:option value="14:00:00">
								14:00
							</form:option>
							<form:option value="15:00:00">
								15:00
							</form:option>
							<form:option value="16:00:00">
								16:00
							</form:option>
							<form:option value="17:00:00">
								17:00
							</form:option>
							<form:option value="18:00:00">
								18:00
							</form:option>
							<form:option value="19:00:00">
								19:00
							</form:option>
							<form:option value="20:00:00">
								20:00
							</form:option>
							<form:option value="21:00:00">
								21:00
							</form:option>
						</form:select>
					</div>
<%-- 					<form:input id="starttime" path="starttime" type='text' --%>
<%-- 							class='form:input-large' required="required" /> --%>
<!-- 					</div> -->
					<label class="control-label col-lg-2 col-lg-2" for='et' style="margin:10px;width:28px;line-height:0px;">
						~
					</label>
					<div class="col-lg-10" style="width:80px; padding-left:0px" >
						<form:select path="et" id="et">
							<form:option value="-1">
<%-- 								<spring:message code='spring.addProduct.form.select.label' /> --%>
							請選擇
							</form:option>
							<form:option value="08:00:00">
								08:00
							</form:option>
							<form:option value="09:00:00">
								09:00
							</form:option>
							<form:option value="10:00:00">
								10:00
							</form:option>
							<form:option value="11:00:00">
								11:00
							</form:option>
							<form:option value="12:00:00">
								12:00
							</form:option>
							<form:option value="13:00:00">
								13:00
							</form:option>
							<form:option value="14:00:00">
								14:00
							</form:option>
							<form:option value="15:00:00">
								15:00
							</form:option>
							<form:option value="16:00:00">
								16:00
							</form:option>
							<form:option value="17:00:00">
								17:00
							</form:option>
							<form:option value="18:00:00">
								18:00
							</form:option>
							<form:option value="19:00:00">
								19:00
							</form:option>
							<form:option value="20:00:00">
								20:00
							</form:option>
							<form:option value="21:00:00">
								21:00
							</form:option>
							<form:option value="22:00:00">
								22:00
							</form:option>
						</form:select>
						
					</div>
					<div><form:errors path="et" cssClass="error"/></div>
<!-- 					<div class="col-lg-10" style="float: left"> -->
<%-- 						<form:input id="endtime" path="endtime" type='text' --%>
<%-- 							class='form:input-large' required="required" /> --%>
					</div>
					<div>
					<input type="button" value="check" id="checkroom">
					</div>
<!-- 				</div> -->
				
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='location' style="font-size:18px">
<%-- 						<spring:message code='spring.addProduct.form.author.label' /> --%>
						教室
					</label>
					<div class="col-lg-10">
						<form:select path="location" id="location">
							<form:option value="-1">
<%-- 								<spring:message code='spring.addProduct.form.select.label' /> --%>
							請選擇
							</form:option>
<%-- 							<form:option value="B001"> --%>
<!-- 								B001 -->
<%-- 							</form:option> --%>
<%-- 							<form:option value="B002"> --%>
<!-- 								B002 -->
<%-- 							</form:option> --%>
<%-- 							<form:option value="B003"> --%>
<!-- 								B003 -->
<%-- 							</form:option> --%>
<%-- 							<form:option value="B004"> --%>
<!-- 								B004 -->
<%-- 							</form:option> --%>
<%-- 							<form:option value="B005"> --%>
<!-- 								B005 -->
<%-- 							</form:option> --%>
						</form:select>
						<form:errors path="location" cssClass="error"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='price' style="font-size:18px">
<%-- 						<spring:message code='spring.addProduct.form.author.label' /> --%>
						價格
					</label>
					<c:choose>
						<c:when test="${courseBean.courseId == null }">
							<div class="col-lg-10">
								<form:input id="price" path="price" type='text'
									class='form:input-large' required="required" />
							</div>
						</c:when>
						<c:otherwise>
							<div class="col-lg-10">
								<form:input id="price" path="price" type='text'
									class='form:input-large' readonly="readonly" />
							</div>
						</c:otherwise>
					</c:choose>
					
				</div>

				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="category" style="font-size:18px">
<%-- 						<spring:message code='spring.addProduct.form.category.label' /> --%>
					分類
					</label>
					<div class='col-lg-10'>
						<form:select path="category" required="required">
							<form:option value="-1">
<%-- 								<spring:message code='spring.addProduct.form.select.label' /> --%>
							請選擇
							</form:option>
							<form:option value="瑜珈 & 皮拉提斯">
								瑜珈 & 皮拉提斯
							</form:option>
							<form:option value="拳擊">
								拳擊
							</form:option>
							<form:option value="舞蹈">
								舞蹈
							</form:option>
							<form:option value="飛輪">
								飛輪
							</form:option>
							<form:option value="肌力訓練">
								肌力訓練
							</form:option>
						</form:select>
						<form:errors path="category" cssClass="error"/>
					</div>
				</div>

				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="max" style="font-size:18px">
<%-- 						<spring:message code='spring.addProduct.form.price.label' /> --%>
					人數
					</label>
					<div class='col-lg-10'>
						<form:input id="max" path="max" type='text'
							class='form:input-large' required="required" />
					</div>
				</div>

				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="coachId" style="font-size:18px">
<%-- 						<spring:message code='spring.addProduct.form.bookCompany.label' /> --%>
					教練
					</label>
					<div class='col-lg-10'>
						<form:select path="coachId">
							<form:option value="-1">
<%-- 								<spring:message code='spring.addProduct.form.select.label' /> --%>
							請選擇
							</form:option>
							<form:options items="${coachList}" />
						</form:select>
						<form:errors path="coachId" cssClass="error"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="description" style="font-size:18px">
<%-- 						<spring:message code='spring.addProduct.form.price.label' /> --%>
					描述
					</label>
					<div class='col-lg-10'>
						<form:textarea id="description" path="description" type='text' cols="10" rows="3" style="resize:none;"
							class='form:input-large' required="required" />
					</div>
				</div>
				
				<div class="form-group">
<!-- 				<p style="color:black">商品圖片</p>  -->
<!-- 							<div class="product__details__pic__item"> -->
<!-- 							<img class="product__details__pic__item--large" -->
<!-- 								id="preview_progressbarTW_img" src="#" /> -->
<!-- 								</div> -->
					<label class='control-label col-lg-2 col-lg-2' for="productImage" style="font-size:18px">
<%-- 						<spring:message code='spring.addProduct.form.image.label' /> --%>
					圖片
					</label>
					<div class='col-lg-10'>
						<form:input id="productImage" path="productImage" type='file'
							class='form:input-large' onchange="readURL(this)" targetID="preview_progressbarTW_img" />
					</div>
				</div>
				<input type='hidden' name='status' value='True'>
				<div class="form-group">
					<div class='col-lg-offset-2 col-lg-10'>
						<input id="btnAdd" type='submit' style="background-color:black;" class='btn btn-primary'
							value="送出" />
<%-- 							<spring:message code='spring.addProduct.form.submit.label' /> --%>
					</div>
				</div>
			</fieldset>
		</form:form>
	</section>
</body>
</html>

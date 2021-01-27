<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta charset="UTF-8">
<!--     <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<!--     <meta http-equiv="X-UA-Compatible" content="ie=edge"> -->

<!--     <link href="https://fonts.googleapis.com/css?family=Rajdhani:300,400,500,600,700&display=swap" rel="stylesheet"> -->
<!--     <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,400i,600,600i,700,700i&display=swap" -->
<!--         rel="stylesheet"> -->

<!--     <link rel="stylesheet" href="http://cdn.bootstrapmb.com/bootstrap/4.3.1/css/bootstrap.min.css"> -->
<!--     <link rel="stylesheet" href="assets/css/all.min.css"> -->
<!--     <link rel="stylesheet" href="assets/css/animate.css"> -->
<!--     <link rel="stylesheet" href="assets/css/lightcase.css"> -->
<!--     <link rel="stylesheet" href="assets/css/flaticon.css"> -->
<!--     <link rel="stylesheet" href="assets/css/swiper.min.css"> -->

<!--     <link rel="shortcut icon" href="assets/images/favicon.png" type="image/x-icon"> -->
<!--     <link rel="stylesheet" href="assets/css/style.css"> -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<style type="text/css">
fieldset {
	border: 1px solid rgb(255, 232, 57);
	width: 400px;
	margin: auto;
}

span.error {
	color: red;
	display: inline-block;
	font-size: 10pt;
	margin-left: 10px;
}
</style>
<title>Course</title>
<!-- <link rel="stylesheet" -->
<%-- 	href='${pageContext.request.contextPath}/css/styles.css' --%>
<!-- 	type="text/css"> -->

<!-- <link rel='stylesheet' href='css/styles.css' type="text/css" /> -->
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
					<label class="control-label col-lg-2 col-lg-2" for='title'>
<%-- 						<spring:message code='spring.addProduct.form.title.label' /> --%>
					名稱
					</label>
					<div class="col-lg-10">
						<form:input id="title" path="title" type='text'
							class='form:input-large' required="required" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='date'>
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
					<label class="control-label col-lg-2 col-lg-2" for='st' style="margin-right:15px">
<%-- 						<spring:message code='spring.addProduct.form.author.label' /> --%>
						時段
					</label>
<!-- 					<div class="col-lg-10"> -->
					<div class="col-lg-10" style="width:60px;padding:0px" >
						<form:select path="st" required="required" >
							<form:option value="-1">
								<spring:message code='spring.addProduct.form.select.label' />
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
					<div class="col-lg-10" style="width:60px; padding-left:0px" >
						<form:select path="et" required="required">
							<form:option value="-1">
								<spring:message code='spring.addProduct.form.select.label' />
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
<!-- 				</div> -->
				
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='location'>
<%-- 						<spring:message code='spring.addProduct.form.author.label' /> --%>
						教室
					</label>
					<div class="col-lg-10">
						<form:select path="location" required="required">
							<form:option value="-1">
								<spring:message code='spring.addProduct.form.select.label' />
							</form:option>
							<form:option value="B001">
								B001
							</form:option>
							<form:option value="B002">
								B002
							</form:option>
							<form:option value="B003">
								B003
							</form:option>
							<form:option value="B004">
								B004
							</form:option>
							<form:option value="B005">
								B005
							</form:option>
						</form:select>
						<form:errors path="location" cssClass="error"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='price'>
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
					<label class='control-label col-lg-2 col-lg-2' for="category">
						<spring:message code='spring.addProduct.form.category.label' />
					</label>
					<div class='col-lg-10'>
						<form:select path="category" required="required">
							<form:option value="-1">
								<spring:message code='spring.addProduct.form.select.label' />
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
					<label class='control-label col-lg-2 col-lg-2' for="price">
<%-- 						<spring:message code='spring.addProduct.form.price.label' /> --%>
					人數
					</label>
					<div class='col-lg-10'>
						<form:input id="max" path="max" type='text'
							class='form:input-large' required="required" />
					</div>
				</div>

				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="coachId">
<%-- 						<spring:message code='spring.addProduct.form.bookCompany.label' /> --%>
					教練
					</label>
					<div class='col-lg-10'>
						<form:select path="coachId">
							<form:option value="-1">
								<spring:message code='spring.addProduct.form.select.label' />
							</form:option>
							<form:options items="${coachList}" />
						</form:select>
						<form:errors path="coachId" cssClass="error"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="description">
<%-- 						<spring:message code='spring.addProduct.form.price.label' /> --%>
					描述
					</label>
					<div class='col-lg-10'>
						<form:textarea id="description" path="description" type='text' cols="50" rows="5" style="resize:none;"
							class='form:input-large' required="required" />
					</div>
				</div>
				
				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="productImage">
<%-- 						<spring:message code='spring.addProduct.form.image.label' /> --%>
					圖片
					</label>
					<div class='col-lg-10'>
						<form:input id="productImage" path="productImage" type='file'
							class='form:input-large' />
					</div>
				</div>
				<input type='hidden' name='status' value='True'>
				<div class="form-group">
					<div class='col-lg-offset-2 col-lg-10'>
						<input id="btnAdd" type='submit' class='btn btn-primary'
							value="<spring:message code='spring.addProduct.form.submit.label' />" />
					</div>
				</div>
			</fieldset>
		</form:form>
	</section>
</body>
</html>

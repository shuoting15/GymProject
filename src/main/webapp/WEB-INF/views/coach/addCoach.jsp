<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	data-integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	data-crossorigin="anonymous">
<style type="text/css">
fieldset {
	border: 1px solid rgb(255, 232, 57);
	width: 400px;
	margin: auto;
}
</style>
<title>Products</title>
<link rel="stylesheet"
	href='${pageContext.request.contextPath}/css/styles.css'
	type="text/css">

<link rel='stylesheet' href='css/styles.css' type="text/css" />
</head>
<body>
	<section>
		<div class="container">
			<h1 style="text-align: center">
				<spring:message code='spring.addCoach.form.addCoachData.label' />
			</h1>
		</div>
	</section>
	<hr
		style="height: 1px; border: none; color: #333; background-color: #333;">
	<section class="container">
		<!--       三個地方要完全一樣 -->
		<form:form method='POST' modelAttribute="coachBean"
			class='form-horizontal' enctype="multipart/form-data">

			<input type='hidden' id='updateOrDelete' name='_method'>
			<form:input id="coachRating" path="coachRating" type='hidden'
				class='form:input-large' />

			<div class="form-row">
				<div class="form-group col-md-6" align="right">
					<label for="coachName"> <spring:message
							code='spring.addCoach.form.coachName.label' />
					</label>
					<form:input id="coachName" path="coachName" type='text'
						class='form:input-large' />
				</div>
				<div class="form-group col-md-6">
					<label for="coachGender"> <spring:message
							code='spring.addCoach.form.coachGender.label' />
					</label>
					<form:input id="coachGender" path="coachGender" type='text'
						class='form:input-large' />
				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6" align="right">
					<label for='coachHeight'> <spring:message
							code='spring.addCoach.form.coachHeight.label' />
					</label>
					<form:input id="coachHeight" path="coachHeight" type='text'
						class='form:input-large' />
				</div>
				<div class="form-group col-md-6">
					<label for='coachWeight'> <spring:message
							code='spring.addCoach.form.coachWeight.label' />
					</label>
					<form:input id="coachWeight" path="coachWeight" type='text'
						class='form:input-large' />
				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6" align="right">
					<label for="coachExpertise"> <spring:message
							code='spring.addCoach.form.coachExpertise.label' />
					</label>
					<form:select path='coachExpertise'>
						<form:option value="0" label="請挑選" />
						<form:options items="${expertiseList}" />
					</form:select>
				</div>

				<div class="form-group col-md-6">
					<label for="coachExpertiseOne"> <spring:message
							code='spring.addCoach.form.coachExpertiseOne.label' />
					</label>
					<form:input id="coachExpertiseOne" path="coachExpertiseOne"
						type='text' class='form:input-large' />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-6" align="right">
					<label for="coachExpertiseTwo"> <spring:message
							code='spring.addCoach.form.coachExpertiseTwo.label' />
					</label>
					<form:input id="coachExpertiseTwo" path="coachExpertiseTwo"
						type='text' class='form:input-large' />
				</div>
				<div class="form-group col-md-6">
					<label for="coachExpertiseThree"> <spring:message
							code='spring.addCoach.form.coachExpertiseThree.label' />
					</label>
					<form:input id="coachExpertiseThree" path="coachExpertiseThree"
						type='text' class='form:input-large' />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-6" align="right">
					<label for="coachPrice"> 教練課價格(小時)
					</label>
					<form:input id="coachPrice" path="coachPrice"
						type='text' class='form:input-large' />
				</div>
			</div>
			
			<div class="form-group" align="center">
				<label for="exampleFormControlTextarea1"><spring:message
						code='spring.addCoach.form.coachIntroduction.label' /></label>
				<form:textarea style="width: 450px" class="form-control"
					id="coachIntroduction" rows="7" path="coachIntroduction" />
			</div>
			<div class="form-group" align="center">
				<label class='control-label col-lg-2 col-lg-2' for="coachImage">
					<spring:message code='spring.addCoach.form.image.label' />
				</label>
				<div class='col-lg-10'>
					<form:input id="coachImage" path="coachImage" type='file'
						class='form:input-large' />
				</div>
			</div>
			<div class="form-group" align="center">
				<div class='col-lg-offset-2 col-lg-10'>
					<input id="btnAdd" type='submit' class='btn btn-primary'
						value="<spring:message code='spring.addCoach.form.submit.label' />" />
				</div>
			</div>
		</form:form>
	</section>
</body>
</html>

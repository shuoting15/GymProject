<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
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
<script type="text/javascript">
function confirmDelete(id) {
	if (confirm("確定刪除此項教練資料(教練編號:${coachBean.coachId})?") ) {
		document.forms[0].action="<c:url value='/coachDelete/${coachBean.coachId}'  />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
	}
}

function updateCoach() {
    var hiddenField = document.getElementById("updateOrDelete");
//     hiddenField.value='PUT';
    document.forms[0].method="POST";
	document.forms[0].submit();
}

</script>
</head>
<body>
	<section>
		<div class="container">
			<h1 style="text-align: center">
				<spring:message code='spring.addCoach.form.UpdateCoachData.label' />
			</h1>
		</div>
	</section>
	<hr
		style="height: 1px; border: none; color: #333; background-color: #333;">
	<section class="container">
		<!--       三個地方要完全一樣 -->
		<form:form method='POST' modelAttribute="coachBean"
			class='form-horizontal' enctype="multipart/form-data">
			<input type='hidden' id='updateOrDelete' name='_method' >
			<form:input id="coachRating" path="coachRating" type='hidden'
							class='form:input-large' />
			<fieldset>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='coachName'>
						<spring:message code='spring.addCoach.form.coachName.label' />
					</label>
					<div class="col-lg-10">
						<form:input id="coachName" path="coachName" type='text'
							class='form:input-large' />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='coachGender'>
						<spring:message code='spring.addCoach.form.coachGender.label' />
					</label>
					<div class="col-lg-10">
						<form:input id="coachGender" path="coachGender" type='text'
							class='form:input-large' />
					</div>
				</div>

				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="coachExpertise">
						<spring:message code='spring.addCoach.form.coachExpertise.label' />
					</label>
					<div class='col-lg-10'>
						<form:input id="coachExpertise" path="coachExpertise" type='text'
							class='form:input-large' />
					</div>
				</div>
				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2'
						for="coachIntroduction"> <spring:message
							code='spring.addCoach.form.coachIntroduction.label' />
					</label>
					<div class='col-lg-10'>
						<form:input id="coachIntroduction" path="coachIntroduction"
							type='text' class='form:input-large' />
					</div>
				</div>
				<div class="form-group">
					<img width='100' height='200'
						src="<c:url value='/getPicture/${coachBean.coachId}'/>" /> <label
						class='control-label col-lg-2 col-lg-2' for="coachImage">
						<spring:message code='spring.addCoach.form.image.label' />
					</label>
					<div class='col-lg-10'>
						<form:input id="coachImage" path="coachImage" type='file'
							class='form:input-large' />
					</div>
				</div>
				<div class="form-group">
					<div class='col-lg-offset-2 col-lg-10'>
						<input id="btnAdd" type='button' name="update" class='btn btn-primary'
							value="<spring:message code='spring.addCoach.form.update.label' />"
							onclick='updateCoach()' /> 
						<input id="btnAdd" name="delete"
							type='button' class='btn btn-primary'
							value="<spring:message code='spring.addCoach.form.delete.label' />"
							onclick='confirmDelete(${coahBean.coachId})' />
					</div>
				</div>
			</fieldset>
		</form:form>
	</section>
	<div id="backToCoachs">
		<a href="<c:url value='/coachs' />">回教練頁面</a>
	</div>
</body>
</html>

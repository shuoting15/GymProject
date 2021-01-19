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
	href='../${pageContext.request.contextPath}/css/styles.css'
	type="text/css">

<link rel='stylesheet' href='../css/styles.css' type="text/css" />
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script type="text/javascript">

function confirmDelete(id) {
	Swal.fire({
		  title: "確定刪除此項教練資料(教練姓名:${coachBean.coachName})?",
		  text: "",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: '確定刪除',
		  cancelButtonText:'取消刪除'
		}).then((result) => {
		  if (result.isConfirmed) {
           	$.ajax({
		url : "<c:url value='/coachDelete'  />",
		type : "POST",
		dataType : "JSON",
		data : {"coachId":"${coachBean.coachId}"},
		success : function (data) {
			Swal.fire('Delete Success','刪除成功','success');
			
				setTimeout(function(){
					location.href = "<c:url value='/coachMaintain'  />";//刷新当前页面.
						},1500)
			 
		}
		})              		               		       				           				              				  
			  
          		
		    
		  }
		})
	
	
	
	
	
	
	
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
					<form:input id="coachExpertise" path="coachExpertise" type='text'
						class='form:input-large' />
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
				<img width='250' height='350' style="position: position:relative;"
					src="<c:url value='/getPicture/${coachBean.coachId}'/>" /> <label
					class='control-label col-lg-2 col-lg-2' for="coachImage"> </label>
				<div class='col-lg-10'>
					<form:input id="coachImage" path="coachImage" type='file'
						class='form:input-large' />
				</div>
			</div>
			<div class="form-group" align="center">
				<div class='col-lg-offset-2 col-lg-10' align="center">
					<input id="btnAdd" type='button' name="update"
						class='btn btn-primary'
						value="<spring:message code='spring.addCoach.form.update.label' />"
						onclick='updateCoach()' /> <input id="btnAdd" name="delete" style="background-color: red;"
						type='button' class='btn btn-primary'
						value="<spring:message code='spring.addCoach.form.delete.label' />"
						onclick='confirmDelete(${coahBean.coachId})' />
				</div>
			</div>
		</form:form>
	</section>
	<div id="backToCoachs" align="center">
		<a href="<c:url value='/coachMaintain' />">回教練管理頁面</a>
	</div>

</body>
</html>

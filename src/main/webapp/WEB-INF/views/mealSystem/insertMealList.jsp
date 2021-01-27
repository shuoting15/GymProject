<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<style type="text/css">
span.error {
	color: red;
	display: inline-block;
	font-size: 5pt;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<meta charset="UTF-8">
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/css/style.css' type="text/css" />
</head>
<body>
	<fieldset>
		<legend>新增餐點資料(MealList)</legend>
		<form:form method="POST" modelAttribute="add"	enctype='multipart/form-data'>
			<Table align="center">
				<tr>
					<td>餐點名稱：<br>&nbsp;
					</td>
					<td width='360'><form:input path='mealName' /><br>&nbsp;
						<form:errors path='mealName' cssClass="error" /></td>
				</tr>
				<tr>
					<td>餐點價格：<br>&nbsp;
					</td>
					<td width='360'><form:input path='mealPrice' /><br>&nbsp;
						<form:errors path='mealPrice' cssClass="error" /></td>
					<td>餐點內容：<br>&nbsp;
					</td>
					<td width='360'><form:textarea style="width: 450px" class="form-control"
				 		rows="20" path="mealContent" /><br>&nbsp;
						<form:errors path='mealContent' cssClass="error" /></td>
				</tr>
				<tr>
					<td>餐點熱量：<br>&nbsp;
					</td>
					<td><form:input path="mealKcal" /><br>&nbsp; <form:errors
							path='mealKcal' cssClass="error" /></td>
				</tr>

				<tr>
					<td>分類餐點：<br>&nbsp;
					</td>
					<td><form:select path="mealCategoryBean.categoryId">
							<form:option value="-1" label="請挑選" />
							<form:options items="${menuList}" itemLabel='categoryName'
								itemValue='categoryId' />
						</form:select><br>&nbsp; <form:errors path="mealCategoryBean"
							cssClass="error" /></td>
				</tr>
				<tr>
					<td>照片：<br>&nbsp;
					</td>
					<td><img id="blah" width='200'
						src="<c:url value='' />" /></td>
					<td>
						<form:input runat="server" id="imgInp" path="productImage" type='file' /><br>&nbsp; 
						<form:errors path="productImage" cssClass="error" />
					</td>
				</tr>
				<tr>
					<td colspan='4' align='center'><br>&nbsp; <input
						type='submit' value="新增餐點"></td>
				</tr>
			</Table>

		</form:form>

	</fieldset>
	<br>
	<a href="<c:url value='/updateShowAllMealList'/> ">回前頁</a>

	<script type='text/javascript'>
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$('#blah').attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]); // convert to base64 string
			}
		}
		$("#imgInp").change(function() {
			readURL(this);
		});
	</script>


</body>
</html>
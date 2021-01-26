<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Course Manage</title>
<link rel="stylesheet" href='${pageContext.request.contextPath}/css/styles.css' type="text/css">
</head>
<body>
<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/top.jsp" />
	<h1 style="text-align: center">Course Manage</h1>
	<hr>
	<table border="1" style="margin: 0px auto;">
		<tr height="52" bgcolor="lightblue" align="center">
			<td width="350"><p align="left" /> <a
				href="<c:url value='/coursesAdd'/>">新增課程</a><BR></td>
			<td width="350"><p align="left" /> <a
				href="<c:url value='/coursesMaintain'/>">查詢課程</a><BR></td>
		</tr>
		
	</table>
</body>
</html>
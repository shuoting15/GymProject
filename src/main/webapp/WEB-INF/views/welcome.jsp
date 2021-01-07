<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>welcome</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href='${pageContext.request.contextPath}/css/styles.css' type="text/css">

</head>
<body>
<div style="text-align: center" class="jumbotron">
<h1>${title}</h1>
<p>${subtitle}</p>
</div>
<div align="center">
<a href="<c:url value='/'/>">回首頁</a>
</div>


</body>
</html>
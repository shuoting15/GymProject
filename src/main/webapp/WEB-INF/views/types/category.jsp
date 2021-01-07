<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="text-align: center">
<h1>請依照類別挑選商品</h1>
<c:forEach var = "category" items="${categorylist}" >
<a href="<c:url value='/products/${category}'/>">${category}</a><br>
</c:forEach>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
    href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Products</title>
<link rel="stylesheet" href='${pageContext.request.contextPath}/css/styles.css' type="text/css">

</head>
<body>
    <section>
        <div>
            <div class="container" style="text-align: center" >
                <h1>產品清單</h1>
                <p>本購物商城所有產品資料</p>
            </div>
        </div>
    </section>
    <hr style="height:1px;border:none;color:#333;background-color:#333;">
    <section class="container">
        <div class="row">
        <c:forEach var='product' items='${products}'>
            <div class="col-sm-6 col-md-3" style="width: 360px; height: 360px">
                <div class="thumbnail" style="width: 320px; height: 340px">
                <img  width='100' height='200' src="<c:url value='/getPicture/${product.bookId}'/>" />
                    <div class="caption">
                        <p>
                            <b style='font-size: 16px;'>${product.title}</b>
                        </p>
                        <p>${product.author}</p>
                        <p>${product.companyBean.name}</p>
                        <p>目前在庫數量: ${product.stock}本</p>
                        <p><a href="<c:url value='product?id=${product.bookId}'/>" class="btn btn-primary">
                        <span class="glyphicon-info-sigh glyphicon"></span>詳細資訊
                        </a>
                        <a href="<spring:url value='product.json?id=${product.bookId}' />"   
								class="btn btn-primary"> 
								<span class="glyphicon-info-sigh glyphicon"></span>JSON
							</a>
                        </p>
                    </div>
                </div>
            </div>
            </c:forEach>
        </div>
    </section>
</body>
</html>
    
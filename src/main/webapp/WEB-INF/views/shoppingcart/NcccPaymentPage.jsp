<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
/* response.setContentType("text/html; charset=UTF-8");
request.setCharacterEncoding("UTF-8");

String amountTotal = request.getParameter("amountTotal"); */

/* int amount_final = 0;
if(request.getSession().getAttribute("discode")!=null){
	amount_final = Integer.parseInt(amountTotal)-(int)request.getSession().getAttribute("discode");
	
}else{
	amount_final = Integer.parseInt(amountTotal);
} */

/* request.getSession().setAttribute("discode", request.getSession().getAttribute("discode"));
request.getSession().setAttribute("receivername", request.getParameter("receivername"));
request.getSession().setAttribute("receiverphone", request.getParameter("receiverphone"));
request.getSession().setAttribute("address", request.getParameter("address"));
request.getSession().setAttribute("remarks", request.getParameter("remarks")); */
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NCCC</title>
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="../css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="../css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="../css/nice-select.css" type="text/css">
<link rel="stylesheet" href="../css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="../css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet" href="../css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="../css/style.css" type="text/css">


<script language=JavaScript src="js/sha256.js"></script>
<script language="javascript">

function setConf(){

	var SignatureData="";
	var SignatureTemp="";


	SignatureData = "MerchantID="+document.getElementById("MerchantID").value+
					"&TerminalID="+document.getElementById("TerminalID").value+
					"&OrderID="+document.getElementById("OrderID").value+
					"&TransAmt="+document.getElementById("TransAmt").value
	                +"&TransMode="+document.getElementById("TransMode").value+
	                "&Install="+document.getElementById("Install").value+
	                "&NotifyURL="+document.getElementById("NotifyURL").value
	                +"&CSS_URL="+document.getElementById("CSS_URL").value+
	                "&BankNo="+document.getElementById("BankNo").value+
	                "&TEMPLATE="+document.getElementById("BankNo").value
	                +"&BankNo="+document.getElementById("BankNo").value+
	                "&TravelStartDate="+document.getElementById("TravelStartDate").value+
	                "&TravelEndDate="+document.getElementById("TravelEndDate").value+
	                "&"+SHA256("8888888");

	SignatureTemp = SHA256(SignatureData);

	document.getElementById("Signature").value= SignatureData;

	alert(document.getElementById("Signature").value);

	return true;
}
</script>


</head>
<body onload="document.HPP.submit();">

	<jsp:include page="/fragment/top.jsp" />
    <div><a href='<c:url value="/shoppingCart/removeShoppingCart"/>'>跳過</a></div>
		




<div style="margin-top: 100px ; margin-left:150px">
<form name="HPP" method="post" target="HPPFrame" ACTION="https://nccnet-ectest.nccc.com.tw/merchant/HPPRequest">
<input type="hidden" name="MerchantID" id="MerchantID" value="6600800020">
<input type="hidden" name="TerminalID" id="TerminalID" value="70502744">
<input type="hidden" name="Install"    id="Install" value="0">
<input type="hidden" name="OrderID"    id="OrderID" value="<%=System.currentTimeMillis() %>">
<input type="hidden" name="TransMode"  id="TransMode" value="0">
<input type="hidden" name="CSS_URL"    id="CSS_URL" value="">
<input type="hidden" name="BankNo"     id="BankNo" value="">
<input type="hidden" name="TEMPLATE"   id="TEMPLATE" value="BOTH">
<input type="hidden" name="TravelStartDate"   id="TravelStartDate" value="">
<input type="hidden" name="TravelEndDate"     id="TravelEndDate" value="">
<input type="hidden" name="TransAmt"   id="TransAmt" value="${transAmt}">
<!-- <input type="hidden" name="NotifyURL"  id="NotifyURL" value="http://localhost:8080/JoYouProject/NcccResponsePage.jsp"> -->
<input type="hidden" name="NotifyURL"  id="NotifyURL" value="http://localhost:8080/GymProject/shoppingCart/ncccProcess">
<input type="hidden" name="Signature"  id="Signature" value="">
<iframe name="HPPFrame" id="mainFrame" height="600" width="800" Frameborder="0"/>
</form>
</div>
</body>
</html>
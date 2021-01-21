<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.lang.reflect.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.regex.*"%>
<%@ page import="java.net.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.io.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- <%!

public void _closeRS(ResultSet rs){
	if(rs!=null){
		try{
			rs.close();
		}catch(SQLException sex){}finally{rs=null;}
	}
}
public void _closeStmt(Statement stmt){
	if(stmt!=null){
		try{
			stmt.close();

		}catch(SQLException sex){}finally{stmt=null;}
	}
}

public java.sql.Connection _getDBConnetion(String dn) throws Exception {
	Context initContext = new InitialContext();
	DataSource ds =null;
	java.sql.Connection conn = null;
	try{
		ds = (DataSource) initContext.lookup( "java:comp/env/jdbc/"+dn);
		conn = ds.getConnection();
	}catch(javax.naming.NameNotFoundException e){
		try{
			ds = (DataSource) initContext.lookup( "jdbc/"+dn );
			conn = ds.getConnection();
		}catch(javax.naming.NameNotFoundException e2){
			throw new javax.naming.NameNotFoundException("Data source Name [" + dn + "] not found!");
		}
	}catch(Exception e){
		throw e;
	}
	return conn;
}




%>

<% 
request.setCharacterEncoding("big5");
response.setCharacterEncoding("UTF-8");
String MerchantID = request.getParameter("MerchantID");
String TerminalID = request.getParameter("TerminalID");
String OrderID = request.getParameter("OrderID");
String TransAmt = request.getParameter("TransAmt");
String ResponseCode = request.getParameter("ResponseCode");
String ResponseMsg = request.getParameter("ResponseMsg");

// jdbc/BookDataSQLver

java.sql.Connection conn = null;
PreparedStatement ps = null;
ResultSet rs = null;
try{
	String sqlstr = "Select * from PRODUCTS ";
	ps=conn.prepareStatement(sqlstr);
	rs = ps.executeQuery();
	if (rs.next()) {
		out.print("rs getString " + rs.getString(2));
	}
}catch(Exception e){
	e.printStackTrace();
}finally{
	_closeRS(rs);
	_closeStmt(ps);
	if(conn!=null){
		try{
			conn.close();
		}catch(SQLException sex){}finally{conn=null;}
	}
}

%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NCCC Response</title>

</head>
<body onload="shownum()">

<table>
<tr><td>特店代號： ${MerchantID}</td></tr>
<tr><td>端末機代碼：${TerminalID}</td></tr>
<tr><td>訂單編號：${OrderID}</td></tr>
<tr><td>訂單總金額：${TransAmt}</td></tr>
<tr><td>交易代碼：${ResponseCode}</td></tr>
<tr><td>${ResponseMsg}</td></tr>
</table>

<!-- 自動跳轉回訂單成立頁面 -->
<meta http-equiv="refresh" content ="2;url=<c:url value='/shoppingCart/removeShoppingCart?ResponseCode=${ResponseCode}'/>" />;

</body>
</html>
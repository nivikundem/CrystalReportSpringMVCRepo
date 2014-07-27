<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.*"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<style type="text/css"><%@include file="css/crossing_stylesheet.css" %></style><!--  stylesheet -->
	<title>About View</title> 	
</head>
<body>
	<div id="container">
    <!-- header -->
   	<%@ include file="header.jsp" %>
	<!-- menu -->
	<div id="menu">	   
	   	<%@ include file="menu.jsp" %>
	</div>

	<!--content-->
	<div id="content">
		   <div id="textPara">		   
		  Demo for using <br/>
		  <ul>
		     <li>Spring MVC </li>
		     <li>Servlet</li>
		     <li>JSP </li>
		     <li>JSTL</li>
		     <li>Converting Crystal report into PDF format using java </li>
		     <li>MySQL</li>
		  </ul>		   
		  </div>	
	</div>
	<!-- footer -->
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>
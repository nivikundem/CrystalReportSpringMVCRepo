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
	<title>Bridge Crossing Activity</title> 
    <script src="/scripts/utility.js"></script> 
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script>
    $(document).ready(function(){    	
    	quickSearchFormSubmit();
    	crossingActivityFormSubmit();
   	});
    </script>
 </head>
<body>
	<div id="container">
		
    <!-- header -->
	<%@ include file="header.jsp" %>
	
	<!-- menu -->
	<div id="menu">	   
	   <%@ include file="menu.jsp" %>	   
		<form  method="GET" action='ActivityView' name="quickSearchForm" id="quickSearchForm">
			<div>
				&nbsp;Vrn &nbsp; <input type="text" id="quickSearchTextbox" name="quickSearchTextbox"/>
				&nbsp;
				<div id="errormessage1"></div>
				<input type="submit" name="quickSearchButton" value="Search" class="quicksearchbutton" />
				<br/>   
				<div>
				<c:choose>
					<c:when test="${selectedPdfs != null}">
					<div id="successPara">
					PDF(s) are available at C:\CR_to_PDF\PDFs
					for the UID:
					<br/> 				    
					<c:forEach items="${selectedPdfs}" var="selectedPdf">
					<c:out value="${selectedPdf}"/><br/>
					</c:forEach>	
					</div>
					</c:when>	 	
				</c:choose>  
				</div>   
				<br/>
			</div>
		</form>
		<div id="division"><img src="finalCountPieChartByVehicletype" id="chart"/>  </div>
	</div>

	<!--content-->
	<div id="content">
	     <c:choose>
	    <c:when test="${fn:length(crossingDetailsObjList) > 0}">
		<br/><br/>
		<form  method="POST" action='PdfGenView' name="crossingActivityForm" id="crossingActivityForm">  
			<div>  
			<table id="activitytable">
			   	<tr>
					<th>UID</th>
					<th><a href="javascript:void(0);" onClick="sortFormSubmit('vrn');return false;">Vehicle Registration Number</a></th>
					<th>Vehicle Type</th>
					<th>Gate No</th>
					<th>Direction</th>
					<th>Date And Time</th>	   
					<th>PDF? <div id="errormessage2"></div></th>	                 
				</tr>

				<c:forEach items="${crossingDetailsObjList}" var="crossingDetailsObj">
					<tr>						
						<td><c:out value="${crossingDetailsObj.getUid()}" /></td>
						<td><c:out value="${crossingDetailsObj.getVrn()}" /></td>
						<td><c:out value="${crossingDetailsObj.getVehicleType()}" /></td>	
						<td><c:out value="${crossingDetailsObj.getGateNumber()}" /></td>							
						<td><c:out value="${crossingDetailsObj.getDirection()}" /></td>		
						<td><c:out value="${crossingDetailsObj.getCrossingDateTime()}" /></td>
						<td><span title="${crossingDetailsObj.getVrn()}"><input type="checkbox" class="pdf" name="pdf" value="${crossingDetailsObj.getUid()}"/></span></td>	 	
					</tr>
				</c:forEach>	
				<tr>
				<td colspan="6" align="center">
				     <a id="folink" href="javascript:void(0);" onClick="window.open('DisplayHtmlView', 'DisplayHtmlView', 'width=400, height=200');">Full Report HTML View</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				     <a id="folink"  href="javascript:void(0);" onClick="window.open('DisplayPdfView', 'DisplayPdfView', 'width=400, height=200');">Full Report PDF View</a></td>		
				<td><input type="submit" id="pdfgen" name="pdfgen" value="PDF" align="right"/>&nbsp;&nbsp;</td>		 
				</tr>
			</table>
			</div>
		</form>    
		<!--  forms with with hidden values -->    
		<form  method="GET" action='ActivityView' name="sortForm">		   
		<input type="hidden" name="sortField" value="date">
		</form> 
		</c:when>
		<c:otherwise>
		   <div id="textPara"> No records match this criteria.</div>
		</c:otherwise>
		</c:choose>
	</div>
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>
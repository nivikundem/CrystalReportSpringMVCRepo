CrystalReportSpringMVCRepo
==================
Demo of displaying data from the db table using MVC frame work, and producing the pdfs using Crystal Reports.

BridgeCrossingWebApplication
-------
1. In the -servlet.xml file declare the controllers class
2. In the controller give the path of the jsp file as RequestMapping value.
3. Use Model object to pass the db tables result set.
4. Using JSTL display the records in the jsp page.
5. Using ReportDocument API access the Crystal report with the MySQL procedure data and convert into PDF
   
To Run
-------
Deploy to the tomcat server[Export as war and drop in tomcats webapps] and in the browser you can access it with the url-  http://localhost:8080/BridgeCrossingWebApplication/ActivityView 

Technologies
---------
- Html/css/jQuery/Javascript
- JSTL
- J2EE
- Spring MVC
- Tomcat
- MySQL
- Crystal reports






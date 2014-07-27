package com.bridge.charts;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.data.jdbc.JDBCPieDataset;
import com.crossing.utilities.DBDetails;


public class CountPieChartByVehicletype extends HttpServlet {
private static final long serialVersionUID = 1L;
private static DBDetails dBDetails = new DBDetails();

/**
 * doGetServlet to drawChart
 */
protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {	
	Connection connection = null;
	try {
		connection=dBDetails.getDBConnection();
	} catch (SQLException e) {
	e.printStackTrace();
	} catch (ClassNotFoundException e) {		
		e.printStackTrace();
	}	
	JDBCPieDataset dataset = new JDBCPieDataset(connection);
	try {		
		dataset.executeQuery(dBDetails.getCountByVehicleTypeQuery());
	} catch (SQLException e) {		
		e.printStackTrace();
	}            	
	ChartConstructor chartConstructor = new ChartConstructor();
	String title = "Count By Vehicle Type";
	chartConstructor.drawChart(response, dataset, title);
 } 
} 





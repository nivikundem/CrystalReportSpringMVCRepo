package com.crossing.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.bridge.crossing.model.CrossingActivityObjModel;

public class DBDetails {
	
	private static final Logger LOG = Logger.getLogger(DBDetails.class.getName());
	 //get database connections
	/**
	 * getDBConnection get mysql db connection details
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getDBConnection() throws ClassNotFoundException,
	SQLException {
		// Load the mySql Driver class
		Class.forName("com.mysql.jdbc.Driver");		
		// build the connection string, and get a connection
		String db_connect_string = "jdbc:mysql://localhost/";	
		String dbName = "qe2_bridge";
		String userName = "root"; 
		String password = "root";
		Connection con = DriverManager.getConnection(db_connect_string+dbName,userName,password);	
		return con;
    }
    	
	/**
	 * getQueryString
	 * @param sortString 
	 * @param quickSearchTextboxVar 
	 * @param sqlQueryType
	 * @return
	 */
	public String getSqlQuery(String quickSearchTextboxVar, String sortString){		
		String	sqlQuery = "SELECT uid, gate_number , vehicle_type , direction, vrn, crossing_datetime FROM qe2_bridge.bridge_crossing_performance   ";     			  			
		if(!quickSearchTextboxVar.equalsIgnoreCase("ALL")){
			sqlQuery = sqlQuery + " where vrn like('%"+quickSearchTextboxVar+"%')";
		}
		if(sortString.equalsIgnoreCase("vrn")){
			sqlQuery = sqlQuery + "order by  vrn ";
		}
		else{
			sqlQuery = sqlQuery + " order by crossing_datetime desc";
		}
		return sqlQuery;		
	}
	
	/**
	 * getCountByVehicleTypeQuery
	 * @return
	 */
	  public String getCountByVehicleTypeQuery(){		   
		   String countByVehicleTypeQuery = "select vehicle_type vehicleType, count(vehicle_type) 	finalCount from qe2_bridge.bridge_crossing_performance group by vehicle_type \n";
			   return countByVehicleTypeQuery;		   
	   }
}

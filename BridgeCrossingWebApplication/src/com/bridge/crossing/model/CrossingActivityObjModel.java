package com.bridge.crossing.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.crossing.pojos.CrossingActivityDetailsPOJO;
import com.crossing.utilities.DBDetails;


 /**
	 * @param args
	 * Nivi Kundem
	 */ 

public class CrossingActivityObjModel {
	
	private static final Logger LOG = Logger.getLogger(CrossingActivityObjModel.class.getName());
	private static DBDetails dBDetails = new DBDetails();
	
	/**
	 * getActivityDetailsResultList
	 * @param quickSearchTextboxVar
	 * @param sortString
	 * @return
	 */
	public static List<CrossingActivityDetailsPOJO> getActivityDetailsResultList(String quickSearchTextboxVar, String  sortString) {
		Statement stmt;
		Connection con;
		try {			 		
			con = dBDetails.getDBConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(dBDetails.getSqlQuery(quickSearchTextboxVar,sortString));
			List<CrossingActivityDetailsPOJO> resultSetList = getResultSetList(rs);
			stmt.close();
			con.close();
			return resultSetList;

		} catch (Exception e) {
			LOG.info(e.getMessage());

		}
		return null;
	}
	
	
	/**
	 * getResultSetList
	 * @param rs
	 * @return
	 */
	public static List<CrossingActivityDetailsPOJO> getResultSetList(ResultSet rs){
		List<CrossingActivityDetailsPOJO> crossingActivityPojoList = new ArrayList<CrossingActivityDetailsPOJO>();	
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		 try {		 
			while (rs.next()) {				
				try{					
					CrossingActivityDetailsPOJO crossingActivityDetailsPOJO = new CrossingActivityDetailsPOJO();		
					crossingActivityDetailsPOJO.setUid(rs.getInt("uid"));
					crossingActivityDetailsPOJO.setGateNumber(rs.getString("gate_number"));
					crossingActivityDetailsPOJO.setVehicleType(rs.getString("vehicle_type"));
					crossingActivityDetailsPOJO.setDirection(rs.getString("direction"));
					crossingActivityDetailsPOJO.setVrn(rs.getString("vrn"));
					LOG.info(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(rs.getTimestamp("crossing_datetime")));
					crossingActivityDetailsPOJO.setCrossingDateTime(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(rs.getTimestamp("crossing_datetime")));					
					crossingActivityPojoList.add(crossingActivityDetailsPOJO);
				}
				catch (NullPointerException npe)
				{
					LOG.info(npe.toString());
				}
			}

		 } catch (Exception e)
		 {
			 LOG.info(e.toString());
		 }

		return crossingActivityPojoList;
	}
		
}

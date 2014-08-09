package com.xslt.pdf;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.bridge.crossing.model.CrossingActivityObjModel;
import com.bridge.jaxb.Activity;
import com.bridge.jaxb.Activitylist;
import com.crossing.utilities.DBDetails;


public class XmlSource {
	
		private static final Logger LOG = Logger.getLogger(XmlSource.class.getName());
		private static DBDetails dBDetails = new DBDetails();
		
		
	    Activitylist getActivityList() {
	    	
	    	
    	Statement stmt;
		Connection con;
		try {			 		
			con = dBDetails.getDBConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(dBDetails.getSqlQuery("ALL","ALL"));
			Activitylist activitylist = getResultSetList(rs);
			stmt.close();
			con.close();
			return activitylist;

		} catch (Exception e) {
			LOG.info(e.getMessage());

		}
		return null;
	    	
		/*Activity activity = new Activity();
		activity.setVrn("nivi1");
		activity.setVehicleType("nivi2");
		activity.setDirection("nivi3");
		activity.setUid("nivi4");
		activity.setCrossingDateAndTime("nivi5");
		activity.setGateNumber("nivi6");
		Activity activity2 = new Activity();
		activity2.setVrn("nivi11");
		activity2.setVehicleType("nivi21");
		activity2.setDirection("nivi33");
		activity2.setUid("nivi44");
		activity2.setCrossingDateAndTime("nivi55");
		activity2.setGateNumber("nivi66");
		Activitylist activitylist = new Activitylist();
		activitylist.getAct().add(activity);
		activitylist.getAct().add(activity2);
		return activitylist;*/
	}
	    
	    
	    /**
		 * getResultSetList
		 * @param rs
		 * @return
		 */
		public static Activitylist getResultSetList(ResultSet rs){
			Activitylist activitylist = new Activitylist();
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			 try {		 
				while (rs.next()) {				
					try{					
						Activity activity = new Activity();		
						activity.setUid(rs.getInt("uid"));
						activity.setGateNumber(rs.getString("gate_number"));
						activity.setVehicleType(rs.getString("vehicle_type"));
						activity.setDirection(rs.getString("direction"));
						activity.setVrn(rs.getString("vrn"));
						//LOG.info(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(rs.getTimestamp("crossing_datetime")));
						activity.setCrossingDateAndTime(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(rs.getTimestamp("crossing_datetime")));					
						 activitylist.getAct().add(activity);
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

			return activitylist;
		}

}

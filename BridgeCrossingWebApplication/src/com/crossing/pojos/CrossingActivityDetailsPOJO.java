package com.crossing.pojos;

public class CrossingActivityDetailsPOJO {

	/**
	 * @param args
	 * Nivi Kundem
	 */
		
	private int uid;
	private String gateNumber;
	private String vehicleType;
	private String direction;
	private String vrn;
	private String crossingDateTime;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getGateNumber() {
		return gateNumber;
	}
	public void setGateNumber(String gateNumber) {
		this.gateNumber = gateNumber;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getVrn() {
		return vrn;
	}
	public void setVrn(String vrn) {
		this.vrn = vrn;
	}
	public String  getCrossingDateTime() {
		return crossingDateTime;
	}
	public void setCrossingDateTime(String crossingDateTime) {
		this.crossingDateTime = crossingDateTime;
	}
		
}

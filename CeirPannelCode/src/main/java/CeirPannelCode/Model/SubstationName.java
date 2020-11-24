package CeirPannelCode.Model;

public class SubstationName {
	private String unitID;
    private String substation ;
	private String lastIntervalPacketDate;
	private String lastFaultPacketDate;
	
	public SubstationName() {
		
	}
	public SubstationName(String unitID, String substation, String lastIntervalPacketDate, String lastFaultPacketDate) {
		super();
		this.unitID = unitID;
		this.substation = substation;
		this.lastIntervalPacketDate = lastIntervalPacketDate;
		this.lastFaultPacketDate = lastFaultPacketDate;
	}
	public String getUnitID() {
		return unitID;
	}
	public void setUnitID(String unitID) {
		this.unitID = unitID;
	}
	public String getSubstation() {
		return substation;
	}
	public void setSubstation(String substation) {
		this.substation = substation;
	}
	public String getLastIntervalPacketDate() {
		return lastIntervalPacketDate;
	}
	public void setLastIntervalPacketDate(String lastIntervalPacketDate) {
		this.lastIntervalPacketDate = lastIntervalPacketDate;
	}
	public String getLastFaultPacketDate() {
		return lastFaultPacketDate;
	}
	public void setLastFaultPacketDate(String lastFaultPacketDate) {
		this.lastFaultPacketDate = lastFaultPacketDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SubstationName [unitID=");
		builder.append(unitID);
		builder.append(", substation=");
		builder.append(substation);
		builder.append(", lastIntervalPacketDate=");
		builder.append(lastIntervalPacketDate);
		builder.append(", lastFaultPacketDate=");
		builder.append(lastFaultPacketDate);
		builder.append("]");
		return builder.toString();
	}
}

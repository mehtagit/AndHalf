package CeirPannelCode.Model;

public class PortName {
	
	private Integer id ;
	private String modemId;
	private String portId;
	private String modem;
	private Integer statusint;
	
	public PortName() {
		
	}
	public PortName(Integer id, String modemId, String portId, String modem,Integer statusint) {
		super();
		this.id = id;
		this.modemId = modemId;
		this.portId = portId;
		this.modem = modem;
		this.statusint = statusint;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getModemId() {
		return modemId;
	}
	public void setModemId(String modemId) {
		this.modemId = modemId;
	}
	public String getPortId() {
		return portId;
	}
	public void setPortId(String portId) {
		this.portId = portId;
	}
	public String getModem() {
		return modem;
	}
	public void setModem(String modem) {
		this.modem = modem;
	}
	public Integer getStatusint() {
		return statusint;
	}
	public void setStatusint(Integer statusint) {
		this.statusint = statusint;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PortName [id=");
		builder.append(id);
		builder.append(", modemId=");
		builder.append(modemId);
		builder.append(", portId=");
		builder.append(portId);
		builder.append(", modem=");
		builder.append(modem);
		builder.append(", statusint=");
		builder.append(statusint);
		builder.append("]");
		return builder.toString();
	}
	
	
}

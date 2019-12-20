package org.gl.ceir.CeirPannelCode.Model;

public class FilterRequest_UserPaidStatus {
	
		private Integer deviceIdType,deviceType,taxPaidStatus,consignmentStatus;
		  private String createdOn,modifiedOn,nid;
	/*
	 * "nid": "ABC", "deviceStatus": 0, "": 0, "taxPaidStatusInterp": null, "": 0,
	 * "deviceTypeInterp": null,
	 * 
	 * "deviceIdTypeInterp": null, "multiSimStatus": "Y", "country": "US",
	 * "deviceSerialNumber": "73465242", "txnId": "R78450DF", "price": 1000,
	 * "currency": 0, "currencyInterp": null, "firstImei": 193746598310452,
	 * "secondImei": null, "thirdImei": null, "fourthImei": null, "endUserDB": null
	 */
		public Integer getDeviceIdType() {
			return deviceIdType;
		}
		public void setDeviceIdType(Integer deviceIdType) {
			this.deviceIdType = deviceIdType;
		}
		public Integer getDeviceType() {
			return deviceType;
		}
		public void setDeviceType(Integer deviceType) {
			this.deviceType = deviceType;
		}
		public Integer getTaxPaidStatus() {
			return taxPaidStatus;
		}
		public void setTaxPaidStatus(Integer taxPaidStatus) {
			this.taxPaidStatus = taxPaidStatus;
		}
		public String getCreatedOn() {
			return createdOn;
		}
		public void setCreatedOn(String createdOn) {
			this.createdOn = createdOn;
		}
		public String getModifiedOn() {
			return modifiedOn;
		}
		public void setModifiedOn(String modifiedOn) {
			this.modifiedOn = modifiedOn;
		}
		@Override
		public String toString() {
			return "FilterRequest_UserPaidStatus [deviceIdType=" + deviceIdType + ", deviceType=" + deviceType
					+ ", taxPaidStatus=" + taxPaidStatus + ", consignmentStatus=" + consignmentStatus + ", createdOn="
					+ createdOn + ", modifiedOn=" + modifiedOn + ", nid=" + nid + "]";
		}
		public Integer getConsignmentStatus() {
			return consignmentStatus;
		}
		public void setConsignmentStatus(Integer consignmentStatus) {
			this.consignmentStatus = consignmentStatus;
		}
		
		
	
}

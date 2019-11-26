package com.gl.CEIR.CDRPicker.beans;


public class CDRFile {

	private String serialNumber;
	private String recordType;
	private String servedIMSI;
	private String servedIMEI;
	private String servedMSISDN;
	private String calledNumber;
	private String connectedNumber;
	private String roamingNumber;
	private String recordingEntity;
	private String mscOutgoingTKGP;
	private String locationArea;
	private String plmnId;
	private String rncId_value;
	private String locationArea1;
	private String changeTime;
	private String mSCID;
	private String teleservice;
	private String seizureTime;
	private String answerTime;
	private String releaseTime;
	private String callDuration;
	private String causeForTerm;
	private String callReference;
	private String chargeIndicator;
	private String chargedParty;
	private String serviceKey;
	private String networkCallReference;
	private String mSCAddress;
	private String exchangeIdentity;
	private String dialledNumber;
	private String subscriberCategory;
	private String recordSequenceNumber;
	private String hotBillingTag2;
	private String bSCIdentification;
	private String serviceCategory;
	private String transactionIdentification;
	private String outgoingTKGPName;
	private String calledIMSI;
	private String millisecDuration;
	private String callingNumber;
	private String mscIncomingTKGP;
	private String callType;
	private String userType;
	private String dataRate;
	private String incomingTKGPName;
	private String routingCategory;
	private String ci_answerTime;
	private String ci_releaseTime;
	private String ci_roamingNumber; 
	private String originationTime;
	private String destinationNumber;
	private String sourceUnitName;
	private String logicalFileName;
	private String statusMessage;
	private String autoCorrectedCDR;
	




	public CDRFile(String serialNumber, String recordType, String servedIMSI, String servedIMEI, String servedMSISDN,
			String calledNumber, String connectedNumber, String roamingNumber, String recordingEntity,
			String mscOutgoingTKGP, String locationArea, String plmnId, String rncId_value, String locationArea1,
			String changeTime, String mSCID, String teleservice, String seizureTime, String answerTime,
			String releaseTime, String callDuration, String causeForTerm, String callReference, String chargeIndicator,
			String chargedParty, String serviceKey, String networkCallReference, String mSCAddress,
			String exchangeIdentity, String dialledNumber, String subscriberCategory, String recordSequenceNumber,
			String hotBillingTag2, String bSCIdentification, String serviceCategory, String transactionIdentification,
			String outgoingTKGPName, String calledIMSI, String millisecDuration, String callingNumber,
			String mscIncomingTKGP, String callType, String userType, String dataRate, String incomingTKGPName,
			String routingCategory, String ci_answerTime, String ci_releaseTime, String ci_roamingNumber,
			String originationTime, String destinationNumber, String sourceUnitName, String logicalFileName,
			String statusMessage ) {
		super();
		this.serialNumber = serialNumber;
		this.recordType = recordType;
		this.servedIMSI = servedIMSI;
		this.servedIMEI = servedIMEI;
		this.servedMSISDN = servedMSISDN;
		this.calledNumber = calledNumber;
		this.connectedNumber = connectedNumber;
		this.roamingNumber = roamingNumber;
		this.recordingEntity = recordingEntity;
		this.mscOutgoingTKGP = mscOutgoingTKGP;
		this.locationArea = locationArea;
		this.plmnId = plmnId;
		this.rncId_value = rncId_value;
		this.locationArea1 = locationArea1;
		this.changeTime = changeTime;
		this.mSCID = mSCID;
		this.teleservice = teleservice;
		this.seizureTime = seizureTime;
		this.answerTime = answerTime;
		this.releaseTime = releaseTime;
		this.callDuration = callDuration;
		this.causeForTerm = causeForTerm;
		this.callReference = callReference;
		this.chargeIndicator = chargeIndicator;
		this.chargedParty = chargedParty;
		this.serviceKey = serviceKey;
		this.networkCallReference = networkCallReference;
		this.mSCAddress = mSCAddress;
		this.exchangeIdentity = exchangeIdentity;
		this.dialledNumber = dialledNumber;
		this.subscriberCategory = subscriberCategory;
		this.recordSequenceNumber = recordSequenceNumber;
		this.hotBillingTag2 = hotBillingTag2;
		this.bSCIdentification = bSCIdentification;
		this.serviceCategory = serviceCategory;
		this.transactionIdentification = transactionIdentification;
		this.outgoingTKGPName = outgoingTKGPName;
		this.calledIMSI = calledIMSI;
		this.millisecDuration = millisecDuration;
		this.callingNumber = callingNumber;
		this.mscIncomingTKGP = mscIncomingTKGP;
		this.callType = callType;
		this.userType = userType;
		this.dataRate = dataRate;
		this.incomingTKGPName = incomingTKGPName;
		this.routingCategory = routingCategory;
		this.ci_answerTime = ci_answerTime;
		this.ci_releaseTime = ci_releaseTime;
		this.ci_roamingNumber = ci_roamingNumber;
		this.originationTime = originationTime;
		this.destinationNumber = destinationNumber;
		this.sourceUnitName = sourceUnitName;
		this.logicalFileName = logicalFileName;
		this.statusMessage = statusMessage;
		//this.autoCorrectedCDR = autoCorrectedCDR;
	}





	public String getSerialNumber() {
		return serialNumber;
	}





	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}





	public String getRecordType() {
		return recordType;
	}





	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}





	public String getServedIMSI() {
		return servedIMSI;
	}





	public void setServedIMSI(String servedIMSI) {
		this.servedIMSI = servedIMSI;
	}





	public String getServedIMEI() {
		return servedIMEI;
	}





	public void setServedIMEI(String servedIMEI) {
		this.servedIMEI = servedIMEI;
	}





	public String getServedMSISDN() {
		return servedMSISDN;
	}





	public void setServedMSISDN(String servedMSISDN) {
		this.servedMSISDN = servedMSISDN;
	}





	public String getCalledNumber() {
		return calledNumber;
	}





	public void setCalledNumber(String calledNumber) {
		this.calledNumber = calledNumber;
	}





	public String getConnectedNumber() {
		return connectedNumber;
	}





	public void setConnectedNumber(String connectedNumber) {
		this.connectedNumber = connectedNumber;
	}





	public String getRoamingNumber() {
		return roamingNumber;
	}





	public void setRoamingNumber(String roamingNumber) {
		this.roamingNumber = roamingNumber;
	}





	public String getRecordingEntity() {
		return recordingEntity;
	}





	public void setRecordingEntity(String recordingEntity) {
		this.recordingEntity = recordingEntity;
	}





	public String getMscOutgoingTKGP() {
		return mscOutgoingTKGP;
	}





	public void setMscOutgoingTKGP(String mscOutgoingTKGP) {
		this.mscOutgoingTKGP = mscOutgoingTKGP;
	}





	public String getLocationArea() {
		return locationArea;
	}





	public void setLocationArea(String locationArea) {
		this.locationArea = locationArea;
	}





	public String getPlmnId() {
		return plmnId;
	}





	public void setPlmnId(String plmnId) {
		this.plmnId = plmnId;
	}





	public String getRncId_value() {
		return rncId_value;
	}





	public void setRncId_value(String rncId_value) {
		this.rncId_value = rncId_value;
	}





	public String getLocationArea1() {
		return locationArea1;
	}





	public void setLocationArea1(String locationArea1) {
		this.locationArea1 = locationArea1;
	}





	public String getChangeTime() {
		return changeTime;
	}





	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}





	public String getmSCID() {
		return mSCID;
	}





	public void setmSCID(String mSCID) {
		this.mSCID = mSCID;
	}





	public String getTeleservice() {
		return teleservice;
	}





	public void setTeleservice(String teleservice) {
		this.teleservice = teleservice;
	}





	public String getSeizureTime() {
		return seizureTime;
	}





	public void setSeizureTime(String seizureTime) {
		this.seizureTime = seizureTime;
	}





	public String getAnswerTime() {
		return answerTime;
	}





	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}





	public String getReleaseTime() {
		return releaseTime;
	}





	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}





	public String getCallDuration() {
		return callDuration;
	}





	public void setCallDuration(String callDuration) {
		this.callDuration = callDuration;
	}





	public String getCauseForTerm() {
		return causeForTerm;
	}





	public void setCauseForTerm(String causeForTerm) {
		this.causeForTerm = causeForTerm;
	}





	public String getCallReference() {
		return callReference;
	}





	public void setCallReference(String callReference) {
		this.callReference = callReference;
	}





	public String getChargeIndicator() {
		return chargeIndicator;
	}





	public void setChargeIndicator(String chargeIndicator) {
		this.chargeIndicator = chargeIndicator;
	}





	public String getChargedParty() {
		return chargedParty;
	}





	public void setChargedParty(String chargedParty) {
		this.chargedParty = chargedParty;
	}





	public String getServiceKey() {
		return serviceKey;
	}





	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}





	public String getNetworkCallReference() {
		return networkCallReference;
	}





	public void setNetworkCallReference(String networkCallReference) {
		this.networkCallReference = networkCallReference;
	}





	public String getmSCAddress() {
		return mSCAddress;
	}





	public void setmSCAddress(String mSCAddress) {
		this.mSCAddress = mSCAddress;
	}





	public String getExchangeIdentity() {
		return exchangeIdentity;
	}





	public void setExchangeIdentity(String exchangeIdentity) {
		this.exchangeIdentity = exchangeIdentity;
	}





	public String getDialledNumber() {
		return dialledNumber;
	}





	public void setDialledNumber(String dialledNumber) {
		this.dialledNumber = dialledNumber;
	}





	public String getSubscriberCategory() {
		return subscriberCategory;
	}





	public void setSubscriberCategory(String subscriberCategory) {
		this.subscriberCategory = subscriberCategory;
	}





	public String getRecordSequenceNumber() {
		return recordSequenceNumber;
	}





	public void setRecordSequenceNumber(String recordSequenceNumber) {
		this.recordSequenceNumber = recordSequenceNumber;
	}





	public String getHotBillingTag2() {
		return hotBillingTag2;
	}





	public void setHotBillingTag2(String hotBillingTag2) {
		this.hotBillingTag2 = hotBillingTag2;
	}





	public String getbSCIdentification() {
		return bSCIdentification;
	}





	public void setbSCIdentification(String bSCIdentification) {
		this.bSCIdentification = bSCIdentification;
	}





	public String getServiceCategory() {
		return serviceCategory;
	}





	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}





	public String getTransactionIdentification() {
		return transactionIdentification;
	}





	public void setTransactionIdentification(String transactionIdentification) {
		this.transactionIdentification = transactionIdentification;
	}





	public String getOutgoingTKGPName() {
		return outgoingTKGPName;
	}





	public void setOutgoingTKGPName(String outgoingTKGPName) {
		this.outgoingTKGPName = outgoingTKGPName;
	}





	public String getCalledIMSI() {
		return calledIMSI;
	}





	public void setCalledIMSI(String calledIMSI) {
		this.calledIMSI = calledIMSI;
	}





	public String getMillisecDuration() {
		return millisecDuration;
	}





	public void setMillisecDuration(String millisecDuration) {
		this.millisecDuration = millisecDuration;
	}





	public String getCallingNumber() {
		return callingNumber;
	}





	public void setCallingNumber(String callingNumber) {
		this.callingNumber = callingNumber;
	}





	public String getMscIncomingTKGP() {
		return mscIncomingTKGP;
	}





	public void setMscIncomingTKGP(String mscIncomingTKGP) {
		this.mscIncomingTKGP = mscIncomingTKGP;
	}





	public String getCallType() {
		return callType;
	}





	public void setCallType(String callType) {
		this.callType = callType;
	}





	public String getUserType() {
		return userType;
	}





	public void setUserType(String userType) {
		this.userType = userType;
	}





	public String getDataRate() {
		return dataRate;
	}





	public void setDataRate(String dataRate) {
		this.dataRate = dataRate;
	}





	public String getIncomingTKGPName() {
		return incomingTKGPName;
	}





	public void setIncomingTKGPName(String incomingTKGPName) {
		this.incomingTKGPName = incomingTKGPName;
	}





	public String getRoutingCategory() {
		return routingCategory;
	}





	public void setRoutingCategory(String routingCategory) {
		this.routingCategory = routingCategory;
	}





	public String getCi_answerTime() {
		return ci_answerTime;
	}





	public void setCi_answerTime(String ci_answerTime) {
		this.ci_answerTime = ci_answerTime;
	}





	public String getCi_releaseTime() {
		return ci_releaseTime;
	}





	public void setCi_releaseTime(String ci_releaseTime) {
		this.ci_releaseTime = ci_releaseTime;
	}





	public String getCi_roamingNumber() {
		return ci_roamingNumber;
	}





	public void setCi_roamingNumber(String ci_roamingNumber) {
		this.ci_roamingNumber = ci_roamingNumber;
	}





	public String getOriginationTime() {
		return originationTime;
	}





	public void setOriginationTime(String originationTime) {
		this.originationTime = originationTime;
	}





	public String getDestinationNumber() {
		return destinationNumber;
	}





	public void setDestinationNumber(String destinationNumber) {
		this.destinationNumber = destinationNumber;
	}





	public String getSourceUnitName() {
		return sourceUnitName;
	}





	public void setSourceUnitName(String sourceUnitName) {
		this.sourceUnitName = sourceUnitName;
	}





	public String getLogicalFileName() {
		return logicalFileName;
	}





	public void setLogicalFileName(String logicalFileName) {
		this.logicalFileName = logicalFileName;
	}





	public String getStatusMessage() {
		return statusMessage;
	}





	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}





	public String getAutoCorrectedCDR() {
		return autoCorrectedCDR;
	}





	public void setAutoCorrectedCDR(String autoCorrectedCDR) {
		this.autoCorrectedCDR = autoCorrectedCDR;
	}





	@Override
	public String toString() {
		return "CDRFile [serialNumber=" + serialNumber + ", recordType=" + recordType + ", servedIMSI=" + servedIMSI
				+ ", servedIMEI=" + servedIMEI + ", servedMSISDN=" + servedMSISDN + ", calledNumber=" + calledNumber
				+ ", connectedNumber=" + connectedNumber + ", roamingNumber=" + roamingNumber + ", recordingEntity="
				+ recordingEntity + ", mscOutgoingTKGP=" + mscOutgoingTKGP + ", locationArea=" + locationArea
				+ ", plmnId=" + plmnId + ", rncId_value=" + rncId_value + ", locationArea1=" + locationArea1
				+ ", changeTime=" + changeTime + ", mSCID=" + mSCID + ", teleservice=" + teleservice + ", seizureTime="
				+ seizureTime + ", answerTime=" + answerTime + ", releaseTime=" + releaseTime + ", callDuration="
				+ callDuration + ", causeForTerm=" + causeForTerm + ", callReference=" + callReference
				+ ", chargeIndicator=" + chargeIndicator + ", chargedParty=" + chargedParty + ", serviceKey="
				+ serviceKey + ", networkCallReference=" + networkCallReference + ", mSCAddress=" + mSCAddress
				+ ", exchangeIdentity=" + exchangeIdentity + ", dialledNumber=" + dialledNumber
				+ ", subscriberCategory=" + subscriberCategory + ", recordSequenceNumber=" + recordSequenceNumber
				+ ", hotBillingTag2=" + hotBillingTag2 + ", bSCIdentification=" + bSCIdentification
				+ ", serviceCategory=" + serviceCategory + ", transactionIdentification=" + transactionIdentification
				+ ", outgoingTKGPName=" + outgoingTKGPName + ", calledIMSI=" + calledIMSI + ", millisecDuration="
				+ millisecDuration + ", callingNumber=" + callingNumber + ", mscIncomingTKGP=" + mscIncomingTKGP
				+ ", callType=" + callType + ", userType=" + userType + ", dataRate=" + dataRate + ", incomingTKGPName="
				+ incomingTKGPName + ", routingCategory=" + routingCategory + ", ci_answerTime=" + ci_answerTime
				+ ", ci_releaseTime=" + ci_releaseTime + ", ci_roamingNumber=" + ci_roamingNumber + ", originationTime="
				+ originationTime + ", destinationNumber=" + destinationNumber + ", sourceUnitName=" + sourceUnitName
				+ ", logicalFileName=" + logicalFileName + ", statusMessage=" + statusMessage + ", autoCorrectedCDR="
				+ autoCorrectedCDR + "]";
	}


}

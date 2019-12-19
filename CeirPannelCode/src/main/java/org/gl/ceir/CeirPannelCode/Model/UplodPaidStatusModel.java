package org.gl.ceir.CeirPannelCode.Model;

import org.springframework.stereotype.Component;

@Component
public class UplodPaidStatusModel {

	   private String country ,createdOn,email,firstName,lastName,locality,middleName,modifiedOn,nid,phoneNo,propertyLocation,province,
	   fileName,regularizeDeviceDbs,street;
		private Integer id;
		
		@Override
		public String toString() {
			return "UplodPaidStatusModel [country=" + country + ", createdOn=" + createdOn + ", email=" + email
					+ ", firstName=" + firstName + ", lastName=" + lastName + ", locality=" + locality + ", middleName="
					+ middleName + ", modifiedOn=" + modifiedOn + ", nid=" + nid + ", phoneNo=" + phoneNo
					+ ", propertyLocation=" + propertyLocation + ", province=" + province + ", fileName=" + fileName
					+ ", regularizeDeviceDbs=" + regularizeDeviceDbs + ", street=" + street + ", id=" + id + "]";
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getCreatedOn() {
			return createdOn;
		}
		public void setCreatedOn(String createdOn) {
			this.createdOn = createdOn;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getLocality() {
			return locality;
		}
		public void setLocality(String locality) {
			this.locality = locality;
		}
		public String getMiddleName() {
			return middleName;
		}
		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}
		public String getModifiedOn() {
			return modifiedOn;
		}
		public void setModifiedOn(String modifiedOn) {
			this.modifiedOn = modifiedOn;
		}
		public String getNid() {
			return nid;
		}
		public void setNid(String nid) {
			this.nid = nid;
		}
		public String getPhoneNo() {
			return phoneNo;
		}
		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}
		public String getPropertyLocation() {
			return propertyLocation;
		}
		public void setPropertyLocation(String propertyLocation) {
			this.propertyLocation = propertyLocation;
		}
		public String getProvince() {
			return province;
		}
		public void setProvince(String province) {
			this.province = province;
		}
		public String getRegularizeDeviceDbs() {
			return regularizeDeviceDbs;
		}
		public void setRegularizeDeviceDbs(String regularizeDeviceDbs) {
			this.regularizeDeviceDbs = regularizeDeviceDbs;
		}
		public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		
		
		
		

}


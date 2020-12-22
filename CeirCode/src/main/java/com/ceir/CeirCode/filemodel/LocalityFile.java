package com.ceir.CeirCode.filemodel;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class LocalityFile {
	  
		@CsvBindByName(column = "Created On")
		@CsvBindByPosition(position = 0)
		private String createdOn;
		@CsvBindByName(column = "Country")
		@CsvBindByPosition(position = 1)
		private String country;
		
		@CsvBindByName(column = "Province")
		@CsvBindByPosition(position = 2)
		private String province;
		
		
		@CsvBindByName(column = "District")
		@CsvBindByPosition(position = 3)
		private String district;
		
		@CsvBindByName(column = "Commune")
		@CsvBindByPosition(position = 4)
		private String commune;
		
		@CsvBindByName(column = "Village")
		@CsvBindByPosition(position = 5)
		private String village;
		public String getCreatedOn() {
			return createdOn;
		}

		public void setCreatedOn(String createdOn) {
			this.createdOn = createdOn;
		}

		public String getCountry() {
			return country;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("LocalityFile [createdOn=");
			builder.append(createdOn);
			builder.append(", country=");
			builder.append(country);
			builder.append(", province=");
			builder.append(province);
			builder.append(", district=");
			builder.append(district);
			builder.append(", commune=");
			builder.append(commune);
			builder.append(", village=");
			builder.append(village);
			builder.append("]");
			return builder.toString();
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getCommune() {
			return commune;
		}

		public void setCommune(String commune) {
			this.commune = commune;
		}

		public String getVillage() {
			return village;
		}

		public void setVillage(String village) {
			this.village = village;
		}


}

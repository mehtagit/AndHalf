package org.gl.ceir.CeirPannelCode.Model;

import org.springframework.stereotype.Component;

@Component
public class AddressResponse {
	private String province;
	private String district;

	private long id;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddressResponse [province=");
		builder.append(province);
		builder.append(", district=");
		builder.append(district);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}

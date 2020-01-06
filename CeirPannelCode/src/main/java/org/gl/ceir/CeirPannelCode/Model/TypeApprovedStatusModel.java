package org.gl.ceir.CeirPannelCode.Model;

public class TypeApprovedStatusModel {
		private String adminUserType,txnId;
		private Integer adminUserId,adminApproveStatus,featureId;
		
		
		
		@Override
		public String toString() {
			return "TypeApprovedStatusModel [adminUserType=" + adminUserType + ", txnId=" + txnId + ", adminUserId="
					+ adminUserId + ", adminApproveStatus=" + adminApproveStatus + ", featureId=" + featureId + "]";
		}
		
		

		public Integer getFeatureId() {
			return featureId;
		}



		public void setFeatureId(Integer featureId) {
			this.featureId = featureId;
		}



		public String getAdminUserType() {
			return adminUserType;
		}
		
		public void setAdminUserType(String adminUserType) {
			this.adminUserType = adminUserType;
		}
		public String getTxnId() {
			return txnId;
		}
		public void setTxnId(String txnId) {
			this.txnId = txnId;
		}
		public Integer getAdminUserId() {
			return adminUserId;
		}
		public void setAdminUserId(Integer adminUserId) {
			this.adminUserId = adminUserId;
		}
		public Integer getAdminApproveStatus() {
			return adminApproveStatus;
		}
		public void setAdminApproveStatus(Integer adminApproveStatus) {
			this.adminApproveStatus = adminApproveStatus;
		}
		
		
}

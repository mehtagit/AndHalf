package com.gl.ceir.config;

public interface ConfigTags {
	// Message

	// System
	String page_size_for_Notification = "page_size_for_Notification";
	String sample_file_link = "sample_file_link";
	String manuals_link = "manuals_link";

	String system_error_filepath = "system_error_filepath";
	String system_error_file_link = "system_error_file_link";

	String system_upload_filepath = "system_upload_filepath";
	String upload_file_link = "upload_file_link";
	
	String new_year_date_register_device = "new_year_date_register_device";
	String grace_period_for_rgister_device = "grace_period_for_rgister_device";

		//----------------
	String file_consignment_download_dir = "file_consignment_download_dir";
	String file_consignment_download_link = "file_consignment_download_link";

	String file_stock_download_dir = "file_stock_download_dir";
	String file_stock_download_link = "file_stock_download_link";

	String file_stolen_and_recovery_dir = "file_stolen_and_recovery_dir";
	String file_stolen_and_recovery_download_link = "file_stolen_and_recovery_download_link";

	String file_custom_regularized_dir = "file_custom_regularized_dir";
	String file_custom_regularized_download_link = "file_custom_regularized_download_link"; 

	String file_audit_trail_download_dir = "file_audit_trail_download_dir";
	String file_audit_trail_download_link = "file_audit_trail_download_link";

	String file_end_user_download_dir = "file_end_user_download_dir";
	String file_end_user_download_link = "file_end_user_download_link";
	
	String file_system_config_list_download_dir = "file_system_config_list_download_dir";
	String file_system_config_list_download_link = "file_system_config_list_download_link";

	// User
	String default_visa_expiration_days = "default_visa_expiration_days";

	// Policy
	String max_end_user_device_count = "max_end_user_device_count";

}

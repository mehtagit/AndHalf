/**
 * 
 */


	    
	

function chooseStolenOption()
{
	$('#chooseStolenOption').openModal();
}

function openStolenRecoveryPage(pageType)
{
	console.log("###");
	window.location.href = "./openlawfulStolenRecoveryPage?pageType="+pageType;
}

function showSingleFormDiv()
{
	$("#SingleForm").css("display", "block");
	$("#Bulkform").css("display", "none");
	
	$('#singleFormSubmit').trigger("reset");
	$('#bulkFormSubmit').trigger("reset");
	
}
function  showBulkFormDiv(){
	
	$("#SingleForm").css("display", "none");
	$("#Bulkform").css("display", "block");
	
	$('#singleFormSubmit').trigger("reset");
	$('#bulkFormSubmit').trigger("reset");
	
}

function singleRecoverydiv()
{
	$("#singleRecoveryDiv").css("display", "block");
	$("#bulkRecoveryDiv").css("display", "none");
	
	$('#singleRecoveryForm').trigger("reset");
	$('#bulkRecoveryForm').trigger("reset");
	
}
function  showBulkRecovery(){
	
	$("#singleRecoveryDiv").css("display", "none");
	$("#bulkRecoveryDiv").css("display", "block");
	
	$('#singleRecoveryForm').trigger("reset");
	$('#bulkRecoveryForm').trigger("reset");
	
}




$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
	
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#singleStolendeviceIDType,#sigleRecoverydeviceIDType');

	}
});


$.getJSON('./getDropdownList/DEVICE_TYPE', function(data) {
	
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#singleStolendeviceType,#sigleRecoverydeviceType');

	}
});

$.getJSON('./getDropdownList/MULTI_SIM_STATUS', function(data) {
	
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#sigleRecoverydeviceSimStatus,#singleStolenSimStatus');
		
	}
});

$.getJSON('./getDropdownList/OPERATORS', function(data) {
	
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#singleStolenOperator');
	}
});

$.getJSON('./getDropdownList/COMPLAINT_TYPE', function(data) {
	
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#singleStolenComplaintType,#deviceBulkStolenComplaint');
	}
});


$.getJSON('./getDropdownList/DEVICE_STATUS', function(data) {
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#sigleRecoverydeviceStatus');
	
	}
});


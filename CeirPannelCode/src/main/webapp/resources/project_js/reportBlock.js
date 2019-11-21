
function submitBlockDevicesRequest()
{
	var deviceType=$('#blockdeviceType').val();
	var multipleSimStatus=$('#blockmultipleSimStatus').val();
	var serialNumber=$('#blockserialNumber').val();
	var remark=$('#blockremark').val();
	var IMEI1=$('#blockIMEI1').val();
	var IMEI2=$('#blockIMEI2').val();
	var IMEI3=$('#blockIMEI3').val();
	var IMEI4=$('#blockIMEI4').val();
	console.log("****");
	console.log("sucess include deviceType="+deviceType+" multipleSimStatus="+multipleSimStatus+" serialNumber="+serialNumber+" remark="+remark+" IMEI1="+IMEI1 );

	var formData= new FormData();
	 	
		formData.append('deviceType',deviceType);
	 	formData.append('multipleSimStatus',multipleSimStatus);
	 	formData.append('serialNumber',serialNumber);
		formData.append('remark',remark);
		formData.append('IMEI1',IMEI1);
		formData.append('IMEI2',IMEI2);
		formData.append('IMEI3',IMEI3);
		formData.append('IMEI4',IMEI4);
		
		
		console.log(JSON.stringify(formData));
		console.log("*********");
	 	
	 $.ajax({
		url: './blockSingleDevices',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function (data, textStatus, jqXHR) {
			
			 console.log(data);
			 $('#markAsBlock').copenModal();
			 if(data.errorCode==200){
			
		     $('#singleDeviceBlockMessage').text('');
			 $('#singleDeviceBlockMessage').text(data.message);
				 }
			 else{
				 $('#singleDeviceBlockMessage').text('');
 				 $('#singleDeviceBlockMessage').text(data.message);
			 }
		   // $('#updateConsignment').modal('open'); 
			//alert("success");
			
		},
		error: function (jqXHR, textStatus, errorThrown) {
		console.log("error in ajax")
		}
	});
}

function uploadEndUserStock()
{
	var formData= new FormData();
	 var endUsercsvUploadFile=$('#endUsercsvUploadFile').val();
	 var endUseremail=$('#endUseremail').val();
	 var endUserquantity=$('#endUserquantity').val();
	 var request={
		  "quantity": endUserquantity,
		   "userType": "End User",
		   "user": {
		       "userProfile": {
		           "email": endUseremail
		          },
		 },
	
	 };
	    formData.append('file', $('#endUsercsvUploadFile')[0].files[0]);
		formData.append("request",JSON.stringify(request));
		$.ajax({
			url: './ednUserStockUpload',
			type: 'POST',
			data: formData,
			processData: false,
			contentType: false,
			success: function (data, textStatus, jqXHR) {
				console.log("in suucess method");
				console.log(data);
				$('#endUserStockModal').openModal();
				if(data.errorCode==0){
					$('#endUsertXnId').text(data.txnId);
				}
				else{
					$('#sucessMessageId').text('');
					$('#sucessMessageId').text(data.message);
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				console.log("error in ajax")

			}
		});
		return false;	
		
}

function validateTxnId()
{
	var txnId=$('#checktransactionID').val();
	$.ajax({
		url : "./openEndUserStockPopup?txnId="+txnId,
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'GET',
		success : function(data) {
			console.log(data)
			setViewPopupData(data);
		},
		error : function() {
		$('#errorModal').openModal();
		}
	});
    return false;
}



function setViewPopupData(data){
	console.log("_________________++++++++++"+data)
   /* $("#viewStockModal").openModal();*/
	$('#singleInput').css("display", "none");
	$('#inputDetails').css("display", "block");
	$("#transactionID").val(data.txnId);
	$("#uploadDate").val(data.user.createdOn);
	$("#viewUploadFile").val(data.fileName);
	console.log(data.stockStatus);
	if(data.stockStatus=='2')
		{
		console.log("if condition");
		$('#errorFileStatusDiv').css("display", "block");
    	$("#errorFileStatus").val('Error');
    	$("#errorFileName").val(data.fileName);
    	$('#updateEndUserStockOK').css("display", "none");
    	$('#updateEndUserStock').css("display", "block");
    	
		}
	else{
		console.log("else condition");
		$('#errorFileStatusDiv').css("display", "none");
		$('#updateEndUserStockOK').css("display", "block");
		$('#updateEndUserStock').css("display", "none");
		
	}
}

function endUserStockFileDownload(){
	var fileName=$("#viewUploadFile").val();
	var txnId=$("#transactionID").val();
	window.location.href=contextpath+"/Consignment/dowloadFiles/actual/"+fileName+"/"+txnId+"/DEFAULT";
	
	//.//Consignment%20(18).csv/C20200121142445617/DEFAULT
}


function updateFile()
{
	var fileName=$("#viewUploadFile").val();
	var txnId=$("#transactionID").val();
	
	var formData= new FormData();
	formData.append('file', $('#viewUploadFile')[0].files[0]);
	formData.append('txnId',txnId);
	$.ajax({
		url: 'updateEndUserUploadedStock',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function (data, textStatus, jqXHR) {

			console.log(data);
		/*	$('#editStockModal').closeModal();
			$('#successUpdateStockModal').openModal();
			if(data.errorCode==200){

				$('#stockSucessMessage').text('');
				$('#stockSucessMessage').text(operationNotAllowed);
			}
			else if (data.errorCode==0){
				$('#stockSucessMessage').text('');
				$('#stockSucessMessage').text(updateMsg+' '+(data.txnId)+' '+hasBeenUpdated);
			}
			else{
				$('#stockSucessMessage').text('');
				$('#stockSucessMessage').text(errorOccured);
			}*/
//			$('#updateConsignment').modal('open'); 
//			alert("success");

		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")
		}
	});
return false;
}
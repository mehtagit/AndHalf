var featureId = 26;
var userId = $("body").attr("data-userID");
var cierRoletype = sessionStorage.getItem("cierRoletype");
var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
var userType = $("body").attr("data-roleType");

var msisdn = $("body").attr("data-msisdn");
var imei = $("body").attr("data-imei");

var identifierType = $("body").attr("data-deviceIdType");
var deviceIdType = identifierType.replace(" ","_");


$.i18n().locale = lang;


$.i18n().load( {
	'en': './resources/i18n/en.json',
	'km': './resources/i18n/km.json'
}).done( function() { 
	
});

$(document).ready(function(){
	$('div#initialloader').fadeIn('fast');
	getGsmaDetails();
	stateTable();
	deviceTable();
	notificationTable();

});


function getGsmaDetails(){
	console.log("msisdn-->"+msisdn+" imei-->"+imei+" deviceIdType-->"+deviceIdType);
	$.ajax({
		url: './getGsmaDetails?imei='+imei+'&msisdn='+msisdn+'&identifierType='+deviceIdType+'',
		type: 'POST',
		processData: false,
		contentType: false,
		success: function (data, textStatus, jqXHR) {
			setGsmaDetails(data);
		}
	});
}

function setGsmaDetails(data){
	$("#MSISDN").val(data.msisdn);
	$("#IMEI").val(data.imei);
	$("#IMSI").val(data.imsi);
	$("#handsetType").val(data.equipmentType);
	$("#osType").val(data.operatingSystem);
	$("#brand").val(data.bandName);
	$("#modal").val(data.modelName);
	
	$("label[for='MSISDN']").addClass('active');
	$("label[for='IMEI']").addClass('active');
	$("label[for='IMSI']").addClass('active');
	$("label[for='handsetType']").addClass('active');
	$("label[for='osType']").addClass('active');
	$("label[for='brand']").addClass('active');
	$("label[for='modal']").addClass('active');
}


function stateTable(){
		
	var customerCareRequest = {
			"imei" : $("body").attr("data-imei"),
			"msisdn" : $("body").attr("data-msisdn"),
			"deviceIdType" : $("body").attr("data-deviceIdType")	
	}
	 console.log(JSON.stringify(customerCareRequest));
	$('#DeviceStateTable').DataTable({
		destroy:true,
		searching: false,
		scrollCollapse : true,
		dataType : 'json',
		ordering : false,
		bPaginate : true,
		bInfo : true,
		'ajax' : {
			'url' : "./customerRecord?listType=state",
			'type' : 'POST',
			"contentType" : "application/json",
			 data : function(data) {
				 console.log(JSON.stringify(customerCareRequest));
				 return JSON.stringify(customerCareRequest);
			},
			
		},
		
		"columns" : [{
			"data" : "name","defaultContent": ""
		}, {
			"data" : "date","defaultContent": ""
		}, {
			"data" : "status","defaultContent": "",render: function ( data, type, row ) {
								if(data=="N"){
									return '<i class="fa fa-times-circle-o red-text" title="Rejected"></i>'
								}else{
									return '<i class="fa fa-check-circle-o green-text" title="Approved"></i>'
								}
			}
		}, {
			"data" : "featureId","defaultContent": "",render: function ( data, type, row ) {
								if(row['status']=="N"){
									return '<i class="fa fa-eye teal-text disable eventNone" onclick="setStakeHolderData(\''+row['name']+'\',\''+row['date']+'\',\''+row['featureId']+'\',\''+row['status']+'\',\''+row['txnId']+'\',\''+row['imei']+'\')" title="View"></i>'
								}else{
									//alert("sss");
									return '<i class="fa fa-eye teal-text" onclick="setStakeHolderData(\''+row['name']+'\',\''+row['date']+'\',\''+row['featureId']+'\',\''+row['status']+'\',\''+row['txnId']+'\',\''+row['imei']+'\')" title="View"></i>'
				}
				
			}
		}]
		
	});
	
	
}

function deviceTable(){
	var customerCareRequest = {
			"imei" : $("body").attr("data-imei"),
			"msisdn" : $("body").attr("data-msisdn"),
			"deviceIdType" : $("body").attr("data-deviceIdType")	
	}
	
	$('#DeviceTable').DataTable({
		destroy:true,
		searching : false,
		scrollCollapse : true,
		dataType : 'json',
		ordering : false,
		bPaginate : true,
		bInfo : true,
		'ajax' : {
			'url' : "./customerRecord?listType=device",
			'type' : 'POST',
			"contentType" : "application/json",
			 data : function(data) {
				 console.log(JSON.stringify(customerCareRequest));
				 return JSON.stringify(customerCareRequest);
			},
			
		},
		
		"columns" : [{
			"data" : "name","defaultContent": ""
		}, {
			"data" : "date","defaultContent": ""
		}, {
			"data" : "status","defaultContent": "",render: function ( data, type, row ) {
				if(data=="N"){
					return '<i class="fa fa-times-circle-o red-text" title="Rejected"></i>'
				}else{
					return '<i class="fa fa-check-circle-o green-text" title="Approved"></i>'
				}
			}
		}, {
			"data" : "featureId","defaultContent": "",render: function ( data, type, row ) {
				if(row['status']=="N"){
					return '<i class="fa fa-eye teal-text disable eventNone" onclick="setStakeHolderData(\''+row['name']+'\',\''+row['date']+'\',\''+row['featureId']+'\',\''+row['status']+'\',\''+row['txnId']+'\',\''+row['imei']+'\')" title="View"></i>'
				}else{
					
					return '<i class="fa fa-eye teal-text" onclick="setStakeHolderData(\''+row['name']+'\',\''+row['date']+'\',\''+row['featureId']+'\',\''+row['status']+'\',\''+row['txnId']+'\',\''+row['imei']+'\')" title="View"></i>'
				}
			}
		
		}]
		
	});
	
	
}

function notificationTable(){
	
	var filterRequest={
			"imei" : $("body").attr("data-imei"),
			"msisdn" : $("body").attr("data-msisdn"),
			"deviceIdType" : parseInt($("body").attr("data-deviceIdvalue")),
			"userType" : $("body").attr("data-roleType"),
			"userId" : $("body").attr("data-userID"),
			"featureId" : 26,
			"userTypeId":$("body").attr("data-userTypeID"),
		}

if(lang=='km'){
var langFile='./resources/i18n/khmer_datatable.json';
}
$.ajax({
url: './headers?type=ccdashboardNotification',
type: 'POST',
dataType: "json",
success: function(result){
	var table=	$("#Notification-data-table").removeAttr('width').DataTable({
		destroy:true,
		searching : false,
		"serverSide": true,
		orderCellsTop : true,
		"ordering" : false,
		"bPaginate" : true,
		"bFilter" : true,
		"bInfo" : true,
		"bSearchable" : true,
		"oLanguage": {  
			"sUrl": langFile  
		},
		ajax: {
			url : './CCNotificationData',
			type: 'POST',
			dataType: "json",
			data : function(d) {
				d.filter = JSON.stringify(filterRequest); 
				console.log(JSON.stringify(filterRequest));
			}

		},
		"columns": result,
		fixedColumns: true,
		columnDefs: [
            { width: 155, targets: result.length - 1 },
           
	]
	});
	
	$('#Notification-data-table input').unbind();
    $('#Notification-data-table input').bind('keyup', function (e) {
        if (e.keyCode == 13) {
            table.search(this.value).draw();
        }
    });
    $('div#initialloader').delay(300).fadeOut('slow');
},
error: function (jqXHR, textStatus, errorThrown) {
	console.log("error in ajax");
}
});
	
	
	
}




function setStakeHolderData(name,date,featureId,status,txnId,imei)
{
	var formData= new FormData();	
	console.log("name=="+name+"  date=="+date+" featureId= "+featureId+"  status="+status+ "  txnId="+txnId);
	var customerCareRequest={
			 "date": date,
			  "featureId": featureId,
			  "imei": imei,
			  "name": name,
			  "status": status,
			  "txnId": txnId
	}
	
	
	
	//alert(JSON.stringify(customerCareRequest))
	formData.append("customerCareRequest",JSON.stringify(customerCareRequest));
	$.ajax({
		url: './customeCareByTxnId',
		type: 'POST',
		data:formData,
		processData: false,
		"contentType" :false,
		success: function (data, textStatus, jqXHR) {
		
			
			var  assigneIdLabel=$.i18n('assigneIdLabel');
			 var assigneNameLabel=$.i18n('assigneNameLabel');
	    if(data.errorCode==3)
	    	{
	    	console.log("invalid txn id");
	    	$("#InvalidTxnModal").openModal({
		        dismissible:false
		    });
	    	return false;
	    	}
	    
	    
	    if(name=='Importer' && featureId==3)
		{
			
			$("#viewModal").openModal({
		        dismissible:false
		    });
		$("#supplierId").val(data.data.supplierld);
		$("#supplierName").val(data.data.supplierName);
		$("#consignmentNumber").val(data.data.consignmentNumber);
		$("#expectedDispatcheDate").val(data.data.expectedDispatcheDate);
		$("#countryview").val(data.data.organisationCountry);
		$("#expectedArrivaldate").val(data.data.expectedArrivaldate);
		$("#expectedArrivalPort").val(data.data.expectedArrivalPortInterp);
		$("#Quantity").val(data.data.quantity);
		$("#TransactionId").val(data.data.txnId);
		$("#remark").val(data.data.remarks);
		$("#fileName").val(data.data.fileName); 
		$("#viewcurrency").val(data.data.currencyInterp);
		$("#viewtotalPrice").val(data.data.totalPrice);
		
		}
		else if(name=='Importer' && featureId==4)
			{
			$('#viewStockModal').openModal({
		    	   dismissible:false
		       });
		        $("#supplierIdDiv").css("display", "block"); 
				$("#supplierNameDiv").css("display", "block");
				$("#invoiceNumberDiv").css("display", "block");
				$("#SupplierId").val(data.data.supplierId);
				$("#SupplierName").val(data.data.suplierName);
				$("#InvoiceNumber").val(data.data.invoiceNumber);
				$("#StockQuantity").val(data.data.quantity);
				$("#StockTransactionId").val(data.data.txnId);
				$("#csvUploadFileName").val(data.data.fileName);
				$("#withdrawnRemark").val(data.data.remarks);
			}
		
		else if(name=='Custom' && featureId==4)
		{
			//alert("4");
		$('#viewStockModal').openModal({
	    	   dismissible:false
	       });
		$('#SupplierIdLabel').text('');
		$('#SupplierIdLabel').text(assigneIdLabel);
	
		$('#SupplierNameLabel').text('');
		$('#SupplierNameLabel').text(assigneNameLabel);
		
		$("#editSupplierIdDiv").css("display", "block"); 
		$("#editSupplierNameDiv").css("display", "block");
		$("#editSupplierNameDiv").css("display", "block");
			$("#SupplierId").val(data.data.supplierId);
			$("#SupplierName").val(data.data.suplierName);
			$("#InvoiceNumber").val(data.data.invoiceNumber);
			$("#StockQuantity").val(data.data.quantity);
			$("#StockTransactionId").val(data.data.txnId);
			$("#csvUploadFileName").val(data.data.fileName);
			$("#withdrawnRemark").val(data.data.remarks);
		}
		
		else if(name=='Custom' && featureId==3)
		{
		//	alert("3");
			$("#viewModal").openModal({
		        dismissible:false
		    });
		$("#supplierId").val(data.data.supplierld);
		$("#supplierName").val(data.data.supplierName);
		$("#consignmentNumber").val(data.data.consignmentNumber);
		$("#expectedDispatcheDate").val(data.data.expectedDispatcheDate);
		$("#countryview").val(data.data.organisationCountry);
		$("#expectedArrivaldate").val(data.data.expectedArrivaldate);
		$("#expectedArrivalPort").val(data.data.expectedArrivalPortInterp);
		$("#Quantity").val(data.data.quantity);
		$("#TransactionId").val(data.data.txnId);
		$("#remark").val(data.data.remarks);
		$("#fileName").val(data.data.fileName); 
		$("#viewcurrency").val(data.data.currencyInterp);
		$("#viewtotalPrice").val(data.data.totalPrice);
		}
		else if(name=='Distributor' || name=='Retailer') {
			
			$('#viewStockModal').openModal({
		    	   dismissible:false
		       });
		        $("#supplierIdDiv").css("display", "block"); 
				$("#supplierNameDiv").css("display", "block");
				$("#invoiceNumberDiv").css("display", "block");
				$("#SupplierId").val(data.data.supplierId);
				$("#SupplierName").val(data.data.suplierName);
				$("#InvoiceNumber").val(data.data.invoiceNumber);
				$("#StockQuantity").val(data.data.quantity);
				$("#StockTransactionId").val(data.data.txnId);
				$("#csvUploadFileName").val(data.data.fileName);
				$("#withdrawnRemark").val(data.data.remarks);
			}
		
         else if(name=='Manufacturer') {
			
			$('#viewStockModal').openModal({
		    	   dismissible:false
		       });
			$("#supplierIdDiv").css("display", "none"); 
			$("#supplierNameDiv").css("display", "none");
			$("#invoiceNumberDiv").css("display", "none");
			
				$("#SupplierId").val(data.data.supplierId);
				$("#SupplierName").val(data.data.suplierName);
				$("#InvoiceNumber").val(data.data.invoiceNumber);
				$("#StockQuantity").val(data.data.quantity);
				$("#StockTransactionId").val(data.data.txnId);
				$("#csvUploadFileName").val(data.data.fileName);
				$("#withdrawnRemark").val(data.data.remarks);
			}
         else if(name=='Type Approve')
        	 {
        	 $('#viewImporterModal').openModal({
		    	   dismissible:false
		       });
        	
        	 $("#viewtradmark").val(data.data.trademark);
     		$("#viewmodelName").val(data.data.productNameInterp);
     		$("#viewModelnumber").val(data.data.modelNumberInterp);
     		$("#viewManufacturercountry").val(data.data.manufacturerCountry);
     		$('#viewrequestDate').val(data.data.requestDate);
     		$('#viewFrequency').val(data.data.frequencyRange);
     		$("#viewImportertac").val(data.data.tac);
     		$("#viewtxnId").val(data.data.txnId);
        	 }
         else if(name=='End User')
    	 {
    	 $('#viewDeviceInfo').openModal({
	    	   dismissible:false
	       });
    	$("#endUserdeviceType").val(data.data.deviceTypeInterp);
 		$("#endUserdeviceIdType").val(data.data.deviceIdTypeInterp);
 		$("#endUserMultiSimStatus").val(data.data.multiSimStatusInterp);
 		$("#endUserCountry").val(data.data.country);
 		$('#endUserSerialNumer').val(data.data.deviceSerialNumber);
 		$('#endUserTaxPaid').val(data.data.taxPaidStatusInterp);
 		$("#endUserDeviceStatus").val(data.data.deviceStatusInterp);
 		$("#endUserPrice").val(data.data.price);
 		
 		$('#endUserCurrency').val(data.data.currencyInterp);
 		$('#endUserImei1').val(data.data.firstImei);
 		$("#endUserImei2").val(data.data.secondImei);
 		$("#endUserImei3").val(data.data.thirdImei);
 		$("#endUserImei4").val(data.data.fourthImei);
 		$("#enduserTransactionId").val(data.data.txnId);
 		$("#enduserNid").val(data.data.nid);
 		
    	 }

         else if(name=='Duplicate')
    	 {
    	 $('#greyListTableModal').openModal({
	    	   dismissible:false
	       });

    	 duplicateData(data.data);
 		
    	 }
         else if(name=='VIP')
    	 {
    	 $('#greyListTableModal').openModal({
	    	   dismissible:false
	       });

    	 greyListDataTable(data.data);
 		
    	 }
         else if(name=="Blacklist")
		{
        	 $('#blockDeviceHeader').text('');
        	 $('#blockDeviceHeader').text('Block Device Details');
        	 $('#blockListModal').openModal({
  	    	   dismissible:false
  	       });	 
        	 $("#blockedUserType").val(data.data.userType);
       		$("#blockedUserName").val(data.data.userId);
       		$("#blockedDate").val(data.data.createdOn);
       		$("#blockExpiryDate").val(data.data.expiryDate);
       		$("#blockeModeType").val(data.data.modeType);
       		$("#complaintType").val(data.data.complainType);
       		
       		
		}
         else if(name=="Greylist"){
        	 $('#blockDeviceHeader').text('');
        	 $('#blockDeviceHeader').text('Grey Device Details');
        	 $('#blockListModal').openModal({
  	    	   dismissible:false
  	       });
        	 
        	$("#blockedUserType").val(data.data.userType);
      		$("#blockedUserName").val(data.data.userId);
      		$("#blockedDate").val(data.data.createdOn);
      		$("#blockExpiryDate").val(data.data.expiryDate);
       		$("#blockeModeType").val(data.data.modeType);
       		$("#complaintType").val(data.data.complainType);
         }
	    
	    
         else if(featureId=="36")
        	 {
        	 $('#globalBlackListTableModal').openModal({
  	    	   dismissible:false
  	       });

        	 globalBlackListDataTable(data.data);
        	 }
		}
	
	
		
	});
}


function greyListDataTable(data){
var tableData=[];

tableData.push(data);

	$('#greyListDataTable').DataTable({
		destroy:true,
		searching: false,
		scrollCollapse : true,
		dataType : 'json',
		ordering : false,
		bPaginate : false,
		"data":tableData,
		bInfo : true,
		"columns" : [{
			"data" : "createdOn","defaultContent": ""
		}, {
			"data" : "createdOn", "defaultContent": ""
		},
		{
			"data" : "imeiMsisdnIdentity.msisdn","defaultContent": ""
		},
		{
			"data" : "imeiMsisdnIdentity.imei","defaultContent": ""
		}, {
			"data" : "imeiEsnMeid","defaultContent": ""
		}]
		
	});
}
function duplicateData(data){
	$('#greyListDataTable').DataTable({
			destroy:true,
			searching: false,
			scrollCollapse : true,
			dataType : 'json',
			ordering : false,
			bPaginate : false,
			"data":data,
			bInfo : true,
			"columns" : [{
				"data" : "createdOn","defaultContent": ""
			}, {
				"data" : "createdOn", "defaultContent": ""
			},
			{
				"data" : "imeiMsisdnIdentity.msisdn","defaultContent": ""
			},
			{
				"data" : "imeiMsisdnIdentity.imei","defaultContent": ""
			}, {
				"data" : "imsi","defaultContent": ""
			}]
			
		});
	}
function closeViewModal()
{
$('#viewModal').closeModal();
$('#viewStockModal').closeModal();

	$(".lean-overlay").remove();

}


function globalBlackListDataTable(data){
	var tableData=[];

	tableData.push(data);

		$('#globalBlackListDataTable').DataTable({
			destroy:true,
			searching: false,
			scrollCollapse : true,
			dataType : 'json',
			ordering : false,
			bPaginate : false,
			"data":tableData,
			bInfo : true,
			"columns" : [{
				"data" : "createdOn","defaultContent": ""
			}, {
				"data" : "blackliststatus", "defaultContent": ""
			},
			{
				"data" : "greyliststatus","defaultContent": ""
			}]
			
		});
	}
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
								if(data==0){
									return '<i class="fa fa-eye teal-text disable eventNone" title="View"></i>'
								}else{
									return '<i class="fa fa-eye teal-text" title="View"></i>'
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
				if(data==0){
					return '<i class="fa fa-eye teal-text disable eventNone" title="View"></i>'
				}else{
					return '<i class="fa fa-eye teal-text" title="View"></i>'
				}

			}
		
		}]
		
	});
	
	
}

function notificationTable(){
	var filterRequest={
			"imei" : $("body").attr("data-imei"),
			"msisdn" : $("body").attr("data-msisdn"),
			"deviceIdType" : $("body").attr("data-deviceIdType")
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





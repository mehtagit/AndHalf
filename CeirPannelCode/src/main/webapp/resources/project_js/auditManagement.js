var featureId = 17;
var roleType = $("body").attr("data-roleType");
var userId = $("body").attr("data-userID");
var currentRoleType = $("body").attr("data-selected-roleType"); 
var startdate=$('#startDate').val(); 
var endDate=$('#endDate').val();

$(document).ready(function(){
	pageRendering();
	setTimeout(function(){ auditManagementDatatable(); }, 200);
	
	
});

var role = currentRoleType == null ? roleType : currentRoleType;


//**************************************************Audit Management table**********************************************

function auditManagementDatatable(){
	
	var roleDropDown =  document.getElementById("roleType");
	var userTypeInterp = roleDropDown.options[roleDropDown.selectedIndex].text;
	var userType = $('#roleType').val()=='' ? null : userTypeInterp;
	
	var feature = document.getElementById("feature");
	var featureName = feature.options[feature.selectedIndex].text;
	
	var filterRequest={
			
			//"userId":parseInt(userId),
			//"featureId":parseInt(featureId),
			//"userTypeId": parseInt($("body").attr("data-userTypeID")),
			"userType": userType,
			"featureId": parseInt(featureId),
			"startDate" : $("#startDate").val(),
			"endDate" : $("#endDate").val(),
			"txnId" : $("#transactionID").val(),
			"featureName" : featureName,
			"subFeatureName" : $("#subFeature").val(),
			"userName" : $("#userName").val()
			
			
	}
	
	console.log("filterRequest-->" +JSON.stringify(filterRequest));
	$.ajax({
		url: 'headers?type=auditManagement',
		type: 'POST',
		dataType: "json",
		success: function(result){
			var table=	$("#auditLibraryTable").removeAttr('width').DataTable({
				destroy:true,
				"serverSide": true,
				orderCellsTop : true,
				"ordering" : false,
				"bPaginate" : true,
				"bFilter" : true,
				"bInfo" : true,
				"bSearchable" : true,
				ajax: {
					url : 'auditManagementData',
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
		            { width: 50, targets: result.length - 1 },
		            { width: 125, targets: 0 },
		            { width: 150, targets: 1 }
		        ]
			});
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax");
		}
	});
}



//**************************************************viewAudit page buttons**********************************************

function pageRendering(){
	$.ajax({
		url: 'audit/pageRendering',
		type: 'POST',
		dataType: "json",
		success: function(data){
			data.userStatus == "Disable" ? $('#btnLink').addClass( "eventNone" ) : $('#btnLink').removeClass( "eventNone" );
			
			var elem='<p class="PageHeading">'+data.pageTitle+'</p>';		
			$("#pageHeader").append(elem);
			var button=data.buttonList;
			var date=data.inputTypeDateList;
			for(i=0; i<date.length; i++){
				if(date[i].type === "date"){
					$("#auditTableDiv").append("<div class='input-field col s6 m2'>"+
							"<div id='enddatepicker' class='input-group date'>"+
							"<input class='form-control datepicker' onchange='checkDate(startDate,endDate)' type='text' id="+date[i].id+" autocomplete='off'>"+
							"<label for="+date[i].id+">"+date[i].title
							+"</label>"+
							"<span	class='input-group-addon' style='color: #ff4081'>"+
							"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");
					$( "#"+date[i].id ).datepicker({
						dateFormat: "yy-mm-dd",
						 maxDate: new Date()
			        }); 
				}else if(date[i].type === "text"){
					$("#auditTableDiv").append("<div class='input-field col s6 m2' ><input type="+date[i].type+" id="+date[i].id+" maxlength='19' /><label for="+date[i].id+" class='center-align'>"+date[i].title+"</label></div>");
				}
				
			} 
		
		// dynamic dropdown portion
			var dropdown=data.dropdownList;
			for(i=0; i<dropdown.length; i++){
				var dropdownDiv=
					$("#auditTableDiv").append("<div class='col s6 m2 selectDropdwn'>"+
						
							"<div class='select-wrapper select2  initialized'>"+
							"<span class='caret'>"+"</span>"+
							"<input type='text' class='select-dropdown' readonly='true' data-activates='select-options-1023d34c-eac1-aa22-06a1-e420fcc55868' value='Consignment Status'>"+

							"<select id="+dropdown[i].id+" class='select2 initialized'>"+
							"<option value='' disabled selected>"+dropdown[i].title+
							"</option>"+
							"</select>"+
							"</div>"+
					"</div>");
			}

				$("#auditTableDiv").append("<div class=' col s3 m2 l1'><button type='button' class='btn primary botton' id='submitFilter'/></div>");
				$("#auditTableDiv").append("<div class=' col s3 m2 l1'><a href='JavaScript:void(0)' type='button' class='export-to-excel right'  onclick='exportAuditData()'>Export<i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");
				for(i=0; i<button.length; i++){
					$('#'+button[i].id).text(button[i].buttonTitle);
					$('#'+button[i].id).attr("onclick", button[i].buttonURL);
				}

			
		


		}
	});
	
	setAllDropdown()
	
}


function setAllDropdown(){
	$.getJSON('./registrationUserType', function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].id).text(data[i].usertypeName)
			.appendTo('#roleType');
		}
	});
	
	
	$.getJSON('./getAllfeatures', function(data) {
		for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].id).text(data[i].name).appendTo('#feature');
		}
	});
}






$('.datepicker').on('mousedown',function(event){
	event.preventDefault();
});


function viewDetails(Id){
	$("#viewAuditModel").openModal({
        dismissible:false
    });
	 var Id = parseInt(Id);
	$.ajax({
		url : './audit/view/'+Id,
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'GET',
		success : function(data) {
			setViewPopupData(data);
		},
		error : function() {
			alert("Failed");
		}
	});
}


function setViewPopupData(data){
	$("#viewUserId").val(data.userId);
	$("#viewTxnId").val(data.txnId);
	$("#viewUserName").val(data.userName);
	$("#viewRoleType").val(data.userType);
	$("#viewFeature").val(data.featureName);
	$("#viewSubFeature").val(data.subFeature);
	
}


function exportAuditData(){
	var table = $('#auditLibraryTable').DataTable();
	var info = table.page.info(); 
	var pageNo=info.page;
	var pageSize =info.length;
	window.location.href="./exportAuditData?pageSize="+pageSize+"&pageNo="+pageNo;
	
}
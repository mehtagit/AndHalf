var featureId = 11;
var userId = $("body").attr("data-userID");
var cierRoletype = sessionStorage.getItem("cierRoletype");

$(document).ready(function(){
	$('div#initialloader').fadeIn('fast');
	typeApprovedDataTable()
	pageRendering();
});


//**************************************************Type Approved table**********************************************

function typeApprovedDataTable(){
	var filterRequest={
	"endDate":$('#endDate').val(),
	"startDate":$('#startDate').val(),
  	"tac" : $('#tac').val(),
  	"userId":parseInt($("body").attr("data-userID")),
	"featureId":parseInt(featureId),
	"userTypeId": parseInt($("body").attr("data-userTypeID")),
	"userType":$("body").attr("data-roleType"),
	"status" : parseInt($('#Status').val()),
	}
	$.ajax({
		url: 'headers?type=trcManageType',
		type: 'POST',
		dataType: "json",
		success: function(result){
			var table=	$("#typeAprroveTable").DataTable({
				destroy:true,
				"serverSide": true,
				orderCellsTop : true,
				"ordering" : false,
				"bPaginate" : true,
				"bFilter" : true,
				"bInfo" : true,
				"bSearchable" : true,
				ajax: {
					url : './trc',
					type: 'POST',
					dataType: "json",
					data : function(d) {
						d.filter = JSON.stringify(filterRequest); 
						console.log(JSON.stringify(filterRequest));
					}

				},
				"columns": result
			});
			
			$('#typeAprroveTable input').unbind();
		    $('#typeAprroveTable input').bind('keyup', function (e) {
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


//**************************************************Type Approved page buttons**********************************************

function pageRendering(){
	$.ajax({
		url: 'TRC/pageRendering',
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
					$("#typeAprroveTableDiv").append("<div class='col s6 m2 l2 responsiveDiv'>"+
							"<div id='enddatepicker' class='input-group date'>"+
							"<label for='TotalPrice'>"+date[i].title
							+"</label>"+"<input class='form-control datepicker' type='text' id="+date[i].id+" autocomplete='off'>"+
							"<span	class='input-group-addon' style='color: #ff4081'>"+
							"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");
				}else if(date[i].type === "text"){
					$("#typeAprroveTableDiv").append("<div class='input-field col s6 m2 filterfield' style='margin-top: 22px;'><input type="+date[i].type+" id="+date[i].id+" maxlength='19' /><label for='tac' class='center-align'>"+date[i].title+"</label></div>");
				}
			} 

			// dynamic drop down portion
			var dropdown=data.dropdownList;
			for(i=0; i<dropdown.length; i++){
				var dropdownDiv=
					$("#typeAprroveTableDiv").append("<div class='col s6 m2 l2 selectDropdwn'>"+
							"<br>"+
							"<div class='select-wrapper select2 form-control boxBorder boxHeight initialized'>"+
							"<span class='caret'>"+"</span>"+
							"<input type='text' class='select-dropdown' readonly='true' data-activates='select-options-1023d34c-eac1-aa22-06a1-e420fcc55868' value='Consignment Status'>"+

							"<select id="+dropdown[i].id+" class='select2 form-control boxBorder boxHeight initialized'>"+
							"<option>"+dropdown[i].title+
							"</option>"+
							"</select>"+
							"</div>"+
					"</div>");
			}

			$("#typeAprroveTableDiv").append("<div class='col s12 m2 l2'><input type='button' class='btn primary botton' id='submitFilter' value='filter'></div>");
			$("#typeAprroveTableDiv").append("<div class='col s12 m2'><a href='JavaScript:void(0)' onclick='exportTacData()' type='button' class='export-to-excel right'>Export <i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");
			for(i=0; i<button.length; i++){
				$('#'+button[i].id).text(button[i].buttonTitle);
				if(button[i].type === "HeaderButton"){
					$('#'+button[i].id).attr("href", button[i].buttonURL);
				}
				else{
					$('#'+button[i].id).attr("onclick", button[i].buttonURL);
				}
			}
	
			$('.datepicker').datepicker({
			    dateFormat: "yy-mm-dd"
			    });
			

			$.getJSON('./getDropdownList/'+featureId+'/'+$("body").attr("data-userTypeID"), function(data) {

				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].state).text(data[i].interp)
					.appendTo('#Status');
				}
			});
		}
		
	}); 
};


function viewByID(id,actionType){
	
	
	
	$.ajax({
		url : "./viewByID/"+id, //controller haven'nt made yet for this url. this is dummy url.
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success : function(data) {
			console.log(+data);
			if(actionType=='view')
				{
				$("#viewModal").openModal();
				console.log("222222222");
				setViewPopupData(data);
				}
			else if(actionType=='edit')
				{
				console.log("3333333333");
				$("#editModal").openModal();
				setEditPopupData(data)
				}
			
		},
		error : function() {
			console.log("failed");
		}
	});
	
}

function setViewPopupData(data){
	$("#viewmanufacturerId").val(data.manufacturerId);
	$("#viewmanufacturerName").val(data.manufacturerName);
	$("#viewcountry").val(data.country);
	$("#viewtac").val(data.tac);
	$("#status").val(data.stateInterp);
	$('#viewrequestDate').val(data.requestDate)
	$("#viewapproveDisapproveDate").val(data.approveDisapproveDate);
	$("#viewremark").val(data.remark);
}
function setEditPopupData(data){
	$("#editmanufacturerId").val(data.manufacturerId);
	$("#editmanufacturerName").val(data.manufacturerName);
	$("#editcountry").val(data.country);
	$("#edittac").val(data.tac);
	$("#editdeviceType").val(data.approveStatus).change();
	$('#editRequestDate').val(data.requestDate)
	$("#editApproveRejectionDate").val(data.approveDisapproveDate);
	$("#editRemark").val(data.remark);
	$("#editFileName").val(data.file);
	$("#transactionid").val(data.txnId);
	$("#columnid").val(data.id);
	
	
}

populateCountries
(   
		"editcountry"
);


function updateReportTypeDevice()
{
	var manufacturerId=$("#editmanufacturerId").val();
	var manufacturerName=$("#editmanufacturerName").val();
	 var country=$("#editcountry").val();
	 var tac=$("#edittac").val();
	 var approveStatus=$("#editdeviceType").val();
    var requestDate=$('#editRequestDate').val()
	 var approveDisapproveDate=$("#editApproveRejectionDate").val();
	 var remark =$("#editRemark").val();
	 var file=$("#editFileName").val();
	 var txnid=$("#transactionid").val();
	 var id=$("#columnid").val();
	 console.log("approveStatus=="+approveStatus);
	 
	 var formData = new FormData();
		formData.append('file', $('#editUploadFile')[0].files[0]);
		formData.append('manufacturerId', manufacturerId);
		formData.append('manufacturerName', manufacturerName);
		formData.append('country', country);
		formData.append('tac', tac);
		formData.append('status', approveStatus);
		formData.append('approveDisapproveDate', approveDisapproveDate);
		formData.append('remark', remark);
		formData.append('txnId', txnid);
		formData.append('id', id);
		formData.append('fileName', file);
		formData.append('requestDate', requestDate);
		
		$.ajax({
			url : './update-register-approved-device',
			type : 'POST',
			data : formData,
			processData : false,
			contentType : false,
			success : function(data, textStatus, jqXHR) {
			
				console.log(data);
				$('#updateManageTypeDevice').openModal();
				/*if(data.errorCode=="200")
				 {
				 console.log("status code = 0");
				$('#updateTacMessage').text('');
				$('#updateTacMessage').append(data.message);
				$('#errorCode').val(data.errorCode);
				 }
				else if(data.errorCode=="204")
				 {
				console.log("status code = 3"); 
				$('#updateTacMessage').text('');
				$('#updateTacMessage').text(data.message);
				$('#errorCode').val(data.errorCode);
				 }
				// $('#updateConsignment').modal('open'); 
				//alert("success");
*/				 
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("error in ajax")
			}
		});
}










//**********************************************************Export Excel file************************************************************************
function exportTacData()
{
	var tacStartDate=$('#startDate').val();
	var tacEndDate=$('#endDate').val();
	var tacStatus=parseInt($('#Status').val());
	var tacNumber=$('#tac').val();
	console.log("tacStatus=="+tacStatus);
     if(isNaN(tacStatus))
	   {
    	 tacStatus='';
  	   console.log(" tacStatus=="+tacStatus);
	   }
 
	var table = $('#typeAprroveTable').DataTable();
	var info = table.page.info(); 
 var pageNo=info.page;
  var pageSize =info.length;
	console.log("--------"+pageSize+"---------"+pageNo+" tacStartDate="+tacStartDate+" tacEndDate="+tacEndDate+" tacStatus= "+tacStatus);
	
	window.location.href="./exportTac?tacNumber="+tacNumber+"&tacStartDate="+tacStartDate+"&tacEndDate="+tacEndDate+"&tacStatus="+tacStatus+"&pageSize="+pageSize+"&pageNo="+pageNo;
}
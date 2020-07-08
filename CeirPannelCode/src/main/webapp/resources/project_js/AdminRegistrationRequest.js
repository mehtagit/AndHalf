	var cierRoletype = sessionStorage.getItem("cierRoletype");
	var featureId = 8;
	var roleType = $("body").attr("data-roleType");
	var userId = $("body").attr("data-userID");
	var currentRoleType = $("body").attr("data-selected-roleType"); 
	var startdate=$('#startDate').val(); 
	var endDate=$('#endDate').val();

	var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';


	$.i18n().locale = lang;	

	$.i18n().load( {
		'en': './resources/i18n/en.json',
		'km': './resources/i18n/km.json'
	} ).done( function() {
		
	});

	$(document).ready(function(){
		registrationDatatable(lang,null);
		pageRendering();
		
	});

	var role = currentRoleType == null ? roleType : currentRoleType;


	//**************************************************Registration table**********************************************

	function registrationDatatable(lang,source){
		var source__val;

		if(source == 'filter' ) {
			source__val= source;
		}
		else{
			source__val= $("body").attr("data-session-source");

		}
		
		var asType = $('#asType').val();
		var userRoleTypeId = $("#role").val();
		var status =  $('#recentStatus').val();
		var txn= (txnIdValue == 'null' && transactionIDValue == undefined)? $('#transactionID').val() : transactionIDValue;
		var filterRequest={
				"endDate":$('#endDate').val(),
				"startDate":$('#startDate').val(),
				"asType": parseInt(asType),
				"userRoleTypeId" : parseInt(userRoleTypeId),
				"status" : parseInt(status),
				"userId":parseInt(userId),
				"txnId":txn,
				"featureId":parseInt(featureId),
				"userTypeId": parseInt($("body").attr("data-userTypeID")),
				"userType":$("body").attr("data-roleType"),
				"email" : $('#emailID').val(),
				"phoneNo" : $('#phone').val(),
				"username" : $('#userName').val(),
		}
		
		if(lang=='km'){
			var langFile="./resources/i18n/khmer_datatable.json";
				}
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$.ajaxSetup({
			headers:
			{ 'X-CSRF-TOKEN': token }
		});
		
		console.log("source__val in filter request------>" +source__val);
		$.ajax({
			url: 'headers?type=adminRegistration&lang='+lang,
			type: 'POST',
			dataType: "json",
			success: function(result){
				/*console.log("Url-------" +url+"--------"+ "dataUrl-------" +dataUrl);*/
				var table=	$("#registrationLibraryTable").DataTable({
					destroy:true,
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
						url : 'registrationData?source='+source__val,
						type: 'POST',
						dataType: "json",
						data : function(d) {
							d.filter = JSON.stringify(filterRequest); 
							//console.log(JSON.stringify(filterRequest));
						}

					},
					"columns": result
				});
				$('div#initialloader').delay(300).fadeOut('slow');
				$('.dataTables_filter input')
			       .off().on('keyup', function(event) {
			    	   if(event.keyCode == 8 && !textBox.val() || event.keyCode == 46 && !textBox.val() || event.keyCode == 83 && !textBox.val()) {
				    
				            }
			    		if (event.keyCode === 13) {
			    			 table.search(this.value.trim(), false, false).draw();
			    		}
			          
			       });
			},
			error: function (jqXHR, textStatus, errorThrown) {
				//console.log("error in ajax");
			}
		});
	}



	//**************************************************Registration page buttons**********************************************

	function pageRendering(){
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$.ajaxSetup({
			headers:
			{ 'X-CSRF-TOKEN': token }
		});
		$.ajax({
			url: 'registration/pageRendering',
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
					$("#registrationTableDiv").append("<div class='input-field col s6 m2'>"+
							"<div id='enddatepicker' class='input-group'>"+
							"<input class='form-control datepicker' type='text' id="+date[i].id+" autocomplete='off' onchange='checkDate(startDate,endDate)'>"+
							"<label for="+date[i].id+">"+date[i].title
							+"</label>"+
							"<span	class='input-group-addon' style='color: #ff4081'>"+
							"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");
					$( "#"+date[i].id ).datepicker({
						dateFormat: "yy-mm-dd",
						 maxDate: new Date()
			        }); 
					}
					else if(date[i].type === "text"){
						$("#registrationTableDiv").append("<div class='input-field col s6 m2' ><input type="+date[i].type+" id="+date[i].id+" maxlength='60' /><label for="+date[i].id+" class='center-align'>"+date[i].title+"</label></div>");
						
					}
					
					
				} 
				
				// dynamic dropdown portion
				var dropdown=data.dropdownList;
				for(i=0; i<dropdown.length; i++){
					var dropdownDiv=
						$("#registrationTableDiv").append("<div class='col s6 m2 selectDropdwn'>"+
								
								"<div class='select-wrapper select2  initialized'>"+
								"<span class='caret'>"+"</span>"+
								"<input type='text' class='select-dropdown' readonly='true' data-activates='select-options-1023d34c-eac1-aa22-06a1-e420fcc55868' value='Consignment Status'>"+

								"<select id="+dropdown[i].id+" class='select2 initialized'>"+
								"<option value='-1'>"+dropdown[i].title+
								"</option>"+
								"</select>"+
								"</div>"+
						"</div>");
				}
				
				$("#registrationTableDiv").append("<div class=' col s3 m2 l1'><button type='button' class='btn primary botton' id='submitFilter'/></div>");
				$("#registrationTableDiv").append("<div class=' col s3 m2 l1'><a onclick='exportButton()' type='button' class='export-to-excel right'>"+$.i18n('Export')+"<i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");
				for(i=0; i<button.length; i++){
					$('#'+button[i].id).text(button[i].buttonTitle);
					$('#'+button[i].id).attr("onclick", button[i].buttonURL);
				
				}
				
			
			
				cierRoletype=="CEIRAdmin"? $("#btnLink").css({display: "none"}) : $("#btnLink").css({display: "block"});
				/*sourceType=="viaStolen" ? $("#btnLink").css({display: "none"}) : $("#btnLink").css({display: "none"});*/
			
				
			}

		}); 
	
		setAllDropdown();
	};

	
	function setAllDropdown(){
		
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$.ajaxSetup({
			headers:
			{ 'X-CSRF-TOKEN': token }
		});
		$.getJSON('./getDropdownList/'+featureId+'/'+$("body").attr("data-userTypeID"), function(data) {
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].state).text(data[i].interp)
				.appendTo('#recentStatus,#userStatus');
			}
		});
		

		$.getJSON('./registrationUserType', function(data) {
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].id).text(data[i].usertypeName)
				.appendTo('#role');
			}
		});
		
		
		$.getJSON('./getSourceTypeDropdown/AS_TYPE/'+featureId+'', function(data) {
			for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#asType');
			}
		});
		
	}


	function myFunction(message) {
		var x = document.getElementById("snackbar");
		x.className = "show";
		$('#errorMessage').html(message);
		setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
	}

	function dispatchDateValidation(){
		var currentDate;
		var dispatcDate=  $('#expectedDispatcheDate').val();
		var now=new Date();
		if(now.getDate().toString().charAt(0) != '0'){
			currentDate='0'+now.getDate();

			/* alert("only date="+currentDate); */
		}
		else{
			currentDate=now.getDate();
		}
		var today = now.getFullYear()+ '-' + (now.getMonth()+1)+ '-' +currentDate ;
		//alert("today"+today);
		//console.log("dispatche="+dispatcDate);
		//console.log("todays parse date"+Date.parse(today));
		//console.log("dispatche parse date"+Date.parse(dispatcDate));


		if(Date.parse(today)>Date.parse(dispatcDate))
		{
			myFunction("dispatche date should be greater then or equals to today");
			$('#expectedDispatcheDate').val("");
		}

		//alert("current date="+today+" dispatche date="+dispatcDate)
	}

	function arrivalDateValidation(){
		var currentDate;
		var dispatcDate=  $('#expectedArrivalDate').val();
		var now=new Date();
		if(now.getDate().toString().charAt(0) != '0'){
			currentDate='0'+now.getDate();

			/* alert("only date="+currentDate); */
		}
		else{
			currentDate=now.getDate();
		}
		var today = now.getFullYear()+ '-' + (now.getMonth()+1)+ '-' +currentDate ;
		//alert("today"+today);
		//console.log("dispatche="+dispatcDate);
		//console.log("todays parse date"+Date.parse(today));
		//console.log("dispatche parse date"+Date.parse(dispatcDate));


		if(Date.parse(today)>Date.parse(dispatcDate))
		{
			myFunction("Arrival date should be greater then or equals to today");
			$('#expectedArrivalDate').val("");
		}
		//alert("current date="+today+" dispatche date="+dispatcDate)
	}

	$('.datepicker').on('mousedown',function(event){
		event.preventDefault();
	});



	function userApprovalPopup(Id,date,username,sessionUserName){
		$("#registrationTxnId").text(username);
		$("#sessionUserName").val(sessionUserName);
		$('#approveInformation').openModal({
		 	   dismissible:false
	    });
		$("#userId").text(Id);
		window.ID=Id;
		window.userName = username
		window.date=date.replace("="," ");
	}




	function aprroveUser(){
		var id= $("#userId").text();
		var approveRequest={
				"id": parseInt(id),
				"status" : "Approved",
				"remark": $("#Reason").val(),	
				"featureId" : parseInt(featureId),
				"statusValue" : 3,
				"username" : $("#sessionUserName").val(),
				"userId" : parseInt(userId)
		}
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$.ajaxSetup({
			headers:
			{ 'X-CSRF-TOKEN': token }
		});
		$.ajax({
			url : './adminApproval',
			data : JSON.stringify(approveRequest),
			dataType : 'json',
			'async' : false,
			contentType : 'application/json; charset=utf-8',
			type : 'POST',
			success : function(data) {
				//console.log("approveRequest----->"+JSON.stringify(approveRequest));
				confirmApproveInformation(window.ID,window.date);
			},
			error : function() {
				alert("Failed");
			}
		});
	}

	function confirmApproveInformation(ID,date){
		$('#approveInformation').closeModal(); 
		setTimeout(function(){ $('#confirmApproveInformation').openModal({
		 	   dismissible:false
	    });}, 200);
		$("#registrationDate").text(date);
		$("#RegistrationId").text(window.userName);
	}

	function userRejectPopup(Id,sessionUserName){
		$('#rejectInformation').openModal({
		 	   dismissible:false
	    });
		//console.log("Reject userId is---->"+Id);
		$("#userId").text(Id)
		$("#rejectUserName").val(sessionUserName);
		
		
	}


	function rejectUser(userId){
		var id= $("#userId").text();
		var rejectRequest={
				"id": parseInt(id),
				"status" : "Rejected",
				"remark": $("#Reason").val(),
				"featureId" : parseInt(featureId),
				"statusValue" : 4,
				"username" : $("#rejectUserName").val(),
				"userId" : parseInt($("body").attr("data-userID"))
				
		}
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$.ajaxSetup({
			headers:
			{ 'X-CSRF-TOKEN': token }
		});
		$.ajax({
			url : './adminApproval',
			data : JSON.stringify(rejectRequest),
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			type : 'POST',
			success : function(data) {
			   //console.log("rejectRequest----->"+JSON.stringify(rejectRequest));
				confirmRejectInformation();
			},
			error : function() {
				alert("Failed");
			}
		});
		
		return false;
		
	}

	function confirmRejectInformation(){
		$('#rejectInformation').closeModal();
		$('#confirmRejectInformation').openModal({
		 	   dismissible:false
	    });
	}

	function exportButton(){
		var startdate=$('#startDate').val(); 
		var endDate=$('#endDate').val();
		var emailId = $('#emailID').val();
		var phone = $('#phone').val();
		var username = $('#userName').val();
		var asType =  $('#asType').val();
		var userRoleTypeId =  $("#role").val();
		var status =  $('#recentStatus').val();
		
		var featureId = 8;
		var usertypeId= parseInt($("body").attr("data-usertypeid"));
		var table = $('#registrationLibraryTable').DataTable();
		var info = table.page.info(); 
		var pageNo=info.page;
		var pageSize =info.length;
		
		
		
		var source;
		if(startdate != "" || endDate != "" || emailId !="" || phone != '' || username != "" || asType != "-1" ||  userRoleTypeId != "-1" || status != "-1"  ){
			source = "filter"
		}else{
			source = source__val= $("body").attr("data-session-source");
		}
		
		console.log ("source--->" +source);
		
		window.location.href="./exportAdminRegistration?RegistrationStartDate="+startdate+"&RegistrationEndDate="+endDate+"&email="+emailId+"&phoneNo="+phone+"&username="+username+"&asType="+asType+"&userRoleTypeId="+userRoleTypeId+"&featureId="+featureId+"&status="+status+"&source="+source+"&pageSize="+pageSize+"&pageNo="+pageNo+"&userTypeId="+usertypeId+"";
	}


	function previewFile(srcFilePath,srcFileName){

		window.filePath = srcFilePath;
		window.fileName = srcFileName;
		window.fileExtension = fileName.replace(/^.*\./, '');
		window.FinalLink = filePath.concat(fileName);

		if(filePath == null || filePath == "" || filePath == undefined && fileName == null || fileName == "" || fileName == undefined ){
			//console.log("File is not Avialable")
		}else if(fileExtension=="jpg" || fileExtension=="jpeg" || fileExtension=="png" || fileExtension=="gif" ){
			$("#fileSource").attr("src",FinalLink);
			$("#viewuplodedModel").openModal();
		}else{
			window.open(FinalLink);
		}
	}
	

function roleStatusChange(Id,sessionUserName, userTypeId, tableId){
		
	    window.Id = Id,
	    window.sessionUserName = sessionUserName,
	    window.userTypeId = userTypeId, 
	    window.tableId = tableId,
	    
	   
	   //usertypeData2(userTypeId);
	    
	    $("#statusRoleChange").openModal({
	    	dismissible:false
	    });

	    if(userTypeId == "4" || userTypeId == "5" || userTypeId == "6"){
	    	$('input[name=group2]').attr("disabled", false);
	    }else{
	    	$('input[name=group2]').attr("disabled",true);
	    }
	}
	 	




function userChangeStatus(entity){
	if (entity == "status"){
		 $("#statusChangemodal").openModal({
		 	   dismissible:false
		    });
	}else{
		$("#roleTypeChangemodal").openModal({
		 	   dismissible:false
		    });
	}
}

 function resetButtons(){
	 $('input[name=group1]').attr('checked',false);
	 $('input[name=group2]').attr('checked',false);
 }
	
 $('#addDeleteRole').on(
		 'change',
		 function() {
			 	var request = {
							"action" : parseInt($('#addDeleteRole').val()),
							"dataId" : parseInt(window.Id),
							"featureId" : parseInt(featureId),
							"userId" : parseInt(userId),
							"userType" : $("body").attr("data-roleType"),
							"userTypeId" : parseInt($("body").attr("data-userTypeID")),
							"username" : $("body").attr("data-selected-username")
						}
			 		//console.log(JSON.stringify(request));	
			 	var token = $("meta[name='_csrf']").attr("content");
				var header = $("meta[name='_csrf_header']").attr("content");
				$.ajaxSetup({
					headers:
					{ 'X-CSRF-TOKEN': token }
				});
			 		$.ajax({
							url : './getAddDeleteRoles',
							type : 'POST',
							data : JSON.stringify(request),
							dataType : 'json',
							contentType : 'application/json; charset=utf-8',
							success : function(data, textStatus, jqXHR) {
								if(data.errorCode == 200){
								$.i18n().locale = lang;	
								$.i18n().load( {
									'en': './resources/i18n/en.json',
									'km': './resources/i18n/km.json'
								}).done( function() {
									var result = data.data;
									console.log("result---> " + JSON.stringify(result));
									$("#usertypes").empty();
									for (i = 0; i < result.length; i++) {
										$('<option>').val(result[i].id).text(
												result[i].usertypeName).appendTo(
												'#usertypes');
									}
								});
								}else{
									$("#ErrorModel").openModal({
									 	   dismissible:false
								    });
									$('#ErrorFieldMessage').text($.i18n(data.tag));
								}
								

							},
							error : function(jqXHR, textStatus, errorThrown) {
								//console.log("error in ajax")
							}
						});
		});

	
 function chanegeUserStatus(changeType){
	 	var action;
	 	if (changeType == "status" ){
	 		action = 0; 
	 	}else if($('#addDeleteRole').val() == 1){
	 		action = 1;
	 	}else if($('#addDeleteRole').val() == 2){
	 		action = 2;
	 	}
	 	//var fileData = [];
	 	//var selectedRoleType = $('#usertypes').val();
	 	//var RoleType=fileData.push(selectedRoleType);
	 	
	 	var RoleType = $('#usertypes').val();
	 	var status= $("#userStatus").val();
	 	
	 	var Request={
				"action" : action,
				"status" : parseInt(status),
				"id": parseInt(window.Id),
				"username" : $("body").attr("data-selected-username"),
				"referenceId" : $("#refererenceId").val(),
				"remark" : $("#changeStatusRemark").val(),
				"userId" : parseInt(userId),
				"role"  : parseInt(RoleType),
				"usertype": parseInt(window.userTypeId)
	 		}
				
		
		//console.log("Request-->"+JSON.stringify(Request));
	 	var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$.ajaxSetup({
			headers:
			{ 'X-CSRF-TOKEN': token }
		});
		$.ajax({
			url : './adminChangeRequest',
			data : JSON.stringify(Request),
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			type : 'POST',
			success : function(data) {
				$("#confirmUserStatus").openModal({
				 	   dismissible:false
			    });
				if(data.errorCode == 200){
					$.i18n().locale = lang;	
					$.i18n().load( {
						'en': './resources/i18n/en.json',
						'km': './resources/i18n/km.json'
					}).done( function() {
						$('#statusChangedMessage').text($.i18n(data.tag));
					});
					}else{
						$('#statusChangedMessage').text($.i18n(data.tag));
					}
				
				
			},
			error : function() {
				alert("Failed");
			}
		});
	 return false
 }	
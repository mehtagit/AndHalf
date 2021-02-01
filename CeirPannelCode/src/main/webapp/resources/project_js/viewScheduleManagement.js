		var cierRoletype =$("body").attr("data-roleType");	
		var userId = $("body").attr("data-userID");
		var userType=$("body").attr("data-roleType");
		var featureId="54";
		var rejectedMsg,consignmentApproved,errorMsg,havingTxnID,updateMsg,hasBeenUpdated;
		var consignmentDeleted,deleteInProgress;
		var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';


		
		
		

         $(window).load(function(){
			$('div#initialloader').fadeIn('fast');
			scheduleTable(lang);
			sessionStorage.removeItem("session-value");
			pageRendering();
			
			
		 });
		

		$('.datepick').datepicker({
			dateFormat: "yy-mm-dd"
		});


		var sourceType =localStorage.getItem("sourceType");
		var TagId = sessionStorage.getItem("tagId");
		
		
		//**************************************************filter table**********************************************
		
		function scheduleTable(lang){
			
			var filterRequest={
					"startDate": $('#startDate').val(),
					"endDate":  $('#startDate').val(),
					"userId":parseInt(userId), 
					"featureId":parseInt(featureId),
					"userTypeId": parseInt($("body").attr("data-userTypeID")),
					"userType":$("body").attr("data-roleType"),
					"username" : $("body").attr("data-selected-username")
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
			$.ajax({
				url: 'headers?type=scheduleHeaders&lang='+lang,
				/*	headers: {"Accept-Language": "en"},*/
				type: 'POST',
				dataType: "json",
				success: function(result){
					var table=	$("#ScheduleManagementLibraryTable").DataTable({
						destroy:true,
						"serverSide": true,
						orderCellsTop : true,
						"ordering" : false,
						"bPaginate" : true,
						"bFilter" : true,
						"bInfo" : true,
						"bSearchable" : true,
						"oLanguage": {
							"sEmptyTable": "No records found in the system"
					    },
						initComplete: function() {
					 		$('.dataTables_filter input')
	       .off().on('keyup', function(event) {
	    	   if (event.keyCode === 13) {
	    			 table.search(this.value.trim(), false, false).draw();
	    		}
	          
	       });
		   },
						ajax: {
							url : 'scheduleReportData',
							type: 'POST',
							dataType: "json",
							data : function(d) {
								d.filter = JSON.stringify(filterRequest); 

							}
						},
						"columns": result
					});

					$('div#initialloader').delay(300).fadeOut('slow');
					
				},
				error: function (jqXHR, textStatus, errorThrown) {
					
				}
			});
			
		
		}

		$('.datepicker').on('mousedown',function(event){
			event.preventDefault();
		});



		function pageRendering(){
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			$.ajaxSetup({
				headers:
				{ 'X-CSRF-TOKEN': token }
			});
			$.ajax({
				url: 'scheduleManagement/pageRendering',
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
							$("#ScheduleTableDiv").append("<div class='input-field col s6 m2'>"+
									"<div id='enddatepicker' class='input-group'>"+
									"<input class='form-control datepicker' type='text' id="+date[i].id+" autocomplete='off'>"+
									"<label for="+date[i].id+">"+date[i].title
									+"</label>"+
									"<span	class='input-group-addon' style='color: #ff4081'>"+
									"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");
							$( "#"+date[i].id ).datepicker({
								dateFormat: "yy-mm-dd",
								 maxDate: new Date()
					        });
						}
						 
					} 
				
				// dynamic dropdown portion
					var dropdown=data.dropdownList;
					for(i=0; i<dropdown.length; i++){
						var dropdownDiv=
							$("#ScheduleTableDiv").append("<div class='col s6 m2 selectDropdwn'>"+
								
									"<div class='select-wrapper select2  initialized'>"+
									"<span class='caret'>"+"</span>"+
									"<input type='text' class='select-dropdown' readonly='true' data-activates='select-options-1023d34c-eac1-aa22-06a1-e420fcc55868' value='Consignment Status'>"+

									"<select id="+dropdown[i].id+" class='select2 initialized'>"+
									"<option value='' selected>"+dropdown[i].title+
									"</option>"+
									"</select>"+
									"</div>"+
							"</div>");
					}

						$("#ScheduleTableDiv").append("<div class=' col s3 m2 l1'><button type='button' class='btn primary botton' id='submitFilter'/></div>");
						$("#ScheduleTableDiv").append("<div class=' col s3 m2 l1'><a href='JavaScript:void(0)' type='button' class='export-to-excel right'  onclick='exportAddressData()'>"+$.i18n('Export')+"<i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");
						for(i=0; i<button.length; i++){
							$('#'+button[i].id).text(button[i].buttonTitle);
							$('#'+button[i].id).attr("onclick", button[i].buttonURL);
						}

				}
			}); 
			
			//populateCountries( "country",    "state");
	        //$("#country").val("Cambodia");
			setDropdown();
	   }

	

		
	function setDropdown(){
		$.getJSON('./getDropdownList/REPORT_CATEGORY', function(data) {
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].value).text(data[i].interp)
				.appendTo('#reportCatagory');
			}
		});
	}
	
	$('#reportCatagory').on(
			'change',
			function() {
				var token = $("meta[name='_csrf']").attr("content");
				var header = $("meta[name='_csrf_header']").attr("content");
				$.ajaxSetup({
					headers:
					{ 'X-CSRF-TOKEN': token }
				});
				
				var reportCategory = parseInt($('#reportCatagory').val());
				
				$.ajax({
					url: './getallreports?reportCategory='+reportCategory,
					type: 'POST',
					dataType : 'json',
					contentType : 'application/json; charset=utf-8',
					success: function (data, textStatus, jqXHR) {
						var result= data;
						$("#reportName").empty();
						for (i = 0; i < result.length; i++){
							//alert(result[i].reportTrends[0].typeFlag);
							$('<option>').val(result[i].reportnameId).text(result[i].reportName).attr("trendValue",result[i].reportTrends[0].typeFlag).appendTo('#reportName');
						//	$('<option>').val(data[i].tagId).text(data[i].interp).attr("docValue",data[i].value).appendTo('#doc_type');
						}

					},
					error: function (jqXHR, textStatus, errorThrown) {
						//////console.log("error in ajax")
					}
				});
			});
	
	
	
	function AddShedule(){
			$('#addSceduleModal').openModal({
		        dismissible:false
		    });
			$.getJSON('./getDropdownList/REPORT_CATEGORY', function(data) {
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#addreportCatagory');
				}
			});
		}
	
	$('#addreportCatagory').on(
			'change',
			function() {
				var token = $("meta[name='_csrf']").attr("content");
				var header = $("meta[name='_csrf_header']").attr("content");
				$.ajaxSetup({
					headers:
					{ 'X-CSRF-TOKEN': token }
				});
				
				var reportCategory = parseInt($('#addreportCatagory').val());
				
				$.ajax({
					url: './getallreports?reportCategory='+reportCategory,
					type: 'POST',
					dataType : 'json',
					contentType : 'application/json; charset=utf-8',
					success: function (data, textStatus, jqXHR) {
						var result= data;
						$("#tableId").empty();
						for (i = 0; i < result.length; i++){
							//alert(result[i].reportTrends[0].typeFlag);
							$('<option>').val(result[i].reportnameId).text(result[i].reportName).attr("trendValue",result[i].reportTrends[0].typeFlag).appendTo('#tableId');
						//	$('<option>').val(data[i].tagId).text(data[i].interp).attr("docValue",data[i].value).appendTo('#doc_type');
						}

					},
					error: function (jqXHR, textStatus, errorThrown) {
						//////console.log("error in ajax")
					}
				});
			});
	
	function saveShedule(){
		
		var reportCatagory = $("#addreportCatagory").val() == 'null' ? null : $("#addreportCatagory").val() == 'null' ? null : $("#addreportCatagory option:selected").text();
		var reportName = $("#tableId").val() == 'null' ? null : $("#tableId option:selected").text();
		var emailId = $("#email").val();
		var flag = $('#addStatus').val()== "" ? null : $("#addStatus option:selected").text();
	 	
		var Request={
				"category" : reportCatagory,
				"reportName": reportName,
				"emailId" :  emailId,
				"flag" : flag
			}
		console.log("Request-->"+JSON.stringify(Request));
	 	var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$.ajaxSetup({
			headers:
			{ 'X-CSRF-TOKEN': token }
		});
		$.ajax({
			url : './addSchedule',
			data : JSON.stringify(Request),
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			type : 'POST',
			success : function(data) {
				$("#confirmSaveModal").openModal({
				 	   dismissible:false
			    });
				
			$('#successMessage').text('Report Scheduled Successfully.');
			},
			error : function() {
				//alert("Failed");
			}
		});
	 return false
}
	
		
	function exportAddressData(){
		var table = $('#ScheduleManagementLibraryTable').DataTable();
		var info = table.page.info(); 
		var pageNo=info.page;
		var pageSize =info.length;
		var filterDistrict = $('#district').val() == ""|| $('#district').val() == undefined ? null : $("#district option:selected").text();
		var filterCommune = $('#commune').val() == ""|| $('#commune').val() == undefined ? null : $("#commune option:selected").text();
		var filterVillage = $('#village').val() == ""|| $('#village').val() == undefined ? null : $("#village option:selected").text();
		var filterRequest={
				"startDate": $('#startDate').val(),
				"province" : $("#proviance").val(),
				"district" : filterDistrict,
				"commune" :  filterCommune,
				"village" :  filterVillage,
				"userId":parseInt(userId), 
				"featureId":parseInt(featureId),
				"userTypeId": parseInt($("body").attr("data-userTypeID")),
				"userType":$("body").attr("data-roleType"),
				"username" : $("body").attr("data-selected-username"),
				//"userTypeId": parseInt($("body").attr("data-userTypeID")),
				//"userType" : $("body").attr("data-roleType"),
				"pageNo":parseInt(pageNo),
				"pageSize":parseInt(pageSize)
		}
		
		////console.log(JSON.stringify(filterRequest))
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$.ajaxSetup({
			headers:
			{ 'X-CSRF-TOKEN': token }
		});
		
		$.ajax({
			url: './exportAddress',
			type: 'POST',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			data : JSON.stringify(filterRequest),
			success: function (data, textStatus, jqXHR) {
				  window.location.href = data.url;

			},
			error: function (jqXHR, textStatus, errorThrown) {
				
			}
		});
		}
	
	function DeleteByID(id){
		$("#DeleteAddressModal").openModal({
	        dismissible:false
	    });
		$("#deleteFieldId").val(id);
		
	}	
	
	
	
	function confirmantiondelete(){
		var request ={
				"userId" : $("body").attr("data-userID"),
				"id"  : parseInt($("#deleteFieldId").val()),
				"userId":parseInt(userId),
				"featureId":parseInt(featureId),
				"userTypeId": parseInt($("body").attr("data-userTypeID")),
				"userType":$("body").attr("data-roleType"),
				"username" : $("body").attr("data-selected-username")
		}
		//////console.log(JSON.stringify(request));
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$.ajaxSetup({
			headers:
			{ 'X-CSRF-TOKEN': token }
		});
		
		$.ajax({
			url : './deleteAddress',
			data : JSON.stringify(request),
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			type : 'DELETE',
			success : function(data, textStatus, xhr) {
				////console.log(data);
				$("#DeleteAddressModal").closeModal();
				$("#closeDeleteModal").openModal({
			        dismissible:false
			    });
				
				$("#materialize-lean-overlay-3").css("display","none");
			},
			error : function() {
				////console.log("Error");
			}
		});
	}
	
	
	
	
	
	
	 
	 function resetFields(){
		$("#addreportCatagory").val("");
		$("#tableId").val("");
		$("#email").val("");
		$("#addStatus").val("");
		
	 }
	 
	 
	 function viewDetails(id,action){
			
			
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			$.ajaxSetup({
				headers:
				{ 'X-CSRF-TOKEN': token }
			});
			
				$.ajax({
					url : "./viewBy/"+id,
					dataType : 'json',
					contentType : 'application/json; charset=utf-8',
					type : 'GET',
					success: function (data, textStatus, jqXHR) {
							var data = data
							if(action == "View"){
								$("#ViewModel").openModal({
							        dismissible:false
							    });
								console.log("data---"+JSON.stringify(data));
								viewPopupData(data);
							}else{
								$("#editModel").openModal({
							        dismissible:false
							    });
								//editPopupData(result);
							}
							
					},
					error: function (jqXHR, textStatus, errorThrown) {
						////console.log("error in ajax")
					}
				});	
			}
	 
	 function viewPopupData(data){
		 	$("#viewreportCatagory").val(data.category);
		 	$("#viewtableId").val(data.reportName);
		 	$("#viewemail").val(data.emailId);
		 	$("#viewStatus").val(data.flag);
		 	$("label[for='viewemail']").addClass('active');
	}
	
		var cierRoletype =$("body").attr("data-roleType");	
		var startdate=$('#startDate').val(); 
		var endDate=$('#endDate').val();
		var taxStatus=$('#taxPaidStatus').val();
		var txnId=$('#transactionID').val();
		var consignmentStatus=$('#filterConsignmentStatus').val();
		var userId = $("body").attr("data-userID");
		var userType=$("body").attr("data-roleType");
		var featureId="25";
		var rejectedMsg,consignmentApproved,errorMsg,havingTxnID,updateMsg,hasBeenUpdated;
		var consignmentDeleted,deleteInProgress;
		var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';


		$.i18n().locale = lang;	
		
		$.i18n().load( {
			'en': '../resources/i18n/en.json',
			'km': '../resources/i18n/km.json'
		} ).done( function() { 
			rejectedMsg=$.i18n('rejectedMsg');
			consignmentApproved=$.i18n('consignmentApproved');
			errorMsg=$.i18n('errorMsg');
			havingTxnID=$.i18n('havingTxnID');
			updateMsg=$.i18n('updateMsg');
			hasBeenUpdated=$.i18n('hasBeenUpdated');
			consignmentDeleted=$.i18n('consignmentDeleted');
			deleteInProgress=$.i18n('deleteInProgress');
		});

         $(window).load(function(){
			$('div#initialloader').fadeIn('fast');
			currencyFieldTable(lang);
			sessionStorage.removeItem("session-value");
			pageRendering();
			
		 });
		

		$('.datepick').datepicker({
			dateFormat: "yy-mm-dd"
		});


		var sourceType =localStorage.getItem("sourceType");
		var TagId = sessionStorage.getItem("tagId");
		
		
		//**************************************************filter table**********************************************
		
		function currencyFieldTable(lang){
			
			var filterRequest={
					"endDate":$('#endDate').val(),
					"startDate":$('#startDate').val(),
					"userId":parseInt(userId),
					"featureId":parseInt(featureId),
					"userTypeId": parseInt($("body").attr("data-userTypeID")),
					"userType":$("body").attr("data-roleType"),
					"currency" : parseInt($("#currencyType").val())
			}				
			if(lang=='km'){
				var langFile="//cdn.datatables.net/plug-ins/1.10.20/i18n/Khmer.json";
			}
			$.ajax({
				url: 'headers?type=currencyHeaders&lang='+lang,
				/*	headers: {"Accept-Language": "en"},*/
				type: 'POST',
				dataType: "json",
				success: function(result){
					var table=	$("#currencyManagementLibraryTable").DataTable({
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
							url : 'currencyManagementData',
							type: 'POST',
							dataType: "json",
							data : function(d) {
								d.filter = JSON.stringify(filterRequest); 

							}
						},
						"columns": result
					});

					$('div#initialloader').delay(300).fadeOut('slow');
						$('#currencyManagementLibraryTable input').unbind();
						$('#currencyManagementLibraryTable input').bind('keyup', function (e) {
							if (e.keyCode == 13) {
								table.search(this.value).draw();
							}

						});
				},
				error: function (jqXHR, textStatus, errorThrown) {
					
				}
			});
			
		
		}

		$('.datepicker').on('mousedown',function(event){
			event.preventDefault();
		});



		function pageRendering(){
			$.ajax({
				url: 'currencyManagement/pageRendering',
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
							$("#CurrencyTableDiv").append("<div class='input-field col s6 m2'>"+
									"<div id='enddatepicker' class='input-group'>"+
									"<input class='form-control datepicker' type='text' id="+date[i].id+" autocomplete='off' onchange='checkDate(startDate,endDate)'>"+
									"<label for="+date[i].id+">"+date[i].title
									+"</label>"+
									"<span	class='input-group-addon' style='color: #ff4081'>"+
									"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");

						}else if(date[i].type === "text"){
							$("#CurrencyTableDiv").append("<div class='input-field col s6 m2' ><input type="+date[i].type+" id="+date[i].id+" maxlength='19' /><label for="+date[i].id+" class='center-align'>"+date[i].title+"</label></div>");
						}
					} 
				
				// dynamic dropdown portion
					var dropdown=data.dropdownList;
					for(i=0; i<dropdown.length; i++){
						var dropdownDiv=
							$("#CurrencyTableDiv").append("<div class='col s6 m2 selectDropdwn'>"+
								
									"<div class='select-wrapper select2  initialized'>"+
									"<span class='caret'>"+"</span>"+
									"<input type='text' class='select-dropdown' readonly='true' data-activates='select-options-1023d34c-eac1-aa22-06a1-e420fcc55868' value='Consignment Status'>"+

									"<select id="+dropdown[i].id+" class='select2 initialized'>"+
									"<option value=''>"+dropdown[i].title+
									"</option>"+
									"</select>"+
									"</div>"+
							"</div>");
					}

						$("#CurrencyTableDiv").append("<div class=' col s3 m2 l1'><button type='button' class='btn primary botton' id='submitFilter'/></div>");
						//$("#CurrencyTableDiv").append("<div class=' col s3 m2 l7'><a href='JavaScript:void(0)' type='button' class='export-to-excel right'  onclick='exportConsignmentData()'>"+$.i18n('Export')+"<i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");
						for(i=0; i<button.length; i++){
							$('#'+button[i].id).text(button[i].buttonTitle);
							$('#'+button[i].id).attr("onclick", button[i].buttonURL);
						}

					/*	for(i=0; i<button.length; i++){
							$('#'+button[i].id).text(button[i].buttonTitle);
							if(button[i].type === "HeaderButton"){
								$('#'+button[i].id).attr("onclick", button[i].buttonURL);
							}
							
						}*/

				
					
				$('.datepicker').datepicker({
						dateFormat: "yy-mm-dd"
					});
				}
			}); 
			
			
			setDropdown();
	}



		
	function setDropdown(){
		$.getJSON('./getDropdownList/CURRENCY', function(data) {
				/ $("#expectedArrivalPort").empty(); /
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp).appendTo('#currencyType');
				}
			});
	}

		function AddCurrencyAddress(){
			$('#addPort').openModal();
			//var tagDropDown =  document.getElementById("tag");
			//var displayName = tagDropDown.options[tagDropDown.selectedIndex].text;
		}
		
	
	/*----------------------------------- Save Field ----------------------------------------- */
		
	function submitPort(){
		
		var request={
					  "port":   $('#port').val(),
					  "address": $('#portAddress').val(),
				}
		
		console.log("request------------->" +JSON.stringify(request))
		$.ajax({
			url : './add-Port',
			data : JSON.stringify(request),
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			type : 'POST',
			success : function(data, textStatus, jqXHR) {
					console.log(JSON.stringify(data));
					$("#confirmField").openModal();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("error in ajax")
			}
		});
			
			return false
	}


  
	/*--------------------------------- Edit Model View -----------------------------------*/
	
	
	function PortViewByID(id){
		$("#editId").val(id);
		
		$.ajax({
				url: './portViewByID/'+id,
				type: 'POST',
			//	data : JSON.stringify(request),
				dataType : 'json',
				contentType : 'application/json; charset=utf-8',
				success: function (data, textStatus, jqXHR) {
						var result = data.data
						$("#editPortAddressModal").openModal();
						PortEditPopupData(result);
						console.log(result)
				},
				error: function (jqXHR, textStatus, errorThrown) {
					console.log("error in ajax")
				}
			});	
		}
	
	
	function PortEditPopupData(result){
		$("#editport").val(result.port);
		$("#editId").val(result.id);
		$("#editportAddress").val(result.address);
		
	}
	
	
	/*---------------------------------- Update Field-------------------------------------*/
	
	
	function updatedPort(){
	
		var request ={ 
				 "id" : parseInt($("#editId").val()),
				 "port":   $('#editport').val(),
				 "address": $('#editportAddress').val(),
		}
		
		console.log("request--->" +JSON.stringify(request))
		$.ajax({
			url: './updatePortAddress',
			type: 'POST',
			data : JSON.stringify(request),
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success: function (data, textStatus, jqXHR) {
			
				console.log("Updated data---->" +data)
				$("#editPortAddressModal").closeModal();	
				$("#updateFieldsSuccess").openModal();
				
			},
			error: function (jqXHR, textStatus, errorThrown) {
				console.log("error in ajax")
			}
		});	
		
		return false
	}

	
	
  /*------------------------------------ Delete Field -----------------------------------*/
	
	
	function DeletePortRecord(id){
		$("#DeleteFieldModal").openModal();
		$("#deletePortId").val(id);
		
	}	
	
	
	
	function confirmantiondelete(){
		
		var id  = parseInt($("#deletePortId").val());
		
		console.log(JSON.stringify(id));
		$.ajax({
			url : './deletePort/'+id,
//			data : JSON.stringify(request),
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			type : 'POST',
			success : function(data, textStatus, xhr) {
				console.log(data);
				$("#DeleteFieldModal").closeModal();
				$("#closeDeleteModal").openModal();
				
				$("#materialize-lean-overlay-3").css("display","none");
			},
			error : function() {
				console.log("Error");
			}
		});
	}
	
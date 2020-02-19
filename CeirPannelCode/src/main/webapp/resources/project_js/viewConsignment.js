		var cierRoletype =$("body").attr("data-roleType");	
		var startdate=$('#startDate').val(); 
		var endDate=$('#endDate').val();
		var taxStatus=$('#taxPaidStatus').val();
		var txnId=$('#transactionID').val();
		var consignmentStatus=$('#filterConsignmentStatus').val();
		var userId = $("body").attr("data-userID");
		var userType=$("body").attr("data-roleType");
		var featureId="3";
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
			filterConsignment(lang);
			sessionStorage.removeItem("session-value");
			pageRendering();
		 });
		

		$('.datepick').datepicker({
			dateFormat: "yy-mm-dd"
		});




		function DeleteConsignmentRecord(txnId){
			$("#DeleteConsignment").openModal();
			$("#transID").text(txnId);
		}


		function confirmantiondelete(){
			var txnId = $("#transID").text();
			var remarks = $("#textarea1").val();
			var obj ={
					"txnId" : txnId,
					"remarks" : remarks
			}
			$.ajax({
				url : "./deleteConsignment",
				data : JSON.stringify(obj),
				dataType : 'json',
				contentType : 'application/json; charset=utf-8',
				type : 'POST',
				success : function(data, textStatus, xhr) {
					if(data.errorCode == 200){
						$("#consignmentText").text('');
						$("#consignmentText").text(data.message);

					}else if(data.errorCode == 0){
						$("#consignmentText").text('');
						$("#consignmentText").text(deleteInProgress);
					}
					else{
						$("#consignmentText").text(errorMsg);
					}
				},
				error : function() {
					
				}
			});
			$("#DeleteConsignment").closeModal();
			$("#confirmDeleteConsignment").openModal();
		}
		$.getJSON('../getDropdownList/CUSTOMS_PORT', function(data) {
			$("#expectedArrivalPortEdit").empty();
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].value).text(data[i].interp)
				.appendTo('#expectedArrivalPortEdit');

			}
		});

		function EditConsignmentDetails(txnId){ 	
			$.ajax({
				url : "./openRegisterConsignmentPopup?reqType=editPage&txnId="+txnId,
				dataType : 'json',
				contentType : 'application/json; charset=utf-8',
				type : 'GET',
				success : function(data) {

					/*	$("#expectedArrivalPortEdit").empty();*/


					ConsignmentCurrency();

					setEditPopupData(data) ;
				},
				error : function() {
					alert("Failed");
				}
			});

			$("#updateModal").openModal();
		}

	function ConsignmentCurrency()
		{
			var currency="CURRENCY";
			$.ajax({
				url: './consignmentCurency?CURRENCY='+currency,
				type: 'GET',
				processData: false,
				contentType: false,
				success: function (data, textStatus, jqXHR) {
					

					$('#currency').empty();
					for (i = 0; i < data.length; i++){
						var html='<option value="'+data[i].value+'">'+data[i].interp+'</option>';
						//$('<option>').val(data[i]).channnelName.text(data[i]).channnelName.appendTo('#channelId');
						$('#currency').append(html);	
					}
					$('#currency').val($("#hideCurrency").val()); 

				},
				error: function (jqXHR, textStatus, errorThrown) {
					
				}
			});
		}

		function viewConsignmentCurrency()
		{
			var currency="CURRENCY";
			$.ajax({
				url: './consignmentCurency?CURRENCY='+currency,
				type: 'GET',
				processData: false,
				contentType: false,
				success: function (data, textStatus, jqXHR) {
					
			

					/*	$('#viewcurrency').empty();*/
					for (i = 0; i < data.length; i++){
						var html='<option value="'+data[i].value+'">'+data[i].interp+'</option>';
						//$('<option>').val(data[i]).channnelName.text(data[i]).channnelName.appendTo('#channelId');
						$('#viewcurrency').append(html);	
					}
					$('#viewcurrency').val($("#viewhideCurrency").val()); 

				},
				error: function (jqXHR, textStatus, errorThrown) {
					
				}
			});
		}

		function viewConsignmentDetails(txnId){
			$("#viewModal").openModal();
			$.ajax({
				url : "./openRegisterConsignmentPopup?reqType=editPage&txnId="+txnId,
				dataType : 'json',
				contentType : 'application/json; charset=utf-8',
				type : 'GET',
				success : function(data) {
					setViewPopupData(data);
					viewConsignmentCurrency();
				},
				error : function() {
					alert("Failed");
				}
			});
		}


		function setViewPopupData(data){
			var totalPrice='';
			console.log(data.totalPrice);
			if(data.totalPrice==null){
			     totalPrice="";
			     $("#viewCurrencyDiv").css("display", "none");
			     
			}
			else{
				totalPrice=(parseInt(data.totalPrice));
				$("#viewCurrencyDiv").css("display", "block");
			}
			
			$("#supplierId").val(data.supplierId);
			$("#supplierName").val(data.supplierName);
			$("#consignmentNumber").val(data.consignmentNumber);
			$("#expectedDispatcheDate").val(data.expectedDispatcheDate);
			$("#countryview").val(data.organisationCountry);
			$("#expectedArrivaldate").val(data.expectedArrivaldate);
			$("#expectedArrivalPort").val(data.expectedArrivalPortInterp);
			$("#Quantity").val(data.quantity);
			$("#TransactionId").val(data.txnId);
			$("#remark").val(data.remarks);
			$("#fileName").val(data.fileName); 
			$("#viewcurrency").val(data.currency);
			$("#viewtotalPrice").val(totalPrice);
			$("#viewhideCurrency").val(data.currency);


		}

		function setEditPopupData(data){

			var totalPrice='';
			console.log(data.totalPrice);
			if(data.totalPrice==null){
			     totalPrice="";
			     $("#currencyDiv").css("display", "none"); 
			}
			else{
				totalPrice=(parseInt(data.totalPrice));
				 $("#currencyDiv").css("display", "block"); 
				
			}
			
			$("#supplierIdEdit").val(data.supplierId);
			$("#supplierNameEdit").val(data.supplierName);
			$("#consignmentNumberEdit").val(data.consignmentNumber);
			$("#expectedDispatcheDateEdit").val(data.expectedDispatcheDate);
			$('#country').val(data.organisationCountry);
			$("#expectedArrivaldateEdit").val(data.expectedArrivaldate);
			$("#expectedArrivalPortEdit").val(data.expectedArrivalPort);
			$("#QuantityEdit").val(data.quantity);
			$("#TransactionIdEdit").val(data.txnId);
			$("#fileNameEdit").val(data.fileName);
			$("#fileNameToBeSame").val(data.fileName);
			
			$("#currency").val(data.currency);
			$("#totalPrice").val(totalPrice);
			$("#hideCurrency").val(data.currency);


		} 



		var sourceType =localStorage.getItem("sourceType");
		function filterConsignment(lang)
		{       	
			
			var sessionFlag;
			
			if(sourceType==null){
				sessionFlag=2;
				
			}
			else{
				sessionFlag=1;
				
			}

			if(cierRoletype=="Importer" && sourceType !="viaStolen" ){
				table('../headers?lang='+lang+'&type=consignment','../consignmentData?sessionFlag='+sessionFlag);
			}

			else if(cierRoletype=="Custom" && sourceType !="viaStolen"){
				table('../headers?lang='+lang+'&type=customConsignment','../consignmentData?sessionFlag='+sessionFlag);
			}

			else if(cierRoletype=="CEIRAdmin"  && sourceType !="viaStolen"){
				table('../headers?lang='+lang+'&type=adminConsignment','../consignmentData?sessionFlag='+sessionFlag);
			}  

			else if(cierRoletype=="Importer" && sourceType ==="viaStolen" ){

				table('../headers?lang='+lang+'&type=stolenconsignment','../consignmentData?sourceType=viaStolen&sessionFlag='+sessionFlag);
			}


			localStorage.removeItem('sourceType');

		}

		
		
		//**************************************************filter table**********************************************

		function table(url,dataUrl){
		var txn= (txnIdValue == 'null' && transactionIDValue == undefined)? $('#transactionID').val() : transactionIDValue;
		
			var filterRequest={
					"consignmentStatus":parseInt($('#filterConsignmentStatus').val()),
					"endDate":$('#endDate').val(),
					"startDate":$('#startDate').val(),
					"taxPaidStatus":parseInt($('#taxPaidStatus').val()),
					"userId":parseInt(userId),
					"featureId":parseInt(featureId),
					"userTypeId": parseInt($("body").attr("data-userTypeID")),
					"txnId":txn,
					"userType":$("body").attr("data-roleType")
			}
			if(lang=='km'){
				var langFile="//cdn.datatables.net/plug-ins/1.10.20/i18n/Khmer.json";
			}
			$.ajax({
				url: url,
				/*	headers: {"Accept-Language": "en"},*/
				type: 'POST',
				dataType: "json",
				success: function(result){
					var table=	$("#consignmentLibraryTable").DataTable({
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
							url : dataUrl,
							type: 'POST',
							dataType: "json",
							data : function(d) {
								d.filter = JSON.stringify(filterRequest); 

							}
						},
						"columns": result
					});

					$('div#initialloader').delay(300).fadeOut('slow');
						$('#consignmentLibraryTable input').unbind();
						$('#consignmentLibraryTable input').bind('keyup', function (e) {
							if (e.keyCode == 13) {
								table.search(this.value).draw();
							}

						});
				},
				error: function (jqXHR, textStatus, errorThrown) {
					
				}
			});
		}



		//******************************************************************************************************************************************************************888888
		//******************************************************************************************************************************************************************888888
		//******************************************************************************************************************************************************************888888   

		function editRegisterConsignment(){

			var supplierId=$('#supplierIdEdit').val();
			var supplierName=$('#supplierNameEdit').val();
			var consignmentNumber=$('#consignmentNumberEdit').val();
			var expectedArrivalDate=$('#expectedArrivaldateEdit').val();
			var expectedDispatcheDate=$('#expectedDispatcheDateEdit').val();
			var expectedArrivalPort=$('#expectedArrivalPortEdit').val();
			var organisationcountry=$('#country').val();
			var filename=$('#fileNameEdit').val();
			var txnId=$('#TransactionIdEdit').val();
			var quantity=$('#QuantityEdit').val();
			var currency=$('#currency').val();
			var totalPrice=$('#totalPrice').val();

			var formData= new FormData();
			formData.append('file', $('#csvUploadFile')[0].files[0]);
			formData.append('supplierId',supplierId);
			formData.append('supplierName',supplierName);
			formData.append('consignmentNumber',consignmentNumber);
			formData.append('expectedArrivaldate',expectedArrivalDate);
			formData.append('expectedDispatcheDate',expectedDispatcheDate);
			formData.append('expectedArrivalPort',expectedArrivalPort);
			formData.append('organisationcountry',organisationcountry);
			formData.append('quantity',quantity);
			formData.append('txnId',txnId);
			formData.append('filename',filename);
			formData.append('currency',currency);
			formData.append('totalPrice',totalPrice);
			$.ajax({
				url: './updateRegisterConsignment',
				type: 'POST',
				data: formData,
				processData: false,
				contentType: false,
				success: function (data, textStatus, jqXHR) {
					$('#updateModal').closeModal();

					$('#updateConsignment').openModal();
					if(data.errorCode==200){


						$('#sucessMessage').text('');
						$('#sucessMessage').text(data.message);
					}

					else if (data.errorCode==0){

						$('#sucessMessage').text('');
						$('#sucessMessage').text(updateMsg+" "+ (data.txnId) +" "+hasBeenUpdated);
					}
					else 
					{
						$('#sucessMessage').text('');
						$('#sucessMessage').text(data.message);
					}

				},
				error: function (jqXHR, textStatus, errorThrown) {
					
				}
			});
			
			return false;
		}



		function openDeleteModal(transactionId)
		{
			$('#deletemodal').openModal();
			$('#deleteTransactionId').val(transactionId);
		}




		function myFunction(message) {
			var x = document.getElementById("snackbar");
			x.className = "show";
			$('#errorMessage').html(message);
			setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
		}

		


		$('.datepicker').on('mousedown',function(event){
			event.preventDefault();
		});



		function closeUpdateModal(){
			$("#DeleteConsignment").closeModal();
			$('#updateModal').closeModal();
			$(".lean-overlay").remove();
		}

		function closeViewModal()
		{
		$('#viewModal').closeModal();
			$(".lean-overlay").remove();

		}

		populateCountries
		(   
				"country"
		);

		function pageRendering(){
			if(sourceType !="viaStolen" ){
				pageButtons('../consignment/pageRendering');

			}else if(sourceType ==="viaStolen" ){
				pageButtons('../consignment/pageRendering?sourceType=viaStolen');

			}
			localStorage.removeItem('sourceType');

		}


		function pageButtons(url){
			$.ajax({
				url: url,
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
							$("#consignmentTableDIv").append("<div class='input-field col s6 m2'>"+
									"<div id='enddatepicker' class='input-group'>"+
									"<input class='form-control datepicker' type='text' id="+date[i].id+" autocomplete='off' onchange='checkDate(startDate,endDate)'>"+
									"<label for="+date[i].id+">"+date[i].title
									+"</label>"+
									"<span	class='input-group-addon' style='color: #ff4081'>"+
									"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");

						}else if(date[i].type === "text"){
							$("#consignmentTableDIv").append("<div class='input-field col s6 m2' ><input type="+date[i].type+" id="+date[i].id+" maxlength='19' /><label for="+date[i].id+" class='center-align'>"+date[i].title+"</label></div>");
						}
					} 
				
					// dynamic dropdown portion
					var dropdown=data.dropdownList;
					for(i=0; i<dropdown.length; i++){
						var dropdownDiv=
							$("#consignmentTableDIv").append("<div class='col s6 m2 selectDropdwn'>"+
								
									"<div class='select-wrapper select2  initialized'>"+
									"<span class='caret'>"+"</span>"+
									"<input type='text' class='select-dropdown' readonly='true' data-activates='select-options-1023d34c-eac1-aa22-06a1-e420fcc55868' value='Consignment Status'>"+

									"<select id="+dropdown[i].id+" class='select2 initialized'>"+
									"<option>"+dropdown[i].title+
									"</option>"+
									"</select>"+
									"</div>"+
							"</div>");
					}


					if(sourceType=="viaStolen"){
						$("#btnLink").css({display: "none"});

						$("#consignmentTableDIv").append("<div class=' col s3 m2 l1'><button type='button' class='btn primary botton' id='submitFilter'/></div>");
						$("#consignmentTableDIv").append("<div class=' col s3 m2 l1'><a href='JavaScript:void(0)' type='button' class='export-to-excel right' onclick='exportConsignmentData()'>"+$.i18n('Export')+"<i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");
							
						for(i=0; i<button.length; i++){
							$('#'+button[i].id).text(button[i].buttonTitle);
							if(button[i].type === "HeaderButton"){
								$('#'+button[i].id).attr("href", button[i].buttonURL);
							}
							else{
								$('#'+button[i].id).attr("onclick", button[i].buttonURL);
							}
						}

						$("#footerBtn").append("<div class='col s12 m2 l2'><button class='btn' id='markedstolen' style='margin-left:38%;margin-top: 8px;'></button><button class='btn' id='cancel' style='margin-left: 22px;margin-top: 8px;'></button></div>");
						for(i=0; i<button.length; i++){
							$('#'+button[i].id).text(button[i].buttonTitle);
							if(button[i].type === "FooterButton"){
								$('#'+button[i].id).attr("onclick", button[i].buttonURL);
							}
							else{
								$('#'+button[i].id).attr("href", button[i].buttonURL);

							}
						}		

					}else{
						$("#consignmentTableDIv").append("<div class=' col s3 m2 l1'><button type='button' class='btn primary botton' id='submitFilter'/></div>");
						$("#consignmentTableDIv").append("<div class=' col s3 m2 l1'><a href='JavaScript:void(0)' type='button' class='export-to-excel right'  onclick='exportConsignmentData()'>"+$.i18n('Export')+"<i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");


						for(i=0; i<button.length; i++){
							$('#'+button[i].id).text(button[i].buttonTitle);
							if(button[i].type === "HeaderButton"){
								$('#'+button[i].id).attr("href", button[i].buttonURL);
							}
							else{
								$('#'+button[i].id).attr("onclick", button[i].buttonURL);
							}
						}

					}
					sourceType=="viaStolen"? $("#btnLink").css({display: "none"}) : $("#btnLink").css({display: "block"});
				
					if(cierRoletype=="CEIRAdmin" || cierRoletype=="Custom"){ 
						$("#btnLink").css({display: "none"}); 
					}
					//Consignment status-----------dropdown
					$.getJSON('../getDropdownList/'+featureId+'/'+$("body").attr("data-userTypeID"), function(data) {

						for (i = 0; i < data.length; i++) {
							$('<option>').val(data[i].state).text(data[i].interp)
							.appendTo('#filterConsignmentStatus');
						}
						var consignmentStatus= $("body").attr("data-selected-consignmentStatus");
						
						if(consignmentStatus=="")
						{
							

						}
						else{
							
							$("#filterConsignmentStatus").val(consignmentStatus).change();
						}
					});



					//Tax paid status-----------dropdown
					$.getJSON('../getDropdownList/CUSTOMS_TAX_STATUS', function(data) {
						for (i = 0; i < data.length; i++) {
							$('<option>').val(data[i].value).text(data[i].interp)
							.appendTo('#taxPaidStatus');
						}
					});

					var txnid = $("body").attr("data-selected-consignmentTxnId");

				/* 	$('#transactionID').val('');
					$('#transactionID').val(txnid); */
					$('#transactionID').attr("placeholder","" );
					if(txnid=="")
					{
					
					}
					else{
					
						$('label[for=TransactionID]').remove();
					}

					$('.datepicker').datepicker({
						dateFormat: "yy-mm-dd"
					});
				}
			}); 
		//	$("#consignmentTableDIv").append("<span id='errorMsg'></span>");
			
		}






		function openApprovePopUp(txnId,displayName)
		{
			var userType=$("body").attr("data-roleType");
			displayName=displayName.replace("+20"," " );
			$('#ApproveConsignment').openModal();
			if(userType=='Custom'){
				
				$('#ApproveConsignmentTxnid').text(txnId);
				$('#setApproveConsignmentTxnId').val(txnId);
				$('#displayname').text(displayName);
					
			}
			else{
				$('#approveConsignmnetHeading').text('');
				$('#approveConsignmnetHeading').text(havingTxnID+txnId+'?');
				$('#confirmationMessage').text('');
				$('#setApproveConsignmentTxnId').val(txnId);
				$('#displayname').text(displayName);
				
			}



		}
		function approveSubmit(actiontype){
			var txnId=$('#setApproveConsignmentTxnId').val();

			var approveRequest={
					"action": actiontype,
					"txnId":txnId,
					"featureId":3
			}
			$.ajax({
				url : "./updateConsignmentStatus",
				data : JSON.stringify(approveRequest),
				dataType : 'json',
				contentType : 'application/json; charset=utf-8',
				type : 'POST',
				success : function(data) {
					$('#confirmApproveConsignment').openModal();
					if(data.errorCode==0){

						$('#approveSuccessMessage').text('');
						$('#approveSuccessMessage').text(consignmentApproved);
					}
					else{
						$('#approveSuccessMessage').text('');
						$('#approveSuccessMessage').text(errorMsg);
					}
				},
				error : function() {
				
				}
			});
		}

		function openDisapprovePopup(txnId,displayName)
		{
			displayName=displayName.replace("+20"," " );
			$('#RejectConsignment').openModal();
			$('#disaproveTxnId').text(txnId);
			$('#setDisapproveConsignmentTxnId').val(txnId);
			$('#disapprovedDisplayname').text(displayName);


		}

		function disapproveSubmit(actiontype){
			var txnId=$('#setDisapproveConsignmentTxnId').val();
			var Remark=$('#dispproveRemarks').val();

			var approveRequest={
					"action": actiontype,
					"txnId":txnId,
					"remarks":Remark,
					"featureId":3
			}
			$.ajax({
				url : "./updateConsignmentStatus",
				data : JSON.stringify(approveRequest),
				dataType : 'json',
				contentType : 'application/json; charset=utf-8',
				type : 'POST',
				success : function(data) {
					setTimeout(function(){ $('#confirmRejectConsignment').openModal()}, 200);

					if(data.errorCode==0){

						$('#disapproveSuccessMessage').text('');
						$('#disapproveSuccessMessage').text(rejectedMsg);
					}
					else{
						$('#disapproveSuccessMessage').text('');
						$('#disapproveSuccessMessage').text(errorMsg);
					}
				},
				error : function() {
				
				}
			});
		}	


		function valuesPush(){
			var selectedMultipleConsignment=[];
			var roleType = $("body").attr("data-roleType");
			var currentRoleType = $("body").attr("data-stolenselected-roleType"); 
			var role = currentRoleType == null ? roleType : currentRoleType;
			var requestType=0;
			$('#consignmentLibraryTable tr td input:checkbox:checked').each(function() {

				var json={
						"txnId":$(this).closest('tr').find('td:eq(2)').text(),
						"userId":userId,
						"sourceType":'0',
						"roleType":role,
						"requestType":requestType
						}
			selectedMultipleConsignment.push(json);
			});
			return selectedMultipleConsignment;
		}



		function markedstolen(){
			$('#markAsMultipleStolen').openModal();

		}

		function openMulipleStolenPopUp()
		{

			var stolenRecoverydata=JSON.stringify(valuesPush());
			$.ajax({
				url: '../multipleStolenRecovery',
				type: 'POST',
				data: stolenRecoverydata,
				dataType : 'json',
				contentType : 'application/json; charset=utf-8',
				success: function (data, textStatus, jqXHR) {
					$('#markAsStolenDone').openModal();
				},
				error: function (jqXHR, textStatus, errorThrown) {
				
				}
			});

		}
		function redirectToViewPage()
		{
			var roleType = $("body").attr("data-roleType");
			var userId = $("body").attr("data-userID");
			var currentRoleType = $("body").attr("data-stolenselected-roleType"); 
			var role = currentRoleType == null ? roleType : currentRoleType;
			window.location.href = "../stolenRecovery?userTypeId="+role;

		}

		//**********************************************************Export Excel file************************************************************************
		function exportConsignmentData()
		{
			var consignmentStartDate=$('#startDate').val();
			var consignmentEndDate=$('#endDate').val();
			var consignmentTxnId=$('#transactionID').val();
			var filterConsignmentStatus=parseInt($('#filterConsignmentStatus').val());
			var consignmentTaxPaidStatus=parseInt($('#taxPaidStatus').val());
			if(isNaN(consignmentTaxPaidStatus) && isNaN(filterConsignmentStatus) )
			{
				consignmentTaxPaidStatus="";
				filterConsignmentStatus='';
				
			}
			else if(isNaN(consignmentTaxPaidStatus))
			{
				consignmentTaxPaidStatus="";
			
			}
			else if(isNaN(filterConsignmentStatus))
			{
				filterConsignmentStatus='';
			
			}

			var table = $('#consignmentLibraryTable').DataTable();
			var info = table.page.info(); 
			var pageNo=info.page;
			var pageSize =info.length;
			window.location.href="./exportConsignmnet?consignmentStartDate="+consignmentStartDate+"&consignmentEndDate="+consignmentEndDate+"&consignmentTxnId="+consignmentTxnId+"&filterConsignmentStatus="+filterConsignmentStatus+"&consignmentTaxPaidStatus="+consignmentTaxPaidStatus+"&pageSize="+pageSize+"&pageNo="+pageNo;
		}

function fileTypeValueChanges() {
			var uploadedFileName = $("#csvUploadFile").val();
			uploadedFileName = uploadedFileName.replace(/^.*[\\\/]/, '');
			var ext = uploadedFileName.split('.').pop();
		
			var fileSize = ($("#csvUploadFile")[0].files[0].size);
			fileSize = (Math.round((fileSize / 1024) * 100) / 100)
		   if (uploadedFileName.length > 30) {
		       $('#fileFormateModal').openModal();
		       $('#fileErrormessage').text('');
		       $('#fileErrormessage').text('file name length must be less then 30 characters.');
		   } 
			else if(ext!='csv')
				{
				$('#fileFormateModal').openModal();
				 $('#fileErrormessage').text('');
			       $('#fileErrormessage').text('file extension must be in  CSV.');
				}
			else if(fileSize>='5000'){
				$('#fileFormateModal').openModal();
				 $('#fileErrormessage').text('');
			       $('#fileErrormessage').text('file size must be less then 5 mb.');
			}
			else {
				console.log("file formate is correct")
				
			}
			

		}

		function clearFileName() {
			var existingfile=$("#fileNameToBeSame").val();
			//$('#fileNameEdit').val('');
			$("#csvUploadFile").val('');
			$('#fileFormateModal').closeModal();
			
			$("#fileNameEdit").val(existingfile);
		}

		
		
		$(document).on("keyup", "#totalPrice", function(e) {
			var totalPrice=$('#totalPrice').val();
			if(totalPrice.length<'1' )
			{
			$("#currency").attr("required", false);
			/*$('#currency').attr("disabled",true);*/
			$('#currencyDiv').hide();

			//$("#currency")[0].selectedIndex = 0;

			}
			else
			{
			$("#currency").attr("required", true);
			/*$('#currency').attr("disabled",false);*/
			$('#currencyDiv').show();

			}
			});	
		function fileTypeValueChanges() {
			var uploadedFileName = $("#csvUploadFile").val();
			uploadedFileName = uploadedFileName.replace(/^.*[\\\/]/, '');
			var ext = uploadedFileName.split('.').pop();
		
			var fileSize = ($("#csvUploadFile")[0].files[0].size);
			fileSize = (Math.round((fileSize / 1024) * 100) / 100)
		   if (uploadedFileName.length > 30) {
		       $('#fileFormateModal').openModal();
		       
		   } 
			else if(ext!='csv')
				{
				$('#fileFormateModal').openModal();
				 
				}
			else if(fileSize>='2000'){
				$('#fileFormateModal').openModal();
				 
			}
			else {
				console.log("file formate is correct")
				
			}
			

		}

		function clearFileName() {
			var existingfile=$("#fileNameToBeSame").val();
			//$('#fileNameEdit').val('');
			$("#csvUploadFile").val('');
			$('#fileFormateModal').closeModal();
			
			$("#fileNameEdit").val(existingfile);
		}

		
		
		$(document).on("keyup", "#totalPrice", function(e) {
			var totalPrice=$('#totalPrice').val();
			if(totalPrice.length<'1' )
			{
			$("#currency").attr("required", false);
			/*$('#currency').attr("disabled",true);*/
			$('#currencyDiv').hide();

			//$("#currency")[0].selectedIndex = 0;

			}
			else
			{
			$("#currency").attr("required", true);
			/*$('#currency').attr("disabled",false);*/
			$('#currencyDiv').show();

			}
			});
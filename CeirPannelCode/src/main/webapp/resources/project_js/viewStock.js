var period= $("body").attr("data-period");	
var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';

		$.i18n().locale = lang;
		var stockRejected,stockApproved,stockTxn,errorOccured,operationNotAllowed,stockDeleted,updateMsg,hasBeenUpdated;
	
		$.i18n().load( {
			'en': './resources/i18n/en.json',
			'km': './resources/i18n/km.json'
		}).done( function() { 
			updateMsg=$.i18n('updateMsg');
			hasBeenUpdated=$.i18n('hasBeenUpdated');
			stockRejected=$.i18n('stockRejected');
			stockApproved=$.i18n('stockUploadSuccess');
			stockTxn=$.i18n('stockhaveTxn');	
			errorOccured=$.i18n('errorMsg');
			operationNotAllowed=$.i18n('operationnotallowed');
			stockDeleted=$.i18n('stockDeleted');
			console.log("done"+stockDeleted);
		});


	$(document).ready(function(){
		$('div#initialloader').fadeIn('fast');
			filter(lang);
			sessionStorage.removeItem("session-value");
		pageRendering()
	});
	/*$('li .active a').attr("data-featureid")*/
	$('.datepicker').on('mousedown',function(event){
		event.preventDefault();
	});


	var roleType = $("body").attr("data-roleType");
	var userId = $("body").attr("data-userID");
	var currentRoleType = $("body").attr("data-selected-roleType"); 


	//*******************************************View Pop up data *************************************************************************************************
	function viewUploadedStockDetails(txnId){

		
		 $('#viewStockModal').openModal({
	    	   dismissible:false
	       });
		
		var role = currentRoleType == null ? roleType : currentRoleType;

		$.ajax({
			url : "./openStockPopup?reqType=editPage&txnId="+txnId+'&role='+role,
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			type : 'GET',
			success : function(data) {
				console.log(data)
				setViewPopupData(data);
			},
			error : function() {
				//alert("Failed");
			}
		});
	}


	function setViewPopupData(data){
		var  assigneIdLabel=$.i18n('assigneIdLabel');
		 var assigneNameLabel=$.i18n('assigneNameLabel');
		
 var currentRoleTypeAssignei = $("body").attr("data-selected-roleType"); 
		if(currentRoleTypeAssignei=='Manufacturer')
			{
			
			$("#supplierIdDiv").css("display", "none"); 
			$("#supplierNameDiv").css("display", "none");
			$("#invoiceNumberDiv").css("display", "none");
			}
		else if(currentRoleTypeAssignei=='Custom'){
			$('#SupplierIdLabel').text('');
			$('#SupplierIdLabel').text(assigneIdLabel);
		
			$('#SupplierNameLabel').text('');
			$('#SupplierNameLabel').text(assigneNameLabel);
			
			$("#editSupplierIdDiv").css("display", "block"); 
			$("#editSupplierNameDiv").css("display", "block");
			$("#editSupplierNameDiv").css("display", "block");
		}
		else {
			$("#supplierIdDiv").css("display", "block"); 
			$("#supplierNameDiv").css("display", "block");
			$("#invoiceNumberDiv").css("display", "block");
		}
		$("#SupplierId").val(data.supplierId);
		$("#SupplierName").val(data.suplierName);
		$("#InvoiceNumber").val(data.invoiceNumber);
		$("#Quantity").val(data.quantity);
		$("#TransactionId").val(data.txnId);
		$("#csvUploadFileName").val(data.fileName);
		$("#withdrawnRemark").val(data.remarks);


	}


	//***************************************** end View Pop up data ***********************************************************************************************

	//***************************************** start edit Pop up data ***********************************************************************************************

	function EditUploadedStockDetails(txnId){ 

		var role = currentRoleType == null ? roleType : currentRoleType;

		$.ajax({
			url : "./openStockPopup?reqType=editPage&txnId="+txnId+'&role='+role,
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			type : 'GET',
			success : function(data) {
				console.log(data)
				setEditPopupData(data) 
			},
			error : function() {
				//alert("Failed");
			}
		});

		 $('#editStockModal').openModal({
	    	   dismissible:false
	       });
	}


	function setEditPopupData(data){
		var  assigneIdLabel=$.i18n('assigneIdLabel');
		 var assigneNameLabel=$.i18n('assigneNameLabel');

		console.log()
var currentRoleTypeAssignei = $("body").attr("data-selected-roleType"); 		
		if(currentRoleTypeAssignei=='Manufacturer')
			{
			
			$("#editSupplierIdDiv").css("display", "none"); 
			$("#editSupplierNameDiv").css("display", "none");
			$("#editInvoiceNumberDiv").css("display", "none");
			$("#editSupplierName").attr("required", false);
			}
		else if(currentRoleTypeAssignei=='Custom'){
			$('#editSupplierIdLabel').text('');
			$('#editSupplierIdLabel').text(assigneIdLabel);
		
			$('#editSupplierNameLabel').text('');
			$('#editSupplierNameLabel').text(assigneNameLabel);
			$("#editSupplierIdDiv").css("display", "block"); 
			$("#editSupplierNameDiv").css("display", "block");
			$("#editSupplierNameDiv").css("display", "block");
		}
		$("#editSupplierId").val(data.supplierId);
		$("#editSupplierName").val(data.suplierName);
		$("#editInvoiceNumber").val(data.invoiceNumber);
		$("#editQuantity").val(data.quantity);
		$("#editTransactionId").val(data.txnId);
		$("#editcsvUploadFileName").val(data.fileName);
		$("#existingFileName").val(data.fileName);
		

	} 


	//********************************************************************************************************************************************************
	//******************************************************* update Stock function **************************************************************************

	function editUploadStock(){

		var supplierId=$('#editSupplierId').val();
		var supplierName=$('#editSupplierName').val();
		var filename=$('#editcsvUploadFileName').val();
		var txnId=$('#editTransactionId').val();
		var quantity=$('#editQuantity').val();
		var InvoiceNumber=$('#editInvoiceNumber').val();

		console.log(supplierName,supplierName,filename,txnId,quantity,InvoiceNumber);

		var formData= new FormData();
		formData.append('file', $('#editcsvUploadFile')[0].files[0]);
		formData.append('supplierId',supplierId);
		formData.append('supplierName',supplierName);
		formData.append('quantity',quantity);
		formData.append('txnId',txnId);
		formData.append('filename',filename);

		formData.append('invoiceNumber',InvoiceNumber);

		console.log(JSON.stringify(formData));
		console.log("*********");

		$.ajax({
			url: 'updateUploadedStock',
			type: 'POST',
			data: formData,
			processData: false,
			contentType: false,
			success: function (data, textStatus, jqXHR) {

				console.log(data);
				$('#editStockModal').closeModal();
				 $('#successUpdateStockModal').openModal({
			    	   dismissible:false
			       });
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
				}
	//			$('#updateConsignment').modal('open'); 
	//			alert("success");

			},
			error: function (jqXHR, textStatus, errorThrown) {
				console.log("error in ajax")
			}
		});
	return false;
	}



	//************************************************************* End update Stock function *******************************************************


	//**************************************************** Delete Stock Modal *************************************************************************

	function DeleteStockRecord(txnId){
		
		$('#DeleteStockconfirmationModal').openModal({
	    	   dismissible:false
	       });
		$("#stockdeleteTxnId").text(txnId);
	}


	function confirmantiondelete(){
		var role = currentRoleType == null ? roleType : currentRoleType;
		var txnId= $("#stockdeleteTxnId").text();
		var stockRemark= $("#deleteStockremark").val();
		console.log("roleType=="+role+" ==stockRemark=="+stockRemark);
		var obj ={
				"txnId" : txnId,
				"userType":role,
				"remarks":stockRemark
		}

		$.ajax({
			url : "./stockDelete",
			data : JSON.stringify(obj),
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			type : 'POST',
			success : function(data, textStatus, xhr) {
				console.log(data);

				//$("#stockModalText").text(data.message);
				$("#DeleteStockconfirmationModal").closeModal();

				$('#closeDeleteModal').openModal({
			    	   dismissible:false
			       });
				if(data.errorCode == 0){
					$("#stockModalText").text(stockDeleted);
				}
				else{	$("#stockModalText").text(data.message);}
				$("#materialize-lean-overlay-3").css("display","none");
			},
			error : function() {
				console.log("Error");
			}
		});
		return false;
		/* 
	$(".lean-overlay").remove(); */ 

	}






	/* function closeDeleteModal(){
	$("#DeleteStockconfirmationModal").closeModal();
	// $('#updateModal').closeModal();
	$(".lean-overlay").remove();
	} */

	function closeViewModal()
	{
		$('#viewStockModal').closeModal();
		$(".lean-overlay").remove();

	}

	function closeEditModal()
	{
		$('#editStockModal').closeModal();
		$(".lean-overlay").remove();

	}

	var sourceType =localStorage.getItem("sourceType");
	var currentRoleType = $("body").attr("data-selected-roleType"); 
	//alert("sourceType<><><><>"+sourceType);
	//console.log("currentRoleType<><><><>"+currentRoleType);
	function filter(lang){
		if((currentRoleType=="Importer" || currentRoleType=="Retailer" || currentRoleType=="Distributor" || currentRoleType=="Manufacturer") && sourceType !="viaStock" ){
		Datatable('headers?lang='+lang+'&type=stockHeaders','stockData');
		}else if(currentRoleType=="Custom" && sourceType !="viaStock"){
		Datatable('./headers?lang='+lang+'&type=customStockHeaders','stockData')
		}else if(currentRoleType=="CEIRAdmin" && sourceType !="viaStock"){
		Datatable('./headers?lang='+lang+'&type=adminStockHeaders','stockData')
		}else if((currentRoleType=="Importer"|| currentRoleType=="Retailer" || currentRoleType=="Distributor" || currentRoleType=="Custom") && sourceType =="viaStock"){
		Datatable('./headers?lang='+lang+'&type=stockcheckHeaders','stockData?sourceType=viaStock')
		}
		localStorage.removeItem('sourceType');
	}



	var role = currentRoleType == null ? roleType : currentRoleType;
	var featureId="4";
	var userTypeId = $("body").attr("data-userTypeID");




	function Datatable(url,dataUrl) {
		var txn= (txnIdValue == 'null' && transactionIDValue == undefined)? $('#transactionID').val() : transactionIDValue;
		var jsonObj = {
				"endDate":$('#endDate').val(),
				"startDate":$('#startDate').val(),
				"roleType": role,
				"userId": userId,
				"userType" : role,
				"featureId":featureId,
				"userTypeId":$("body").attr("data-userTypeID"),
				"txnId":txn,
				"consignmentStatus":parseInt($('#StockStatus').val())
		}
		if(lang=='km'){
			var langFile='./resources/i18n/khmer_datatable.json';
		}

		$.ajax({
			url: url,
			type: 'POST',
			dataType: "json",
			success: function(result){
				var table=	$("#stockTable").removeAttr('width').DataTable({
					bAutoWidth: false,
					destroy:true,
					"serverSide": true,
					orderCellsTop : true,
					"ordering": false,
					"bPaginate" : true,
					"bFilter" : true,
					"bInfo" : true,
					"bSearchable" : true,
					scrollCollapse: true,
					"oLanguage": {  
						"sUrl": langFile  
					},	
					ajax: {
						type: 'POST',
						url: dataUrl, 
						data : function(d) {
							d.filter = JSON.stringify(jsonObj); 
							console.log(JSON.stringify(jsonObj));
						}
					},
					"columns": result,
					fixedColumns: true,
					columnDefs: [
						{ width: 240, targets: result.length - 1}
					]
			
				});
				$('div#initialloader').delay(300).fadeOut('slow');
			}
		}); 
	}	


	function pageRendering(){
		console.log("sourceType in render check" +sourceType);
		if(sourceType !="viaStock" ){
			pageButtons('./stock/pageRendering');

		}else if(sourceType ==="viaStock" ){
			pageButtons('./stock/pageRendering?sourceType=viaStock');
		}
		localStorage.removeItem('sourceType');

	}


	function pageButtons(url){ 
		$.ajax({
			url: url,
			type: 'POST',
			dataType: "json",
			success: function(data){
				var elem='<p class="PageHeading">'+data.pageTitle+'</p>';
				$("#pageHeader").append(elem);
				var button=data.buttonList;

				var date=data.inputTypeDateList;
				for(i=0; i<date.length; i++){
					if(date[i].type === "date"){
						$("#consignmentTableDIv").append("<div class='input-field col s6 m2'>"+
								"<div id='enddatepicker' class='input-group date'>"+
								"<input class='form-control datepicker' onchange='checkDate(startDate,endDate)' type='text' id="+date[i].id+" autocomplete='off'>"+
								"<label for="+date[i].id+">"+date[i].title
								+"</label>"+
								"<span	class='input-group-addon' style='color: #ff4081'>"+
								"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");
					} 
					else if(date[i].type === "text"){
						$("#consignmentTableDIv").append("<div class='input-field col s6 m2' ><input type="+date[i].type+" id="+date[i].id+" maxlength='19' /><label for="+date[i].id+" class='center-align'>"+date[i].title+"</label></div>");

					}
				}
	//			dynamic dropdown portion
				var dropdown=data.dropdownList;
				for(i=0; i<dropdown.length; i++){
					var dropdownDiv=
						$("#consignmentTableDIv").append("<div class='col s6 m2 l2 selectDropdwn'>"+
								
								"<div class='select-wrapper select2  initialized'>"+
								"<span class='caret'>"+"</span>"+
								"<input type='text' class='select-dropdown' readonly='true' data-activates='select-options-1023d34c-eac1-aa22-06a1-e420fcc55868' value='Consignment Status'>"+

								"<select id="+dropdown[i].id+" class='select-wrapper select2  initialized'>"+
								"<option>"+dropdown[i].title+
								"</option>"+
								"</select>"+
								"</div>"+
						"</div>");
				}
							
				if(sourceType=="viaStock"){
					$("#btnLink").css({display: "none"});
					$("#consignmentTableDIv").append("<div class=' col s3 m2 l1'><button type='button' class='btn primary botton' id='submitFilter' /></div>");
					$("#consignmentTableDIv").append("<div class=' col s3 m2 l1'><a href='JavaScript:void(0)' type='button' class='export-to-excel right' onclick='exportStockData()'>"+$.i18n('Export')+" <i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");
					for(i=0; i<button.length; i++){
						$('#'+button[i].id).text(button[i].buttonTitle);
						$('#'+button[i].id).attr("href", button[i].buttonURL);
					}
					
					
					
					$("#footerBtn").append("<div class='col s12 m2 l2'><button class='btn' id='markedstolen' style='margin-left:38%;margin-top: 8px;'></button><button class='btn' id='cancel' style='margin-left: 22px;margin-top: 8px;'></button></div>");
					for(i=0; i<button.length; i++){
						$('#'+button[i].id).text(button[i].buttonTitle);
						$('#'+button[i].id).attr("onclick", button[i].buttonURL);
					}

				}else{

					
					$("#consignmentTableDIv").append("<div class=' col s3 m2 l1'><button type='button' class='btn primary botton' id='submitFilter' /></div>");
					$("#consignmentTableDIv").append("<div class=' col s3 m2 l1'><a href='JavaScript:void(0)' type='button' class='export-to-excel right' onclick='exportStockData()'>"+$.i18n('Export')+"<i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");
					for(i=0; i<button.length; i++){
						$('#'+button[i].id).text(button[i].buttonTitle);
						/*$('#'+button[i].id).attr("onclick", button[i].buttonURL);*/

						if(button[i].type === "HeaderButton"){
							$('#'+button[i].id).attr("href", button[i].buttonURL);
						}
						else{
							$('#'+button[i].id).attr("onclick", button[i].buttonURL);
						}
					}
				}

				sourceType=="viaStock"? $("#btnLink").css({display: "none"}) : $("#btnLink").css({display: "block"});
			
				if(currentRoleType=="CEIRAdmin" || (period == 'POST_GRACE' && role=='Importer')){
					$("#btnLink").css({display: "none"});
				}
				
			
				$('.datepicker').datepicker({
					dateFormat: "yy-mm-dd"
					});
			}

	//	$("#filterBtnDiv").append();
		});
		setAllDropdowns(); 
	}


	function valuesPush(){
		var multipleMarkedRequest=[];
		var roleType = $("body").attr("data-roleType");
		var currentRoleType = $("body").attr("data-stolenselected-roleType"); 
		var role = currentRoleType == null ? roleType : currentRoleType;
		var requestType="0";
		console.log("role++++"+role+"requestType++"+requestType+"currentRoleType="+currentRoleType);
		$('#stockTable tr td input:checkbox:checked').each(function() {

			var json={"txnId":$(this).closest('tr').find('td:eq(2)').text(),
					"userId":userId,
					"sourceType":'1',
					"roleType":role,
					"requestType":requestType
			};

			multipleMarkedRequest.push(json);
		});
		console.log(multipleMarkedRequest)
		return multipleMarkedRequest;
	}



	function markedstolen(){
		//$('#markAsMultipleStolen').openModal();
		$('#markAsMultipleStolen').openModal({
	    	   dismissible:false
	       });

	}

	function openMulipleStolenPopUp()
	{

		var stolenRecoverydata=JSON.stringify(valuesPush());
		console.log("release-------"+stolenRecoverydata);
		$.ajax({
			url: './multipleStolenRecovery',
			type: 'POST',
			data: stolenRecoverydata,
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success: function (data, textStatus, jqXHR) {

				console.log(data);
				$('#markAsStolenDone').openModal();
			},
			error: function (jqXHR, textStatus, errorThrown) {
				console.log("error in ajax");
			}
		});

	}

	function redirectToViewPage()
	{

		var roleType = $("body").attr("data-roleType");
		var userId = $("body").attr("data-userID");
		var currentRoleType = $("body").attr("data-selected-roleType"); 
		var role = currentRoleType == null ? roleType : currentRoleType;
		console.log(" userId="+userId+" role="+role);
		console.log("./stolenRecovery?userTypeId=="+role);
		window.location.href = "./stolenRecovery?userTypeId="+role;


	}

	function setAllDropdowns(){
		$.getJSON('./getDropdownList/'+featureId+'/'+$("body").attr("data-userTypeID"), function(data) {
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].state).text(data[i].interp)
				.appendTo('#StockStatus');

			}
		});
	}

	function ApproveStock(txnId)
	{
		var userType=$("body").attr("data-roleType");
		if(userType=='Custom')
			{
			//$('#ApproveStock').openModal();
			$('#ApproveStock').openModal({
		    	   dismissible:false
		       });

			$('#approveStockTxnId').text(txnId);
			
			}
		else {
			//$('#ApproveStock').openModal();
			$('#ApproveStock').openModal({
		    	   dismissible:false
		       });
			$('#stockApproveMessage').text('');
			$('#stockApproveMessage').text(stockTxn+" "+txnId);
			$('#stockAppapprove').text('');
		
		}
		
		$('#approveStockTransactionId').val(txnId);
		
	}

	function approveStockSubmit(actiontype){
		var txnId=$('#approveStockTransactionId').val();
		console.log("txnId==="+txnId);
		var approveRequest={
				"action": actiontype,
				"txnId":txnId,
				"featureId":4
		}
		console.log(JSON.stringify(approveRequest))
		$.ajax({
			url : "./acceptRejectStockController",
			data : JSON.stringify(approveRequest),
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			type : 'POST',
			success : function(data) {
				$('#ApproveStock').closeModal();
				//$('#confirmApproveStockModal').openModal();
				$('#confirmApproveStockModal').openModal({
			    	   dismissible:false
			       });
				console.log(data);
				if(data.errorCode==0){

					$('#stockApproveSucessMessage').text('');
					$('#stockApproveSucessMessage').text(stockApproved);
				}
				else{
					$('#stockApproveSucessMessage').text('');
					$('#stockApproveSucessMessage').text(data.message);
				}
			},
			error : function() {
				
			}
		});
	}

	function disApproveStock(txnId)
	{

		//$('#RejectStockModal').openModal();
		$('#RejectStockModal').openModal({
	    	   dismissible:false
	       });
		$('#disaproveTxnId').text(txnId);
		


	}

	function disApproveStockSubmit(actiontype){
		var txnId=$('#disaproveTxnId').text();
		var Remark=$('#stockDispproveRemarks').val();
	console.log("txnId =="+txnId+" Remark="+Remark );
		var approveRequest={
				"action": actiontype,
				"txnId":txnId,
				"remarks":Remark,
				"featureId":4
		}
		$.ajax({
			url : "./acceptRejectStockController",
			data : JSON.stringify(approveRequest),
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			type : 'POST',
			success : function(data) {
				
				//setTimeout(function(){ $('#confirmRejectStock').openModal()}, 200);
				$('#RejectStockModal').closeModal();
				//$('#confirmRejectStock').openModal();
				$('#confirmRejectStock').openModal({
			    	   dismissible:false
			       });
				if(data.errorCode==0){

					$('#stockDisapproveSucessMessage').text('');
					$('#stockDisapproveSucessMessage').text(stockRejected);
				}
				else{
					$('#stockDisapproveSucessMessage').text('');
					$('#stockDisapproveSucessMessage').text(data.message);
				}
			},
			error : function() {
				
			}
		});
	}



	var role = currentRoleType == null ? roleType : currentRoleType;

//**********************************************************Export Excel file************************************************************************
	function exportStockData()
	{
		var stockStartDate=$('#startDate').val();
		var stockEndDate=$('#endDate').val();
		var stockTxnId=$('#transactionID').val();
		var StockStatus=parseInt($('#StockStatus').val());
		var roleType = role;
		var currentRoleType = $("body").attr("data-stolenselected-roleType");	
		var userType = role;
		
		//var userTypeId = $("body").attr("data-userTypeID");
		var selectedRoleTypeId=$("body").attr("data-selectedRoleTypeId");
		//alert("selectedRoleTypeId="+selectedRoleTypeId);
		//var currentSelectedRoleType=selectedRoleTypeId == null ? selectedRoleTypeId : userTypeId;
		
		//console.log("userType--->"+userType+"-------------userTypeId------------>"+userTypeId);
		//console.log("roleType=="+roleType+" currentRoleType="+currentRoleType+" role="+role);
	
		if(isNaN(StockStatus))
		{
			StockStatus='';
		console.log(" StockStatus=="+StockStatus);
		}
	
		var table = $('#stockTable').DataTable();
		var info = table.page.info(); 
		var pageNo=info.page;
		var pageSize =info.length;
		console.log("--------"+pageSize+"---------"+pageNo);
		console.log("stockStartDate  ="+stockStartDate+"  stockEndDate=="+stockEndDate+"  stockTxnId="+stockTxnId+" StockStatus ="+StockStatus+" roleType="+$("body").attr("data-roleType")+"  userType="+role);
		window.location.href="./exportStock?stockStartDate="+stockStartDate+"&stockEndDate="+stockEndDate+"&stockTxnId="+stockTxnId+"&StockStatus="+StockStatus+"&userType="+userType+"&userTypeId="+selectedRoleTypeId+"&pageSize="+pageSize+"&pageNo="+pageNo+"&roleType="+roleType;
	}
	
	function fileTypeValueChanges() {
		var uploadedFileName = $("#editcsvUploadFile").val();
		uploadedFileName = uploadedFileName.replace(/^.*[\\\/]/, '');
		var ext = uploadedFileName.split('.').pop();
	
		var fileSize = ($("#editcsvUploadFile")[0].files[0].size);
		fileSize = (Math.round((fileSize / 1024) * 100) / 100)
	   if (uploadedFileName.length > 30) {
	     //  $('#fileFormateModal').openModal();
	       $('#fileFormateModal').openModal({
	    	   dismissible:false
	       });
	      
	   } 
		else if(ext!='csv')
			{
			  $('#fileFormateModal').openModal({
		    	   dismissible:false
		       }); 
			}
		else if(fileSize>='2000'){
			  $('#fileFormateModal').openModal({
		    	   dismissible:false
		       });
		}
		else {
			console.log("file formate is correct")
			
		}
	}

	function clearFileName() {
		var existingfile=$("#existingFileName").val();
		//$('#fileNameEdit').val('');
		$("#editcsvUploadFile").val('');
		$('#fileFormateModal').closeModal();
		
		$("#editcsvUploadFileName").val(existingfile);
	}

	$("input[type=file]").keypress(function(ev) {
	    return false;
	    //ev.preventDefault(); //works as well

	});
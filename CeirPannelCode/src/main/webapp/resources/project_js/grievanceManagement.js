
var featureId = 6;
			var cierRoletype = sessionStorage.getItem("cierRoletype");

			var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
		/*		window.parent.$('#langlist').on('change', function() {
					var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
				window.location.assign("./grievanceManagement?lang="+lang);
			
				});*/ 


				$.i18n().locale = lang;
				var documenttype,selectfile,selectDocumentType;
				$.i18n().load( {
					'en': './resources/i18n/en.json',
					'km': './resources/i18n/km.json'
				} ).done( function() { 
						documenttype=$.i18n('documenttype');
						selectfile=$.i18n('selectfile');
						selectDocumentType=$.i18n('selectDocumentType');
				
				});



			$(document).ready(function(){
				$('div#initialloader').fadeIn('fast');
				grievanceDataTable(lang);
				pageRendering();
			});


			var startdate=$('#startDate').val(); 
			var endDate=$('#endDate').val();
			var taxStatus=$('#taxPaidStatus').val();
			var consignmentStatus=$('#filterConsignmentStatus').val();
			var userId = $("body").attr("data-userID");

			/*localStorage.setItem("grievancePageSource", "viaGriebva");*/




			//**************************************************Grievance table**********************************************

			function grievanceDataTable(lang){
				var txn= (txnIdValue == 'null' && transactionIDValue == undefined)? $('#grievanceID').val() : transactionIDValue;
	
				var grievancePageSource =localStorage.getItem("grievancePageSource");
				var grievanceSessionUsesFlag;
				var grievanceStatus=$('#recentStatus').val();
				if(grievanceStatus==null)
				{
					grievanceStatus=-1;
				}
				else{
					grievanceStatus=$('#recentStatus').val();
				}
				if(grievancePageSource=="viaDashBoard")
				{
					grievanceSessionUsesFlag=1;
				}
				else{
					grievanceSessionUsesFlag=0;
				}
				localStorage.removeItem('grievancePageSource');
			
				var filterRequest={
						"grievanceStatus":grievanceStatus,
						"endDate":$('#endDate').val(),
						"startDate":$('#startDate').val(),
						"recentStatus":parseInt($('#recentStatus').val()),
						/*"userId": parseInt($("body").attr("data-userTypeID") == 8 ? 0 : parseInt(userId)),*/
						"userId": parseInt($("body").attr("data-userID")),
						"featureId":parseInt(featureId),
						"userTypeId": parseInt($("body").attr("data-userTypeID")),
						"txnId":  $('#transactionID').val(),
						"grievanceId":txn,
						"userType":$("body").attr("data-roleType"),

				}

				if(lang=='km'){
						var langFile="//cdn.datatables.net/plug-ins/1.10.20/i18n/Khmer.json";
					}

				$.ajax({
					url: 'headers?type=grievanceHeaders&lang='+lang,
					type: 'POST',
					dataType: "json",
					success: function(result){
						var table=	$("#grivanceLibraryTable").DataTable({
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
								url : 'grievanceData?grievanceSessionUsesFlag='+grievanceSessionUsesFlag,
								type: 'POST',
								dataType: "json",
								data : function(d) {
									d.filter = JSON.stringify(filterRequest); 
									console.log(JSON.stringify(filterRequest));
								}

							},
							"columns": result
						});
						$('div#initialloader').delay(300).fadeOut('slow');
						$('#grivanceLibraryTable input').unbind();
						$('#grivanceLibraryTable input').bind('keyup', function (e) {
							if (e.keyCode == 13) {
								table.search(this.value).draw();
							}

						});
					},
					error: function (jqXHR, textStatus, errorThrown) {
						console.log("error in ajax");
					}
				});
			}



			//**************************************************Grievance page buttons**********************************************

			function pageRendering(){
				$.ajax({
					url: 'grievance/pageRendering',
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
								$("#greivanceTableDiv").append("<div class='input-field col s6 m2'>"+
										"<div id='enddatepicker' class='input-group date'>"+
										"<input class='form-control datepicker' type='text' onchange='checkDate(startDate,endDate)' id="+date[i].id+" autocomplete='off'>"+
										"<label for="+date[i].id+">"+date[i].title
										+"</label>"+
										"<span	class='input-group-addon' style='color: #ff4081'>"+
										"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");
							}
							else if(date[i].type === "text"){
								$("#greivanceTableDiv").append("<div class='input-field col s6 m2'><input type="+date[i].type+" id="+date[i].id+" maxlength='19' /><label for="+date[i].id+" class='center-align'>"+date[i].title+"</label></div>");

							}



						} 

						// dynamic dropdown portion
						var dropdown=data.dropdownList;
						for(i=0; i<dropdown.length; i++){
							var dropdownDiv=
								$("#greivanceTableDiv").append("<div class='col s6 m2 l2 selectDropdwn'>"+
										
										"<div class='select-wrapper select2 form-control boxBorder boxHeight initialized'>"+
										"<span class='caret'>"+"</span>"+
										"<input type='text' class='select-dropdown' readonly='true' data-activates='select-options-1023d34c-eac1-aa22-06a1-e420fcc55868' value='Consignment Status'>"+

										"<select id="+dropdown[i].id+"  class='select-wrapper select2  initialized'>"+
										"<option value='-1'>"+dropdown[i].title+
										"</option>"+
										"</select>"+
										"</div>"+
								"</div>");
						}

						$("#greivanceTableDiv").append("<div class=' col s3 m2 l1'><button type='button' class='btn primary botton' id='submitFilter'></div>");
						$("#greivanceTableDiv").append("<div class=' col s3 m2 l1'><a href='JavaScript:void(0)' type='button' class='export-to-excel right'  onclick='exportData()'>"+$.i18n('Export')+"<i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");

						for(i=0; i<button.length; i++){
							$('#'+button[i].id).text(button[i].buttonTitle);
							if(button[i].type === "HeaderButton"){
								$('#'+button[i].id).attr("href", button[i].buttonURL);
							}
							else{
								$('#'+button[i].id).attr("onclick", button[i].buttonURL);
							}
						}

						if(cierRoletype=="CEIRAdmin"){
							$("#btnLink").css({display: "none"});
						}

						$.getJSON('./getDropdownList/'+featureId+'/'+$("body").attr("data-userTypeID"), function(data) {
							for (i = 0; i < data.length; i++) {
								$('<option>').val(data[i].state).text(data[i].interp)
								.appendTo('#recentStatus');
							}


							var grievanceStatus= $("body").attr("data-grievanceStatus");
							
							if(grievanceStatus=="")
							{
								

							}
							else{
								
								$("#recentStatus").val(grievanceStatus).change();

}
						});

						var grievanceId = $("body").attr("data-grievanceId");
						var txnid = $("body").attr("data-grievanceTxnId");

						$('#transactionID').val(txnid);
						$('#grievanceID').val(grievanceId);
						if(txnid=="" )
						{
						}
						else{
						$('label[for=TransactionID]').remove();

						}

						//cierRoletype=="Importer"? $("#btnLink").css({display: "block"}) : $("#btnLink").css({display: "none"});
						/*sourceType=="viaStolen" ? $("#btnLink").css({display: "none"}) : $("#btnLink").css({display: "none"});*/
						$('.datepicker').datepicker({
							dateFormat: "yy-mm-dd"
						});
					}

				}); 
			};




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
				
				if(Date.parse(today)>Date.parse(dispatcDate))
				{
					myFunction("dispatche date should be greater then or equals to today");
					$('#expectedDispatcheDate').val("");
				}
}

			function arrivalDateValidation(){
				var currentDate;
				var dispatcDate=  $('#expectedArrivalDate').val();
				var now=new Date();
				if(now.getDate().toString().charAt(0) != '0'){
					currentDate='0'+now.getDate();

				}
				else{
					currentDate=now.getDate();
				}
				var today = now.getFullYear()+ '-' + (now.getMonth()+1)+ '-' +currentDate ;
				if(Date.parse(today)>Date.parse(dispatcDate))
				
							{
					myFunction("Arrival date should be greater then or equals to today");
					$('#expectedArrivalDate').val("");
				}
			}

			$('.datepicker').on('mousedown',function(event){
				event.preventDefault();
			});



			


			function grievanceReply(userId,grievanceId,txnId)
			{
   

				$.ajax({
					url: './viewGrievance?recordLimit=2&grievanceId='+grievanceId,
					type: 'GET',
					processData: false,
					contentType: false,
					success: function (data, textStatus, jqXHR) {
						//$('#replyModal').openModal();
						$('#replyModal').openModal({
						 	   dismissible:false
						    });
						//console.log(data.grievance.categoryId)
						//alert("11"+data.categoryId)
						setDocTypeValue(data[0].grievance.categoryId);
						$('#existingGrievanceID').val(data[0].grievance.categoryId);
					/*	alert("22");*/
						$('#grievanceIdToSave').text(grievanceId);
						$('#grievanceTxnId').text(txnId);
						$('#grievanceUserid').val(userId);
						var usertype = $("body").attr("data-roleType");
						var projectpath=path+"/Consignment/dowloadFiles/actual";
						$("#viewPreviousMessage").empty();
						for(var i=0; i<data.length; ++i)
						{

							$("#viewPreviousMessage").append("<div class='chat-message-content clearfix'><h6 style='float: left; font-weight: bold;' class='grievance-reply-msg' id='mesageUserType'>" +data[i].userDisplayName+" : </h6><span style='float:right;'>" + data[i].modifiedOn + "</span><h6>" + data[i].reply + "</h6></div>");
							for (var j=0 ; j<data[i].attachedFiles.length;j++)
							{
								if(data[i].attachedFiles[j].docType==null)
									{
									
									$("#viewPreviousMessage").append("<div class='chat-message-content clearfix'><a href='"+projectpath+"/"+data[i].attachedFiles[j].fileName+"/"+data[i].attachedFiles[j].grievanceId+"/"+data[i].attachedFiles[j].docType+"'>"+data[i].attachedFiles[j].fileName+"</a></div>");
									}
								else{
								//alert(data[i].attachedFiles[j].docType);
								if(data[i].attachedFiles[j].docType=="")
									{
									//$("#chatMsg").append("<div class='chat-message-content clearfix'> <span class='document-Type' ><b>Document Type : </b>"+data[i].attachedFiles[j].docType+"</span> <a href='"+projectpath+"/"+data[i].attachedFiles[j].fileName+"/"+data[i].attachedFiles[j].grievanceId+"/"+data[i].attachedFiles[j].docType+"'>"+data[i].attachedFiles[j].fileName+"</a></div>");
									}
								else{
									
									
									fileName=data[i].attachedFiles[j].fileName.split(' ').join('%20');
									$("#viewPreviousMessage").append("<div class='chat-message-content clearfix'> <span class='document-Type' ><b>Document Type : </b>"+data[i].attachedFiles[j].docType+"</span> <a onclick=fileDownload('"+fileName+"','actual','"+data[i].attachedFiles[j].grievanceId+"','"+data[i].attachedFiles[j].docType+"') >"+data[i].attachedFiles[j].fileName+"</a></div>");
								}
								}
								
								
							}	

						}
						if(usertype=='CEIRAdmin')
						{
							$("#closeTicketCheckbox").css("display","block");
						}
						else{
							$("#closeTicketCheckbox").css("display","none");	
						}


					},
					error: function (jqXHR, textStatus, errorThrown) {
						console.log("error in ajax")
					}
				});
			}

			function saveGrievanceReply()
			{
			
				var grievanceTicketStatus;
				if ($('#closeTicketCheck').is(":checked"))
				{
					grievanceTicketStatus=3;

				}
				else{
					grievanceTicketStatus=0;
				}
			
				 var roleType = $("body").attr("data-roleType");
				 var userId ='';
				 
				 if (roleType==undefined)
				 {
					 roleType="End User";
					 userId=$('#grievanceUserid').val();
				 }
				 else{
					 userId = $("body").attr("data-userID");
				 }
				var remark=$('#replyRemark').val();
				var replyFile=$('#replyFile').val();
				var  grievanceIdToSave= $('#grievanceIdToSave').text();
				var  grievanceTxnId=  $('#grievanceTxnId').text();

				var fieldId=1;
				var fileInfo =[];
				var formData= new FormData();
				var fileData = [];
				var documentFileName='';
				var documentFileNameArray=[];
				var x;
				var filename='';
				var filediv;
				var i=0;
				var formData= new FormData();
				var docTypeTagIdValue='';
				var filename='';
				var filesameStatus=false;
				var documenttype=false;
				var docTypeTag='';
				$('.fileDiv').each(function() {	
					var x={
					"docType":$('#docTypetag'+fieldId).val(),
					"fileName":$('#docTypeFile'+fieldId).val().replace('C:\\fakepath\\',''),
					"grievanceId":$('#grievanceIdToSave').text()
					}
					formData.append('files[]',$('#docTypeFile'+fieldId)[0].files[0]);
					fileInfo.push(x);
					
					documentFileName=$('#docTypeFile'+fieldId).val().replace('C:\\fakepath\\','')
					docTypeTag=$('#docTypetag'+fieldId).val();
					
					var fileIsSame=	documentFileNameArray.includes(documentFileName);
					var documentTypeTag=documentFileNameArray.includes(docTypeTag);
					
					if(filesameStatus!=true){
						filesameStatus=	fileIsSame;
					}
					
					if(documenttype!=true)
					{
					documenttype=documentTypeTag;
			         }
					documentFileNameArray.push(documentFileName);
					documentFileNameArray.push(docTypeTag);
					
					fieldId++;
					i++;
				});
				if(filesameStatus==true)
				{	
				
			///	$('#fileFormateModal').openModal();
				$('#fileFormateModal').openModal({
				 	   dismissible:false
				    });
					$('#fileErrormessage').text('')
					$('#fileErrormessage').text($.i18n('duplicateFileName'));
				return false;
				
				}
				if(documenttype==true)
				{	
					$('#fileFormateModal').openModal({
					 	   dismissible:false
					    });
					$('#fileErrormessage').text('')
					$('#fileErrormessage').text($.i18n('documentTypeName'));
				return false;
				
				}
			
				var multirequest={
						"attachedFiles":fileInfo,
						"txnId":grievanceTxnId,
						"reply":remark,
						"grievanceId":grievanceIdToSave,
						"grievanceStatus":grievanceTicketStatus,
						"featureId":6,
						"userId":userId,
						"userType":roleType
					
					}

				formData.append('fileInfo[]',JSON.stringify(fileInfo));
				formData.append('multirequest',JSON.stringify(multirequest));
				
				/*
				formData.append('file', $('#replyFile')[0].files[0]);
				formData.append('remark',remark);
				formData.append('grievanceId',grievanceIdToSave);
				formData.append('txnId',grievanceTxnId);
				formData.append('grievanceStatus',grievanceTicketStatus);*/

				$.ajax({
					url: './saveGrievanceMessage',
					type: 'POST',
					data: formData,
					processData: false,
					contentType: false,
					success: function (data, textStatus, jqXHR) {

					//	$('#replyMsg').openModal();
						$('#replyMsg').openModal({
						 	   dismissible:false
						    });
						if(data.errorCode=="0")
						{
							
							$('#replyGrievanceId').text(data.txnId);

						}
						else 
						{
							$('#showReplyResponse').text('');
							$('#showReplyResponse').text(data.message);
						}
						
					},
					error: function (jqXHR, textStatus, errorThrown) {
						console.log("error in ajax")
					}
				});
				return false;
			}


			function viewGrievanceHistory(grievanceId,projectPath)
			{


			$.ajax({
					url: './viewGrievance?recordLimit=-1&grievanceId='+grievanceId,
					type: 'GET',
					processData: false,
					contentType: false,
					success: function (data, textStatus, jqXHR) {
						var grievanceID=	
						$('#chatMsg').empty();
						//$('#manageAccount').openModal();
						$('#manageAccount').openModal({
						 	   dismissible:false
						    });
						
						var projectpath=path+"/Consignment/dowloadFiles/actual";
							for(var i=0; i<data.length; i++)
						{
								$('#viewGrievanceId').text('');	
							$('#viewGrievanceId').text(grievanceId);	
							
							$("#chatMsg").append("<div class='chat-message-content clearfix'><span class='chat-time' id='timeHistory'>"+data[i].modifiedOn+"</span><h5 id='userTypehistory'>"+data[i].userDisplayName+"</h5><p id='messageHistory'>"+data[i].reply+"</p></div>");
								for (var j=0 ; j<data[i].attachedFiles.length;j++)
								{
									if(data[i].attachedFiles[j].docType==null)
										{
										
										$("#chatMsg").append("<div class='chat-message-content clearfix'><a href='"+projectpath+"/"+data[i].attachedFiles[j].fileName+"/"+data[i].attachedFiles[j].grievanceId+"/"+data[i].attachedFiles[j].docType+"'>"+data[i].attachedFiles[j].fileName+"</a></div>");
										}
									else{
									//alert(data[i].attachedFiles[j].docType);
									if(data[i].attachedFiles[j].docType=="")
										{
										//$("#chatMsg").append("<div class='chat-message-content clearfix'> <span class='document-Type' ><b>Document Type : </b>"+data[i].attachedFiles[j].docType+"</span> <a href='"+projectpath+"/"+data[i].attachedFiles[j].fileName+"/"+data[i].attachedFiles[j].grievanceId+"/"+data[i].attachedFiles[j].docType+"'>"+data[i].attachedFiles[j].fileName+"</a></div>");
										}
									else{
										
										fileName=data[i].attachedFiles[j].fileName.split(' ').join('%20');
										$("#chatMsg").append("<div class='chat-message-content clearfix'> <span class='document-Type' ><b>Document Type : </b>"+data[i].attachedFiles[j].docType+"</span> <a onclick=fileDownload('"+fileName+"','actual','"+data[i].attachedFiles[j].grievanceId+"','"+data[i].attachedFiles[j].docType+"')>"+data[i].attachedFiles[j].fileName+"</a></div>");
									}
									}
									
									
								}
								$("#chatMsg").append("<div class='chat-message-content clearfix'><hr></div>");
						
						}
					},
					error: function (jqXHR, textStatus, errorThrown) {
						console.log("error in ajax")
					}
				});
			}






			//**********************************************************Export Excel file************************************************************************
			function exportData()
			{
				var grievanceStartDate=$('#startDate').val();
				var grievanceEndDate=$('#endDate').val();
				var grievancetxnId=$('#transactionID').val();
				var grievanceId=$('#grievanceID').val();
				var grievanceStatus=$('#recentStatus').val();

				var table = $('#grivanceLibraryTable').DataTable();
				var info = table.page.info(); 
				var pageNo=info.page;
				var pageSize =info.length;
				window.location.href="./exportGrievance?grievanceStartDate="+grievanceStartDate+"&grievanceEndDate="+grievanceEndDate+"&grievancetxnId="+grievancetxnId+"&grievanceId="+grievanceId+"&grievanceStatus="+grievanceStatus+"&pageSize="+pageSize+"&pageNo="+pageNo;
			}

			//************************************************ category dropdown function ******************************************************************
			
			$(document).ready(function(){
				$.getJSON('./getDropdownList/DOC_TYPE', function(data) {
					for (i = 0; i < data.length; i++) {
						$('<option>').val(data[i].tagId).text(data[i].interp).appendTo('#docTypetag1');
						$('#docTypetagValue1').val(data[i].value);
					}
				});
			});




			function cleanReplyPopUp()
			{
				$('#replymessageForm').trigger("reset");
				$('.fileDiv12').remove();
			}




			$.getJSON('./addMoreFile/more_files_count', function(data) {
				console.log(data);
				
				localStorage.setItem("maxCount", data.value);
				
			});
		 
				//var max_fields = 2; //maximum input boxes allowed
				var max_fields =localStorage.getItem("maxCount");
				

			//var max_fields = 15; //maximum input boxes allowed
			var wrapper = $(".mainDiv"); //Fields wrapper
			var add_button = $(".add_field_button"); //Add button ID
			var x = 1; //initlal text box count
			var id=2;
			$(".add_field_button").click(function (e) { //on add input button click
				e.preventDefault();
						
				if (x < max_fields) { //max input box allowed
					x++; //text box increment
					$(wrapper).append(
							'<div id="filediv'+id+'" class="fileDiv"><div class="row"><div class="file-field col s12 m6"><label for="Category">'+documenttype+'</label><select id="docTypetag'+id+'"  class="browser-default"> <option value="" disabled selected>'+selectDocumentType+' </option></select><select id="docTypetagValue'+id+'" style="display:none" class="browser-default"> <option value="" disabled selected>'+selectDocumentType+' </option></select></div><div class="file-field col s12 m6" style="margin-top: 23px;"><div class="btn"><span>'+selectfile+'</span><input id="docTypeFile'+id+'" type="file"  name="files[]" id="filer_input" /></div><div class="file-path-wrapper"><input class="file-path validate" placeholder="'+$.i18n('selectFilePlaceHolder')+'" type="text"></div></div><div style="cursor:pointer;background-color:red;margin-right: 1.7%;" class="remove_field btn right btn-info">-</div></div></div>'
					); //add input box
				}
	               /*$.getJSON('./getDropdownList/DOC_TYPE', function(data) {


					for (i = 0; i < data.length; i++) {
						var optionId=id-1;
						$('<option>').val(data[i].tagId).text(data[i].interp).appendTo('#docTypetag'+optionId);
						$('<option>').val(data[i].value).text(data[i].tagId).appendTo('#docTypetagValue'+optionId);
						
					}
				});*/
				
				var grievanceUserTypeId=parseInt($("body").attr("data-userTypeID"));
				var categoryParentValues= $('#existingGrievanceID').val();
				if(isNaN(grievanceUserTypeId))
					{
					
					grievanceUserTypeId=17;
					categoryParentValues=$('#grievanceSelectedCategory').val();
					}
				
				var request ={
						 "childTag": "DOC_TYPE",
						  "featureId": 6,
						  "parentValue":  parseInt(categoryParentValues),	
						  "tag": "GRIEVANCE_CATEGORY",
						  "userTypeId":grievanceUserTypeId,
					}
			
			console.log("request --->" +JSON.stringify(request));	
			 $.ajax({
					url: './get/tags-mapping',
					type: 'POST',
					data : JSON.stringify(request),
					dataType : 'json',
					contentType : 'application/json; charset=utf-8',
					success: function (data, textStatus, jqXHR) {
						/*$("#docTypetag1").empty();
						$('#docTypetag1').append('<option value="">'+$.i18n('selectDocumentType')+'</option>');*/
						console.log(data);
						for (i = 0; i < data.length; i++){
							var optionId=id-1;
								//var html='<option value="'+data[i].value+'">'+data[i].interp+'</option>';
								//$('#docTypetag1').append(html);	
								
							$('<option>').val(data[i].tagId).text(data[i].interp).appendTo('#docTypetag'+optionId);
						
						}
						
					},
					error: function (jqXHR, textStatus, errorThrown) {
						console.log("error in ajax")
					}
				});
				
				
				id++;
				/*alert("$$$$"+id)*/
			});
			$(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
				e.preventDefault();
				var Iid=id-1;
				/*alert("@@@"+Iid)*/
				$('#filediv'+Iid).remove();
				$(this).parent('div').remove();
				x--;
				id--;
			
			})

			function saveDocTypeValue(){
				$('#docTypetagValue').val(data[i].value).change();
				$('#docTypetagValue').val(data[i].value).change();
			}
			
			
			function setDocTypeValue(categoryParentValue){
			
			var request ={
						 "childTag": "DOC_TYPE",
						  "featureId": 6,
						  "parentValue":  parseInt(categoryParentValue),	
						  "tag": "GRIEVANCE_CATEGORY",
						  "userTypeId": parseInt($("body").attr("data-userTypeID")),
					}
			
			console.log("request --->" +JSON.stringify(request));	
			 $.ajax({
					url: './get/tags-mapping',
					type: 'POST',
					data : JSON.stringify(request),
					dataType : 'json',
					contentType : 'application/json; charset=utf-8',
					success: function (data, textStatus, jqXHR) {
						$("#docTypetag1").empty();
						$('#docTypetag1').append('<option value="">'+$.i18n('selectDocumentType')+'</option>');
						console.log(data);
						for (i = 0; i < data.length; i++){
								//var html='<option value="'+data[i].value+'">'+data[i].interp+'</option>';
								//$('#docTypetag1').append(html);	
								
							$('<option>').val(data[i].tagId).text(data[i].interp).appendTo('#docTypetag1');
						
						}
						
					},
					error: function (jqXHR, textStatus, errorThrown) {
						console.log("error in ajax")
					}
				});
			}
			
			function clearFileName() {
				$('#fileName').val('');
				$("#file").val('');
				$('#fileFormateModal').closeModal();
			}
			function enableAddMore(){
				$(".add_field_button").attr("disabled", false);
			}
			function enableSelectFile(){
				$("#docTypeFile1").attr("disabled", false);
				$("#docTypeFile1").attr("required", true);
				$("#supportingdocumentFile").append('<span class="star">*</span>');
			}
			$("input[type=file]").keypress(function(ev) {
			    return false;
			    //ev.preventDefault(); //works as well

			});
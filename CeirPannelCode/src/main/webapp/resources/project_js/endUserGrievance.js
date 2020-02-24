	var featureId = 6;
	$('#langlist').on('change', function() {
		lang=$('#langlist').val() == 'km' ? 'km' : 'en';
		var url_string = window.location.href;
		var url = new URL(url_string);
		var type = url.searchParams.get("reportType");

		window.location.assign("raiseAgrievance?reportType="+type+"&lang="+lang);			
		}); 

	
	$(document).ready(function () {
		 $('#langlist').val(data_lang_param);
	});
//**************************************************Grievance table**********************************************

			function endUsergrivanceLibraryTable(){
						var filterRequest={
						        "featureId":parseInt(featureId),
						        "grievanceId":$('#trackGrievanceId').val(),
						        "grievanceStatus":-1
				      }
						$('#endUsergrivanceLibraryTable').css("display", "block");
						$('#trackGrievanceDiv').css("display", "none");
						$('#trackGrievanctableDiv').css("display", "block");
						$('#trackGrievanceHeader').css("display", "none");
						
				
				$.ajax({
					url: 'headers?type=grievanceHeaders',
					type: 'POST',
					dataType: "json",
					success: function(result){
						
						/*console.log("Url-------" +url+"--------"+ "dataUrl-------" +dataUrl);*/
						var table=	$("#endUsergrivanceLibraryTable").DataTable({
							destroy:true,
							bAutoWidth: false,
							"serverSide": true,
							orderCellsTop : true,
							"ordering" : false,
							"bPaginate" : true,
							"bFilter" : true,
							"bInfo" : true,
							"bSearchable" : true,
					
							ajax: {
								url : 'grievanceData',
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
					            { width: 137, targets: result.length - 1 },
					            { width: 280, targets: 4 },
					            { width: 155, targets: 2 },
					            
					        ]
						});
						$('div#initialloader').delay(300).fadeOut('slow');
					},
					error: function (jqXHR, textStatus, errorThrown) {
						console.log("error in ajax");
					}
				});
				
				return false;
			}
			

			function endUserviewGrievanceHistory(grievanceId,projectPath,userId)
			{




				console.log(projectPath+path);
				$.ajax({
					url: './endUserViewGrievance?recordLimit=2&grievanceId='+grievanceId+"&userId="+userId,
					type: 'GET',
					processData: false,
					contentType: false,
					success: function (data, textStatus, jqXHR) {

						console.log(JSON.stringify(data));
						$('#chatMsg').empty();
						$('#manageAccount').openModal();
						console.log("****projectPath"+projectPath);
						console.log("+++++path"+path);
						
						var projectpath=path+"/Consignment/dowloadFiles/actual";
						console.log("--projectpath--"+projectpath);
						for(var i=0; i<data.length; i++)
						{
							console.log("iiiiiii"+i);
							$("#chatMsg").append("<div class='chat-message-content clearfix'><span class='chat-time' id='timeHistory'>"+data[i].modifiedOn+"</span><h5 id='userTypehistory'>"+data[i].userDisplayName+"</h5><p id='messageHistory'>"+data[i].reply+"</p></div>");
								for (var j=0 ; j<data[i].attachedFiles.length;j++)
								{
									
									if(data[i].attachedFiles[j].docType==null)
									{
									console.log("if condition file and doctype is empty");		$("#chatMsg").append("<div class='chat-message-content clearfix'><a href='"+projectpath+"/"+data[i].attachedFiles[j].fileName+"/"+data[i].attachedFiles[j].grievanceId+"/"+data[i].attachedFiles[j].docType+"'>"+data[i].attachedFiles[j].fileName+"</a></div>");
									}
								else{
								
									$("#chatMsg").append("<div class='chat-message-content clearfix'> <span class='document-Type' ><b>Document Type : </b>"+data[i].attachedFiles[j].docType+"</span> <a href='"+projectpath+"/"+data[i].attachedFiles[j].fileName+"/"+data[i].attachedFiles[j].grievanceId+"/"+data[i].attachedFiles[j].docType+"'>"+data[i].attachedFiles[j].fileName+"</a></div>");
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


			
			
			function endUserGrievanceReply(userId,grievanceId,txnId)
			{
				

				$.ajax({
					url: './endUserViewGrievance?recordLimit=2&grievanceId='+grievanceId+"&userId="+userId,
					type: 'GET',
					processData: false,
					contentType: false,
					success: function (data, textStatus, jqXHR) {

						console.log(JSON.stringify(data));
						$('#replyModal').openModal();
						$('#grievanceIdToSave').text(grievanceId);
						$('#grievanceTxnId').text(txnId);
						$('#grievanceUserid').val(userId);
						var usertype = $("body").attr("data-roleType");
						console.log("usertype=="+usertype);
						$("#viewPreviousMessage").empty();
						for(var i=0; i<data.length; ++i)
						{

							$("#viewPreviousMessage").append("<div class='chat-message-content clearfix'><h6 style='float: left; font-weight: bold;' id='mesageUserType'>" +data[i].userDisplayName+" : </h6><span style='float:right;'>" + data[i].modifiedOn + "</span><h6>" + data[i].reply + "</h6></div>");


						}
						if(usertype=='CEIRAdmin')
						{
							$("#closeTicketCheckbox").css("display","block");
							console.log("block");
						}
						else{
							$("#closeTicketCheckbox").css("display","none");	
							console.log("none");
						}
						$.getJSON('./getDropdownList/DOC_TYPE', function(data) {
							for (i = 0; i < data.length; i++) {
								console.log(data[i].interp);
								$('<option>').val(data[i].tagId).text(data[i].interp).appendTo('#docTypetag1');
								
							}
						});


					},
					error: function (jqXHR, textStatus, errorThrown) {
						 $('#errorModal').openModal();
					}
				});
			}
			
			
			
			
			function saveEndUserGrievanceReply()
			{
				 var endUseruserId=488;
				var endUsergrievanceTicketStatus;
				if ($('#closeTicketCheck').is(":checked"))
				{
					endUsergrievanceTicketStatus=3;

				}
				else{
					endUsergrievanceTicketStatus=0;
				}
				var endUserremark=$('#replyRemark').val();
				var endUserreplyFile=$('#replyFile').val();
				var  endUsergrievanceIdToSave= $('#grievanceIdToSave').text();
				var  endUsergrievanceTxnId=  $('#grievanceTxnId').text();
				
				//console.log("remark "+remark+"  replyFile="+replyFile+" grievanceTxnId="+grievanceTxnId+"grievanceIdToSave="+grievanceIdToSave+"grievanceTicketStatus=="+grievanceTicketStatus);
				var endUserfieldId=1;
				var endUserfileInfo =[];
				var endUserformData= new FormData();
				var endUserfileData = [];
			
				var endUserx;
				var endUserfilename='';
				var endUserfilediv;
				var endUseri=0;
				var formData= new FormData();
				var endUserdocTypeTagIdValue='';
				var endUserfilename='';
		
				$('.fileDiv').each(function() {	
					var endUserx={
					"docType":$('#docTypetag'+endUserfieldId).val(),
					"fileName":$('#docTypeFile'+endUserfieldId).val().replace('C:\\fakepath\\',''),
					"grievanceId":$('#grievanceIdToSave').text()
					}
					endUserformData.append('files[]',$('#docTypeFile'+endUserfieldId)[0].files[0]);
					endUserfileInfo.push(endUserx);
					endUserfieldId++;
					endUseri++;
				});
				var endUsermultirequest={
						"attachedFiles":endUserfileInfo,
						"txnId":endUsergrievanceTxnId,
						"reply":endUserremark,
						"grievanceId":endUsergrievanceIdToSave,
						"grievanceStatus":endUsergrievanceTicketStatus,
						"featureId":6,
						"userId":endUseruserId
					}
				endUserformData.append('fileInfo[]',JSON.stringify(endUserfileInfo));
				endUserformData.append('multirequest',JSON.stringify(endUsermultirequest));
				
				
				formData.append('file', $('#replyFile')[0].files[0]);
				formData.append('remark',remark);
				formData.append('grievanceId',grievanceIdToSave);
				formData.append('txnId',grievanceTxnId);
				formData.append('grievanceStatus',grievanceTicketStatus);
			
				$.ajax({
					url: './saveEndUserGrievanceReply',
					type: 'POST',
					data: formData,
					processData: false,
					contentType: false,
					success: function (data, textStatus, jqXHR) {
					
						$('#replyMsg').openModal();
						console.log(data.txnId);
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
			
			
			
			function dispatchDateValidation() {
				var currentDate;
				var dispatcDate = $('#expectedDispatcheDate').val();
				var now = new Date();
				if (now.getDate().toString().charAt(0) != '0') {
					currentDate = '0' + now.getDate();

					/* alert("only date="+currentDate); */
				} else {
					currentDate = now.getDate();
				}
				var today = now.getFullYear() + '-' + (now.getMonth() + 1) + '-'
						+ currentDate;
				//alert("today"+today);
				console.log("dispatche=" + dispatcDate);
				console.log("todays parse date" + Date.parse(today));
				console.log("dispatche parse date" + Date.parse(dispatcDate));

				if (Date.parse(today) > Date.parse(dispatcDate)) {
					myFunction("dispatche date should be greater then or equals to today");
					$('#expectedDispatcheDate').val("");
				}

				//alert("current date="+today+" dispatche date="+dispatcDate)
			}
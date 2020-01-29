	var featureId = 6;
  /*  $(document).ready(function(){
				$('div#initialloader').fadeIn('fast');
				grievanceDataTable();
			
			});
*/
//**************************************************Grievance table**********************************************

			function endUsergrivanceLibraryTable(){
						var filterRequest={
						        "featureId":parseInt(featureId),
						        "grievanceId":$('#trackGrievanceId').val(),
				      }
						$('#endUsergrivanceLibraryTable').css("display", "block");
						$('#trackGrievanceDiv').css("display", "none");
				
				$.ajax({
					url: 'headers?type=grievanceHeaders',
					type: 'POST',
					dataType: "json",
					success: function(result){
						/*console.log("Url-------" +url+"--------"+ "dataUrl-------" +dataUrl);*/
						var table=	$("#endUsergrivanceLibraryTable").DataTable({
							destroy:true,
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
							"columns": result
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
									
									console.log("jjjjjj"+j);
									$("#chatMsg").append("<div class='chat-message-content clearfix'><a href='"+projectpath+"/"+data[i].attachedFiles[j].fileName+"/"+data[i].attachedFiles[j].grievanceId+"/"+data[i].attachedFiles[j].docType+"'>"+data[i].attachedFiles[j].fileName+"</a></div>");
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
				alert("inside message");
				 var endUseruserId=488;
				var endUsergrievanceTicketStatus;
				if ($('#closeTicketCheck').is(":checked"))
				{
					endUsergrievanceTicketStatus=3;

				}
				else{
					endUsergrievanceTicketStatus=0;
				}
				alert("---------");
				var endUserremark=$('#replyRemark').val();
				var endUserreplyFile=$('#replyFile').val();
				var  endUsergrievanceIdToSave= $('#grievanceIdToSave').text();
				var  endUsergrievanceTxnId=  $('#grievanceTxnId').text();
				alert("+++++++++");
				//console.log("remark "+remark+"  replyFile="+replyFile+" grievanceTxnId="+grievanceTxnId+"grievanceIdToSave="+grievanceIdToSave+"grievanceTicketStatus=="+grievanceTicketStatus);
				var endUserfieldId=1;
				var endUserfileInfo =[];
				var endUserformData= new FormData();
				var endUserfileData = [];
				alert("$$$$$$$$$$$$");
				var endUserx;
				var endUserfilename='';
				var endUserfilediv;
				var endUseri=0;
				var formData= new FormData();
				var endUserdocTypeTagIdValue='';
				var endUserfilename='';
				alert("@@@@@@@@");
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
				alert("$$$$$$$$$$$$$$-");
				var endUsermultirequest={
						"attachedFiles":endUserfileInfo,
						"txnId":endUsergrievanceTxnId,
						"reply":endUserremark,
						"grievanceId":endUsergrievanceIdToSave,
						"grievanceStatus":endUsergrievanceTicketStatus,
						"featureId":6,
						"userId":endUseruserId
					}
				alert("after form data");
				endUserformData.append('fileInfo[]',JSON.stringify(endUserfileInfo));
				endUserformData.append('multirequest',JSON.stringify(endUsermultirequest));
				
				
				formData.append('file', $('#replyFile')[0].files[0]);
				formData.append('remark',remark);
				formData.append('grievanceId',grievanceIdToSave);
				formData.append('txnId',grievanceTxnId);
				formData.append('grievanceStatus',grievanceTicketStatus);
					alert("before ajax");
				$.ajax({
					url: './saveEndUserGrievanceReply',
					type: 'POST',
					data: formData,
					processData: false,
					contentType: false,
					success: function (data, textStatus, jqXHR) {
						alert("inside sucess");
						console.log(data);
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
			
			
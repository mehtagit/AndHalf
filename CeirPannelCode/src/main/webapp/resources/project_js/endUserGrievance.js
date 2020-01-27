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
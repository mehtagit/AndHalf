/**
 * 
 */
function pageRendering(){
	if(sourceType !="viaStolen" ){
		pageButtons('../consignment/pageRendering');

	}else if(sourceType ==="viaStolen" ){
		pageButtons('../consignment/pageRendering?sourceType=viaStolen');

	}
	localStorage.removeItem('sourceType');

}
function filterConsignment()
{       	 	


	if( startdate !='' || endDate !='' || taxStatus != null || consignmentStatus != null ){
		console.log("startdate="+startdate+" endDate="+endDate+" taxPaidstatus="+taxStatus+" consignmentStatus="+consignmentStatus)


		if(cierRoletype=="Importer" && sourceType !="viaStolen" ){
			table('../headers?type=consignment','../consignmentData');
		}

		else if(cierRoletype=="Custom" && sourceType !="viaStolen"){
			table('../headers?type=customConsignment','../consignmentData');
		}

		else if(cierRoletype=="CEIRAdmin"  && sourceType !="viaStolen"){
			table('../headers?type=adminConsignment','../consignmentData');
		}  

		else if(cierRoletype=="Importer" && sourceType ==="viaStolen" ){
			table('../headers?type=stolenconsignment','../consignmentData?sourceType=viaStolen');
		}
		localStorage.removeItem('sourceType');
	}
	else{
		console.log("please fill select");
	}
}

//**************************************************filter table**********************************************

function table(url,dataUrl){

	$.ajax({
		url: url,
		type: 'POST',
		dataType: "json",
		success: function(result){
			/*console.log("Url-------" +url+"--------"+ "dataUrl-------" +dataUrl);*/
			var table=	$("#consignmentLibraryTable").DataTable({
				destroy:true,
				"serverSide": true,
				orderCellsTop : true,
				"ordering" : false,
				"bPaginate" : true,
				"bFilter" : true,
				"bInfo" : true,
				"bSearchable" : true,
				ajax: {
					url : dataUrl,
					type: 'POST',
					dataType: "json",
					data : function(d) {
						d.filter = JSON.stringify(filterRequest); 
						
						console.log(JSON.stringify(filterRequest));
					}

				},
				"columns": result
			});
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax");
		}
	});
}

var startdate=$('#startDate').val(); 
var endDate=$('#endDate').val();
var taxStatus=$('#taxPaidStatus').val();
var consignmentStatus=$('#filterConsignmentStatus').val();
var userId = $("body").attr("data-userID");
var featureId="3";
var filterRequest={
		"consignmentStatus":consignmentStatus,
		"endDate":startdate,
		"startDate":endDate,
		"taxPaidStatus":taxStatus,
		"userId":userId,
		"featureId":featureId
};


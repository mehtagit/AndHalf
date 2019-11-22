
var cierRoletype = sessionStorage.getItem("cierRoletype");
$(document).ready(function(){
	$('.datepicker').datepicker();
	grievanceDataTable();
	pageRendering();
});


var startdate=$('#startDate').val(); 
var endDate=$('#endDate').val();
var taxStatus=$('#taxPaidStatus').val();
var consignmentStatus=$('#filterConsignmentStatus').val();
var userId = $("body").attr("data-userID");

var filterRequest={
		"consignmentStatus":consignmentStatus,
		"endDate":startdate,
		"startDate":endDate,
		"taxPaidStatus":taxStatus,
		"userId":userId,
};



//**************************************************Grievance table**********************************************

function grievanceDataTable(){

	$.ajax({
		url: 'headers?type=grievanceHeaders',
		type: 'POST',
		dataType: "json",
		success: function(result){
			/*console.log("Url-------" +url+"--------"+ "dataUrl-------" +dataUrl);*/
			var table=	$("#grivanceLibraryTable").DataTable({
				destroy:true,
				"serverSide": true,
				orderCellsTop : true,
				"aaSorting" : [],
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
				$("#greivanceTableDiv").append("<div class='col s6 m2 l2 responsiveDiv'>"+
						"<div id='enddatepicker' class='input-group date' data-date-format='yyyy-mm-dd'>"+
						"<label for='TotalPrice'>"+date[i].title
						+"</label>"+"<input class='form-control' type="+date[i].type+" id="+date[i].id+"/>"+
						"<span	class='input-group-addon' style='color: #ff4081'>"+
						"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");
				
			} 

			// dynamic dropdown portion
			var dropdown=data.dropdownList;
			for(i=0; i<dropdown.length; i++){
				var dropdownDiv=
					$("#greivanceTableDiv").append("<div class='col s6 m2 l2 selectDropdwn'>"+
							"<br>"+
							"<div class='select-wrapper select2 form-control boxBorder boxHeight initialized'>"+
							"<span class='caret'>"+"</span>"+
							"<input type='text' class='select-dropdown' readonly='true' data-activates='select-options-1023d34c-eac1-aa22-06a1-e420fcc55868' value='Consignment Status'>"+

							"<select id="+dropdown[i].id+" class='select2 form-control boxBorder boxHeight initialized'>"+
							"<option>"+dropdown[i].title+
							"</option>"+
							"</select>"+
							"</div>"+
					"</div>");
			}
			
			$("#greivanceTableDiv").append("<div class='col s12 m2 l2'><button class='btn primary botton' id='submitFilter'></button></div>");
			for(i=0; i<button.length; i++){
				$('#'+button[i].id).text(button[i].buttonTitle);
				if(button[i].type === "HeaderButton"){
					$('#'+button[i].id).attr("href", button[i].buttonURL);
				}
				else{
					$('#'+button[i].id).attr("onclick", button[i].buttonURL);
				}
			}
			//cierRoletype=="Importer"? $("#btnLink").css({display: "block"}) : $("#btnLink").css({display: "none"});
			/*sourceType=="viaStolen" ? $("#btnLink").css({display: "none"}) : $("#btnLink").css({display: "none"});*/
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
	//alert("today"+today);
	console.log("dispatche="+dispatcDate);
	console.log("todays parse date"+Date.parse(today));
	console.log("dispatche parse date"+Date.parse(dispatcDate));


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
	console.log("dispatche="+dispatcDate);
	console.log("todays parse date"+Date.parse(today));
	console.log("dispatche parse date"+Date.parse(dispatcDate));


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

populateCountries
(   
		"country"
);






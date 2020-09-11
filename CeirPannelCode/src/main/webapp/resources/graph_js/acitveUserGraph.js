/*[

	'./resources/js/materialize.js',
	'./resources/custom_js/bootstrap.min.js',
	'./resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js'
	].forEach(function(src) {
		$('body').append('<script type="text/javascript" src='+src+' async defer><\/script>');

	});
	

*/
function activeDeviceGraph() {
	[31,48].forEach(function(reportnameId) {
		var graphRequest=null;
		var chartID=null;
		var type=null;
		var title=null;
		if(reportnameId == 31){
			graphRequest={
					"columns": [
						"Approved TAC",
						"Rejected TAC"
						],
						"reportnameId": reportnameId,
						"file" : 0,
						"pageSize" :1,
						"pageNo" :0
			}
			chartID='pieGraph';
			type='pie';
			title='User Login Pie Graph';
		}

		else if(reportnameId == 48){
			graphRequest={

					"reportnameId": reportnameId,
					"file" : 0,
					"pageSize" :10,
					"pageNo" :0
			}
			chartID='lineGraph';
			type='line';
			title='User Login Line Graph';
		}

		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$.ajaxSetup({
			headers:
			{ 'X-CSRF-TOKEN': token }
		});
		$.ajax({
			type : 'POST',
			url : './report/data',
			contentType : "application/json",
			dataType : 'html',
			async:false,
			data : JSON.stringify(graphRequest),
			/*beforeSend : function() {
				$("#loading-image").show();
			},*/
			success : function(data) {
	
				var response = JSON.parse(data);
				graph(response,chartID,type,title);

			},
			error : function() {
			}
		});
	});

}


function graph(response,id,chartType,chartTitle)
{


	var imeiCount=[];
	var msisdnFrequency=[];
	var date=[];
	var pieLabelName=response['columns'];
	var pieData=[];
	for(var i=0;i<response['rowData'].length;i++){
		pieData.push(response['rowData'][i].approvedTAC);
		pieData.push(response['rowData'][i].rejectedTAC);
		date.push(response['rowData'][i].createdOn);
		msisdnFrequency.push(response['rowData'][i].msisdnFrequency);
		imeiCount.push(response['rowData'][i].imeiCount);
	}
	if(chartType=='pie'){
/*

		['./resources/graph_js/chartjs-plugin-datalabel.js'].forEach(function(src) {
			$('body').append('<script type="text/javascript" id="pieJS" src='+src+' async defer><\/script>');

		});
		
*/
		var options = {
				responsive: false,
				maintainAspectRatio: false,
				plugins: {
				    datalabels: {
				      formatter: (value, ctx) => {
				        let sum = ctx.dataset._meta[0].total;
				        
				        let percentage = (value * 100 / sum).toFixed(2) + "%";
				        return percentage;


				      },
				      color: '#fff',
				    }
				  }
				      };
				    
		

		var ctx = document.getElementById(''+id+'').getContext('2d');

		var chart = new Chart(ctx, {
			// The type of chart we want to create
			type: ''+chartType+'',

			// The data for our dataset
			data: {
				labels: pieLabelName,
				datasets: [ {
					backgroundColor: [
						'#512DA8',
						'#D32F2F'],
						data: pieData
				}]
			},

			// Configuration options go here
			options: options
		});

	
	}


	else if(chartType == "line"){

		var ctx = document.getElementById(''+id+'').getContext('2d');
		var chart = new Chart(ctx, {
			// The type of chart we want to create
			type: ''+chartType+'',

			// The data for our dataset
			data: {
				labels: date,
				datasets: [ {
					label: "IMEI Count",
					backgroundColor:  'rgb(70, 191, 189)',
					borderColor: 'rgb(70, 191, 189)',
					data: imeiCount
				},
				{
					label: "MSISDN Frequency",
					backgroundColor: 'rgb(235, 203, 138)',
					borderColor:  'rgb(235, 203, 138)',
					data: msisdnFrequency
				}]
			},
			
			// Configuration options go here
			options: {
				responsive: false,
				maintainAspectRatio: false,
				elements: {
                    point:{
                        radius: 0
                    }
                },
                plugins: {
    			    datalabels: {
    			        display: false,
    			    },
    			},
				scales: {
					xAxes: [{
						gridLines: {
							display: false
						}
					}],
					yAxes: [{
						gridLines: {
							display: false
						}
					}]
				}           

			}
		});
		 	}

}

















//boxes
$(document).ready(function(){
	$('div#initialloader').fadeIn('fast');
	var url;

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajaxSetup({
	headers:
	{ 'X-CSRF-TOKEN': token }
	});
	var graphRequest={

			"reportnameId": 31,
			"file" : 0,
			"pageSize" :1,
			"pageNo" :0
	}
	
	$.ajax({
		type : 'POST',
		url : './report/count',
		contentType : "application/json",
		data : JSON.stringify(graphRequest),
		success: function(data){
var i=0;
				Object.keys(data['rowData'][0]).map(function(key){ 
				if(key == 'Date'){
					$('#dateVal').text('Last Update Date: '+data['rowData'][0][key]);
				}
				else{
					if(i == 0){ $('#firstTD').text(data['rowData'][0][key]);}
					else if(i == 1){$('#secondTD').text(data['rowData'][0][key]);}
					$("#infoBox").append("<div class='round-circle-center-responsive'><div class='round-circle'><h6 class='right' style='width: 105px;'>"+key+"</h6><p class='circle-para right' style='position:absolute;margin-top:62px;width: 180px;margin-left: 5px;padding-right: 0px !important;'><b id="+i+++">"+data['rowData'][0][key]+"</b> </p><div class='icon-div center'><i class='fa fa-puzzle-piece test-icon' aria-hidden='true'></i></div></div>");
				
				}
				$('div#initialloader').delay(300).fadeOut('slow');
				});
		}
	
	});
	
});


var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$.ajaxSetup({
headers:
{ 'X-CSRF-TOKEN': token }
});
$.ajax({
	type : 'POST',
	url : './mobileDevice/report/count',
	contentType : "application/json",
	async:false,
	data : JSON.stringify({"reportnameId": 28,"file" : 0,"pageSize" :1,"pageNo" :0}),
	success: function(data){
		Object.keys(data['rowData'][0]).map(function(key){ 
			if(key != 'Date'){
				
				$("#infoBox").append("<div class='round-circle-center-responsive'><div class='round-circle'><h6 class='right' style='width: 105px;'>"+key+"</h6><p class='circle-para right' style='position:absolute;margin-top:62px;width: 180px;margin-left: 5px;padding-right: 0px !important;'><b>"+data['rowData'][0][key]+"</b> </p><div class='icon-div center'><i class='fa fa-puzzle-piece test-icon' aria-hidden='true'></i></div></div>");
			}
			
		});
	}
});

/*[

	'./resources/js/materialize.js',
	'./resources/custom_js/bootstrap.min.js',
	'./resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js'
	].forEach(function(src) {
		$('body').append('<script type="text/javascript" src='+src+' async defer><\/script>');

	});
	

*/
function activeDeviceGraph() {
	[31,48,52,53].forEach(function(reportnameId) {
		var graphRequest=null;
		var chartID=null;
		var type=null;
		var title=null;
		var urlHit=null;
		
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
			urlHit='./report/data';
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
			urlHit='./report/data';
		}
		
		else if(reportnameId == 52 ){
			graphRequest={

					"reportnameId": reportnameId,
					"file" : 0,
					"pageSize" :10,
					"pageNo" :0
			}
			
			chartID='pieGraphBrandName';
			type='pie';
			title='User Login Pie Graph';
			urlHit='./brandModel/data';
		}
		else if(reportnameId == 53 ){
			graphRequest={

					"reportnameId": reportnameId,
					"file" : 0,
					"pageSize" :10,
					"pageNo" :0
			}
			
			chartID='pieGraphModelNumber';
			type='pie';
			title='User Login Pie Graph';
			urlHit='./brandModel/data';
		}

		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$.ajaxSetup({
			headers:
			{ 'X-CSRF-TOKEN': token }
		});
		
		$.ajax({
			type : 'POST',
			url : urlHit,
			contentType : "application/json",
			dataType : 'html',
			async:false,
			data : JSON.stringify(graphRequest),
			/*beforeSend : function() {
				$("#loading-image").show();
			},*/
			success : function(data) {
				
				var response = JSON.parse(data);
				
				if(reportnameId==52){
					
					graphBrandName(response,chartID,type,title);
				}
				
				else if(reportnameId==53){
					
					graphTopModelNumber(response,chartID,type,title);
					}
				
				else{
				
					graph(response,chartID,type,title);	
				}
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
					else if(i == 2){$('#thirdTD').text(data['rowData'][0][key]);}
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



function graphBrandName(response,id,chartType,chartTitle)
{


	var imeiCount=[];
	var msisdnFrequency=[];
	var date1=[];
	var pieLabelName1=[];
	var pieData1=[];
	
	
	for(var i=0;i<=4;i++){
		
		pieData1.push(parseInt(response['rowData'][i]['Count']));
		pieLabelName1.push(response['rowData'][i]['Brand Name']);
		//pieData.push(response['rowData'][i].rejectedTAC);
		//date1.push(response['rowData'][i].date);
	//	msisdnFrequency.push(response['rowData'][i].msisdnFrequency);
		//imeiCount.push(response['rowData'][i].imeiCount);
	}
	if(chartType=='pie'){
/*

		['./resources/graph_js/chartjs-plugin-datalabel.js'].forEach(function(src) {
			$('body').append('<script type="text/javascript" id="pieJS" src='+src+' async defer><\/script>');

		});
		
*/
		
		var ctx = document.getElementById(''+id+'').getContext('2d');
		
		var options = {
				responsive: false,
				maintainAspectRatio: false,
				plugins: {
					datalabels: {
					formatter: (value, ctx) => {

					let datasets = ctx.chart.data.datasets;

					if (datasets.indexOf(ctx.dataset) === datasets.length - 1) {
					let sum = datasets[0].data.reduce((a, b) => a + b, 0);
					let percentage = Math.round((value / sum) * 100) + '%';
					return percentage;
					} else {
					return percentage;
					}
					},
					color: '#fff',
					}
					}
							}

		var chart = new Chart(ctx, {
			// The type of chart we want to create
			type: ''+chartType+'',

			// The data for our dataset
			data: {
				labels: pieLabelName1,
				datasets: [ {
					backgroundColor: [
						'#512DA8',
						'#D32F2F',
						'#808000',
						'#B8860B',
						'#006400',
						'#2F4F4F',
						'#008B8B',
						'#4682B4',
						'#8B4513',
						'#696969'],
						data: pieData1
				}]
			},

			// Configuration options go here

			 options : options
		});

	
	}


}

function graphTopModelNumber(response,id,chartType,chartTitle)
{


	var imeiCount=[];
	var msisdnFrequency=[];
	var date1=[];
	var pieLabelName2=[];
	var pieData2=[];
	for(var i=0;i<=4;i++){
		
		pieData2.push(parseInt(response['rowData'][i]['Count']));
		pieLabelName2.push(response['rowData'][i]['Model Name']);
		//pieData.push(response['rowData'][i].rejectedTAC);
		//date1.push(response['rowData'][i].date);
	//	msisdnFrequency.push(response['rowData'][i].msisdnFrequency);
		//imeiCount.push(response['rowData'][i].imeiCount);
	}
	if(chartType=='pie'){


		['./resources/graph_js/chartjs-plugin-datalabel.js'].forEach(function(src) {
			$('body').append('<script type="text/javascript" id="pieJS" src='+src+' async defer><\/script>');

		});
		

		
		var ctx = document.getElementById(''+id+'').getContext('2d');
		var options = {
				responsive: false,
				maintainAspectRatio: false,
				plugins: {
					datalabels: {
					formatter: (value, ctx) => {

					let datasets = ctx.chart.data.datasets;

					if (datasets.indexOf(ctx.dataset) === datasets.length - 1) {
					let sum = datasets[0].data.reduce((a, b) => a + b, 0);
					let percentage = Math.round((value / sum) * 100) + '%';
					return percentage;
					} else {
					return percentage;
					}
					},
					color: '#fff',
					}
					}
							}
		var chart = new Chart(ctx, {
			// The type of chart we want to create
			type: ''+chartType+'',

			// The data for our dataset
			data: {
				labels: pieLabelName2,
				datasets: [ {
					backgroundColor: [
						'#512DA8',
						'#D32F2F',
						'#808000',
						'#B8860B',
						'#006400',
						'#2F4F4F',
						'#008B8B',
						'#4682B4',
						'#8B4513',
						'#696969'],
						data: pieData2
				}]
			},

			// Configuration options go here
			 options : options		});

	
	}


}

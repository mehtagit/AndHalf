/*[

	'./resources/js/materialize.js',
	'./resources/custom_js/bootstrap.min.js',
	'./resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js'
	].forEach(function(src) {
		$('body').append('<script type="text/javascript" src='+src+' async defer><\/script>');

	});
	

*/
function activeDeviceGraph() {
	[29,27].forEach(function(reportnameId) {
		var graphRequest=null;
		var chartID=null;
		var type=null;
		var title=null;
		var  urlParam=null;
		if(reportnameId == 29){
			graphRequest={
					 "columns": [
						    "Date",
						    "Operator Name",
						    "Total IMEI"
						  ],
						  "groupBy": "Operator Name",
						  "reportnameId": 29,
						  "file" : 0, 
						  "pageSize" :55, 
						  "pageNo" :0
			}
			urlParam= './report/data';
			
		}
		if(reportnameId == 27){
			graphRequest={
					 
					  
					  "lastDate": true,
					  "reportnameId": 27,
						  "file" : 0, 
						  "pageSize" :10, 
						  "pageNo" :0
			}
			urlParam='./report/imeiUsageDashBoard';
		}
		

		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$.ajaxSetup({
			headers:
			{ 'X-CSRF-TOKEN': token }
		});
		$.ajax({
			type : 'POST',
			url :urlParam,
			contentType : "application/json",
			dataType : 'html',
			async:false,
			data : JSON.stringify(graphRequest),
			/*beforeSend : function() {
				$("#loading-image").show();
			},*/
			success : function(data) {
				
				var response = JSON.parse(data);
				//graph(response,chartID,type,title);
if(reportnameId==29){
					
	graph(response,'lineGraph','line','User Login Line Graph')
				}
			
if(reportnameId==27){
	
	graph(response,'gaugeGraph','gauge','User Login Doughnut Graph')
				}
		
			},
			error : function() {
			}
		});
	});

}


function graph(response,id,chartType,chartTitle)
{
  var date=[];
  var operatorName=[];
  var totalImei=[];
  var QB=[];
  var seatel=[];
  var smart=[];
  var cellcard=[];
  var metfone=[];
  var pieLabelName=['Primary','Secondary'];
  var pieData=[];
	   	//console.log("repsonse-->"+JSON.stringify(response));
		for(var i=0;i<response['rowData'].length;i++){
	   		QB.push(response['rowData'][i]['QB']);
	   		seatel.push(response['rowData'][i]['SEATEL']);
	   		smart.push(response['rowData'][i]['SMART']);
	   		cellcard.push(response['rowData'][i]['CELLCARD']);
	   		metfone.push(response['rowData'][i]['METFONE']);
	   		date.push(response['rowData'][i]['Date']);
	   		//totalImei.push(response['rowData'][i]['Total IMEI']);
	   	}	
		pieData.push(parseInt(response['rowData'][0]['Total Paired IMEI']));
		pieData.push(parseInt(response['rowData'][0]['Total Duplicate IMEI']));
		
		
		if(chartType=='line' ){
		$("#exp").unbind("click").click(function(){
	        var data = response['rowData'];
	        if(data == '')
	            return;
	        
	        JSONToCSVConvertor(data, "Report", true);
	    });
	   	var ctx = document.getElementById(''+id+'').getContext('2d');
	    var chart = new Chart(ctx, {
	      // The type of chart we want to create
	      type: ''+chartType+'',

	      // The data for our dataset
	      data: {
	        labels: date,
	        datasets: [{
	            label: "QB",
	            borderColor:  'rgb(235, 203, 138)',
	            data: QB,
	            fill: false
	            
	        },
	        {
	            label: "Seatel",
	            borderColor: 'rgb(70, 191, 189)',
	            data: seatel,
	            fill: false
	            
	        },
	        {
	            label: "Smart",
	            borderColor:  '#512DA8',
	            data: smart,
	            fill: false
	            
	        },
	        {
	            label: "Cellcard",
	            borderColor: '#D32F2F',
	            data: cellcard,
	            fill: false
	            
	        },
	        {
	            label: "Metfone",
	            borderColor:  '#FFA000',
	            data: metfone,
	            fill: false
	            
	        }]
	      },

	      // Configuration options go here
	      options: {
	    	    responsive: false,
	    	    maintainAspectRatio: false,
	    	    animation: {
	    	        onComplete: captureImage
	    	      },
	    	    elements: {
	                point:{
	                    radius: 0
	                }
	            },
	    	    plugins: {
				    datalabels: {
				        display: false,
				    },
				    anchor :'end',
		            align :'top',
		            // and if you need to format how the value is displayed...
		            formatter: function(value, context) {
		                return GetValueFormatted(value);
		            }
				},
				
	    	    scales: {
	                xAxes: [{
	                   gridLines: {
	                      display: false
	                   },
	                   scaleLabel: {
	                       display: true,
	                       labelString: 'Date'
	                     }
	                }],
	                yAxes: [{
	                   gridLines: {
	                      display: false
	                   },
	                   scaleLabel: {
	                       display: true,
	                       labelString: 'IMEI Count'
	                     }
	                }]
	             }           
	             
	    	}
	    });
	    function captureImage(){  
	        var url=chart.toBase64Image();
	        document.getElementById("lineImage").href=url;
	    }
		}
		
		
		
		else if(chartType=='gauge'){
	    	 $("#expPairingType").unbind("click").click(function(){
	 	        var data = response['rowData'];
	 	        if(data == '')
	 	            return;
	 	        
	 	        JSONToCSVConvertor(data, "Pairing_Type_Report", true);
	 	    });
	        var ctx = document.getElementById(''+id+'').getContext('2d');
	        var chart = new Chart(ctx, {
	          // The type of chart we want to create
	          type: 'doughnut',

	          // The data for our dataset
	          data: {
	            labels: pieLabelName,
	            datasets: [ {
	            	 backgroundColor: [
	            		 '#512DA8',
	                     '#D32F2F'],
	                data: pieData
	            }],
	            borderWidth: 0
	          },

	          // Configuration options go here
	          options: {
	        	    responsive: false,
	        	    maintainAspectRatio: false,
	        	    animation: {
	        	        onComplete: captureImage
	        	      },
	        	    rotation: 1 * Math.PI,
	                circumference: 1 * Math.PI,
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
	 
	        });
	         
	          function captureImage(){  
	              var url=chart.toBase64Image();
	              document.getElementById("pairingTypesImage").href=url;
	              }	
	    
	    }
    $('div#initialloader').delay(300).fadeOut('slow');
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
			  "reportnameId": 29,
			  "file" : 0, 
			  "pageSize" :5, 
			  "pageNo" :0
	}
	
	$.ajax({
		type : 'POST',
		url : './report/data',
		contentType : "application/json",
		data : JSON.stringify(graphRequest),
		success: function(data){
			$("#expOperatorWiseIMEI").unbind("click").click(function(){
		        var result = JSON.stringify(data['rowData']);
		        if(result == '')
		            return;
		        
		        JSONToCSVConvertor(result, "Report", true);
		    });
var i=0;
				Object.keys(data['rowData'][0]).map(function(key){ 
				if(key == 'Date'){
					$('#dateVal').text('Last Update Date: '+data['rowData'][0][key]);
				}
				else{
					if(i == 0){ $('#firstTD').text(data['rowData'][0]['Total IMEI']);}
					else if(i == 1){$('#secondTD').text(data['rowData'][1]['Total IMEI']);}
					else if(i == 2){$('#thirdTD').text(data['rowData'][2]['Total IMEI']);}
					else if(i == 3){$('#fourthTD').text(data['rowData'][3]['Total IMEI']);}
					else if(i == 4){$('#fifthTD').text(data['rowData'][4]['Total IMEI']);}
					
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





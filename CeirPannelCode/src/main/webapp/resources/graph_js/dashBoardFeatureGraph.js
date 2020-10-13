function featureDashboardGraph() {
/*	var currentTime = new Date()
	var month = ("0" + (currentTime.getMonth() + 1)).slice(-2)
	var day = ("0" + (currentTime.getDate())).slice(-2)
	var year = currentTime.getFullYear();
	var endDate=year+"-"+month+"-"+day;
	var startDate=year+"-"+month+"-"+(day-15);
	*/
	var graphRequest=null;
	[41,44,16].forEach(function(reportnameId) {
		if(reportnameId==41){
			
	 graphRequest={
			"columns": [
				"Date",
				"Stock Status",
				"Count"
				],

			"reportnameId": reportnameId,
			"groupBy": "Stock Status",
			"file" : 0,
			"pageSize" :20,
			"pageNo" :0
	}
	}
		else if(reportnameId==44){
			
			graphRequest={
						"columns": [
							"Date",
							"Consignment Status",
							"Count"
							],

						"reportnameId": reportnameId,
						"groupBy": "Consignment Status",
						"file" : 0,
						"pageSize" :20,
						"pageNo" :0
				}			
		}
else if(reportnameId==16){
	
			graphRequest={
						"columns": [
							"Date",
							"Grievance Status",
							"Count"
							],

						"reportnameId": reportnameId,
						"groupBy": "Grievance Status",
						"file" : 0,
						"pageSize" :20,
						"pageNo" :0
				}			
		}

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajaxSetup({
		headers:
		{ 'X-CSRF-TOKEN': token }
	});
	$.ajax({
		type : 'POST',
		async:false,
		url : './brandModel/data',
		contentType : "application/json",
		data : JSON.stringify(graphRequest),
		/*beforeSend : function() {
			$("#loading-image").show();
		},*/
		success : function(data) {
			var response = data;
			var labelSet=null;
			if(reportnameId==41){
				
				labelSet=setLabelByID(4 , 4);
				graph(response,'horizontalBarGraph','horizontalBar','User Login HorizontalBar Graph',labelSet);
				
				
			}
			else if(reportnameId==44){
				
				labelSet=setLabelByID(3 , 4);
				graph(response,'ConsignmentBarGraph','horizontalBar','User Login HorizontalBar Graph',labelSet);
				
				
			}
				
            else if(reportnameId==16){
            	
            	labelSet=setLabelByID(6 , 4);
            	graph(response,'grievanceStatusWise','horizontalBar','User Login HorizontalBar Graph',labelSet);
				
            }
		},
		error : function() {
		}
	});
	});
}



function graph(response,id,chartType,chartTitle,pieLabelName)
{
	var date = [];
	//console.log("resonse=="+pieLabelName);
	//var pieLabelName=['New','Approved By CEIR Admin','Pending Approval From CEIR Admin','Rejected by CEIR Admin','Rejected By System','Withdrawn By User','Withdrawn By CEIR Admin'];
	var backgroundColors=['#512DA8','#008B8B','#F20515','#4682B4','#8B4513','#006400','#7C0378','#696969','#800080','#9400D3','#FFFF00','#7E57C2'];
	var backgroundHoverColors=['#512DA8','#008B8B','#F20515','#4682B4','#8B4513','#006400','#7C0378','#696969','#800080','#9400D3','#FFFF00','#7E57C2'];
	var rowData = [];
	var allData = new Map();
	var dataSetList = [];
	for(var i=0;i<response['rowData'].length;i++)
	{
	    for( var j=0; j<pieLabelName.length; j++ )
	    {
	      if( allData.has( pieLabelName[j] ) ){
	        rowData = allData.get( pieLabelName[j] );
	      }
	      else{
	    	  rowData = [];
	      }
	      
	      if(response['rowData'][i][pieLabelName[j]]==null || response['rowData'][i][pieLabelName[j]]=="null"){
	    	  rowData.push(0);  
	      }
	      else{
	    	  rowData.push(response['rowData'][i][pieLabelName[j]]);  
	      }
	      //console.log(rowData);;	
	      	allData.set( pieLabelName[j], rowData );
	    }
	    date.push(response['rowData'][i]['Date']);
	}

	for( var j=0; j<pieLabelName.length; j++ ){
		  dataSetList.push( {
			label: pieLabelName[j],
			backgroundColor: backgroundColors[j],
			hoverBackgroundColor: backgroundHoverColors[j],
			data: allData.get( pieLabelName[j] )
		});

	}
	
    	var bar_ctx = document.getElementById(''+id+'');
    	var bar_chart = new Chart(bar_ctx, {
    	    type: ''+chartType+'',
    	    data: {
    	        labels: date,
    	        datasets:dataSetList
    	    },
    	    options: {
    	        responsive: false,
        	    maintainAspectRatio: false,
    	     		animation: {
    	        	duration: 10,
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
    	          	stacked: true, 
    	            gridLines: { display: false },
    	            }],
    	          yAxes: [{ 
    	          	stacked: true
    	           
    	            }],
    	        }, // scales
    	        legend: {display: true}
    	    } // options
    	   }
    	);
    	$('div#initialloader').delay(300).fadeOut('slow');    
}

$('div#initialloader').delay(300).fadeOut('slow');

function setLabelByID(featureId,userTypeId){
	var res=[];
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajaxSetup({
		async: false,
	headers:
	{ 'X-CSRF-TOKEN': token }
	});
	$.getJSON('./getDropdownList/'+featureId+"/"+userTypeId, function(data) {
		
		for (i = 0; i < data.length; i++) {
			data[i].interp;
			res.push(data[i].interp);
		}
	
	});
	
	return res;
}
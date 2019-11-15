$.ajax({
	url: "./headers?type=stolen",
	type: 'POST',
	dataType: "json",
	success: function(result){
		var table=	$("#stolenLibraryTable").DataTable({
			destroy:true,
			"serverSide": true,
			orderCellsTop : true,
			"aaSorting" : [],
			"bPaginate" : true,
			"bFilter" : true,
			"bInfo" : true,
			"bSearchable" : true,
			ajax: {
				url: './stolenData',
				type: 'POST',
				data : function(d) {
					d.filter = null;       		    		
				}
			},
			"columns": result
		});
	}
}); 


$.ajax({
	url: "./stolen/pageRendering",
	type: 'POST',
	dataType: "json",
	success: function(data){
		var elem='<p class="PageHeading">'+data.pageTitle+'</p>';
		$("#pageHeader").append(elem);
		var button=data.buttonList;

		var date=data.inputTypeDateList;
		for(i=0; i<date.length; i++){
			$("#consignmentTableDIv").append("<div class='col s6 m2 l2 responsiveDiv'>"+
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
				$("#consignmentTableDIv").append("<div class='col s6 m2 l2 selectDropdwn'>"+
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

		$("#consignmentTableDIv").append("<div class='col s12 m2 l2'><button class='btn primary botton' id='submitFilter'></button></div>");
		for(i=0; i<button.length; i++){
			$('#'+button[i].id).text(button[i].buttonTitle);
			if(button[i].type === "HeaderButton"){
				$('#'+button[i].id).attr("href", button[i].buttonURL);
			}
			else{
				$('#'+button[i].id).attr("onclick", button[i].buttonURL);
			}
		}
	}

}); 
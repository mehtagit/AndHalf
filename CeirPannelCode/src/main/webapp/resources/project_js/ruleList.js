		
		var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';


		$.i18n().locale = lang;	
		
		$.i18n().load( {
			'en': './resources/i18n/en.json',
			'km': './resources/i18n/km.json'
		} ).done( function() { 
		});

         $(window).load(function(){
			$('div#initialloader').fadeIn('fast');
			filter(lang);
			pageRendering();
		 });
		
         $.getJSON('./getDropdownList/RULE_STATE', function(data) {
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#editState');
				}
			});
         
         function filter(lang)
 		{       	
 			table('./headers?lang='+lang+'&type=ruleList','./ruleListData');
 			}

		//**************************************************filter table**********************************************

		function table(url,dataUrl){
		var state= $("#State").val() == undefined ? null : $("#State option:selected").text();

			var filterRequest={
					"endDate":$('#endDate').val(),
					"startDate":$('#startDate').val(),
					  "state": state
					
			}
			if(lang=='km'){
				var langFile='./resources/i18n/khmer_datatable.json';
			}

			$.ajax({
				url: url,
				/*	headers: {"Accept-Language": "en"},*/
				type: 'POST',
				dataType: "json",
				success: function(result){
					var table=	$("#table").DataTable({
						destroy:true,
						"serverSide": true,
						orderCellsTop : true,
						"ordering" : false,
						"bPaginate" : true,
						"bFilter" : true,
						"bInfo" : true,
						"bSearchable" : true,
						"oLanguage": {  
							"sUrl": langFile  
						},
						ajax: {
							url : dataUrl,
							type: 'POST',
							dataType: "json",
							data : function(d) {
								d.filter = JSON.stringify(filterRequest); 

							}
						},
						"columns": result
					});

					$('div#initialloader').delay(300).fadeOut('slow');
						$('#table input').unbind();
						$('#table input').bind('keyup', function (e) {
							if (e.keyCode == 13) {
								table.search(this.value).draw();
							}

						});
				},
				error: function (jqXHR, textStatus, errorThrown) {
					
				}
			});
		}



		function pageRendering(){
			pageButtons('./ruleList/pageRendering');

		}


		function pageButtons(Url){
			$.ajax({
				url: Url,
				type: 'POST',
				dataType: "json",
				success: function(data){
					data.userStatus == "Disable" ? $('#btnLink').addClass( "eventNone" ) : $('#btnLink').removeClass( "eventNone" );
					
					var elem='<p class="PageHeading">'+data.pageTitle+'</p>';		
					$("#pageHeader").append(elem);
					var button=data.buttonList;
					/*				var date=data.inputTypeDateList;
			for(i=0; i<date.length; i++){
						if(date[i].type === "date"){
							$("#FieldTableDiv").append("<div class='input-field col s6 m2'>"+
									"<div id='enddatepicker' class='input-group'>"+
									"<input class='form-control datepicker' type='text' id="+date[i].id+" autocomplete='off' onchange='checkDate(startDate,endDate)'>"+
									"<label for="+date[i].id+">"+date[i].title
									+"</label>"+
									"<span	class='input-group-addon' style='color: #ff4081'>"+
									"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");
							$( "#"+date[i].id ).datepicker({
								dateFormat: "yy-mm-dd",
								 maxDate: new Date()
					        });
						}else if(date[i].type === "text"){
							$("#FieldTableDiv").append("<div class='input-field col s6 m2' ><input type="+date[i].type+" id="+date[i].id+" maxlength='19' /><label for="+date[i].id+" class='center-align'>"+date[i].title+"</label></div>");
						}
						 
					} */
				
				// dynamic dropdown portion
					var dropdown=data.dropdownList;
					for(i=0; i<dropdown.length; i++){
						var dropdownDiv=
							$("#FieldTableDiv").append("<div class='col s6 m2 selectDropdwn'>"+
								
									"<div class='select-wrapper select2  initialized'>"+
									"<span class='caret'>"+"</span>"+
									"<input type='text' class='select-dropdown' readonly='true' data-activates='select-options-1023d34c-eac1-aa22-06a1-e420fcc55868' value='Consignment Status'>"+

									"<select id="+dropdown[i].id+" class='select2 initialized'>"+
									"<option value='null' disabled selected>"+dropdown[i].title+
									"</option>"+
									"</select>"+
									"</div>"+
							"</div>");
					}

						$("#FieldTableDiv").append("<div class=' col s3 m2 l1'><button type='button' class='btn primary botton' id='submitFilter'/></div>");
						//$("#FieldTableDiv").append("<div class=' col s3 m2 l7'><a href='JavaScript:void(0)' type='button' class='export-to-excel right'  onclick='exportConsignmentData()'>"+$.i18n('Export')+"<i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");
						for(i=0; i<button.length; i++){
							$('#'+button[i].id).text(button[i].buttonTitle);
							$('#'+button[i].id).attr("onclick", button[i].buttonURL);
						}

						$.getJSON('./getDropdownList/RULE_STATE', function(data) {
							for (i = 0; i < data.length; i++) {
								$('<option>').val(data[i].value).text(data[i].interp)
								.appendTo('#State');
							}
						});
						
				}
			
	
			}); 
			
	}






			function getDetailBy(id){
				$("#editModel").openModal({
			        dismissible:false
			    });
				window.xid=id;
				
				$.ajax({
					url : "./viewRuleListAPI/"+id,
					dataType : 'json',
					contentType : 'application/json; charset=utf-8',
					type : 'GET',
					success : function(data) {
						setData(data);
					},
					error : function() {
						alert("Failed");
					}
				});	
			}
			
			
			function setData(data){
				$("#editName").val(data.name);
				$("#editDescription").val(data.description);
				$("#editState").val(data.state);
			}
			
			
			
			function update(){

				var name=$('#editName').val();
				var description=$('#editDescription').val();
				var state=$('#editState').val();
				var ruleListContent={
						"name":name,
						"description":description,
						"state":state,
						"id":window.xid
						
				}
	
				$.ajax({
					
					url : "./updateRuleList",
					data : JSON.stringify(ruleListContent),
					dataType : 'json',
					contentType : 'application/json; charset=utf-8',
					type : 'POST',
					success: function (data, textStatus, jqXHR) {
						
					/*	$('#updateModal').closeModal();

						$('#updateConsignment').openModal({
							dismissible:false
						});
						if(data.errorCode==200){


							$('#sucessMessage').text('');
							$('#sucessMessage').text(data.message);
						}

						else if (data.errorCode==0){

							$('#sucessMessage').text('');
							$('#sucessMessage').text(updateMsg+" "+ (data.txnId) +" "+hasBeenUpdated);
						}
						else 
						{
							$('#sucessMessage').text('');
							$('#sucessMessage').text(data.message);
						}
*/
					},
					error: function (jqXHR, textStatus, errorThrown) {
						
					}
				});
				
				return false;
			}


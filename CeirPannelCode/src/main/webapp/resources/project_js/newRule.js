$.getJSON('./getAllfeatures', function(data) {
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].name).text(data[i].name).appendTo('#Feature');
	}
});
$.getJSON('./registrationUserType', function(data) {
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].usertypeName).text(data[i].usertypeName)
		.appendTo('#User');
	}
});
$.getJSON('./ruleName', function(data) {
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].description).text(data[i].description)
		.appendTo('#Rule');
	}
});

$.getJSON('./getDropdownList/PERIOD_ACTION', function(data) {
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].interp).text(data[i].interp)
		.appendTo('#GracePeriod,#PostGracePeriod');
	}
});


$.getJSON('./getDropdownList/MOVE_TO_NEXT', function(data) {
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].interp).text(data[i].interp)
		.appendTo('#MoveToGracePeriod,#MoveToPostGracePeriod');
	}
});


function save(){
	var name=$('#editName').val();
	var description=$('#editDescription').val();
	var state=$('#editState').val();
	var newRule={
			"failedRuleActionGrace": $('#MoveToGracePeriod').val(),
			"failedRuleActionPostGrace":$('#MoveToPostGracePeriod').val(),
			"feature": $('#Feature').val(),
			"graceAction": $('#GracePeriod').val(),
			"name": $('#Rule').val(),
			"postGraceAction": $('#PostGracePeriod').val(),
			"ruleOrder":parseInt($('#order').val()),
			"userType":$('#User').val(),
			"output": $('#output').val()
	}
	$.ajax({

		url : "./save",
		data : JSON.stringify(newRule),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success: function (data, textStatus, jqXHR) {
			$("#successModal").openModal({
		        dismissible:false
		    });

			
		},
		error: function (jqXHR, textStatus, errorThrown) {

		}
	});

	return false;
}
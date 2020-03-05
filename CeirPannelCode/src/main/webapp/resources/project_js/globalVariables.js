
/*$.getScript('../resources/project_js/CLDRPluralRuleParser.js');
	$.getScript('../resources/i18n_library/i18n.js');
	$.getScript('../resources/i18n_library/messagestore.js');
	$.getScript('../resources/i18n_library/fallbacks.js');
	$.getScript('../resources/i18n_library/language.js');
	$.getScript('../resources/i18n_library/parser.js');
	$.getScript('../resources/i18n_library/emitter.js');
	$.getScript('../resources/i18n_library/bidi.js');
	$.getScript('../resources/i18n_library/history.js');
	$.getScript('../resources/i18n_library/min.js');
	*/


$('input,select,textArea,button').attr('title', window.webkitURL ? ' ' : '');
var txnIdValue = $("body").attr("session-valueTxnID");
var data_lang_param =$("body").attr("data-lang-param") == 'km' ? 'km' : 'en';
var langDropdownValue=$("body").attr("data-lang-param");
var transactionIDValue= txnIdValue == 'null' ? $('#transactionID').val() : txnIdValue;

$('input').on('invalid', function(e) {
    setTimeout(function(){
        $('html, body').animate({scrollTop: document.documentElement.scrollTop - 150 }, 0);
    }, 0);
});


function fileDownload(fileName,fileType,txnId,doc_TypeTag)
{
	fileName=fileName.split("%20").join(" ");
console.log(" fileName "+fileName+" fileType  "+fileType+" txnId "+txnId+"  doc_TypeTag "+doc_TypeTag)
	$.ajax({
		url : "Consignment/dowloadFiles/"+fileType+'/'+fileName+'/'+txnId+'/'+doc_TypeTag,
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'GET',
		success : function(data) {
			console.log(data);
			if(data.url=='Not Found')
				{
				
				$('#fileFormateModal').openModal({
					dismissible:false
				});
				$('#fileErrormessage').text('')
				$('#fileErrormessage').text($.i18n('fileNotFound'));
				}
			else{
				console.log("file is found");
				 window.location.href=data.url;

			}
			
		},
		error : function() {
		
		}
	});
}








$("input[type=file]").keypress(function(ev) {
	return false;
	//ev.preventDefault(); //works as well

	});
	var featureId = window.parent.$('.navData li.active a').attr('data-featureid')

	
	
	
	
	
	
	
	
	
	function isFileValid(id,hiddenVal,editInputID) {

			var uploadedFileName = $("#"+id).val();
			uploadedFileName = uploadedFileName.replace(/^.*[\\\/]/, '');
			var ext = uploadedFileName.split('.').pop();
		
			var fileSize = ($("#"+id)[0].files[0].size);
			fileSize = (Math.round((fileSize / 1024) * 100) / 100)
		   if (uploadedFileName.length > 30) {
		       window.parent.$('#fileFormateModal').openModal({
		    	   dismissible:false
		       });
		   } 
			else if(ext!='csv')
				{
				window.parent.$('#fileFormateModal').openModal({
					dismissible:false
				});
				}
			else if(fileSize>='2000'){
				window.parent.$('#fileFormateModal').openModal({
					dismissible:false
				});
			}
			else {
				console.log("file formate is correct")
				
			}
			

		}
	
	
	

	function isImageValid(id) {
		
		var uploadedFileName = $("#"+id).val();
		uploadedFileName = uploadedFileName.replace(/^.*[\\\/]/, '');
		//alert("file extension=="+uploadedFileName)
		var ext = uploadedFileName.split('.').pop();

		var fileSize = ($("#"+id)[0].files[0].size);
		/*fileSize = (Math.round((fileSize / 100000) * 100) / 100)
		alert("----"+fileSize);*/
		fileSize = Math.floor(fileSize/1000) + 'KB';

		//alert(uploadedFileName+"----------"+ext+"----"+fileSize)

		if (uploadedFileName.length > 30) {
			$('#fileFormateModal').openModal();
			$('#fileErrormessage').text('');
			$('#fileErrormessage').text($.i18n('imageMessage'));
		} 
		else if(ext !='png')
		{
			$('#fileFormateModal').openModal({
				dismissible:false
			});
			$('#fileErrormessage').text('');
			$('#fileErrormessage').text($.i18n('imageMessage'));

		}
		else if(fileSize>=100){
			$('#fileFormateModal').openModal({
				dismissible:false
			});
			$('#fileErrormessage').text('');
			$('#fileErrormessage').text($.i18n('imageSize'));	
		}
		


	}
	
	
/*	function clearFileName(id) {
		$('#'+id).val('');
		$("#"+id).val('');
		$('#fileFormateModal').closeModal();
	}*/
/*	function clearFileName(id,hiddenVal,editInputID) {
		//$('#mainArea').contents().find('#fileNameEdit').val()
		var existingfile=$('#mainArea').contents().find("#"+hiddenVal).val();
		//$('#fileNameEdit').val('');
		$('#mainArea').contents().find("#"+id).val('');
		$('#fileFormateModal').closeModal();
		
		$('#mainArea').contents().find("#"+editInputID).val(existingfile);
	}

	*/
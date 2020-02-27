
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
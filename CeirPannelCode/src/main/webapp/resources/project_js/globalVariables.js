
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
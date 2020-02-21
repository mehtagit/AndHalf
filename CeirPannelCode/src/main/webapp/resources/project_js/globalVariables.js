var txnIdValue = $("body").attr("session-valueTxnID");
var data_lang_param =$("body").attr("data-lang-param") == 'km' ? 'km' : 'en';
var langDropdownValue=$("body").attr("data-lang-param");
var transactionIDValue= txnIdValue == 'null' ? $('#transactionID').val() : txnIdValue;


$('input').attr('title', window.webkitURL ? ' ' : '');
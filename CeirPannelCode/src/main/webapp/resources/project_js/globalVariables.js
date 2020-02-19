var txnIdValue = $("body").attr("session-valueTxnID");
var data_lang_param =$("body").attr("data-lang-param") == ('en_US' || 'en') ? 'en' : 'km';
var langDropdownValue=$("body").attr("data-lang-param");
var transactionIDValue= txnIdValue == 'null' ? $('#transactionID').val() : txnIdValue;

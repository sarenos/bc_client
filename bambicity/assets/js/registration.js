$(document).ready(function(){

	    $('#account').text(WRAPI.getUserAccount());   
	
$('form').submit(function() {
	$('#btn-save').hide();
	$('#loader').show();
	WRAPI.setNickName($('#input-nik').val());
	WRAPI.setBirthdayDate($('#input-date').val());
	WRAPI.setSex($('#input-sex').val());
   	WRAPI.createUser();
});
});

function failed(statusMsg)
{
	$('#btn-save').show();
	alert(statusMsg);
}


$(document).ready(function(){
		   $('#input-sex').val(WFFAPI.getFilterSex());
		   $('#input-min-age').val(WFFAPI.getFilterMinAge());
		   $('#input-max-age').val(WFFAPI.getFilterMaxAge());
		   $('#slider').val(WFFAPI.getFilterRadius());
		   $('#radius').text(WFFAPI.getFilterRadius());
		   $('#showOffline').attr('checked', WFFAPI.getFilterShowOffline());
		   


$('form').submit(function() {
				
	      	   WFFAPI.saveFilter($('#input-sex').val(), $('#input-min-age').val(), $('#input-max-age').val(), $('#slider').val(), $('#showOffline').is(":checked"));
	      	   $('#btn-save').prop('disabled', true);
});
});
function change(value)
{	 	 
	   $('#radius').text(value);
}

function success()
{
	$('#btn-save').prop('disabled', false);
}

function failed(message)
{
	alert(message);
	$('#btn-save').prop('disabled', false);
}
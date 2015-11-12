$(document).ready(function(){
		   $('#input-sex').val(WFFAPI.getFilterSex());
		   		   $('#input-min-age').val(WFFAPI.getFilterMinAge());
		   $('#input-max-age').val(WFFAPI.getFilterMaxAge());


$('form').submit(function() {
	 //  WFFAPI.setSex($('#input-sex').val());
	      	   WFFAPI.saveFilter($('#input-sex').val(), $('#input-min-age').val(), $('#input-max-age').val());

});
});
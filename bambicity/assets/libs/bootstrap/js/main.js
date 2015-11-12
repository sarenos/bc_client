$(document).ready(function(){
	   $('#account').text(API.getNickName());
	   $('#input-date').val(API.getBirthdayDate());
	   $('#input-sex').val(API.getSex());
	   $('#inputcity').val(API.getCity());
	   $('#input-site').val(API.getSiteUrl());
	   $('#photo').attr("src", API.getPhotoUrl());	  

		
   	$('#btn-edit').click( function(){
	   $('.form-control').prop('disabled', false);
	   $('#btn-save').show();
	   $('#btn-cancel').show();
	   $('#btn-edit').hide();

	}); 
	
	$('#btn-cancel').click( function(){
	   $('.form-control').prop('disabled', true);
	   $('#btn-save').hide();
	   $('#btn-cancel').hide();
	   $('#btn-edit').show();
	}); 
	
$('form').submit( function() {
			$('#loader').show();

	   API.setNickName($('#account').text());
	   API.setBirthdayDate($('#input-date').val());
	   API.setSex($('#input-sex').val());
	   API.setCity($('#inputcity').val());
	   API.setSiteUrl($('#input-site').val());	   
   	   API.updateInfo();
   	   
	   $('.form-control').prop('disabled', true);
	   			$('#loader').hide();

	   $('#btn-save').hide();
	   $('#btn-cancel').hide();
	   $('#btn-edit').show();});
	
});

function hide()
{
	   $("#container").hide();
}

function show()
{
	   $("#container").show();
		location.reload();
}
	
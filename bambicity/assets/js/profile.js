$(document).ready(function(){
	   $('#photo').attr("src", WPAPI.getPhoto());
	   $('#account').text(WPAPI.getNickName());
	   $('#input-date').val(WPAPI.getAge());
	   $('#input-sex').val(WPAPI.getSex());
	   
	   
	   	$('#photo').click( function(){
			WPAPI.changePhoto();
	}); 
	   
		
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
	   WPAPI.setNickName($('#account').text());
	   WPAPI.setAge($('#input-date').val());
	   WPAPI.setSex($('#input-sex').val());   
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
	
$(document).ready(function(){
	   $('#nickName').text(UAAPI.getNickName());
	   $('#age').text(UAAPI.getAge());
	   $('#sex').text(UAAPI.getSex());
	   $('#photo').attr("src", UAAPI.getPhoto());
	   $('#onlineStatus').text(UAAPI.getOnlineStatus());
	   
	   
	$('#btn-deleteFromFriend').click( function(){
	 UAAPI.deleteFriend();
	}); 
	
	    $('#btn-addToFriend').click( function(){
	 UAAPI.addToFriend();
	}); 
	
		    $('#btn-sendMessage').click( function(){
	 UAAPI.sendMessage();
	}); 
	
	$('#btn-showOnMap').click( function(){
	 UAAPI.showOnMap();
	}); 
	
	if(UAAPI.getFriendStatus() == 1)
		   {
			   	   $('#btn-deleteFromFriend').show(); 
			   	    $('#btn-addToFriend').hide(); 
			   	   $('#sendtofriend').hide();  
			   	     
			   	    
		   } else {
		   if(UAAPI.getFriendStatus() == 0){
		   
			        $('#btn-addToFriend').show();  
			   	    $('#btn-deleteFromFriend').hide(); 
			   	    $('#sendtofriend').hide(); 
			   	    
		   } else{
			        $('#btn-addToFriend').show();  
			   	    $('#btn-deleteFromFriend').hide(); 
			   	   	$('#sendtofriend').hide();   
				}
		   }
	
});	
function failed(statusMsg)
{
	alert(statusMsg);
}

function inviteSuccess()
{
	alert('Заявка отправлена');
	$('#btn-addToFriend').disabled = true;
}

function confirmSuccess()
{
	$('#btn-deleteFromFriend').show(); 
	$('#btn-addToFriend').hide(); 
	$('#sendtofriend').hide();
}

function deleteSuccess()
{
	$('#btn-addToFriend').show();  
	$('#btn-deleteFromFriend').hide(); 
	$('#sendtofriend').hide();  
}



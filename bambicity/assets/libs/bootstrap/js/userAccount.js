$(document).ready(function(){
	   
	    $('#btn-deleteFromFriend').click( function(){
	 WUAAPI.deleteFriend();
	}); 
	
	    $('#btn-addToFriend').click( function(){
	 WUAAPI.addToFriend();
	}); 
	
		    $('#btn-sendMessage').click( function(){
	 WUAAPI.sendMessage();
	}); 
	
	$('#btn-showOnMap').click( function(){
	 WUAAPI.showOnMap();
	}); 
	
	   });
	   
	   function showInfo()
	   {
			if(WUAAPI.getStatus() == '1')
		   {
			   	   $('#btn-deleteFromFriend').show(); 
			   	    $('#btn-addToFriend').hide(); 
			   	   $('#sendtofriend').hide();  
			   	     
			   	    
		   } else {
		   if(WUAAPI.getStatus() == '3'){
		   
		   		            $('#sendtofriend').show();  
		   
		            $('#btn-addToFriend').hide();  
			   	    $('#btn-deleteFromFriend').hide(); 
			   	    
		   } else{
			        $('#btn-addToFriend').show();  
			   	    $('#btn-deleteFromFriend').hide(); 
			   	    			   	   $('#sendtofriend').hide();  
			   	     
				}
		   }
		   
	   $('#account').text(WUAAPI.getNickName());
	   $('#date').text(WUAAPI.getBirthdayDate());
	   $('#sex').text(WUAAPI.getSex());
	   $('#city').text(WUAAPI.getCity());
	   $('#site').text(WUAAPI.getSiteUrl());
	   $('#photo').attr("src", WUAAPI.getPhotoUrl());			   
	   }
	   
	   function changeStatus()
	   {
	if(WUAAPI.getStatus() == '1')
		   {
			   	   $('#btn-deleteFromFriend').show(); 
			   	    $('#btn-addToFriend').hide(); 
			   	   $('#sendtofriend').hide();  
			   	     
			   	    
		   } else {
		   if(WUAAPI.getStatus() == '3'){
		   
		   		            $('#sendtofriend').show();  
		   
		            $('#btn-addToFriend').hide();  
			   	    $('#btn-deleteFromFriend').hide(); 
			   	    
		   } else{
			        $('#btn-addToFriend').show();  
			   	    $('#btn-deleteFromFriend').hide(); 
			   	    			   	   $('#sendtofriend').hide();  
			   	     
				}
		   }
	   }
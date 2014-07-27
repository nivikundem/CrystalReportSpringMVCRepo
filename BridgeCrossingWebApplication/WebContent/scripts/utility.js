function quickSearchFormSubmit(){
		$("#quickSearchForm").submit(function(event){    	
    	var textboxValue = $("#quickSearchTextbox").val();    	
    	if(textboxValue == ""){
    	$("#errormessage1").html("*");
    	event.preventDefault();
    	}
    	});
}

function sortFormSubmit(sortName){	 
	  document.forms["sortForm"].sortField.value=sortName;
	  document.forms["sortForm"].submit();	  
}


function crossingActivityFormSubmit(){	
	$("#crossingActivityForm").submit(function(event){      	 	
    	if(!checkifclicked()){
    	$("#errormessage2").html("*");
    	event.preventDefault();
    	}
    	});
}

function checkifclicked(){   
    var checkboxs=document.getElementsByName("pdf");
    var okay=false;
    for(var i=0,l=checkboxs.length;i<l;i++)
    {
        if(checkboxs[i].checked)
        {
            return true;
            break;
        }
    }
    if(!okay){
        return false;
    }      
    return okay;
}	  


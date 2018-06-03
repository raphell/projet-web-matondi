

function displayDays(idDay, nbrDay) {
	for (i=0 ; i<nbrDay ; i++){
		var day = document.getElementById("day"+i);
		if(i==idDay){
			day.style.display="block";
		}
		else{
			day.style.display ="none";
		}
	}
    
}




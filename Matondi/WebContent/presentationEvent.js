

function displayLineUp()
{
	var info = document.getElementById("info");
	var lineUp = document.getElementById("lineUp");
    info.style.display="none";
    lineUp.style.display="block";
}

function displayInformations()
{
	var info = document.getElementById("info");
	var lineUp = document.getElementById("lineUp");
    lineUp.style.display="none";
    info.style.display="block";
}


function displayDays(idDay, nbrDay) {
	var info = document.getElementById("info");
	info.style.display="none";
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
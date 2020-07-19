$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var naziv=$("#naziv").val();
	var adresa=$("#adresa").val();
	var brojCentrale=$("#telefon").val();
	var mail=$("#mail").val();
	var menadzer=$("#menadzer").val();
	
	var newBioskopJSON=formToJSON(naziv,adresa,brojCentrale,mail,menadzer);
	
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/bioskop/dodaj-bioskop",
		dataType:"json",
		contentType:"application/json",
		data:newBioskopJSON,
		success:function(){
			alert("Dodali ste bioskop.");
			window.location.href="Bioskopi.html";
		},
		error:function(data){
			alert("Doslo je do greske! Pokusajte ponovo.");
			window.location.href="DodajBioskopGreska.html";
		}
		
	});
	
});

function formToJSON(naziv,adresa,telefon,mail,menadzer){
	return JSON.stringify({
		"naziv":naziv,
		"adresa":adresa,
		"telefon":telefon,
		"mail":mail,
		"menadzer":menadzer
	});
}
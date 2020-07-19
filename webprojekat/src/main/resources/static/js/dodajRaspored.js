$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var bioskop=$("#nazivBioskopa").val();
	var film=$("#nazivFilma").val();
	var datum=$("#datum").val();
	var pocetak=$("#vreme").val();
	var cena=$("#cena").val();
	var sala=$("#oznaka").val();
	
	var newRasporedJSON=formToJSON(bioskop,film,datum,pocetak,cena,sala);
	
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/bioskop/dodaj-projekcije",
		dataType:"json",
		contentType:"application/json",
		data:newRasporedJSON,
		success:function(){
			alert("Dodali ste novi raspored projekcija.");
		
		},
		error:function(data){
			alert("Greska! Pokusajte ponovo!");
			
		}
	});
});

function formToJSON(bioskop,film,datum,vreme,cena,oznaka){
	return JSON.stringify({
		"bioskop":bioskop,
		"naziv":film,
		"datum":datum,
		"vreme":vreme,
		"cena":cena,
		"oznaka":oznaka
	});
}
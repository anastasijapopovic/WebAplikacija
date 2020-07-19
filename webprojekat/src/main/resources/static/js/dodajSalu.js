$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var oznakaSale=$("#oznaka").val();
	var kapacitet=$("#kapacitet").val();
	var bioskop=$("#bioskop").val();
	
	var newSalaJSON=formToJSON(oznakaSale,kapacitet,bioskop);
	
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/bioskop/dodaj-salu",
		dataType:"json",
		contentType:"application/json",
		data:newSalaJSON,
		success:function(){
			alert("Dodali ste salu.");
			window.location.href="Login.html";
		},
		error:function(data){
			alert("Greska! Pokusajte ponovo!");
			window.location.href="DodajSaluGreska.html";
		}
		
	});
	
});

function formToJSON(oznaka,kapacitet,bioskop){
	return JSON.stringify({
		"oznaka":oznaka,
		"kapacitet":kapacitet,
		"bioskop":bioskop
	});
}
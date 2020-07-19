$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var korisnickoIme=$("#korisnicko").val();
	var lozinka=$("#lozinka").val();
	var ime=$("#ime").val();
	var prezime=$("#prezime").val();
	var kontaktTelefon=$("#telefon").val();
	var mail=document.getElementById("mail").value;
	var datum=$("#datum").val();
	
	var newKorisnikJSON=formToJSON(korisnickoIme,lozinka,ime,prezime,kontaktTelefon,mail,datum);
	
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/bioskop/novi-menadzer",
		dataType:"json",
		contentType:"application/json",
		data:newKorisnikJSON,
		success:function(){
			alert(ime+" "+prezime+" je registrovan kao mendazer.");
			window.location.href="Login.html";
		},
		error:function(data){
			alert("Doslo je do greske! Pokusajte ponovo.");
		}
	});
	
});


function formToJSON(korisnicko,lozinka,ime,prezime,telefon,mail,datum){
	return JSON.stringify({
		"korisnicko":korisnicko,
		"lozinka":lozinka,
		"ime":ime,
		"prezime":prezime,
		"telefon":telefon,
		"mail":email,
		"datum":datum
	});
}
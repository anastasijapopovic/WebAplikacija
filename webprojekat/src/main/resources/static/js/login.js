$(document).on("submit","form",function(event){
	event.preventDefault();
	var loginKartica=$("#kartica").hide();

	
		
	var korisnicko=$("#korisnicko").val();
	var lozinka=$("#lozinka").val();
	
	var newKorisnikJSON=formToJSON(korisnicko,lozinka);
	
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/bioskop/registracija",
		dataType:"json",
		contentType:"application/json",
		data:newKorisnikJSON,
		success:function(data){
			console.log("SUCCESS: ",data);
			
			//$("#ime").append(data['ime']);
			//$("#korisnicko").append(data['korisnicko']);
			//$('#lozinka').append(data['lozinka']);
			$('#ime').append(data['ime']);
			$('#prezime').append(data['prezime']);
			$('#telefon').append(data['telefon']);
			$('#datum').append(data['datum']);
			$('#mail').append(data['mail']);
			$('#uloga').append(data['uloga']);
			
			  var profil=$("#profil1").removeClass("d-none").show();
			  if(data['uloga']=="admin"){
				  var pod=$("#admin").removeClass("d-none").show();
			  }else if(data['uloga']=="menadzer"){
				  var m="<a class='btnBioskopi btn-outline-light btn-lg btn-block' id="+data['id']+">Pregled bioskopa</a>";
				  m+="<a class='btnSale btn-outline-light btn-block' id="+data['id']+">Izmeni salu</a>";
				  m+="<a class='btnRepertoar btn-outline-light btn-lg btn-block' id="+data['id']+">Izmeni repertoar</a>";
				  
				   $("#menadzer").append(m);
				   $("#menadzer").removeClass("d-none").show();
			  }
			  else{
				  var rez="<a class='btnRezervisani btn-outline-light btn-lg btn-block' id="+data['id']+">Rezervisane karte</a>";
				   rez+="<a class='btnOdgledani btn-outline-light btn-lg btn-block' id="+data['id']+">Odgledani filmovi</a>";
				   rez+="<a class='btnNeocenjeni btn-outline-light btn-lg btn-block' id="+data['id']+">Neocenjeni filmovi</a>";
				   rez+="<a class='btnOcenjeni btn-outline-light btn-lg btn-block' id="+data['id']+">Ocenjeni filmovi</a>";
				  
				 $("#gledalac").append(rez);
				  $("#gledalac").removeClass("d-none").show();
			  }
			
		},
		error:function(data){
			var loginKartica=$("#kartica").show();
			alert("Neispravna lozinka ili korisnicko ime.");
		}
	});
	
});

function formToJSON(korisnicko,lozinka){
	return JSON.stringify({
		"korisnicko":korisnicko,
		"lozinka":lozinka
		
	});
}


//GLEDALAC- U KORISNIK KONTROLER
$(document).on('click', '.btnRezervisani', function () {            // kada je button (čija je class = btnSeeMore) kliknut
    $("#odgledaniFilmovi").hide();
    $("#ocenjeniFilmovi").hide();
    $(".sakrij").empty();

    // nakon što korisnik klikne button See more možemo i samo da se prebacimo na employee.html
    // tada ajax poziv za dobavljanje jednog zaposlenog moze da bude u fajlu employee.js
    $.ajax({
        type: "GET",
        url: "http://localhost:3050/bioskop/rezervisane-karte-gledaoc/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
        dataType: "json",
        success: function (data) {
        
        	for(i=0;i<data.length;i++){
        		var row="<tr class='sakrij'>";
        		row+="<td>"+data[i]['naziv']+"</td>";
        		row+="<td>"+data[i]['datum']+"</td>";
        		row+="<td>"+data[i]['vreme']+"</td>";
        		row+="<td>"+data[i]['cena']+"</td>";
        		row+="<td>"+data[i]['rezervisano']+"</td>";
        		row+="<td>"+data[i]['bioskop']+"</td>";
        		row+="<td>"+data[i]['oznaka']+"</td>";
        		
        		
        		 var btn = "<button class='btnOtkazi' value="+data[i]['gledaocId']+" id= " + data[i]['id']+ ">Otkazi</button>";
	              row += "<td>" + btn + "</td>"; 

	              var btn1 = "<button class='btnPotvrdi' value="+data[i]['gledaocId']+" id= " + data[i]['id']+ ">Potvrdite rezervaciju</button>";
		          row += "<td>" + btn1 + "</td>"; 
	              row+="</tr>";
	              
	              $('#tabela').append(row);
	              $("#listaKarte").removeClass("d-none").show();
	              
        	}                          
           
        },
        error: function (data) {
        	alert("Doslo je do greske, pokusajte ponovo!");
            console.log("ERROR : ", data);
        }
    });
});


$(document).on('click', '.btnOdgledani', function () {            // kada je button (čija je class = btnSeeMore) kliknut
	  $("#listaKarte").hide();
	  $("#ocenjeniFilmovi").hide();
	  $("#neocenjeniFilmovi").hide();
	  $(".sakrij").empty();

    // nakon što korisnik klikne button See more možemo i samo da se prebacimo na employee.html
    // tada ajax poziv za dobavljanje jednog zaposlenog moze da bude u fajlu employee.js
    $.ajax({
        type: "GET",
        url: "http://localhost:3050/bioskop/odgledani-filmovi-gledaoc/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
        dataType: "json",
        success: function (data) {
        	
        	for(i=0;i<data.length;i++){
        		var row="<tr class='sakrij'>";
        		row+="<td>"+data[i]['naziv']+"</td>";
        		row+="<td>"+data[i]['zanr']+"</td>";
        		row+="<td>"+data[i]['trajanje']+"</td>";
        		row+="<td>"+data[i]['ocena']+"</td>";
        		
        		
        		
	              row+="</tr>";
	              
	              $('#tabela1').append(row);
	              
	              $("#odgledaniFilmovi").removeClass("d-none").show();
	              
	              
        	}                          
           
        },
        error: function (data) {
        	alert("Doslo je do greske, pokusajte ponovo!");
            console.log("ERROR : ", data);
        }
    });
});


$(document).on('click', '.btnOcenjeni', function () {            // kada je button (čija je class = btnSeeMore) kliknut
	  $("#listaKarte").hide();
	  $("#odgledaniFilmovi").hide();
	  $("#neocenjeniFilmovi").hide();
	  $(".sakrij").empty();
	 

  // nakon što korisnik klikne button See more možemo i samo da se prebacimo na employee.html
  // tada ajax poziv za dobavljanje jednog zaposlenog moze da bude u fajlu employee.js
  $.ajax({
      type: "GET",
      url: "http://localhost:3050/bioskop/ocenjeni-filmovi-gledaoc/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
      dataType: "json",
      success: function (data) {
     
      	for(i=0;i<data.length;i++){
      		var row="<tr class='sakrij'>";
      		row+="<td>"+data[i]['naziv']+"</td>";
      		row+="<td>"+data[i]['zanr']+"</td>";
      		row+="<td>"+data[i]['trajanje']+"</td>";
      		row+="<td>"+data[i]['ocena']+"</td>";
      		
      		
      		
	              row+="</tr>";
	              
	              $('#tabela2').append(row);
	              
	              $("#ocenjeniFilmovi").removeClass("d-none").show();
	              
      	}                          
         
      },
      error: function (data) {
      	alert("Doslo je do greske, pokusajte ponovo!");
          console.log("ERROR : ", data);
      }
  });
});


$(document).on('click', '.btnNeocenjeni', function () {            // kada je button (čija je class = btnSeeMore) kliknut
	  $("#listaKarte").hide();
	  $("#odgledaniFilmovi").hide();
	  $("#ocenjeniFilmovi").hide();
	  $(".sakrij").empty();

// nakon što korisnik klikne button See more možemo i samo da se prebacimo na employee.html
// tada ajax poziv za dobavljanje jednog zaposlenog moze da bude u fajlu employee.js
$.ajax({
    type: "GET",
    url: "http://localhost:3050/bioskop/neocenjeni-filmovi-gledaoc/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
    dataType: "json",
    success: function (data) {
    	for(i=0;i<data.length;i++){
    		var row="<tr class='sakrij'>";
    		row+="<td>"+data[i]['naziv']+"</td>";
    		row+="<td>"+data[i]['zanr']+"</td>";
    		row+="<td>"+data[i]['trajanje']+"</td>";
    		row+="<td>"+data[i]['ocena']+"</td>";
    		

   		 var btn = "<button class='btnOceni' value="+data[i]['gledaocId']+"  id = " + data[i]['id'] + ">Oceni</button>";
             row += "<td>" + btn + "</td>"; 
             row+="</tr>";
             
	              
	              $('#tabela3').append(row);
	              
	              $("#neocenjeniFilmovi").removeClass("d-none").show();
	              
    	}                          
       
    },
    error: function (data) {
    	alert("Doslo je do greske, pokusajte ponovo!");
        console.log("ERROR : ", data);
    }
});
});


//MENADZER - MENADZER KONTROLER

$(document).on('click', '.btnBioskopi', function () {            // kada je button (čija je class = btnSeeMore) kliknuti
	$("#sale").hide();
	$("#repertoar").hide();
	$(".sakrij").empty();
	
	$.ajax({
		    type: "GET",
		url: "http://localhost:3050/bioskop/bioskopi/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data) {
			for(i=0;i<data.length;i++){
				var row="<tr class='sakrij'>";
				row+="<td>"+data[i]['naziv']+"</td>";
				row+="<td>"+data[i]['adresa']+"</td>";
				row+="<td>"+data[i]['telefon']+"</td>";
				row+="<td>"+data[i]['mail']+"</td>";
		
				var btn = "<button class='btnSale' id = " + data[i]['id'] + ">Pregled sala</button>";
				var btn1 = "<button class='btnRepertoar' id = " + data[i]['id'] + ">Repertoar</button>";
				row += "<td>" + btn + "</td>"; 
				row += "<td>" + btn1+ "</td>"; 
		        row+="</tr>";
		         
		              
		              $('#tabela4').append(row);
		              
		              $("#bioskopi").removeClass("d-none").show();
		              
			}                          
		   
		},
		error: function (data) {
			alert("Doslo je do greske, pokusajte ponovo!");
		    console.log("ERROR : ", data);
		    }
		});
});


$(document).on('click', '.btnSale', function () {            // kada je button (čija je class = btnSeeMore) kliknuti
	$("#bioskopi").hide(); 
	$("#repertoar").hide();
	$(".sakrij").empty();
	
	$.ajax({
		    type: "GET",
		url: "http://localhost:3050/bioskop/sala/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data) {
		
			for(i=0;i<data.length;i++){
				var row="<tr class='sakrij'>";
				row+="<td>"+data[i]['bioskop']+"</td>";
				row+="<td>"+data[i]['oznaka']+"</td>";
				row+="<td>"+data[i]['kapacitet']+"</td>";
				
				var btn = "<button class='btnIzmeniSalu' id = " + data[i]['id'] + ">Izmeni</button>";
				var btn1 = "<button class='btnUkloniSalu' id = " + data[i]['id'] + ">Ukloni</button>";
	             row += "<td>" + btn + "</td>"; 
	             row += "<td>" + btn1 + "</td>"; 
			
		
		        row+="</tr>";
		         
		        $('#tabela5').append(row);
				$("#sale").removeClass("d-none").show(); 
		             
		              
		             
		              
			} 
			var btn2 = "<a href='DodajSalu.html'><button class='btnDodajSalu' '>Dodaj novu salu</button></a>";
            
			row="<tr class='sakrij'><td colspan='5'>"+btn2+"</td></tr>";
			 $('#tabela5').append(row);
			$("#sale").removeClass("d-none").show();
			
		   
		},
		error: function (data) {
			alert("Doslo je do greske, pokusajte ponovo!");
		    console.log("ERROR : ", data);
		    }
		});
});


$(document).on('click', '.btnUkloniSalu', function () {            // kada je button (čija je class = btnSeeMore) kliknuti
	$("#bioskopi").hide(); 
	$("#repertoar").hide();
	$("#sale").hide();
	$(".sakrij").empty();
	
	$.ajax({
		    type: "GET",
		url: "http://localhost:3050/bioskop/ukloni-salu/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
			alert("Sala je uklonjena!");
		},
		error: function (data) {
			alert("Doslo je do greske, pokusajte ponovo!");
		    console.log("ERROR : ", data);
		    }
		});
});

$(document).on('click', '.btnIzmeniSalu', function () {        

	
	$.ajax({
		    type: "GET",
		    //uzimam podatke za gledaoca i od te terminske liste
		url: "http://localhost:3050/bioskop/izmena-sale/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
	
			
			var red="Kapacitet:<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
            red+="<input type='number' class='form-control' id='kapacitet' placeholder='Kapacitet sale' value="+data['kapacitet']+" ></div>"
            
            red+="Oznaka sale:<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
            red+="<input type='text' class='form-control' id='oznakaSale' placeholder='Oznaka sale' value="+data['oznaka']+"  ></div>"
            
            
			red+="<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
            red+="<input type='text' class='form-control' id='salaId' placeholder='Izabrnai id' value="+data['id']+"  disabled='disabled'></div>"
             $('#izmenaSala').append(red);
             $("#Izmena-sala").removeClass("d-none").show();
			 
			
		   
		},
		error: function (data) {
			alert("Doslo je do greske, pokusajte ponovo!");
			 window.location.href="Login.html";
		    console.log("ERROR : ", data);
		    }
		});
});


$(document).on('click', '#izmeni1', function () {            // kada je button (čija je class = btnSeeMore) kliknut
	event.preventDefault();
	$("#Izmena-sala").hide();
	
	var kapacitet=$("#kapacitet").val();
	var oznakaSale=$("#oznaka").val();
	var idSale=$("#salaId").val();
   
	var newSalaJSON=formToJSON2(kapacitet,oznaka,idSale);
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/bioskop/sala/izmena",
		dataType:"json",
		contentType:"application/json",
		data:newSalaJSON,
		success:function(data){
			alert("Sala je izmenjena!");
			window.location.href="Login.html";
			
			
		},
		error:function(data){
			
			alert("Doslo je do greske, pokusajte ponovo!");
			window.location.href="Login.html";
        }
    });
});

function formToJSON2(kapacitet,oznaka,id){
	return JSON.stringify({
		"kapacitet":kapacitet,
		"oznaka":oznaka,
		"id":id
		
	});
}





$(document).on('click', '.btnRepertoar', function () {            // kada je button (čija je class = btnSeeMore) kliknuti
	$("#bioskopi").hide(); 
	$(".sakrij").empty();
	
	$.ajax({
		    type: "GET",
		url: "http://localhost:3050/bioskop/repertoar/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
			for(i=0;i<data.length;i++){
        		var row="<tr class='sakrij'>";
        		row+="<td>"+data[i]['bioskop']+"</td>";
        		row+="<td>"+data[i]['naziv']+"</td>";
        		row+="<td>"+data[i]['datum']+"</td>";
        		row+="<td>"+data[i]['vreme']+"</td>";
        		row+="<td>"+data[i]['cena']+"</td>";
        		row+="<td>"+data[i]['rezervisano']+"</td>";
        		row+="<td>"+data[i]['oznaka']+"</td>";
        		
        		
        		 var btn = "<button class='btnIzmeniRepertoar' id = " + data[i]['id'] + ">Izmeni repertoar</button>";
	              row += "<td>" + btn + "</td>"; 
	              row+="</tr>";
	              
	              $('#tabela6').append(row);
	             
	              $("#repertoar").removeClass("d-none").show();
        	}       
			var btn2 = "<a href='DodajRaspored.html'><button class='btnDodajRepertoar' '>Dodajte novi raspored</button></a>";
            
			row="<tr class='sakrij'><td colspan='8'>"+btn2+"</td></tr>";
			 $('#tabela6').append(row);
			$("#repertoar").removeClass("d-none").show();
			 
			
		   
		},
		error: function (data) {
			alert("Doslo je do greske, pokusajte ponovo!");
		    console.log("ERROR : ", data);
		    }
		});
});

$(document).on('click', '.btnIzmeniRepertoar', function () {        
	//$("#repertoar").hide();
	
	$.ajax({  //BioiskopController
		    type: "GET",
		    //uzimam podatke za gledaoca i od te terminske liste
		url: "http://localhost:3050/bioskop/izmeni-repertoar/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
	
			
			var red="Naziv filma:<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
            red+="<input type='text' class='form-control' id='naziv' placeholder='Kapacitet sale' value="+data['naziv']+" ></div>"
            
            red+="Datum projekcije:<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
            red+="<input type='date' class='form-control' id='datum' placeholder='Datum projekcije' value="+data['datum']+"  ></div>"
            
            red+="Vreme projekcije:<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
            red+="<input type='text' class='form-control' id='vreme' placeholder='Vreme projekcije' value="+data['vreme']+"  ></div>"
            
            red+="Cena:<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
            red+="<input type='number' class='form-control' id='cena' placeholder='Cijena' value="+data['cena']+"  ></div>"
            
            red+="Sala:<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
            red+="<input type='text' class='form-control' id='oznaka' placeholder='Sala' value="+data['oznaka']+"  ></div>"
            
            
			red+="<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
            red+="<input type='text' class='form-control' id='repertoarId' placeholder='Izabrnai id' value="+data['id']+"  disabled='disabled'></div>"
             $('#izmenaRepertoara').append(red);
             $("#Izmena-repertoar").removeClass("d-none").show();
			 
			
		   
		},
		error: function (data) {
			alert("Doslo je do greske, pokusajte ponovo!");
			 window.location.href="Login.html";
		    console.log("ERROR : ", data);
		    }
		});
});

$(document).on('click', '#izmeni2', function () {            // kada je button (čija je class = btnSeeMore) kliknut
	event.preventDefault();
	$("#Izmena-repertoar").hide();
	
	var naziv=$("#naziv").val();
	var datum=$("#datum").val();
	var vreme=$("#vreme").val();
	var cena=$("#cena").val();
	var sala=$("#oznaka").val();
	var id=$("#repertoarId").val()
	
	

   
	var newRepertoarJSON=formToJSON3(naziv,datum,vreme,cena,sala,id);
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/bioskop/repertoar/izmenjivanje",
		dataType:"json",
		contentType:"application/json",
		data:newRepertoarJSON,
		success:function(data){
			alert("Uspesno izmenjeno!");
			window.location.href="Login.html";
			
			
		},
		error:function(data){
			
			alert("Doslo je do greske, pokusajte ponovo!");
			window.location.href="Login.html";
        }
    });
});

function formToJSON3(naziv,datum,vreme,cena,sala,id){
	return JSON.stringify({
		"naziv":naziv,
		"datum":datum,
		"vreme":vreme,
		"cena":cena,
		"oznaka":sala,
		"id":id
		
	});
}






$(document).on('click', '.btnOtkazi', function () {        

	
	$.ajax({
		    type: "GET",
		    //uzimam podatke za gledaoca i od te terminske liste
		url: "http://localhost:3050/bioskop/gledaoc-otkazuje-rezervaciju/" + this.id+"/"+this.value,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
			alert("Rezervacija je otkazana!");
		     window.location.href="Login.html";
			 
			
		   
		},
		error: function (data) {
			alert("Doslo je do greske, pokusajte ponovo!");
			 window.location.href="Login.html";
		    console.log("ERROR : ", data);
		    }
		});
});

$(document).on('click', '.btnPotvrdi', function () {        

	
	$.ajax({
		    type: "GET",
		    //uzimam podatke za gledaoca i od te terminske liste
		url: "http://localhost:3050/bioskop/gledaoc-potvrdjuje-rezervaciju/" + this.id+"/"+this.value,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
			alert("Karta je rezervisana!");
		     window.location.href="Login.html";
			 
			
		   
		},
		error: function (data) {
			alert("Doslo je do greske, pokusajte ponovo!");
			 window.location.href="Login.html";
		    console.log("ERROR : ", data);
		    }
		});
});


$(document).on('click', '.btnOcijeni', function () {        

	
	$.ajax({
		    type: "GET",
		    //uzimam podatke za gledaoca i od te terminske liste
		url: "http://localhost:3050/bioskop/oceni-film-gledaoc/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
			alert("Ocenili ste film!");
			var red="<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
            red+="<input type='text' class='form-control' id='podatak' placeholder='Izabrnai id' value="+data['id']+"  disabled='disabled'></div>"
             $('#fil').append(red);
             $("#ocenjivanje").removeClass("d-none").show();
			 
			
		   
		},
		error: function (data) {
			alert("Doslo je do greske, pokusajte ponovo!");
			 window.location.href="Login.html";
		    console.log("ERROR : ", data);
		    }
		});
});

$(document).on('click', '#ocjeniFilm', function () {            // kada je button (čija je class = btnSeeMore) kliknut
	event.preventDefault();
	$("#kartica1").hide();
	
	var korisnicko=$("#korisnicko").val();
	var lozinka=$("#lozinkaa").val();
	var ocena=$("#ocena").val();
	var id=$("#podatak").val();
   
	var newKorisnikJSON=formToJSON1(korisnicko,lozinka,ocena,id);
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/bioskop/ocenjivanje",
		dataType:"json",
		contentType:"application/json",
		data:newKorisnikJSON,
		success:function(data){
			alert("Ocenili ste film!");
			window.location.href="Filmovi.html";
			
			
		},
		error:function(data){
			
			alert("Doslo je do greske, pokusajte ponovo!");
			window.location.href="Filmovi.html";
        }
    });
});

function formToJSON1(korisnicko,lozinka,ocena,id){
	return JSON.stringify({
		"korisnicko":korisnicko,
		"lozinka":lozinka,
		"ocena":ocena,
		"id":id
		
	});
}
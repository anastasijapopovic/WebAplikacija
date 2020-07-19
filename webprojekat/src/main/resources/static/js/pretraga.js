$(document).on("submit","form",function(event){
	event.preventDefault();
	$("#kartica").hide();
	$("#kartica1").hide();
	
	var naziv=$("#naziv").val();
	var zanr=$("#zanr").val();
	var opis=$("#opis").val();
	var ocena=$("#ocena").val();
	var cena=$("#cena").val();
	var datum=$("#datum").val();
	
	
	var newFilmJSON=formToJSON(naziv,zanr,opis,ocena,cena,datum);
	
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/bioskop/filmovi/pretraga",
		dataType:"json",
		contentType:"application/json",
		data:newFilmJSON,
		success:function(data){
			for(i=0;i<data.length;i++){
				var col=" <div class=\" col-sm-6 col-lg-3\" style=\"float:left\">"
				col+="<div class=\"card mb-2\">";
				col+="<a class='btnSlika' id=" + data[i]['id'] + "><img class=\"card-img-top\" src='images/"+data[i]['naziv']+".jpg'></a>";
				col+="<div class=\"card-body\" id='filmKartica'> <h4 class=\"card-title\">"+data[i]['naziv']+"</h4>";
				col+="<p class=\"card-text\"><b>Zanr:</b>  <td>"+data[i]['zanr']+"</td>";
				col+="<br><b>Opis:</b><td>"+data[i]['opis']+"</td>";
				col+="<br><b>Ocena:</b><td>"+data[i]['ocena']+"</td></p>";
				col+="</div></div></div>";
				
				$('#table').append(col);
				$("#lista").removeClass("d-none").show();
			}
			
		},
		error:function(data){
			alert("Nije pronadjen film po ovim kriterijumima.");
			window.location.href="Pretraga.html";
			
		}
		
	});
	
});

function formToJSON(naziv,zanr,opis,ocena,cena,datum){
	return JSON.stringify({
		"naziv":naziv,
		"zanr":zanr,
		"opis":opis,
		"ocena":ocena,
		"cena":cena,
		"datum":datum
});
}

$(document).on('click', '.btnSlika', function () {           
	$("#lista").hide();
	$("#kartica1").hide();

  
    $.ajax({
        type: "GET",
        url: "http://localhost:3050/bioskop/filmovi/" + this.id,  
        dataType: "json",
        success: function (data) {
        	

			var div="<div class='mc-xl-5 ml-4'><p><strong>Naziv: </strong><span>"+data[0]['naziv']+"</span></p></div>";
			div+="<div class='mc-xl-5 ml-4'><p><strong>Zanr: </strong><span>"+data[0]['zanr']+"</span></p></div>";
			div+="<div class='mc-xl-5 ml-4'><p><strong>Trajanje: </strong><span>"+data[0]['trajanje']+" min</span></p></div>";
			div+="<div class='mc-xl-5 ml-4'><p><strong>Ocena: </strong><span>"+data[0]['ocena']+"</span></p></div>";
			div+="<div class='mc-xl-5 ml-4'><p><strong>Opis: </strong><span>"+data[0]['opis']+"</span></p></div>";
			$('#podaciFilm').append(div);
        	
        	for(i=0;i<data.length;i++){
        		var row="<tr>";
        		row+="<td>"+data[i]['naziv']+"</td>";
        		row+="<td>"+data[i]['datum']+"</td>";
        		row+="<td>"+data[i]['vreme']+"</td>";
        		row+="<td>"+data[i]['cena']+"</td>";
        		row+="<td>"+data[i]['rezervisano']+"</td>";
        		row+="<td>"+data[i]['bioskop']+"</td>";
        		row+="<td>"+data[i]['oznaka']+"</td>";
        		
        		
        		 var btn = "<button class='btnRezervisi' id = " + data[i]['id'] + ">Rezervisi</button>";
	              row += "<td>" + btn + "</td>"; 
	              row+="</tr>";
	              
	              $('#tabela').append(row);
	              $("#filmPrikaz").removeClass("d-none").show();
	              
        	}
        	
                                  
           
        },
        error: function (data) {
        	alert("Greska! Pokusajte ponovo!");
            console.log("ERROR : ", data);
        }
    });
});


$(document).on('click', '.btnRezervisi', function () {            
	$("#lista").hide();
	$("#filmPrikaz").hide();
	$("#kartica1").hide();
        $.ajax({
        type: "GET",
        url: "http://localhost:3050/bioskop/filmovi/rezervisi/" + this.id,  
        dataType: "json",
        success: function (data) {
        	
        	
        		
        		var red="<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
	             red+="<input type='text' class='form-control' id='podatak' placeholder='Izabrani id' value="+data['id']+"></div>"
	              $('#fil').append(red);
	              $("#kartica1").removeClass("d-none").show();
	              
        	
        	
                                  
           
        },
        error: function (data) {
        	alert("Greska! Pokusajte ponovo.");
            console.log("ERROR : ", data);
        }
    });
});

$(document).on('click', '#rezervacija', function () {            
	event.preventDefault();
	$("#kartica1").hide();
	
	var korisnickoIme=$("#korisnicko").val();
	var lozinka=$("#lozinka").val();
	var id=$("#podatak").val();
   
	var newKorisnikJSON=formToJSON1(korisnicko,lozinka,id);
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/bioskop/filmovi/rezervacija",
		dataType:"json",
		contentType:"application/json",
		data:newKorisnikJSON,
		success:function(data){
			alert("Upravo ste rezervisali.");
			
			
			
		},
		error:function(data){
			
			alert("Greska! Pokusajte ponovo.");
        }
    });
});

function formToJSON1(korisnicko,lozinka,id){
	return JSON.stringify({
		"korisnicko":korisnicko,
		"lozinka":lozinka,
		"id":id
		
	});
}
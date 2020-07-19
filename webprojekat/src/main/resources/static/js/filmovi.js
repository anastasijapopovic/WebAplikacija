$(document).ready(function(){
	$.ajax({
		type:"GET",
		url:"http://localhost:3050/bioskop/filmovi",
		dataType:"json",
		success:function(data){
			console.log("SUCESS:",data);
			for(i=0;i<data.length;i++){
				var col=" <div class=\" col-sm-6 col-lg-3\" style=\"float:left\">"
				col+="<a class='btnSlika' id=" + data[i]['id'] + "><div class=\"card-body\"> <h4 class=\"card-title\">"+data[i]['naziv']+"</h4></a>";
				col+="<p class=\"card-text\"><b>Zanr:</b>  <td>"+data[i]['zanr']+"</td>";
				col+="<br><b>Trajanje:</b> <td>"+data[i]['trajanje']+"</td>min";
				col+="<br><b>Opis:</b><td>"+data[i]['opis']+"</td>";
				col+="<br><b>Ocena:</b><td>"+data[i]['ocena']+"</td></p>";
				col+="</div></div></div>";
				
				$('#table').append(col);
			}
			
		},
		error:function(data){
			console.log("ERROR:",data);
		}
	});
});



$(document).on('click', '.sortNaziv', function () {            
    var employeesDiv = $("#table");                      
    employeesDiv.hide();   
    $("#sortiraniOcena").hide();
    $("#sortiraniTrajanje").hide();

    
    $.ajax({
        type: "GET",
        url: "http://localhost:3050/bioskop/filmovi/sortiranje-naziv",  
        dataType: "json",
        success: function (data) {
        	
        	for(i=0;i<data.length;i++){
				var col=" <div class=\" col-sm-6 col-lg-3\" style=\"float:left\">"
				col+="<a class='btnSlika' id=" + data[i]['id'] + "><div class=\"card-body\"> <h4 class=\"card-title\">"+data[i]['naziv']+"</h4></a>";
				col+="<p class=\"card-text\"><b>Zanr:</b>  <td>"+data[i]['zanr']+"</td>";
				col+="<br><b>Trajanje:</b> <td>"+data[i]['trajanje']+"</td>min";
				col+="<br><b>Opis:</b><td>"+data[i]['opis']+"</td>";
				col+="<br><b>Ocena:</b><td>"+data[i]['ocena']+"</td></p>";
				
				col+="</div></div></div>";
				
				$('#sortirani').append(col);
				$('#sortirani').show();
			}
			    
		},
		error:function(data){
			console.log("ERROR:",data);
		}
    });
});


$(document).on('click', '.sortOcena', function () {            
    var employeesDiv = $("#table");                      
    employeesDiv.hide(); 
    $("#sortirani").hide();
    $("#sortiraniTrajanje").hide();

    $.ajax({
        type: "GET",
        url: "http://localhost:3050/bioskop/filmovi/sortiranje-ocena",  
        dataType: "json",
        success: function (data) {
        	
        	for(i=0;i<data.length;i++){
				var col=" <div class=\" col-sm-6 col-lg-3\" style=\"float:left\">"
				
				col+="<a class='btnSlika' id=" + data[i]['id'] + "><div class=\"card-body\"> <h4 class=\"card-title\">"+data[i]['naziv']+"</h4></a>";
				col+="<p class=\"card-text\"><b>Zanr:</b>  <td>"+data[i]['zanr']+"</td>";
				col+="<br><b>Trajanje:</b> <td>"+data[i]['trajanje']+"</td>min";
				col+="<br><b>Opis:</b><td>"+data[i]['opis']+"</td>";
				col+="<br><b>Ocena:</b><td>"+data[i]['ocena']+"</td></p>";
				col+="</div></div></div>";
				
				$('#sortiraniOcena').append(col);
				$('#sortiraniOcena').show();
			}
			    
		},
		error:function(data){
			console.log("ERROR:",data);
		}
    });
});

$(document).on('click', '.sortTrajanje', function () {             
    var employeesDiv = $("#table");                     
    employeesDiv.hide();       
    $("#sortirani").hide();
    $("#sortiraniOcena").hide();

    
    $.ajax({
        type: "GET",
        url: "http://localhost:3050/bioskop/filmovi/sortiranje-trajanje",  
        dataType: "json",
        success: function (data) {
        	
        	for(i=0;i<data.length;i++){
				var col=" <div class=\" col-sm-6 col-lg-3\" style=\"float:left\">"
			
					col+="<a class='btnSlika' id=" + data[i]['id'] + "><div class=\"card-body\"> <h4 class=\"card-title\">"+data[i]['naziv']+"</h4></a>";
				col+="<p class=\"card-text\"><b>Zanr:</b>  <td>"+data[i]['zanr']+"</td>";
				col+="<br><b>Trajanje:</b> <td>"+data[i]['trajanje']+"</td>min";
				col+="<br><b>Opis:</b><td>"+data[i]['opis']+"</td>";
				col+="<br><b>Ocena:</b><td>"+data[i]['ocena']+"</td></p>";
				col+="</div></div></div>";
				
				$('#sortiraniTrajanje').append(col);
				$('#sortiraniTrajanje').show();
			}
			    
		},
		error:function(data){
			console.log("ERROR:",data);
		}
    });
});


$(document).on('click', '.btnSlika', function () {           
	$(".top").hide();
	$("#kartica1").hide();

    
    $.ajax({
        type: "GET",
        url: "http://localhost:3050/bioskop/filmovi/" + this.id,  
        dataType: "json",
        success: function (data) {
        	var div="<div class='mc-xl-5 ml-4'><p><strong>Naziv: </strong><span>"+data[0]['naziv']+"</span></p></div>";
			div+="<div class='mc-xl-5 ml-4'><p><strong>Zanr: </strong><span>"+data[0]['zanr']+"</span></p></div>";
			div+="<div class='mc-xl-5 ml-4'><p><strong>Trajanje: </strong><span>"+data[0]['trajanje']+" min</span></p></div>";
			div+="<div class='mc-xl-5 ml-4'><p><strong>Ocea: </strong><span>"+data[0]['ocena']+"</span></p></div>";
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
        	alert("Trenutno nema projekcija za izabrani film.");
        	window.location.href="Filmovi.html";
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
	             red+="<input type='text' class='form-control' id='podatak' placeholder='Izabrnai id' value="+data['id']+"  disabled='disabled'></div>"
	              $('#fil').append(red);
	              $("#kartica1").removeClass("d-none").show();
	              
        	
        	
                                  
           
        },
        error: function (data) {
        	alert("Doslo je do greske! Pokusajte ponovo!");
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
			alert("Rezervisano!");
			window.location.href="Filmovi.html";
			
			
		},
		error:function(data){
			
			alert("Doslo je do greske! Pokusajte ponovo!");
			window.location.href="Filmovi.html";
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
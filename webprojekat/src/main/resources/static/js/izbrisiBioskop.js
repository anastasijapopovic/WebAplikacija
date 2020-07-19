$(document).ready(function(){
	$.ajax({
		type:"GET",
		url:"http://localhost:3050/bioskop/bioskopi",
		dataType:"json",
		success:function(data){
			console.log("SUCCESS:",data);
			for(i=0;i<data.length;i++){
				var row="<tr>";
			
				row+="<td>"+data[i]['naziv']+"</td>";
				row+="<td>"+data[i]['adresa']+"</td>";
				row+="<td>"+data[i]['telefon']+"</td>";
				row+="<td>"+data[i]['mail']+"</td>";
				
				 var btn = "<button class='btnUkloni' id = " + data[i]['id'] + ">Ukloni</button>";
	              row += "<td>" + btn + "</td>"; 
	              var btn1 = "<button class='btnIzmeni' id = " + data[i]['id'] + ">Izmeni</button>";
	              row += "<td>" + btn1 + "</td>"; 
	            
	              
	              row+="</tr>";
	             row+="<br>";
	             
	             $('#tabela').append(row);
	             $("#bioskopi").removeClass("d-none").show();
	             

			}
		},
		error:function(data){
			console.log("ERROR:",data);
		}
	});
});

$(document).on('click', '.btnUkloni', function () {            


    $.ajax({
        type: "GET",
        url: "http://localhost:3050/bioskop/bioskopi/ukloni/" + this.id,  
        dataType: "json",
        success: function (data) {
            console.log("SUCCESS : ", data);
           alert("Bioskop je uklonjen!");
           window.location.href="IzbrisiBioskop.html";
           
        },
        error: function (data) {
        	alert("Greska! Pokusajte ponovo!");
            console.log("ERROR : ", data);
        }
    });
});


$(document).on('click', '.btnIzmeni', function () {        
	
	
	$.ajax({  
		    type: "GET",
		    
		url: "http://localhost:3050/bioskop/izmeni-bioskop/" + this.id,  
		dataType: "json",
		success: function (data){
	
			var red="Naziv bioskopa:<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
            red+="<input type='text' class='form-control' id='naziv' placeholder='Naziv bioskopa' value="+data['naziv']+" ></div>"
            
            red+="Adresa:<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
            red+="<input type='text' class='form-control' id='adresa' placeholder='Adresa' value="+data['adresa']+"  ></div>"
            
            red+="Broj telefona:<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
            red+="<input type='text' class='form-control' id='telefon' placeholder='telefon' value="+data['telefon']+"  ></div>"
            
            red+="e-mail:<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
            red+="<input type='text' class='form-control' id='mail' placeholder='mail' value="+data['mail']+"  ></div>"
            
          
            
			red+="<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
            red+="<input type='text' class='form-control' id='bioskopId' placeholder='Izabrani id' value="+data['id']+"  disabled='disabled'></div>"
             $('#izmenaBioskop').append(red);
             $("#Izmena-bioskop").removeClass("d-none").show();
			 
			
		   
		},
		error: function (data) {
			alert("Greska! Pokusajte ponovo.");
			 window.location.href="Login.html";
		    console.log("ERROR : ", data);
		    }
		});
});


$(document).on('click', '#izmeni', function () {            
	event.preventDefault();
	$("#Izmena-bioskop").hide();
	
	var naziv=$("#naziv").val();
	var adresa=$("#adresa").val();
	var broj=$("#telefon").val();
	var mail=$("#mail").val()
	var id=$("#bioskopId").val();
	

   
	var newBioskopJSON=formToJSON3(naziv,adresa,broj,mail,id);
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/bioskop/bioskopi/izmenjeni",
		dataType:"json",
		contentType:"application/json",
		data:newBioskopJSON,
		success:function(data){
			alert("Bioskop je izmenjen.");
			window.location.href="IzbrisiBioskop.html";
			
			
		},
		error:function(data){
			
			alert("Greska! Pokusajte ponovo.");
			window.location.href="IzbrisiBioskop.html";
        }
    });
});

function formToJSON3(naziv,adresa,telefon,mail,id){
	return JSON.stringify({
		"naziv":naziv,
		"adresa":adresa,
		"telefon":telefon,
		"mail":mail,
		"id":id
	});
}
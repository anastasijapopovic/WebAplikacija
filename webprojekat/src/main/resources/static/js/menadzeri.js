$(document).ready(function(){
	$.ajax({
		type:"GET",
		url:"http://localhost:3050/bioskop/lista-menadzera",
		dataType:"json",
		success:function(data){
			console.log("SUCCESS:",data);
			for(i=0;i<data.length;i++){
				var row="<tr>";
			
				row+="<td>"+data[i]['korisnicko']+"</td>";
				row+="<td>"+data[i]['ime']+"</td>";
				row+="<td>"+data[i]['prezime']+"</td>";
			
				
				 var btn = "<button class='btnUkloni' id = " + data[i]['id'] + ">Ukloni</button>";
	              row += "<td>" + btn + "</td>"; 
	              row+="</tr>";
	             row+="<br>";
	             
	             $('#tabela').append(row);
	             $("#menadzeri").removeClass("d-none").show();

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
        url: "http://localhost:3050/bioskop/obrisi-menadzera/" + this.id,  
        dataType: "json",
        success: function () {
        	alert("Menadzer je uklonjen.");
             window.location.href="Login.html";                           
           
        },
        error: function (data) {
        	alert("Greska! Pokusajte ponovo.");
            console.log("ERROR : ", data);
        }
    });
});
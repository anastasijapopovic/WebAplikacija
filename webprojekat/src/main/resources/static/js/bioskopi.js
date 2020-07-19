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
				
				 var btn = "<button class='btnRepertoar' id = " + data[i]['id'] + ">Repertoar</button>";
	              row += "<td>" + btn + "</td>"; 
	              row+="</tr>";
	              row+="<br>";
	             
	             $('#tabela').append(row);

			}
		},
		error:function(data){
			console.log("ERROR:",data);
		}
	});
});


$(document).on('click', '.btnRepertoar', function () { 
	$("#bioskopi").hide(); 
	$(".sakrij").empty();
	
	$.ajax({
		    type: "GET",
		url: "http://localhost:3050/bioskop/repertoar/" + this.id,  
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
        		        		 
	              row+="</tr>";
	              
	              $('#tabela1').append(row);
	             
	              $("#repertoar").removeClass("d-none").show();
        	}       
			
		},
		error: function (data) {
			alert("Greska! Pokusajte ponovo.");
		    console.log("ERROR : ", data);
		    }
		});
});
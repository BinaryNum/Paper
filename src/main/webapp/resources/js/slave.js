$(document).ready( function() {	
var i = 1;
	 //alert(i);
		if(i){
			setInterval("randomPaper()", 3000);
			
			$.ajax({
				type:"post",
				url:"RandomPaper",
				data:{interest:interest},
				contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
				dataType:"json",
				success:function(data){
					document.getElementById("img1").innerHTML = "<a href='paper_content_view.do?pId="+data[0].pId+"'>" +
					"<img width=240 height=200 src='resources/download/country/"+ data[0].image+"' alt='미리보기'></a>";
					document.getElementById("firsth1").innerHTML =data[0].name;
					document.getElementById("img2").innerHTML = "<a href='paper_content_view.do?pId="+data[1].pId+"'>" +
					"<img width=240 height=200 src='resources/download/country/"+ data[1].image+"' alt='미리보기'></a>";
					document.getElementById("firsth2").innerHTML =data[1].name;
					document.getElementById("img3").innerHTML = "<a href='paper_content_view.do?pId="+data[2].pId+"'>" +
					"<img width=240 height=200 src='resources/download/country/"+ data[2].image+"' alt='미리보기'></a>";
					document.getElementById("firsth3").innerHTML =data[2].name;
				
				
				
					},
				complete:function(data){
					
				},
				error:function(x,e){ if(x.status==0){ alert('You are offline!!n Please Check Your Network.'); }else if(x.status==404){ alert('Requested URL not found.'); }else if(x.status==500){ alert('Internel Server Error.'); }else if(e=='parsererror'){ alert('Error.nParsing JSON Request failed.'); }else if(e=='timeout'){ alert('Request Time out.'); }else {alert("오류");}}

			});
			}

});

function randomPaper(){	
	
	$.ajax({
		type:"post",
		url:"RandomPaper",
		data:{interest:interest},
		contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
		dataType:"json",
		success:function(data){
			document.getElementById("img1").innerHTML = "<a href='paper_content_view.do?pId="+data[0].pId+"'>" +
			"<img width=240 height=200 src='resources/download/country/"+ data[0].image+"' alt='미리보기'></a>";
			document.getElementById("firsth1").innerHTML =data[0].name;
			document.getElementById("img2").innerHTML = "<a href='paper_content_view.do?pId="+data[1].pId+"'>" +
			"<img width=240 height=200 src='resources/download/country/"+ data[1].image+"' alt='미리보기'></a>";
			document.getElementById("firsth2").innerHTML =data[1].name;
			document.getElementById("img3").innerHTML = "<a href='paper_content_view.do?pId="+data[2].pId+"'>" +
			"<img width=240 height=200 src='resources/download/country/"+ data[2].image+"' alt='미리보기'></a>";
			document.getElementById("firsth3").innerHTML =data[2].name;
		
			
			},
		complete:function(data){
			
		},
		error:function(x,e){ if(x.status==0){ alert('You are offline!!n Please Check Your Network.'); }else if(x.status==404){ alert('Requested URL not found.'); }else if(x.status==500){ alert('Internel Server Error.'); }else if(e=='parsererror'){ alert('Error.nParsing JSON Request failed.'); }else if(e=='timeout'){ alert('Request Time out.'); }else {alert("오류");}}
		
	});

	}
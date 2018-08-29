//이미지 클릭시 원본 크기로 팝업 보기
function doImgPop(img){ 
 img1= new Image(); 
 img1.src=(img); 
 imgControll(img); 
} 
  
function imgControll(img){ 
 if((img1.width!=0)&&(img1.height!=0)){ 
    viewImage(img); 
  } 
  else{ 
     controller="imgControll('"+img+"')"; 
     intervalID=setTimeout(controller,20); 
  } 
}
function viewImage(img){ 
 W=img1.width; 
 H=img1.height; 
 O="width="+W+",height="+H+",scrollbars=yes"; 
 imgWin=window.open("","",O); 
 imgWin.document.write("<html><head><title>:*:*:*: 이미지상세보기 :*:*:*:*:*:*:</title></head>");
 imgWin.document.write("<body topmargin=0 leftmargin=0>");
 imgWin.document.write("<img src="+img+" onclick='self.close()' style='cursor:pointer;' title ='클릭하시면 창이 닫힙니다.'>");
 imgWin.document.close();
}




var interest = "${sessionScope.interest}";
function onSuccess(googleUser) {
    console.log('Logged in as: ' + googleUser.getBasicProfile().getName());
    //location.href="Logincheck.do?name="+googleUser.getBasicProfile().getName()+"&id="+googleUser.getBasicProfile().getEmail();
    var id=googleUser.getBasicProfile().getEmail();
    $.ajax({
       type:"post",
       url:"Logincheck.do",
       data:{id:id},
       dataType:"json",
       success:function(data){
       if(data.idcheck==0){
          
          $("#hide").val(id);
          layer_open('layer2');
          //return false;
       }
       else{
          
          location.href="index.jsp";
       }
          
       
          },
       complete:function(data){
          
       },
       error:function(x,e){ if(x.status==0){ alert('You are offline!!n Please Check Your Network.'); }else if(x.status==404){ alert('Requested URL not found.'); }else if(x.status==500){ alert('Internel Server Error.'); }else if(e=='parsererror'){ alert('Error.nParsing JSON Request failed.'); }else if(e=='timeout'){ alert('Request Time out.'); }else {alert("오류");}}
       
    });
  
  }
function onFailure(error) {
    console.log(error);
  }
  function renderButton() {
    gapi.signin2.render('my-signin2', {
      'scope': 'profile email',
      'width': 240,
      'height': 50,
      'longtitle': true,
      'theme': 'dark',
      'onsuccess': onSuccess,
      'onfailure': onFailure
    });
  }
  
  function centerModal() {
      $(this).css('display', 'block');
      var $dialog = $(this).find(".modal-dialog");
      var offset = ($(window).height() - $dialog.height()) / 2;
      // Center modal vertically in window
      $dialog.css("margin-top", offset);
  }

  $('.modal').on('show.bs.modal', centerModal);
  $(window).on("resize", function () {
      $('.modal:visible').each(centerModal);
  });
  
  
  
$(document).ready( function() {
		
	
	
	$("#banner").load("resources//sub/banner.html");
	$("#footer").load("resources/sub/footer.html");
	$("#loginOk").load("resources/sub/loginOk.html");
	$("#loginNot").load("resources/sub/loginNot.html");

	 $("#interestbutton").click(function(){
	        var interested=$("#interested option:selected").val();
	         var id=$("#hide").val();
	        location.href="signup.do?id="+id+"&interested="+interested;
	        
	        
	     });

	  
	
	 
	 $(function(){
		    $("#auto").autocomplete({
		    
		        source : function( request, response ) {
		             $.ajax({
		                    type:"post",
		                    url:"Auto.do",
		                    dataType:"json",
		                    //request.term = $("#autocomplete").val()
		                    data: {keyword:request.term},
		                    success: function(data){
		                        response(
		                            $.map(data, function(item) {
		                            
		                                return {
		                                    label: item.auto,
		                                    value: item.auto,
		                                    id: item.auto
		                                }
		                                
		                            })
		                        );
		                    }
		                    
		               });
		            },
		            //최소글자
		            minLength: 1,  
		    });
		})


});








function Logincheck(){
	var id=$("#id").val();
	var password=$("#password").val();
	
	$.ajax({
		type:"post",
		url:"Logincheck.do",
		data:{id:id,password:password},
		dataType:"json",
		success:function(data){
		if(data.idcheck==1){
			
			alert("로그인에 성공 하였습니다!");
			location.href="index.do";
		}
		else{
			alert("아이디 또는 비밀번호를 확인하여 주십시오");
		}
		
		
			},
		complete:function(data){
			
		},
		error:function(x,e){ if(x.status==0){ alert('You are offline!!n Please Check Your Network.'); }else if(x.status==404){ alert('Requested URL not found.'); }else if(x.status==500){ alert('Internel Server Error.'); }else if(e=='parsererror'){ alert('Error.nParsing JSON Request failed.'); }else if(e=='timeout'){ alert('Request Time out.'); }else {alert("오류");}}
		
	});
}

var flag=0;
function doublecheck(){
		
		var id=$("#id").val();
		
		$.ajax({
			type:"post",
			url:"doublecheck.do",
			data:{id:id},
			dataType:"json",
			success:function(data){
			if(data.idflag==1){
				
				alert("사용할 수 없는 아이디입니다");
				
			}
			else{
				flag=1;
				alert("사용할 수 있는 아이디입니다");
			}
				
			
				},
			complete:function(data){
				
			},
			error:function(x,e){ if(x.status==0){ alert('You are offline!!n Please Check Your Network.'); }else if(x.status==404){ alert('Requested URL not found.'); }else if(x.status==500){ alert('Internel Server Error.'); }else if(e=='parsererror'){ alert('Error.nParsing JSON Request failed.'); }else if(e=='timeout'){ alert('Request Time out.'); }else {alert("오류");}}
			
		});
	}



function checkfield(){
	 //var lan=${lno};
	 if(flag==0){
		 alert("아이디 중복체크를 해주세요!");
		 return false;
	 }
	 
	 if(document.join.id.value==""){ //id값이 없을 경우
	alert("아이디를 입력하세요!");
	       //메세지 경고창
	 return false;
	 }
	 else if(document.join.pw1.value==""){
		alert("비밀번호를 입력하세요!");
	 return false;
	 
	 }else if(document.join.pw2.value==""){
	alert("비밀번호 확인을 입력하세요!");
	 return false;
	 }
	 else if(document.join.name.value==""){
			alert("이름을 입력하세요!");
			 return false;
			 }
	 else if(document.join.pw1.value!=document.join.pw2.value){
	 //비밀번호와 비밀번호확인의 값이 다를 경우
	alert("비밀번호가 일치하지 않습니다.");
	 return false;
	 }
	 else if(exptext.test(document.join.email.value)==false){
	 //이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우
	 
	 alert("이 메일형식이 올바르지 않습니다.");
	 return false;
	 }
		 
	 else{
	alert("회원가입을 축하합니다.");
	return true;
	 }
	 

	 
	}


function translatee(){

	 //form태그 내의 모든 데이터 보내주기
	var lang1=$("#lang1 option:selected").val();
	var lang2=$("#lang2 option:selected").val();
	var sentence=$("#korea").val();
	
	$.ajax({
		type:"post",
		url:"translate.do",
		data:{lang1:lang1,lang2:lang2,sentence:sentence},
		dataType:"json",
		success:function(data){
			$("#english").val(data.trans);
			
		},
		complete:function(data){
			
		},
		error:function(x,e){ if(x.status==0){ alert('You are offline!!n Please Check Your Network.'); }else if(x.status==404){ alert('Requested URL not found.'); }else if(x.status==500){ alert('Internel Server Error.'); }else if(e=='parsererror'){ alert('Error.nParsing JSON Request failed.'); }else if(e=='timeout'){ alert('Request Time out.'); }else { alert('Unknow Error.n'+x.responseText); } }
		

		
	});
}

function change(){

    var lang1=$("#lang1").val(); //form태그 내의 모든 데이터 보내주기
    var lang2=$("#lang2").val();
    $("#lang1").val(lang2);
    $("#lang2").val(lang1);
 }

function layer_open(el){
    
    var temp = $('#' + el);
    var bg = temp.prev().hasClass('bg');    //dimmed 레이어를 감지하기 위한 boolean 변수
   
    if(bg){
    $('.layer').fadeIn();   //'bg' 클래스가 존재하면 레이어가 나타나고 배경은 dimmed 된다.
    }else{
    temp.fadeIn();
    }
    // 화면의 중앙에 레이어를 띄운다.
  
    if (temp.outerHeight() < $(document).height() ) temp.css('margin-top', '-'+temp.outerHeight()/2+'px');
    else temp.css('top', '0px');
    if (temp.outerWidth() < $(document).width() ) temp.css('margin-left', '-'+temp.outerWidth()/2+'px');
    else temp.css('left', '0px');

    temp.find('a.cbtn').click(function(e){
    if(bg){
    $('.layer').fadeOut(); //'bg' 클래스가 존재하면 레이어를 사라지게 한다.
    }else{
    temp.fadeOut();
    }
    e.preventDefault();

    });
  
     

    $('.layer .bg').click(function(e){  //배경을 클릭하면 레이어를 사라지게 하는 이벤트 핸들러
  
    $('.layer').fadeOut();

    e.preventDefault();

    });

     
    }  
 


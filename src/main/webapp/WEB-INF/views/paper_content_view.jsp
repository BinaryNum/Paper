<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="resources/css/master.css">
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="resources/js/bootstrap.min.js"></script>
	 <script src="resources/js/master.js"></script>
    <title>논문 유통 사이트</title>
</head>
<body>
<div class="container">
    <!--배너부분 jquery활용해서 처리-->
  <header id="banner" class="jumbotron"></header>

<!--본 화면-->
  <div id="content" class="row">
    <!--로그인jquery 활용---->
      <section id="loginUI" class="col-md-3">
      	<c:if test="${userid eq null}">
      		<div id="loginNot">
	          
         	</div>
         </c:if>
         <c:if test="${userid ne null}">
         <h5> ${userid} 님!</h5>
         	<div id="loginOk">
	         	
            </div>
         </c:if>
      </section>
      
      
      <!--메인 컨텐츠-->
      <div id="main" class="col-md-9">
        <div class="card">
		  <h3 class="card-header">${content_view.name }</h3>
		  <div class="card-body">
		    <h5 class="card-title">${content_view.country } / ${content_view.field }</h5>
		    <div id="download" class="btn-group">
		    <form action="https://www.paypal.com/cgi-bin/webscr" method="post" target="window.open()">
				<input type="hidden" name="cmd" value="_s-xclick">
				<input type="hidden" name="hosted_button_id" value="3T8AMWSBR9B9U">
				<input type="image" src="https://www.paypalobjects.com/en_US/i/btn/btn_paynow_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
				<img alt="" border="0" src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1">
			</form>
		    <form action="Download" method="post">
		    <input name="download" type="hidden" value="${content_view.download }">
	         <input type="submit" class="btn btn-outline-secondary" value="결제">
			</form>
			<a href="basket?pId=${content_view.pId }">
			<button   class="btn btn-outline-secondary" >담기</button>
          	</a>
          	</div>
		    <!-- <h6 class="card-subtitle text-muted"></h6> -->
		    
		  </div>
		  <img onclick="doImgPop('resources/download/country/${content_view.image }')"  alt="논문 미리보기"  src="resources/download/country/${content_view.image }">
		  <center><h3>논문 미리보기</h3></center>
		  <div class="card-body">
		    <p class="card-text">저자 : ${content_view.author } 
		    <br />
			기관 : ${content_view.institution }
			<br />
		    </p>
		   
		  </div>
		  <div class="card-footer text-muted">
		    	 ${content_view.pDate }
		    	 <a href="paper_list?country=${content_view.country }&field=${content_view.field }">목록보기</a>
		  </div>
		</div>
      </div>
  </div>

    <!--바닥부분 jquery 활용-->
   <footer id="footer" class="footer"></footer>
</div>

</body>
</html>

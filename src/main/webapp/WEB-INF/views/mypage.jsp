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
       <section id="board">
            <!--논문리스트-->
          <h3>나의 관심 논문</h3>
          <br>
          
          <%
			int i=0;
          	if(request.getParameter("i")!=null){
			    	i=Integer.parseInt(request.getParameter("i"));
			    }	
			   int j = 6 * i; %>

          <table class="table">
          <c:forEach  items="${basket}" begin="<%=j %>" end="<%=j+5 %>" var="dto">
          	<tr>
          		<td style="padding-right:0;">
          			<img width="100" height="120" onclick="doImgPop('resources/download/country/${dto.image }')" src="resources/download/country/${dto.image }" >
          			 
          		</td>
          		<td style="padding-left:0; width:100%;">
			          <div class="card border-dark" style=" height:100%;">
						  <div class="card-body">
						    <blockquote class="card-blockquote">
							   
							     <a href="paper_content_view?pId=${dto.pId}"> <p>
							      	${dto.name }</p>
								</a>
							  		<div align="right"><a href="basket_delete?pId=${dto.pId }"><strong>삭제</strong></a></div>
							  </blockquote>
						  </div>
						</div>
				</td>
			</tr>
			</c:forEach>
			<c:forEach  items="${basket}" begin="1" end="1" var="dto">
			<tr>
			<td colspan="4"> </td>
			<td>
			<%if(i==0){ %>
				<!--페이지-->
	          <div id="page">
	            <ul class="pagination pagination-sm">
	              <li class="page-item">
	                <a class="page-link" href="/PAPER/mypage?i=<%=i+1%>">&raquo;</a>
	              </li>
	            </ul>
	          </div>
         	<%} else { %>
         
          	<div id="page">
	            <ul class="pagination pagination-sm">
	              <li class="page-item">
	                <a class="page-link" href="/PAPER/mypage?i=<%=i-1%>">&laquo;</a>
	              </li>
	              <li class="page-item">
	                <a class="page-link" href="/PAPER/mypage?i=<%=i+1%>">&raquo;</a>
	              </li>
	            </ul>
	          </div>
	         <%}  %>
          </td>
		</tr>
		</c:forEach>
		</table>
		  
          </section>
      </div>
  </div>

    <!--바닥부분 jquery 활용-->
   <footer id="footer" class="footer"></footer>
</div>

</body>
</html>

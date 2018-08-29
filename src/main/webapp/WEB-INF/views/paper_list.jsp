<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <!--  <link rel="stylesheet" href="css2/bootstrap.min.css"> -->
 <link href="resources/css/bootstrap.min.css" rel="stylesheet">
 
	  <meta name="google-signin-client_id" content="285825034341-vvd83uvo76oe44q95p6i1ibvf0ic628m.apps.googleusercontent.com">
   <meta charset="utf-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
   
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
   <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
 <link href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" rel="Stylesheet"></link>
 <link rel="stylesheet" href="resources/css/master.css">
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
      <section id="login" class="col-md-3">
      	<c:if test="${userid eq null}">
      		<div id="loginNot">
	          
         	</div>
         </c:if>
         <c:if test="${userid ne null}">
         	<h5> ${userid} 님!</h5>
         	<div id="loginOk">
	         	
            </div>
         </c:if>
                  <br /><br /><br />
          <div class="list-group " >
           <h4 class="dropdown-toggle">국내</h4>
            <a class="list-group-item  list-group-item-action  "  href="paper_list?country=Domestic&field=Economy"  >
              	경제
            </a>
            <a class="list-group-item  list-group-item-action  "   href="paper_list?country=Domestic&field=politics"  >
              	정치
            </a>
            <a class="list-group-item  list-group-item-action  "  href="paper_list?country=Domestic&field=IT"   >
              	IT
            </a>
          	<h4 class="dropdown-toggle">해외</h4>
            <a class="list-group-item  list-group-item-action  " href="paper_list?country=oversea&field=Economy" >
              	경제
            </a>
            <a class="list-group-item  list-group-item-action  " href="paper_list?country=oversea&field=politics" >
              	정치
            </a>
            <a class="list-group-item  list-group-item-action  "   href="paper_list?country=oversea&field=IT"  >
              	IT
            </a>
           	
           
          </div>
         
      </section>
      
      <!--메인 컨텐츠-->
      <div id="main" class="col-md-9">
     <form action="search" method="post" class="form">
					<div class="input-group">
                      <div class="input-group-addon">$</div>
                      <input class="form-control" name="search"  id="auto" type="text" placeholder="Search">
                      <div class="input-group-addon"><button class="btn btn-outline-secondary" type="submit">Search</button></div>
                    </div>
		</form>
 	
          <section id="board">
            <!--논문리스트-->
          <h3>논문자료</h3>
          <br>
          
          <%
			int i=0;
          	if(request.getParameter("i")!=null){
			    	i=Integer.parseInt(request.getParameter("i"));
			    }	
			   int j = 6 * i; %>

          <table class="table">
          <c:forEach  items="${paper}" begin="<%=j %>" end="<%=j+5 %>" var="dto">
          	<tr>
          		<td style= "padding-right: 0;">
          			<img width="100" height="120" onclick="doImgPop('resources/download/country/${dto.image }')" src="resources/download/country/${dto.image }" >
          		</td>
          		<td style= "padding-left: 0;width:100%; ">
			          <div class="card border-dark" style="height:100%;">
						  <div class="card-body">
						    <blockquote class="card-blockquote">
							    	<a href="paper_content_view?pId=${dto.pId}">
							      <p>${dto.name }</p>
							      <footer>${dto.country } / <cite title="Source Title">${dto.field }</cite></footer>
							  </a>
							  </blockquote>
						  </div>
						</div>
				</td>
			</tr>
			</c:forEach>
			<c:forEach  items="${paper}" begin="1" end="1" var="dto">
			<tr>
			<c:if test="${userid eq 'admin' }">
			<td colspan="4"> <a href="paper_write_view">
								<button class="btn" type="button">논문등록</button>
							</a> </td>
			</c:if>
			<c:if test="${userid ne 'admin' }">
				<td colspan="4"></td>
			</c:if>
			<td>
			<%if(i==0){ %>
				<!--페이지-->
	          <div id="page">
	            <ul class="pagination pagination-sm">
	              <li class="page-item">
	                <a class="page-link" href="paper_list?i=<%=i+1%>&country=${dto.country}&field=${dto.field}">&raquo;</a>
	              </li>
	            </ul>
	          </div>
         	<%} else { %>
         
          	<div id="page">
	            <ul class="pagination pagination-sm">
	              <li class="page-item">
	                <a class="page-link" href="paper_list?i=<%=i-1%>&country=${dto.country}&field=${dto.field}">&laquo;</a>
	              </li>
	              <li class="page-item">
	                <a class="page-link" href="paper_list?i=<%=i+1%>&country=${dto.country}&field=${dto.field}">&raquo;</a>
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
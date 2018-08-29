<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta name="google-signin-client_id" content="348806701632-feua9p2g63ro9sdfb89n7t7d4i89rjoh.apps.googleusercontent.com">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="google-signin-client_id" content="348806701632-feua9p2g63ro9sdfb89n7t7d4i89rjoh.apps.googleusercontent.com">
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

      </section>
      
      <!--메인 컨텐츠-->
      <div id="main" class="col-md-9">
          <section id="board">
            <!--게시판-->
              <h3>커뮤니티</h3>
            <table class="table table-striped table-hover table-bordered">
                <thead class="thead-dark">
                  <tr>
                    <th>#</th>
                    <th>작성자</th>
                    <th>제목</th>
                    <th>날짜</th>
                    <th>Hit</th>
                  </tr>
                </thead>
                <tbody>
			    <%int i = 0;
                	if(request.getParameter("i")!=null){
			    	i=Integer.parseInt(request.getParameter("i"));
			    }	
			    	int j=8*i; %>            
			    
                <!-- c:forEach items="${list}" var="dto" -->
                <c:forEach  items="${list}" var="dto">
           			<c:if test="${dto.bName eq 'admin'}">
		                	<tr class="table-info">
								<td><<공지>></td>
								<td>관리자</td>
								<td>
									<a href="content_view?bId=${dto.bId}">${dto.bTitle}</a></td>
								<td>${dto.bDate}</td>
								<td>${dto.bHit}</td>
							</tr>
					</c:if>
				</c:forEach>
				<c:forEach  items="${list}" begin="<%=j %>" end="<%=j+7 %>" var="dto">
				<c:if test="${dto.bName ne 'admin' }">
                	<c:if test="${dto.bIndent eq 0}">
						
		                	<tr class="table-success">
								<td><<일반>></td>
								<td>${dto.bName}</td>
								<td>
									<a href="content_view?bId=${dto.bId}">${dto.bTitle}</a></td>
								<td>${dto.bDate}</td>
								<td>${dto.bHit}</td>
							</tr>
					</c:if>
					<c:if test="${dto.bIndent ne 0}">
						<tr class="table-warning">
							<td><<답글>></td>
							<td>${dto.bName}</td>
							<td>
								<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>&raquo;
								<a href="content_view?bId=${dto.bId}">${dto.bTitle}</a></td>
							<td>${dto.bDate}</td>
							<td>${dto.bHit}</td>
						</tr>
					</c:if>
				</c:if>
				</c:forEach>
		<tr>
			<c:if test="${userid eq null }">
				<td colspan="4"></td>
			</c:if>
			<c:if test="${userid ne null }">
			
			<td colspan="4"> <a href="write_view">
								<button class="btn" type="button">글작성</button>
							</a> </td>
			</c:if>
			<td>
			<%if(i==0) {%>
				<!--페이지-->
	          <div id="page">
	            <ul class="pagination pagination-sm">
	              <li class="page-item">
	                <a class="page-link" href="list?i=<%=i+1%>">&raquo;</a>
	              </li>
	            </ul>
	          </div>
         	<%}else{ %>
          	<div id="page">
	            <ul class="pagination pagination-sm">
	              <li class="page-item">
	                <a class="page-link" href="list?i=<%=i-1%>">&laquo;</a>
	              </li>
	              <li class="page-item">
	                <a class="page-link" href="list?i=<%=i+1%>">&raquo;</a>
	              </li>
	            </ul>
	          </div>
	         <%} %>
          </td>
		</tr>
                </tbody>
                </table>
          </section>
      </div>
  </div>

      <!--바닥부분 jquery 활용-->
   <footer id="footer" class="footer"></footer>
</div>
<div class="container">
	<div class="row">
            <!-- Button trigger modal -->
        
            
            <!-- Modal -->
          <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="myModalLabel">관심분야 선택</h4>
                  </div>
                  <div class="modal-body">
                   <form id="interest">
					<select name="interested" id="interested">
		        	<option>IT</option>
		        	<option>경제</option>
		        	<option>정치</option>
		        	</select>
		        	<input type="hidden" id="hide">
		        	</form>
                  </div>
                  <div class="modal-footer">
                  
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button"  id="interestbutton" class="btn btn-primary" data-dismiss="modal" >Confirm</button>
                   
                  </div>
                </div>
              </div>
            </div>
	</div>
</div>
</body>
</html>
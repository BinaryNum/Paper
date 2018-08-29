<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>


  <!--  <link rel="stylesheet" href="css2/bootstrap.min.css"> -->
 <link href="resources/css/bootstrap.min.css" rel="stylesheet">
 
	  <meta name="google-signin-client_id" content="982296015885-nefb42l3b6k09ae80f76s3p3euo1c9aa.apps.googleusercontent.com">
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
  <c:if test="${userid ne null }">
  <script src="resources/js/slave.js" charset="utf-8"></script>
</c:if>
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
         	
		</select>
		
         
         </c:if>
         <c:if test="${userid ne null}">
         	 <h5> ${userid} 님!</h5>
         	<div id="loginOk">
	         	
            </div>
         </c:if>
         
	       
		
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
        <!--추천 논문-->
          <section id="newDocument">
              <h3>추천 논문</h3>
              <c:if test="${userid ne null }">
              <div class="row">
                <div class="col-sm-6 col-md-4">
                  <div class="thumbnail">
                    
                    <div class="caption">
                    	<div id = "img1"></div>
                      <h6 id="firsth1">제목</h6>
                    </div>
                  </div>
                </div>
                <div class="col-sm-6 col-md-4">
                  <div class="thumbnail">
                   
                    <div class="caption">
                    <div id = "img2"></div>
                       <h6 id="firsth2">제목</h6>
                     	
                         
                    </div>
                  </div>
                </div>
                <div class="col-sm-6 col-md-4">
                  <div class="thumbnail">
                   
                    <div class="caption">
                    <div id = "img3"></div>
                       <h6 id="firsth3">제목</h6>
                     	
                          
                    </div>
                  </div>
                </div>
              </div>
             </c:if>
             <c:if test="${userid eq null }">
             	<div class="row">
                <div class="col-sm-6 col-md-4">
                  <div class="thumbnail">
                    
                    <div class="caption">
                    	<div id = "img1"><img width=240 height=200 src="resources/img/noimage.png" alt="..."></div>
                      <h6 id="firsth1">로그인 해야 가능합니다.</h6>
                    </div>
                  </div>
                </div>
                <div class="col-sm-6 col-md-4">
                  <div class="thumbnail">
                   
                    <div class="caption">
                    <div id = "img2"><img width=240 height=200 src="resources/img/noimage.png" alt="..."></div>
                       <h6 id="firsth2">로그인 해야 가능합니다.</h6>
                     	
                         
                    </div>
                  </div>
                </div>
                <div class="col-sm-6 col-md-4">
                  <div class="thumbnail">
                   
                    <div class="caption">
                    <div id = "img3"><img width=240 height=200 src="resources/img/noimage.png" alt="..."></div>
                       <h6 id="firsth3">로그인 해야 가능합니다.</h6>
                     	
                          
                    </div>
                  </div>
                </div>
              </div>
             </c:if>
          </section>

          <section id="board">
            <!--게시판<-->
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
                <!-- c:forEach items="${list}" var="dto" -->
                <c:forEach  items="${list}" begin="0" end="7" var="dto">
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
			<td><a id="page" href="./list?i=0" class="btn btn-outline-primary" role="button">More
							</a></td>
		</tr>
                </tbody>
                </table>
          </section>
      </div>
  </div>

      <!--바닥부분 jquery 활용-->
   <footer id="footer" class="footer"></footer>
   <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
</div>
 <div class="layer">
		<div class="bg"></div>
		<div id="layer2" class="pop-layer">
		<div class="pop-container">
		<div class="pop-conts">
		<!--content //-->
		<p class="ctxt mb20">Thank you.<br>
		   <form id="interest">
		               <select name="interested" id="interested">
		                 <option>IT</option>
		                 <option>Economy</option>
		                 <option>Politics</option>
		                 </select>
		                 <input type="hidden" id="hide">
		                 </form>
		</p>
		<div class="btn-r">
		<a href="#" class="cbtn" id="interestbutton">Confirm</a>
		<a href="#" class="cbtn">Close</a>
		</div>
		<!--// content-->
		</div>
		</div>
		</div>
		</div>
</body>
</html>

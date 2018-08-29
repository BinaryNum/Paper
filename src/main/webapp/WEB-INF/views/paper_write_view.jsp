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
      </section>
      
      <!--메인 컨텐츠-->
      <div id="main" class="col-md-9">
          <section id="board">
            <!--게시판<-->
              <h3>공지 게시판</h3>
            <table class="table">
          
		<form action="paper_write" method="post" enctype="multipart/form-data">
			<tr>
				<td> 카테고리&nbsp; &nbsp; &nbsp; &nbsp;  </td>
				<td>
					<select class="form-control" name="country">
						<option value="Domestic">국내</option>
						<option value="oversea">해외</option>
					</select>
					<select class="form-control" name="field">
						<option value="Economy">경제</option>
						<option value="politics">정치</option>
						<option value="IT">IT</option>
					</select>
				
			</tr>
			<tr>
				<td> 논문제목 </td>
				<td> <input class="form-control" type="text" name="name" size = "100"> </td>
			</tr>
			<tr>
				<td> 학술기관 </td>
				<td> <input class="form-control" type="text" name="institution" size = "100"> </td>
			</tr>
			<tr>
				<td> 저자 </td>
				<td> <input class="form-control" type="text" name="author" size = "100"></textarea> </td>
			</tr>
			<tr>
				<td> 이미지 </td>
				<td><input class="form-control"   type="file" name="image" >※ 영문파일만 입력 가능합니다. </td>
				
			</tr>
			<tr>
				<td> 파일 </td>
				<td><input class="form-control"   type="file" name="download" >※ 영문파일만 입력 가능합니다. </td>
				
			</tr>
			<tr >
				<td colspan="2"> <input type="submit"  class="btn" value="입력"> &nbsp;&nbsp; <a href="list">목록보기</a></td>
			</tr>
			
		</form>
	</table>
          </section>
      </div>
  </div>

      <!--바닥부분 jquery 활용-->
   <footer id="footer" class="footer"></footer>
</div>
</body>
</html>

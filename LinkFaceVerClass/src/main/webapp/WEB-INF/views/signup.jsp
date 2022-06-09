<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resources/includes/tags.jsp" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/resources/includes/jqueryandcsrf.jsp"/>
<title>Insert title here</title>
</head>
<body>
	<form id="signup-form" action="signup" method="post">
		<div id="id">
			<label>ID</label>
			<input type="text" name="id" maxlength="30"/>
			<button type="button" class="id-duplicate-check">
			ID duplicate check</button>
			<div class="result">
			</div>
		</div>
		<div>
			<label>PASSWORD</label>
			<input type="password" name="password" maxlength="30"/>
		</div>
		<div>
			<label>PASSWORD Check</label>
			<input type="password" name="passwordCheck" maxlength="30"/>
		</div>
		<div id="email">
			<label>EMAIL</label>
			<input type="email" name="email" maxlength="30"/>
			<button type="button" class="email-duplicate-check"
			>EMAIL duplicate check</button>
			<div class="result">
			</div>	
		</div>
		<div>
			<label>NAME</label>
			<input type="text" name="name" maxlength="30">
		</div>
		<button class="signUp">submit</button>
	</form>
	
	<!-- 스크립트 -->
	<script type="text/javascript" src="/resources/js/signup.js"></script> 
	
</body>
</html>
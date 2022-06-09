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
	<form class="newPasswordForm" data-key="<c:out value="${key}"/>">
		<div>
			<label>PASSWORD</label>
			<input type="password" name="password" maxlength="30"/>
		</div>
		<div>
			<label>PASSWORD Check</label>
			<input type="password" name="passwordCheck" maxlength="30"/>
		</div>
		<button class="btnSubmit">제출</button>
	</form>
	
	<script type="text/javascript" src="/resources/js/newpassword.js"></script> 
</body>
</html>
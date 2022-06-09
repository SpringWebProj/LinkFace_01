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
	회원
	<p>principal : <sec:authentication property="principal"/></p>
	<p>ID : <sec:authentication property="principal.userInfo.userId"/></p>
	<p>EMAIL : <sec:authentication property="principal.userInfo.userEmail"/></p>
	<p>NAME : <sec:authentication property="principal.userInfo.userName"/></p>
	<p>ROLE : <sec:authentication property="principal.userStatus.role"/></p>
</body>
</html>
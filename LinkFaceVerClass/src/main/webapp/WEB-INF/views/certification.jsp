<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/resources/includes/jqueryandcsrf.jsp"/>
<title>Insert title here</title>
</head>
<body>
	<script>
	$(document).ready(function(){
		
		let messege = `<c:out value="${message}"/>`;
		
		alert(messege);	
		
		window.location.href = "/main";
	})
	</script> 
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resources/includes/tags.jsp" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/resources/includes/jqueryandcsrf.jsp"/>
</head>
<body>
	메뉴
	<sec:authorize access="isAnonymous()">
		 <button class="login" onClick="location.href='/login'">로그인</button>
	</sec:authorize>
	
	<sec:authorize access="isAuthenticated()">
		<form action="logout" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
		<button>로그아웃</button>
	</form>
		<button class="member" onClick="location.href='/member'">회원페이지</button>
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ROLE_MEMBER','ROLE_MANAGER','ROLE_ADMIN')">
		<button class="admin" onClick="location.href='/write'">레시피 등록</button>
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')">
		<button class="admin" onClick="location.href='/admin'">관리자페이지</button>
	</sec:authorize>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>테스트 페이지</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <link rel="icon" type="image/x-icon" href="resources/assets/favicon.ico" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <link href="resources/css/styles.css" rel="stylesheet" />
</head>
<body>

	<!-- nav -->
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="#!">테스트 테스트</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="#!">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="#!">About</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Recipe</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#!">All Foods</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="#!">Popular Foods</a></li>
                                <li><a class="dropdown-item" href="#!">New Recipes</a></li>
                            </ul>
                        </li>
                    </ul>
                        <sec:authorize access="isAnonymous()">
                            <a type="button" class="btn btn-primary" href="/login">로그인</a>
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">
                            <form action="logout" method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>   
                            <button class="btn btn-danger">로그아웃</button>
                        </form>
                        </sec:authorize>
                        <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')">
                            <a class="btn btn-outline-dark" type="submit" href="/jjim" >
                                <i class="bi-bag-check me-1"></i>
                                    찜목록
                                <span class="badge bg-dark text-white ms-1 rounded-pill"></span>
                            </a>
                        </sec:authorize>
                    
                </div>
            </div>
        </nav>
        
        
        <!-- Header-->
        <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">대충 요리 레시피</h1>
                    <p class="lead fw-normal text-white-50 mb-0">homepage template</p>
                </div>
            </div>
            
            <form action="search" method="GET" class="search">
	            <div class="container w-50">
					<div class="d-flex align-items-center">
			    		<input name="search" class="form-control" type="search" placeholder="검색" aria-label="Search">
			    		<button id="searchBtn" class="btn btn-outline-info flex-shrink-0" type="submit">검색</button>
			  		</div>
				</div>
			</form>
        </header>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="resources/js/scripts.js"></script>
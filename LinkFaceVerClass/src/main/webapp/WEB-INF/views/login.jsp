<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	
	<link rel="stylesheet" type="text/css" href="https://recipe1.ezmember.co.kr/static/css/font_20210621.css" />
	<link rel="stylesheet" type="text/css" href="https://recipe1.ezmember.co.kr/static/css/font-awesome.min.css" />
	<link rel="stylesheet" type="text/css" href="https://recipe1.ezmember.co.kr/static/css/ez_recipe_20220407.css" />
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="resources/js/scripts.js"></script> 
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
                    <form class="d-flex">
                    </form>
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
            

        </header>

<div class="container_etc" style="width:460px;">
    <h2>로그인 / 회원가입</h2>
    <div class="panel-body">
        <form name="form_login" id="formLogin" method="post" action="/login" autocomplete="off">
            <div class="form_login_in" style="padding-right:22px;">
                <input type="text" name="username" class="" id="username" placeholder="아이디">
                <span id="idMsg" style="display:none; color:#FF0000;">아이디를 입력해주세요.</span>
            </div>
            <div class="form_login_in">
                <input type="password" name="password" class="" id="password" placeholder="비밀번호">
                <span id="pwMsg" style="display:none;color:#FF0000;">비밀번호를 입력해주세요.</span>
            </div>
            <div class="checkbox">
                <label>
                    <input type="checkbox" name="login_perma" value="1"><span class="guide_txt">로그인 상태 유지</span>
                </label>
            </div>
            <div>
            	<label>
            		<a type="button" href="http://127.0.0.1:3000/">얼굴인식 로그인</a>
            	</label>
            </div>
			<div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			</div>
            <div class="form_login_btn">
                <button type="submit">로그인</button>
            </div>
        </form>
            <div class="join_btn3">
                <a href="#">아이디/비밀번호 찾기</a>
            </div>
            <button class="btn_signUp" onClick="location.href='/signup'">회원가입</button>
    </div>
</div>

<%@ include file="includes/footer.jsp" %>
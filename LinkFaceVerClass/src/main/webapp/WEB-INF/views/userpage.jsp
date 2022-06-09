<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
		<%@ include file="includes/header_user.jsp" %>
	
		<section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
            	<h1>내 정보</h1>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-2">
                	
                	<form role="form" action="/modify" method="post">
               		<div class="col mb-3" style="padding-left:10%;">
                       	<div class="card w-75 h-100" style="justify-content:center;">
                            <div class="card-body p-4">
		                        <div class="text-center">
		                        	<label for="name">이름</label>
		                        	<input type="text" class="form-control" name="name" value="${account.userInfo.userName}" readonly />
		                        </div>
		                    </div>
           	            </div>
               	    </div>
               		<div class="col mb-3" style="padding-left:10%;">
                       	<div class="card w-75 h-100" style="justify-content:center;">
                            <div class="card-body p-4">
		                        <div class="text-center">
		                        	<label for="id">아이디</label>
		                        	<input type="text" class="form-control" name="id" value="${account.userInfo.userId}" readonly />
		                        </div>
		                    </div>
           	            </div>
               	    </div>
               		<div class="col mb-3" style="padding-left:10%;">
                       	<div class="card w-75 h-100" style="justify-content:center;">
                            <div class="card-body p-4">
		                        <div class="text-center">
		                        	<label for="email">이메일</label>
		                        	<input type="text" class="form-control" name="email" placeholder="${account.userInfo.userEmail}" value="" />
		                        </div>
		                    </div>
           	            </div>
               	    </div>
               	    <input type="hidden" name="key" value="${account.userInfo.userKey}" />
               	    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
               	    <input type="submit" id="modify" value="수정" class="btn btn-secondary" />
               	    </form>
               	    
                </div>
        	</div>
        	
        	<div class="container px-4 px-lg-5 mt-5">
        		<h1><a href="http://127.0.0.1:3000/register">얼굴 등록</a></h1>
        	</div>
        	<div class="container px-4 px-lg-5 mt-5">
        		<h1><a href="/sendkey">비밀번호 변경</a></h1>
        	</div>
        </section>
		
		<style>
			a { text-decoration: none;}
		</style>
		
		<%@ include file="includes/footer.jsp" %>

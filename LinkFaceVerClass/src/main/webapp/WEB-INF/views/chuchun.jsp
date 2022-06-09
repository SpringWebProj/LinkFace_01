<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="includes/header.jsp" %>
	<section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
            	<h1>찜목록과 비슷한 메뉴</h1>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                	<c:forEach items="${chuchun1}" var="foods">
                		<div class="col mb-5">
                        	<div class="card h-100">
                        	<input type="hidden" name="recipeId" value="${foods.recipeid}" />
	                            <img class="card-img-top" src="${foods.imgurl}" alt="..." />
	                            <div class="card-body p-4">
    	                            <div class="text-center">
	                                    <h5 class="fw-bolder">${foods.recipenmko}</h5>
	                                </div>
    	                        </div>
	                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
    	                            <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">보러가기</a><button name="jjimbtn" class="btn btn-outline-dark mt-auto jjim">찜</button></div>
        	                    </div>
            	            </div>
                	    </div>
					</c:forEach>
					
                </div>
            </div>
        </section>
        
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
            	<h1>가지고있는 재료로 만들 수 있는 메뉴 </h1>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                	<c:forEach items="${chuchun2}" var="foods">
                		<div class="col mb-5">
                        	<div class="card h-100">
                        	<input type="hidden" name="recipeId" value="${foods.recipeid}" />
	                            <img class="card-img-top" src="${foods.imgurl}" alt="..." />
	                            <div class="card-body p-4">
    	                            <div class="text-center">
	                                    <h5 class="fw-bolder">${foods.recipenmko}</h5>
	                                </div>
    	                        </div>
	                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
    	                            <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">보러가기</a><button name="jjimbtn" class="btn btn-outline-dark mt-auto jjim">찜</button></div>
        	                    </div>
            	            </div>
                	    </div>
					</c:forEach>
					
                </div>
            </div>
        </section>
<%@ include file="includes/footer.jsp" %>
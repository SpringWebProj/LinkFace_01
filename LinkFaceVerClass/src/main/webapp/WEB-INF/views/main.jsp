<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resources/includes/tags.jsp" %>
<%@ include file="/resources/includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/resources/includes/jqueryandcsrf.jsp"/>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="memberMenu.jsp"/>
	
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
            	<h1>베스트 요리</h1>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                	
                	<c:forEach items="${food}" var="foods">
                		<div class="col mb-5">
                        	<div class="card h-100">
	                            <img class="card-img-top" src="${foods.IMGURL}" alt="..." />
	                            <div class="card-body p-4">
    	                            <div class="text-center">
	                                    <h5 class="fw-bolder">${foods.RECIPENMKO}</h5>
	                                </div>
    	                        </div>
	                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
	                            	<small>조회수: ${foods.viewsCount}</small><br>
	                            	 <c:choose>
									   <c:when test="${foods.GRADEAVG != 0 }">
									  		<small>★ ${foods.GRADEAVG} / 5.0 (${foods.GRADECOUNT})</small>
									   </c:when>
									   <c:otherwise>
									   		<small>등록된 별점이 없습니다</small>
									   </c:otherwise>
									</c:choose>
    	                            <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">보러가기</a></div>
        	                    </div>
            	            </div>
                	    </div>
					</c:forEach>
                
                </div>
            </div>
        </section>
        
        
        <div class="modal fade" id="Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel">알림</h4>
                    </div>
                    <div class="modal-body"></div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="ok">확인</button>
                    </div>
                </div>
            </div>
        </div>
        
        <script type="text/javascript">
        	$(document).ready(function(e){
        		function modalCheck(result){
        			if(result === "") return;
        			if(result === "success") $('.modal-body').html("수정되었습니다. 수정하신 이메일로 인증링크를 발송했습니다.");
        			else $('.modal-body').html("에러가 발생했습니다.");
        			$('#Modal').modal("show");
        		}
        		
        		$('button[id="ok"]').on("click", function(e){
        			$('#Modal').modal("hide");
        		})
        		
        		var result = '<c:out value="${result}"/>';
        		modalCheck(result);
        	})
        </script>
     
		<%@ include file="/resources/includes/footer.jsp" %>
</body>
</html>
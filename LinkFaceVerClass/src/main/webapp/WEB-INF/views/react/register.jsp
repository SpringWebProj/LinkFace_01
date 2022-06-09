<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>얼굴 등록</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <link rel="icon" type="image/x-icon" href="../resources/assets/favicon.ico" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <link href="../resources/css/styles.css" rel="stylesheet" />
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="../resources/js/scripts.js"></script>
</head>
<style>
	.principals{ display: none; }
</style>
<body>

	<section class="py-5">
	    <div class="container px-4 px-lg-5 mt-5 text-center">
	    	<div class="spinner-border" style="width: 5rem; height: 5rem;" role="status">
				<span class="sr-only"></span>
			</div>
	    	<h1>REGISTERING</h1>
	    </div>
	</section>
	
	<div class="principals">
		<p id="principal"><sec:authentication property="principal"/></p>
		<p id="name"><sec:authentication property="principal.userInfo.userName"/></p>
		<p id="id"><sec:authentication property="principal.userInfo.userId"/></p>
		<p id="email"><sec:authentication property="principal.userInfo.userEmail"/></p>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(e){
			var userName = document.getElementById("name").innerText;
			console.log(userName);
			$.ajax({
				url: "http://127.0.0.1:5000/username",
				type: "POST",
				data: JSON.stringify(userName),
				contentType : "application/json; charset=UTF-8",
				success: function(result){
					console.log(result);
				},
				error: function(err){
					console.log("error: " + err);
				}
			});
		})
		setTimeout(function(){
			window.location.href = "/homepage";
		}, 1000)
	</script>

</body>
</html>
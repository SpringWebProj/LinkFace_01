<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인 중</title>
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
	#form{ display: none; }
</style>
<body>

	<section class="py-5">
	    <div class="container px-4 px-lg-5 mt-5 text-center">
	    	<div class="spinner-border" style="width: 5rem; height: 5rem;" role="status">
				<span class="sr-only"></span>
			</div>
	    	<h1>LOADING</h1>
	    </div>
	</section>
	
	<form id="form" action="/login" method="post">
		<div>
			<input type="text" name="username" value="${resp.id}">
		</div>
		<div>
			<input type="password" name="password" value="${resp.password}">
		</div>
		<div>
			<input type="submit">
		</div>
	</form>

	<script type="text/javascript">
		setTimeout(function(){
			$('#form').submit();
		}, 1000)
	</script>

</body>
</html>
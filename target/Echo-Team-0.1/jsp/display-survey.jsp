<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 


<!DOCTYPE HTML>

<html>
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Metatranslate</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Website Template by gettemplates.co" />
	<meta name="keywords" content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
	<meta name="author" content="gettemplates.co" />

  	<!-- Facebook and Twitter integration -->
	
	<!-- <link href="https://fonts.googleapis.com/css?family=Merriweather:300,400|Montserrat:400,700" rel="stylesheet"> -->

	<link href="https://fonts.googleapis.com/css?family=Lora" rel="stylesheet">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="css/icomoon.css">
	<!-- Themify Icons-->
	<link rel="stylesheet" href="css/themify-icons.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="css/bootstrap.css">
	<!-- Theme style  -->
	<link rel="stylesheet" href="css/style.css">

	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->



	</head>
	<body>
		
	<div class="gtco-loader"></div>
	
	<div id="page">

		<nav class="gtco-nav" role="navigation">
			<div class="container">
				
				<div class="row">
					<div class="col-sm-2 col-xs-2">
						<div id="gtco-logo"><a href="index.html"></a>MetaTranslator<sup>&trade;</sup></div>
					</div>

				</div>
				
			</div>
		</nav>



	<body>
		<h1 align="center">Survey #<c:out value="${key}"/></h1>
		<h2 alighn="center">Number of Sentences is equal to #<c:out value="${texts_size}"/></h2>
		<c:forEach begin="0" end="${fn:length(texts) - 1}" var="cycle">
	   <p><font color="green">Sentence #<c:out value="${cycle}"/></font></p>
	   <tr>
	      <td>Sentence: <c:out value="${texts[cycle]}"/></td>
	      <br>
		   <td>Translation: <c:out value="${translated_texts[cycle]}"/></td>
		   <br>
	      <c:set var = "string" value = "${fn:split(texts[cycle], ' ')}"/>
	      <br>
	      <p><font color="red">Choose the word causing problems in the translation:</font></p>
	      <span>	      
	      	<select>
	      	  	

	      <c:forEach begin="0" end="${fn:length(string) - 1}" var="internal">
	      
	      	<br>

	      	<option value="${string[internal]}"><c:out value="${string[internal]}"/></option>
	      	<br>
	      	
	      </c:forEach>
	      
	      </select>
	     
	      </span>

	      <span>
	      	<button type="button">Correct translation!</button>
	      </span>

	      <br>
	   </tr>
	   <br>
</c:forEach>
	</body>



<!-- 		<footer id="fh5co-footer">
			<div class="container">
				<div class="row">
					<div class="col-md-4">
						<div class="fh5co-footer-widget">
							<a href="index.html">Motion <sup>&trade;</sup></a> Free HTML5 &copy; All Rights Reserved.  <br> Designed by <a href="http://gettemplates.co" target="_blank">GetTemplates.co</a> Images: <a href="http://pixeden.com" target="_blank">Pixeden</a>
						</div>
					</div>
					<div class="col-md-3 col-md-push-1">
						<div class="fh5co-footer-widget">
							<p><a href="tel://+1 234 567 8910">+1 234 567 8910</a> <br> <a href="#">info@metatranslate.com</a></p>
						</div>
					</div>
					<div class="col-md-4 col-md-push-1">
						<div class="fh5co-footer-widget gtco-social-wrap">
							<ul class="gtco-social">
								<li><a href="#" class="icon-twitter"></a></li>
								<li><a href="#" class="icon-dribbble"></a></li>
								<li><a href="#" class="icon-instagram"></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</footer> -->
		

	</div>

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
	</div>
	
	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>

	<!-- Main -->
	<script src="js/main.js"></script>

	</body>
</html>


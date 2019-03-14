<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE HTML>

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>MetaTranslate</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Free HTML5 Website Template by gettemplates.co" />
        <meta name="keywords" content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
        <meta name="author" content="gettemplates.co" />

        <link rel="stylesheet" href="css/style.css">
	
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
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

        <style>
            body {font:10px Montserrat, sans-serif;}
            .navbar {
                border: 0px;
                border-radius: 0px;
                margin-bottom: 0px;
            }
        </style>
    </head>

    <body>
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navabar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>                        
                    </button>
                    <a class="navbar-brand" href="index.html" id="index">MetaTranslate</a>
                    <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="login" id="login">LOGIN</a></li>
                            <li><a href="registration" id="registration">REGISTER</a>
                        </ul>
                    </div>
                </div>
            </div>
        </nav> 
        
        
        <div class="container-fluid bg-1 text-center">
                <h4 class="margin" style="line-height: 1.5em"><br>SURVEY <c:out value="${key}"/>
                    <br><br><br> 
                    NUMBER OF SENTENCES: <c:out value="${texts_size}"/>
                </h4>
        </div>
        <c:forEach begin="0" end="${fn:length(texts) - 1}" var="cycle">
            <br><br>
            <div class="row">
                <div class="col-lg-6 col-sm-6" style="text-align: center">
                    Sentence <c:out value ="${cycle+1}"/> 
                    <div class="container-fluid bg-3">
                        <p>
                            Sentence: <c:out value="${texts[cycle]}"/>
                            <br><br>
                            Translation: <c:out value="${translated_texts[cycle]}"/>
                            <br>
                            <c:set var = "string" value = "${fn:split(texts[cycle], ' ')}"/>
                            <br>
                        </p>
                        <p>Choose the word causing problems in the translation:</p>
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
                        <br><br>
                    </div>
                </div>
            </div>
        </c:forEach>
        
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
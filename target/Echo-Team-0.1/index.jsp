<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
                <a class="navbar-brand" href="index.jsp" id="index">MetaTranslate</a>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav navbar-right">
						<c:choose>
							<c:when test="${sessionScope.current_logged_in == null}">
								<li><a href="login" id="login">LOGIN</a></li>
								<li><a href="registration" id="registration">REGISTER</a>
							</c:when>
							<c:otherwise>
								<li><a href="userpage" id="userpage">USER PAGE</a></li>
								<li><a href="logout" id="logout">LOGOUT</a></li>
							</c:otherwise>
						</c:choose>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
        
        
        <div class="container-fluid bg-1 text-center">
            <div class="col-lg-2 col-sm-1"></div>
            <div class="col-lg-8 col-sm-10" style="vertical-align">
                <h4 class="margin" style="line-height: 1.5em"><br>Welcome to our metaphor translation survey. 
                    <br><br><br> 
                    You will evaluate the translation quality of given sentences. It takes about 5 minutes to fill in the survey. You have to answer all the questions for each sentence. The survey is anonymous. Your answers will be recorded for academic research. It may be published on academic articles for research purpose.
                </h4>
                <br><br>
                <form action="index" method="post" accept-charset="utf-8">
                    <p class="label-txt"><br>ENTER THE SURVEY KEY<br></p>
                    <div class="row">
                        <div class="col-lg-3"></div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <input type="text" class="input" id="key" name="key">
                                <div class="line-box">
                                    <div class="line"></div>
                                </div>
                            </div>
                            <br>
                        </div>
                        <!-- <input type="text" name="key" style="width:350px"> -->
                        <div class="col-lg-3"><br></div>
                    </div>
                    <br>
                    <div class="col-lg-12">
                        <p><input class="btn btn-basic" type="submit" value="Start Survey!" style="color: black"></p>
                        <p> <c:out value="${error}"/></p>
                    </div>
                </form>
            </div>
        </div>
        
        <div class="container-fluid bg-3" style="text-align: center">    
            <h3 class="margin" style="text-align: center">How to fill in the survey?</h3><br><br><br><br>
            <div class="row">
                <div class="col-sm-4">
                    <p>Explanation on how to select incorrect words, translation. etc..</p>
                </div>
                <div class="col-sm-4"> 
                    <p>Explanation on how to get dictionary definition for a word.</p>
                </div>
                <div class="col-sm-4"> 
                    <p>Explanation about the different translations.</p>
                </div>
            </div>
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
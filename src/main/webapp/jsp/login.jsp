<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Free HTML5 Website Template by gettemplates.co" />
        <meta name="keywords" content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
        <meta name="author" content="gettemplates.co" />
        <link href="https://fonts.googleapis.com/css?family=Lora" rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link rel="stylesheet" href="../css/style.css">
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
            label {
                display: block;
                position: relative;
                margin: 40px 0px;
            }
            form {
                width: 60%;
                margin: 60px auto;
                background-color: #efefef;
                padding: 60px 60px 60px 60px;
                text-align: center;
                -webkit-box-shadow: 2px 2px 3px rgba(0,0,0,0.1);
                box-shadow: 2px 2px 3px rgba(0,0,0,0.1);
                color: #808080;
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
                            <li><a href="registration" id="registration">REGISTER</a>
                        </ul>
                    </div>
                </div>
            </div>
        </nav> 
        <div class="container-fluid bg-1 text-center">
            <div class="col-lg-2 col-sm-1"></div>
            <div class="col-lg-8 col-sm-10 col-centered" style="vertical-align">
                <h4 class="margin" align="center">
                    <br><br>
                    If you wish to create an account, please click on the register button on the top of the page. 
                    <br> <br><br><br>
                    If you already have an account, please enter your credentials below to log in.
                    <br><br>
                    <form action="login" method="post">
                        <label>
                            <p> <c:out value="${error}"/></p>
                            <p class="label-txt">ENTER YOUR RESEARCHER KEY</p>
                            <input type="text" class="input" name="rkey">
                            <div class="line-box">
                                <div class="line"></div>
                            </div>
                        </label>
                        <label>
                            <p class="label-txt">ENTER YOUR PASSWORD</p>
                            <input type="password" class="input" name="password">
                            <div class="line-box">
                                <div class="line"></div>
                            </div>
                        </label>
                        <button type="submit" class="btn btn-basic" id = "loginButton" value="Login">submit</button>
                    </form>
                </h4>
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
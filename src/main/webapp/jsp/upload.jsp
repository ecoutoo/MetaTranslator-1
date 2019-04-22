<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML>

<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Upload Sentences</title>
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
			form {
                width: 80%;
                margin: 60px auto;
                background: #efefef;
                padding: 60px 120px 80px 120px;
                text-align: center;
                -webkit-box-shadow: 2px 2px 3px rgba(0,0,0,0.1);
                box-shadow: 2px 2px 3px rgba(0,0,0,0.1);
                color: #808080;
            }
            label {
                display: block;
                position: relative;
                margin: 40px 0px;
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
							<li><a href="logout" id="logout">LOGOUT</a></li>
						</ul>
					</div>
                </div>
            </div>
        </nav>
		<div class="container-fluid bg-1 text-center">
            <div class="col-lg-2 col-sm-1"></div>
            <div class="col-lg-8 col-sm-10 col-centered" style="vertical-align">
                <h2 class="label-txt" align="center">UPLOAD SENTENCES AND CREATE SURVEY</h2>
                <h4 class="margin" align="center"> 
				<br><br>Create survey by selecting the parameters you want to use and inserting your sentences.<br><br>
				<form action="personal" method="post">
					<label>
						<p class="label-txt">TRANSLATOR ENGINE</p>
						<div class="row">
							<div class="col-lg-2 col-sm-2"></div>
                            <div class="col-lg-8 col-sm-8" style="text-align: justify">
								<br><input type="radio" name="translator"  value="google" checked> &nbsp; Google Translator <br>
								<input type="radio" name="translator" value="yandex"> &nbsp; Yandex Translator <br>
							</div>
                        </div>
                        <br><br>
                        <div class="line-box">
                            <div class="line"></div>
                        </div>
                    </label>
                    <label>
                        <p class="label-txt">TARGET LANGUAGE</p>
                        <div class="row">
                            <div class="col-lg-2 col-sm-2"></div>
                            <div class="col-lg-8 col-sm-8" style="text-align: justify">
                                <select name="languages">
                                    <option value="Chinese">Chinese</option>
                                    <option value="Italian">Italian</option>
                                    <option value="French">French</option>
                                    <option value="German">German</option>
                                </select>
                            </div>
                        </div>
						<br><br>
                        <div class="line-box">
                            <div class="line"></div>
                        </div>
                    </label>
                    <label>
                        <p class="label-txt">SENTENCES</p>
                        <div class="row">
                            <div class="col-lg-2 col-sm-2"></div>
                            <div class="col-lg-8 col-sm-8" style="text-align: justify">
								<c:forEach begin="0" end="${num-1}" var="cycle">
									<p><c:out value="${cycle+1}"/> <input type="text" name="ownsent${cycle}"></p>
								</c:forEach>
							</div>
						</div>
						<br><br>
                        <div class="line-box">
                            <div class="line"></div>
                        </div>
					</label>
					<input type="hidden" name="num" value="${num}">
					<input type="hidden" name="rkey" value="${rkey}">
					<input type="submit" class="btn btn-basic" name="create survey" value="Create Survey" style="color: black">
				</form>
            </div>
        </div>		
	</body>
</html>
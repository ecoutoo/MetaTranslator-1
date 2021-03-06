<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML>

<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>User Page</title>
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
                width: 60%;
                margin: 60px auto;
                background-color: rgba(0,0,0,0);
                padding: 60px 60px 60px 60px;
                text-align: center;
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
							<li><a href="userpage" id="userpage">USER PAGE</a></li>
							<li><a href="logout" id="logout">LOGOUT</a></li>
						</ul>
					</div>
                </div>
            </div>
        </nav>
		<div class="container-fluid bg-1 text-center">
            <h4 class="margin" style="line-height: 1.5em">
                WELCOME, <c:out value="${username}"/>
            </h4>
        </div>
		<div class="row">
            <div class="container-fluid bg-3" style="text-align: center"> 
                <div class="col-lg-6 col-sm-6">
                    <form action="creation" style="text-align:left;width:80%" method="post">
                        <p><b>Researcher ID</b> <c:out value="${rkey}"/><br><br>
                            <b>Name</b> <c:out value="${name}"/><br><br>
                            <b>Surname</b> <c:out value="${surname}"/><br><br>
                            <b>Email</b> <c:out value="${email}"/><br><br></p>
                        <input type="hidden" name="rkey" value="${rkey}">
                        <!--<input type="file" name="owncorpora" size="50" />-->
                        <input class="btn btn-basic" type="submit" id = "displayrkey_createSurvey" value="Create Survey" style="color: black">
                    </form>
                </div>
                <div class="col-lg-6 col-sm-6">
                    <form action="upload" style="text-align:left;width:80%" method="post">
                        <p><b>Create a survey with your own sentences</b><br><br>
                            Number of sentences: <input type="radio" name="num" value="5" checked> 5 &nbsp; <input type="radio" name="num" value="10"> 10 &nbsp; <input type="radio" name="num" value="15"> 15<br>
                            <input type="hidden" name="rkey" value="${rkey}">
                            <br><input type="submit" class="btn btn-basic" name="upload" value="Upload" style="color: black"></p>
                    </form>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="container-fluid bg-3" style="text-align: center"> 
                <c:choose>
                    <c:when test="${fn:length(survarr)<1}">
                        <div class="row">
                            <div class="col-lg-2 col-sm-2"></div>
                            <div class="col-lg-8 col-sm-8">
                                <p>You haven't created any survey yet.</p>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${fn:length(survarr)>0}">
                        <c:forEach begin="0" end="${fn:length(survarr)-1}" var="cycle">
                            <div class="container-fluid bg-1 text-center">
                                <h4 class="margin" style="line-height: 1.5em">
                                    SURVEY <c:out value="${survarr[cycle]}"/>
                                </h4>
                            </div>
                            <div class="row">
                                <div class="col-lg-6 col-sm-6">
                                    <form class="diff" action="analytics" method="post">
                                        <p><input type="hidden" name="survkey" value="${survarr[cycle]}">
                                            <input class="btn btn-basic" type="submit" name="Analytics" value="Analytics" style="color: black"></p>
                                    </form>
                                </div>
                                <div class="col-lg-6 col-sm-6">
                                    <form class="diff" action="downloadraw" method="post">
                                        <input type="hidden" name="survkey" value="${survarr[cycle]}">
                                        <input class="btn btn-basic" type="submit" name="DownloadRaw" value="Download Raw Data" style="color: black">
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                </c:choose>
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


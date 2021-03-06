<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE HTML>

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Complete Survey</title>
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
		<script type="text/javascript">
			function corincCheck(i) {
				if (document.getElementById('incCheck'+i).checked) {
					document.getElementById('ifInc'+i).style.display='block';
				} else if (document.getElementById('corCheck'+i).checked) {
					document.getElementById('ifInc'+i).style.display='none';
				}
			}
		</script>
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
            <h4 class="margin" style="line-height: 1.5em">
				<br>SURVEY <c:out value="${key}"/><br>
                TRANSLATOR: <c:out value="${translator}"/><br>
                NUMBER OF SENTENCES: <c:out value="${texts_size}"/>
            </h4>
        </div>
		<form action="completion" method="post" style="width:100%">
        <div class="diff">
            <input type="hidden" name="survkey" value="${key}">
            <input type="hidden" name="survsize" value="${texts_size}">
            <!--<input type="hidden" name="sentids" value="<c:out value="${sentids}"/>">-->
            <c:forEach begin="0" end="${fn:length(texts) - 1}" var="cycle">
                <div class="row">
                    <div class="col-lg-6 col-sm-6" style="text-align:center;width:100%">
                        
                
                        <!--<input type="hidden" name="sentkey<c:out value="${cycle+1}"/>" value="<c:out value="${sentid[cycle]}"/>">-->
                        <div class="container-fluid bg-3">
                            <p>Sentence <c:out value ="${cycle+1}"/></p>
                            <p>
                                <b>Sentence:</b> <c:out value="${texts[cycle]}"/>
                                <br><br>
                                <b>Translation:</b> <c:out value="${translated_texts[cycle]}"/>
                                <br>
                                <c:set var = "string" value = "${fn:split(texts[cycle], ' ')}"/>
                                <br>
                            </p>                 
                            <span>
                                <p>
                                    <input type="radio" onclick="javascript:corincCheck(${cycle})" name="CorrectTranslation${cycle}" id="incCheck${cycle}" value="Incorrect"> &nbsp; Incorrect Translation &nbsp;&nbsp;
                                    <input type="radio" onclick="javascript:corincCheck(${cycle})" name="CorrectTranslation${cycle}" id="corCheck${cycle}" value="Correct" checked> &nbsp; Correct Translation
                                </p>
                            </span>
                            <div id="ifInc${cycle}" style="display:none">
                                <br>
                                <p>Choose the words causing problems in the translation:</p>
                                <div id="ck-button">
                                    <label>
                                        <input type="checkbox" name="IncorrectWord${cycle}" value=" " checked>
                                        <span>&nbsp;</span>
                                    </label>
                                    <c:forEach begin="0" end="${fn:length(string) - 1}" var="internal">
                                        <label>
                                            <input type="checkbox" name="IncorrectWord${cycle}" value="${string[internal]}">
                                            <span><c:out value="${string[internal]}"/>&nbsp;</span>
                                        </label>
                                    </c:forEach>
                                </div>
                                <br>
                                <br>
                                <p>Confidence (1-5):</p>
                                <span>
                                    <select name="Confidence">
                                        <!--<option hidden disabled selected value>Select a number</option>-->
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                    </select>
                                </span>
                                <br>
                                <br>
                                <p>Suggest your own translation:</p>
                                <input type="text" class="input" name="OwnTranslation" value="" size="100"/>
                                <div class="line-box">
                                    <div class="line"></div>
                                </div>
                                <br><br>
                            </div>
                        </div>
                        
                    </div>
                </div>
            </c:forEach>
            <div class="col-lg-12">
                <p><input class="btn btn-basic" type="submit" value="Submit" style="color: black"></p>
            </div>
        </div>
        </form>
            <br><br><br>
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
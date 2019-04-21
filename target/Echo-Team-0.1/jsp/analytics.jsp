<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML>

<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Analytics</title>
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
		<script src="https://code.highcharts.com/highcharts.js"></script>
		<script>
			document.addEventListener('DOMContentLoaded', function () {
				var myChart = Highcharts.chart('piechart0', {
					chart: {
						plotBackgroundColor: null,
						plotBorderWidth: 0,
						plotShadow: false
					},
					title: {
						text: 'Correctness',
						align: 'center',
						verticalAlign: 'middle',
						y: 40
					},
					tooltip: {
						pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
					},
					plotOptions: {
						pie: {
							dataLabels: {
								enabled: true,
								distance: -50,
								style: {
									fontWeight: 'bold',
									color: 'white'
								}
							},
							startAngle: -90,
							endAngle: 90,
							center: ['50%', '75%'],
							size: '110%'
						}
					},
					series: [{
						type: 'pie',
						name: 'Correctness',
						innerSize: '50%',
						data: [
							['Correct', <c:out value="${corres[0]}"/>],
							['Incorrect', <c:out value="${incorres[0]}"/>],
							{
								name: 'Other',
								y: 0.0,
								dataLabels: {
									enabled: false
								}
							}
						]
					}]	
				});
			});
			document.addEventListener('DOMContentLoaded', function () {
				var myChart = Highcharts.chart('piechart1', {
					chart: {
						plotBackgroundColor: null,
						plotBorderWidth: 0,
						plotShadow: false
					},
					title: {
						text: 'Correctness',
						align: 'center',
						verticalAlign: 'middle',
						y: 40
					},
					tooltip: {
						pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
					},
					plotOptions: {
						pie: {
							dataLabels: {
								enabled: true,
								distance: -50,
								style: {
									fontWeight: 'bold',
									color: 'white'
								}
							},
							startAngle: -90,
							endAngle: 90,
							center: ['50%', '75%'],
							size: '110%'
						}
					},
					series: [{
						type: 'pie',
						name: 'Correctness',
						innerSize: '50%',
						data: [
							['Correct', <c:out value="${corres[1]}"/>],
							['Incorrect', <c:out value="${incorres[1]}"/>],
							{
								name: 'Other',
								y: 0.0,
								dataLabels: {
									enabled: false
								}
							}
						]
					}]	
				});
			});
			document.addEventListener('DOMContentLoaded', function () {
				var myChart = Highcharts.chart('piechart2', {
					chart: {
						plotBackgroundColor: null,
						plotBorderWidth: 0,
						plotShadow: false
					},
					title: {
						text: 'Correctness',
						align: 'center',
						verticalAlign: 'middle',
						y: 40
					},
					tooltip: {
						pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
					},
					plotOptions: {
						pie: {
							dataLabels: {
								enabled: true,
								distance: -50,
								style: {
									fontWeight: 'bold',
									color: 'white'
								}
							},
							startAngle: -90,
							endAngle: 90,
							center: ['50%', '75%'],
							size: '110%'
						}
					},
					series: [{
						type: 'pie',
						name: 'Correctness',
						innerSize: '50%',
						data: [
							['Correct', <c:out value="${corres[2]}"/>],
							['Incorrect', <c:out value="${incorres[2]}"/>],
							{
								name: 'Other',
								y: 0.0,
								dataLabels: {
									enabled: false
								}
							}
						]
					}]	
				});
			});
			document.addEventListener('DOMContentLoaded', function () {
				var myChart = Highcharts.chart('piechart3', {
					chart: {
						plotBackgroundColor: null,
						plotBorderWidth: 0,
						plotShadow: false
					},
					title: {
						text: 'Correctness',
						align: 'center',
						verticalAlign: 'middle',
						y: 40
					},
					tooltip: {
						pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
					},
					plotOptions: {
						pie: {
							dataLabels: {
								enabled: true,
								distance: -50,
								style: {
									fontWeight: 'bold',
									color: 'white'
								}
							},
							startAngle: -90,
							endAngle: 90,
							center: ['50%', '75%'],
							size: '110%'
						}
					},
					series: [{
						type: 'pie',
						name: 'Correctness',
						innerSize: '50%',
						data: [
							['Correct', <c:out value="${corres[3]}"/>],
							['Incorrect', <c:out value="${incorres[3]}"/>],
							{
								name: 'Other',
								y: 0.0,
								dataLabels: {
									enabled: false
								}
							}
						]
					}]	
				});
			});
			document.addEventListener('DOMContentLoaded', function () {
				var myChart = Highcharts.chart('piechart4', {
					chart: {
						plotBackgroundColor: null,
						plotBorderWidth: 0,
						plotShadow: false
					},
					title: {
						text: 'Correctness',
						align: 'center',
						verticalAlign: 'middle',
						y: 40
					},
					tooltip: {
						pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
					},
					plotOptions: {
						pie: {
							dataLabels: {
								enabled: true,
								distance: -50,
								style: {
									fontWeight: 'bold',
									color: 'white'
								}
							},
							startAngle: -90,
							endAngle: 90,
							center: ['50%', '75%'],
							size: '110%'
						}
					},
					series: [{
						type: 'pie',
						name: 'Correctness',
						innerSize: '50%',
						data: [
							['Correct', <c:out value="${corres[4]}"/>],
							['Incorrect', <c:out value="${incorres[4]}"/>],
							{
								name: 'Other',
								y: 0.0,
								dataLabels: {
									enabled: false
								}
							}
						]
					}]	
				});
			});

		</script>
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
							<li><a href="logout" id="logout">LOGOUT</a></li>
							<li><a href="registration" id="registration">REGISTER</a>
						</ul>
					</div>
                </div>
            </div>
        </nav>
		<div class="container-fluid bg-1 text-center">
            <h4 class="margin" style="line-height: 1.5em">
				<br>SURVEY <c:out value="${survkey}"/> ANALYTICS<br><br> 
                Number of sentences: <c:out value="${numb}"/><br>
				Number of completions: <c:out value="${survtaken}"/>
            </h4>
        </div>
		<c:choose>
			<c:when test="${survtaken==0}">
				<div class="row">
					<div class="col-lg-3 col-sm-3"></div>
					<div class="col-lg-6 col-sm-6" style="text-align:center;width:100%">
						<div class="container-fluid bg-3">
							<p>No one competed the survey yet.</p>
						</div>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<c:forEach begin="0" end="${numb - 1}" var="cycle">
					<div class="row">
						<div class="col-lg-3 col-sm-3"></div>
						<div class="col-lg-6 col-sm-6" style="text-align:center;width:100%">
							<p>Sentence ID: <c:out value ="${sentid[cycle]}"/></p>
							<div class="container-fluid bg-3">
								<p>
									<b>Correct:</b> <c:out value="${corres[cycle]}"/>
									<br>
									<b>Incorrect:</b> <c:out value="${incorres[cycle]}"/>
								<div id="piechart${cycle}" style="width:100%;height:400px"></div>
								</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
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
		
		
		
		
		
		
		
		
		
		
		
		
		
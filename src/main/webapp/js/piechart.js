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
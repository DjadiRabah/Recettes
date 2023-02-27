define([ "jquery" ], function($) {

	return function(login, goodAnswersCount, badAnswersCount, size) {

		var context = document.getElementById('participantChart').getContext(
				'2d');
		var myPieChart = new Chart(context, {
			type : 'pie',
			data : {
				labels: [
			        'Taux de bonnes réponses',
			        'Taux de mauvaises réponses'
			    ],
				datasets : [ {
				backgroundColor : ['rgb(99, 255, 132)', 'rgb(255, 99, 132)'],
					borderColor : ['rgb(99, 255, 132)', 'rgb(255, 99, 132)'],
					data : [goodAnswersCount, badAnswersCount ]
				}]
			}
		});
	}

})

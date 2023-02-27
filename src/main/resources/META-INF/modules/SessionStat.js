define([ "jquery" ], function($) {

	return function(participants) {

		var labels = [];
		var dataCount = [];
		var dataPoints = [];
		var dataFirst = [];
		var dataCountBadAnswers = [];

		participants.sort(compare)
		displayRank(participants);

		for (var i = 0; i < participants.length; i++) {
			labels.push(participants[i]["login"])
			dataCount.push(participants[i]["goodAnswersCount"]);
			dataCountBadAnswers.push(participants[i]["badAnswersCount"]);
			dataFirst.push(participants[i]["firstCount"]);
			dataPoints.push(participants[i]["points"]);
		}

		var context = document.getElementById('participantChart').getContext(
				'2d');
		var chart = new Chart(context, {
			type : 'horizontalBar',

			data : {
				labels : labels,
				datasets : [ {
					label : 'Bonnes réponses',
					backgroundColor : 'rgb(99, 255, 132)',
					borderColor : 'rgb(99, 255, 132)',
					data : dataCount
				}, {
					label : 'Mauvaises réponses',
					backgroundColor : 'rgb(255, 99, 132)',
					borderColor : 'rgb(255, 99, 132)',
					data : dataCountBadAnswers
				}, {
					label : 'Nombre de fois premier à répondre',
					backgroundColor : 'rgb(255, 242, 0)',
					borderColor : 'rgb(255, 242, 0)',
					data : dataFirst
				},
				
				{
					label : 'Points',
					backgroundColor : 'rgb(132, 99, 255)',
					borderColor : 'rgb(132, 99, 255)',
					data : dataPoints
				} ]
			},

			options : {}
		});
	}

	function compare(player1, player2) {
		if (player1["points"] > player2["points"])
			return -1;
		if (player1["points"] < player2["points"])
			return 1;
		return 0;
	}

	function displayRank(players) {

		var idSession = parseInt(window.location.pathname.split('/')[2])
		var tbody = document.getElementById("rank")

		for (var i = 0; i < players.length; i++) {
			var tr = document.createElement("tr")
			var rank = document.createElement("td")
			var player = document.createElement("td")
			var points = document.createElement("td")
			var buttonTd = document.createElement("td")
			var button = document.createElement("a")
			
			rank.innerHTML = (i+1);
			player.innerHTML = players[i]["login"];
			points.innerHTML = players[i]["points"];
			button.innerHTML = "Consulter les réponses"
			button.className = "btn btn-info";
			button.href="/sessionPlayerStat/" + idSession + "/" + players[i]["id"];
			buttonTd.appendChild(button)
			
			tr.appendChild(rank);
			tr.appendChild(player);
			tr.appendChild(points);
			tr.appendChild(buttonTd);
			tbody.appendChild(tr);
		}
	}
})

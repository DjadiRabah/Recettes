define([ "jquery" ], function($) {

	return function(url) {
		
		request = new XMLHttpRequest;
		request.open('GET', url, true);

		request.onload = function() {
		  if (request.status >= 200 && request.status < 400){
		    // Success!
		    json = JSON.parse(request.responseText)["themes"];
		    displayThemes(json)
		  } else {
		    // We reached our target server, but it returned an error

		  }
		};

		request.onerror = function() {
		  // There was a connection error of some sort
		};

		request.send();
		
		function displayThemes(json)
		{
			var divThemes = document.getElementById("themes");
			for(i in json)
			{
				var div = document.createElement("div")
				divThemes.appendChild(div)
				
				var name = document.createElement("a");
				name.className = "btn btn-lg btn-outline-primary"
				name.title = "Choisir le thème " + json[i]["name"];
				name.href = window.location.href + "/" + json[i]["name"].toLowerCase()
				name.innerHTML = json[i]["name"];
				
				var description = document.createElement("p")
				description.className = "lead";
				description.innerHTML = json[i]["description"];
				
				div.appendChild(name)
				div.appendChild(description)
				div.appendChild(getImg(json[i]["thumbnail"], json[i]["name"]))
			}
		}
		
		function getImg(url, imageName)
		{
			var image = document.createElement("img");
			image.className = "img-fluid rounded border border-dark mb-2";
			image.src = url;
			image.alt = imageName;
			image.ondragstart = function() {
				return false;
			};
			image.oncontextmenu = function() {
				return false;
			};
			return image
		}
		
	}
})

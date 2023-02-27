define(
		[ "jquery" ],
		function($) {

			return function(recipes) {
				var div = document.getElementById("letters");
				var mainDiv = document.getElementById("main")

				var i = 0;
				var firstLetter = "";
				for (letter in recipes) {
					if (!firstLetter)
						firstLetter = letter;
					i++;
					var button = document.createElement("a");
					button.className = "btn btn-lg btn-outline-dark";
					button.onclick = displayRecipes.bind(this, letter);
					button.innerHTML = letter;
					button.title = letter;
					div.appendChild(button);
				}
				if (i === 0) {
					var h1 = document.createElement("h1");
					h1.innerHTML = "Aucune recette enregistrée";
					div.appendChild(h1);
				}

				else
				{
					var line = document.createElement("hr");
					div.appendChild(line);
					displayRecipes(firstLetter)
				}
				function displayRecipes(letter) {
					var currentRecipes = recipes[letter];
					mainDiv.innerHTML = "";

					for (i in currentRecipes) {
						var name = document.createElement("h2");
						var button = document.createElement("a");
						button.onclick = displayRecipe.bind(this,
								currentRecipes[i]);
						button.className = "btn btn-outline-primary";
						button.innerHTML = currentRecipes[i]["Name"] + " de "
								+ currentRecipes[i]["Creator"];
						button.title = currentRecipes[i]["Name"];
						name.appendChild(button);
						mainDiv.appendChild(name);
					}
				}

				function displayRecipe(recipe) 
				{
					
				
				
				
					mainDiv.innerHTML = "";

					var name = document.createElement("h1");
					name.innerHTML = recipe["Name"] + " de "
							+ recipe["Creator"];

					mainDiv.appendChild(name);
					if (recipe["Image"])
						mainDiv.appendChild(getImg(recipe["Image"]));

					displayIngredients(recipe["Ingredients"])
					displayInstructions(recipe["Instructions"])			
					displaySpeechButtons();
				}

				function displayIngredients(ingredientsJSON) {
					if (ingredientsJSON) {
						var ingredientsH2 = document.createElement("h2");
						ingredientsH2.innerHTML = "Ingredients";
						mainDiv.appendChild(ingredientsH2)

						var ingredients = Array();
						for ( const i in ingredientsJSON) 
						{
							ingredients.push(IngredientToString(ingredientsJSON[i]));
							displayIngredient(ingredientsJSON[i]);
						}
						
						if ('speechSynthesis' in window)
						{
							var recipeButtonDiv = document.createElement("div");
							recipeButtonDiv.className = "btn-group";
						
							var readRecipeButton = document.createElement("a");
							readRecipeButton.className = "btn btn-lg btn-outline-success";
							readRecipeButton.onclick = ReadRecipe.bind(this, ingredients);
							readRecipeButton.innerHTML = "Lire la recette";
							readRecipeButton.title = "Lire";
							
							var stopReadingRecipeButton = document.createElement("a");
							stopReadingRecipeButton.className = "btn btn-lg btn-outline-danger";
							stopReadingRecipeButton.onclick = stopReadingRecipe;
							stopReadingRecipeButton.innerHTML = "Arrêter la lecture";
							stopReadingRecipeButton.title = "Arrêter";
							
							recipeButtonDiv.appendChild(readRecipeButton);
							recipeButtonDiv.appendChild(stopReadingRecipeButton);
							
							
							mainDiv.appendChild(recipeButtonDiv);
						}
						else
						{
							console.log("does not support speech");
						}
					}
				}
				
				function IngredientToString(ingredientJson)
				{
					var amount = ingredientJson["Amount"];
					var measure = ingredientJson["Measure"];
					var aliment = ingredientJson["Aliment"];
					var string = "";

					ingredientString = amount + " ";
					if (measure) {
						ingredientString += (amount == 1 ? measure
								: measure + "s")
								+ (startWithVowel(aliment) ? " d'" : " de ")
								+ aliment;
					} 
					else 
					{
						if (amount == 0)
							ingredientString = aliment + " à l'oeil";
						else
							ingredientString += (amount == 1 ? aliment
								: aliment + "s");
					}
					return ingredientString;
				}
				
				
				function ReadRecipe(recipe)
				{
					speechSynthesis.cancel();
					for(var i = 0; i < recipe.length; i++)
					{
						var utterance = new SpeechSynthesisUtterance(recipe[i]);
						utterance.volume = 1;
						utterance.rate = 1;
						utterance.pitch = 2;
						utterance.lang = 'fr';
						speechSynthesis.speak(utterance);
					}
				}
				
				function stopReadingRecipe()
				{
					speechSynthesis.cancel();
				}

				function displayIngredient(currentIngredient) {

					var ingredient = document.createElement("p")
					ingredient.className = "lead";
					
					mainDiv.appendChild(ingredient);

					ingredient.innerHTML = IngredientToString(currentIngredient)
				}

				function displayInstructions(instructions) {
					if (instructions && instructions.length > 0) {
						var instructionsH2 = document.createElement("h2");
						instructionsH2.innerHTML = "Instructions";
						mainDiv.appendChild(instructionsH2)
						
						for (instruction in instructions)
						{
							var instructionText = document.createElement("p")
							instructionText.className = "lead";
							instructionText.innerHTML = instructions[instruction]
							mainDiv.appendChild(instructionText);
						}
					}
				}

				function startWithVowel(word) {
					return [ "A", "E", "I", "O", "U", "Y", "Œ" ].includes(word
							.charAt(0));
				}

				function getImg(url) {
					var image = document.createElement("img");
					image.className = "img-fluid rounded border border-dark";
					image.src = url;
					image.ondragstart = function() {
						return false;
					};
					image.oncontextmenu = function() {
						return false;
					};
					return image;
				}
			}
		})

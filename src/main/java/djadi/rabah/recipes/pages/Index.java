package djadi.rabah.recipes.pages;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import djadi.rabah.recipes.dao.impl.AlimentDao;
import djadi.rabah.recipes.dao.impl.IngredientDao;
import djadi.rabah.recipes.dao.impl.InstructionDao;
import djadi.rabah.recipes.dao.impl.MeasureDao;
import djadi.rabah.recipes.dao.impl.RecipeDao;
import djadi.rabah.recipes.dao.impl.UserDao;
import djadi.rabah.recipes.pojo.Ingredient;
import djadi.rabah.recipes.pojo.Instruction;
import djadi.rabah.recipes.pojo.Recipe;
import djadi.rabah.recipes.services.ImageUploader;

/**
 * Start page of application quiz.
 */
public class Index extends BasePage {

	@Environmental
	private JavaScriptSupport javaScriptSupport;

	@AfterRender
	void setup() {
		List<Recipe> recipes = RecipeDao.getInstance().getAll();

		Collections.sort(recipes, new Comparator<Recipe>() {
			@Override
			public int compare(Recipe recipe1, Recipe recipe2) {
				return recipe1.getName().compareTo(recipe2.getName());
			}
		});
		javaScriptSupport.require("Recipes").with(getJsonRecipes(recipes));
	}

	public JSONObject getJsonRecipes(List<Recipe> recipes) {
		JSONObject json = new JSONObject();
		for (Recipe recipe : recipes) {
			char firstLetter = recipe.getName().toUpperCase().charAt(0);
			json.append(firstLetter + "", getJsonRecipe(recipe));
		}
		return json;
	}

	private JSONObject getJsonRecipe(Recipe recipe) {
		JSONObject json = new JSONObject();
		json.put("Creator", UserDao.getInstance().getLoginByIdUser(recipe.getIdUser()));
		json.put("Name", recipe.getName());
		if (recipe.getImage() == null)
			json.put("Image", "");
		else
			json.put("Image", ImageUploader.getInstance().getImage(recipe.getImage()));
		for (Ingredient ingredient : IngredientDao.getInstance().getIngredientsByIdRecipe(recipe.getId())) {
			json.append("Ingredients", getJsonIngredient(ingredient));
		}
		json.put("Instructions", getJsonInstructions(recipe.getId()));
		return json;
	}

	private JSONObject getJsonIngredient(Ingredient ingredient) {
		JSONObject json = new JSONObject();
		json.put("Amount", ingredient.getAmount());
		json.put("Measure", MeasureDao.getInstance().getById(ingredient.getIdMeasure()).getMeasure());
		json.put("Aliment", AlimentDao.getInstance().getById(ingredient.getIdAliment()).getAliment());
		return json;
	}
	
	private JSONArray getJsonInstructions(int idRecipe) {
		JSONArray json = new JSONArray();
		for(Instruction instruction : InstructionDao.getInstance().getInstructionsByIdRecipe(idRecipe))
			json.put(instruction.getInstruction());
		return json;
	}
	
	@Override
    public Object onActivate(EventContext eventContext)
    {
		if (!getIsConnected())
			return Login.class;
		if (eventContext.getCount() != 0)
			return Index.class;
    	return null;
    }
}

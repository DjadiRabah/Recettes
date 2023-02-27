package djadi.rabah.recipes.pages;

import java.util.List;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.annotations.Property;

import djadi.rabah.recipes.dao.impl.RecipeDao;
import djadi.rabah.recipes.pojo.Recipe;

public class Recipes extends BasePage {
	@Property
	private Recipe recipe;

	@Property
	private List<Recipe> recipes;
	
	@Override
	public Object onActivate(EventContext eventContext)
	{
		if (eventContext.getCount() != 0)
			return Recipes.class;
		if(!getIsConnected())
			return Login.class;
		
		recipes = RecipeDao.getInstance().getRecipesByIdUser(getIdUser());
		return null;
	}

	public void onDeleteRecipe(int idRecipe) {
		if (!recipes.isEmpty()) 
		{
			for (Recipe recipe : recipes) {
				if (recipe.getIdUser() == getIdUser() && recipe.getId() == idRecipe) {
					RecipeDao.getInstance().deleteByRecipe(recipe);
					return;
				}
			}
		}
	}
}

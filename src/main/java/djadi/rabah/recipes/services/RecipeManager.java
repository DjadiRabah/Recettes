package djadi.rabah.recipes.services;

import djadi.rabah.recipes.dao.impl.RecipeDao;
import djadi.rabah.recipes.pojo.Recipe;

public class RecipeManager 
{
	private static RecipeManager manager;
	
	private RecipeManager()
	{
	}
	
	public static RecipeManager getInstance()
	{
		if(manager == null)
			manager = new RecipeManager();
		return manager;
	}
	
	public void deleteRecipe(int idRecipe)
	{	
		Recipe recipe = RecipeDao.getInstance().getById(idRecipe);
		ImageUploader.getInstance().destroy(recipe.getImage());
		RecipeDao.getInstance().deleteById(idRecipe);
	}
}

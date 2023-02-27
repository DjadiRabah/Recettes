package djadi.rabah.recipes.pages;

import java.util.List;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import djadi.rabah.recipes.dao.impl.ActionDao;
import djadi.rabah.recipes.dao.impl.IngredientDao;
import djadi.rabah.recipes.dao.impl.InstructionDao;
import djadi.rabah.recipes.dao.impl.RecipeDao;
import djadi.rabah.recipes.pojo.Action;
import djadi.rabah.recipes.pojo.Ingredient;
import djadi.rabah.recipes.pojo.Instruction;
import djadi.rabah.recipes.pojo.Recipe;
import djadi.rabah.recipes.services.ImageUploader;

@Import(module = "Cloudinary")
public class ManageRecipe extends BasePage {
	@Inject
	private AlertManager alertManager;

	@Environmental
	private JavaScriptSupport javaScriptSupport;

	@InjectComponent("beanEditForm")
	private BeanEditForm beanEditForm;

	@Property
	private Recipe recipe;

	@Property
	private Ingredient ingredient;

	@Property
	private List<Ingredient> ingredients;

	@Property
	private Instruction instruction;

	@Property
	private List<Instruction> instructions;

	@Property
	private String url;
	
	public boolean getIsNewRecipe() {
		return recipe.getId() == 0;
	}

	public String getFunction() {
		return getIsNewRecipe() ? "Créer" : "Modifier";
	}

	public int onPassivate() {
		return recipe.getId();
	}

	@AfterRender
	void setup() {
		this.javaScriptSupport.importJavaScriptLibrary("https://widget.cloudinary.com/v2.0/global/all.js");
	}

	@Override
	public Object onActivate(EventContext eventContext) {
		if (!getIsConnected())
			return Login.class;

		if (eventContext.getCount() == 1) {
			int idRecipe = eventContext.get(Integer.class, 0);
			ingredient = new Ingredient();
			instruction = new Instruction();

			ingredient.setIdRecipe(idRecipe);
			instruction.setIdRecipe(idRecipe);
			if (idRecipe == 0) {
				recipe = new Recipe();
				recipe.setIdUser(getIdUser());
				recipe.setId(0);
			} else {
				recipe = RecipeDao.getInstance().getById(idRecipe);
				if (recipe.getId() == 0) {
					idRecipe = 0;
					return this;
				}
				ingredients = IngredientDao.getInstance().getIngredientsByIdRecipe(idRecipe);
				if (recipe.getImage() != null)
					url = ImageUploader.getInstance().getImage(recipe.getImage());
				instructions = InstructionDao.getInstance().getInstructionsByIdRecipe(idRecipe);
			}
		}

		else if (eventContext.getCount() == 5) {
			int idRecipe = eventContext.get(Integer.class, 0);
			String privat = eventContext.get(String.class, 1);
			String v = eventContext.get(String.class, 2);
			String folder = eventContext.get(String.class, 3);
			String fileName = eventContext.get(String.class, 4);
			recipe = RecipeDao.getInstance().getById(idRecipe);
			if (!isCreator())
				return Index.class;
			String filePath = privat + "/" + v + "/" + folder + "/" + fileName;

			if (recipe.getId() == 0) {
				recipe.setId(0);
				ImageUploader.getInstance().destroy(filePath);
				return this;
			}
			if (recipe.getImage() != null)
				ImageUploader.getInstance().destroy(recipe.getImage());
			recipe.setImage(filePath);
			RecipeDao.getInstance().update(recipe);
			return this;
		}

		else if (eventContext.getCount() == 0 || eventContext.getCount() == 2 || eventContext.getCount() == 3
				|| eventContext.getCount() == 4 || eventContext.getCount() > 5) {
			recipe = new Recipe();
			recipe.setId(0);
			return this;
		}

		return null;
	}

	public boolean isCreator() {
		return recipe.getId() == 0 ? true : recipe.getIdUser() == getIdUser();
	}

	public void onValidateFromBeanEditForm() {
		if (recipe.getName() == null) {
			alertManager.error("La recette doit avoir un nom");
		} else {
			recipe.setName(recipe.getName().trim());
			recipe.setName(
					recipe.getName().substring(0, 1).toUpperCase() + recipe.getName().substring(1).toLowerCase());
			if (getIsNewRecipe()) {
				int idRecipe = RecipeDao.getInstance().insert(recipe);
				recipe.setId(idRecipe);
			} else {
				RecipeDao.getInstance().update(recipe);
			}
		}
	}
	
	public Action getAction(int idAction)
	{
		return ActionDao.getInstance().getById(idAction);
	}
}

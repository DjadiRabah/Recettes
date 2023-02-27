package djadi.rabah.recipes.select;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.OptionGroupModel;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.util.AbstractSelectModel;

import djadi.rabah.recipes.dao.impl.AlimentDao;
import djadi.rabah.recipes.dao.impl.IngredientDao;
import djadi.rabah.recipes.pojo.Ingredient;

public class ActionIdSelectModel extends AbstractSelectModel {

	private List<String> ingredients;

	public ActionIdSelectModel(int idRecipe) {
		this.ingredients = new ArrayList<>();
		for(Ingredient ingredient : IngredientDao.getInstance().getIngredientsByIdRecipe(idRecipe))
		{
			ingredients.add(AlimentDao.getInstance().getById(ingredient.getIdAliment()).getAliment());
		}
	}

	@Override
	public List<OptionGroupModel> getOptionGroups() {
		return null;
	}

	@Override
	public List<OptionModel> getOptions() {
		List<OptionModel> options = new ArrayList<OptionModel>();
		for (String ingredient : ingredients) {
			options.add(new OptionModelImpl("", ingredient));
		}
		return options;
	}

}

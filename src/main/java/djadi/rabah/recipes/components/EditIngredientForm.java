package djadi.rabah.recipes.components;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;

import djadi.rabah.recipes.dao.impl.AlimentDao;
import djadi.rabah.recipes.dao.impl.IngredientDao;
import djadi.rabah.recipes.dao.impl.MeasureDao;
import djadi.rabah.recipes.pages.BasePage;
import djadi.rabah.recipes.pojo.Ingredient;
import djadi.rabah.recipes.pojo.Measure;
import djadi.rabah.recipes.select.AlimentIdSelectModel;
import djadi.rabah.recipes.select.MeasureIdSelectModel;

public class EditIngredientForm extends BasePage
{
	@InjectComponent("editForm")
	private Form editForm;
	
	@Parameter(required=true)
    @Property
    private int idIngredient;
	
	@Property
	private int idRecipe;
	
	@Property
	private int idMeasure;
	
	@Property
	private int idAliment;
	
	@Property
	private float amount;
	
	@Parameter(required=true)
	private int idCreator;
	
	@Property
	private SelectModel measureSelectModel;

	@Property
	private SelectModel alimentSelectModel;
	
	public void setupRender() {
		Ingredient ingredient = IngredientDao.getInstance().getById(idIngredient);
		idRecipe = ingredient.getIdRecipe();
		idMeasure = ingredient.getIdMeasure();
		idAliment = ingredient.getIdAliment();
		amount = ingredient.getAmount();
		List<Measure> measures = MeasureDao.getInstance().getAll();
		Collections.sort(measures, new Comparator<Measure>() {

			@Override
			public int compare(Measure o1, Measure o2) {
				if(o1.getMeasure() == null)
					return "".compareTo(o2.getMeasure());
				if(o2.getMeasure() == null)
					return o1.getMeasure().compareTo("");
				return o1.getMeasure().compareTo(o2.getMeasure());
			}
		});
		measureSelectModel = new MeasureIdSelectModel(measures);
		alimentSelectModel = new AlimentIdSelectModel(AlimentDao.getInstance().getAll());
	}
	
	public void onValidateFromEditForm()
	{
		Ingredient ingredient = new Ingredient();
		ingredient.setId(idIngredient);
		ingredient.setIdRecipe(idRecipe);
		ingredient.setIdMeasure(idMeasure);
		ingredient.setIdAliment(idAliment);
		ingredient.setAmount(amount);
		IngredientDao.getInstance().update(ingredient);
	}
	
	public void onDeleteIngredient(int idIngredient)
	{
		if(idCreator == getIdUser())
			IngredientDao.getInstance().deleteById(idIngredient);
	}
}

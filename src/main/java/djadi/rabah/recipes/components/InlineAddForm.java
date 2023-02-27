package djadi.rabah.recipes.components;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import djadi.rabah.recipes.dao.Dao;
import djadi.rabah.recipes.dao.impl.ActionDao;
import djadi.rabah.recipes.dao.impl.AlimentDao;
import djadi.rabah.recipes.dao.impl.MeasureDao;
import djadi.rabah.recipes.pages.BasePage;
import djadi.rabah.recipes.pojo.Action;
import djadi.rabah.recipes.pojo.Aliment;
import djadi.rabah.recipes.pojo.Measure;
import djadi.rabah.recipes.pojo.Pojo;

public class InlineAddForm<T extends Pojo> extends BasePage
{
	private Dao<T> dao;

	@Inject
	private AlertManager alertManager;

	@InjectComponent("form")
	private Form form;

	@Property
	private String input;
	
	@Parameter(required = true)
	@Property
	private String placeHolder;
	
	@Parameter(required = true)
	@Property
	private String value;

	public void onValidateFromForm() {
		if (getIsConnected() && getIsAdmin()) {
			if (input != null) {
				input = input.trim();
				input = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
	
				if(dao instanceof AlimentDao)
				{
					boolean isAlreadyStored = false;
					for (Aliment currentAliment : ((AlimentDao) dao).getAll()) {
						if (currentAliment.getAliment().equals(input)) {
							isAlreadyStored = true;
							alertManager.error("L'aliment " + input + " est déjà enregistré.");
							return;
						}
					}

					if (!isAlreadyStored) {
						Aliment newAliment = new Aliment();
						newAliment.setAliment(input);
						((AlimentDao) dao).insert(newAliment);
					}
				}
				else if(dao instanceof MeasureDao)
				{
					if(((MeasureDao) dao).getByMeasure(input).getId() == 0)
					{
						Measure newMeasure = new Measure();
						newMeasure.setMeasure(input);
						((MeasureDao) dao).insert(newMeasure);
					}
					else
						alertManager.error(input + " est déjà enregistré.");
				}
				
				else if(dao instanceof ActionDao)
				{
					for(Action currentAction : ActionDao.getInstance().getAll())
					{
						if (currentAction.getAction().equals(input))
						{
							alertManager.error(input + " est déjà enregistré.");
							return;
						}
					}
					Action newAction = new Action();
					newAction.setAction(input);
					ActionDao.getInstance().insert(newAction);
				}
			}
		}
	}

	public void setDao(Dao<T> dao) {
		this.dao = dao;
	}
}

package djadi.rabah.recipes.components;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import djadi.rabah.recipes.dao.impl.MeasureDao;
import djadi.rabah.recipes.pages.BasePage;
import djadi.rabah.recipes.pojo.Measure;

public class AddMeasureForm extends BasePage {
	@Inject
	private AlertManager alertManager;

	@InjectComponent("form")
	private Form form;

	@Property
	private String measure;

	public void onValidateFromForm() {
		if (getIsConnected() && getIsAdmin()) {
			if (measure != null) {
				measure = measure.trim();
				measure = measure.substring(0, 1).toUpperCase() + measure.substring(1).toLowerCase();

				if(MeasureDao.getInstance().getByMeasure(measure).getId() == 0)
				{
					Measure newMeasure = new Measure();
					newMeasure.setMeasure(measure);
					MeasureDao.getInstance().insert(newMeasure);
				}
			}
		}
	}
}

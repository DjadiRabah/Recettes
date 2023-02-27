package djadi.rabah.recipes.components;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import djadi.rabah.recipes.dao.impl.MeasureDao;
import djadi.rabah.recipes.pages.BasePage;
import djadi.rabah.recipes.pojo.Measure;

public class EditMeasureForm extends BasePage {
	@Inject
	private AlertManager alertManager;

	@InjectComponent("editForm")
	private Form editForm;

	@Parameter(required = true)
	@Property
	private Measure measure;

	public void onValidateFromEditForm() {
		if (getIsConnected() && getIsAdmin()) {
			if (measure.getMeasure() != null) {
				String measureString = measure.getMeasure().trim();
				measureString = measureString.substring(0, 1).toUpperCase() + measureString.substring(1).toLowerCase();
				
				if(MeasureDao.getInstance().getByMeasure(measureString).getId() == 0)
				{
					measure.setMeasure(measureString);
					MeasureDao.getInstance().update(measure);
				}
			}
		}
	}

	public void onDeleteMeasure(int idMeasure) {
		if (getIsConnected() && getIsAdmin())
			MeasureDao.getInstance().deleteById(idMeasure);
	}
}

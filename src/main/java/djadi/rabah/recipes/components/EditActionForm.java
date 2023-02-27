package djadi.rabah.recipes.components;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import djadi.rabah.recipes.dao.impl.ActionDao;
import djadi.rabah.recipes.pages.BasePage;
import djadi.rabah.recipes.pojo.Action;

public class EditActionForm extends BasePage {
	@Inject
	private AlertManager alertManager;

	@InjectComponent("form")
	private Form form;

	@Parameter(required = true)
	@Property
	private Action action;

	public void onValidateFromForm() {
		if (getIsConnected() && getIsAdmin()) {
			if (action.getAction() != null) {
				String string = action.getAction().trim();
				string = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();

				boolean isAlreadyStored = false;
				for (Action currentAction : ActionDao.getInstance().getAll()) {
					if (currentAction.getAction().equals(string)) {
						isAlreadyStored = true;
						if (currentAction.getId() != action.getId())
							alertManager.error("L'action " + string + " est déjà enregistrée.");
						return;
					}
				}

				if (!isAlreadyStored) {
					action.setAction(string);
					ActionDao.getInstance().update(action);
				}
			}
		}
	}

	public void onDeleteAction(int id) {
		if (getIsConnected() && getIsAdmin())
			ActionDao.getInstance().deleteById(id);
	}
}

package djadi.rabah.recipes.components;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import djadi.rabah.recipes.dao.impl.AlimentDao;
import djadi.rabah.recipes.pages.BasePage;
import djadi.rabah.recipes.pojo.Aliment;

public class AddAlimentForm extends BasePage {
	@Inject
	private AlertManager alertManager;

	@InjectComponent("form")
	private Form form;

	@Property
	private String aliment;

	public void onValidateFromForm() {
		if (getIsConnected() && getIsAdmin()) {
			if (aliment != null) {
				aliment = aliment.trim();
				aliment = aliment.substring(0, 1).toUpperCase() + aliment.substring(1).toLowerCase();

				boolean isAlreadyStored = false;
				for (Aliment currentAliment : AlimentDao.getInstance().getAll()) {
					if (currentAliment.getAliment().equals(aliment)) {
						isAlreadyStored = true;
						alertManager.error("L'aliment " + aliment + " est déjà enregistré.");
						return;
					}
				}

				if (!isAlreadyStored) {
					Aliment newAliment = new Aliment();
					newAliment.setAliment(aliment);
					AlimentDao.getInstance().insert(newAliment);
				}
			}
		}
	}
}

package djadi.rabah.recipes.components;

import java.util.List;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import djadi.rabah.recipes.dao.impl.AlimentDao;
import djadi.rabah.recipes.pages.BasePage;
import djadi.rabah.recipes.pojo.Aliment;

public class EditAlimentForm extends BasePage {
	@Inject
	private AlertManager alertManager;

	@InjectComponent("editForm")
	private Form editForm;

	@Parameter(required = true)
	@Property
	private Aliment aliment;

	@Parameter(required = true)
	@Property
	private List<Aliment> aliments;

	public void onValidateFromEditForm() {
		if (getIsConnected() && getIsAdmin()) {
			if (aliment.getAliment() != null) {
				String alimentString = aliment.getAliment().trim();
				alimentString = alimentString.substring(0, 1).toUpperCase() + alimentString.substring(1).toLowerCase();

				boolean isAlreadyStored = false;
				for (Aliment currentAliment : AlimentDao.getInstance().getAll()) {
					if (currentAliment.getAliment().equals(alimentString)) {
						isAlreadyStored = true;
						if (currentAliment.getId() != aliment.getId())
							alertManager.error("L'aliment " + alimentString + " est déjà enregistré.");
						return;
					}
				}

				if (!isAlreadyStored) {
					aliment.setAliment(alimentString);
					AlimentDao.getInstance().update(aliment);
				}
			}
		}
	}

	public void onDeleteAliment(int id) {
		if (getIsConnected() && getIsAdmin())
			AlimentDao.getInstance().deleteById(id);
	}
}

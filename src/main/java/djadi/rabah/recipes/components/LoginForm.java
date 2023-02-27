package djadi.rabah.recipes.components;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import djadi.rabah.recipes.dao.impl.UserDao;
import djadi.rabah.recipes.pages.BasePage;
import djadi.rabah.recipes.pages.Index;
import djadi.rabah.recipes.pojo.User;
import djadi.rabah.recipes.services.PasswordHash;

public class LoginForm extends BasePage {

	@Inject
	private AlertManager alertManager;

	@Property
	private String userLogin;

	@Property
	private String password;

	@InjectComponent
	private Form form;

	void onValidateFromForm() throws NoSuchAlgorithmException, InvalidKeySpecException {
		User user = UserDao.getInstance().getByLogin(userLogin);

		if (userLogin.equals(user.getLogin())) {
			if (!PasswordHash.validatePassword(password, user.getSalt(), user.getHash())) {
				form.recordError("Erreur dans le mot de passe");
			} else {
				this.connect(user);
				return;
			}
		}

		form.recordError("Ce compte n'existe pas");
	}

	Object onSuccessFromForm() {
		return Index.class;
	}

	void onFailureFromForm() {
		alertManager.error("Votre login ou mot de passe n'est pas bon");
	}
}

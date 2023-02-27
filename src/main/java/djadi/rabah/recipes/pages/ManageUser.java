package djadi.rabah.recipes.pages;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import djadi.rabah.recipes.dao.impl.SettingsDao;
import djadi.rabah.recipes.dao.impl.UserDao;
import djadi.rabah.recipes.pojo.Settings;
import djadi.rabah.recipes.pojo.User;
import djadi.rabah.recipes.services.PasswordHash;

public class ManageUser extends BasePage {
	@Inject
	private AlertManager alertManager;

	@Property
	private User user;

	@InjectComponent("form")
	private Form form;

	public boolean getIsNewUser() {
		return user.getId() == 0;
	}

	public int onPassivate() {
		return user.getId();
	}
	
	@Override
	public Object onActivate(EventContext eventContext)
	{
		if(!getIsConnected())
			return Login.class;
		if(!getIsAdmin())
			return Index.class;
		
		user = new User();
		if (eventContext.getCount() != 1)
		{
			user.setId(0);
			return ManageUser.class;
		}
		
		user.setId(eventContext.get(Integer.class, 0));
		if (user.getId() != 0) {
			user = UserDao.getInstance().getById(user.getId());
			if (user.getId() == 0)
				return this;
		}
		return null;
	}

	void onValidateFromForm() throws NoSuchAlgorithmException, InvalidKeySpecException {
		if (getIsAdmin()) {
			if (getIsNewUser()) {
				String login = user.getLogin().trim();
				login = login.substring(0, 1).toUpperCase() + login.substring(1).toLowerCase();
				user.setLogin(login);
				user.setAdmin(false);
				byte[] salt = PasswordHash.createSalt();
				String hash = PasswordHash.createHash(user.getHash(), salt);
				user.setSalt(PasswordHash.toHex(salt));
				user.setHash(hash);
				user.setId(UserDao.getInstance().insert(user));
				Settings settings = new Settings();
				settings.setIdUser(user.getId());
				settings.setTheme("cerulean");
				SettingsDao.getInstance().insert(settings);
			} else {
				byte[] salt = PasswordHash.createSalt();
				String hash = PasswordHash.createHash(user.getHash(), salt);
				user.setSalt(PasswordHash.toHex(salt));
				user.setHash(hash);
				UserDao.getInstance().update(user);
			}
		}
	}
}

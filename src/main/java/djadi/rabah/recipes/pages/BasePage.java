package djadi.rabah.recipes.pages;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.apache.tapestry5.services.javascript.StylesheetLink;

import djadi.rabah.recipes.dao.impl.SettingsDao;
import djadi.rabah.recipes.pojo.Settings;
import djadi.rabah.recipes.pojo.User;

public class BasePage {
	private final String AUTH_TOKEN = "authToken";
	private final String SETTINGS_TOKEN = "settingsToken";

	@Inject
	private Request request;

	@Environmental
	private JavaScriptSupport javaScriptSupport;

	public void connect(User user) {
		if (!getIsConnected()) {
			user.setSalt("");
			user.setHash("");
			request.getSession(true).setAttribute(AUTH_TOKEN, user);
			setSettings(SettingsDao.getInstance().getByIdUser(user.getId()));
		}
	}

	public void setSettings(Settings settings) {
		request.getSession(true).setAttribute(SETTINGS_TOKEN, settings);
	}

	public User getConnectedUser() {
		if (getIsConnected()) {
			return (User) request.getSession(true).getAttribute(AUTH_TOKEN);
		}
		return null;
	}

	private Settings getSettings() {
		if (getIsConnected()) {
			return (Settings) request.getSession(true).getAttribute(SETTINGS_TOKEN);
		}
		return null;
	}

	public String getTheme() {
		return getSettings().getTheme();
	}

	public String getLogin() {
		return getConnectedUser().getLogin();
	}

	public int getIdUser() {
		return getConnectedUser().getId();
	}

	public boolean getIsConnected() {
		Session session = request.getSession(false);
		if (session != null) {
			return session.getAttribute(AUTH_TOKEN) != null;
		}
		return false;
	}

	public void disconnect() {
		Session session = request.getSession(false);
		if (session != null) {
			session.setAttribute(AUTH_TOKEN, null);
			session.invalidate();
		}
	}

	public boolean getIsAdmin() {
		return getConnectedUser().getAdmin();
	}

	public Object onActivate(EventContext eventContext) {
		if (!getIsConnected())
			return Login.class;
		return Index.class;
	}

	@AfterRender
	void setup() {
		javaScriptSupport.importJavaScriptLibrary("https://code.jquery.com/jquery-3.6.3.min.js");
		if (getIsConnected())
		{
			javaScriptSupport
					.importStylesheet(new StylesheetLink("https://bootswatch.com/5/" + getTheme() + "/bootstrap.css"));
			//javaScriptSupport.require("DarkMode");
		}
	}
}

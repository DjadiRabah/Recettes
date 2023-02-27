package djadi.rabah.recipes.pages;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import djadi.rabah.recipes.dao.impl.SettingsDao;
import djadi.rabah.recipes.pojo.Settings;

public class ManageTheme extends BasePage {
	@Environmental
	private JavaScriptSupport javaScriptSupport;

	@Override
	public Object onActivate(EventContext eventContext) {
		if (eventContext.getCount() > 1)
			return ManageTheme.class;
		if (!getIsConnected())
			return Login.class;
		if (eventContext.getCount() == 1) {
			String theme = eventContext.get(String.class, 0);
			Settings settings = SettingsDao.getInstance().getByIdUser(getIdUser());
			if (!settings.getTheme().equals(theme)) {
				settings.setTheme(theme);
				SettingsDao.getInstance().update(settings);
				setSettings(settings);
				return ManageTheme.class;
			}
		}

		return null;
	}

	@AfterRender
	void setup() {
		javaScriptSupport.require("Theme").with("https://bootswatch.com/api/5.json");
	}
}

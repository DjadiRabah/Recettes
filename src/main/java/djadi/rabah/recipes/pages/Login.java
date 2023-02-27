package djadi.rabah.recipes.pages;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Secure;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import com.fasterxml.jackson.databind.ObjectMapper;

@Secure
public class Login extends BasePage {

	@Environmental
	private JavaScriptSupport javaScriptSupport;
	
	@Property
	private String urlbase;

	@Override
	public Object onActivate(EventContext eventContext) {
		if (eventContext.getCount() != 0)
			return Login.class;
		if (getIsConnected())
			return Index.class;

		try {
			ObjectMapper mapper = new ObjectMapper();
			URL url = new URL("https://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=en-fr");

			Map<?, ?> map = mapper.readValue(url, HashMap.class);
			List<?> json = (List<?>) map.get("images");
			LinkedHashMap<?, ?> linkedMap = (LinkedHashMap<?, ?>) json.get(0);
			urlbase = "https://www.bing.com" + (String) linkedMap.get("urlbase") + "_1920x1080.jpg";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

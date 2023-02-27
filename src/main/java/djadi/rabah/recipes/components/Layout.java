package djadi.rabah.recipes.components;

import java.time.LocalDate;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;

import djadi.rabah.recipes.pages.BasePage;
import djadi.rabah.recipes.pages.Login;

/**
 * Layout component for pages of application test-project.
 */
@Import(module= {"Bootstrap"}, library= {"https://cdn.jsdelivr.net/npm/darkmode-js@1.5.7/lib/darkmode-js.js"}, stylesheet="context:mybootstrap/css/customcss.css")
public class Layout extends BasePage
{
    @Inject
    private ComponentResources resources;

    /**
    * The page title, for the <title> element and the <h1> element.
    */
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String title;

    @Property
    private String pageName;

    @Property
    @Inject
    @Symbol(SymbolConstants.APPLICATION_VERSION)
    private String appVersion;

    public String getClassForPageName()
    {
        return resources.getPageName().equalsIgnoreCase(pageName)
            ? "active"
            : null;
    }

    public String[] getPageNames()
    {
        return new String[]{"Index", "About"};
    }

    public int getYear()
    {
        return LocalDate.now().getYear();
    }
    
    Object onDisconnect() 
    {
    	this.disconnect();
        return Login.class;
    }
}

package djadi.rabah.recipes.pages;

import java.util.List;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;

import djadi.rabah.recipes.components.InlineAddForm;
import djadi.rabah.recipes.dao.impl.AlimentDao;
import djadi.rabah.recipes.pojo.Aliment;

public class ManageAliments extends BasePage
{
	@Property
	private Aliment aliment;
	
	@Property
	private List<Aliment> aliments;
	
	@InjectComponent
	private InlineAddForm<Aliment> inlineAddForm;
	
	@Override
	public Object onActivate(EventContext eventContext)
	{
		if (eventContext.getCount() != 0)
			return ManageAliments.class;
		if(!getIsConnected())
			return Login.class;
		if(!getIsAdmin())
			return Index.class;
		aliment = new Aliment();
		aliments = AlimentDao.getInstance().getAll();
		
		
		inlineAddForm.setDao(AlimentDao.getInstance());
		
		return null;
	}
}

package djadi.rabah.recipes.pages;

import java.util.List;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.annotations.Property;

import djadi.rabah.recipes.dao.impl.MeasureDao;
import djadi.rabah.recipes.pojo.Measure;

public class ManageMeasures extends BasePage
{
	@Property
	private Measure measure;
	
	@Property
	private List<Measure> measures;
	
	@Override
	public Object onActivate(EventContext eventContext)
	{
		if (eventContext.getCount() != 0)
			return ManageMeasures.class;
		if(!getIsConnected())
			return Login.class;
		if(!getIsAdmin())
			return Index.class;
		measure = new Measure();
		measures = MeasureDao.getInstance().getAll();
		
		return null;
	}
}

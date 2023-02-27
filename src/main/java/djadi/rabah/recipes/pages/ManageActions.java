package djadi.rabah.recipes.pages;

import java.util.List;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;

import djadi.rabah.recipes.components.InlineAddForm;
import djadi.rabah.recipes.dao.impl.ActionDao;
import djadi.rabah.recipes.pojo.Action;

public class ManageActions extends BasePage
{
	@Property
	private Action action;
	
	@Property
	private List<Action> actions;
	
	@InjectComponent
	private InlineAddForm<Action> inlineAddForm;
	
	@Override
	public Object onActivate(EventContext eventContext)
	{
		if (eventContext.getCount() != 0)
			return ManageActions.class;
		if(!getIsConnected())
			return Login.class;
		if(!getIsAdmin())
			return Index.class;
		action = new Action();
		actions = ActionDao.getInstance().getAll();
		
		inlineAddForm.setDao(ActionDao.getInstance());
		
		return null;
	}

}

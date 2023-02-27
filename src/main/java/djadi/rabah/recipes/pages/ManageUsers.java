package djadi.rabah.recipes.pages;

import java.util.List;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.annotations.Property;

import djadi.rabah.recipes.dao.impl.UserDao;
import djadi.rabah.recipes.pojo.User;

public class ManageUsers extends BasePage
{
	@Property
	private User user;
	
	@Property
	private List<User> users;
	
	@Override
	public Object onActivate(EventContext eventContext)
	{
		if (eventContext.getCount() != 0)
			return ManageUsers.class;
		if(!getIsConnected())
			return Login.class;
		if(!getIsAdmin())
			return Index.class;
		
		return null;
	}
	
	public void setupRender()
	{
		users = UserDao.getInstance().getAll();
	}
}

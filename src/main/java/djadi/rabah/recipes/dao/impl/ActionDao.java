package djadi.rabah.recipes.dao.impl;
// Generated 2 mars 2021 12:58:32 by Hibernate Tools 5.2.10.Final

import java.util.Comparator;
import java.util.List;

import djadi.rabah.recipes.dao.abstractimpl.AbstractActionDaoImpl;
import djadi.rabah.recipes.pojo.Action;

/**
 * Auto Dao object for domain model class Aliment.
 * @see djadi.rabah.recipes.dao.impl.Aliment
 * @author Rabah DJADI
 */
public class ActionDao extends AbstractActionDaoImpl {
	private static ActionDao dao;

	private ActionDao() {
	}

	public static synchronized ActionDao getInstance() {
		if (dao == null)
			dao = new ActionDao();
		return dao;
	}
	
	@Override
	public List<Action> getAll()
	{
		List<Action> actions = super.getAll();
		actions.sort( new Comparator<Action>() {
			@Override
			public int compare(Action o1, Action o2) {
				return o1.getAction().compareTo(o2.getAction());
			}
		});
		return actions;
	}
}

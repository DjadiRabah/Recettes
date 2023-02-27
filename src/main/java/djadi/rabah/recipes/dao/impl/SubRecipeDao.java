package djadi.rabah.recipes.dao.impl;
// Generated 2 mars 2021 12:58:32 by Hibernate Tools 5.2.10.Final

import djadi.rabah.recipes.dao.abstractimpl.AbstractSubRecipeDaoImpl;

/**
 * Auto Dao object for domain model class SubRecipe.
 * @see djadi.rabah.recipes.dao.impl.SubRecipe
 * @author Rabah DJADI
 */
public class SubRecipeDao extends AbstractSubRecipeDaoImpl {
	private static SubRecipeDao dao;

	private SubRecipeDao() {
	}

	public static synchronized SubRecipeDao getInstance() {
		if (dao == null)
			dao = new SubRecipeDao();
		return dao;
	}
}

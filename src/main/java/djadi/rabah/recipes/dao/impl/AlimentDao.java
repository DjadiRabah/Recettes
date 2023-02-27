package djadi.rabah.recipes.dao.impl;
// Generated 2 mars 2021 12:58:32 by Hibernate Tools 5.2.10.Final

import java.util.Comparator;
import java.util.List;

import djadi.rabah.recipes.dao.abstractimpl.AbstractAlimentDaoImpl;
import djadi.rabah.recipes.pojo.Aliment;

/**
 * Auto Dao object for domain model class Aliment.
 * @see djadi.rabah.recipes.dao.impl.Aliment
 * @author Rabah DJADI
 */
public class AlimentDao extends AbstractAlimentDaoImpl {
	private static AlimentDao dao;

	private AlimentDao() {
	}

	public static synchronized AlimentDao getInstance() {
		if (dao == null)
			dao = new AlimentDao();
		return dao;
	}
	
	@Override
	public List<Aliment> getAll()
	{
		List<Aliment> aliments = super.getAll();
		aliments.sort( new Comparator<Aliment>() {
			@Override
			public int compare(Aliment o1, Aliment o2) {
				return o1.getAliment().compareTo(o2.getAliment());
			}
		});
		return aliments;
	}
}

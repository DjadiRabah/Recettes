package djadi.rabah.recipes.dao.impl;
// Generated 2 mars 2021 12:58:32 by Hibernate Tools 5.2.10.Final

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.List;

import djadi.rabah.recipes.dao.DaoConnection;
import djadi.rabah.recipes.dao.abstractimpl.AbstractMeasureDaoImpl;
import djadi.rabah.recipes.pojo.Measure;

/**
 * Auto Dao object for domain model class Measure.
 * @see djadi.rabah.recipes.dao.impl.Measure
 * @author Rabah DJADI
 */
public class MeasureDao extends AbstractMeasureDaoImpl {
	private static MeasureDao dao;

	private MeasureDao() {
	}

	public static synchronized MeasureDao getInstance() {
		if (dao == null)
			dao = new MeasureDao();
		return dao;
	}
	
	@Override
	public List<Measure> getAll()
	{
		List<Measure> measures = super.getAll();
		measures.sort( new Comparator<Measure>() {
			@Override
			public int compare(Measure o1, Measure o2) {
				if(o1.getMeasure() == null)
					return "".compareTo(o2.getMeasure());
				if(o2.getMeasure() == null)
					return o1.getMeasure().compareTo("");
				return o1.getMeasure().compareTo(o2.getMeasure());
			}
		});
		return measures;
	}
	
	public Measure getByMeasure(String name) {
		try {
			Measure pojo = new Measure();
			String query = "SELECT * FROM " + TABLE + " WHERE measure = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				pojo.setId(resultSet.getInt("id"));
				pojo.setMeasure(resultSet.getString("measure"));
			}

			resultSet.close();
			statement.close();

			return pojo;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}

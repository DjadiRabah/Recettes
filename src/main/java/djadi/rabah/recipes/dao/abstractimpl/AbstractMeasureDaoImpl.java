package djadi.rabah.recipes.dao.abstractimpl;
// Generated 16 mars 2021 17:11:13 by Hibernate Tools 5.2.10.Final

import djadi.rabah.recipes.pojo.Measure;
import djadi.rabah.recipes.dao.Dao;
import djadi.rabah.recipes.dao.DaoConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.google.common.base.CaseFormat;

/**
 * Auto Dao object for domain model class Measure.
 * @see djadi.rabah.recipes.dao.abstractimpl.Measure
 * @author Rabah DJADI
 */
public abstract class AbstractMeasureDaoImpl implements Dao<Measure> {

	protected final String TABLE = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "Measure");

	@Override
	public void deleteById(int idMeasure) {
		try {
			String query = "DELETE FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query);
			statement.setInt(1, idMeasure);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	* Update the database's Measure table
	* @see Dao#update()
	*/
	@Override
	public void update(Measure pojo) {
		try {

			String query = "UPDATE " + TABLE + " SET measure = ? WHERE id = ?";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query);
			preparedStatement.setString(1, pojo.getMeasure());
			preparedStatement.setInt(2, pojo.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(Measure pojo) {
		try {

			String query = "INSERT INTO " + TABLE + " (id,measure) VALUES (?,?);";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, pojo.getId());
			preparedStatement.setString(2, pojo.getMeasure());
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			int id = resultSet.getInt(1);
			preparedStatement.close();
			resultSet.close();

			return id;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public List<Measure> getAll() {
		try {
			List<Measure> list = new ArrayList<Measure>();
			String query = "SELECT * FROM " + TABLE;
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Measure pojo = new Measure();
				pojo.setId(resultSet.getInt("id"));
				pojo.setMeasure(resultSet.getString("measure"));
				list.add(pojo);
			}

			resultSet.close();
			statement.close();

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Measure getById(int idMeasure) {
		try {
			Measure pojo = new Measure();
			String query = "SELECT * FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idMeasure);
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

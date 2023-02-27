package djadi.rabah.recipes.dao.abstractimpl;
// Generated 16 mars 2021 17:11:13 by Hibernate Tools 5.2.10.Final

import djadi.rabah.recipes.pojo.Aliment;
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
 * Auto Dao object for domain model class Aliment.
 * @see djadi.rabah.recipes.dao.abstractimpl.Aliment
 * @author Rabah DJADI
 */
public abstract class AbstractAlimentDaoImpl implements Dao<Aliment> {

	protected final String TABLE = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "Aliment");

	@Override
	public void deleteById(int idAliment) {
		try {
			String query = "DELETE FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query);
			statement.setInt(1, idAliment);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	* Update the database's Aliment table
	* @see Dao#update()
	*/
	@Override
	public void update(Aliment pojo) {
		try {

			String query = "UPDATE " + TABLE + " SET aliment = ? WHERE id = ?";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query);
			preparedStatement.setString(1, pojo.getAliment());
			preparedStatement.setInt(2, pojo.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(Aliment pojo) {
		try {

			String query = "INSERT INTO " + TABLE + " (id,aliment) VALUES (?,?);";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, pojo.getId());
			preparedStatement.setString(2, pojo.getAliment());
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
	public List<Aliment> getAll() {
		try {
			List<Aliment> list = new ArrayList<Aliment>();
			String query = "SELECT * FROM " + TABLE;
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Aliment pojo = new Aliment();
				pojo.setId(resultSet.getInt("id"));
				pojo.setAliment(resultSet.getString("aliment"));
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
	public Aliment getById(int idAliment) {
		try {
			Aliment pojo = new Aliment();
			String query = "SELECT * FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idAliment);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				pojo.setId(resultSet.getInt("id"));
				pojo.setAliment(resultSet.getString("aliment"));
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

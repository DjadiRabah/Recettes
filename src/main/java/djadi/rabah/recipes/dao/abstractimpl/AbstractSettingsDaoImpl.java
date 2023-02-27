package djadi.rabah.recipes.dao.abstractimpl;
// Generated 16 mars 2021 17:11:13 by Hibernate Tools 5.2.10.Final

import djadi.rabah.recipes.pojo.Settings;
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
 * Auto Dao object for domain model class Settings.
 * @see djadi.rabah.recipes.dao.abstractimpl.Settings
 * @author Rabah DJADI
 */
public abstract class AbstractSettingsDaoImpl implements Dao<Settings> {

	protected final String TABLE = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "Settings");

	@Override
	public void deleteById(int idSettings) {
		try {
			String query = "DELETE FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query);
			statement.setInt(1, idSettings);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	* Update the database's Settings table
	* @see Dao#update()
	*/
	@Override
	public void update(Settings pojo) {
		try {

			String query = "UPDATE " + TABLE + " SET idUser = ?,theme = ? WHERE id = ?";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query);
			preparedStatement.setInt(1, pojo.getIdUser());
			preparedStatement.setString(2, pojo.getTheme());
			preparedStatement.setInt(3, pojo.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(Settings pojo) {
		try {

			String query = "INSERT INTO " + TABLE + " (id,idUser,theme) VALUES (?,?,?);";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, pojo.getId());
			preparedStatement.setInt(2, pojo.getIdUser());
			preparedStatement.setString(3, pojo.getTheme());
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
	public List<Settings> getAll() {
		try {
			List<Settings> list = new ArrayList<Settings>();
			String query = "SELECT * FROM " + TABLE;
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Settings pojo = new Settings();
				pojo.setId(resultSet.getInt("id"));
				pojo.setIdUser(resultSet.getInt("idUser"));
				pojo.setTheme(resultSet.getString("theme"));
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
	public Settings getById(int idSettings) {
		try {
			Settings pojo = new Settings();
			String query = "SELECT * FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idSettings);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				pojo.setId(resultSet.getInt("id"));
				pojo.setIdUser(resultSet.getInt("idUser"));
				pojo.setTheme(resultSet.getString("theme"));
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

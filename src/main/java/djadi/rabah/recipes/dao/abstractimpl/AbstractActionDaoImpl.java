package djadi.rabah.recipes.dao.abstractimpl;
// Generated 16 mars 2021 17:11:13 by Hibernate Tools 5.2.10.Final

import djadi.rabah.recipes.pojo.Action;
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
 * Auto Dao object for domain model class Action.
 * @see djadi.rabah.recipes.dao.abstractimpl.Action
 * @author Rabah DJADI
 */
public abstract class AbstractActionDaoImpl implements Dao<Action> {

	protected final String TABLE = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "Action");

	@Override
	public void deleteById(int idAction) {
		try {
			String query = "DELETE FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query);
			statement.setInt(1, idAction);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	* Update the database's Action table
	* @see Dao#update()
	*/
	@Override
	public void update(Action pojo) {
		try {

			String query = "UPDATE " + TABLE + " SET action = ? WHERE id = ?";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query);
			preparedStatement.setString(1, pojo.getAction());
			preparedStatement.setInt(2, pojo.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(Action pojo) {
		try {

			String query = "INSERT INTO " + TABLE + " (id,action) VALUES (?,?);";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, pojo.getId());
			preparedStatement.setString(2, pojo.getAction());
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
	public List<Action> getAll() {
		try {
			List<Action> list = new ArrayList<Action>();
			String query = "SELECT * FROM " + TABLE;
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Action pojo = new Action();
				pojo.setId(resultSet.getInt("id"));
				pojo.setAction(resultSet.getString("action"));
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
	public Action getById(int idAction) {
		try {
			Action pojo = new Action();
			String query = "SELECT * FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idAction);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				pojo.setId(resultSet.getInt("id"));
				pojo.setAction(resultSet.getString("action"));
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

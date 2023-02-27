package djadi.rabah.recipes.dao.abstractimpl;
// Generated 16 mars 2021 17:11:13 by Hibernate Tools 5.2.10.Final

import djadi.rabah.recipes.pojo.User;
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
 * Auto Dao object for domain model class User.
 * @see djadi.rabah.recipes.dao.abstractimpl.User
 * @author Rabah DJADI
 */
public abstract class AbstractUserDaoImpl implements Dao<User> {

	protected final String TABLE = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "User");

	@Override
	public void deleteById(int idUser) {
		try {
			String query = "DELETE FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query);
			statement.setInt(1, idUser);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	* Update the database's User table
	* @see Dao#update()
	*/
	@Override
	public void update(User pojo) {
		try {

			String query = "UPDATE " + TABLE + " SET admin = ?,login = ?,hash = ?,salt = ? WHERE id = ?";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query);
			preparedStatement.setBoolean(1, pojo.getAdmin());
			preparedStatement.setString(2, pojo.getLogin());
			preparedStatement.setString(3, pojo.getHash());
			preparedStatement.setString(4, pojo.getSalt());
			preparedStatement.setInt(5, pojo.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(User pojo) {
		try {

			String query = "INSERT INTO " + TABLE + " (id,admin,login,hash,salt) VALUES (?,?,?,?,?);";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, pojo.getId());
			preparedStatement.setBoolean(2, pojo.getAdmin());
			preparedStatement.setString(3, pojo.getLogin());
			preparedStatement.setString(4, pojo.getHash());
			preparedStatement.setString(5, pojo.getSalt());
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
	public List<User> getAll() {
		try {
			List<User> list = new ArrayList<User>();
			String query = "SELECT * FROM " + TABLE;
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				User pojo = new User();
				pojo.setId(resultSet.getInt("id"));
				pojo.setAdmin(resultSet.getBoolean("admin"));
				pojo.setLogin(resultSet.getString("login"));
				pojo.setHash(resultSet.getString("hash"));
				pojo.setSalt(resultSet.getString("salt"));
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
	public User getById(int idUser) {
		try {
			User pojo = new User();
			String query = "SELECT * FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idUser);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				pojo.setId(resultSet.getInt("id"));
				pojo.setAdmin(resultSet.getBoolean("admin"));
				pojo.setLogin(resultSet.getString("login"));
				pojo.setHash(resultSet.getString("hash"));
				pojo.setSalt(resultSet.getString("salt"));
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

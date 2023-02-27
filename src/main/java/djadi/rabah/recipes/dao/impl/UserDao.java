package djadi.rabah.recipes.dao.impl;
// Generated 2 mars 2021 12:58:32 by Hibernate Tools 5.2.10.Final

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.List;

import djadi.rabah.recipes.dao.DaoConnection;
import djadi.rabah.recipes.dao.abstractimpl.AbstractUserDaoImpl;
import djadi.rabah.recipes.pojo.User;

/**
 * Auto Dao object for domain model class User.
 * @see djadi.rabah.recipes.dao.impl.User
 * @author Rabah DJADI
 */
public class UserDao extends AbstractUserDaoImpl {
	private static UserDao dao;

	private UserDao() {
	}

	public static synchronized UserDao getInstance() {
		if (dao == null)
			dao = new UserDao();
		return dao;
	}
	
	@Override
	public List<User> getAll()
	{
		List<User> users = super.getAll();
		users.sort( new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				return o1.getLogin().compareTo(o2.getLogin());
			}
		});
		return users;
	}
	
	public User getByLogin(String login) {
		try {
			User pojo = new User();
			String query = "SELECT * FROM " + TABLE + " WHERE login = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, login);
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
	
	public String getLoginByIdUser(int idUser) {
		try {
			String login = "";
			String query = "SELECT login FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idUser);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				login = resultSet.getString("login");
			}

			resultSet.close();
			statement.close();

			return login;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}

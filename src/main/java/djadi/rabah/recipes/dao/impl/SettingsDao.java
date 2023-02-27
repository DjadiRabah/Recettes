package djadi.rabah.recipes.dao.impl;
// Generated 2 mars 2021 12:58:32 by Hibernate Tools 5.2.10.Final

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import djadi.rabah.recipes.dao.DaoConnection;
import djadi.rabah.recipes.dao.abstractimpl.AbstractSettingsDaoImpl;
import djadi.rabah.recipes.pojo.Settings;

/**
 * Auto Dao object for domain model class SubRecipe.
 * @see djadi.rabah.recipes.dao.impl.SubRecipe
 * @author Rabah DJADI
 */
public class SettingsDao extends AbstractSettingsDaoImpl {
	private static SettingsDao dao;

	private SettingsDao() {
	}

	public static synchronized SettingsDao getInstance() {
		if (dao == null)
			dao = new SettingsDao();
		return dao;
	}
	
	public Settings getByIdUser(int idUser) {
		try {
			Settings pojo = new Settings();
			String query = "SELECT * FROM " + TABLE + " WHERE idUser = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idUser);
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

package djadi.rabah.recipes.dao.abstractimpl;
// Generated 16 mars 2021 17:11:13 by Hibernate Tools 5.2.10.Final

import djadi.rabah.recipes.pojo.Recipe;
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
 * Auto Dao object for domain model class Recipe.
 * @see djadi.rabah.recipes.dao.abstractimpl.Recipe
 * @author Rabah DJADI
 */
public abstract class AbstractRecipeDaoImpl implements Dao<Recipe> {

	protected final String TABLE = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "Recipe");

	@Override
	public void deleteById(int idRecipe) {
		try {
			String query = "DELETE FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query);
			statement.setInt(1, idRecipe);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	* Update the database's Recipe table
	* @see Dao#update()
	*/
	@Override
	public void update(Recipe pojo) {
		try {

			String query = "UPDATE " + TABLE + " SET idUser = ?,name = ?,image = ? WHERE id = ?";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query);
			preparedStatement.setInt(1, pojo.getIdUser());
			preparedStatement.setString(2, pojo.getName());
			preparedStatement.setString(3, pojo.getImage());
			preparedStatement.setInt(4, pojo.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(Recipe pojo) {
		try {

			String query = "INSERT INTO " + TABLE + " (id,idUser,name,image) VALUES (?,?,?,?);";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, pojo.getId());
			preparedStatement.setInt(2, pojo.getIdUser());
			preparedStatement.setString(3, pojo.getName());
			preparedStatement.setString(4, pojo.getImage());
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
	public List<Recipe> getAll() {
		try {
			List<Recipe> list = new ArrayList<Recipe>();
			String query = "SELECT * FROM " + TABLE;
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Recipe pojo = new Recipe();
				pojo.setId(resultSet.getInt("id"));
				pojo.setIdUser(resultSet.getInt("idUser"));
				pojo.setName(resultSet.getString("name"));
				pojo.setImage(resultSet.getString("image"));
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
	public Recipe getById(int idRecipe) {
		try {
			Recipe pojo = new Recipe();
			String query = "SELECT * FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idRecipe);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				pojo.setId(resultSet.getInt("id"));
				pojo.setIdUser(resultSet.getInt("idUser"));
				pojo.setName(resultSet.getString("name"));
				pojo.setImage(resultSet.getString("image"));
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

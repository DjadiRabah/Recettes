package djadi.rabah.recipes.dao.abstractimpl;
// Generated 16 mars 2021 17:11:13 by Hibernate Tools 5.2.10.Final

import djadi.rabah.recipes.pojo.SubRecipe;
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
 * Auto Dao object for domain model class SubRecipe.
 * @see djadi.rabah.recipes.dao.abstractimpl.SubRecipe
 * @author Rabah DJADI
 */
public abstract class AbstractSubRecipeDaoImpl implements Dao<SubRecipe> {

	protected final String TABLE = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "SubRecipe");

	@Override
	public void deleteById(int idSubRecipe) {
		try {
			String query = "DELETE FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query);
			statement.setInt(1, idSubRecipe);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	* Update the database's SubRecipe table
	* @see Dao#update()
	*/
	@Override
	public void update(SubRecipe pojo) {
		try {

			String query = "UPDATE " + TABLE + " SET idRecette1 = ?,idRecette2 = ? WHERE id = ?";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query);
			preparedStatement.setInt(1, pojo.getIdRecette1());
			preparedStatement.setInt(2, pojo.getIdRecette2());
			preparedStatement.setInt(3, pojo.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(SubRecipe pojo) {
		try {

			String query = "INSERT INTO " + TABLE + " (id,idRecette1,idRecette2) VALUES (?,?,?);";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, pojo.getId());
			preparedStatement.setInt(2, pojo.getIdRecette1());
			preparedStatement.setInt(3, pojo.getIdRecette2());
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
	public List<SubRecipe> getAll() {
		try {
			List<SubRecipe> list = new ArrayList<SubRecipe>();
			String query = "SELECT * FROM " + TABLE;
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				SubRecipe pojo = new SubRecipe();
				pojo.setId(resultSet.getInt("id"));
				pojo.setIdRecette1(resultSet.getInt("idRecette1"));
				pojo.setIdRecette2(resultSet.getInt("idRecette2"));
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
	public SubRecipe getById(int idSubRecipe) {
		try {
			SubRecipe pojo = new SubRecipe();
			String query = "SELECT * FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idSubRecipe);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				pojo.setId(resultSet.getInt("id"));
				pojo.setIdRecette1(resultSet.getInt("idRecette1"));
				pojo.setIdRecette2(resultSet.getInt("idRecette2"));
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

package djadi.rabah.recipes.dao.abstractimpl;
// Generated 16 mars 2021 17:11:13 by Hibernate Tools 5.2.10.Final

import djadi.rabah.recipes.pojo.Ingredient;
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
 * Auto Dao object for domain model class Ingredient.
 * @see djadi.rabah.recipes.dao.abstractimpl.Ingredient
 * @author Rabah DJADI
 */
public abstract class AbstractIngredientDaoImpl implements Dao<Ingredient> {

	protected final String TABLE = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "Ingredient");

	@Override
	public void deleteById(int idIngredient) {
		try {
			String query = "DELETE FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query);
			statement.setInt(1, idIngredient);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	* Update the database's Ingredient table
	* @see Dao#update()
	*/
	@Override
	public void update(Ingredient pojo) {
		try {

			String query = "UPDATE " + TABLE + " SET idAliment = ?,idMeasure = ?,idRecipe = ?,amount = ? WHERE id = ?";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query);
			preparedStatement.setInt(1, pojo.getIdAliment());
			preparedStatement.setInt(2, pojo.getIdMeasure());
			preparedStatement.setInt(3, pojo.getIdRecipe());
			preparedStatement.setFloat(4, pojo.getAmount());
			preparedStatement.setInt(5, pojo.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(Ingredient pojo) {
		try {

			String query = "INSERT INTO " + TABLE + " (id,idAliment,idMeasure,idRecipe,amount) VALUES (?,?,?,?,?);";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, pojo.getId());
			preparedStatement.setInt(2, pojo.getIdAliment());
			preparedStatement.setInt(3, pojo.getIdMeasure());
			preparedStatement.setInt(4, pojo.getIdRecipe());
			preparedStatement.setFloat(5, pojo.getAmount());
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
	public List<Ingredient> getAll() {
		try {
			List<Ingredient> list = new ArrayList<Ingredient>();
			String query = "SELECT * FROM " + TABLE;
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Ingredient pojo = new Ingredient();
				pojo.setId(resultSet.getInt("id"));
				pojo.setIdAliment(resultSet.getInt("idAliment"));
				pojo.setIdMeasure(resultSet.getInt("idMeasure"));
				pojo.setIdRecipe(resultSet.getInt("idRecipe"));
				pojo.setAmount(resultSet.getFloat("amount"));
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
	public Ingredient getById(int idIngredient) {
		try {
			Ingredient pojo = new Ingredient();
			String query = "SELECT * FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idIngredient);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				pojo.setId(resultSet.getInt("id"));
				pojo.setIdAliment(resultSet.getInt("idAliment"));
				pojo.setIdMeasure(resultSet.getInt("idMeasure"));
				pojo.setIdRecipe(resultSet.getInt("idRecipe"));
				pojo.setAmount(resultSet.getFloat("amount"));
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

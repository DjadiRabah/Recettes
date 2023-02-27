package djadi.rabah.recipes.dao.impl;
// Generated 2 mars 2021 12:58:32 by Hibernate Tools 5.2.10.Final

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import djadi.rabah.recipes.dao.DaoConnection;
import djadi.rabah.recipes.dao.abstractimpl.AbstractIngredientDaoImpl;
import djadi.rabah.recipes.pojo.Ingredient;

/**
 * Auto Dao object for domain model class Ingredient.
 * @see djadi.rabah.recipes.dao.impl.Ingredient
 * @author Rabah DJADI
 */
public class IngredientDao extends AbstractIngredientDaoImpl {
	private static IngredientDao dao;

	private IngredientDao() {
	}

	public static synchronized IngredientDao getInstance() {
		if (dao == null)
			dao = new IngredientDao();
		return dao;
	}
	
	public List<Ingredient> getIngredientsByIdRecipe(int idRecipe) {
		try {
			List<Ingredient> list = new ArrayList<Ingredient>();
			String query = "SELECT * FROM " + TABLE + " WHERE idRecipe = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idRecipe);
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
}

package djadi.rabah.recipes.dao.impl;
// Generated 2 mars 2021 12:58:32 by Hibernate Tools 5.2.10.Final

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import djadi.rabah.recipes.dao.DaoConnection;
import djadi.rabah.recipes.dao.abstractimpl.AbstractRecipeDaoImpl;
import djadi.rabah.recipes.pojo.Recipe;
import djadi.rabah.recipes.services.ImageUploader;

/**
 * Auto Dao object for domain model class Recipe.
 * @see djadi.rabah.recipes.dao.impl.Recipe
 * @author Rabah DJADI
 */
public class RecipeDao extends AbstractRecipeDaoImpl {
	private static RecipeDao dao;

	private RecipeDao() {
	}

	public static synchronized RecipeDao getInstance() {
		if (dao == null)
			dao = new RecipeDao();
		return dao;
	}
	
	public List<Recipe> getRecipesByIdUser(int idUser) {
		try {
			List<Recipe> list = new ArrayList<Recipe>();
			String query = "SELECT * FROM " + TABLE + " WHERE idUser = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idUser);
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
	
	public void deleteByRecipe(Recipe recipe) 
	{
		if (recipe.getImage() != null)
			ImageUploader.getInstance().destroy(recipe.getImage());
		deleteById(recipe.getId());
	}
	
	
}

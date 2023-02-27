
package djadi.rabah.recipes.dao.impl;
//Generated 2 mars 2021 12:58:32 by Hibernate Tools 5.2.10.Final

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import djadi.rabah.recipes.dao.DaoConnection;
import djadi.rabah.recipes.dao.abstractimpl.AbstractInstructionIngredientDaoImpl;
import djadi.rabah.recipes.pojo.InstructionIngredient;

/**
 * Auto Dao object for domain model class Ingredient.
 * 
 * @see djadi.rabah.recipes.dao.impl.Ingredient
 * @author Rabah DJADI
 */
public class InstructionIngredientDao extends AbstractInstructionIngredientDaoImpl {
	private static InstructionIngredientDao dao;

	private InstructionIngredientDao() {
	}

	public static synchronized InstructionIngredientDao getInstance() {
		if (dao == null)
			dao = new InstructionIngredientDao();
		return dao;
	}
	
	public List<InstructionIngredient> getByIdInstruction(int idInstruction) {
		try {
			List<InstructionIngredient> list = new ArrayList<InstructionIngredient>();
			String query = "SELECT * FROM " + TABLE + " WHERE idInstruction = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idInstruction);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				InstructionIngredient pojo = new InstructionIngredient();
				pojo.setId(resultSet.getInt("id"));
				pojo.setIdInstruction(resultSet.getInt("idInstruction"));
				pojo.setIngredient(resultSet.getString("ingredient"));
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

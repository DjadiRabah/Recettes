package djadi.rabah.recipes.dao.abstractimpl;
// Generated 16 mars 2021 16:17:02 by Hibernate Tools 5.2.10.Final

import djadi.rabah.recipes.pojo.InstructionIngredient;
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
 * Auto Dao object for domain model class InstructionIngredient.
 * @see djadi.rabah.recipes.dao.abstractimpl.InstructionIngredient
 * @author Rabah DJADI
 */
public abstract class AbstractInstructionIngredientDaoImpl implements Dao<InstructionIngredient> {

	protected final String TABLE = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "InstructionIngredient");

	@Override
	public void deleteById(int idInstructionIngredient) {
		try {
			String query = "DELETE FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query);
			statement.setInt(1, idInstructionIngredient);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	* Update the database's InstructionIngredient table
	* @see Dao#update()
	*/
	@Override
	public void update(InstructionIngredient pojo) {
		try {

			String query = "UPDATE " + TABLE + " SET idInstruction = ?,ingredient = ? WHERE id = ?";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query);
			preparedStatement.setInt(1, pojo.getIdInstruction());
			preparedStatement.setString(2, pojo.getIngredient());
			preparedStatement.setInt(3, pojo.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(InstructionIngredient pojo) {
		try {

			String query = "INSERT INTO " + TABLE + " (id,idInstruction,ingredient) VALUES (?,?,?);";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, pojo.getId());
			preparedStatement.setInt(2, pojo.getIdInstruction());
			preparedStatement.setString(3, pojo.getIngredient());
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
	public List<InstructionIngredient> getAll() {
		try {
			List<InstructionIngredient> list = new ArrayList<InstructionIngredient>();
			String query = "SELECT * FROM " + TABLE;
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
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

	@Override
	public InstructionIngredient getById(int idInstructionIngredient) {
		try {
			InstructionIngredient pojo = new InstructionIngredient();
			String query = "SELECT * FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idInstructionIngredient);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				pojo.setId(resultSet.getInt("id"));
				pojo.setIdInstruction(resultSet.getInt("idInstruction"));
				pojo.setIngredient(resultSet.getString("ingredient"));
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

package djadi.rabah.recipes.dao.abstractimpl;
// Generated 16 mars 2021 17:11:13 by Hibernate Tools 5.2.10.Final

import djadi.rabah.recipes.pojo.Instruction;
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
 * Auto Dao object for domain model class Instruction.
 * @see djadi.rabah.recipes.dao.abstractimpl.Instruction
 * @author Rabah DJADI
 */
public abstract class AbstractInstructionDaoImpl implements Dao<Instruction> {

	protected final String TABLE = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "Instruction");

	@Override
	public void deleteById(int idInstruction) {
		try {
			String query = "DELETE FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query);
			statement.setInt(1, idInstruction);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	* Update the database's Instruction table
	* @see Dao#update()
	*/
	@Override
	public void update(Instruction pojo) {
		try {

			String query = "UPDATE " + TABLE + " SET idRecipe = ?,rank = ?,instruction = ? WHERE id = ?";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query);
			preparedStatement.setInt(1, pojo.getIdRecipe());
			preparedStatement.setInt(2, pojo.getRank());
			preparedStatement.setString(3, pojo.getInstruction());
			preparedStatement.setInt(4, pojo.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(Instruction pojo) {
		try {

			String query = "INSERT INTO " + TABLE + " (id,idRecipe,rank,instruction) VALUES (?,?,?,?);";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, pojo.getId());
			preparedStatement.setInt(2, pojo.getIdRecipe());
			preparedStatement.setInt(3, pojo.getRank());
			preparedStatement.setString(4, pojo.getInstruction());
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
	public List<Instruction> getAll() {
		try {
			List<Instruction> list = new ArrayList<Instruction>();
			String query = "SELECT * FROM " + TABLE;
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Instruction pojo = new Instruction();
				pojo.setId(resultSet.getInt("id"));
				pojo.setIdRecipe(resultSet.getInt("idRecipe"));
				pojo.setRank(resultSet.getInt("rank"));
				pojo.setInstruction(resultSet.getString("instruction"));
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
	public Instruction getById(int idInstruction) {
		try {
			Instruction pojo = new Instruction();
			String query = "SELECT * FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idInstruction);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				pojo.setId(resultSet.getInt("id"));
				pojo.setIdRecipe(resultSet.getInt("idRecipe"));
				pojo.setRank(resultSet.getInt("rank"));
				pojo.setInstruction(resultSet.getString("instruction"));
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

package djadi.rabah.recipes.dao.impl;
//Generated 2 mars 2021 12:58:32 by Hibernate Tools 5.2.10.Final

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import djadi.rabah.recipes.dao.DaoConnection;
import djadi.rabah.recipes.dao.abstractimpl.AbstractInstructionDaoImpl;
import djadi.rabah.recipes.pojo.Instruction;

/**
* Auto Dao object for domain model class Ingredient.
* @see djadi.rabah.recipes.dao.impl.Ingredient
* @author Rabah DJADI
*/
public class InstructionDao extends AbstractInstructionDaoImpl {
	private static InstructionDao dao;

	private InstructionDao() {
	}

	public static synchronized InstructionDao getInstance() {
		if (dao == null)
			dao = new InstructionDao();
		return dao;
	}
	
	public List<Instruction> getInstructionsByIdRecipe(int idRecipe) {
		try {
			List<Instruction> list = new ArrayList<Instruction>();
			String query = "SELECT * FROM " + TABLE + " WHERE idRecipe = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idRecipe);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Instruction pojo = new Instruction();
				pojo.setId(resultSet.getInt("id"));
				pojo.setInstruction(resultSet.getString("instruction"));
				pojo.setIdRecipe(resultSet.getInt("idRecipe"));
				pojo.setRank(resultSet.getInt("rank"));
				list.add(pojo);
			}

			resultSet.close();
			statement.close();

			list.sort( new Comparator<Instruction>() {
				@Override
				public int compare(Instruction o1, Instruction o2) 
				{
					return Integer.compare(o1.getRank(), o2.getRank());
				}
			});
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public int getRankMax(int idRecipe) {
		try {
			int rankMax = 0;
			String query = "SELECT MAX(rank) as max_rank FROM " + TABLE + " WHERE idRecipe = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idRecipe);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				rankMax = resultSet.getInt("max_rank");
			}

			resultSet.close();
			statement.close();

			return rankMax;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}
}
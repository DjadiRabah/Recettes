package djadi.rabah.recipes.dao;

import java.sql.PreparedStatement;
import java.util.List;

public interface Dao<Pojo>
{
	/**
  	* Update a tuple in the database
  	* by calling the PreparedStatement's executeUpdate method
  	* @param pojo the new tuple
  	* @see PreparedStatement#executeUpdate()
  	*/
	public void update(Pojo pojo);
	
	public int insert(Pojo pojo);
	public Pojo getById(int id);
	public List<Pojo> getAll();

	public void deleteById(int id);
}

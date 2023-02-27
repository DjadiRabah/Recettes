package djadi.rabah.recipes.pojo;
// Generated 16 mars 2021 17:11:07 by Hibernate Tools 5.2.10.Final

public class SubRecipe implements Pojo {
	private int id;
	private int idRecette1;
	private int idRecette2;

	/**
	 * Default empty constructor
	 */
	public SubRecipe() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdRecette1() {
		return this.idRecette1;
	}

	public void setIdRecette1(int idRecette1) {
		this.idRecette1 = idRecette1;
	}

	public int getIdRecette2() {
		return this.idRecette2;
	}

	public void setIdRecette2(int idRecette2) {
		this.idRecette2 = idRecette2;
	}
}

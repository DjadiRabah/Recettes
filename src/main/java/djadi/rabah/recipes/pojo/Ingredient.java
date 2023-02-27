package djadi.rabah.recipes.pojo;
// Generated 16 mars 2021 17:11:07 by Hibernate Tools 5.2.10.Final

public class Ingredient implements Pojo {
	private int id;
	private int idAliment;
	private int idMeasure;
	private int idRecipe;
	private float amount;

	/**
	 * Default empty constructor
	 */
	public Ingredient() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdAliment() {
		return this.idAliment;
	}

	public void setIdAliment(int idAliment) {
		this.idAliment = idAliment;
	}

	public int getIdMeasure() {
		return this.idMeasure;
	}

	public void setIdMeasure(int idMeasure) {
		this.idMeasure = idMeasure;
	}

	public int getIdRecipe() {
		return this.idRecipe;
	}

	public void setIdRecipe(int idRecipe) {
		this.idRecipe = idRecipe;
	}

	public float getAmount() {
		return this.amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
}

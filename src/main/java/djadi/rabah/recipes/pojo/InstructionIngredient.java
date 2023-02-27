package djadi.rabah.recipes.pojo;
// Generated 16 mars 2021 16:16:47 by Hibernate Tools 5.2.10.Final

public class InstructionIngredient implements Pojo {
	private int id;
	private int idInstruction;
	private String ingredient;

	/**
	 * Default empty constructor
	 */
	public InstructionIngredient() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdInstruction() {
		return this.idInstruction;
	}

	public void setIdInstruction(int idInstruction) {
		this.idInstruction = idInstruction;
	}

	public String getIngredient() {
		return this.ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}
}

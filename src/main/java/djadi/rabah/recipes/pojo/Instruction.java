package djadi.rabah.recipes.pojo;
// Generated 16 mars 2021 17:11:07 by Hibernate Tools 5.2.10.Final

public class Instruction implements Pojo {
	private int id;
	private int idRecipe;
	private int rank;
	private String instruction;

	/**
	 * Default empty constructor
	 */
	public Instruction() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdRecipe() {
		return this.idRecipe;
	}

	public void setIdRecipe(int idRecipe) {
		this.idRecipe = idRecipe;
	}

	public int getRank() {
		return this.rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getInstruction() {
		return this.instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
}

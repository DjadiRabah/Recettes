package djadi.rabah.recipes.pojo;
// Generated 16 mars 2021 17:11:07 by Hibernate Tools 5.2.10.Final

public class Aliment implements Pojo {
	private int id;
	private String aliment;

	/**
	 * Default empty constructor
	 */
	public Aliment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAliment() {
		return this.aliment;
	}

	public void setAliment(String aliment) {
		this.aliment = aliment;
	}
}

package djadi.rabah.recipes.pojo;
// Generated 16 mars 2021 17:11:07 by Hibernate Tools 5.2.10.Final

public class Measure implements Pojo {
	private int id;
	private String measure;

	/**
	 * Default empty constructor
	 */
	public Measure() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMeasure() {
		return this.measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}
}

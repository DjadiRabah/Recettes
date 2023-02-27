package djadi.rabah.recipes.pojo;
// Generated 16 mars 2021 17:11:07 by Hibernate Tools 5.2.10.Final

public class Recipe implements Pojo {
	private int id;
	private int idUser;
	private String name;
	private String image;

	/**
	 * Default empty constructor
	 */
	public Recipe() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}

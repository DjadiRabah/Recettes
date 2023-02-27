package djadi.rabah.recipes.pojo;
// Generated 16 mars 2021 17:11:07 by Hibernate Tools 5.2.10.Final

public class Settings implements Pojo {
	private int id;
	private int idUser;
	private String theme;

	/**
	 * Default empty constructor
	 */
	public Settings() {
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

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
}

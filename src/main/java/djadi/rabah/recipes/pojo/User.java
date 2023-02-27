package djadi.rabah.recipes.pojo;
// Generated 16 mars 2021 17:11:07 by Hibernate Tools 5.2.10.Final

public class User implements Pojo {
	private int id;
	private boolean admin;
	private String login;
	private String hash;
	private String salt;

	/**
	 * Default empty constructor
	 */
	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getAdmin() {
		return this.admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getHash() {
		return this.hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}

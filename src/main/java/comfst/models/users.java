package comfst.models;

import java.time.LocalDateTime;

public class users {
	private int id;
	private LocalDateTime created_at;
	private String email;
	private String password;
	private String username;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "users [id=" + id + ", created_at=" + created_at + ", email=" + email + ", password=" + password
				+ ", username=" + username + "]";
	}

}

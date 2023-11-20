package comfst.models;

import java.time.LocalDateTime;

public class emails {

	private int id;
	private LocalDateTime created_at;
	private String address;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "emails [id=" + id + ", created_at=" + created_at + ", address=" + address + "]";
	}
}

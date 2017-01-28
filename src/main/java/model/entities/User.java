package model.entities;

import model.entities.enums.UserRoles;

/**
 * Created by Dyvak on 16.12.2016.
 */
public class User {

	private int id;
	private String name;
	private String surname;
	private String email;
	private Integer passwordHash;
	private UserRoles role;

	public User() {
	}

	public User(int id, String name, String surname, String email, int passwordHash, UserRoles role) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.passwordHash = passwordHash;
		this.role = role;
	}

	public static class Builder{

		User instance = new User();

		public Builder setId(int id) {
			instance.id = id;
			return this;
		}

		public Builder setName(String name) {
			instance.name = name;
			return this;
		}

		public Builder setSurname(String surname) {
			instance.surname = surname;
			return this;
		}

		public Builder setEmail(String email) {
			instance.email = email;
			return this;
		}

		public Builder setPasswordHash(String password) {
			instance.passwordHash = calcPasswordHash(password);
			return this;
		}

		public Builder setPasswordInt(int password) {
			instance.passwordHash = password;
			return this;
		}

		public Builder setRole(UserRoles role) {
			instance.role = role;
			return this;
		}

		public User build() {
			return instance;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(Integer passwordHash) {
		this.passwordHash = passwordHash;
	}

	public UserRoles getRole() {
		return role;
	}

	public void setRole(UserRoles role) {
		this.role = role;
	}

    public static Integer calcPasswordHash(String password) {
		Integer seed = 131;
		Integer hash = 12;
		hash = seed * hash + password.hashCode();
		return hash;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (id != user.id) return false;
		if (email != null ? !email.equals(user.email) : user.email != null) return false;
		if (name != null ? !name.equals(user.name) : user.name != null) return false;
		if (passwordHash != null ? !passwordHash.equals(user.passwordHash) : user.passwordHash != null) return false;
		if (role != user.role) return false;
		if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (surname != null ? surname.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
		result = 31 * result + (role != null ? role.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", email='" + email + '\'' +
				", passwordHash=" + passwordHash +
				", role=" + role +
				'}';
	}
}

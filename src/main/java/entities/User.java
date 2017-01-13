package entities;

import entities.enums.UserRoles;

/**
 * Created by Dyvak on 16.12.2016.
 */
public class User {

	private Integer id;
	private String login;
	private String name;
	private String surname;
	private Integer passwordHash;
	private UserRoles role;

	public User() {}

	public User(String login, String name, String surname, String password,
				UserRoles role) {
		super();
		this.login = login;
		this.name = name;
		this.surname = surname;
		this.passwordHash = calcPasswordHash(password);
		this.role = role;
	}

    public User(Integer id, String login, String name, String surname,
				Integer passwordHash, UserRoles role) {
		super();
		this.id = id;
		this.name = name;
		this.login = login;
		this.surname = surname;
		this.passwordHash = passwordHash;
		this.role = role;
	}

    public User(Integer id, String login, String name, String surname,
				String password, UserRoles role) {
		super();
		this.id = id;
		this.name = name;
		this.login = login;
		this.surname = surname;
		this.passwordHash = calcPasswordHash(password);
		this.role = role;
	}

	public static class Builder{
		User instance = new User();

		public Builder setLogin(String login) {
			instance.login = login;
			return this;
		}

		public Builder setName(String name , boolean isNull) {
			if(!isNull) {
				instance.name = name;
			}else{
				instance.name = null;
			}
			return this;
		}

		public Builder setSurname(String surname) {
			instance.surname = surname;
			return this;
		}

		public Builder setPasswordHash(String password) {
			instance.passwordHash = calcPasswordHash(password);
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((passwordHash == null) ? 0 : passwordHash.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (passwordHash == null) {
			if (other.passwordHash != null)
				return false;
		} else if (!passwordHash.equals(other.passwordHash))
			return false;
		if (role != other.role)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", login='" + login + '\'' +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", passwordHash=" + passwordHash +
				", role=" + role +
				'}';
	}
}

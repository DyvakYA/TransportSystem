package model.entities;

public class Route {

	private Integer id;
	private String name;

    public Route() {
		super();
	}

    public Route(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Route(String name) {
		this.name = name;
	}

	public static class Builder{
		Route instance = new Route();

		public Builder setName(String name) {
			instance.name = name;
			return this;
		}

		public Route build() {
			return instance;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Route [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Route other = (Route) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}

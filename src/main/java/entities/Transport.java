package entities;

import entities.enums.TransportType;

/**
 * Created by Dyvak on 16.12.2016.
 */
public class Transport {

	private Integer id;
	private TransportType type;
	private String model;
	private String number;

    public Transport() {
	}

	public Transport(TransportType type, String model, String number) {
		this.type = type;
		this.model = model;
		this.number = number;
	}

	public Transport(Integer id, TransportType type,
					 String model, String number) {
		this.type = type;
		this.id = id;
		this.model = model;
		this.number = number;
	}

	public static class Builder{
		Transport instance = new Transport();

		public Builder setType(TransportType type) {
			instance.type = type;
			return this;
		}

		public Builder setModel(String model) {
			instance.model = model;
			return this;
		}

		public Builder setNumber(String number) {
			instance.number = number;
			return this;
		}

		public Transport build() {
			return instance;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public TransportType getType() {
		return type;
	}

	public void setType(TransportType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Transport other = (Transport) obj;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transport{" +
				"id=" + id +
				", type=" + type +
				", model='" + model + '\'' +
				", number='" + number + '\'' +
				'}';
	}
}

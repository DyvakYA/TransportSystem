package model.entities.transports;

import model.entities.enums.TransportType;

/**
 * Created by Dyvak on 16.12.2016.
 */
public class UrbanTransport implements Transport {

	private Integer id;
	private TransportType type;
	private String model;
	private String number;

    public UrbanTransport() {
	}

    public UrbanTransport(Integer id, TransportType type,
						  String model, String number) {
		this.type = type;
		this.id = id;
		this.model = model;
		this.number = number;
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

	public static class Builder{
		UrbanTransport instance = new UrbanTransport();

		public Builder setName(String number) {
			instance.number = number;
			return this;
		}

	public Builder setNumber(String number , boolean isNull) {
		if(!isNull) {
			instance.number = number;
		}else{
			instance.number = null;
		}
		return this;
	}

	public Builder setId(Integer id) {
		instance.id = id;
		return this;
	}

	public Transport build() {
		return instance;
	}
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
		UrbanTransport other = (UrbanTransport) obj;
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
		return "UrbanTransport{" +
				"id=" + id +
				", type=" + type +
				", model='" + model + '\'' +
				", number=" + number +
				'}';
	}
}

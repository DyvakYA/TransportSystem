package model.entities;

import java.util.Objects;

public class Plan {

	private Integer id;
	private Integer routeId;
	private Integer transportId;

	public Plan() {
	}

    public Plan(Integer routeId, Integer transportId) {
		this.routeId = routeId;
		this.transportId = transportId;
	}

    public Plan(Integer id, Integer routeId, Integer transportId) {
		super();
		this.id = id;
		this.routeId = routeId;
		this.transportId = transportId;
	}



	public static class Builder{
		Plan instance = new Plan();

		public Builder setId(Integer id) {
			instance.id = id;
			return this;
		}

		public Builder setRouteId(Integer routeId) {
			instance.routeId = routeId;
			return this;
		}

		public Builder setTransportId(Integer transportId) {
			instance.transportId = transportId;
			return this;
		}

		public Plan build() {
			return instance;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	public Integer getTransportId() {
		return transportId;
	}

	public void setTransportId(Integer transportId) {
		this.transportId = transportId;
	}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.routeId);
        hash = 13 * hash + Objects.hashCode(this.transportId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Plan other = (Plan) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.routeId, other.routeId)) {
            return false;
        }
        if (!Objects.equals(this.transportId, other.transportId)) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", routeId=" + routeId + ", transportId="
				+ transportId + "]";
	}
}

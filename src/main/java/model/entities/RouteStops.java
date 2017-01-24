package model.entities;

public class RouteStops {

	private Integer id;
	private Integer routeId;
	private Integer stopId;
	private Integer number;

    public RouteStops() {
		super();
	}

    public RouteStops(Integer id, Integer routeId, Integer stopId,
					  Integer number) {
		super();
		this.id = id;
		this.routeId = routeId;
		this.stopId = stopId;
		this.number = number;
	}

	public static class Builder{
		RouteStops instance = new RouteStops();

		public Builder setId(int id) {
			instance.id = id;
			return this;
		}

		public Builder setRouteId(int routeId) {
			instance.routeId = routeId;
			return this;
		}

		public Builder setStopId(int stopId) {
			instance.stopId = stopId;
			return this;
		}

		public Builder setNumber(int number) {
			instance.number = number;
			return this;
		}

		public RouteStops build() {
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

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public Integer getStopId() {
		return stopId;
	}

	public void setStopId(Integer stopId) {
		this.stopId = stopId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "RouteStop [id=" + id + ", routeId=" + routeId + ", stopId="
				+ stopId + ", number=" + number + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((routeId == null) ? 0 : routeId.hashCode());
		result = prime * result + ((stopId == null) ? 0 : stopId.hashCode());
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
		RouteStops other = (RouteStops) obj;
		if (routeId == null) {
			if (other.routeId != null)
				return false;
		} else if (!routeId.equals(other.routeId))
			return false;
		if (stopId == null) {
			if (other.stopId != null)
				return false;
		} else if (!stopId.equals(other.stopId))
			return false;
		return true;
	}
}

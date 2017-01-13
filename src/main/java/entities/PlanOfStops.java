package entities;

public class PlanOfStops {

	private Integer id;
	private Integer stopId;
	private Integer planId;
	private String arriveTime;
	private String leaveTime;
	
    public PlanOfStops() {
		super();
	}

    public PlanOfStops(Integer id, Integer stopId, Integer planId,
					   String arriveTime, String leaveTime) {
		super();
		this.id = id;
		this.stopId = stopId;
		this.planId = planId;
		this.arriveTime = arriveTime;
		this.leaveTime = leaveTime;
	}

	public static class Builder{
		PlanOfStops instance = new PlanOfStops();

		public Builder setStopId(Integer stopId) {
			instance.stopId = stopId;
			return this;
		}

		public Builder setPlanId(Integer planId) {
			instance.planId = planId;
			return this;
		}

		public Builder setArriveTime(String arriveTime) {
			instance.arriveTime = arriveTime;
			return this;
		}

		public Builder setLeaveTime(String leaveTime) {
			instance.leaveTime = leaveTime;
			return this;
		}

		public PlanOfStops build() {
			return instance;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStopId() {
		return stopId;
	}

	public void setStopId(Integer stopId) {
		this.stopId = stopId;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((arriveTime == null) ? 0 : arriveTime.hashCode());
		result = prime * result
				+ ((leaveTime == null) ? 0 : leaveTime.hashCode());
		result = prime * result
				+ ((planId == null) ? 0 : planId.hashCode());
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
		PlanOfStops other = (PlanOfStops) obj;
		if (arriveTime == null) {
			if (other.arriveTime != null)
				return false;
		} else if (!arriveTime.equals(other.arriveTime))
			return false;
		if (leaveTime == null) {
			if (other.leaveTime != null)
				return false;
		} else if (!leaveTime.equals(other.leaveTime))
			return false;
		if (planId == null) {
			if (other.planId != null)
				return false;
		} else if (!planId.equals(other.planId))
			return false;
		if (stopId == null) {
			if (other.stopId != null)
				return false;
		} else if (!stopId.equals(other.stopId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PlanOfStops [id=" + id + ", stopId=" + stopId
				+ ", planId=" + planId + ", arriveTime=" + arriveTime
				+ ", leaveTime=" + leaveTime + "]";
	}
}

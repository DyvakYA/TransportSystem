package model.entities;

/**
 *
 * @author KIRIL
 */
public class PlanOfStops {

	private Integer id;
	private Integer stopId;
	private Integer scheduleId;
	private String arriveTime;
	private String leaveTime;
	
    /**
     *
     */
    public PlanOfStops() {
		super();
	}

    /**
     *
     * @param id
     * @param stopId
     * @param scheduleId
     * @param arriveTime
     * @param leaveTime
     */
    public PlanOfStops(Integer id, Integer stopId, Integer scheduleId,
					   String arriveTime, String leaveTime) {
		super();
		this.id = id;
		this.stopId = stopId;
		this.scheduleId = scheduleId;
		this.arriveTime = arriveTime;
		this.leaveTime = leaveTime;
	}



	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}



	/**
	 * @return the stopId
	 */
	public Integer getStopId() {
		return stopId;
	}



	/**
	 * @param stopId the stopId to set
	 */
	public void setStopId(Integer stopId) {
		this.stopId = stopId;
	}



	/**
	 * @return the scheduleId
	 */
	public Integer getScheduleId() {
		return scheduleId;
	}



	/**
	 * @param scheduleId the scheduleId to set
	 */
	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}



	/**
	 * @return the arriveTime
	 */
	public String getArriveTime() {
		return arriveTime;
	}



	/**
	 * @param arriveTime the arriveTime to set
	 */
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}



	/**
	 * @return the leaveTime
	 */
	public String getLeaveTime() {
		return leaveTime;
	}



	/**
	 * @param leaveTime the leaveTime to set
	 */
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((arriveTime == null) ? 0 : arriveTime.hashCode());
		result = prime * result
				+ ((leaveTime == null) ? 0 : leaveTime.hashCode());
		result = prime * result
				+ ((scheduleId == null) ? 0 : scheduleId.hashCode());
		result = prime * result + ((stopId == null) ? 0 : stopId.hashCode());
		return result;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
		if (scheduleId == null) {
			if (other.scheduleId != null)
				return false;
		} else if (!scheduleId.equals(other.scheduleId))
			return false;
		if (stopId == null) {
			if (other.stopId != null)
				return false;
		} else if (!stopId.equals(other.stopId))
			return false;
		return true;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ScheduleOfStop [id=" + id + ", stopId=" + stopId
				+ ", scheduleId=" + scheduleId + ", arriveTime=" + arriveTime
				+ ", leaveTime=" + leaveTime + "]";
	}
	
	
	
	
}

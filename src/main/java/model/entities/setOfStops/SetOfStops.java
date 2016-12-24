package model.entities.setOfStops;

import model.entities.enums.Stops;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KIRIL
 */
public class SetOfStops implements SetsForRoute{

	private Integer id;
	private String name;
	private List<Stops> setOfStops = new ArrayList<>();

    /**
     *
     */
    public SetOfStops() {
		super();
	}

    /**
     *
     * @param id
     * @param name
     */
    public SetOfStops(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public void addStopInSetOfStops (Stops city){
		setOfStops.add(city);
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		SetOfStops other = (SetOfStops) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SetOfStops{" +
				"id=" + id +
				", name='" + name + '\'' +
				", setOfStops=" + setOfStops +
				'}';
	}
}

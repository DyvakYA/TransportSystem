package entities.enums;

public enum TransportType {

    /**
     *
     */
    BUS("Bus", 1),

    /**
     *
     */
    TROLLEYBUS("Trolleybus", 2),

    /**
     *
     */
    TRAM("Tram", 3);

	private String name;
	private int index;

	private TransportType(String name, int index) {
		this.name = name;
		this.index = index;
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

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

}

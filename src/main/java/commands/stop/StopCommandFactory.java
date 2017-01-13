package commands.stop;

public class StopCommandFactory {

    /**
     *
     */
    public static final String FIND_ALL_STOPS_ON_ROUTE_COMMAND = "findAllStopsOnRoute";

    /**
     *
     */
    public static final String UPDATE_STOP_COMMAND = "updateStop";

    /**
     *
     */
    public static final String DELETE_STOP_COMMAND = "deleteStop";

    /**
     *
     */
    public static final String FIND_STOP_BY_ID_COMMAND = "findStop";

    /**
     *
     */
    public static final String FIND_ALL_STOP_COMMAND = "findAllStop";

    /**
     *
     */
    public static final String CREATE_STOP_COMMAND = "createStop";
	
	
	private static StopCommandFactory instance = new StopCommandFactory();

	private StopCommandFactory() {
		super();
	}

    /**
     *
     * @return
     */
    public static StopCommandFactory getInstance() {
		return instance;
	}

    /**
     *
     * @param name
     * @return
     */
    public StopCommand getStopCommand(String name) {

		if (name.contains(FIND_ALL_STOP_COMMAND)) {
			return new FindAllStopCommand();
		}

		if (name.contains(FIND_STOP_BY_ID_COMMAND)) {
			return new FindStopCommand();
		}

		if (name.contains(FIND_ALL_STOPS_ON_ROUTE_COMMAND)) {
			return new FindAllStopsOnRouteCommand();
		}

		if (name.contains(CREATE_STOP_COMMAND)) {
			return new CreateStopCommand();
		}

		if (name.contains(UPDATE_STOP_COMMAND)) {
			return new UpdateStopCommand();
		}

		if (name.contains(DELETE_STOP_COMMAND)) {
			return new DeleteStopCommand();
		}

		return null;
	}
}

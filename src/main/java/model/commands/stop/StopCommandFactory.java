package ua.kpi.epam.transport.commands.stop;

/**
 *
 * @author KIRIL
 */
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
    public static StopCommandFactory getInstanse() {
		return instance;
	}

    /**
     *
     * @param name
     * @return
     */
    public StopCommand getStopCommand(String name) {
		switch (name) {
		case CREATE_STOP_COMMAND: {
			return new CreateStopCommand();
		}

		case FIND_ALL_STOP_COMMAND: {
			return new FindAllStopCommand();
		}

		case FIND_STOP_BY_ID_COMMAND: {
			return new FindStopCommand();
		}

		case DELETE_STOP_COMMAND: {
			return new DeleteStopCommand();
		}
		case UPDATE_STOP_COMMAND: {
			return new UpdateStopCommand();
		}
		case FIND_ALL_STOPS_ON_ROUTE_COMMAND: {
			return new FindAllStopsOnRouteCommand();
		}
		default:
			return null;
		}
	}

}

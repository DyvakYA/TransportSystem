package ua.kpi.epam.transport.commands.transport;

/**
 *
 * @author KIRIL
 */
public class TransportCommandFactory {

    /**
     *
     */
    public static final String UPDATE_TRANSPORT_COMMAND = "updateTransport";

    /**
     *
     */
    public static final String DELETE_TRANSPORT_COMMAND = "deleteTransport";

    /**
     *
     */
    public static final String FIND_TRANSPORT_BY_ID_COMMAND = "findTransport";

    /**
     *
     */
    public static final String FIND_ALL_TRANSPORT_COMMAND = "findAllTransport";

    /**
     *
     */
    public static final String FIND_TRANSPORT_FOR_ROUTE_SCHEDULES = "findTransportForRouteSchedules";

    /**
     *
     */
    public static final String CREATE_TRANSPORT_COMMAND = "addTransport";

	private static TransportCommandFactory instance = new TransportCommandFactory();

	private TransportCommandFactory() {
		super();
	}

    /**
     *
     * @return
     */
    public static TransportCommandFactory getInstanse() {
		return instance;
	}

    /**
     *
     * @param name
     * @return
     */
    public TransportCommand getTransportCommand(String name) {
		switch (name) {
		case CREATE_TRANSPORT_COMMAND: {
			return new CreateTransportCommand();
		}

		case FIND_ALL_TRANSPORT_COMMAND: {
			return new FindAllTransportCommand();
		}

		case FIND_TRANSPORT_BY_ID_COMMAND: {
			return new FindTransportCommand();
		}

		case DELETE_TRANSPORT_COMMAND: {
			return new DeleteTransportCommand();
		}
		case UPDATE_TRANSPORT_COMMAND: {
			return new UpdateTransportCommand();
		}
		case FIND_TRANSPORT_FOR_ROUTE_SCHEDULES: {
			return new FindTransportForRouteSchedules();
		}
		default:
			return null;
		}
	}
}

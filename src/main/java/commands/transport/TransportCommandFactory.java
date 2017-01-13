package commands.transport;

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
    public static TransportCommandFactory getInstance() {
		return instance;
	}

    /**
     *
     * @param name
     * @return
     */
    public TransportCommand getTransportCommand(String name) {

		if (name.contains(FIND_ALL_TRANSPORT_COMMAND)) {
			return new FindAllTransportCommand();
		}

		if (name.contains(FIND_TRANSPORT_BY_ID_COMMAND)) {
			return new FindTransportCommand();
		}

		if (name.contains(CREATE_TRANSPORT_COMMAND)) {
			return new CreateTransportCommand();
		}

		if (name.contains(UPDATE_TRANSPORT_COMMAND)) {
			return new UpdateTransportCommand();
		}

		if (name.contains(DELETE_TRANSPORT_COMMAND)) {
			return new DeleteTransportCommand();
		}

		return null;
	}
}

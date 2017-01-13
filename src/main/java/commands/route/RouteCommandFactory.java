package commands.route;

public class RouteCommandFactory {

	/**
	 *
	 */
	public static final String FIND_ROUTES_BY_STOPS_COMMAND = "findRouteByStops";

	/**
	 *
	 */
	public static final String FIND_ALL_ROUTE_AND_STOPS_COMMAND = "findAllRouteAndStops";

	/**
	 *
	 */
	public static final String FIND_ALL_STOP_ON_ROUTE_COMMAND = "findAllStopOnRoute";

	/**
	 *
	 */
	public static final String UPDATE_ROUTE_COMMAND = "updateRoute";

	/**
	 *
	 */
	public static final String DELETE_ROUTE_COMMAND = "deleteRoute";

	/**
	 *
	 */
	public static final String FIND_ROUTE_BY_ID_COMMAND = "findRoute";

	/**
	 *
	 */
	public static final String FIND_ALL_ROUTE_COMMAND = "findAllRoute";

	/**
	 *
	 */
	public static final String CREATE_ROUTE_COMMAND = "createRoute";

	/**
	 *
	 */
	public static final String ADD_STOP_ON_ROOT_COMMAND = "addStopOnRoute";

	/**
	 *
	 */
	public static final String REMOVE_STOP_FROM_ROOT_COMMAND = "removeStopFromRoute";

	private static final RouteCommandFactory instance = new RouteCommandFactory();

	private RouteCommandFactory() {
		super();
	}

	/**
	 * @return
	 */
	public static RouteCommandFactory getInstance() {
		return instance;
	}

	/**
	 * @param name
	 * @return
	 */
	public RouteCommand getRouteCommand(String name) {

		if (name.contains(FIND_ROUTES_BY_STOPS_COMMAND)) {
			return new FindRoutesByStopsCommand();
		}

		if (name.contains(FIND_ALL_ROUTE_AND_STOPS_COMMAND)) {
			return new FindAllRouteAndStopsCommand();
		}

		if (name.contains(FIND_ALL_ROUTE_COMMAND)) {
			return new FindAllRoutesCommand();
		}

		if (name.contains(FIND_ROUTE_BY_ID_COMMAND)) {
			return new FindRouteById();
		}

		if (name.contains(FIND_ALL_STOP_ON_ROUTE_COMMAND)) {
			return new FindAllStopOnRouteCommand();
		}

		if (name.contains(CREATE_ROUTE_COMMAND)) {
			return new CreateRouteCommand();
		}

		if (name.contains(UPDATE_ROUTE_COMMAND)) {
			return new UpdateRouteCommand();
		}

		if (name.contains(DELETE_ROUTE_COMMAND)) {
			return new DeleteRouteCommand();
		}

		if (name.contains(ADD_STOP_ON_ROOT_COMMAND)) {
			return new AddStopOnRouteCommand();
		}

		if (name.contains(REMOVE_STOP_FROM_ROOT_COMMAND)) {
			return new RemoveStopFromRouteCommand();
		}

		return null;
	}
}

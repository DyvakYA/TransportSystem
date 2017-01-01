package ua.kpi.epam.transport.commands.schedule_of_stop;

/**
 *
 * @author KIRIL
 */
public class ScheduleOfStopCommandFactory {

    /**
     *
     */
    public static final String FIND_SCHEDULE_OF_STOPS_FOR_ROUTE_COMMAND = "findScheduleOfStopsForRoute";
	
	private static ScheduleOfStopCommandFactory instance = new ScheduleOfStopCommandFactory();

	private ScheduleOfStopCommandFactory() {
		super();
	}

    /**
     *
     * @return
     */
    public static ScheduleOfStopCommandFactory getInstanse() {
		return instance;
	}

    /**
     *
     * @param name
     * @return
     */
    public ScheduleOfStopCommand getScheduleCommand(String name) {
		switch (name) {

		case FIND_SCHEDULE_OF_STOPS_FOR_ROUTE_COMMAND: {
			return new FindScheduleOfStopsForRoute();
		}
		default: {
			return null;
		}
		}
	}
}

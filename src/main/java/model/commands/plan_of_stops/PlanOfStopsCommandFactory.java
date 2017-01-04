package ua.kpi.epam.transport.commands.schedule_of_stop;

/**
 *
 * @author KIRIL
 */
public class PlanOfStopsCommandFactory {

    /**
     *
     */
    public static final String FIND_SCHEDULE_OF_STOPS_FOR_ROUTE_COMMAND = "findScheduleOfStopsForRoute";
	
	private static ua.kpi.epam.transport.commands.schedule_of_stop.PlanOfStopCommandFactory instance = new ua.kpi.epam.transport.commands.schedule_of_stop.PlanOfStopCommandFactory();

	private PlanOfStopsCommandFactory() {
		super();
	}

    /**
     *
     * @return
     */
    public static ua.kpi.epam.transport.commands.schedule_of_stop.PlanOfStopCommandFactory getInstanse() {
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

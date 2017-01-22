package commands.plan_of_stops;

public class PlanOfStopsCommandFactory {

    /**
     *
     */
    public static final String FIND_SCHEDULE_OF_STOPS_FOR_ROUTE_COMMAND = "findScheduleOfStopsForRoute";
	
	private static PlanOfStopsCommandFactory instance = new PlanOfStopsCommandFactory();

	private PlanOfStopsCommandFactory() {
		super();
	}

    /**
     *
     * @return
     */
    public static PlanOfStopsCommandFactory getInstance() {
		return instance;
	}

    /**
     *
     * @param name
     * @return
     */
    public PlanOfStopsCommand getPlanCommand(String name) {

		if (name.contains(FIND_SCHEDULE_OF_STOPS_FOR_ROUTE_COMMAND)) {
			return new FindPlanOfStopsForRoute();
		}

		return null;
	}
}


package commands.plan;


public class PlanCommandFactory {

    /**
     *
     */
    public static final String FIND_ALL_SCHEDULES_FOR_ROUTE_COMMAND = "findAllSchedulesForRouteToSchedulePage";

    /**
     *
     */
    public static final String DELETE_SCHEDULE_COMMAND = "deleteSchedule";

    /**
     *
     */
    public static final String SHOW_ADD_SCHEDULE_FORM_COMMAND = "showAddScheduleForm";

    /**
     *
     */
    public static final String ADD_SCHEDULE_TO_STOP_COMMAND = "addScheduleToStop";

    /**
     *
     */
    public static final String CREATE_SCHEDULE_COMMAND = "createSchedule";

    /**
     *
     */
    public static final String SET_TRANSPORT_ON_SCHEDULE = "setTransportOnSchedule";

    /**
     *
     */
    public static final String SHOW_TRANSPORT_ON_SCHEDULE = "showTransportOnSchedule";

    /**
     *
     */
    public static final String CREATE_SCHEDULE_USING_INTERVAL_COMMAND = "createScheduleUsingInterval";

    private static final PlanCommandFactory instance = new PlanCommandFactory();

    private PlanCommandFactory() {
        super();
    }

    /**
     * @return
     */
    public static PlanCommandFactory getInstance() {
        return instance;
    }

    /**
     * @param name
     * @return
     */
    public PlanCommand getPlanCommand(String name) {

        if (name.contains(FIND_ALL_SCHEDULES_FOR_ROUTE_COMMAND)) {
            return new FindAllPlansForRoutesCommand();
        }

        if (name.contains(SHOW_ADD_SCHEDULE_FORM_COMMAND)) {
            return new ShowPlanAddFormCommand();
        }

        if (name.contains(ADD_SCHEDULE_TO_STOP_COMMAND)) {
            return new AddPlanForStopsCommand();
        }

        if (name.contains(CREATE_SCHEDULE_COMMAND)) {
            return new CreatePlanCommand();
        }

        if (name.contains(DELETE_SCHEDULE_COMMAND)) {
            return new DeletePlanCommand();
        }

        if (name.contains(SET_TRANSPORT_ON_SCHEDULE)) {
            return new SetTransportOnPlanCommand();
        }

        if (name.contains(SHOW_TRANSPORT_ON_SCHEDULE)) {
            return new ShowTransportOnPlanCommand();
        }

        if (name.contains(CREATE_SCHEDULE_USING_INTERVAL_COMMAND)) {
            return new CreatePlanUsingIntervalCommand();
        }

        return null;
    }
}





package ua.kpi.epam.transport.commands.schedule;

/**
 *
 * @author KIRIL
 */
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

    private static final ua.kpi.epam.transport.commands.schedule.PlanCommandFactory instance = new ua.kpi.epam.transport.commands.schedule.PlanCommandFactory();

    private PlanCommandFactory() {
        super();
    }

    /**
     *
     * @return
     */
    public static ua.kpi.epam.transport.commands.schedule.PlanCommandFactory getInstanse() {
        return instance;
    }

    /**
     *
     * @param name
     * @return
     */
    public ScheduleCommand getScheduleCommand(String name) {
        switch (name) {

            case FIND_ALL_SCHEDULES_FOR_ROUTE_COMMAND: {
                return new FindAllSchedulesforRoutesCommand();
            }
            case DELETE_SCHEDULE_COMMAND: {
                return new DeleteScheduleCommand();
            }
            case SHOW_ADD_SCHEDULE_FORM_COMMAND: {
                return new ShowScheduleAddFormCommand();
            }
            case ADD_SCHEDULE_TO_STOP_COMMAND: {
                return new AddScheduleForStopCommand();
            }
            case CREATE_SCHEDULE_COMMAND: {
                return new CreateScheduleCommand();
            }
            case SET_TRANSPORT_ON_SCHEDULE: {
                return new SetTransportOnScheduleCommand();
            }
            case SHOW_TRANSPORT_ON_SCHEDULE: {
                return new ShowTransportOnScheduleCommand();
            }case CREATE_SCHEDULE_USING_INTERVAL_COMMAND:{
                return new CreateScheduleUsingIntervaCommand();
            }
            default: {
                return null;
            }
        }
    }

}

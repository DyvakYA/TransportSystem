package ua.kpi.epam.transport.commands.schedule_of_stop;

import ua.kpi.epam.transport.commands.Command;

/**
 *
 * @author KIRIL
 */
public interface ScheduleOfStopCommand extends Command {

    /**
     *
     */
    String ROUTE_ID_ATTRIBUTE = "route_id";

    /**
     *
     */
    String DESTINATION_PAGE = "schedule.jsp";

    /**
     *
     */
    String RESULT_MAP_ATTRIBUTE = "scheduleMap";
}

package ua.kpi.epam.transport.commands.schedule_of_stop;

import ua.kpi.epam.transport.commands.Command;

/**
 *
 * @author KIRIL
 */
public interface PlanOfStopsCommand extends Command {

    /**
     *
     */
    String ROUTE_ID_ATTRIBUTE = "route_id";

    /**
     *
     */
    String DESTINATION_PAGE = "plan.jsp";

    /**
     *
     */
    String RESULT_MAP_ATTRIBUTE = "scheduleMap";
}

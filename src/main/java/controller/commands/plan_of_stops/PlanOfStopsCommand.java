package commands.plan_of_stops;

import commands.Command;

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

package ua.kpi.epam.transport.commands.schedule;

import ua.kpi.epam.transport.commands.Command;

/**
 *
 * @author KIRIL
 */
public interface ScheduleCommand extends Command {

    /**
     *
     */
    String RESULT_ROUTES_LIST = "routeList";

    /**
     *
     */
    String RESULT_SCHEDULE_LIST = "scheduleList";

    /**
     *
     */
    String RESULT_ROUTE_ATTRIBUTE = "resultRoute";

    /**
     *
     */
    String RESULT_MAP_ATTRIBUTE = "scheduleMap";

    /**
     *
     */
    String RESULT_ROUTE_MAP_ATTRIBUTE = "routeMap";

    /**
     *
     */
    String RESULT_ATTRIBUTE = "result";

    /**
     *
     */
    String ROUTE_ID_ATTRIBUTE = "routeId";

    /**
     *
     */
    String ADMIN_DESTINATION_PAGE = "admin/schedule.jsp";

    /**
     *
     */
    String SCHEDULE_ID_ATTRIBUTE = "scheduleId";

    /**
     *
     */
    String STOP_ID_ATTRIBUTE = "stopId";

    /**
     *
     */
    String TRANSPORT_ID_ATTRIBUTE = "transportId";

    /**
     *
     */
    String RESULT_TRANSPORT_ATTRIBUTE = "transportResult";

    /**
     *
     */
    String TRANSPORT_LIST_ATTRIBUTE = "transportList";

    /**
     *
     */
    String ARRIVE_TIME_ATTRIBUTE = "arriveTime";

    /**
     *
     */
    String LEAVE_TIME_ATTRIBUTE = "leaveTime";

    /**
     *
     */
    String INTERVAL_ATTRIBUTE = "interval";

    /**
     *
     */
    String INTERVAL_SIGN_ATTRIBUTE = "intervalSign";

}

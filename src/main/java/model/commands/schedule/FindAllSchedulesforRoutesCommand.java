package ua.kpi.epam.transport.commands.schedule;

import org.apache.log4j.Logger;
import ua.kpi.epam.transport.dao.*;
import ua.kpi.epam.transport.entities.Route;
import ua.kpi.epam.transport.entities.Schedule;
import ua.kpi.epam.transport.entities.ScheduleOfStop;
import ua.kpi.epam.transport.entities.Stop;
import ua.kpi.epam.transport.extras.LocalizationHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static ua.kpi.epam.transport.servlets.TransportServlet.LOGGER_NAME;

/**
 *
 * @author KIRIL
 */
public class FindAllSchedulesforRoutesCommand implements ScheduleCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        DaoFactory daoFactory = DaoFactory.getInstance();
        ScheduleDao scheduleDao = daoFactory.createScheduleDao();
        ScheduleOfStopDao scheduleOfStopDao = daoFactory
                .createScheduleOfStopDao();
        StopDao stopDao = daoFactory.createStopDao();
        RouteDao routeDao = daoFactory.createRouteDao();

        Route route = routeDao.find(Integer.parseInt(request
                .getParameter(ROUTE_ID_ATTRIBUTE)));

        List<Schedule> scheduleList = scheduleDao.findSchedulesForRoute(route);

        Map<Schedule, Map<Stop, ScheduleOfStop>> scheduleMap = new LinkedHashMap<>();

        for (Schedule schedule : scheduleList) {

            List<ScheduleOfStop> scheduleOfStopsList = scheduleOfStopDao
                    .findScheduleOfStopsForSchedule(schedule);

            Map<Stop, ScheduleOfStop> scheduleOfStopMap = new LinkedHashMap<>();

            for (ScheduleOfStop scheduleOfStop : scheduleOfStopsList) {
                scheduleOfStopMap.put(stopDao.find(scheduleOfStop.getStopId()),
                        scheduleOfStop);
            }
            scheduleMap.put(schedule, scheduleOfStopMap);
        }
        request.setAttribute(RESULT_ROUTE_ATTRIBUTE, route);
        request.setAttribute(RESULT_MAP_ATTRIBUTE, scheduleMap);

        try {
            request.setAttribute(TRANSPORT_LIST_ATTRIBUTE, DaoFactory.getInstance()
                    .createTransportDao().findAll());
            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(
                    request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }
}

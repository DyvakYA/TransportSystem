package ua.kpi.epam.transport.commands.transport;

import org.apache.log4j.Logger;
import ua.kpi.epam.transport.dao.DaoFactory;
import ua.kpi.epam.transport.dao.ScheduleDao;
import ua.kpi.epam.transport.dao.TransportDao;
import ua.kpi.epam.transport.entities.Route;
import ua.kpi.epam.transport.entities.Schedule;
import ua.kpi.epam.transport.entities.Transport;
import ua.kpi.epam.transport.extras.LocalizationHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.kpi.epam.transport.servlets.TransportServlet.LOGGER_NAME;

/**
 *
 * @author KIRIL
 */
public class FindTransportForRouteSchedules implements TransportCommand {

    private static final String ROUTE_ATTRIBUTE = "route_id";
    private static final String NEW_DESTINATION_PAGE = "schedule.jsp";
    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        DaoFactory daoFactory = DaoFactory.getInstance();
        TransportDao transportDao = daoFactory.createTransportDao();
        ScheduleDao scheduleDao = daoFactory.createScheduleDao();

        Map<Schedule, Transport> transportMap = new HashMap<>();

        List<Schedule> scheduleList = scheduleDao
                .findSchedulesForRoute(new Route(Integer.parseInt(request
                                        .getParameter(ROUTE_ATTRIBUTE)), null));

        for (Schedule schedule : scheduleList) {
            transportMap.put(schedule,
                    transportDao.findTransportOnSchedule(schedule));
        }

        request.setAttribute(RESULT_MAP_ATTRIBUTE, transportMap);
        try {
            request.getRequestDispatcher(NEW_DESTINATION_PAGE).forward(request,
                    response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }

}

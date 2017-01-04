package ua.kpi.epam.transport.commands.schedule;

import org.apache.log4j.Logger;
import ua.kpi.epam.transport.dao.DaoFactory;
import ua.kpi.epam.transport.dao.RouteDao;
import ua.kpi.epam.transport.dao.StopDao;
import ua.kpi.epam.transport.dao.jdbc.JdbcDaoFactory;
import ua.kpi.epam.transport.entities.Route;
import ua.kpi.epam.transport.entities.Stop;
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
public class ShowPlanAddFormCommand implements ScheduleCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String SHOW_SCHEDULE_FOR_ROUTE_ERROR_MSG = "ShowScheduleForRouteError";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        DaoFactory factory = JdbcDaoFactory.getInstance();

        StopDao stopDao = factory.createStopDao();
        RouteDao routeDao = factory.createRouteDao();

        Route route = routeDao.find(Integer.parseInt(request
                .getParameter(ROUTE_ID_ATTRIBUTE)));
        if (route == null) {
            request.setAttribute(RESULT_ATTRIBUTE, LocalizationHelper.getInstanse()
                    .getLocalizedMessage(request, SHOW_SCHEDULE_FOR_ROUTE_ERROR_MSG));
        } else {

            List<Stop> stopList = stopDao.findAllStopsOnRoute(route);
            Map<Route, List<Stop>> resultMap = new HashMap<>();
            resultMap.put(route, stopList);

            request.setAttribute(RESULT_ROUTE_MAP_ATTRIBUTE, resultMap);
        }

        try {
            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }

}

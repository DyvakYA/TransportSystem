package ua.kpi.epam.transport.commands.route;

import org.apache.log4j.Logger;
import ua.kpi.epam.transport.commands.validators.route.AddStopOnRouteCommandValidator;
import ua.kpi.epam.transport.dao.DaoFactory;
import ua.kpi.epam.transport.dao.RouteStopDao;
import ua.kpi.epam.transport.dao.jdbc.JdbcDaoFactory;
import ua.kpi.epam.transport.entities.Route;
import ua.kpi.epam.transport.entities.RouteStop;
import ua.kpi.epam.transport.extras.LocalizationHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ua.kpi.epam.transport.servlets.TransportServlet.LOGGER_NAME;

/**
 *
 * @author KIRIL
 */
public class AddStopOnRouteCommand implements RouteCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String ADD_STOP_ERROR_MSG = "AddStopError";
    private static final String ADD_STOP_SUCCSESFULL_MSG = "AddStopSuccessful";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new AddStopOnRouteCommandValidator().validate(request, response)) {
            return;
        }

        RouteStopDao routeStopDao = JdbcDaoFactory.getInstance()
                .createRouteStopsDao();

        Route route = new Route(Integer.parseInt(request
                .getParameter(ROUTE_ID_ATTRIBUTE)), null);
        int stopId = Integer.parseInt(request.getParameter(STOP_ID_ATTRIBUTE));

        List<RouteStop> routeStopList = routeStopDao.findByRoute(route);

        LocalizationHelper helper = LocalizationHelper.getInstanse();

        if (routeStopList.isEmpty()) {
            routeStopDao.create(new RouteStop(null, route.getId(), stopId, 1));

            request.setAttribute(RESULT_ATTRIBUTE, helper.getLocalizedMessage(request, ADD_STOP_SUCCSESFULL_MSG));

        } else if (routeStopList.contains(new RouteStop(null, route.getId(),
                stopId, null))) {
            request.setAttribute(RESULT_ATTRIBUTE, ADD_STOP_ERROR_MSG);

        } else {
            routeStopDao
                    .create(new RouteStop(null, route.getId(), stopId,
                                    routeStopList.get(routeStopList.size() - 1)
                                    .getNumber() + 1));
            request.setAttribute(RESULT_ATTRIBUTE, helper.getLocalizedMessage(request, ADD_STOP_SUCCSESFULL_MSG));

        }

        request.setAttribute(STOP_LIST_ATTRIBUTE, DaoFactory.getInstance().createStopDao().findAll());

        try {
            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(
                    request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION),e);
        }
    }
}

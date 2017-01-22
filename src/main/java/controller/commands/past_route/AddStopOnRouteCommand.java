package commands.route;

import commands.validators.route.AddStopOnRouteCommandValidator;
import dao.DaoFactory;
import dao.RouteStopsDao;
import dao.jdbc.JdbcDaoFactory;
import entities.Route;
import entities.RouteStops;
import extras.Localization;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static servlet.TransportServlet.LOGGER_NAME;

public class AddStopOnRouteCommand implements RouteCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String ADD_STOP_ERROR_MSG = "AddStopError";
    private static final String ADD_STOP_SUCCESSFUL_MSG = "AddStopSuccessful";

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

        RouteStopsDao routeStopDao = JdbcDaoFactory.getInstance()
                .createRouteStopsDao();

        Route route = new Route(Integer.parseInt(request
                .getParameter(ROUTE_ID_ATTRIBUTE)), null);
        int stopId = Integer.parseInt(request.getParameter(STOP_ID_ATTRIBUTE));

        List<RouteStops> routeStopsList = routeStopDao.findByRoute(route);

        Localization localization = Localization.getInstanse();

        if (routeStopsList.isEmpty()) {
            routeStopDao.create(new RouteStops(null, route.getId(), stopId, 1));

            request.setAttribute(RESULT_ATTRIBUTE, localization.getLocalizedMessage(request, ADD_STOP_SUCCESSFUL_MSG));

        } else if (routeStopsList.contains(new RouteStops(null, route.getId(),
                stopId, null))) {
            request.setAttribute(RESULT_ATTRIBUTE, ADD_STOP_ERROR_MSG);

        } else {
            routeStopDao
                    .create(new RouteStops(null, route.getId(), stopId,
                                    routeStopsList.get(routeStopsList.size() - 1)
                                    .getNumber() + 1));
            request.setAttribute(RESULT_ATTRIBUTE, localization.getLocalizedMessage(request, ADD_STOP_SUCCESSFUL_MSG));

        }

        request.setAttribute(STOP_LIST_ATTRIBUTE, DaoFactory.getInstance().createStopDao().findAll());

        try {
            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(
                    request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION),e);
        }
    }
}

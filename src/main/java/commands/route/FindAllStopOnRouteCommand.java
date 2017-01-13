package commands.route;

import dao.DaoFactory;
import dao.RouteDao;
import dao.StopDao;
import dao.jdbc.JdbcDaoFactory;
import entities.Route;
import entities.Stop;
import extras.Localization;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static servlet.TransportServlet.LOGGER_NAME;

public class FindAllStopOnRouteCommand implements RouteCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String FIND_ALL_STOP_ON_ROUTE_ERROR_MSG = "FindAllStopOnRouteError";

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

        Route route = routeDao.findById(Integer.parseInt(request
                .getParameter(ROUTE_ID_ATTRIBUTE)));
        if (route == null) {
            request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                    .getLocalizedMessage(request, FIND_ALL_STOP_ON_ROUTE_ERROR_MSG));
        } else {

            List<Stop> stopList = stopDao.findAllStopsOnRoute(route);
            Map<Route, List<Stop>> resultMap = new HashMap<>();
            resultMap.put(route, stopList);

            request.setAttribute(RESULT_MAP_ATTRIBUTE, resultMap);
        }

        try {
            request.setAttribute(STOP_LIST_ATTRIBUTE, DaoFactory.getInstance().createStopDao().findAll());

            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }
}

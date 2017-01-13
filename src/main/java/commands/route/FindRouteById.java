package commands.route;

import org.apache.log4j.Logger;
import dao.DaoFactory;
import dao.RouteDao;
import entities.Route;
import extras.Localization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlet.TransportServlet.LOGGER_NAME;

public class FindRouteById implements RouteCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        RouteDao routeDao = DaoFactory.getInstance().createRouteDao();

        Route route = routeDao.findById(Integer.parseInt(request
                .getParameter(ROUTE_ID_ATTRIBUTE)));

        if (route == null) {
            request.setAttribute(
                    RESULT_ATTRIBUTE,
                    "Route"
                    + Integer.parseInt(request
                            .getParameter(ROUTE_ID_ATTRIBUTE))
                    + " don`t exist");
        } else {
            request.setAttribute(RESULT_ATTRIBUTE, "Route" + route.getId()
                    + " created");
        }
        try {
            request.setAttribute(STOP_LIST_ATTRIBUTE, DaoFactory.getInstance().createStopDao().findAll());

            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(
                    request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }

    }
}

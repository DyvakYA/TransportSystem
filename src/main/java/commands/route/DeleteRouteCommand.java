package commands.route;

import commands.validators.route.DeleteRouteCommandValidator;
import dao.DaoFactory;
import dao.RouteDao;
import dao.RouteStopsDao;
import entities.Route;
import extras.Localization;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlet.TransportServlet.LOGGER_NAME;

public class DeleteRouteCommand implements RouteCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String ROUTE_DELETED_MSG = "RouteDeletedSuccessful";
    private static final String DELETE_ROUTE_ERROR = "DeleteRouteError";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new DeleteRouteCommandValidator().validate(request, response)) {
            return;
        }

        RouteDao routeDao = DaoFactory.getInstance().createRouteDao();
        RouteStopsDao routeStopDao = DaoFactory.getInstance().createRouteStopsDao();

        int routeId = Integer.parseInt(request.getParameter(ROUTE_ID_ATTRIBUTE));

        if (!routeStopDao.findByRoute(new Route(routeId, null)).isEmpty()) {

            request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                    .getLocalizedMessage(request, DELETE_ROUTE_ERROR));

        } else {

            routeDao.delete(routeId);
            request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                    .getLocalizedMessage(request, ROUTE_DELETED_MSG));
        }

        request.setAttribute(STOP_LIST_ATTRIBUTE, DaoFactory.getInstance()
                .createStopDao().findAll());

        try {
            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(
                    request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }

    }

}

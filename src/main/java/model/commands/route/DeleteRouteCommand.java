package ua.kpi.epam.transport.commands.route;

import org.apache.log4j.Logger;
import ua.kpi.epam.transport.commands.validators.route.DeleteRouteCommandValidator;
import ua.kpi.epam.transport.dao.DaoFactory;
import ua.kpi.epam.transport.dao.RouteDao;
import ua.kpi.epam.transport.dao.RouteStopDao;
import ua.kpi.epam.transport.entities.Route;
import ua.kpi.epam.transport.extras.LocalizationHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.kpi.epam.transport.servlets.TransportServlet.LOGGER_NAME;

/**
 *
 * @author KIRIL
 */
public class DeleteRouteCommand implements RouteCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String ROUTE_DELETED_MSG = "RouteDeletedSuccsesfull";
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
        RouteStopDao routeStopDao = DaoFactory.getInstance().createRouteStopsDao();

        int routeId = Integer.parseInt(request.getParameter(ROUTE_ID_ATTRIBUTE));

        if (!routeStopDao.findByRoute(new Route(routeId, null)).isEmpty()) {

            request.setAttribute(RESULT_ATTRIBUTE, LocalizationHelper.getInstanse()
                    .getLocalizedMessage(request, DELETE_ROUTE_ERROR));

        } else {

            routeDao.delete(routeId);
            request.setAttribute(RESULT_ATTRIBUTE, LocalizationHelper.getInstanse()
                    .getLocalizedMessage(request, ROUTE_DELETED_MSG));
        }

        request.setAttribute(STOP_LIST_ATTRIBUTE, DaoFactory.getInstance()
                .createStopDao().findAll());

        try {
            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(
                    request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }

    }

}

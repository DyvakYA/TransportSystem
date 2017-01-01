package ua.kpi.epam.transport.commands.route;

import org.apache.log4j.Logger;
import ua.kpi.epam.transport.commands.validators.route.RemoveStopFromRouteCommandValidator;
import ua.kpi.epam.transport.dao.DaoFactory;
import ua.kpi.epam.transport.dao.RouteStopDao;
import ua.kpi.epam.transport.dao.jdbc.JdbcDaoFactory;
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
public class RemoveStopFromRouteCommand implements RouteCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String REMOVE_STOP_FROM_ROUTE_MSG = "RemoveStopFromRouteSuccesfull";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new RemoveStopFromRouteCommandValidator().validate(request, response)) {
            return;
        }
        RouteStopDao routeStopDao = JdbcDaoFactory.getInstance()
                .createRouteStopsDao();

        routeStopDao.delete(Integer.parseInt(request
                .getParameter(ROUTE_ID_ATTRIBUTE)), Integer.parseInt(request
                        .getParameter(STOP_TO_REMOVE_ATTRIBUTE)));

        request.setAttribute(RESULT_ATTRIBUTE, LocalizationHelper.getInstanse()
                .getLocalizedMessage(request, REMOVE_STOP_FROM_ROUTE_MSG));

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

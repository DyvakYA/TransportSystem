package commands.route;

import org.apache.log4j.Logger;
import commands.validators.route.RemoveStopFromRouteCommandValidator;
import dao.DaoFactory;
import dao.RouteStopsDao;
import dao.jdbc.JdbcDaoFactory;
import extras.Localization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlet.TransportServlet.LOGGER_NAME;

public class RemoveStopFromRouteCommand implements RouteCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String REMOVE_STOP_FROM_ROUTE_MSG = "RemoveStopFromRouteSuccessful";

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
        RouteStopsDao routeStopDao = JdbcDaoFactory.getInstance()
                .createRouteStopsDao();

//        routeStopDao.delete(Integer.parseInt(request
//                .getParameter(ROUTE_ID_ATTRIBUTE)), Integer.parseInt(request
//                        .getParameter(STOP_TO_REMOVE_ATTRIBUTE)));

        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, REMOVE_STOP_FROM_ROUTE_MSG));

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

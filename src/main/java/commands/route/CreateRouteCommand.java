package commands.route;

import org.apache.log4j.Logger;
import commands.validators.route.CreateRouteCommandValidator;
import dao.DaoFactory;
import dao.RouteDao;
import entities.Route;
import extras.Localization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlet.TransportServlet.LOGGER_NAME;

public class CreateRouteCommand implements RouteCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String ROUTE_CREATED_SUCCESSFUL_MSG = "RouteCreatedSuccessful";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new CreateRouteCommandValidator().validate(request, response)) {
            return;
        }
        RouteDao routeDao = DaoFactory.getInstance().createRouteDao();

        routeDao.create(new Route(null, request
                .getParameter(ROUTE_NAME_ATTRIBUTE)));

        request.setAttribute(STOP_LIST_ATTRIBUTE, DaoFactory.getInstance().createStopDao().findAll());
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse().getLocalizedMessage(request, ROUTE_CREATED_SUCCESSFUL_MSG));
        try {
            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(
                    request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }
}

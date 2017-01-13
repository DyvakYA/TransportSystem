package commands.route;

import commands.validators.route.UpdateRouteCommandValidator;
import dao.DaoFactory;
import dao.RouteDao;
import extras.Localization;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlet.TransportServlet.LOGGER_NAME;

public class UpdateRouteCommand implements RouteCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String UPDATE_ROUTE_SUCCESSFUL_MSG = "UpdateRouteSuccessful";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new UpdateRouteCommandValidator().validate(request, response)) {
            return;
        }
        RouteDao routeDao = DaoFactory.getInstance().createRouteDao();

//        routeDao.update(new Route(Integer.parseInt(request
//                .getParameter(ROUTE_ID_ATTRIBUTE)), request
//                .getParameter(ROUTE_NAME_ATTRIBUTE)));

        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, UPDATE_ROUTE_SUCCESSFUL_MSG));
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

package ua.kpi.epam.transport.commands.route;

import org.apache.log4j.Logger;
import ua.kpi.epam.transport.dao.DaoFactory;
import ua.kpi.epam.transport.dao.RouteDao;
import ua.kpi.epam.transport.entities.Route;
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
public class FindAllRoutesCommand implements RouteCommand {
    
     private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";

    /**
     *
     * @param request
     * @param response
     */
    @Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		RouteDao routeDao = DaoFactory.getInstance().createRouteDao();

		List<Route> route = routeDao.findAll();

		request.setAttribute(STOP_LIST_ATTRIBUTE, DaoFactory.getInstance().createStopDao().findAll());

		request.setAttribute(RESULT_LIST_ATTRIBUTE, route);
		try {
			request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(request,
					response);
		} catch (ServletException | IOException e) {
          Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
		}

	}
}

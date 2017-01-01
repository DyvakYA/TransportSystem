package ua.kpi.epam.transport.commands.route;

import org.apache.log4j.Logger;
import ua.kpi.epam.transport.dao.DaoFactory;
import ua.kpi.epam.transport.dao.RouteDao;
import ua.kpi.epam.transport.dao.StopDao;
import ua.kpi.epam.transport.entities.Route;
import ua.kpi.epam.transport.entities.Stop;
import ua.kpi.epam.transport.extras.LocalizationHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.kpi.epam.transport.servlets.TransportServlet.LOGGER_NAME;

/**
 *
 * @author KIRIL
 */
public class FindAllRouteAndStopsCommand implements RouteCommand {

	 private static final String DESTINATION_PAGE = "routeSchemes.jsp";
         private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";

    /**
     *
     * @param request
     * @param response
     */
    @Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		RouteDao routeDao = DaoFactory.getInstance().createRouteDao();

		List<Route> routeList = routeDao.findAll();

		StopDao stopDao = DaoFactory.getInstance().createStopDao();

		Map<Route, List<Stop>> routeMap = new HashMap<>();

		for (Route route : routeList) {
			routeMap.put(route, stopDao.findAllStopsOnRoute(route));
		}

		request.setAttribute(STOP_LIST_ATTRIBUTE, DaoFactory.getInstance().createStopDao().findAll());
		
		request.setAttribute(RESULT_MAP_ATTRIBUTE, routeMap);
		try {
			request.getRequestDispatcher(DESTINATION_PAGE).forward(request,
					response);
		} catch (ServletException | IOException e) {
		Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
		}
	}

}

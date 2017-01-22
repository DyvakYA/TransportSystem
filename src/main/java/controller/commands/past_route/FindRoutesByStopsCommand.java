package commands.route;

import org.apache.log4j.Logger;
import commands.validators.route.FindRoutesByStopsCommandValidator;
import dao.DaoFactory;
import dao.RouteDao;
import dao.StopDao;
import dao.TransportDao;
import entities.Route;
import entities.Stop;
import entities.Transport;
import entities.enums.TransportType;
import extras.Localization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static servlet.TransportServlet.LOGGER_NAME;

public class FindRoutesByStopsCommand implements RouteCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String ROUTE_NOT_FOUND_MSG = "RouteByStopsNotFound";
    private static final String TROLLEYBUS = "trolleybus";
    private static final String TRAM = "tram";
    private static final String BUS = "bus";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new FindRoutesByStopsCommandValidator().validate(request, response)) {
            return;
        }

        DaoFactory daoFactory = DaoFactory.getInstance();
        RouteDao routeDao = daoFactory.createRouteDao();
        TransportDao transportDao = daoFactory.createTransportDao();
        StopDao stopDao = daoFactory.createStopDao();

        List<TransportType> transportTypesList = new LinkedList<>();

        String[] types = request.getParameterValues(TRANSPORT_TYPE_ATTRIBUTE);

        for (String type : types) {

            type = type.toLowerCase();

            switch (type) {

                case (BUS): {
                    transportTypesList.add(TransportType.BUS);
                }
                break;
                case (TRAM): {
                    transportTypesList.add(TransportType.TRAM);

                }
                break;
                case (TROLLEYBUS): {
                    transportTypesList.add(TransportType.TROLLEYBUS);
                }
                break;
            }
        }

        List<Route> routeList = routeDao.findRoutesByStops(new Stop(null, null,
                request.getParameter(START_STOP_ATTRIBUTE)), new Stop(null,
                        null, request.getParameter(FINISH_STOP_ATTRIBUTE)));
        for (Iterator<Route> itr = routeList.iterator(); itr.hasNext();) {
            Route route = itr.next();
            if (!filterByTransportType(transportTypesList, route,
                    transportDao.findTransportOnRoute(route))) {
                itr.remove();
            }
        }

        Map<Route, List<Stop>> routeMap = new HashMap<>();

        for (Route route : routeList) {
            routeMap.put(route, stopDao.findAllStopsOnRoute(route));
        }

        request.setAttribute(RESULT_MAP_ATTRIBUTE, routeMap);
        if (routeMap.isEmpty()) {
            request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                    .getLocalizedMessage(request, ROUTE_NOT_FOUND_MSG));
        }
        try {
            request.getRequestDispatcher(DESTINATION_PAGE).forward(request,
                    response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }

    private boolean filterByTransportType(
            List<TransportType> transportTypesList, Route route,
            List<Transport> transportList) {

        for (Transport transport : transportList) {
            for (TransportType type : transportTypesList) {
                if (transport.getType().equals(type)) {
                    return true;
                }
            }
        }
        return false;
    }

}

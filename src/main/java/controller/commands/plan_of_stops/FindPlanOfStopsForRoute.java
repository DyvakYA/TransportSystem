package commands.plan_of_stops;

import org.apache.log4j.Logger;
import dao.*;
import entities.Route;
import entities.Plan;
import entities.PlanOfStops;
import entities.Stop;
import extras.Localization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static servlet.TransportServlet.LOGGER_NAME;


public class FindPlanOfStopsForRoute implements PlanOfStopsCommand {

    private static final String RESULT_ROUTE_ATTRIBUTE = "route";
    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        DaoFactory daoFactory = DaoFactory.getInstance();
        PlanDao scheduleDao = daoFactory.createPlanDao();
        PlanOfStopsDao scheduleOfStopDao = daoFactory
                .createPlanOfStopsDao();
        StopDao stopDao = daoFactory.createStopDao();
        RouteDao routeDao = daoFactory.createRouteDao();

        Route route = routeDao.findById(Integer.parseInt(request
                .getParameter(ROUTE_ID_ATTRIBUTE)));

        List<Plan> scheduleList = scheduleDao.findPlansForRoute(route);

        Map<Plan, Map<Stop, PlanOfStops>> scheduleMap = new LinkedHashMap<>();

        for (Plan plan : scheduleList) {

            List<PlanOfStops> scheduleOfStopsList = scheduleOfStopDao
                    .findPlanOfStopsForPlan(plan);

            Map<Stop, PlanOfStops> scheduleOfStopMap = new LinkedHashMap<>();

            for (PlanOfStops scheduleOfStop : scheduleOfStopsList) {
                scheduleOfStopMap.put(stopDao.findById(scheduleOfStop.getStopId()),
                        scheduleOfStop);
            }
            scheduleMap.put(plan, scheduleOfStopMap);
        }
        request.setAttribute(RESULT_ROUTE_ATTRIBUTE, route);
        request.setAttribute(RESULT_MAP_ATTRIBUTE, scheduleMap);

        try {
            request.getRequestDispatcher(DESTINATION_PAGE).forward(request,
                    response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }
}

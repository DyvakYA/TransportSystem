package commands.plan;

import dao.*;
import entities.*;
import extras.Localization;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static servlet.TransportServlet.LOGGER_NAME;

public class FindAllPlansForRoutesCommand implements PlanCommand {

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

            Map<Stop, PlanOfStops> planOfStopsMap = new LinkedHashMap<>();

            for (PlanOfStops planOfStops : scheduleOfStopsList) {
                planOfStopsMap.put(stopDao.findById(planOfStops.getStopId()),
                        planOfStops);
            }
            scheduleMap.put(plan, planOfStopsMap);
        }
        request.setAttribute(RESULT_ROUTE_ATTRIBUTE, route);
        request.setAttribute(RESULT_MAP_ATTRIBUTE, scheduleMap);

        try {
            request.setAttribute(TRANSPORT_LIST_ATTRIBUTE, DaoFactory.getInstance()
                    .createTransportDao().findAll());
            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(
                    request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }
}

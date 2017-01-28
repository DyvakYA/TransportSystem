package controller.commands.route;

import model.entities.Route;
import model.entities.Stop;
import model.extras.Localization;
import model.services.service.RouteService;
import model.services.service.StopService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static controller.servlet.MainController.LOGGER_NAME;

public class FindAllStopOnRouteCommand implements RouteCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String FIND_ALL_STOP_ON_ROUTE_ERROR_MSG = "FindAllStopOnRouteError";

    private RouteService routeService = RouteService.getInstance();
    private StopService stopService = StopService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("-----------------------------------------------");

        Optional<Route> route = routeService.findById(Integer.parseInt(request.getParameter(ROUTE_ID_ATTRIBUTE)));

        if (route.equals(Optional.empty())) {
            request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                    .getLocalizedMessage(request, FIND_ALL_STOP_ON_ROUTE_ERROR_MSG));
        } else {

            List<Stop> stopList = stopService.findAllStopsOnRoute(route.get());
            Map<Route, List<Stop>> resultMap = new HashMap<>();
            resultMap.put(route.get(), stopList);

            request.setAttribute(RESULT_MAP_ATTRIBUTE, resultMap);
        }

        try {
            request.setAttribute(STOP_LIST_ATTRIBUTE, stopService.getAll());

            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
        return "/WEB-INF/admin/routes.jsp";
    }
}

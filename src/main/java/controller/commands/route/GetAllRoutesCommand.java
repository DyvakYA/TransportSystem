package controller.commands.route;

import model.entities.Route;
import model.services.RouteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Dyvak on 21.01.2017.
 */
public class GetAllRoutesCommand implements RouteCommand {

    private RouteService routeService = RouteService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Route> routes = routeService.getAllRoutes();
        request.setAttribute(ROUTE_LIST_ATTRIBUTE, routes);
        return "/WEB-INF/admin/routes.jspx";
    }
}

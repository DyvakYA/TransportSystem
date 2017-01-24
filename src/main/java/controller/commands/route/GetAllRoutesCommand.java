package controller.commands.route;

import model.entities.Route;
import model.services.service.RouteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 21.01.2017.
 */
public class GetAllRoutesCommand implements RouteCommand {

    private RouteService routeService = RouteService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Optional<Route>> routes = routeService.getAll();
        request.setAttribute(ROUTE_LIST_ATTRIBUTE, routes);
        return "/WEB-INF/admin/routes.jspx";
    }
}

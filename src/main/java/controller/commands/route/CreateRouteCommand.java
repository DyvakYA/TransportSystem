package controller.commands.route;

import controller.commands.validators.route.CreateRouteCommandValidator;
import model.extras.Localization;
import model.entities.Route;
import model.services.service.RouteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateRouteCommand implements RouteCommand {

    private RouteService routeService = RouteService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        if (!new CreateRouteCommandValidator().validate(request, response)) {
            return null;
        }
        Route route = new Route(request.getParameter(NAME_ATTRIBUTE));
        routeService.create(route);
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, CREATE_ROUTE_SUCCESSFUL_MSG));
        request.setAttribute(ROUTE_LIST_ATTRIBUTE, routeService.getAll());
        return "/WEB-INF/admin/routes.jsp";
    }
}

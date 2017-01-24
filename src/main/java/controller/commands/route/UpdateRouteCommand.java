package controller.commands.route;

import controller.commands.Command;
import controller.commands.validators.route.UpdateRouteCommandValidator;
import model.extras.Localization;
import model.entities.Route;
import model.services.service.RouteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateRouteCommand implements RouteCommand {

    private RouteService routeService = RouteService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        if (!new UpdateRouteCommandValidator().validate(request, response)) {
            return null;
        }
        Route route = new Route(Integer.parseInt(request.getParameter(ID_ATTRIBUTE)),
                request.getParameter(Command.NAME_ATTRIBUTE));
        routeService.update(route, route.getId());
        request.setAttribute(Command.RESULT_ATTRIBUTE, Localization.getInstanse()
            .getLocalizedMessage(request, UPDATE_ROUTE_SUCCESSFUL_MSG));
        request.setAttribute(ROUTE_LIST_ATTRIBUTE, routeService.getAll());
        return "/WEB-INF/admin/routes.jspx";
    }

}

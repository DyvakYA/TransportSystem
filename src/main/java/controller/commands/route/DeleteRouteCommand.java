package controller.commands.route;

import controller.commands.Command;
import controller.commands.validators.route.DeleteRouteCommandValidator;
import model.extras.Localization;
import model.services.service.RouteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteRouteCommand implements RouteCommand {

    private RouteService routeService = RouteService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        if (!new DeleteRouteCommandValidator().validate(request, response)) {
            return null;
        }
        int routeId = Integer.parseInt(request.getParameter(ROUTE_ID_ATTRIBUTE));
        routeService.delete(routeId);
        request.setAttribute(Command.RESULT_ATTRIBUTE, Localization.getInstanse()
            .getLocalizedMessage(request, DELETE_ROUTE_SUCCESSFUL_MSG));
        request.setAttribute(ROUTE_LIST_ATTRIBUTE, routeService.getAll());
        return "/WEB-INF/admin/routes.jspx";
    }

}

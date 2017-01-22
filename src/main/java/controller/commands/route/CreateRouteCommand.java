package controller.commands.route;

import controller.commands.validators.route.CreateRouteCommandValidator;
import model.extras.Localization;
import model.entities.Route;
import model.services.RouteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateRouteCommand implements RouteCommand {

    private RouteService routeService = RouteService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!new CreateRouteCommandValidator().validate(request, response)) {
            return "";
        }

        Route route = new Route(request.getParameter(NAME_ATTRIBUTE));

        routeService.createRoute(route);

        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, CREATE_ROUTE_SUCCESSFUL_MSG));

            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(request,
                    response);

        return "/WEB-INF/view/transportsList.jspx";
    }
}

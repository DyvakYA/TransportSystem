package controller.commands.driver;

import controller.commands.validators.route.DeleteRouteCommandValidator;
import model.extras.Localization;
import model.services.DriverService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteDriverCommand implements DriverCommand {

    private DriverService driverService = DriverService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!new DeleteRouteCommandValidator().validate(request, response)) {
            return "";
        }

        int routeId = Integer.parseInt(request.getParameter(DRIVER_ID_ATTRIBUTE));

        driverService.deleteDriver(routeId);

            request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                    .getLocalizedMessage(request, DELETE_DRIVER_SUCCESSFUL_MSG));

        request.setAttribute(DRIVER_LIST_ATTRIBUTE, driverService.getAllDrivers());

            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(
                    request, response);

        return DELETE_DRIVER_SUCCESSFUL_MSG;

    }

}

package controller.commands.driver;

import controller.commands.validators.route.DeleteRouteCommandValidator;
import model.extras.Localization;
import model.services.service.DriverService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteDriverCommand implements DriverCommand {

    private DriverService driverService = DriverService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        if (!new DeleteRouteCommandValidator().validate(request, response)) {
            return null;
        }
        int driverId = Integer.parseInt(request.getParameter(DRIVER_ID_ATTRIBUTE));
        driverService.delete(driverId);
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, DELETE_DRIVER_SUCCESSFUL_MSG));
        request.setAttribute(DRIVER_LIST_ATTRIBUTE, driverService.getAll());
        return "/WEB-INF/admin/drivers.jspx";
    }

}

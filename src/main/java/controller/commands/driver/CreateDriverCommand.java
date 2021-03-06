package controller.commands.driver;

import controller.commands.validators.driver.CreateDriverCommandValidator;
import model.entities.Driver;
import model.extras.Localization;
import model.services.service.DriverService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateDriverCommand implements DriverCommand {

    private DriverService driverService = DriverService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        if (!new CreateDriverCommandValidator().validate(request, response)) {
            return null;
        }
        Driver driver = new Driver(
                null,
                request.getParameter(NAME_ATTRIBUTE),
                request.getParameter(SURNAME_ATTRIBUTE),
                Integer.parseInt(request.getParameter(AGE_ATTRIBUTE)),
                Integer.parseInt(request.getParameter(ROUTE_ID_ATTRIBUTE)));
        driverService.create(driver);
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, CREATE_DRIVER_SUCCESSFUL_MSG));
        request.setAttribute(DRIVER_LIST_ATTRIBUTE, driverService.getAll());
        return "/WEB-INF/admin/drivers.jsp";
    }
}

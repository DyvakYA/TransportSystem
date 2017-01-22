package controller.commands.driver;

import controller.commands.validators.driver.CreateDriverCommandValidator;
import model.extras.Localization;
import model.entities.Driver;
import model.services.DriverService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateDriverCommand implements DriverCommand {

    private DriverService driverService = DriverService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!new CreateDriverCommandValidator().validate(request, response)) {
            return "";
        }

        Driver driver = new Driver(request.getParameter(NAME_ATTRIBUTE),
                request.getParameter(SURNAME_ATTRIBUTE),
                Integer.parseInt(request.getParameter(AGE_ATTRIBUTE)),
                Integer.parseInt(request.getParameter(ROUTE_ID_ATTRIBUTE)));

        driverService.createDriver(driver);

        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, CREATE_DRIVER_SUCCESSFUL_MSG));

            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(request,
                    response);

        return CREATE_DRIVER_SUCCESSFUL_MSG;
    }
}

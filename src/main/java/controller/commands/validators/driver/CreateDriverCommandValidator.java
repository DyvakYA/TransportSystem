package controller.commands.validators.driver;

import controller.commands.validators.CommandValidator;
import controller.commands.validators.CommandValidatorHelper;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.commands.driver.DriverCommand.*;

public class CreateDriverCommandValidator implements CommandValidator {
    
    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = Localization.getInstanse().getLocalizedMessage(request, DRIVER_ERROR_MSG);

        return CommandValidatorHelper.getInstance().isNullValidate(new String[]{ROUTE_ID_ATTRIBUTE},
                RESULT_ATTRIBUTE, ADMIN_DESTINATION_PAGE, message, request, response);
    }
    
}

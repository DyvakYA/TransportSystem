package controller.commands.validators.stop;

import controller.commands.validators.CommandValidator;
import controller.commands.validators.CommandValidatorHelper;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.commands.stop.StopCommand.*;

public class DeleteStopCommandValidator implements CommandValidator {

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

         String message = Localization.getInstanse().getLocalizedMessage(request, STOP_ERROR_MSG);
         
        return CommandValidatorHelper.getInstance().isNullValidate(new String[]{ID_ATTRIBUTE},
                RESULT_ATTRIBUTE, USER_DESTINATION_PAGE, message, request, response);
    }

}

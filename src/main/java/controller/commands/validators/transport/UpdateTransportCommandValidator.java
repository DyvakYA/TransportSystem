package controller.commands.validators.transport;

import controller.commands.validators.CommandValidator;
import controller.commands.validators.CommandValidatorHelper;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.commands.transport.TransportCommand.*;

public class UpdateTransportCommandValidator implements CommandValidator {

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

                String message = Localization.getInstanse().getLocalizedMessage(request, TRANSPORT_ERROR_MSG);
        
        return CommandValidatorHelper.getInstance().isNullValidate(new String[]{ID_ATTRIBUTE},
                RESULT_ATTRIBUTE, ADMIN_DESTINATION_PAGE, message, request, response) &&
               CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{NUMBER_ATTRIBUTE,MODEL_ATTRIBUTE},
                RESULT_ATTRIBUTE, ADMIN_DESTINATION_PAGE, message, request, response) ;
    }
}

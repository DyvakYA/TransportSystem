package controller.commands.validators.user;

import controller.commands.validators.CommandValidator;
import controller.commands.validators.CommandValidatorHelper;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.commands.user.AuthenticateUserCommand.*;

public class AutentificateUserCommandValidator implements CommandValidator{

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = Localization.getInstanse().getLocalizedMessage(request, MSG_NAME);
        
         return CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{EMAIL_ATTRIBUTE,PASSWORD_ATTRIBUTE},
                RESULT_ATTRIBUTE, RESULT_PAGE, message, request, response);
    }

    
}

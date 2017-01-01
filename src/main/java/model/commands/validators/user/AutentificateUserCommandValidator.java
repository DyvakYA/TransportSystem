
package model.commands.validators.user;

import model.commands.validators.CommandValidator;
import model.commands.validators.CommandValidatorHelper;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.commands.user.AutentificateUserCommand.*;

public class AutentificateUserCommandValidator implements CommandValidator{

    private static final String MSG_NAME = "NotValidEmptyLoginAndPassword";

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = Localization.getInstanse().getLocalizedMessage(request, MSG_NAME);
        
         return CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{LOGIN_ATTRIBUTE,PASSWORD_ATTRIBUTE},
                RESULT_ATTRIBUTE, RESULT_PAGE, message, request, response);
    }
}

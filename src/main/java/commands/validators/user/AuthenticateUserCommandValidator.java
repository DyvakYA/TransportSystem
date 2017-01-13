
package commands.validators.user;

import commands.validators.CommandValidator;
import commands.validators.CommandValidatorHelper;
import extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static commands.user.AuthenticateUserCommand.*;

public class AuthenticateUserCommandValidator implements CommandValidator{

    private static final String MSG_NAME = "NotValidEmptyLoginAndPassword";

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = Localization.getInstanse().getLocalizedMessage(request, MSG_NAME);
        
         return CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{LOGIN_ATTRIBUTE,PASSWORD_ATTRIBUTE},
                RESULT_ATTRIBUTE, RESULT_PAGE, message, request, response);
    }
}

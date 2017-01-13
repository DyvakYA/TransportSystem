package commands.validators.stop;

import commands.validators.CommandValidator;
import commands.validators.CommandValidatorHelper;
import extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static commands.stop.StopCommand.*;

public class UpdateStopCommandValidator implements CommandValidator {

    private static final String ERROR_MSG = "StopForUpdateNotSelected";

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = Localization.getInstanse().getLocalizedMessage(request, ERROR_MSG);
        
        return CommandValidatorHelper.getInstance().isNullValidate(new String[]{ID_ATTRIBUTE},
                RESULT_ATTRIBUTE, DESTINATION_PAGE, message, request, response)
                && CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{NAME_ATTRIBUTE, ADDRESS_ATTRIBUTE},
                RESULT_ATTRIBUTE, DESTINATION_PAGE, message, request, response);
    }

}

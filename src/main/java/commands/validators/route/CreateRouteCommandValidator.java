package commands.validators.route;

import commands.validators.CommandValidator;
import commands.validators.CommandValidatorHelper;
import extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static commands.route.RouteCommand.*;

public class CreateRouteCommandValidator implements CommandValidator {

    private static final String ERROR_MSG = "EmptyRouteCreationFields";

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = Localization.getInstanse().getLocalizedMessage(request, ERROR_MSG);
        
        return CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{ROUTE_NAME_ATTRIBUTE},
                RESULT_ATTRIBUTE, ADMIN_DESTINATION_PAGE, message, request, response);
    }
}

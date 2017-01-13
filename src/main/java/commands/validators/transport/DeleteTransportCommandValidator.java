package commands.validators.transport;

import commands.validators.CommandValidator;
import commands.validators.CommandValidatorHelper;
import extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static commands.transport.TransportCommand.*;

public class DeleteTransportCommandValidator implements CommandValidator{

  private static final String ERROR_MSG = "NotSelectedTransportToDelete";

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
                RESULT_ATTRIBUTE, DESTINATION_ADMIN_PAGE, message, request, response);
    }

}

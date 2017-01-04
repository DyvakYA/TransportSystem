/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.commands.validators.transport;

import model.extras.Localization;
import model.commands.validators.CommandValidator;
import model.commands.validators.CommandValidatorHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.commands.transport.TransportCommand.*;

public class UpdateTransportCommandValidator implements CommandValidator{

    private static final String ERROR_MSG = "EmptyTransportNumberAndModel";

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
                RESULT_ATTRIBUTE, DESTINATION_ADMIN_PAGE, message, request, response) &&
               CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{NUMBER_ATTRIBUTE,MODEL_ATTRIBUTE},
                RESULT_ATTRIBUTE, DESTINATION_ADMIN_PAGE, message, request, response) ;
    }
}

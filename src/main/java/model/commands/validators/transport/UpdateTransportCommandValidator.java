/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.epam.transport.commands.validators.transport;

import ua.kpi.epam.transport.commands.validators.CommandValidator;
import ua.kpi.epam.transport.commands.validators.CommandValidatorHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.kpi.epam.transport.commands.transport.TransportCommand.*;

/**
 *
 * @author KIRIL
 */
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

                String message = ua.kpi.epam.transport.extras.Localization.getInstanse().getLocalizedMessage(request, ERROR_MSG);
        
        return CommandValidatorHelper.getInstance().isNullValidate(new String[]{ID_ATTRIBUTE},
                RESULT_ATTRIBUTE, DESTINATION_ADMIN_PAGE, message, request, response) &&
               CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{NUMBER_ATTRIBUTE,MODEL_ATTRIBUTE},
                RESULT_ATTRIBUTE, DESTINATION_ADMIN_PAGE, message, request, response) ;
    }
}

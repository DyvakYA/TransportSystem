/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.epam.transport.commands.validators.stop;

import ua.kpi.epam.transport.commands.validators.CommandValidator;
import ua.kpi.epam.transport.commands.validators.CommandValidatorHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.kpi.epam.transport.commands.stop.StopCommand.*;


/**
 *
 * @author KIRIL
 */
public class CreateStopCommandValidator implements CommandValidator{

   private static final String ERROR_MSG = "EmptyStopCreationFields";

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = ua.kpi.epam.transport.extras.Localization.getInstanse().getLocalizedMessage(request, ERROR_MSG);
        
        return CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{NAME_ATTRIBUTE,ADDRESS_ATTRIBUTE},
                RESULT_ATTRIBUTE, DESTINATION_PAGE, message, request, response);
    }
    
}

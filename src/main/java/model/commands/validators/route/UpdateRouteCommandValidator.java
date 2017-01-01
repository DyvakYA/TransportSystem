/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.epam.transport.commands.validators.route;

import ua.kpi.epam.transport.commands.validators.CommandValidator;
import ua.kpi.epam.transport.commands.validators.CommandValidatorHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.kpi.epam.transport.commands.route.RouteCommand.*;


/**
 *
 * @author KIRIL
 */
public class UpdateRouteCommandValidator implements CommandValidator {

    private static final String ERROR_MSG = "RouteNotCoosed";

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = ua.kpi.epam.transport.extras.Localization.getInstanse().getLocalizedMessage(request, ERROR_MSG);
        
        return (CommandValidatorHelper.getInstance().isNullValidate(new String[]{ROUTE_ID_ATTRIBUTE},
                RESULT_ATTRIBUTE, ADMIN_DESTINATION_PAGE, message, request, response)
                && CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{ROUTE_NAME_ATTRIBUTE},
                RESULT_ATTRIBUTE, ADMIN_DESTINATION_PAGE, message, request, response));
    }

}

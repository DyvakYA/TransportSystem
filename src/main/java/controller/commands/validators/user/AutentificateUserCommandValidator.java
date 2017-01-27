/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.validators.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.kpi.epam.transport.commands.validators.CommandValidator;
import ua.kpi.epam.transport.commands.validators.CommandValidatorHelper;

import static ua.kpi.epam.transport.commands.user.AutentificateUserCommand.*;
import static ua.kpi.epam.transport.commands.user.AutentificateUserCommand.LOGIN_ATTRIBUTE;
import static ua.kpi.epam.transport.commands.user.AutentificateUserCommand.PASSWORD_ATTRIBUTE;
import static ua.kpi.epam.transport.commands.user.AutentificateUserCommand.RESULT_ATTRIBUTE;
import static ua.kpi.epam.transport.commands.user.AutentificateUserCommand.RESULT_PAGE;

import ua.kpi.epam.transport.extras.LocalizationHelper;

/**
 *
 * @author KIRIL
 */
public class AutentificateUserCommandValidator implements CommandValidator{

    private static final String MSG_NAME = "NotValidEmtyLoginAndPassword";

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = LocalizationHelper.getInstanse().getLocalizedMessage(request, MSG_NAME);
        
         return CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{LOGIN_ATTRIBUTE,PASSWORD_ATTRIBUTE},
                RESULT_ATTRIBUTE, RESULT_PAGE, message, request, response);
    }

    
}

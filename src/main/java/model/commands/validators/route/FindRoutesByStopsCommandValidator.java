/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.epam.transport.commands.validators.route;

import ua.kpi.epam.transport.commands.validators.CommandValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ua.kpi.epam.transport.commands.route.RouteCommand.*;

/**
 *
 * @author KIRIL
 */
public class FindRoutesByStopsCommandValidator implements CommandValidator {

    private static final String ERROR_MSG = "TransportTypesNotChoosed";

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

         String message = ua.kpi.epam.transport.extras.Localization.getInstanse().getLocalizedMessage(request, ERROR_MSG);
        
        String[] types = request.getParameterValues(TRANSPORT_TYPE_ATTRIBUTE);

        String startStop = request.getParameter(START_STOP_ATTRIBUTE);
        String finishStop = request.getParameter(FINISH_STOP_ATTRIBUTE);

        if (types == null || startStop.isEmpty() || finishStop.isEmpty()) {
            request.setAttribute(RESULT_ATTRIBUTE, message);
            try {
                request.getRequestDispatcher(DESTINATION_PAGE).forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(FindRoutesByStopsCommandValidator.class.getName()).log(Level.SEVERE, null, ex);
            }
              return false;
        }
        return true;
    }

}

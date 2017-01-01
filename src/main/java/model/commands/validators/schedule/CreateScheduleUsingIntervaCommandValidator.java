/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.epam.transport.commands.validators.schedule;

import ua.kpi.epam.transport.commands.validators.CommandValidator;
import ua.kpi.epam.transport.commands.validators.CommandValidatorHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.kpi.epam.transport.commands.schedule.ScheduleCommand.*;

/**
 *
 * @author KIRIL
 */
public class CreateScheduleUsingIntervaCommandValidator implements CommandValidator {

    private static final String SHEDULE_OR_TRANSPORT_ERROR_MSG = "TransportForScheduleNotChoosed";
    private static final String INTERVAL_ERROR_MSG = "EmptyInterval";
    private static final String BAD_INTERVAL_ERROR_MSG = "NotValidInterval";
    private static final String TIME_PATTERN = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        
        String badScheduleMessage = ua.kpi.epam.transport.extras.Localization.getInstanse().getLocalizedMessage(request, SHEDULE_OR_TRANSPORT_ERROR_MSG);
        String emptyIntervalmessage = ua.kpi.epam.transport.extras.Localization.getInstanse().getLocalizedMessage(request, INTERVAL_ERROR_MSG);
        String badIntervalmessage = ua.kpi.epam.transport.extras.Localization.getInstanse().getLocalizedMessage(request, BAD_INTERVAL_ERROR_MSG);
        
        if (!CommandValidatorHelper.getInstance().isNullValidate(new String[]{TRANSPORT_ID_ATTRIBUTE, SCHEDULE_ID_ATTRIBUTE, ROUTE_ID_ATTRIBUTE},
                RESULT_ATTRIBUTE, ADMIN_DESTINATION_PAGE, badScheduleMessage, request, response)) {
            return false;
        } else if (!CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{INTERVAL_ATTRIBUTE},
                RESULT_ATTRIBUTE, ADMIN_DESTINATION_PAGE, emptyIntervalmessage, request, response)) {
            return false;
        } else if (!CommandValidatorHelper.getInstance().matchesValidate(INTERVAL_ATTRIBUTE, TIME_PATTERN,
                RESULT_ATTRIBUTE, ADMIN_DESTINATION_PAGE, badIntervalmessage, request, response)) {
            return false;
        }
        return true;
    }

}

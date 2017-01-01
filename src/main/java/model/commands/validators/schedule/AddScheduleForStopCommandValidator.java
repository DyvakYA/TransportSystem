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
public class AddScheduleForStopCommandValidator implements CommandValidator {

    private static final String STOP_OR_SCHEDULE_ERROR_MSG = "StopNotChosed";
    private static final String TIME_ERROR_MSG = "EmptyTime";
    private static final String BAD_TIME_FORMAT_ERROR_MSG = "NotValidTimeFormat";
    private static final String TIME_PATTERN = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String stopErrorMessage = ua.kpi.epam.transport.extras.Localization.getInstanse().getLocalizedMessage(request, STOP_OR_SCHEDULE_ERROR_MSG);
        String noTimeMessage = ua.kpi.epam.transport.extras.Localization.getInstanse().getLocalizedMessage(request, TIME_ERROR_MSG);
        String badTimeMessage = ua.kpi.epam.transport.extras.Localization.getInstanse().getLocalizedMessage(request, BAD_TIME_FORMAT_ERROR_MSG);
        
        if (!CommandValidatorHelper.getInstance().isNullValidate(new String[]{SCHEDULE_ID_ATTRIBUTE, STOP_ID_ATTRIBUTE},
                RESULT_ATTRIBUTE, ADMIN_DESTINATION_PAGE, stopErrorMessage, request, response)) {
            return false;
        } else if (!CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{ARRIVE_TIME_ATTRIBUTE, LEAVE_TIME_ATTRIBUTE},
                RESULT_ATTRIBUTE, ADMIN_DESTINATION_PAGE, noTimeMessage, request, response)) {
            return false;
        } else if (!CommandValidatorHelper.getInstance().matchesValidate(ARRIVE_TIME_ATTRIBUTE, TIME_PATTERN,
                RESULT_ATTRIBUTE, ADMIN_DESTINATION_PAGE, badTimeMessage, request, response)
                || !CommandValidatorHelper.getInstance().matchesValidate(LEAVE_TIME_ATTRIBUTE, TIME_PATTERN,
                        RESULT_ATTRIBUTE, ADMIN_DESTINATION_PAGE, badTimeMessage, request, response)) {
            return false;
        }
        return true;
    }

}

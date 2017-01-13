package commands.validators.plan;

import commands.validators.CommandValidator;
import commands.validators.CommandValidatorHelper;
import extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static commands.plan.PlanCommand.*;

public class AddPlanForStopCommandValidator implements CommandValidator {

    private static final String STOP_OR_SCHEDULE_ERROR_MSG = "StopNotSelected";
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

        String stopErrorMessage = Localization.getInstanse().getLocalizedMessage(request, STOP_OR_SCHEDULE_ERROR_MSG);
        String noTimeMessage = Localization.getInstanse().getLocalizedMessage(request, TIME_ERROR_MSG);
        String badTimeMessage = Localization.getInstanse().getLocalizedMessage(request, BAD_TIME_FORMAT_ERROR_MSG);
        
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

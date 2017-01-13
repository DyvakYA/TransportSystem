package commands.validators.plan;

import commands.validators.CommandValidator;
import commands.validators.CommandValidatorHelper;
import extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static commands.plan.PlanCommand.*;

public class CreatePlanUsingIntervalCommandValidator implements CommandValidator {

    private static final String PLAN_OR_TRANSPORT_ERROR_MSG = "TransportForScheduleNotSelected";
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

        
        String badScheduleMessage = Localization.getInstanse().getLocalizedMessage(request, PLAN_OR_TRANSPORT_ERROR_MSG);
        String emptyIntervalMessage = Localization.getInstanse().getLocalizedMessage(request, INTERVAL_ERROR_MSG);
        String badIntervalMessage = Localization.getInstanse().getLocalizedMessage(request, BAD_INTERVAL_ERROR_MSG);
        
        if (!CommandValidatorHelper.getInstance().isNullValidate(new String[]{TRANSPORT_ID_ATTRIBUTE, SCHEDULE_ID_ATTRIBUTE, ROUTE_ID_ATTRIBUTE},
                RESULT_ATTRIBUTE, ADMIN_DESTINATION_PAGE, badScheduleMessage, request, response)) {
            return false;
        } else if (!CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{INTERVAL_ATTRIBUTE},
                RESULT_ATTRIBUTE, ADMIN_DESTINATION_PAGE, emptyIntervalMessage, request, response)) {
            return false;
        } else if (!CommandValidatorHelper.getInstance().matchesValidate(INTERVAL_ATTRIBUTE, TIME_PATTERN,
                RESULT_ATTRIBUTE, ADMIN_DESTINATION_PAGE, badIntervalMessage, request, response)) {
            return false;
        }
        return true;
    }

}

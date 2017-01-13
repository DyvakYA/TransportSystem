package commands.plan;

import commands.validators.plan.CreatePlanUsingIntervalCommandValidator;
import dao.DaoFactory;
import dao.PlanDao;
import dao.PlanOfStopsDao;
import dao.jdbc.JdbcDaoFactory;
import entities.Plan;
import entities.PlanOfStops;
import extras.Localization;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static servlet.TransportServlet.LOGGER_NAME;

public class CreatePlanUsingIntervalCommand implements PlanCommand {

    private static final String TIME_FORMAT = "HH:mm";
    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String SCHEDULE_BY_INTERVAL_CREATED_SUCCESSFUL_MSG = "ScheduleByIntervalCreatedSuccessful";
    private static final String STRING_TO_DATE_PARSE_EXCEPTION = "StringToDateParseException";
    private static final String PLUS = "+";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new CreatePlanUsingIntervalCommandValidator().validate(request, response)) {
            return;
        }

        DaoFactory daoFactory = JdbcDaoFactory.getInstance();

        PlanOfStopsDao planOfStopsDao = daoFactory.createPlanOfStopsDao();
        PlanDao planDao = daoFactory.createPlanDao();

        List<PlanOfStops> scheduleOfStopList = planOfStopsDao.findPlanOfStopsForPlan(
                new Plan(Integer.parseInt(request.getParameter(SCHEDULE_ID_ATTRIBUTE)), null, null));
        int scheduleId = planDao.createAndGetGeneratedKey(new Plan(null,
                Integer.parseInt(request.getParameter(ROUTE_ID_ATTRIBUTE)),
                Integer.parseInt(request.getParameter(TRANSPORT_ID_ATTRIBUTE))));

        for (PlanOfStops planOfStops : scheduleOfStopList) {
            String newArriveTime = addInterval(planOfStops.getArriveTime(),
                    request.getParameter(INTERVAL_ATTRIBUTE),
                    request.getParameter(INTERVAL_SIGN_ATTRIBUTE).equals(PLUS), request);

            String newLeaveTime = addInterval(planOfStops.getLeaveTime(),
                    request.getParameter(INTERVAL_ATTRIBUTE),
                    request.getParameter(INTERVAL_SIGN_ATTRIBUTE).equals(PLUS), request);

            planOfStopsDao.create(new PlanOfStops(null,
                    planOfStops.getStopId(), scheduleId, newArriveTime, newLeaveTime));
        }

        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, SCHEDULE_BY_INTERVAL_CREATED_SUCCESSFUL_MSG));
        try {
            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(request,
                    response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }

    private String addInterval(String time, String interval, boolean sign, HttpServletRequest request) {

        String result = null;

        try {
            SimpleDateFormat df = new SimpleDateFormat(TIME_FORMAT);

            Date date = df.parse(time);
            Date intervalDate = df.parse(interval);

            Calendar timeCalendar = Calendar.getInstance();
            timeCalendar.setTime(date);

            Calendar intervalCalendar = Calendar.getInstance();
            intervalCalendar.setTime(intervalDate);

            if (sign) {
                timeCalendar.add(Calendar.MINUTE, intervalCalendar.get(Calendar.MINUTE));
                timeCalendar.add(Calendar.HOUR, intervalCalendar.get(Calendar.HOUR));
            } else {
                timeCalendar.add(Calendar.MINUTE, -intervalCalendar.get(Calendar.MINUTE));
                timeCalendar.add(Calendar.HOUR, -intervalCalendar.get(Calendar.HOUR));
            }

            result = df.format(timeCalendar.getTime());
        } catch (ParseException ex) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(STRING_TO_DATE_PARSE_EXCEPTION), ex);
        }
        return result;
    }

}

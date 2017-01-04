/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.epam.transport.commands.schedule;

import org.apache.log4j.Logger;
import ua.kpi.epam.transport.dao.DaoFactory;
import ua.kpi.epam.transport.dao.ScheduleDao;
import ua.kpi.epam.transport.dao.ScheduleOfStopDao;
import ua.kpi.epam.transport.dao.jdbc.JdbcDaoFactory;
import ua.kpi.epam.transport.entities.Schedule;
import ua.kpi.epam.transport.entities.ScheduleOfStop;
import ua.kpi.epam.transport.extras.LocalizationHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static ua.kpi.epam.transport.commands.schedule.PlanCommand.ADMIN_DESTINATION_PAGE;
import static ua.kpi.epam.transport.servlets.TransportServlet.LOGGER_NAME;

/**
 *
 * @author KIRIL
 */
public class CreatePlanUsingIntervalCommand implements ScheduleCommand {

    private static final String TIME_FORMAT = "HH:mm";
    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String SCHEDULE_BY_INTERVAL_CREATED_SUCCSEFULL_MSG = "ScheduleByIntervalCreatedSuccsefull";
    private static final String STRING_TO_DATE_PARSE_EXCEPTION = "StringToDateParseException";
    private static final String PLUS = "+";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new ua.kpi.epam.transport.commands.validators.schedule.CreatePlanUsingIntervalCommandValidator().validate(request, response)) {
            return;
        }

        DaoFactory daoFactory = JdbcDaoFactory.getInstance();

        ScheduleOfStopDao scheduleOfStopDao = daoFactory.createScheduleOfStopDao();
        ScheduleDao scheduleDao = daoFactory.createScheduleDao();

        List<ScheduleOfStop> scheduleOfStopList = scheduleOfStopDao.findScheduleOfStopsForSchedule(
                new Schedule(Integer.parseInt(request.getParameter(SCHEDULE_ID_ATTRIBUTE)), null, null));
        int scheduleId = scheduleDao.createAndGetGenratedKey(new Schedule(null,
                Integer.parseInt(request.getParameter(ROUTE_ID_ATTRIBUTE)),
                Integer.parseInt(request.getParameter(TRANSPORT_ID_ATTRIBUTE))));

        for (ScheduleOfStop scheduleOfStop : scheduleOfStopList) {
            String newArriveTime = addInterval(scheduleOfStop.getArriveTime(),
                    request.getParameter(INTERVAL_ATTRIBUTE),
                    request.getParameter(INTERVAL_SIGN_ATTRIBUTE).equals(PLUS), request);

            String newLeaveTime = addInterval(scheduleOfStop.getLeaveTime(),
                    request.getParameter(INTERVAL_ATTRIBUTE),
                    request.getParameter(INTERVAL_SIGN_ATTRIBUTE).equals(PLUS), request);

            scheduleOfStopDao.create(new ScheduleOfStop(null,
                    scheduleOfStop.getStopId(), scheduleId, newArriveTime, newLeaveTime));
        }

        request.setAttribute(RESULT_ATTRIBUTE, LocalizationHelper.getInstanse()
                .getLocalizedMessage(request, SCHEDULE_BY_INTERVAL_CREATED_SUCCSEFULL_MSG));
        try {
            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(request,
                    response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }

    private String addInterval(String time, String interval, boolean sign, HttpServletRequest request) {

        String result = null;

        try {
            SimpleDateFormat df = new SimpleDateFormat(TIME_FORMAT);

            Date date = df.parse(time);
            Date intervalDate = df.parse(interval);

            Calendar timeCal = Calendar.getInstance();
            timeCal.setTime(date);

            Calendar intervCal = Calendar.getInstance();
            intervCal.setTime(intervalDate);

            if (sign) {
                timeCal.add(Calendar.MINUTE, intervCal.get(Calendar.MINUTE));
                timeCal.add(Calendar.HOUR, intervCal.get(Calendar.HOUR));
            } else {
                timeCal.add(Calendar.MINUTE, -intervCal.get(Calendar.MINUTE));
                timeCal.add(Calendar.HOUR, -intervCal.get(Calendar.HOUR));
            }

            result = df.format(timeCal.getTime());
        } catch (ParseException ex) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(STRING_TO_DATE_PARSE_EXCEPTION), ex);
        }
        return result;
    }

}

package ua.kpi.epam.transport.commands.schedule;

import org.apache.log4j.Logger;
import ua.kpi.epam.transport.dao.ScheduleOfStopDao;
import ua.kpi.epam.transport.dao.jdbc.JdbcDaoFactory;
import ua.kpi.epam.transport.entities.ScheduleOfStop;
import ua.kpi.epam.transport.extras.LocalizationHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.kpi.epam.transport.servlets.TransportServlet.LOGGER_NAME;

/**
 *
 * @author KIRIL
 */
public class AddPlanForStopsCommand implements ScheduleCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String SCHEDULE_OF_STOP_ADD_SUCCESFULL_MSG = "ScheduleOfStopAddSuccesfull";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new ua.kpi.epam.transport.commands.validators.schedule.AddPlanForStopCommandValidator().validate(request, response)) {
            return;
        }
        ScheduleOfStopDao scheduleStopDao = JdbcDaoFactory.getInstance()
                .createScheduleOfStopDao();
        ScheduleOfStop scheduleOfStop = new ScheduleOfStop(null, Integer
                .parseInt(request.getParameter(STOP_ID_ATTRIBUTE)), Integer
                .parseInt(request.getParameter(SCHEDULE_ID_ATTRIBUTE)),
                request.getParameter(ARRIVE_TIME_ATTRIBUTE),
                request.getParameter(LEAVE_TIME_ATTRIBUTE));

        ScheduleOfStop scheduleOfStopToUpdate = scheduleStopDao.find(scheduleOfStop.getScheduleId(), scheduleOfStop.getStopId());

        if (scheduleOfStopToUpdate == null) {

            scheduleStopDao.create(scheduleOfStop);
        } else {
            scheduleStopDao.update(scheduleOfStopToUpdate);
        }
        request.setAttribute(RESULT_ATTRIBUTE, LocalizationHelper.getInstanse()
                .getLocalizedMessage(request, SCHEDULE_OF_STOP_ADD_SUCCESFULL_MSG));

        try {
            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }

}

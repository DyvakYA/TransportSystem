package ua.kpi.epam.transport.commands.schedule;

import org.apache.log4j.Logger;
import ua.kpi.epam.transport.commands.validators.schedule.CreateScheduleCommandValidator;
import ua.kpi.epam.transport.dao.ScheduleDao;
import ua.kpi.epam.transport.dao.jdbc.JdbcDaoFactory;
import ua.kpi.epam.transport.entities.Schedule;
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
public class CreateScheduleCommand implements ScheduleCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String SCHEDULE_CREATED_SUCCESFUL_MSG = "ScheduleCreatedSuccesful";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new CreateScheduleCommandValidator().validate(request, response)) {
            return;
        }
        ScheduleDao scheduleDao = JdbcDaoFactory.getInstance()
                .createScheduleDao();

        scheduleDao.create(new Schedule(null, Integer.parseInt(request
                .getParameter(ROUTE_ID_ATTRIBUTE)), Integer.parseInt(request
                        .getParameter(TRANSPORT_ID_ATTRIBUTE))));

        request.setAttribute(RESULT_ATTRIBUTE, LocalizationHelper.getInstanse()
                .getLocalizedMessage(request, SCHEDULE_CREATED_SUCCESFUL_MSG));

        try {
            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(
                    request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }
}

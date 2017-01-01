package ua.kpi.epam.transport.commands.schedule;

import org.apache.log4j.Logger;
import ua.kpi.epam.transport.dao.DaoFactory;
import ua.kpi.epam.transport.dao.ScheduleDao;
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
public class DeleteScheduleCommand implements ScheduleCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String DELETE_SCHEDULE_SUCCESSFULL_MSG = "DeleteScheduleSuccessfull";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        ScheduleDao scheduleDao = DaoFactory.getInstance().createScheduleDao();

        scheduleDao.deleteFully(Integer.parseInt(request
                .getParameter(SCHEDULE_ID_ATTRIBUTE)));

        request.setAttribute(RESULT_ATTRIBUTE, LocalizationHelper.getInstanse()
                .getLocalizedMessage(request, DELETE_SCHEDULE_SUCCESSFULL_MSG));
        try {
            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(
                    request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }

}

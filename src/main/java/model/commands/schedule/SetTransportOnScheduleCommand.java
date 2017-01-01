/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.epam.transport.commands.schedule;

import org.apache.log4j.Logger;
import ua.kpi.epam.transport.commands.validators.schedule.SetTransportOnScheduleCommandValidator;
import ua.kpi.epam.transport.dao.DaoFactory;
import ua.kpi.epam.transport.dao.ScheduleDao;
import ua.kpi.epam.transport.entities.Schedule;
import ua.kpi.epam.transport.extras.LocalizationHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.kpi.epam.transport.commands.schedule.ScheduleCommand.*;
import static ua.kpi.epam.transport.servlets.TransportServlet.LOGGER_NAME;

/**
 *
 * @author KIRIL
 */
public class SetTransportOnScheduleCommand implements ScheduleCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String UPDATE_SCHEDULE_TRANSPORT_SUCCESFULL_MSG = "UpdateScheduleTransportSuccesfull";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new SetTransportOnScheduleCommandValidator().validate(request, response)) {
            return;
        }

        ScheduleDao scheduleDao = DaoFactory.getInstance().createScheduleDao();

        scheduleDao.updateTransport(new Schedule(Integer.parseInt(request.getParameter(SCHEDULE_ID_ATTRIBUTE)), null,
                Integer.parseInt(request.getParameter(TRANSPORT_ID_ATTRIBUTE))));

        request.setAttribute(RESULT_ATTRIBUTE, LocalizationHelper.getInstanse()
                .getLocalizedMessage(request, UPDATE_SCHEDULE_TRANSPORT_SUCCESFULL_MSG));
        try {
            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(
                    request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.epam.transport.commands.schedule;

import org.apache.log4j.Logger;
import ua.kpi.epam.transport.dao.DaoFactory;
import ua.kpi.epam.transport.dao.TransportDao;
import ua.kpi.epam.transport.entities.Schedule;
import ua.kpi.epam.transport.entities.Transport;
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
public class ShowTransportOnScheduleCommand implements ScheduleCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        DaoFactory daoFactory = DaoFactory.getInstance();
        TransportDao transportDao = daoFactory.createTransportDao();

        Transport transport = transportDao.findTransportOnSchedule(
                new Schedule(Integer.parseInt(request.getParameter(SCHEDULE_ID_ATTRIBUTE)), null, null));

        request.setAttribute(RESULT_TRANSPORT_ATTRIBUTE, transport);

        request.setAttribute(TRANSPORT_LIST_ATTRIBUTE, DaoFactory.getInstance()
                .createTransportDao().findAll());
        try {
            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(request,
                    response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }
}

package ua.kpi.epam.transport.commands.stop;

import org.apache.log4j.Logger;
import ua.kpi.epam.transport.commands.validators.stop.CreateStopCommandValidator;
import ua.kpi.epam.transport.dao.DaoFactory;
import ua.kpi.epam.transport.dao.StopDao;
import ua.kpi.epam.transport.entities.Stop;
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
public class CreateStopCommand implements StopCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String CREATE_STOP_SUCCESFULL_MSG = "CreateStopSuccesfull";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new CreateStopCommandValidator().validate(request, response)) {
            return;
        }
        StopDao stopDao = DaoFactory.getInstance().createStopDao();

        stopDao.create(new Stop(null, request.getParameter(NAME_ATTRIBUTE), request
                .getParameter(ADDRESS_ATTRIBUTE)));

        request.setAttribute(RESULT_ATTRIBUTE, LocalizationHelper.getInstanse()
                .getLocalizedMessage(request, CREATE_STOP_SUCCESFULL_MSG));
        try {
            request.getRequestDispatcher(DESTINATION_PAGE).forward(request,
                    response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }

    }

}

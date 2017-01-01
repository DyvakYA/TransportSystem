package ua.kpi.epam.transport.commands.transport;

import org.apache.log4j.Logger;
import ua.kpi.epam.transport.commands.validators.transport.DeleteTransportCommandValidator;
import ua.kpi.epam.transport.dao.DaoFactory;
import ua.kpi.epam.transport.dao.TransportDao;
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
public class DeleteTransportCommand implements TransportCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String DELETE_TRANSPORT_SUCCESSFULL_MSG = "DeleteTransportSuccessfull";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new DeleteTransportCommandValidator().validate(request, response)) {
            return;
        }
        TransportDao transportDao = DaoFactory.getInstance()
                .createTransportDao();

        transportDao.delete(Integer.valueOf(request.getParameter(ID_ATTRIBUTE)));

        request.setAttribute(RESULT_ATTRIBUTE, LocalizationHelper.getInstanse()
                .getLocalizedMessage(request, DELETE_TRANSPORT_SUCCESSFULL_MSG));
        try {
            request.getRequestDispatcher(DESTINATION_ADMIN_PAGE).forward(request,
                    response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }

    }

}

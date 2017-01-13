package commands.transport;

import entities.Transport;
import org.apache.log4j.Logger;
import commands.validators.transport.CreateTransportCommandValidator;
import dao.DaoFactory;
import dao.TransportDao;
import entities.enums.TransportType;
import extras.Localization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlet.TransportServlet.LOGGER_NAME;

public class CreateTransportCommand implements TransportCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String CREATE_TRANSPORT_SUCCESSFUL_MSG = "CreateTransportSuccessful";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new CreateTransportCommandValidator().validate(request, response)) {
            return;
        }
        TransportDao transportDao = DaoFactory.getInstance()
                .createTransportDao();

        Transport transport = new Transport(
                TransportType.valueOf(request.getParameter(TYPE_ATTRIBUTE).toUpperCase()),
                request.getParameter(MODEL_ATTRIBUTE),
                String.valueOf(request.getParameter(NUMBER_ATTRIBUTE)));

        transportDao.create(transport);

        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, CREATE_TRANSPORT_SUCCESSFUL_MSG));
        try {
            request.getRequestDispatcher(DESTINATION_ADMIN_PAGE).forward(request,
                    response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }
}

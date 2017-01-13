package commands.stop;

import org.apache.log4j.Logger;
import commands.validators.stop.CreateStopCommandValidator;
import dao.DaoFactory;
import dao.StopDao;
import entities.Stop;
import extras.Localization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlet.TransportServlet.LOGGER_NAME;

public class CreateStopCommand implements StopCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String CREATE_STOP_Successful_MSG = "CreateStopSuccessful";

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

        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, CREATE_STOP_Successful_MSG));
        try {
            request.getRequestDispatcher(DESTINATION_PAGE).forward(request,
                    response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }

    }

}

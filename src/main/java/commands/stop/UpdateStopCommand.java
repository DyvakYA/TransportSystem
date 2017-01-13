package commands.stop;

import commands.validators.stop.UpdateStopCommandValidator;
import dao.DaoFactory;
import dao.StopDao;
import extras.Localization;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlet.TransportServlet.LOGGER_NAME;


public class UpdateStopCommand implements StopCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String UPDATE_STOP_SUCCESSFUL_MSG = "UpdateStopSuccessful";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new UpdateStopCommandValidator().validate(request, response)) {
            return;
        }
        StopDao stopDao = DaoFactory.getInstance().createStopDao();

//        stopDao.update(new Stop(Integer.valueOf(
//                request.getParameter(ID_ATTRIBUTE)),
//                request.getParameter(NAME_ATTRIBUTE),
//                request.getParameter(ADDRESS_ATTRIBUTE)));

        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, UPDATE_STOP_SUCCESSFUL_MSG));
        try {
            request.getRequestDispatcher(DESTINATION_PAGE).forward(request,
                    response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }

    }

}

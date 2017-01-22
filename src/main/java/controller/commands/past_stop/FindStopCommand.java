package commands.stop;

import org.apache.log4j.Logger;
import dao.DaoFactory;
import dao.StopDao;
import entities.Stop;
import extras.Localization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlet.TransportServlet.LOGGER_NAME;

public class FindStopCommand implements StopCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        StopDao stopDao = DaoFactory.getInstance().createStopDao();

        Stop stop = stopDao.findById(Integer.valueOf(request
                .getParameter(ID_ATTRIBUTE)));

        request.setAttribute(RESULT_STOP_ATTRIBUTE, stop);
        try {
            request.getRequestDispatcher(DESTINATION_PAGE).forward(request,
                    response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }

    }

}

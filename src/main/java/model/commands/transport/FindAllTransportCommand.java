package model.commands.transport;

import model.extras.Localization;
import org.apache.log4j.Logger;
import model.dao.DaoFactory;
import model.dao.TransportDao;
import model.entities.Transport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static model.servlet.TransportServlet.LOGGER_NAME;

/**
 *
 * @author KIRIL
 */
public class FindAllTransportCommand implements TransportCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        TransportDao transportDao = DaoFactory.getInstance()
                .createTransportDao();

        List<Transport> transport = transportDao.findAll();

        request.setAttribute(RESULT_LIST_ATTRIBUTE, transport);
        try {
            request.getRequestDispatcher(DESTINATION_PAGE).forward(request,
                    response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }

    }

}

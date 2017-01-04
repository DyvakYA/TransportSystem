package model.commands.transport;

import org.apache.log4j.Logger;
import model.dao.DaoFactory;
import model.dao.TransportDao;
import model.entities.Transport;
import model.extras.Localization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.servlet.TransportServlet.LOGGER_NAME;

public class FindTransportCommand implements TransportCommand {

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

        Transport transport = transportDao.findById(Integer.valueOf(request
                .getParameter(ID_ATTRIBUTE)));

        request.setAttribute(TRANSPORT_RESULT_ATTRIBUTE, transport);
        try {
            request.getRequestDispatcher(DESTINATION_ADMIN_PAGE).forward(request,
                    response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }

    }

}

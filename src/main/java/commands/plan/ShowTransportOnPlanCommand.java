package commands.plan;

import dao.DaoFactory;
import dao.TransportDao;
import extras.Localization;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlet.TransportServlet.LOGGER_NAME;


public class ShowTransportOnPlanCommand implements PlanCommand {

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

//        Transport transport = transportDao.findTransportOnPlan(
//                new Plan(Integer.parseInt(request.getParameter(SCHEDULE_ID_ATTRIBUTE)), null, null));

//        request.setAttribute(RESULT_TRANSPORT_ATTRIBUTE, transport);

        request.setAttribute(TRANSPORT_LIST_ATTRIBUTE, DaoFactory.getInstance()
                .createTransportDao().findAll());
        try {
            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(request,
                    response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }
}

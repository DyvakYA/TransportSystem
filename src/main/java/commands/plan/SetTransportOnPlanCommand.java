package commands.plan;

import commands.validators.plan.SetTransportOnPlanCommandValidator;
import dao.DaoFactory;
import dao.PlanDao;
import extras.Localization;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlet.TransportServlet.LOGGER_NAME;

public class SetTransportOnPlanCommand implements PlanCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String UPDATE_SCHEDULE_TRANSPORT_Successful_MSG = "UpdateScheduleTransportSuccessful";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new SetTransportOnPlanCommandValidator().validate(request, response)) {
            return;
        }

        PlanDao planDao = DaoFactory.getInstance().createPlanDao();

//        planDao.updateTransport(new Plan(
//                Integer.parseInt(request.getParameter(SCHEDULE_ID_ATTRIBUTE)),
//                null,
//                Integer.parseInt(request.getParameter(TRANSPORT_ID_ATTRIBUTE))));

        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, UPDATE_SCHEDULE_TRANSPORT_Successful_MSG));
        try {
            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(
                    request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }

}

package commands.plan;

import commands.validators.plan.SetTransportOnPlanCommandValidator;
import dao.DaoFactory;
import dao.PlanDao;
import entities.Plan;
import extras.Localization;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlet.TransportServlet.LOGGER_NAME;

public class SetTransportOnPlanCommand implements PlanCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String UPDATE_PLAN_TRANSPORT_SUCCESSFUL = "UpdatePlanTransportSuccessful";

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

        planDao.update(new Plan(
                null,
                Integer.parseInt(request.getParameter(ROUTE_ID_ATTRIBUTE)),
                Integer.parseInt(request.getParameter(TRANSPORT_ID_ATTRIBUTE))), Integer.parseInt(request.getParameter(PLAN_ID_ATTRIBUTE)));

        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, UPDATE_PLAN_TRANSPORT_SUCCESSFUL));
        try {
            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(
                    request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }

}

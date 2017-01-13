package commands.plan;

import commands.validators.plan.CreatePlanCommandValidator;
import dao.PlanDao;
import dao.jdbc.JdbcDaoFactory;
import entities.Plan;
import extras.Localization;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlet.TransportServlet.LOGGER_NAME;

public class CreatePlanCommand implements PlanCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String SCHEDULE_CREATED_SUCCESSFUL_MSG = "ScheduleCreatedSuccessful";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new CreatePlanCommandValidator().validate(request, response)) {
            return;
        }
        PlanDao scheduleDao = JdbcDaoFactory.getInstance()
                .createPlanDao();

        scheduleDao.create(new Plan(null, Integer.parseInt(request
                .getParameter(ROUTE_ID_ATTRIBUTE)), Integer.parseInt(request
                        .getParameter(TRANSPORT_ID_ATTRIBUTE))));

        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, SCHEDULE_CREATED_SUCCESSFUL_MSG));

        try {
            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(
                    request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }
}

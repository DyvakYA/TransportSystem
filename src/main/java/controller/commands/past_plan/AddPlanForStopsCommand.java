package commands.plan;

import commands.validators.plan.AddPlanForStopCommandValidator;
import dao.PlanOfStopsDao;
import dao.jdbc.JdbcDaoFactory;
import entities.PlanOfStops;
import extras.Localization;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlet.TransportServlet.LOGGER_NAME;

public class AddPlanForStopsCommand implements PlanCommand {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String PLAN_OF_STOP_ADD_SUCCESSFUL = "PlanOfStopAddSuccessful";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new AddPlanForStopCommandValidator().validate(request, response)) {
            return;
        }
        PlanOfStopsDao planOfStopsDao = JdbcDaoFactory.getInstance()
                .createPlanOfStopsDao();
        PlanOfStops planOfStops = new PlanOfStops(null,
                Integer.parseInt(request.getParameter(STOP_ID_ATTRIBUTE)),
                Integer.parseInt(request.getParameter(PLAN_ID_ATTRIBUTE)),
                request.getParameter(ARRIVE_TIME_ATTRIBUTE),
                request.getParameter(LEAVE_TIME_ATTRIBUTE));

        PlanOfStops planOfStopsToUpdate = planOfStopsDao.findById(planOfStops.getPlanId(), planOfStops.getStopId());

        if (planOfStopsToUpdate == null) {

            planOfStopsDao.create(planOfStops);
        } else {
            planOfStopsDao.update(planOfStopsToUpdate, planOfStops.getPlanId());
        }

        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, PLAN_OF_STOP_ADD_SUCCESSFUL));

        try {
            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }

}

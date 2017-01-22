package controller.commands.plan;

import controller.commands.Command;
import controller.commands.validators.plan.CreatePlanCommandValidator;
import model.extras.Localization;
import model.entities.Plan;
import model.services.PlanService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreatePlanCommand implements PlanCommand {

    private PlanService planService = PlanService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!new CreatePlanCommandValidator().validate(request, response)) {
            return "";
        }

        Plan plan = new Plan(
                Integer.parseInt(request.getParameter(TRANSPORT_ID_ATTRIBUTE)),
                Integer.parseInt(request.getParameter(ROUTE_ID_ATTRIBUTE)));

        planService.createPlan(plan);

        request.setAttribute(Command.RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, CREATE_PLAN_SUCCESSFUL_MSG));

            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(request,
                    response);

        return CREATE_PLAN_SUCCESSFUL_MSG;
    }
}

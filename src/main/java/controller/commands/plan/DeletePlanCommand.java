package controller.commands.plan;

import controller.commands.Command;
import model.extras.Localization;
import model.services.PlanService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeletePlanCommand implements PlanCommand {

    private PlanService planService = PlanService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        planService.deletePlan(
                Integer.parseInt(request
                .getParameter(PLAN_ID_ATTRIBUTE)));

        request.setAttribute(Command.RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, DELETE_PLAN_SUCCESSFUL_MSG));

            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(
                    request, response);

        return DELETE_PLAN_SUCCESSFUL_MSG;
    }

}

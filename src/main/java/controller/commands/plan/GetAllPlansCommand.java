package controller.commands.plan;

import controller.commands.Command;
import model.entities.Plan;
import model.services.PlanService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Dyvak on 21.01.2017.
 */
public class GetAllPlansCommand implements Command {

    private PlanService planService = PlanService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Plan> plans = planService.getAllPlans();
        request.setAttribute("plansList", plans);
        return "/WEB-INF/admin/plans.jspx";
    }
}

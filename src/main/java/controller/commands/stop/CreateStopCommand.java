package controller.commands.stop;

import controller.commands.validators.stop.CreateStopCommandValidator;
import model.extras.Localization;
import model.entities.Stop;
import model.services.service.StopService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateStopCommand implements StopCommand {

    private StopService stopService = StopService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        if (!new CreateStopCommandValidator().validate(request, response)) {
            return "";
        }
        Stop stop = new Stop(request.getParameter(NAME_ATTRIBUTE),
                request.getParameter(ADDRESS_ATTRIBUTE));
        stopService.create(stop);
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, CREATE_STOP_SUCCESSFUL_MSG ));
        request.setAttribute(STOP_LIST_ATTRIBUTE, stopService.getAll());
        return "/WEB-INF/admin/stops.jsp";
    }
}

package controller.commands.user;

import controller.commands.stop.StopCommand;
import controller.commands.validators.stop.DeleteStopCommandValidator;
import model.extras.Localization;
import model.services.service.StopService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteUserCommand implements StopCommand {

    private StopService stopService = StopService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        if (!new DeleteStopCommandValidator().validate(request, response)) {
            return null;
        }
        int stopId = Integer.valueOf(request.getParameter(ID_ATTRIBUTE));
        stopService.delete(stopId);
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
            .getLocalizedMessage(request, DELETE_STOP_SUCCESSFUL_MSG));
        request.setAttribute(STOP_LIST_ATTRIBUTE, stopService.getAll());
        return "/WEB-INF/admin/stops.jsp";

    }

}

package controller.commands.stop;

import controller.commands.validators.stop.DeleteStopCommandValidator;
import model.extras.Localization;
import model.services.StopService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteStopCommand implements StopCommand {

    private StopService stopService = StopService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!new DeleteStopCommandValidator().validate(request, response)) {
            return "";
        }

        int stopId = Integer.valueOf(request.getParameter(ID_ATTRIBUTE));

        stopService.deleteStop(stopId);

        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, DELETE_STOP_SUCCESSFUL_MSG));

            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(request,
                    response);

        return DELETE_STOP_SUCCESSFUL_MSG;

    }

}

package controller.commands.stop;

import controller.commands.Command;
import controller.commands.validators.stop.UpdateStopCommandValidator;
import model.extras.Localization;
import model.entities.Stop;
import model.services.StopService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UpdateStopCommand implements StopCommand {

    private StopService stopService = StopService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!new UpdateStopCommandValidator().validate(request, response)) {
            return "";
        }

        Stop stop = new Stop(Integer.parseInt(request.getParameter(ID_ATTRIBUTE)),
                request.getParameter(Command.NAME_ATTRIBUTE),
                request.getParameter(Command.ADDRESS_ATTRIBUTE));

        stopService.updateStop(stop, stop.getId());

        request.setAttribute(Command.RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, UPDATE_STOP_SUCCESSFUL_MSG));

            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(request,
                    response);

        return UPDATE_STOP_SUCCESSFUL_MSG;
    }

}
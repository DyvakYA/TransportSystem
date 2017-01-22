package controller.commands.stop;

import controller.commands.validators.stop.CreateStopCommandValidator;
import model.extras.Localization;
import model.entities.Stop;
import model.services.StopService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateStopCommand implements StopCommand {

    private StopService stopService = StopService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!new CreateStopCommandValidator().validate(request, response)) {
            return "";
        }

        Stop stop = new Stop(request.getParameter(NAME_ATTRIBUTE),
                request.getParameter(ADDRESS_ATTRIBUTE));

        stopService.createStop(stop);

        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, CREATE_STOP_SUCCESSFUL_MSG ));

            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(request,
                    response);

        return CREATE_STOP_SUCCESSFUL_MSG;
    }
}

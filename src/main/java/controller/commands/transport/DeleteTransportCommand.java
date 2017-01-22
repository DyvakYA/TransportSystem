package controller.commands.transport;

import controller.commands.validators.transport.DeleteTransportCommandValidator;
import model.extras.Localization;
import model.services.TransportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTransportCommand implements TransportCommand {

    private TransportService transportService = TransportService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!new DeleteTransportCommandValidator().validate(request, response)) {
            return "";
        }

        int transportId = Integer.parseInt(request.getParameter(ID_ATTRIBUTE));

        transportService.deleteTransport(transportId);

        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, DELETE_TRANSPORT_SUCCESSFUL_MSG));

            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(request,
                    response);

        return DELETE_TRANSPORT_SUCCESSFUL_MSG;

    }

}

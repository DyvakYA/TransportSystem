package controller.commands.transport;

import controller.commands.validators.transport.CreateTransportCommandValidator;
import model.extras.Localization;
import model.entities.Transport;
import model.entities.enums.TransportType;
import model.services.TransportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateTransportCommand implements TransportCommand {

    private TransportService transportService = TransportService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!new CreateTransportCommandValidator().validate(request, response)) {
            return "";
        }

        Transport transport = new Transport(
                TransportType.valueOf(request.getParameter(TYPE_ATTRIBUTE).toUpperCase()),
                request.getParameter(MODEL_ATTRIBUTE),
                request.getParameter(NUMBER_ATTRIBUTE));

        transportService.createTransport(transport);

        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, CREATE_TRANSPORT_SUCCESSFUL_MSG));

            request.getRequestDispatcher(ADMIN_DESTINATION_PAGE).forward(request,
                    response);

        return CREATE_TRANSPORT_SUCCESSFUL_MSG;
    }
}

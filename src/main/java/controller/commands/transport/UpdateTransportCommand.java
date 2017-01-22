package controller.commands.transport;

import controller.commands.Command;
import controller.commands.validators.transport.UpdateTransportCommandValidator;
import model.extras.Localization;
import model.entities.Transport;
import model.entities.enums.TransportType;
import model.services.TransportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateTransportCommand implements TransportCommand {

    private TransportService transportService = TransportService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!new UpdateTransportCommandValidator().validate(request, response)) {
            return null;
        }
        Transport transport = new Transport(Integer.valueOf(request.getParameter(ID_ATTRIBUTE)),
                TransportType.valueOf(request.getParameter(TYPE_ATTRIBUTE).toUpperCase()),
                request.getParameter(MODEL_ATTRIBUTE),
                String.valueOf(request.getParameter(NUMBER_ATTRIBUTE)));
        transportService.updateTransport(transport, transport.getId());
        request.setAttribute(Command.RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, UPDATE_TRANSPORT_SUCCESSFUL_MSG));
        request.setAttribute(TRANSPORT_LIST_ATTRIBUTE, transportService.getAllTransports());
        return "/WEB-INF/admin/transports.jspx";
    }
}

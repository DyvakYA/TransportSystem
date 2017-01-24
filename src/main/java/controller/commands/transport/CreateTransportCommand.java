package controller.commands.transport;

import controller.commands.validators.transport.CreateTransportCommandValidator;
import model.entities.Transport;
import model.entities.enums.TransportType;
import model.extras.Localization;
import model.services.service.TransportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateTransportCommand implements TransportCommand {

    private TransportService transportService = TransportService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        if (!new CreateTransportCommandValidator().validate(request, response)) {
            return null;
        }
        Transport transport = new Transport(
                TransportType.valueOf(request.getParameter(TYPE_ATTRIBUTE).toUpperCase()),
                request.getParameter(MODEL_ATTRIBUTE),
                request.getParameter(NUMBER_ATTRIBUTE));
        transportService.create(transport);
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, CREATE_TRANSPORT_SUCCESSFUL_MSG));
        request.setAttribute(TRANSPORT_LIST_ATTRIBUTE, transportService.getAll());
        return "/WEB-INF/admin/transports.jspx";
    }
}

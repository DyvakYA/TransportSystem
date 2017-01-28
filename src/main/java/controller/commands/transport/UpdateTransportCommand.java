package controller.commands.transport;

import controller.commands.Command;
import controller.commands.validators.transport.UpdateTransportCommandValidator;
import model.extras.Localization;
import model.entities.Transport;
import model.entities.enums.TransportType;
import model.services.service.TransportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateTransportCommand implements TransportCommand {

    private TransportService transportService = TransportService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        if (!new UpdateTransportCommandValidator().validate(request, response)) {
            return null;
        }
        Transport transport = new Transport.Builder()
                .setId(Integer.parseInt(request.getParameter("transport_id")))
                .setType(TransportType.valueOf(request.getParameter("type")))
                .setModel(request.getParameter("model"))
                .setNumber(request.getParameter("number"))
                .build();
        transportService.update(transport, transport.getId());
        request.setAttribute(Command.RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, UPDATE_TRANSPORT_SUCCESSFUL_MSG));
        request.setAttribute(TRANSPORT_LIST_ATTRIBUTE, transportService.getAll());
        return "/WEB-INF/admin/transports.jsp";
    }
}

package controller.commands.transport;

import controller.commands.validators.transport.DeleteTransportCommandValidator;
import model.extras.Localization;
import model.services.service.TransportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteTransportCommand implements TransportCommand {

    private TransportService transportService = TransportService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        if (!new DeleteTransportCommandValidator().validate(request, response)) {
            return null;
        }
        int transportId = Integer.parseInt(request.getParameter(ID_ATTRIBUTE));
        transportService.delete(transportId);
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, DELETE_TRANSPORT_SUCCESSFUL_MSG));
        request.setAttribute(TRANSPORT_LIST_ATTRIBUTE, transportService.getAll());
        return "/WEB-INF/admin/transports.jsp";

    }

}

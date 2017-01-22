package controller.commands.transport;

import model.entities.Transport;
import model.services.TransportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Dyvak on 21.01.2017.
 */
public class GetAllTransportsCommand implements TransportCommand {

    private TransportService transportService = TransportService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Transport> transports = transportService.getAllTransports();
        request.setAttribute(TRANSPORT_LIST_ATTRIBUTE, transports);
        return "/WEB-INF/admin/transports.jspx";
    }
}

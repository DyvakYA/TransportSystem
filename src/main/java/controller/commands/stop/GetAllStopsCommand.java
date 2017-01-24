package controller.commands.stop;

import model.entities.Stop;
import model.services.service.StopService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 21.01.2017.
 */
public class GetAllStopsCommand implements StopCommand {

    private StopService stopService = StopService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Optional<Stop>> stops = stopService.getAll();
        request.setAttribute(STOP_LIST_ATTRIBUTE, stops);
        return "/WEB-INF/admin/stops.jspx";
    }
}

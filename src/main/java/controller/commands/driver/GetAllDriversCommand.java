package controller.commands.driver;

import model.entities.Driver;
import model.services.service.DriverService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GetAllDriversCommand implements DriverCommand {

	private DriverService driverService = DriverService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		List<Driver> drivers = driverService.getAll();
		request.setAttribute(DRIVER_LIST_ATTRIBUTE, drivers);
		return "/WEB-INF/admin/drivers.jsp";
	}

}

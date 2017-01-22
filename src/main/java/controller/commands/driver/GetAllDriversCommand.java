package controller.commands.driver;

import controller.commands.Command;
import model.entities.Driver;
import model.services.DriverService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllDriversCommand implements Command {

	private DriverService driverService = DriverService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Driver> drivers = driverService.getAllDrivers();
		request.setAttribute("driversList", drivers);
		return "/WEB-INF/admin/drivers.jspx";
	}

}

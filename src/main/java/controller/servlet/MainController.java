package controller.servlet;

import controller.commands.Command;
import controller.commands.driver.CreateDriverCommand;
import controller.commands.driver.DeleteDriverCommand;
import controller.commands.driver.GetAllDriversCommand;
import controller.commands.driver.UpdateDriverCommand;
import controller.commands.plan.GetAllPlansCommand;
import controller.commands.route.*;
import controller.commands.stop.CreateStopCommand;
import controller.commands.stop.DeleteStopCommand;
import controller.commands.stop.GetAllStopsCommand;
import controller.commands.stop.UpdateStopCommand;
import controller.commands.transport.CreateTransportCommand;
import controller.commands.transport.DeleteTransportCommand;
import controller.commands.transport.GetAllTransportsCommand;
import controller.commands.transport.UpdateTransportCommand;
import controller.commands.user.*;
import model.extras.Localization;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/rest/*")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
	private static final String SQL_EXCEPTION = "SQLException";
	public static final String LOGGER_NAME = "log4j";
    
	private Map<String , Command> commands = new HashMap<>();

    public MainController() {
        super();
    }

    @Override
    public void init(){

    	commands.put("GET:/user", new GetAllUsersCommand() );
    	commands.put("GET:/driver", new GetAllDriversCommand() );
    	commands.put("GET:/route", new GetAllRoutesCommand() );
    	commands.put("GET:/stop", new GetAllStopsCommand() );
    	commands.put("GET:/plan", new GetAllPlansCommand() );
    	commands.put("GET:/transport" , new GetAllTransportsCommand() );
    	commands.put("GET:/login",  new AuthenticateUserCommand());
    	commands.put("GET:/registration",  new RegisterUserCommand());

    	commands.put("GET:/find",  new FindAllStopOnRouteCommand());

    	commands.put("GET:/changeLocale",  new ChangeLocaleCommand());

		commands.put("POST:/createUser",  new CreateUserCommand());
		commands.put("POST:/updateUser",  new UpdateUserCommand());
		commands.put("POST:/deleteUser",  new DeleteUserCommand());
		commands.put("POST:/getAllUsers",  new GetAllUsersCommand());

    	commands.put("POST:/createDriver",  new CreateDriverCommand());
    	commands.put("POST:/updateDriver",  new UpdateDriverCommand());
    	commands.put("POST:/deleteDriver",  new DeleteDriverCommand());
    	commands.put("POST:/getAllDrivers",  new GetAllDriversCommand());

		commands.put("POST:/createRoute",  new CreateRouteCommand());
		commands.put("POST:/updateRoute",  new UpdateRouteCommand());
		commands.put("POST:/deleteRoute",  new DeleteRouteCommand());
		commands.put("POST:/getAllRoutes",  new GetAllRoutesCommand());

		commands.put("POST:/createTransport",  new CreateTransportCommand());
		commands.put("POST:/updateTransport",  new UpdateTransportCommand());
		commands.put("POST:/deleteTransport",  new DeleteTransportCommand());
		commands.put("POST:/getAllTransports",  new GetAllTransportsCommand());

		commands.put("POST:/createStop",  new CreateStopCommand());
		commands.put("POST:/updateStop",  new UpdateStopCommand());
		commands.put("POST:/deleteStop",  new DeleteStopCommand());
		commands.put("POST:/getAllStops",  new GetAllStopsCommand());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		processRequest(request , response);
	}
	
	void processRequest(HttpServletRequest request, HttpServletResponse response)  {


		String method = request.getMethod().toUpperCase();
		String path;
		if (method.equals("GET")) {
			path = request.getRequestURI();
			path = path.replaceAll(".*/rest/", "");

		}else{
			path = request.getParameter("command");
			}
		String key = method+":/"+path;
		System.out.println(key);
		Command command = commands.getOrDefault(key, (req , resp)->"/index.jsp" );
		System.out.println(command);
		System.out.println(path);
		String viewPage = null;
		try {
			viewPage = command.execute(request, response);
			request.getRequestDispatcher(viewPage)
					.forward(request, response);
		} catch (ServletException | IOException e) {
			Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
			logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
		} catch (SQLException e) {
			Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
			logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
		}
	}
}

package controller.servlet;

import controller.commands.*;
import controller.commands.driver.GetAllDriversCommand;
import controller.commands.plan.GetAllPlansCommand;
import controller.commands.route.GetAllRoutesCommand;
import controller.commands.stop.GetAllStopsCommand;
import controller.commands.transport.GetAllTransportsCommand;
import controller.commands.user.AuthenticateUserCommand;
import controller.commands.user.ChangeLocaleCommand;
import controller.commands.user.GetAllUsersCommand;
import model.extras.Localization;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/rest/*")
public class TransportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
	public static final String LOGGER_NAME = "log4j";
    
	private Map<String , Command> commands = new HashMap<>();

    public TransportServlet() {
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
    	commands.put("GET:/changeLocale",  new ChangeLocaleCommand());
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
		String path = request.getRequestURI();
		path = path.replaceAll(".*/rest", "");
		String key = method+":"+path;
		Command command = commands.getOrDefault(key, (req , resp)->"/index.jsp" );
		String viewPage = null;
		try {
			viewPage = command.execute(request, response);
			request.getRequestDispatcher(viewPage)
					.forward(request, response);
		} catch (ServletException | IOException e) {
			Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
			logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
		}

	}

}

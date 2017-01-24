package controller.commands.user;

import controller.commands.Command;
import model.entities.User;
import model.services.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class AuthenticateUserCommand implements Command {

	public static final String RESULT_PAGE = "/authentication.jsp";
	public static final String USER_SESSION_ATTRIBUTE = "user";

	public static final String PARAM_LOGIN = "login";
	public static final String PARAM_PASSWORD ="password";
	
	private UserService userService = UserService.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String pageToGo = "/index.jsp";
		String email = request.getParameter(PARAM_LOGIN);
		String password = request.getParameter(PARAM_PASSWORD);
		if( email != null && password != null ){
			Optional<User> user;
			user = userService.login(email, password);

			if( user.isPresent()  ){
				request.getSession().setAttribute("user", user.get());
			    pageToGo = "/rest/city";
			}
			
		}
		return pageToGo;
	}

}

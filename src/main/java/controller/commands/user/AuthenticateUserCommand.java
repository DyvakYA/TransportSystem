package controller.commands.user;

import model.entities.User;
import model.entities.enums.UserRoles;
import model.extras.Localization;
import model.services.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class AuthenticateUserCommand implements UserCommand {

	public static final String RESULT_PAGE = "/authentication.jsp";
	public static final String USER_SESSION_ATTRIBUTE = "user";

	public static final String PARAM_EMAIL = "email";
	public static final String PARAM_PASSWORD ="password";

	private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
	private static final String LOGIN_USER_ERROR_MSG = "AuthenticateUserError";
	private static final String LOGIN_USER_SUCCESSFUL_MSG = "AuthentifyUserSuccessful";
	
	private UserService userService = UserService.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String result = null;
		String pageToGo = "/index.jsp";
		String email = request.getParameter(PARAM_EMAIL);
		String password = request.getParameter(PARAM_PASSWORD);
		System.out.println(request.getSession().getAttributeNames());
		System.out.println(email + password);
		if( email != null && password != null ){
			Optional<User> user;
			user = userService.login(email, password);
			if( user.isPresent()  ){
				result = Localization.getInstanse().getLocalizedMessage(request, LOGIN_USER_SUCCESSFUL_MSG) + " You Enter:" + user.get().getEmail();
				request.getSession().setAttribute("user", user.get());
			    if(user.get().getRole()!= UserRoles.USER){
					request.setAttribute(USER_LIST_ATTRIBUTE, userService.getAll());
					pageToGo = "/WEB-INF/admin/users.jsp";
				}
			} else {
				result = Localization.getInstanse().getLocalizedMessage(request, LOGIN_USER_ERROR_MSG);
			}
			request.setAttribute(RESULT_ATTRIBUTE, result);
		}
		System.out.println(pageToGo);
		return pageToGo;
	}
}

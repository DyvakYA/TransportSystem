package controller.commands.user;

import controller.commands.Command;
import model.entities.User;
import model.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllUsersCommand implements Command {

	private static final String USERS_LIST_ATTRIBUTE = "usersList";
	private UserService userService = UserService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> users = userService.getAllUsers();
		request.setAttribute(USERS_LIST_ATTRIBUTE, users);
		return "/WEB-INF/admin/users.jspx";
	}
	
}

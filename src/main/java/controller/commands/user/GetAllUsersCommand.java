package controller.commands.user;

import controller.commands.Command;
import model.entities.User;
import model.services.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class GetAllUsersCommand implements Command {

	private static final String USERS_LIST_ATTRIBUTE = "usersList";
	private UserService userService = UserService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		List<Optional<User>> users = userService.getAll();
		request.setAttribute(USERS_LIST_ATTRIBUTE, users);
		return "/WEB-INF/admin/users.jspx";
	}
	
}

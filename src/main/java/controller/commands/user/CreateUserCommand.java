package controller.commands.user;

import controller.commands.validators.user.CreateUserCommandValidator;
import model.entities.User;
import model.entities.enums.UserRoles;
import model.extras.Localization;
import model.services.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateUserCommand implements UserCommand {

    private UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        if (!new CreateUserCommandValidator().validate(request, response)) {
            return "";
        }
        User user = new User.Builder()
                .setName(request.getParameter("name"))
                .setSurname(request.getParameter("surname"))
                .setEmail(request.getParameter("email"))
                .setPasswordHash(request.getParameter("password"))
                .setRole(UserRoles.valueOf(request.getParameter("role")))
                .build();
        userService.create(user);
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, CREATE_USER_SUCCESSFUL_MSG ));
        request.setAttribute(USER_LIST_ATTRIBUTE, userService.getAll());
        return "/WEB-INF/admin/users.jsp";
    }
}

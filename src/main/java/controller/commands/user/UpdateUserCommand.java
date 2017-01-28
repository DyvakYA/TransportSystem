package controller.commands.user;

import controller.commands.Command;
import controller.commands.validators.user.UpdateUserCommandValidator;
import model.entities.User;
import model.entities.enums.UserRoles;
import model.extras.Localization;
import model.services.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateUserCommand implements UserCommand {

    private UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        if (!new UpdateUserCommandValidator().validate(request, response)) {
            return null;
        }
        User user = new User.Builder()
                .setId(Integer.parseInt(request.getParameter("user_id")))
                .setName(request.getParameter("name"))
                .setSurname(request.getParameter("surname"))
                .setEmail(request.getParameter("email"))
                .setPasswordHash(request.getParameter("password"))
                .setRole(UserRoles.valueOf(request.getParameter("role")))
                .build();
        userService.update(user, user.getId());
        request.setAttribute(Command.RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, UPDATE_USER_SUCCESSFUL_MSG));
        request.setAttribute(USER_LIST_ATTRIBUTE, userService.getAll());
        return "/WEB-INF/admin/users.jsp";
    }

}

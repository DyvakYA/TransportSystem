package controller.commands.user;

import controller.commands.validators.user.DeleteUserCommandValidator;
import model.extras.Localization;
import model.services.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteUserCommand implements UserCommand {

    private static final String DELETE_USER_SUCCESSFUL_MSG = "DeleteUserSuccessful" ;
    private UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        if (!new DeleteUserCommandValidator().validate(request, response)) {
            return null;
        }
        int userId = Integer.valueOf(request.getParameter(USER_ID_ATTRIBUTE));
        userService.delete(userId);
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
            .getLocalizedMessage(request, DELETE_USER_SUCCESSFUL_MSG));
        request.setAttribute(USER_LIST_ATTRIBUTE, userService.getAll());
        return "/WEB-INF/admin/users.jsp";

    }

}

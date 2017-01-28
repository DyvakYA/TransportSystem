package controller.commands.user;

import controller.commands.Command;
import controller.commands.validators.user.RegisterUserCommandValidator;
import model.entities.User;
import model.entities.enums.UserRoles;
import model.extras.Localization;
import model.services.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static controller.servlet.MainController.LOGGER_NAME;

public class RegisterUserCommand implements Command {

    public static final String EMAIL_ATTRIBUTE = "email";

    public static final String PASSWORD_ATTRIBUTE = "password";

    public static final String CONFIRM_PASSWORD_ATTRIBUTE = "confirmPassword";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String SURNAME_ATTRIBUTE = "surname";

    public static final String ROLE_ATTRIBUTE = "role";

    public static final String RESULT_ATTRIBUTE = "result";

    public static final String DESTINATION_PAGE = "./registration.jsp";
    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String REGISTER_USER_ERROR_MSG = "RegisterUserError";
    private static final String REGISTER_USER_SUCCESSFUL_MSG = "RegisterUserSuccessful";

    private UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        if (!new RegisterUserCommandValidator().validate(request, response)) {

            return String.valueOf(response);
        }
        System.out.println("---------------------------");

        String email = request.getParameter(EMAIL_ATTRIBUTE);
        String password = request.getParameter(PASSWORD_ATTRIBUTE);

        if (userService.getByEmail(email).equals(Optional.empty())) {
            User user = new User.Builder()
                    .setName(request.getParameter("name"))
                    .setSurname(request.getParameter("surname"))
                    .setEmail(request.getParameter("email"))
                    .setPasswordHash(request.getParameter("password"))
                    .setRole(UserRoles.USER)
                    .build();
            System.out.println(user);
            userService.create(user);

            request.setAttribute(RESULT_ATTRIBUTE,
                    Localization.getInstanse().
                    getLocalizedMessage(request, REGISTER_USER_SUCCESSFUL_MSG) + email);

        } else {
            request.setAttribute(RESULT_ATTRIBUTE,
                    Localization.getInstanse().
                    getLocalizedMessage(request, REGISTER_USER_ERROR_MSG) + email);
        }
        try {
            request.getRequestDispatcher(DESTINATION_PAGE).forward(request,
                    response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
        return email;
    }

}

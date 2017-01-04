package model.commands.user;

import model.commands.Command;
import model.commands.validators.user.RegisterUserCommandValidator;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import model.extras.Localization;
import model.entities.enums.UserRoles;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.servlet.TransportServlet.LOGGER_NAME;


public class RegisterUserCommand implements Command {

    public static final String LOGIN_ATTRIBUTE = "login";

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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new RegisterUserCommandValidator().validate(request, response)) {
            return;
        }

        UserDao userDao = DaoFactory.getInstance().createUserDao();

        String login = request.getParameter(LOGIN_ATTRIBUTE);

        String password = request.getParameter(PASSWORD_ATTRIBUTE);

        if (userDao.findByLogin(login) == null) {
            userDao.create(new User(
                    login,
                    request.getParameter(NAME_ATTRIBUTE),
                    request.getParameter(SURNAME_ATTRIBUTE),
                    password,
                    UserRoles.valueOf(request.getParameter(ROLE_ATTRIBUTE).toUpperCase())));

            request.setAttribute(RESULT_ATTRIBUTE,
                    Localization.getInstanse().
                            getLocalizedMessage(request, REGISTER_USER_SUCCESSFUL_MSG) + login);

        } else {
            request.setAttribute(RESULT_ATTRIBUTE,
                    Localization.getInstanse().
                    getLocalizedMessage(request, REGISTER_USER_ERROR_MSG) + login);
        }
        try {
            request.getRequestDispatcher(DESTINATION_PAGE).forward(request,
                    response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }

}

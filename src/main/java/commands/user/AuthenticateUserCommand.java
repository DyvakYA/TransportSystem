package commands.user;

import commands.Command;
import commands.validators.user.AuthenticateUserCommandValidator;
import dao.DaoFactory;
import dao.UserDao;
import dao.jdbc.JdbcDaoFactory;
import entities.User;
import extras.Localization;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlet.TransportServlet.LOGGER_NAME;

public class AuthenticateUserCommand implements Command {

    public static final String USER_SESSION_ATTRIBUTE = "user";
    public static final String RESULT_PAGE = "/userLogged.jsp";
    public static final String RESULT_ATTRIBUTE = "result";
    public static final String PASSWORD_ATTRIBUTE = "password";
    public static final String LOGIN_ATTRIBUTE = "login";
    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String AUTHENTICATE_USER_ERROR_MSG = "AuthenticateUserError";
    private static final String AUTHENTICATE_USER_SUCCESSFUL_MSG = "AuthenticateUserSuccessful";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new AuthenticateUserCommandValidator().validate(request, response)) {
            return;
        }

        String result = null;
        String login = request.getParameter(LOGIN_ATTRIBUTE);
        String password = request.getParameter(PASSWORD_ATTRIBUTE);

        DaoFactory factory = JdbcDaoFactory.getInstance();
        UserDao userDao = factory.createUserDao();
        User user = userDao.authentication(login, User.calcPasswordHash(password));
        if (user != null) {
            result = Localization.getInstanse().getLocalizedMessage(request, AUTHENTICATE_USER_SUCCESSFUL_MSG) + user.getLogin();
            request.getSession().setAttribute(USER_SESSION_ATTRIBUTE, user);
        } else {
            result = Localization.getInstanse().getLocalizedMessage(request, AUTHENTICATE_USER_ERROR_MSG);
        }
        request.setAttribute(RESULT_ATTRIBUTE, result);

        try {
            request.getRequestDispatcher('.' + RESULT_PAGE)
                    .forward(request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }

    }

}

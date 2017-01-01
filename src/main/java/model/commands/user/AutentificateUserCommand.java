package model.commands.user;

import model.commands.Command;
import model.commands.validators.user.AutentificateUserCommandValidator;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dao.jdbc.JdbcDaoFactory;
import model.entities.users.User;
import model.extras.Localization;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.servlets.TransportServlet.LOGGER_NAME;

/**
 *
 * @author KIRIL
 */
public class AutentificateUserCommand implements Command {

    public static final String USER_SESSION_ATTRIBUTE = "user";
    public static final String RESULT_PAGE = "/userLogged.jsp";
    public static final String RESULT_ATTRIBUTE = "result";
    public static final String PASSWORD_ATTRIBUTE = "password";
    public static final String LOGIN_ATTRIBUTE = "login";
    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String AUTENTIFY_USER_ERROR_MSG = "AutentifyUserError";
    private static final String AUTENTIFY_USER_SUCCESSFUL_MSG = "AutentifyUserSuccessful";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (!new AutentificateUserCommandValidator().validate(request, response)) {
            return;
        }

        String result = null;
        String login = request.getParameter(LOGIN_ATTRIBUTE);
        String password = request.getParameter(PASSWORD_ATTRIBUTE);


        DaoFactory factory = JdbcDaoFactory.getInstance();
        UserDao userDao = factory.createUserDao();
        User user = userDao.autentify(login, User.calcPasswordHash(password));
        if (user != null) {
            result = Localization.getInstanse().getLocalizedMessage(request, AUTENTIFY_USER_SUCCESSFUL_MSG) + user.getLogin();
            request.getSession().setAttribute(USER_SESSION_ATTRIBUTE, user);
        } else {
            result = Localization.getInstanse().getLocalizedMessage(request, AUTENTIFY_USER_ERROR_MSG);
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

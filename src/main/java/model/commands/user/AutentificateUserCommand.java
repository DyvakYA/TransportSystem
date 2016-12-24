package model.commands.user;

import org.apache.log4j.Logger;
import ua.kpi.epam.transport.commands.Command;
import ua.kpi.epam.transport.commands.validators.user.AutentificateUserCommandValidator;
import ua.kpi.epam.transport.dao.DaoFactory;
import ua.kpi.epam.transport.dao.UserDao;
import ua.kpi.epam.transport.dao.jdbc.JdbcDaoFactory;
import ua.kpi.epam.transport.entities.User;
import ua.kpi.epam.transport.extras.LocalizationHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.kpi.epam.transport.servlets.TransportServlet.LOGGER_NAME;

/**
 *
 * @author KIRIL
 */
public class AutentificateUserCommand implements Command {

    /**
     *
     */
    public static final String USER_SESSION_ATTRIBUTE = "user";

    /**
     *
     */
    public static final String RESULT_PAGE = "/autentification.jsp";

    /**
     *
     */
    public static final String RESULT_ATTRIBUTE = "result";

    /**
     *
     */
    public static final String PASSWORD_ATTRIBUTE = "password";

    /**
     *
     */
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
            result = LocalizationHelper.getInstanse().getLocalizedMessage(request, AUTENTIFY_USER_SUCCESSFUL_MSG) + user.getLogin();
            request.getSession().setAttribute(USER_SESSION_ATTRIBUTE, user);
        } else {
            result = LocalizationHelper.getInstanse().getLocalizedMessage(request, AUTENTIFY_USER_ERROR_MSG);
        }
        request.setAttribute(RESULT_ATTRIBUTE, result);

        try {
            request.getRequestDispatcher('.' + RESULT_PAGE)
                    .forward(request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }
}

package model.commands.user;

import model.commands.Command;
import model.dao.DaoFactory;
import model.entities.users.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author KIRIL
 */
public class FindAllUsersCommand implements Command {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String DESTINATION_PAGE = "./index.jsp";
    private static final String USER_LIST = "userList";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        UserDao userDao = DaoFactory.getInstance().createUserDao();

        List<User> users = userDao.findAll();

        request.setAttribute(USER_LIST, users);
        try {
            request.getRequestDispatcher(DESTINATION_PAGE).forward(request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(LocalizationHelper.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }

}

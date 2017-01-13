package commands.user;

import commands.Command;
import dao.DaoFactory;
import dao.UserDao;
import entities.User;
import extras.Localization;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static servlet.TransportServlet.LOGGER_NAME;

public class FindAllUsersCommand implements Command {

    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String DESTINATION_PAGE = "./index2.jsp";
    private static final String USER_LIST = "userList";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        UserDao userDao = DaoFactory.getInstance().createUserDao();

        List<User> users = userDao.findAll();

        request.setAttribute(USER_LIST, users);
        try {
            request.getRequestDispatcher(DESTINATION_PAGE).forward(request, response);
        } catch (ServletException | IOException e) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), e);
        }
    }
}

package controller.commands.user;

import controller.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dyvak on 26.01.2017.
 */
public class LogoutCommand implements Command {

    @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getSession().invalidate();
            response.sendRedirect(INDEX_JSP);
            return "REDIRECT";
        }
    }


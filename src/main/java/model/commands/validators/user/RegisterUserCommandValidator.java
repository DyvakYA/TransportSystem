package model.commands.validators.user;

import model.commands.validators.CommandValidator;
import model.extras.Localization;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.commands.user.RegisterUserCommand.*;
import static model.servlets.TransportServlet.LOGGER_NAME;

public class RegisterUserCommandValidator implements CommandValidator {

    private static final String PASSWORD_DIFFER_ERROR_MSG = "NotValidPasswordAndPasswordConfirmationMsg";
    private static final String NOT_VALID_LOGIN_ERROR_MSG = "NotValidLogin";
    private static final String NOT_VALID_PASSWORD_ERROR_MSG = "NotValidPassword";
    private static final String LOGIN_PATTERN = "^[a-z0-9_-]{3,16}$";
    private static final String PASSWORD_PATTERN = "^[a-z0-9_-]{6,18}$";
    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        boolean result = true;
        String password = request.getParameter(PASSWORD_ATTRIBUTE);
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD_ATTRIBUTE);
        String login = request.getParameter(LOGIN_ATTRIBUTE);

        Localization helper = Localization.getInstanse();
        String passwordDifferMessage = helper.getLocalizedMessage(request, PASSWORD_DIFFER_ERROR_MSG);
        String badLoginMessage = helper.getLocalizedMessage(request, NOT_VALID_LOGIN_ERROR_MSG);
        String badPasswordMessage = helper.getLocalizedMessage(request, NOT_VALID_PASSWORD_ERROR_MSG);

        if (!password.equals(confirmPassword)) {
            request.setAttribute(RESULT_ATTRIBUTE, passwordDifferMessage);
            forward(request, response);
            result = false;
        } else if (!login.matches(LOGIN_PATTERN)) {
            request.setAttribute(RESULT_ATTRIBUTE, badLoginMessage);
            forward(request, response);
            result = false;
        } else if (!password.matches(PASSWORD_PATTERN)) {
            request.setAttribute(RESULT_ATTRIBUTE, badPasswordMessage);
            forward(request, response);
            result = false;
        }
        return result;
    }

    private void forward(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher(DESTINATION_PAGE).forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION));
        }
    }
}

package controller.commands.validators.user;

import controller.commands.validators.CommandValidator;
import model.extras.Localization;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controller.servlet.MainController.LOGGER_NAME;

public class RegisterUserCommandValidator implements CommandValidator {

    private static final String PASSWORD_DIFFER_ERROR_MSG = "NotValidPasswordAndPasswordConfirmationMsg";
    private static final String NOT_VALID_LOGIN_ERROR_MSG = "NotValidLogin";
    private static final String NOT_VALID_PASSWORD_ERROR_MSG = "NotValidPassword";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_PATTERN = "^[a-z0-9_-]{6,18}$";
    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String CONFIRM_PASSWORD_ATTRIBUTE = "confirmPassword";
    private static final String RESULT_ATTRIBUTE = "result";
    private static final String DESTINATION_PAGE = "registration.jsp";

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        boolean result = true;
        String password = request.getParameter(PASSWORD_ATTRIBUTE);
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD_ATTRIBUTE);
        String email = request.getParameter(EMAIL_ATTRIBUTE);

        Localization localization = Localization.getInstanse();
        String passwordDifferMessage = localization.getLocalizedMessage(request, PASSWORD_DIFFER_ERROR_MSG);
        String badLoginMessage = localization.getLocalizedMessage(request, NOT_VALID_LOGIN_ERROR_MSG);
        String badPasswordMessage = localization.getLocalizedMessage(request, NOT_VALID_PASSWORD_ERROR_MSG);

        if (!password.equals(confirmPassword)) {
            request.setAttribute(RESULT_ATTRIBUTE, passwordDifferMessage);
            System.out.println("1");
            forward(request, response);
            result = false;
        } else if (!email.matches(EMAIL_PATTERN)) {
            request.setAttribute(RESULT_ATTRIBUTE, badLoginMessage);
            System.out.println("2");
            forward(request, response);
            result = false;
        } else if (!password.matches(PASSWORD_PATTERN)) {
            request.setAttribute(RESULT_ATTRIBUTE, badPasswordMessage);
            System.out.println("3");
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

package controller.commands.user;

import controller.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class ChangeLocaleCommand implements Command {

    public static final String LOCALE_ATTRIBUTE = "userLocale";
    private static final String DESTINATION_PAGE = "/authentication.jsp";
    private static final String RU_COUNTRY = "RU";
    private static final String RU_LANGUAGE = "ru";
    private static final String US_COUNTRY = "US";
    private static final String EN_LANGUAGE = "en";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Locale locale = null;

        if (session == null) {

            session = request.getSession();
            session.setAttribute(LOCALE_ATTRIBUTE, changeLocale(request.getLocale()));
        } else if (session.getAttribute(LOCALE_ATTRIBUTE) == null) {
            session.setAttribute(LOCALE_ATTRIBUTE, changeLocale(request.getLocale()));
        } else if (request.getSession(false) != null && session.getAttribute(LOCALE_ATTRIBUTE) != null) {
            session.setAttribute(LOCALE_ATTRIBUTE, changeLocale((Locale) session.getAttribute(LOCALE_ATTRIBUTE)));
        }


            request.getRequestDispatcher(DESTINATION_PAGE).forward(request, response);

        return null;
    }

    private Locale changeLocale(Locale locale) {
        if (locale.equals(new Locale(EN_LANGUAGE, US_COUNTRY))) {
            return new Locale(RU_LANGUAGE, RU_COUNTRY);
        } else if (locale.equals(new Locale(RU_LANGUAGE, RU_COUNTRY))) {
            return new Locale(EN_LANGUAGE, US_COUNTRY);
        }
        return null;

    }
}

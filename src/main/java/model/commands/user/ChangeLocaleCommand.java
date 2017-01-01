/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.commands.user;

import org.apache.log4j.Logger;
import model.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

import static model.servlets.TransportServlet.LOGGER_NAME;

/**
 *
 * @author KIRIL
 */
public class ChangeLocaleCommand implements Command {

    /**
     *
     */
    public static final String LOCALE_ATTRIBUTE = "userLocale";
    private static final String DESTINATION_PAGE = "/autentification.jsp";
    private static final String SERVLET_EXCEPTION = "ForwardRequestServletException";
    private static final String RU_COUNTRY = "RU";
    private static final String RU_LANGUAGE = "ru";
    private static final String US_COUNTRY = "US";
    private static final String EN_LANGUAGE = "en";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

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

        try {
            request.getRequestDispatcher(DESTINATION_PAGE).forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger logger = (Logger) request.getServletContext().getAttribute(LOGGER_NAME);
            logger.error(model.extras.Localization.getInstanse().getLocalizedErrorMsg(SERVLET_EXCEPTION), ex);
        }
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

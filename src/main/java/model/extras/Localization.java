package model.extras;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

import static controller.commands.user.ChangeLocaleCommand.LOCALE_ATTRIBUTE;

public class Localization {

    public static Localization instance = new Localization();

    private static final String MSG_PROPERTIE_BASE_PATH = "messages";
    private static final String LABEL_PROPERTIE_BASE_PATH = "labels";
    private static final String ERROR_MSG_PROPERTIE_BASE_PATH = "/error_messages";

    private Localization() {

    }

    public static Localization getInstanse() {
        return instance;
    }

    public String getLocalizedMessage(HttpServletRequest request, String resourceName) {
        return getString(request, resourceName, MSG_PROPERTIE_BASE_PATH);
    }

    public String getLocalizedErrorMsg(String resourceName) {
        return ResourceBundle.getBundle(ERROR_MSG_PROPERTIE_BASE_PATH).getString(resourceName);
    }

    public String getLocalizedLabel(HttpServletRequest request, String resourceName) {
        return getString(request, resourceName, LABEL_PROPERTIE_BASE_PATH);
    }


    public String getString(HttpServletRequest request, String resourceName, String propertiePath) {

        HttpSession session = request.getSession(false);

        Locale locale = null;
        if (session != null && session.getAttribute(LOCALE_ATTRIBUTE) != null) {
            locale = (Locale) session.getAttribute(LOCALE_ATTRIBUTE);
        } else {
            locale = request.getLocale();
        }
        return ResourceBundle.getBundle(propertiePath, locale).getString(resourceName);
    }
}
